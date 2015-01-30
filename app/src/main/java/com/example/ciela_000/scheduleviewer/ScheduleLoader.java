package com.example.ciela_000.scheduleviewer;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;


public class ScheduleLoader extends ActionBarActivity {

    private static final String TITLE = "FANs ER Schedule";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_loader);
        setTitle(TITLE);
        initMonthSpinner();
        initYearText();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_schedule_loader, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initYearText()
    {
        try {
            int currentYear = getCurrentYear();
            EditText yearText = (EditText) findViewById(R.id.yearText);
            yearText.setText(String.valueOf(currentYear));
        }
        catch(Exception e)
        {
            String err = "Unable default current year. Please manually put year in.";
            Toast.makeText(ScheduleLoader.this, err, Toast.LENGTH_SHORT).show();
        }
    }

    private int getCurrentYear() {
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.YEAR);
    }

    public void initMonthSpinner()
    {
        try {
            Spinner spinner = (Spinner) findViewById(R.id.monthPicker);
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                    R.array.months_array, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
            spinner.setAdapter(adapter);
        }
        catch(Exception e)
        {
            String err = "ERROR: Unable to load months!";
            Toast.makeText(ScheduleLoader.this, err, Toast.LENGTH_SHORT).show();
        }
    }

    public void pasteButtonClick(View v)
    {
        try {
            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);

            if (clipboard.hasPrimaryClip() && clipboard.getPrimaryClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_HTML)) {
                ClipData.Item item = clipboard.getPrimaryClip().getItemAt(0);
                TextView rawScheduleText = (TextView) findViewById(R.id.rawScheduleText);
                rawScheduleText.setTextColor(Color.GRAY);
                rawScheduleText.setTextSize(8);
                rawScheduleText.setText(item.getText());
            } else {
                String err = "Copied content is not in correct format. Please go back and copy the entire schedule page text, and click PASTE button again.";
                Toast.makeText(ScheduleLoader.this, err, Toast.LENGTH_SHORT).show();
            }
        }
        catch(Exception e)
        {
            String err = "ERROR: Unable to paste copied content. Please check if you copied it correctly!";
            Toast.makeText(ScheduleLoader.this, err, Toast.LENGTH_SHORT).show();
        }
    }

    public void okayButtonClick(View v)
    {
        TextView rawScheduleText = (TextView) findViewById(R.id.rawScheduleText);
        String rawScheduleData = rawScheduleText.getText().toString();
        if(rawScheduleData == null || rawScheduleData.length() == 0 || rawScheduleData.equals(getString(R.string.paste_instruction)))
        {
            String msg = "Please Paste In Schedule Text First!";
            Toast.makeText(ScheduleLoader.this, msg, Toast.LENGTH_SHORT).show();
            return;
        }

        EditText nameText = (EditText) findViewById(R.id.nameText);
        String name = nameText.getText().toString();
        if(name == null || name.length() == 0)
        {
            String msg = "Please Put Your Name!";
            Toast.makeText(ScheduleLoader.this, msg, Toast.LENGTH_SHORT).show();
            return;
        }

        EditText yearText = (EditText) findViewById(R.id.yearText);
        int year = Integer.parseInt(yearText.getText().toString());
        if(year < getCurrentYear() - 1)
        {
            String msg = "Please Put A Valid Year!";
            Toast.makeText(ScheduleLoader.this, msg, Toast.LENGTH_SHORT).show();
            return;
        }

        Spinner spinner = (Spinner) findViewById(R.id.monthPicker);
        int month = spinner.getSelectedItemPosition();

        Intent intent = new Intent(this, CalendarGridView.class);

        intent.putExtra("rawScheduleData", rawScheduleData);
        intent.putExtra("name", name);
        intent.putExtra("year", year);
        intent.putExtra("month", month);

        Toast.makeText(ScheduleLoader.this, "Loading Calendar...", Toast.LENGTH_SHORT).show();

        startActivity(intent);
    }
}
