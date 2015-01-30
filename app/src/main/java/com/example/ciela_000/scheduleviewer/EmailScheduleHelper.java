package com.example.ciela_000.scheduleviewer;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by ciela_000 on 1/14/2015.
 */
public class EmailScheduleHelper
{
    public Intent getEmailScheduleIntent(View view, String userName, String month) throws Exception
    {
        File image = saveImage(view, userName, month);
        Uri imageUri = Uri.fromFile(image);
        Intent send_report = new Intent(Intent.ACTION_SEND);
        send_report.putExtra(Intent.EXTRA_EMAIL, new String[]{});
        send_report.putExtra(Intent.EXTRA_SUBJECT, getEmailSubject(userName, month));
        send_report.putExtra(Intent.EXTRA_STREAM, imageUri);
        send_report.putExtra(Intent.EXTRA_TEXT, getEmailBody(month));
        send_report.setType("text/plain");
        send_report.setType("image/png");

        return send_report;
    }

    private String getEmailSubject(String userName, String month)
    {
        return userName + "'s ER Schedule of " +  month;
    }

    private String getEmailBody(String month)
    {
        String content = "Please check out my schedule of " + month + ".\n";
        String detail = "- Symbol '*' indicates a day on shift\n"
                + "- Green cell is off shift\n"
                + "- Yellow cell is day shift on fast track\n"
                + "- Orange cell is ordinary day shift\n"
                + "- Pink cell is mid-day shift on fast track\n"
                + "- Red cell is ordinary mid-day shift\n"
                + "- Blue cell is night shift\n"
                ;
        return content + detail;
    }

    private File saveImage(View view, String userName, String month) throws Exception
    {
        try {
            SaveScheduleToImageHelper imgHelper = new SaveScheduleToImageHelper();
            return imgHelper.saveViewImage(view, userName, month);
        }
        catch(Exception e)
        {
            throw new Exception("Unable to save schedule." + e.getMessage());
        }
    }
}
