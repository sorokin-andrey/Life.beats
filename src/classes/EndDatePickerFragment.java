package classes;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;
import android.widget.EditText;
import com.quickblox.lifebeats.R;

import java.util.Calendar;

/**
 * Created with IntelliJ IDEA.
 * User: Андрей
 * Date: 03.11.13
 * Time: 14:11
 */
public class EndDatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        EditText endDate = (EditText) getActivity().findViewById(R.id.medications_periodicity_datepicker_EditText_end_date);
        String result = "";
        if (year < 10)
            result = "0" + year;
        else
            result = "" + year;
        result += "-";
        if (month < 10)
            result += "0" + (month + 1);
        else
            result += "" + (month + 1);
        result += "-";
        if (day < 10)
            result += "0" + day;
        else
            result += "" + day;
        endDate.setText(result);
    }
}