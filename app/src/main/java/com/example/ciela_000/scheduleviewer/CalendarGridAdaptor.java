package com.example.ciela_000.scheduleviewer;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ciela_000 on 1/11/2015.
 */
public class CalendarGridAdaptor extends BaseAdapter
{
    Context context;
    List<ScheduleData> gridDataArray = new ArrayList<ScheduleData>();
    int gridCellHeight = 120;

    public CalendarGridAdaptor(Context context)
    {
        this.context = context;
        this.gridCellHeight = getGridCellHeight(40);

        boolean isConst = true;
        gridDataArray.add(new ScheduleData(isConst, "S"));
        gridDataArray.add(new ScheduleData(isConst, "M"));
        gridDataArray.add(new ScheduleData(isConst, "T"));
        gridDataArray.add(new ScheduleData(isConst, "W"));
        gridDataArray.add(new ScheduleData(isConst, "T"));
        gridDataArray.add(new ScheduleData(isConst, "F"));
        gridDataArray.add(new ScheduleData(isConst, "S"));
    }

    private int getGridCellHeight(int dps)
    {
        final float scale = context.getResources().getDisplayMetrics().density;
        int pixels = (int) (dps * scale + 0.5f);
        return pixels;
    }

    public void addGridDateData(ScheduleData data)
    {
        gridDataArray.add(data);
    }

    public List<ScheduleData> getGridDataArray()
    {
        return gridDataArray;
    }

    @Override
    public int getCount() {
        return gridDataArray.size();
    }

    @Override
    public Object getItem(int position) {
        return gridDataArray.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView tv = new TextView(context);
        tv.setHeight(gridCellHeight);

        ScheduleData scheduleData = gridDataArray.get(position);

        //set grid text
        String gridText;
        if(scheduleData.isConstant())
        {
            gridText = scheduleData.getConstantValue();
        }
        else
        {
            gridText = scheduleData.getBriefData();
        }
        tv.setText(gridText);

        //set shift background color
        if(scheduleData.isBlankShift())
        {
            tv.setBackgroundColor(Color.parseColor("#E0E0E0"));
        }
        if(scheduleData.isOffShift())
        {
            tv.setBackgroundColor(Color.parseColor("#E5FFCC"));
        }
        if(scheduleData.isDayShift())
        {
            tv.setBackgroundColor(Color.parseColor("#FFB266"));
        }
        if(scheduleData.isFastDayShift())
        {
            tv.setBackgroundColor(Color.parseColor("#FFFF66"));
        }
        if(scheduleData.isMidShift())
        {
            tv.setBackgroundColor(Color.parseColor("#FF6666"));
        }
        if(scheduleData.isFastMidShift())
        {
            tv.setBackgroundColor(Color.parseColor("#FF9999"));
        }
        if(scheduleData.isNightShift())
        {
            tv.setBackgroundColor(Color.parseColor("#3399FF"));
        }
        return tv;
    }
}
