package com.example.ciela_000.scheduleviewer;

import android.graphics.Bitmap;
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
public class SaveScheduleToImageHelper
{
    private static final String SAVE_FOLDER= "ER_Schedules";
    private static final String FILE_NAME_BASE= "Schedule_";

    public File saveViewImage(View view, String userName, String month) throws Exception
    {
        if(!checkFolderPath())
        {
            throw new Exception("Unable to create folder " + SAVE_FOLDER + " on external storage directory");
        }

        String fileName = getImageName(userName, month);
        // image naming and path  to include sd card  appending name you choose for file
        String mPath = Environment.getExternalStorageDirectory().toString() + "/" + SAVE_FOLDER + "/" + fileName;

        // create bitmap screen capture
        Bitmap bitmap;
        view.setDrawingCacheEnabled(true);
        bitmap = Bitmap.createBitmap(view.getDrawingCache());
        view.setDrawingCacheEnabled(false);

        File imageFile = new File(mPath);

        OutputStream fout = new FileOutputStream(imageFile);
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, fout);
        fout.flush();
        fout.close();

        return imageFile;
    }

    private String getImageName(String userName, String month)
    {
        return FILE_NAME_BASE + "_" + userName + "_" + month + ".png";
    }

    private boolean checkFolderPath() {
        File folder = new File(Environment.getExternalStorageDirectory() + "/" + SAVE_FOLDER);
        boolean success = true;
        if (!folder.exists()) {
            success = folder.mkdir();
        }
        return success;
    }
}
