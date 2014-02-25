package com.quickblox.lifebeats.ui;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import classes.CommonFunctions;
import classes.DBHandlers.User_DBHandler;
import classes.DBTables.User;
import classes.StaticVariables;
import com.quickblox.lifebeats.R;
import com.quickblox.lifebeats.ui.old.LoginPage;

/**
 * Created with IntelliJ IDEA.
 * User: Андрей
 * Date: 12.02.14
 * Time: 10:32
 */
public class Registration extends Activity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);

    }

    public void create_onClick(View v) {
        EditText email = (EditText) findViewById(R.id.registration_EditText_email_value);
        EditText password = (EditText) findViewById(R.id.registration_EditText_password_value);

//        boolean isEmailCorrect = CommonFunctions.checkIfTextIsValid(this, email, "^[^@]+@[^@]+\\.[^@]+$", "Please enter correct email address");
//        boolean isPasswordCorrect = CommonFunctions.checkIfTextIsValid(this, password, "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,20})", "Please enter correct password. Password must contains 6 to 20 characters, one digit from 0-9, one lowercase and one uppercase character.");
//
//        if (isEmailCorrect && isPasswordCorrect) {
        User user = new User(this);
        user.setEmail(CommonFunctions.getStringFromEditText(this, email));
        user.setPassword(CommonFunctions.getStringFromEditText(this, password));
        User_DBHandler udbh = new User_DBHandler(this);
        udbh.addItem(user);
        CommonFunctions.showAlert(this, "New user was successfully created.", "Now you can sign in with your email and password");
        CommonFunctions.startNewActivity(Registration.this, LoginPage.class);
        overridePendingTransition(R.anim.push_right_out, R.anim.push_right_in);
        //}
    }

    @Override
    public void onBackPressed() {
        Log.d(StaticVariables.TAG_RETURN_TO_PREVIOUS_ACTIVITY, this.getLocalClassName());
        super.onBackPressed();
        overridePendingTransition(R.anim.push_right_out, R.anim.push_right_in);
    }
}