package com.example.ciela_000.scheduleviewer;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ciela_000 on 1/12/2015.
 */
public class ScheduleData
{
    private int date;
    private boolean hasSchedule;
    private Calendar startTime;
    private Calendar endTime;
    private boolean isConstant;
    private String constantValue;

    public ScheduleData()
    {

    }

    public ScheduleData(int date)
    {
        this.date = date;
    }

    public ScheduleData(boolean isConstant, String constantValue)
    {
        this.isConstant = isConstant;
        this.constantValue = constantValue;
    }

    public void setDate(int date)
    {
        this.date = date;
    }

    public int getDate()
    {
        return date;
    }

    public void setHasSchedule(boolean hasSchedule)
    {
        this.hasSchedule = hasSchedule;
    }

    public boolean isHasSchedule()
    {
        return hasSchedule;
    }

    public void setStartTime(Calendar startTime)
    {
        this.startTime = startTime;
    }

    public Calendar getStartTime()
    {
        return startTime;
    }

    public void setEndTime(Calendar endTime)
    {
        this.endTime = endTime;
    }

    public Calendar getEndTime()
    {
        return endTime;
    }

    public boolean isConstant()
    {
        return isConstant;
    }

    public String getConstantValue()
    {
        return constantValue;
    }

    public Shift getShift()
    {
        if(isConstant) return Shift.BLANK;
        if(!hasSchedule) return Shift.OFF;
        return Shift.getShift(startTime.getTime().getHours());
    }

    public boolean isBlankShift()
    {
        return getShift().in(Shift.BLANK);
    }

    public boolean isOffShift()
    {
        return getShift().in(Shift.OFF);
    }

    public boolean isDayShift()
    {
        return getShift().in(Shift.DAY1, Shift.DAY2);
    }

    public boolean isFastDayShift()
    {
        return getShift().in(Shift.FASTDAY1, Shift.FASTDAY2);
    }

    public boolean isMidShift()
    {
        return getShift().in(Shift.MID1, Shift.MID2);
    }

    public boolean isFastMidShift()
    {
        return getShift().in(Shift.FASTMID1, Shift.FASTMID2);
    }

    public boolean isNightShift()
    {
        return getShift().in(Shift.NIGHT1, Shift.NIGHT2);
    }

    public String getBriefData()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(String.valueOf(date));
        sb.append("\n");
        if(hasSchedule)
        {
            sb.append("*");
        }
        return sb.toString();
    }

    public String getTimeData()
    {
        try
        {
            if (!hasSchedule) {
                return "";
            }
            StringBuilder sb = new StringBuilder();
            SimpleDateFormat sdf = new SimpleDateFormat("KK:mm aa");
            sb.append(sdf.format(startTime.getTime()));
            sb.append(" - ");
            sb.append(sdf.format(endTime.getTime()));
            return sb.toString();
        }
        catch(Exception e)
        {
            return "Unable to get Time. Please make sure format is correct.";
        }
    }

}
