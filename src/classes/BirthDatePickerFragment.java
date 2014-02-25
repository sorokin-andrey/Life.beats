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
 * Date: 07.02.14
 * Time: 14:40
 */
public class BirthDatePickerFragment extends DialogFragment
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
        EditText birthDate = (EditText) getActivity().findViewById(R.id.profile_current_user_EditText_birthDate_value);
        String result = "";
        if (year < 10)
            result = "0" + year;
        else
            result = "" + year;
        result += "-";
        if (month < 9)
            result += "0" + (month + 1);
        else
            result += "" + (month + 1);
        result += "-";
        if (day < 10)
            result += "0" + day;
        else
            result += "" + day;
        birthDate.setText(result);
    }
}