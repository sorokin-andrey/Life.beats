package com.quickblox.lifebeats.ui;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import classes.CommonFunctions;
import classes.StaticVariables;
import com.quickblox.lifebeats.R;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Андрей
 * Date: 12/1/13
 * Time: 4:55 PM
 * To change this template use File | Settings | File Templates.
 */
public class Medications_Periodicity_WeekDaySchedule extends Activity {
    private static ArrayList<Periodicity_Interval> periodicity_times;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.medications_periodicity_schedule);
        ConstructUI();
    }

    private void ConstructUI() {

        periodicity_times = new ArrayList<Periodicity_Interval>();
        periodicity_times.add(new Periodicity_Interval(0, "Monday", false));
        periodicity_times.add(new Periodicity_Interval(1, "Tuesday", false));
        periodicity_times.add(new Periodicity_Interval(2, "Wednesday", true));
        periodicity_times.add(new Periodicity_Interval(3, "Thursday", false));
        periodicity_times.add(new Periodicity_Interval(4, "Friday", false));
        periodicity_times.add(new Periodicity_Interval(5, "Saturday", true));
        periodicity_times.add(new Periodicity_Interval(6, "Sunday", false));


        final ListView listView = (ListView) findViewById(R.id.medications_periodicity_timeschedule_list);
        ArrayAdapter<Periodicity_Interval> adapter = new MyListAdapter();
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                CheckBox box = (CheckBox) view.findViewById(R.id.medications_priodicity_timeschedule_itemtemplate_CheckBox_select);
                if (box.isChecked() == true) {
                    box.setChecked(false);
                } else {
                    box.setChecked(true);
                }
            }
        });
    }

    public void button_confirm_Click(View v) {
        for (int i = 0; i < periodicity_times.size(); i++) {
            if (periodicity_times.get(i).isChecked)
                StaticVariables.selectedTimeList.add(periodicity_times.get(i));
        }

        CommonFunctions.startNewActivity(this, Medications.class);
        overridePendingTransition(R.anim.push_right_out, R.anim.push_right_in);
    }

    private class MyListAdapter extends ArrayAdapter<Periodicity_Interval> {
        public MyListAdapter() {
            super(Medications_Periodicity_WeekDaySchedule.this, R.layout.medications_periodicity_schedule_itemtemplate, periodicity_times);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View itemView = convertView;
            if (itemView == null) {
                itemView = getLayoutInflater().inflate(R.layout.medications_periodicity_schedule_itemtemplate, parent, false);
            }

            // find the item to work with
            Periodicity_Interval currentItem = periodicity_times.get(position);

            // add itemName
            TextView textTime = (TextView) itemView.findViewById(R.id.medications_priodicity_itemtemplate_TextView_optionName);
            textTime.setText(currentItem.itemName);

            // add title
            CheckBox checkBox_isItSelected = (CheckBox) itemView.findViewById(R.id.medications_priodicity_timeschedule_itemtemplate_CheckBox_select);
            checkBox_isItSelected.setChecked(currentItem.isChecked);

            return itemView;
        }
    }

    @Override
    public void onBackPressed() {
        Log.d(StaticVariables.TAG_RETURN_TO_PREVIOUS_ACTIVITY, this.getLocalClassName());
        super.onBackPressed();
        overridePendingTransition(R.anim.push_right_out, R.anim.push_right_in);
    }
}