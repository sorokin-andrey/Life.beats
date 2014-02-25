package com.quickblox.lifebeats.ui;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import classes.CommonFunctions;
import classes.DBHandlers.MedicalLs_DBHandler;
import classes.DBHandlers.MyMedications_DBHandler;
import classes.DBTables.MedicalLs;
import classes.DBTables.MyMedications;
import classes.StaticVariables;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingActivity;
import com.quickblox.lifebeats.R;

import java.util.Calendar;
import java.util.Locale;

/**
 * Created with IntelliJ IDEA.
 * User: Андрей
 * Date: 21.02.14
 * Time: 13:17
 */
public class NewMainPage extends SlidingActivity {

    private TextView userName;
    private TextView menu_userName;
    private TextView dayNumber;
    private TextView dayName;
    private TextView date;
    private Calendar c;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_main_page);
        setBehindContentView(R.layout.menu);
        getSlidingMenu().setBehindOffset(300);
        setFontStyle();

        userName.setText("Life.beats");
        if (StaticVariables.currentUser.getName().equals("")) {
            menu_userName.setText("Life.beats");
        } else {
            menu_userName.setText("Привет, " + StaticVariables.currentUser.getName());
        }

        c = Calendar.getInstance();
        setDateInfo(c);

        final ArrayAdapter<MyMedications> adapter = new MyListAdapter();
        final ListView listView = (ListView) findViewById(R.id.main_ListView_medications);
        listView.setAdapter(adapter);
    }

    private void setFontStyle() {
        Typeface aparajita = Typeface.createFromAsset(getAssets(), "fonts/Aparajita.ttf");
        Typeface calibri = Typeface.createFromAsset(getAssets(), "fonts/Calibri.ttf");
        Typeface gabriela = Typeface.createFromAsset(getAssets(), "fonts/Gabriela.ttf");
        Typeface arial = Typeface.createFromAsset(getAssets(), "fonts/Arial.ttf");

        TextView menu_medslist = (TextView) findViewById(R.id.menu_TextView_medslist);
        TextView menu_history = (TextView) findViewById(R.id.menu_TextView_history);
        TextView menu_profile = (TextView) findViewById(R.id.menu_TextView_profile);
        TextView menu_settings = (TextView) findViewById(R.id.menu_TextView_settings);
        menu_userName = (TextView) findViewById(R.id.menu_TextView_userName);

        userName = (TextView) findViewById(R.id.main_TextView_greetings);
        dayNumber = (TextView) findViewById(R.id.main_TextView_dayNumber);
        dayName = (TextView) findViewById(R.id.main_TextView_dayName);
        date = (TextView) findViewById(R.id.main_TextView_date);

        userName.setTypeface(calibri, Typeface.NORMAL);
        dayNumber.setTypeface(aparajita, Typeface.NORMAL);
        dayName.setTypeface(gabriela, Typeface.NORMAL);
        date.setTypeface(gabriela, Typeface.NORMAL);

        menu_userName.setTypeface(arial, Typeface.NORMAL);
        menu_medslist.setTypeface(arial, Typeface.NORMAL);
        menu_history.setTypeface(arial, Typeface.NORMAL);
        menu_profile.setTypeface(arial, Typeface.NORMAL);
        menu_settings.setTypeface(arial, Typeface.NORMAL);
    }
    private void setDateInfo(Calendar c) {
        dayNumber.setText(String.valueOf(c.get(Calendar.DAY_OF_MONTH)));
        String month = c.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
        String year = String.valueOf(c.get(Calendar.YEAR));
        date.setText(month + ", " + year);

        String sdayName = "";
        switch (c.get(Calendar.DAY_OF_WEEK)) {
            case Calendar.MONDAY:
                sdayName = "Monday";
                sdayName = "Понедельник";
                break;
            case Calendar.TUESDAY:
                sdayName = "Tuesday";
                sdayName = "Вторник";
                break;
            case Calendar.WEDNESDAY:
                sdayName = "Wednesday";
                sdayName = "Среда";
                break;
            case Calendar.THURSDAY:
                sdayName = "Thursday";
                sdayName = "Четверг";
                break;
            case Calendar.FRIDAY:
                sdayName = "Friday";
                sdayName = "Пятница";
                break;
            case Calendar.SATURDAY:
                sdayName = "Saturday";
                sdayName = "Суббота";
                break;
            case Calendar.SUNDAY:
                sdayName = "Sunday";
                sdayName = "Воскресенье";
                break;
        }
        dayName.setText(sdayName);
    }

    public void main_date_previous_onClick(View v) {
        c.setTimeInMillis(c.getTimeInMillis() - 86400000);
        setDateInfo(c);
    }
    public void main_date_next_onClick(View v) {
        c.setTimeInMillis(c.getTimeInMillis() + 86400000);
        setDateInfo(c);
    }

    //region Menu listeners
    public void menu_onClick(View v) {
        getSlidingMenu().showMenu();
    }

    public void userName_onClick(View v) {
        getSlidingMenu().showContent();
        CommonFunctions.startNewActivity(this, Profile_CurrentUser.class);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    public void main_onClick(View v) {
        getSlidingMenu().showContent();
    }

    public void medication_onClick(View v) {
        getSlidingMenu().showContent();
    }

    public void history_onClick(View v) {
        getSlidingMenu().showContent();
    }

    public void profile_onClick(View v) {
        getSlidingMenu().showContent();
        CommonFunctions.startNewActivity(this, Profile_CurrentUser.class);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    public void settings_onClick(View v) {
        getSlidingMenu().showContent();
        CommonFunctions.startNewActivity(this, Profile_Settings.class);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    public void addMedication_onClick(View v) {
        getSlidingMenu().showContent();
        CommonFunctions.startNewActivity(this, Medications.class);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }
    //endregion


    /**
     * Вложенный класс-адаптер для отображения коллекции.
     *
     * @author Andrey
     *
     */
    private class MyListAdapter extends ArrayAdapter<MyMedications> implements View.OnClickListener {
        public MyListAdapter() {
            super(  NewMainPage.this,
                    R.layout.new_main_page_medication_item,
                    new MyMedications_DBHandler(NewMainPage.this).getAllItemsByUserId(StaticVariables.currentUser.getId()));
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View itemView = convertView;
            if (itemView == null) {
                itemView = getLayoutInflater().inflate(R.layout.new_main_page_medication_item, parent, false);
            }

            // find the item to work with
            // нужно передавать дополнительный идентификатор помимо user id
            final MyMedications currentItem = new MyMedications_DBHandler(NewMainPage.this).getAllItemsByUserId(StaticVariables.currentUser.getId()).get(position);
            final MedicalLs medicalLs = new MedicalLs_DBHandler(NewMainPage.this).getItem(currentItem.getMedicationId());

            TextView textTime = (TextView) itemView.findViewById(R.id.main_item_TextView_time);
            TextView textTitle = (TextView) itemView.findViewById(R.id.main_item_TextView_name);
            TextView textQuantity = (TextView) itemView.findViewById(R.id.main_item_TextView_quantity);

            // add time
            textTime.setText(currentItem.getEventTime(NewMainPage.this));

            // add title
            textTitle.setText(medicalLs.getTradeName());

            // add child quantity
            textQuantity.setText(String.valueOf(currentItem.getQuantity()));

            return itemView;
        }

        public void onClick(View v) {

        }
    }
}