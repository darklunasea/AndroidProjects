package com.example.ciela_000.scheduleviewer;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ciela_000 on 1/14/2015.
 */
public enum Shift
{
    BLANK("", 0,0),
    OFF("Off", 0,0),

    DAY1("Day Shift", 6,18),
    DAY2("Day Shift", 7,19),

    FASTDAY1("Day Fast Track", 9,17),
    FASTDAY2("Day Fast Track",9,19),

    MID1("Mid Shift", 11,11),
    MID2("Mid Shift", 13,1),

    FASTMID1("Mid Fast Track", 15,1),
    FASTMID2("Mid Fast Track", 17,1),

    NIGHT1("Night Shift", 18,6),
    NIGHT2("Night Shift", 19,7);

    private String name;
    private int startTime;
    private int endTime;

    private Shift(String name, int startTime, int endTime)
    {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
    }
    public int getStartTime()
    {
        return startTime;
    }
    public int getEndTime()
    {
        return endTime;
    }
    public String getName() { return name; }

    public boolean is(Calendar start, Calendar end)
    {
        int startHour = start.getTime().getHours();
        int endHour = end.getTime().getHours();
        return (startHour == startTime && endHour == endTime);
    }

    private static Map<Integer, Shift> shiftMap = new HashMap<Integer, Shift>();
    static
    {
        for(Shift shift : Shift.values())
        {
            shiftMap.put(shift.getStartTime(), shift);
        }
    }

    public static Shift getShift(int startTime)
    {
        return shiftMap.get(startTime);
    }

    public boolean in(Shift ...shifts)
    {
        for(Shift shift : shifts)
        {
            if(this.equals(shift))
            {
                return true;
            }
        }
        return false;
    }
}
