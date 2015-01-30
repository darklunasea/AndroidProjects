package com.example.ciela_000.scheduleviewer;

import java.util.Calendar;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by ciela_000 on 1/12/2015.
 */
public class ScheduleProcessor
{
    public static final String TIME_FORMAT_INDICATOR = ".";
    public static final String TIME_FORMAT_SPLITTER = " - ";
    public static final String END_OF_LINE = "\\r?\\n";
    public static final String NAME_FIELD_INDICATOR = " ";
    public static final String TIME_FORMAT_AM = "A";
    public static final String TIME_FORMAT_PM = "P";

    private enum TimeType
    {
        START,
        END,
    }

    public Map<Integer, ScheduleData> processRawScheduleData(String rawScheduleData, String name, int month)
    {
        Map<Integer, ScheduleData> scheduleDataMap = new TreeMap<Integer, ScheduleData>();

        String lines[] = rawScheduleData.split(END_OF_LINE);
        int latestDate = 1;
        for(String line : lines)
        {
            int date = isDate(line);
            if(date > 0)
            {
                //it's the date indicator line
                if(!scheduleDataMap.containsKey(date))
                {
                    ScheduleData data = new ScheduleData();
                    data.setDate(date);
                    scheduleDataMap.put(date, data);
                }
                latestDate = date;
            }
            else
            {
                if(isUsersSchedule(name, line))
                {
                    ScheduleData data = scheduleDataMap.get(latestDate);
                    data.setHasSchedule(true);

                    String rawTime = line.substring(name.length() + 1).trim();
                    String[] rawTimes = rawTime.split(TIME_FORMAT_SPLITTER);
                    String rawStartTime = rawTimes[0];
                    String rawEndTime = "11.59P";
                    if(rawTimes.length > 1)
                    {
                        rawEndTime = rawTimes[1];
                    }
                    Calendar startTime = getDateFromRawTime(TimeType.START, rawStartTime, month, latestDate);
                    Calendar endTime = getDateFromRawTime(TimeType.END, rawEndTime, month, latestDate);

                    data.setStartTime(startTime);
                    data.setEndTime(endTime);
                }
            }
        }

        return scheduleDataMap;
    }

    private boolean isUsersSchedule(String name, String line)
    {
        try
        {
            int spcPtr = line.indexOf(NAME_FIELD_INDICATOR);
            if (spcPtr == 0) return false;
            String lineName = line.substring(0, spcPtr);
            return lineName.equalsIgnoreCase(name);
        }
        catch(Exception e)
        {
            return false;
        }
    }

    private Calendar getDateFromRawTime(TimeType timeType, String rawTime, int month, int date)
    {
        try
        {
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.MONTH, month);
            cal.set(Calendar.DATE, date);

            int hour = 0;
            int min = 0;
            if (rawTime.contains(".")) {
                int dotPtr = rawTime.indexOf(TIME_FORMAT_INDICATOR);
                String hourStr = rawTime.substring(0, dotPtr);
                hour = Integer.parseInt(hourStr);
                String minStr = rawTime.substring(dotPtr + 1, rawTime.length() - 1);
                min = Integer.parseInt(minStr);
            } else {
                String hourStr = rawTime.substring(0, rawTime.length() - 1);
                hour = Integer.parseInt(hourStr);
            }
            cal.set(Calendar.HOUR, hour);
            cal.set(Calendar.MINUTE, min);

            if (rawTime.contains(TIME_FORMAT_AM)) {
                cal.set(Calendar.AM_PM, Calendar.AM);
                if(TimeType.END.equals(timeType))
                {
                    cal.set(Calendar.DATE, date + 1);
                }
            }
            if (rawTime.contains(TIME_FORMAT_PM)) {
                cal.set(Calendar.AM_PM, Calendar.PM);
            }

            return cal;
        }
        catch(Exception e)
        {
            return null;
        }
    }

    private int isDate(String line)
    {
        try
        {
            return Integer.parseInt(line.trim());
        }
        catch(NumberFormatException nfe)
        {
            return -1;
        }
    }
}
