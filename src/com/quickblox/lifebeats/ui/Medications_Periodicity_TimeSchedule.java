package com.quickblox.lifebeats.ui;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import classes.CommonFunctions;
import classes.DBTables.MyMedications;
import classes.StaticVariables;
import com.quickblox.lifebeats.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Андрей
 * Date: 03.11.13
 * Time: 18:02
 */
public class Medications_Periodicity_TimeSchedule extends Activity {

    private ArrayList<Periodicity_Interval> periodicity_times;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.medications_periodicity_schedule);
        ConstructUI();
    }

    private void ConstructUI() {
        periodicity_times = new ArrayList<Periodicity_Interval>();
        periodicity_times.add(new Periodicity_Interval(0, "00:00", false));
        periodicity_times.add(new Periodicity_Interval(1, "01:00", false));
        periodicity_times.add(new Periodicity_Interval(2, "02:00", false));
        periodicity_times.add(new Periodicity_Interval(3, "03:00", false));
        periodicity_times.add(new Periodicity_Interval(4, "04:00", false));
        periodicity_times.add(new Periodicity_Interval(5, "05:00", false));
        periodicity_times.add(new Periodicity_Interval(6, "06:00", false));
        periodicity_times.add(new Periodicity_Interval(7, "07:00", false));
        periodicity_times.add(new Periodicity_Interval(8, "08:00", false));
        periodicity_times.add(new Periodicity_Interval(9, "09:00", false));
        periodicity_times.add(new Periodicity_Interval(10, "10:00", false));
        periodicity_times.add(new Periodicity_Interval(11, "11:00", false));
        periodicity_times.add(new Periodicity_Interval(12, "12:00", false));
        periodicity_times.add(new Periodicity_Interval(13, "13:00", false));
        periodicity_times.add(new Periodicity_Interval(14, "14:00", false));
        periodicity_times.add(new Periodicity_Interval(15, "15:00", false));
        periodicity_times.add(new Periodicity_Interval(16, "16:00", false));
        periodicity_times.add(new Periodicity_Interval(17, "17:00", false));
        periodicity_times.add(new Periodicity_Interval(18, "18:00", false));
        periodicity_times.add(new Periodicity_Interval(19, "19:00", false));
        periodicity_times.add(new Periodicity_Interval(20, "20:00", false));
        periodicity_times.add(new Periodicity_Interval(21, "21:00", false));
        periodicity_times.add(new Periodicity_Interval(22, "22:00", false));
        periodicity_times.add(new Periodicity_Interval(23, "23:00", false));

        final ListView listView = (ListView) findViewById(R.id.medications_periodicity_timeschedule_list);
        ArrayAdapter<Periodicity_Interval> adapter = new MyListAdapter();
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                CheckBox box = (CheckBox) view.findViewById(R.id.medications_priodicity_timeschedule_itemtemplate_CheckBox_select);
                if (box.isChecked()) {
                    periodicity_times.get(position).isChecked = false;
                    box.setChecked(false);
                } else {
                    periodicity_times.get(position).isChecked = true;
                    box.setChecked(true);
                }
            }
        });
    }

    public void button_confirm_Click(View v) {
        List<Calendar> calendarList = new ArrayList<Calendar>();
        for (Periodicity_Interval time : periodicity_times) {
            if (time.isChecked) {
                for (Calendar date : StaticVariables.selectedDateList) {
                    calendarList.add(new GregorianCalendar(
                            date.get(Calendar.YEAR),
                            date.get(Calendar.MONTH),
                            date.get(Calendar.DAY_OF_MONTH),
                            time.getHourPart(this),
                            time.getMinutePart(this)));
                }
            }
        }

        for (Calendar item : calendarList) {
            MyMedications myMedications = new MyMedications();
            myMedications.setEventDate(new java.sql.Date(item.getTimeInMillis()));
            StaticVariables.medicationList.add(myMedications);
        }

        CommonFunctions.startNewActivityWithClearingBackStack(this, Medications.class);
        overridePendingTransition(R.anim.push_right_out, R.anim.push_right_in);
    }

    private class MyListAdapter extends ArrayAdapter<Periodicity_Interval> {
        public MyListAdapter() {
            super(Medications_Periodicity_TimeSchedule.this, R.layout.medications_periodicity_schedule_itemtemplate, periodicity_times);
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