package com.example.ciela_000.scheduleviewer;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.io.File;
import java.util.Calendar;
import java.util.List;
import java.util.Map;


public class CalendarGridView extends ActionBarActivity {

    public static final String TITLE = "Schedule Calendar";
    private int fixedGridOffset = 7;
    private int dynamicMonthOffset = 0;

    private String name = "";
    private String monthStr = "";

    private Map<Integer, ScheduleData> scheduleDataMap;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_calendar_grid_view, menu);
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_grid_view);
        setTitle(TITLE);

        //get data passed by previous view
        Intent intent = getIntent();
        String rawScheduleData = (String) intent.getSerializableExtra("rawScheduleData");
        name = (String) intent.getSerializableExtra("name");
        int year = (int) intent.getSerializableExtra("year");
        int month = (int) intent.getSerializableExtra("month");
        monthStr = Months.getMonth(month);

        try {
            //calculate begin of month offset
            dynamicMonthOffset = getMonthOffset(year, month);

            //process raw schedule data
            ScheduleProcessor processor = new ScheduleProcessor();
            scheduleDataMap = processor.processRawScheduleData(rawScheduleData, name, month);

            //create grid view adaptor, and add data to grid view
            CalendarGridAdaptor adaptor = initializeAdaptorWithData(scheduleDataMap);

            //create grid view
            createGridView(adaptor);
        }
        catch(Exception e)
        {
            String err = "Unable to create Calendar. Please check format of the schedule text, copy the entire schedule page text, and click PASTE button again.";
            Toast.makeText(CalendarGridView.this, err, Toast.LENGTH_SHORT).show();
            startActivity(intent);
        }
    }

    private void createGridView(CalendarGridAdaptor adaptor) {
        GridView gv = (GridView) findViewById(R.id.gridView);
        gv.setAdapter(adaptor);
        final List<ScheduleData> gridDataArray = adaptor.getGridDataArray();
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                ScheduleData data = gridDataArray.get(position);
                if (data.isHasSchedule()) {
                    String dateTimeData = data.getTimeData();
                    Toast.makeText(CalendarGridView.this, dateTimeData, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private CalendarGridAdaptor initializeAdaptorWithData(Map<Integer, ScheduleData> scheduleDataMap) {
        CalendarGridAdaptor adaptor = new CalendarGridAdaptor(CalendarGridView.this);
        //add beginning of month offset
        for (int i = 0; i < dynamicMonthOffset; i++) {
            adaptor.addGridDateData(new ScheduleData(true, " "));
        }
        //add schedule data to adaptor
        for(int i = 1; i < 32; i++)
        {
            if(scheduleDataMap.containsKey(i))
            {
                adaptor.addGridDateData(scheduleDataMap.get(i));
            }
            else
            {
                if(i > 28) continue;
                adaptor.addGridDateData(new ScheduleData(true, "?"));
            }
        }
        return adaptor;
    }

    private int getMonthOffset(int year,int month)
    {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        return cal.get(Calendar.DAY_OF_WEEK) - 1;
    }

    public void saveScheduleButtonClick(View v)
    {
        try {
            SaveScheduleToImageHelper helper = new SaveScheduleToImageHelper();
            GridView scheduleView = (GridView) findViewById(R.id.gridView);
            File savedFile = helper.saveViewImage(scheduleView, name, monthStr);
            String msg = "Saved schedule to " + savedFile.getPath();
            Toast.makeText(CalendarGridView.this, msg, Toast.LENGTH_SHORT).show();
        }
        catch(Exception e)
        {
            String err = "Unable to save schedule. " + e.getMessage();
            Toast.makeText(CalendarGridView.this, err, Toast.LENGTH_SHORT).show();
        }
    }

    public void sendEmailButtonClick(View v)
    {
        try {
            EmailScheduleHelper helper = new EmailScheduleHelper();
            GridView scheduleView = (GridView) findViewById(R.id.gridView);
            Intent intent = helper.getEmailScheduleIntent(scheduleView, name, monthStr);
            startActivity(intent);
        }
        catch(Exception e)
        {
            String err = "Unable to send calendar by email. " + e.getMessage();
            Toast.makeText(CalendarGridView.this, err, Toast.LENGTH_SHORT).show();
        }
    }

    public void saveToCalendarButtonClick(View v)
    {
        try
        {
            SaveToPhoneCalendarHelper helper = new SaveToPhoneCalendarHelper(this.getApplicationContext());
            if(scheduleDataMap != null && scheduleDataMap.size()> 0)
            {
                for(ScheduleData data : scheduleDataMap.values())
                {
                    if(data.isHasSchedule())
                    {
                        helper.addEvent(data);
                    }
                }
            }
            String msg = "Saved schedule to your Calendar!";
            Toast.makeText(CalendarGridView.this, msg, Toast.LENGTH_SHORT).show();
        }
        catch(Exception e)
        {
            String err = "Failed to save schedule to calendar. " + e.getMessage();
            Toast.makeText(CalendarGridView.this, err, Toast.LENGTH_SHORT).show();
        }
    }
}
