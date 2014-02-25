package com.quickblox.lifebeats.ui;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.*;
import classes.BirthDatePickerFragment;
import classes.CommonFunctions;
import classes.DBHandlers.User_DBHandler;
import classes.ImageHelper;
import classes.StaticVariables;
import com.quickblox.lifebeats.R;
import com.quickblox.lifebeats.ui.old.Main;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Andrey
 * Date: 10.12.13
 * Time: 10:35
 */
public class Profile_CurrentUser extends FragmentActivity {
    private static final int SELECT_PHOTO = 100;

    ImageView profileImage;
    EditText nameValue;
    EditText surnameValue;
    Spinner genderValue;
    EditText birthDateValue;
    EditText heightValue;
    EditText weightValue;
    EditText emailValue;
    EditText phoneNumberValue;
    EditText passwordValue;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_current_user);
        setFontStyle();
        setProfileInformation();

        Spinner spinnerUnit = (Spinner) findViewById(R.id.profile_currentUser_Spinner_gender);
        prepare_unit(spinnerUnit);
    }

    public void profile_currentUser_chooseImage_onClick(View v) {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, SELECT_PHOTO);
    }

    public void profile_currentUser_saveImage_onClick(View v) {
        SQLiteDatabase myDB = null;
        try {
            myDB = this.openOrCreateDatabase(StaticVariables.databaseName, MODE_PRIVATE, null);
            User_DBHandler userDBHandler = new User_DBHandler(this);
            Bitmap bitmap = ((BitmapDrawable) profileImage.getDrawable()).getBitmap();

            StaticVariables.currentUser.setProfileImage("http://i.piccy.info/i9/ada71ea984d888b9fa0304662c28d3d3/1392211781/8427/699003/1234_240.jpg");
            StaticVariables.currentUser.setName(CommonFunctions.getStringFromEditText(this, nameValue));
            StaticVariables.currentUser.setSurname(CommonFunctions.getStringFromEditText(this, surnameValue));
            StaticVariables.currentUser.setGender(genderValue.getSelectedItem().toString());
            StaticVariables.currentUser.setBirthDate(CommonFunctions.getDateFromString(this, CommonFunctions.getStringFromEditText(this, birthDateValue), StaticVariables.dateFormat));
            StaticVariables.currentUser.setHeight(this, CommonFunctions.getStringFromEditText(this, heightValue));
            StaticVariables.currentUser.setWeight(this, CommonFunctions.getStringFromEditText(this, weightValue));
            StaticVariables.currentUser.setEmail(CommonFunctions.getStringFromEditText(this, emailValue));
            StaticVariables.currentUser.setPhone(CommonFunctions.getStringFromEditText(this, phoneNumberValue));
            if (!CommonFunctions.getStringFromEditText(this, passwordValue).equals(""))
                StaticVariables.currentUser.setPassword(CommonFunctions.getStringFromEditText(this, passwordValue));
            userDBHandler.updateItem(StaticVariables.currentUser);
        } catch (NullPointerException e) {
            CommonFunctions.showAlert(getBaseContext(), "NullPointerException in profile_currentUser_saveImage_onClick", e.getMessage());
        } catch (Exception e) {
            CommonFunctions.showAlert(getBaseContext(), "Exception in profile_currentUser_saveImage_onClick", e.getMessage());
        } finally {
            if (myDB != null)
                myDB.close();
            CommonFunctions.startNewActivityWithClearingBackStack(this, NewMainPage.class);
            overridePendingTransition(R.anim.push_right_out, R.anim.push_right_in);
        }
    }

    public void profile_currentUser_removeImage_onClick(View v) {

    }

    private void setFontStyle() {
        TextView pageTitle = (TextView) findViewById(R.id.profile_current_user_textView_page_title);
        TextView image = (TextView) findViewById(R.id.profile_current_user_TextView_profileimage);
        TextView name = (TextView) findViewById(R.id.profile_current_user_TextView_name);
        TextView gender = (TextView) findViewById(R.id.profile_current_user_TextView_gender);
        TextView birthDate = (TextView) findViewById(R.id.profile_current_user_TextView_birthDate);
        TextView height = (TextView) findViewById(R.id.profile_current_user_TextView_height);
        TextView weight = (TextView) findViewById(R.id.profile_current_user_TextView_weight);
        TextView surname = (TextView) findViewById(R.id.profile_current_user_TextView_surname);
        TextView email = (TextView) findViewById(R.id.profile_current_user_TextView_email);
        TextView phoneNumber = (TextView) findViewById(R.id.profile_current_user_TextView_phone);
        TextView password = (TextView) findViewById(R.id.profile_current_user_TextView_password);

        nameValue = (EditText) findViewById(R.id.profile_current_user_EditText_name_value);
        surnameValue = (EditText) findViewById(R.id.profile_current_user_EditText_surname_value);
        genderValue = (Spinner) findViewById(R.id.profile_currentUser_Spinner_gender);
        birthDateValue = (EditText) findViewById(R.id.profile_current_user_EditText_birthDate_value);
        heightValue = (EditText) findViewById(R.id.profile_current_user_EditText_height_value);
        weightValue = (EditText) findViewById(R.id.profile_current_user_EditText_weight_value);
        emailValue = (EditText) findViewById(R.id.profile_current_user_EditText_email_value);
        phoneNumberValue = (EditText) findViewById(R.id.profile_current_user_EditText_phone_value);
        passwordValue = (EditText) findViewById(R.id.profile_current_user_EditText_password_value);

        profileImage = (ImageView) findViewById(R.id.profile_current_user_ImageView_profileimage);

        Typeface type = Typeface.createFromAsset(getAssets(), "fonts/Georgia.ttf");

        pageTitle.setTypeface(type, Typeface.ITALIC);
        image.setTypeface(type);
        name.setTypeface(type);
        nameValue.setTypeface(type);
        surname.setTypeface(type);
        surnameValue.setTypeface(type);
        gender.setTypeface(type);
        //genderValue.setTypeface(type);
        birthDate.setTypeface(type);
        birthDateValue.setTypeface(type);
        height.setTypeface(type);
        heightValue.setTypeface(type);
        weight.setTypeface(type);
        weightValue.setTypeface(type);
        email.setTypeface(type);
        emailValue.setTypeface(type);
        phoneNumber.setTypeface(type);
        phoneNumberValue.setTypeface(type);
        password.setTypeface(type);
        passwordValue.setTypeface(type);
    }

    private void setProfileInformation() {
        if (!StaticVariables.currentUser.getProfileImage().equals(""))
            new ImageHelper(profileImage).execute(StaticVariables.currentUser.getProfileImage());
        nameValue.setText(StaticVariables.currentUser.getName());
        surnameValue.setText(StaticVariables.currentUser.getSurname());
        genderValue.setSelection(Integer.valueOf(StaticVariables.currentUser.getSex()));
        birthDateValue.setText(CommonFunctions.getStringFromDate(getBaseContext(), StaticVariables.currentUser.getBirthDate(), StaticVariables.dateFormat));
        heightValue.setText(StaticVariables.currentUser.getHeightAsString());
        weightValue.setText(StaticVariables.currentUser.getWeightAsString());
        emailValue.setText(StaticVariables.currentUser.getEmail());
        phoneNumberValue.setText(StaticVariables.currentUser.getPhone());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        try {
            switch (requestCode) {
                case SELECT_PHOTO:
                    if (resultCode == RESULT_OK) {
                        Uri selectedImage = imageReturnedIntent.getData();
                        InputStream imageStream = null;
                        imageStream = getContentResolver().openInputStream(selectedImage);
                        Bitmap bitmap = BitmapFactory.decodeStream(imageStream);
                        StaticVariables.currentUser.setProfileImage(selectedImage.getPath());
                        profileImage.setImageBitmap(bitmap);
                    }
            }
        } catch (FileNotFoundException e) {
            CommonFunctions.showAlert(Profile_CurrentUser.this, "error", e.getMessage());
        }
    }

    public void editText_onClick(View v) {
        DialogFragment newFragment = new BirthDatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "birthDateValue");
    }

    private void prepare_unit(Spinner spinnerUnit) {
        List<String> list = new ArrayList<String>();

        list.add("Female");
        list.add("Male");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerUnit.setAdapter(dataAdapter);
        int gender = CommonFunctions.getIntFromString(this, StaticVariables.currentUser.getSex());
        spinnerUnit.setSelection(gender);
    }

    @Override
    public void onBackPressed() {
        Log.d(StaticVariables.TAG_RETURN_TO_PREVIOUS_ACTIVITY, this.getLocalClassName());
        super.onBackPressed();
        overridePendingTransition(R.anim.push_right_out, R.anim.push_right_in);
    }
}