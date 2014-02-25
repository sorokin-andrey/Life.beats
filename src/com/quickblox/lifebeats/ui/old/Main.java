package com.quickblox.lifebeats.ui.old;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import classes.CommonFunctions;
import classes.DBHandlers.MyMedications_DBHandler;
import classes.DBTables.MyMedications;
import classes.ImageHelper;
import classes.StaticVariables;
import com.quickblox.core.QBCallbackImpl;
import com.quickblox.core.QBSettings;
import com.quickblox.core.result.Result;
import com.quickblox.lifebeats.R;
import com.quickblox.lifebeats.model.DataHolder;
import com.quickblox.lifebeats.ui.ActivityCallUser;
import com.quickblox.lifebeats.ui.Medications;
import com.quickblox.lifebeats.ui.Profile;
import com.quickblox.lifebeats.ui.Profile_CurrentUser;
import com.quickblox.module.auth.QBAuth;
import com.quickblox.module.auth.result.QBSessionResult;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class Main extends Activity {

    private ProgressDialog progressDialog;
    private TextView userName;
    private TextView dayNumber;
    private TextView month;
    private TextView year;
    private TextView dayName;

    private final String FIRST_USER_PASSWORD = "lifeBeatsTest";
    private final String FIRST_USER_LOGIN = "lifeBeatsTest";
    private final int firstUserId = 819313;
    private final String firstUserName = "lifeBeatsTest";

    private final String SECOND_USER_PASSWORD = "lifeBeatsTest1";
    private final String SECOND_USER_LOGIN = "lifeBeatsTest1";
    private final int secondUserId = 819314;
    private final String secondUserName = "lifeBeatsTest1";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ConstructUI();
        // Set QuickBlox credentials here
        //
        QBSettings.getInstance().fastConfigInit("6767", "PCZJ5FcQKFD67wK", "undTmBAarKRVqxH");
    }

    private void ConstructUI() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.please_wait));
        progressDialog.setCancelable(false);

        setFontStyle();

        new ImageHelper((ImageView) findViewById(R.id.main_ImageView_profileimage)).execute(StaticVariables.currentUser.getProfileImage());
        if (StaticVariables.currentUser.getName().equals("")) {
            userName.setText("Hello, new user");
        } else {
            userName.setText("Hello, " + StaticVariables.currentUser.getName());
        }

        Calendar c = Calendar.getInstance();

//        dayNumber = (TextView) findViewById(R.id.main_textView_date_day_number);
//        month = (TextView) findViewById(R.id.main_textView_date_month);
//        year = (TextView) findViewById(R.id.main_textView_date_year);
//        dayName = (TextView) findViewById(R.id.main_textView_date_day_name);

        dayNumber.setText(String.valueOf(c.get(Calendar.DAY_OF_MONTH)));
        month.setText(c.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.US));
        year.setText(String.valueOf(c.getInstance().get(Calendar.YEAR)));
        String sdayName = "";
        switch (Calendar.getInstance().get(Calendar.DAY_OF_WEEK)) {
            case Calendar.MONDAY:
                sdayName = "Monday";
                break;
            case Calendar.TUESDAY:
                sdayName = "Tuesday";
                break;
            case Calendar.WEDNESDAY:
                sdayName = "Wednesday";
                break;
            case Calendar.THURSDAY:
                sdayName = "Thursday";
                break;
            case Calendar.FRIDAY:
                sdayName = "Friday";
                break;
            case Calendar.SATURDAY:
                sdayName = "Saturday";
                break;
            case Calendar.SUNDAY:
                sdayName = "Sunday";
                break;
        }
        dayName.setText(sdayName);

        RelativeLayout medicationsLayout = (RelativeLayout) findViewById(R.id.main_RelativeLayout_medications);
        medicationsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonFunctions.startNewActivity(Main.this, Medications.class);
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });

        RelativeLayout dueTodayLayout = (RelativeLayout) findViewById(R.id.main_RelativeLayout_due_today);
        dueTodayLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        RelativeLayout visitDoctorLayout = (RelativeLayout) findViewById(R.id.main_RelativeLayout_health_team);
        visitDoctorLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                createSession(FIRST_USER_LOGIN, FIRST_USER_PASSWORD);
                //createSession(SECOND_USER_LOGIN, SECOND_USER_PASSWORD);
            }
        });

        RelativeLayout doctorNotesLayout = (RelativeLayout) findViewById(R.id.main_RelativeLayout_doctor_notes);
        doctorNotesLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        MyMedications_DBHandler mmdbh = new MyMedications_DBHandler(this);
        List<MyMedications> medsList = mmdbh.getAllItemsByUserId(StaticVariables.currentUser.getId());
    }

    private void setFontStyle() {
        userName = (TextView) findViewById(R.id.main_textView_user_name);
        dayNumber = (TextView) findViewById(R.id.main_textView_date_day_number);
        month = (TextView) findViewById(R.id.main_textView_date_month);
        year = (TextView) findViewById(R.id.main_textView_date_year);
        dayName = (TextView) findViewById(R.id.main_textView_date_day_name);
        TextView taskTitle = (TextView) findViewById(R.id.main_textView_task_title);

        Typeface type = Typeface.createFromAsset(getAssets(), "fonts/Georgia.ttf");

        userName.setTypeface(type, Typeface.ITALIC);
        dayNumber.setTypeface(type);
        month.setTypeface(type);
        year.setTypeface(type);
        dayName.setTypeface(type);
        taskTitle.setTypeface(type);
    }

    public void main_button_profile_onClick(View v) {
        CommonFunctions.startNewActivity(Main.this, Profile.class);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    public void main_button_current_profile_onClick(View v) {
        CommonFunctions.startNewActivity(Main.this, Profile_CurrentUser.class);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    private void createSession(String login, final String password) {

        // Create QuickBlox session with user
        QBAuth.createSession(login, password, new QBCallbackImpl() {
            @Override
            public void onComplete(Result result) {
                if (result.isSuccess()) {
                    // save current user
                    DataHolder.getInstance().setCurrentQbUser(((QBSessionResult) result).getSession().getUserId(), password);

                    // show next activity
                    showCallUserActivity();
                }
            }
        });
    }

    private void showCallUserActivity() {
        Intent intent = new Intent(this, ActivityCallUser.class);
        intent.putExtra("userId", DataHolder.getInstance().getCurrentQbUser().getId() == firstUserId ? secondUserId : firstUserId);
        intent.putExtra("userName", DataHolder.getInstance().getCurrentQbUser().getId() == firstUserId ? secondUserName : firstUserName);
        intent.putExtra("myId", DataHolder.getInstance().getCurrentQbUser().getId() != firstUserId ? secondUserId : firstUserId);
        intent.putExtra("myName", DataHolder.getInstance().getCurrentQbUser().getId() != firstUserId ? secondUserName : firstUserName);
        startActivity(intent);
        finish();
    }

    public String addEvent(String calendarId, String title, long startTime,
                           long endTime, int allDay) {
        ContentValues event = new ContentValues();
        event.put("calendar_id", calendarId); // "" for insert
        event.put("title", title);
        event.put("description", "");
        event.put("eventLocation", "");
        event.put("allDay", allDay);
        event.put("eventStatus", 1);
        event.put("transparency", 0);
        event.put("dtstart", startTime);
        event.put("dtend", endTime);

        ContentResolver contentResolver = this.getContentResolver();
        Uri eventsUri = Uri.parse("content://com.android.calendar/calendars");
        Uri url = contentResolver.insert(eventsUri, event);
        String ret = url.toString();
        return ret;
    }

    @Override
    public void onBackPressed() {
        Log.d(StaticVariables.TAG_RETURN_TO_PREVIOUS_ACTIVITY, this.getLocalClassName());
        super.onBackPressed();
        overridePendingTransition(R.anim.push_right_out, R.anim.push_right_in);
    }
}
