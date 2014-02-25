package com.quickblox.lifebeats.ui;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;
import classes.*;
import com.quickblox.lifebeats.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created with IntelliJ IDEA.
 * User: Андрей
 * Date: 12/1/13
 * Time: 4:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class Medications_Periodicity_DatePicker extends FragmentActivity {
    private EditText startDate;
    private EditText endDate;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.medications_periodicity_datepicker);
        ConstructUI();
    }

    public void ConstructUI() {
        StaticVariables.startDate = Calendar.getInstance();
        StaticVariables.endDate = Calendar.getInstance();

        startDate = (EditText) findViewById(R.id.medications_periodicity_datepicker_EditText_start_date);
        endDate = (EditText) findViewById(R.id.medications_periodicity_datepicker_EditText_end_date);

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(StaticVariables.dateFormat);
            startDate.setText(dateFormat.format(Calendar.getInstance().getTime()));
            endDate.setText(dateFormat.format(Calendar.getInstance().getTime()));
        } catch (Exception e) {
            CommonFunctions.showAlert(Medications_Periodicity_DatePicker.this, "Error", e.getLocalizedMessage());
        }
    }

    public void showStartDatePickerDialog(View v) {
        DialogFragment newFragment = new StartDatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "startDate");
    }

    public void showEndDatePickerDialog(View v) {
        DialogFragment newFragment = new EndDatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "endDate");
    }

    public void showIntervalDialog(View v) {
        IntervalDialogClass intervalDialog = new IntervalDialogClass(this);
        intervalDialog.show();
        Window window = intervalDialog.getWindow();
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, 250);
    }

    public void dialog_IntervalConfirmClick(View v) {

    }

    public void dialog_IntervalCancelClick(View v) {

    }

    public void button_periodicity_datepicker_confirm_Click(View v) {
        StaticVariables.startDate = CommonFunctions.getCalendarFromString(getBaseContext(), startDate.getText().toString(), StaticVariables.dateFormat);
        StaticVariables.endDate = CommonFunctions.getCalendarFromString(getBaseContext(), endDate.getText().toString(), StaticVariables.dateFormat);

        if (StaticVariables.periodicityType == 3) {
            CommonFunctions.startNewActivity(this, Medications_Periodicity_WeekDaySchedule.class);
            overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        } else if (StaticVariables.periodicityType == 4) {
            CommonFunctions.startNewActivity(this, Medications_Periodicity_Calendar.class);
            overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        } else {
            CommonFunctions.startNewActivity(this, Medications_Periodicity_TimeSchedule.class);
            overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        }
    }

    @Override
    public void onBackPressed() {
        Log.d(StaticVariables.TAG_RETURN_TO_PREVIOUS_ACTIVITY, this.getLocalClassName());
        super.onBackPressed();
        overridePendingTransition(R.anim.push_right_out, R.anim.push_right_in);
    }
}