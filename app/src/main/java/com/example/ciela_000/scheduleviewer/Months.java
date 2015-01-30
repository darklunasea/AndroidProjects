package com.example.ciela_000.scheduleviewer;

/**
 * Created by ciela_000 on 1/15/2015.
 */
public class Months
{
    private static final String[] months = new String[]{"JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"};

    public static String getMonth(int monthOffset)
    {
        return months[monthOffset];
    }
}
