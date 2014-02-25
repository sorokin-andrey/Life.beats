package com.quickblox.lifebeats.ui;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import classes.CommonFunctions;
import classes.StaticVariables;
import com.quickblox.lifebeats.R;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created with IntelliJ IDEA.
 * User: Андрей
 * Date: 11/30/13
 * Time: 7:02 PM
 */
public class Medications_Periodicity_Calendar extends Activity {
    private ArrayList<ButtonForCalendar> buttons;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.medications_periodicity_timeschedule_calendar);
        buttons = new ArrayList<ButtonForCalendar>();
        for (int i = 1; i < 33; i++) {
            buttons.add(new ButtonForCalendar(i, false));
        }

    }

    public void button_calendar_day_Click(View v) {
        Button button = (Button) v;
        try {
            int id = Integer.valueOf(button.getText().toString());
            buttons.get(id).changeButtonState(button);
        } catch (NumberFormatException e) {
            CommonFunctions.showAlert(Medications_Periodicity_Calendar.this, "Error", e.getLocalizedMessage());
        } catch (IndexOutOfBoundsException e) {
            CommonFunctions.showAlert(Medications_Periodicity_Calendar.this, "Error", e.getLocalizedMessage());
        }
    }

    public void button_calendar_confirm_Click(View v) {
        for (int i = 0; i < buttons.size(); i++) {
            if (buttons.get(i).isSelected) {
                Calendar date = Calendar.getInstance();
                date.set(Calendar.YEAR, Calendar.getInstance().get(Calendar.YEAR));
                date.set(Calendar.MONTH, Calendar.getInstance().get(Calendar.MONTH));
                date.set(Calendar.DAY_OF_MONTH, i);
                if (StaticVariables.periodicityType == 0) {
                    StaticVariables.selectedDateList.add(date);
                } else {
                    if (CommonFunctions.isThisDateInCorrectPeriod(StaticVariables.startDate, date, StaticVariables.endDate)) {
                        StaticVariables.selectedDateList.add(date);
                    }
                }
            }
        }
        CommonFunctions.startNewActivity(this, Medications_Periodicity_TimeSchedule.class);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        //overridePendingTransition(R.anim.push_right_out, R.anim.push_right_in);
    }

    @Override
    public void onBackPressed() {
        Log.d(StaticVariables.TAG_RETURN_TO_PREVIOUS_ACTIVITY, this.getLocalClassName());
        super.onBackPressed();
        overridePendingTransition(R.anim.push_right_out, R.anim.push_right_in);
    }
}

class ButtonForCalendar {
    public int id;
    public boolean isSelected;

    public ButtonForCalendar(int idIn, boolean isSelectedIn) {
        id = idIn;
        isSelected = isSelectedIn;
    }

    public void changeButtonState(Button button) {
        //
        if (isSelected) {
            isSelected = false;
            button.setBackgroundResource(R.drawable.medications_periodicity_calendar_button_default_shape);
        } else {
            isSelected = true;
            button.setBackgroundResource(R.drawable.medications_periodicity_calendar_button_selected_shape);
        }
    }
}