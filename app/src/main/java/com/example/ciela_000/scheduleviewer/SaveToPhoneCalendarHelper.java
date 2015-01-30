package com.example.ciela_000.scheduleviewer;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CalendarContract;
import android.util.Log;

import java.util.Calendar;
import java.util.TimeZone;

import static android.provider.CalendarContract.Events.*;

/**
 * Created by ciela_000 on 1/15/2015.
 */
public class SaveToPhoneCalendarHelper
{
    private static final String calendarTitle = "ER Shift";
    private static final long defaultCalendarId = 1;

    private Context ctx;

    public SaveToPhoneCalendarHelper(Context context)
    {
        this.ctx = context;
    }

    public void addEvent(ScheduleData data) {

        ContentResolver cr = this.ctx.getContentResolver();
        Uri uri = cr.insert(CONTENT_URI, getICSContentValues(data));
        System.out.println("Event URI ["+uri+"]");
    }

    private ContentValues getICSContentValues(ScheduleData data) {

        ContentValues cv = new ContentValues();
        cv.put(CALENDAR_ID, defaultCalendarId);
        cv.put(TITLE, calendarTitle);
        cv.put(DESCRIPTION, data.getShift().getName());
        cv.put(DTSTART, data.getStartTime().getTimeInMillis());
        cv.put(DTEND, data.getEndTime().getTimeInMillis());

        Calendar cal = Calendar.getInstance();
        TimeZone tz = cal.getTimeZone();

        cv.put(EVENT_TIMEZONE, tz.getDisplayName());

        return cv;
    }
}
