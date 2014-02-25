package com.quickblox.lifebeats.ui;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import classes.CommonFunctions;
import classes.StaticVariables;
import com.quickblox.lifebeats.R;

/**
 * Created with IntelliJ IDEA.
 * User: Андрей
 * Date: 03.11.13
 * Time: 17:08
 */
public class Medications_Periodicity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.medications_periodicity);
    }

    public void button_oneTimeEvent_Click(View v) {
        StaticVariables.periodicityType = 0;
        CommonFunctions.startNewActivity(this, Medications_Periodicity_Calendar.class);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    public void button_everyDayEvent_Click(View v) {
        StaticVariables.periodicityType = 1;
        CommonFunctions.startNewActivity(this, Medications_Periodicity_DatePicker.class);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    public void button_everyXDaysEvent_Click(View v) {
        StaticVariables.periodicityType = 2;
//        startActivity(new Intent(this, Medications_Periodicity_TimeSchedule.class));
//        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        final Dialog myDialog = new Dialog(this, R.style.CustomDialogTheme);
        myDialog.setContentView(R.layout.medications_periodicity_popup);
        myDialog.show();

        Button close_btn = (Button) myDialog.findViewById(R.id.medications_periodicity_popup_button_close);
        close_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                myDialog.dismiss();
                StaticVariables.periodicityType = 2;
                CommonFunctions.startNewActivity(Medications_Periodicity.this, Medications_Periodicity_DatePicker.class);
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });
    }

    public void button_everyWeek_Click(View v) {
        StaticVariables.periodicityType = 3;
        CommonFunctions.startNewActivity(this, Medications_Periodicity_DatePicker.class);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    public void button_everyMonth_Click(View v) {
        StaticVariables.periodicityType = 4;
        CommonFunctions.startNewActivity(this, Medications_Periodicity_DatePicker.class);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    @Override
    public void onBackPressed() {
        Log.d(StaticVariables.TAG_RETURN_TO_PREVIOUS_ACTIVITY, this.getLocalClassName());
        super.onBackPressed();
        overridePendingTransition(R.anim.push_right_out, R.anim.push_right_in);
    }
}