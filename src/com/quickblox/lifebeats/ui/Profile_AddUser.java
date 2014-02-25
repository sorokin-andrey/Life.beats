package com.quickblox.lifebeats.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import classes.CommonFunctions;
import classes.StaticVariables;
import com.quickblox.lifebeats.R;
import com.quickblox.lifebeats.ui.old.Main;

import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Created with IntelliJ IDEA.
 * User: Андрей
 * Date: 10.12.13
 * Time: 12:11
 */
public class Profile_AddUser extends Activity {
    private static final int SELECT_PHOTO = 100;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_add_user);
        setFontStyle();
    }

    public void profile_currentUser_chooseImage_onClick(View v) {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, SELECT_PHOTO);
    }

    public void profile_currentUser_saveImage_onClick(View v) {
        CommonFunctions.startNewActivity(Profile_AddUser.this, Main.class);
        overridePendingTransition(R.anim.push_right_out, R.anim.push_right_in);
    }

    private void setFontStyle() {
        TextView pageTitle = (TextView) findViewById(R.id.profile_add_user_textView_page_title);
        TextView image = (TextView) findViewById(R.id.profile_add_user_TextView_profileimage);
        TextView name = (TextView) findViewById(R.id.profile_add_user_TextView_name);
        EditText nameValue = (EditText) findViewById(R.id.profile_add_user_EditText_name_value);
        TextView gender = (TextView) findViewById(R.id.profile_add_user_TextView_gender);
        EditText genderValue = (EditText) findViewById(R.id.profile_add_user_EditText_gender_value);
        TextView birthDate = (TextView) findViewById(R.id.profile_add_user_TextView_birthDate);
        EditText birthDateValue = (EditText) findViewById(R.id.profile_add_user_EditText_birthDate_value);
        TextView height = (TextView) findViewById(R.id.profile_add_user_TextView_height);
        EditText heightValue = (EditText) findViewById(R.id.profile_add_user_EditText_height_value);
        TextView weight = (TextView) findViewById(R.id.profile_add_user_TextView_weight);
        EditText weightValue = (EditText) findViewById(R.id.profile_add_user_EditText_weight_value);

        Typeface type = Typeface.createFromAsset(getAssets(), "fonts/Georgia.ttf");

        pageTitle.setTypeface(type, Typeface.ITALIC);
        image.setTypeface(type);
        name.setTypeface(type);
        nameValue.setTypeface(type);
        gender.setTypeface(type);
        genderValue.setTypeface(type);
        birthDate.setTypeface(type);
        birthDateValue.setTypeface(type);
        height.setTypeface(type);
        heightValue.setTypeface(type);
        weight.setTypeface(type);
        weightValue.setTypeface(type);
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

                        ImageView user_image = (ImageView) findViewById(R.id.profile_current_user_ImageView_profileimage);
                        user_image.setImageBitmap(bitmap);
                    }
            }
        } catch (FileNotFoundException e) {
            CommonFunctions.showAlert(Profile_AddUser.this, "error", e.getMessage());
        }
    }

    @Override
    public void onBackPressed() {
        Log.d(StaticVariables.TAG_RETURN_TO_PREVIOUS_ACTIVITY, this.getLocalClassName());
        super.onBackPressed();
        overridePendingTransition(R.anim.push_right_out, R.anim.push_right_in);
    }
}