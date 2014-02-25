package com.quickblox.lifebeats.ui;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import classes.CommonFunctions;
import classes.StaticVariables;
import com.quickblox.lifebeats.R;
import com.quickblox.lifebeats.ui.old.Main;

/**
 * Created with IntelliJ IDEA.
 * User: Andrey
 * Date: 11.12.13
 * Time: 10:46
 */
public class Profile_Settings extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_settings);
        setFontStyle();
    }

    private void setFontStyle() {
        TextView pageTitle = (TextView) findViewById(R.id.profile_settings_textView_page_title);
        TextView language = (TextView) findViewById(R.id.profile_settings_TextView_language);
        EditText languageValue = (EditText) findViewById(R.id.profile_settings_EditText_language_value);
        TextView alert = (TextView) findViewById(R.id.profile_settings_TextView_alert);
        EditText alertValue = (EditText) findViewById(R.id.profile_settings_EditText_alert_value);
        TextView refillRemind = (TextView) findViewById(R.id.profile_settings_TextView_refillRemind);
        EditText refillRemindValue = (EditText) findViewById(R.id.profile_settings_EditText_refillRemind_value);
        Button confirm = (Button) findViewById(R.id.profile_settings_Button_confirm);

        Typeface type = Typeface.createFromAsset(getAssets(), "fonts/Georgia.ttf");

        pageTitle.setTypeface(type, Typeface.ITALIC);
        language.setTypeface(type);
        languageValue.setTypeface(type);
        alert.setTypeface(type);
        alertValue.setTypeface(type);
        refillRemind.setTypeface(type);
        refillRemindValue.setTypeface(type);
        confirm.setTypeface(type, Typeface.BOLD);
    }

    public void profile_settings_confirm_onClick(View v) {
        CommonFunctions.startNewActivity(Profile_Settings.this, Main.class);
        overridePendingTransition(R.anim.push_right_out, R.anim.push_right_in);
    }

    @Override
    public void onBackPressed() {
        Log.d(StaticVariables.TAG_RETURN_TO_PREVIOUS_ACTIVITY, this.getLocalClassName());
        super.onBackPressed();
        overridePendingTransition(R.anim.push_right_out, R.anim.push_right_in);
    }
}