package com.quickblox.lifebeats.ui;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import classes.CommonFunctions;
import classes.StaticVariables;
import com.quickblox.lifebeats.R;
import com.quickblox.lifebeats.ui.old.Main;

/**
 * Created with IntelliJ IDEA.
 * User: Андрей
 * Date: 12/2/13
 * Time: 12:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class Profile extends Activity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        setFontStyle();

//        profileButton = (ImageButton) findViewById(R.id.main_imageButton_profile);
//        profileButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.setType("image/*");
//                intent.setAction(Intent.ACTION_GET_CONTENT);
//                startActivityForResult(Intent.createChooser(intent, "Complete action using"), 1);
//            }
//        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            Uri selectedImageUri = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(selectedImageUri, filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String filePath = cursor.getString(columnIndex);
            cursor.close();

            Bitmap selectedImage = BitmapFactory.decodeFile(filePath);
            //profileButton.setImageBitmap(selectedImage);
        }
    }

    public void profile_button_create_onClick(View v) {
        //EditText userName = (EditText) findViewById(R.id.main_EditText_userName);
        //StaticVariables.userName = userName.getText().toString();
        CommonFunctions.startNewActivity(Profile.this, Main.class);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    private void setFontStyle() {
        TextView pageTitle = (TextView) findViewById(R.id.profile_textView_page_title);
        TextView currentUser = (TextView) findViewById(R.id.profile_textView_current_user);
        TextView chooseUser = (TextView) findViewById(R.id.profile_textView_choose_user);
        TextView addUser = (TextView) findViewById(R.id.profile_textView_add_user);
        TextView settings = (TextView) findViewById(R.id.profile_textView_settings);

        Typeface type = Typeface.createFromAsset(getAssets(), "fonts/Georgia.ttf");

        pageTitle.setTypeface(type, Typeface.ITALIC);
        currentUser.setTypeface(type);
        chooseUser.setTypeface(type);
        addUser.setTypeface(type);
        settings.setTypeface(type);
    }

    public void profile_button_current_user_onClick(View v) {
        CommonFunctions.startNewActivity(Profile.this, Profile_CurrentUser.class);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    public void profile_button_add_user_onClick(View v) {
        CommonFunctions.startNewActivity(Profile.this, Profile_AddUser.class);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    public void profile_button_settings_onClick(View v) {
        CommonFunctions.startNewActivity(Profile.this, Profile_Settings.class);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    @Override
    public void onBackPressed() {
        Log.d(StaticVariables.TAG_RETURN_TO_PREVIOUS_ACTIVITY, this.getLocalClassName());
        super.onBackPressed();
        overridePendingTransition(R.anim.push_right_out, R.anim.push_right_in);
    }
}