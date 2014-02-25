package com.quickblox.lifebeats.ui;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import classes.CommonFunctions;
import classes.DBHandlers.MyMedications_DBHandler;
import classes.DBHandlers.User_DBHandler;
import classes.DBInit;
import classes.DBTables.MyMedications;
import classes.DBTables.User;
import classes.StaticVariables;
import com.quickblox.lifebeats.R;
import com.quickblox.lifebeats.ui.old.Main;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Андрей
 * Date: 18.02.14
 * Time: 16:27
 */
public class NewLoginPage extends Activity {
    private Context context;
    private ProgressDialog pd;
    private EditText etEmail;
    private EditText etPassword;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_login_page);
        context = this;

        Typeface type = Typeface.createFromAsset(getAssets(), "fonts/Calibri.ttf");
        TextView title = (TextView) findViewById(R.id.login_TextView_title);
        TextView title2 = (TextView) findViewById(R.id.login_TextView_title2);
        etEmail = (EditText) findViewById(R.id.loginpage_editText_userName);
        etPassword = (EditText) findViewById(R.id.loginpage_editText_password);

        title.setTypeface(type);
        title2.setTypeface(type);
    }

    public void login_onClick(View v) {
        db_init();
    }

    public void register_onClick(View v) {
        CommonFunctions.startNewActivity(NewLoginPage.this, Registration.class);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    private void db_init() {
        AsyncTask<Void, Void, Boolean> task = new AsyncTask<Void, Void, Boolean>() {

            @Override
            protected void onPreExecute() {
                pd = new ProgressDialog(context);
                pd.setTitle("Processing...");
                pd.setMessage("Please wait.");
                pd.setCancelable(false);
                pd.setIndeterminate(true);
                pd.show();
            }

            @Override
            protected Boolean doInBackground(Void... arg0) {

                SQLiteDatabase myDB = null;
                try {
                    //context.deleteDatabase(StaticVariables.databaseName);
                    myDB = context.openOrCreateDatabase(StaticVariables.databaseName, MODE_WORLD_WRITEABLE, null);
                    User_DBHandler udbh = new User_DBHandler(context);
                    MyMedications_DBHandler mdbh = new MyMedications_DBHandler(context);

                    DBInit.Mkb10_Proba_Init(context, myDB);
                    DBInit.DosageUnit_Init(context, myDB);
                    DBInit.QuantityUnit_Init(context, myDB);
                    DBInit.Medication_Init(context, myDB);
                    DBInit.Recommendation_Init(context, myDB);
                    DBInit.User_Init(context, myDB);
                    DBInit.MedicalLs_Init(context, myDB);
                    DBInit.MyMedications_Init(context, myDB);

                    List<User> userList = udbh.getAllItems();
                    List<MyMedications> medicalLsList = mdbh.getAllItems();

                    if (CommonFunctions.doesDatabaseExist(context, StaticVariables.databaseName)) {
                        User_DBHandler userDBHandler = new User_DBHandler(context);
                        User user = userDBHandler.getUserByEmail(etEmail.getText().toString());
                        if (!user.getPassword().equals("")) {
                            String password = CommonFunctions.getStringFromEditText(NewLoginPage.this, etPassword);
                            if (user.getPassword().equals(password)) {
                                StaticVariables.currentUser = user;
                                CommonFunctions.startNewActivity(NewLoginPage.this, NewMainPage.class);
                                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                                return true;
                            } else
                                return false;
                        }
                        return false;
                    }
                    return false;
                } catch (Exception e) {
                    Log.e("SQLException", "Exception in dbInit " + String.valueOf(e.getMessage()));
                    e.printStackTrace();
                    return false;
                }
            }

            @Override
            protected void onPostExecute(Boolean result) {
                if (pd != null) {
                    pd.dismiss();
                    if (!result) {
                        CommonFunctions.showAlert(NewLoginPage.this, "There is some error", "Please enter correct password. Password must contains 6 to 20 characters, one digit from 0-9, one lowercase and one uppercase character.");
                    }
                }
            }
        };
        task.execute((Void[]) null);

    }

    @Override
    protected void onDestroy() {
        if (pd != null) {
            pd.dismiss();
        }
        super.onDestroy();
    }
}