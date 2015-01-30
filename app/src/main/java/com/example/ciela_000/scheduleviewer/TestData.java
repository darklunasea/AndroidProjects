package com.example.ciela_000.scheduleviewer;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.Context;
import android.support.v7.app.ActionBarActivity;

/**
 * Created by ciela_000 on 1/21/2015.
 */
public class TestData  extends ActionBarActivity {
    String testStr = "NES HEALTH\n" +
            "\n" +
            "Trinitas Regional Medical Center - January , 2015\t\n" +
            "Sun\tMon\tTue\tWed\tThu\tFri\tSat\n" +
            "1 \n" + "Matossian 6.30A - 6.30P\n" + "D'Angelo 7.30A - 7.30P\n" + "Heslinga 9A - 9P\n" + "Wolslau 1P - 1A\n" + "Haig 6.30P - 6.30A\n" + "Charleton 7.30P - 7.30A\n" +
            "2 \n" + "DeLaCalzada-Je 6.30A - 6.30P\n" + "D'Angelo 7.30A - 7.30P\n" + "Letizia 9A - 5P\n" + "Fan 11A - 11P\n" + "Chirino 5P - 1A\n" + "Khamis 6.30P - 6.30A\n" + "Wolslau 7.30P - 7.30A\n" +
            "3 \n" + "Leavell 6.30A - 6.30P\n" + "DeLaCalzada-Je 7.30A - 7.30P\n" + "Askari 9A - 9P\n" + "Fan 1P - 1A\n" + "Vizzuso 6.30P - 6.30A\n" + "Khamis 7.30P - 7.30A\n" +
            "4 \n" + "Leavell 6.30A - 6.30P\n" + "Matossian 7.30A - 7.30P\n" + "Hakim 9A - 9P\n" + "Fan 1P - 1A\n" + "Vizzuso 6.30P - 6.30A\n" + "Askari 7.30P - 7.30A\n" +
            "5 \n" + "Letizia 6.30A - 6.30P\n" + "Zhang 7.30A - 7.30P\n" + "Heslinga 9A - 7P\n" + "Chirino 11A - 11P\n" + "Hakim 3P - 1A\n" + "Charleton 6.30P - 6.30A\n" + "Askari 7.30P - 7.30A\n" +
            "6 \n" + "Letizia 6.30A - 6.30P\n" + "Matossian 7.30A - 7.30P\n" + "D'Angelo 9A - 7P\n" + "Heslinga 11A - 11P\n" + "Chirino 3P - 1A\n" + "Wolslau 6.30P - 6.30A\n" + "Charleton 7.30P - 7.30A\n" +
            "7 \n" + "Vizzuso 6.30A - 6.30P\n" + "Leavell 7.30A - 7.30P\n" + "DeLaCalzada-Je 9A - 5P\n" + "Heslinga 11A - 11P\n" + "Zaboski 5P - 1A\n" + "Haig 6.30P - 6.30A\n" + "Wolslau 7.30P - 7.30A\n" +
            "8 \n" + "Askari 6.30A - 6.30P\n" + "Vizzuso 7.30A - 7.30P\n" + "Chirino 9A - 5P\n" + "DeLaCalzada-Je 11A - 11P\n" + "Khamis 5P - 1A\n" + "Haig 6.30P - 6.30A\n" + "Wolslau 7.30P - 7.30A\n" +
            "9 \n" + "Leavell 6.30A - 6.30P\n" + "Fan 7.30A - 7.30P\n" + "Letizia 9A - 5P\n" + "Hakim 11A - 11P\n" + "Khamis 5P - 1A\n" + "Zaboski 6.30P - 6.30A\n" + "Charleton 7.30P - 7.30A\n" +
            "10 \n" + "Askari 6.30A - 6.30P\n" + "Fan 7.30A - 7.30P\n" + "Chirino 9A - 9P\n" + "Hakim 1P - 1A\n" + "Zaboski 6.30P - 6.30A\n" + "Charleton 7.30P - 7.30A\n" +
            "11 \n" + "Zhang 6.30A - 6.30P\n" + "Letizia 7.30A - 7.30P\n" + "Heslinga 9A - 9P\n" + "Hakim 1P - 1A\n" + "Wolslau 6.30P - 6.30A\n" + "Charleton 7.30P - 7.30A\n" +
            "12 \n" + "Zhang 6.30A - 6.30P\n" + "D'Angelo 7.30A - 7.30P\n" + "Leavell 9A - 7P\n" + "Chirino 11A - 11P\n" + "Heslinga 3P - 1A\n" + "Wolslau 6.30P - 6.30A\n" + "Haig 7.30P - 7.30A\n" +
            "13 \n" + "Fan 6.30A - 6.30P\n" + "Khamis 7.30A - 7.30P\n" + "Vizzuso 9A - 7P\n" + "Askari 11A - 11P\n" + "Chirino 3P - 1A\n" + "Wolslau 6.30P - 6.30A\n" + "Haig 7.30P - 7.30A\n" +
            "14 \n" + "Leavell 6.30A - 6.30P\n" + "Khamis 7.30A - 7.30P\n" + "Vizzuso 9A - 5P\n" + "Askari 11A - 11P\n" + "Zaboski 5P - 1A\n" + "Hakim 6.30P - 6.30A\n" + "Haig 7.30P - 7.30A\n" +
            //"15 \n" +"Letizia 6.30A - 6.30P\n" +"Leavell 7.30A - 7.30P\n" +"D'Angelo 9A - 5P\n" +"Charleton 11A - 11P\n" +"Chirino 5P - 1A\n" +"Hakim 6.30P - 6.30A\n" +"Fan 7.30P - 7.30A\n" +
            "16 \n" + "Vizzuso 6.30A - 6.30P\n" + "D'Angelo 7.30A - 7.30P\n" + "Letizia 9A - 5P\n" + "DeLaCalzada-Je 11A - 11P\n" + "Chirino 5P - 1A\n" + "Heslinga 6.30P - 6.30A\n" + "Fan 7.30P - 7.30A\n" +
            "17 \n" + "Pamy 6.30A - 6.30P\n" + "Khamis 7.30A - 7.30P\n" + "Letizia 9A - 9P\n" + "McCoy 1P - 1A\n" + "Heslinga 6.30P - 6.30A\n" + "Leavell 7.30P - 7.30A\n" +
            "18 \n" + "D'Angelo 6.30A - 6.30P\n" + "Khamis 7.30A - 7.30P\n" + "Chirino 9A - 9P\n" + "McCoy 1P - 1A\n" + "Zhang 6.30P - 6.30A\n" + "Leavell 7.30P - 7.30A\n" +
            "19 \n" + "Hakim 6.30A - 6.30P\n" + "D'Angelo 7.30A - 7.30P\n" + "Vizzuso 9A - 7P\n" + "Letizia 11A - 11P\n" + "Chirino 3P - 1A\n" + "Askari 6.30P - 6.30A\n" + "Haig 7.30P - 7.30A\n" +
            "20 \n" + "Hakim 6.30A - 6.30P\n" + "Fan 7.30A - 7.30P\n" + "DeLaCalzada-Je 9A - 7P\n" + "Heslinga 11A - 11P\n" + "Letizia 3P - 1A\n" + "Askari 6.30P - 6.30A\n" + "Haig 7.30P - 7.30A\n" +
            "21 \n" + "Hakim 6.30A - 6.30P\n" + "Khamis 7.30A - 7.30P\n" + "Heslinga 9A - 5P\n" + "DeLaCalzada-Je 11A - 11P\n" + "Zaboski 5P - 1A\n" + "Charleton 6.30P - 6.30A\n" + "Haig 7.30P - 7.30A\n" +
            "22 \n" + "Letizia 6.30A - 6.30P\n" + "D'Angelo 7.30A - 7.30P\n" + "Fan 9A - 5P\n" + "Khamis 11A - 11P\n" + "Leavell 5P - 1A\n" + "Charleton 6.30P - 6.30A\n" + "Wolslau 7.30P - 7.30A\n" +
            "23 \n" + "Heslinga 6.30A - 6.30P\n" + "D'Angelo 7.30A - 7.30P\n" + "Fan 9A - 5P\n" + "Khamis 11A - 11P\n" + "Leavell 5P - 1A\n" + "Charleton 6.30P - 6.30A\n" + "Wolslau 7.30P - 7.30A\n" +
            "24 \n" + "Heslinga 6.30A - 6.30P\n" + "DeLaCalzada-Je 7.30A - 7.30P\n" + "Askari 9A - 9P\n" + "McCoy 1P - 1A\n" + "Vizzuso 6.30P - 6.30A\n" + "Letizia 7.30P - 7.30A\n" +
            "25 \n" + "Heslinga 6.30A - 6.30P\n" + "Hakim 7.30A - 7.30P\n" + "Askari 9A - 9P\n" + "Chirino 1P - 1A\n" + "Vizzuso 6.30P - 6.30A\n" + "Letizia 7.30P - 7.30A\n" +
            "26 \n" + "D'Angelo 6.30A - 6.30P\n" + "Hakim 7.30A - 7.30P\n" + "Khamis 9A - 7P\n" + "Zhang 11A - 11P\n" + "Askari 3P - 1A\n" + "Fan 6.30P - 6.30A\n" + "Charleton 7.30P - 7.30A\n" +
            "27 \n" + "D'Angelo 6.30A - 6.30P\n" + "McCoy 7.30A - 7.30P\n" + "DeLaCalzada-Je 9A - 7P\n" + "Khamis 11A - 11P\n" + "Hakim 3P - 1A\n" + "Fan 6.30P - 6.30A\n" + "Charleton 7.30P - 7.30A\n" +
            "28 \n" + "Leavell 6.30A - 6.30P\n" + "Heslinga 7.30A - 7.30P\n" + "DeLaCalzada-Je 9A - 5P\n" + "Khamis 11A - 11P\n" + "Zaboski 5P - 1A\n" + "Haig 6.30P - 6.30A\n" + "Wolslau 7.30P - 7.30A\n" +
            "29 \n" + "Letizia 6.30A - 6.30P\n" + "Heslinga 7.30A - 7.30P\n" + "Vizzuso 9A - 5P\n" + "Leavell 11A - 11P\n" + "Askari 5P - 1A\n" + "Haig 6.30P - 6.30A\n" + "Wolslau 7.30P - 7.30A\n" +
            "30 \n" + "Letizia 6.30A - 6.30P\n" + "Hakim 7.30A - 7.30P\n" + "Vizzuso 9A - 5P\n" + "Leavell 11A - 11P\n" + "Askari 5P - 1A\n" + "Charleton 6.30P - 6.30A\n" + "Zaboski 7.30P - 7.30A\n" +
            "31 \n" + "Khamis 6.30A - 6.30P\n" + "Hakim 7.30A - 7.30P\n" + "Vizzuso 9A - 9P\n" + "Fan 1P - 1A\n" + "Charleton 6.30P - 6.30A\n" + "Zaboski 7.30P - 7.30A"
            + "Notes:\tTimestamp:Friday , 12-26-14, 11:04";

    public void putTestDataToClipboard()
    {
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText(ClipDescription.MIMETYPE_TEXT_PLAIN, testStr);
        clipboard.setPrimaryClip(clip);
    }
}
