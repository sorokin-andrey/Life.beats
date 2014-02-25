package classes.DBHandlers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import classes.CommonFunctions;
import classes.DBTables.Specialist;
import classes.StaticVariables;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Андрей
 * Date: 05.02.14
 * Time: 19:04
 */
public class Specialist_DBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = StaticVariables.databaseName;
    public static final String TABLE_NAME = "tspecialists";
    private static Context CONTEXT;

    //region keys name
    private static final String KEY_ID = "iId";
    private static final String KEY_NAME = "sName";
    private static final String KEY_SURNAME = "sSurname";
    private static final String KEY_SPECIALIST_NAME = "sSpecialistName";
    private static final String KEY_ADDRESS = "sAddress";
    private static final String KEY_EMAIL = "sEmail";
    private static final String KEY_PHONE = "sPhone";
    private static final String KEY_MEDICAL_CENTER = "iMedicalCenter";
    private static final String KEY_PASSWORD = "cPassword";
    private static final String KEY_SALT = "cSalt";
    private static final String KEY_DATE_CREATED = "dtDateCreated";
    private static final String KEY_PROFILE_IMAGE = "profile_image";
    //endregion

    public Specialist_DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        CONTEXT = context;
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" +
                KEY_ID + " INT(11) PRIMARY KEY NOT NULL, " +
                KEY_NAME + " VARACHAR(64) NOT NULL, " +
                KEY_SURNAME + " VARACHAR(128) NOT NULL, " +
                KEY_SPECIALIST_NAME + " VARACHAR(128) NOT NULL, " +
                KEY_ADDRESS + " VARACHAR(256) NOT NULL, " +
                KEY_EMAIL + " VARACHAR(64) NOT NULL, " +
                KEY_PHONE + " VARACHAR(12) NOT NULL, " +
                KEY_MEDICAL_CENTER + " INT(11) NOT NULL, " +
                KEY_PASSWORD + " VARACHAR(128) NOT NULL, " +
                KEY_SALT + " VARACHAR(128) NOT NULL, " +
                KEY_DATE_CREATED + " DATETIME NOT NULL, " +
                KEY_PROFILE_IMAGE + " VARACHAR(200) NOT NULL" +
                ") ";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Adding new item
    public void addItem(Specialist item) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            ContentValues values = new ContentValues();
            values.put(KEY_ID, item.getId());
            values.put(KEY_NAME, item.getName());
            values.put(KEY_SURNAME, item.getSurname());
            values.put(KEY_SPECIALIST_NAME, item.getSpecialistName());
            values.put(KEY_ADDRESS, item.getAddress());
            values.put(KEY_EMAIL, item.getEmail());
            values.put(KEY_PHONE, item.getPhone());
            values.put(KEY_MEDICAL_CENTER, item.getMedicalCenter());
            values.put(KEY_PASSWORD, item.getPassword());
            values.put(KEY_SALT, item.getSalt());
            values.put(KEY_DATE_CREATED, CommonFunctions.getStringFromDate(CONTEXT, item.getDateCreated(), StaticVariables.dateFormat));
            values.put(KEY_PROFILE_IMAGE, item.getProfileImage());

            // Inserting Row
            db.insert(TABLE_NAME, null, values);
        } catch (Exception ex) {
            Log.e("SQLException", "Exception in Specialist_DBHandler " + String.valueOf(ex.getMessage()));
            ex.printStackTrace();
            db.close(); // Closing database connection
        } finally {
            db.close(); // Closing database connection
        }
    }

    // Getting single item
    public Specialist getItem(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Specialist item = new Specialist();
        try {
            Cursor cursor = db.query(TABLE_NAME, new String[]{KEY_ID, KEY_NAME, KEY_SURNAME, KEY_SPECIALIST_NAME,
                    KEY_ADDRESS, KEY_EMAIL, KEY_PHONE, KEY_MEDICAL_CENTER, KEY_PASSWORD, KEY_SALT, KEY_DATE_CREATED,
                    KEY_PROFILE_IMAGE}, KEY_ID + "=?",

                    new String[]{String.valueOf(id)}, null, null, null, null);
            if (cursor != null && !cursor.isClosed()) {
                cursor.moveToFirst();

                item = new Specialist(
                        cursor.getInt(0), // KEY_ID
                        cursor.getString(1), // KEY_NAME
                        cursor.getString(2), // KEY_SURNAME
                        cursor.getString(3), // KEY_SPECIALIST_NAME
                        cursor.getString(4), // KEY_ADDRESS
                        cursor.getString(5), // KEY_EMAIL
                        cursor.getString(6), // KEY_PHONE
                        Integer.parseInt(cursor.getString(7)), // KEY_MEDICAL_CENTER
                        cursor.getString(8), // KEY_PASSWORD
                        cursor.getString(9), // KEY_SALT
                        CommonFunctions.getDateFromString(CONTEXT, cursor.getString(10), StaticVariables.dateFormat), // KEY_DATE_CREATED
                        cursor.getString(11)); // KEY_PROFILE_IMAGE
            }
        } catch (NullPointerException ex) {
            Log.e("SQLException", "NullPointerException in Recommendation_DBHandler.getItem " + String.valueOf(ex.getMessage()));
            ex.printStackTrace();
        } catch (CursorIndexOutOfBoundsException e) {
            Log.e("SQLException", "CursorIndexOutOfBoundsException in Recommendation_DBHandler.getUserByEmail " + String.valueOf(e.getMessage()));
            e.printStackTrace();
        } catch (Exception ex) {
            Log.e("SQLException", "SQLException in Recommendation_DBHandler.getItem " + String.valueOf(ex.getMessage()));
            ex.printStackTrace();
        } finally {
            db.close(); // Closing database connection
        }
        return item;
    }

    // Getting All items
    public List<Specialist> getAllItems() {
        List<Specialist> specialistList = new ArrayList<Specialist>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Specialist item = new Specialist();
                item.setId(Integer.parseInt(cursor.getString(0))); // KEY_ID
                item.setName(cursor.getString(1)); // KEY_NAME
                item.setSurname(cursor.getString(2)); // KEY_SURNAME
                item.setSpecialistName(cursor.getString(3)); // KEY_SPECIALIST_NAME
                item.setAddress(cursor.getString(4)); // KEY_ADDRESS
                item.setEmail(cursor.getString(5)); // KEY_EMAIL
                item.setPhone(cursor.getString(6)); // KEY_PHONE
                item.setMedicalCenter(Integer.parseInt(cursor.getString(7))); // KEY_MEDICAL_CENTER
                item.setPassword(cursor.getString(8)); // KEY_PASSWORD
                item.setSalt(cursor.getString(9)); // KEY_SALT
                item.setDateCreated(CommonFunctions.getDateFromString(CONTEXT, cursor.getString(10), StaticVariables.dateFormat)); // KEY_DATE_CREATED
                item.setProfileImage(cursor.getString(11)); // KEY_PROFILE_IMAGE

                // Adding contact to list
                specialistList.add(item);
            } while (cursor.moveToNext());
        }

        // return items list
        return specialistList;
    }

    // Getting items Count
    public int getItemsCount() {
        int count = 0;
        String countQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            Cursor cursor = db.rawQuery(countQuery, null);

            if (cursor != null && !cursor.isClosed()) {
                count = cursor.getCount();
            }
        } catch (NullPointerException ex) {
            Log.e("SQLException", "NullPointerException in Specialist_DBHandler.getItem " + String.valueOf(ex.getMessage()));
            ex.printStackTrace();
        } catch (Exception e) {
            Log.e("SQLException", "Exception in Specialist_DBHandler.getItemsCount() " + String.valueOf(e.getMessage()));
            e.printStackTrace();
        } finally {
            db.close();
        }
        return count;
    }

    // Updating single item
    public int updateItem(Specialist item) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_ID, item.getId()); // KEY_ID
        values.put(KEY_NAME, item.getName()); // KEY_NAME
        values.put(KEY_SURNAME, item.getSurname()); // KEY_SURNAME
        values.put(KEY_SPECIALIST_NAME, item.getSpecialistName()); // KEY_SPECIALIST_NAME
        values.put(KEY_ADDRESS, item.getAddress()); // KEY_ADDRESS
        values.put(KEY_EMAIL, item.getEmail()); // KEY_EMAIL
        values.put(KEY_PHONE, item.getPhone()); // KEY_PHONE
        values.put(KEY_MEDICAL_CENTER, item.getMedicalCenter()); // KEY_MEDICAL_CENTER
        values.put(KEY_PASSWORD, item.getPassword()); // KEY_PASSWORD
        values.put(KEY_SALT, item.getSalt()); // KEY_SALT
        values.put(KEY_DATE_CREATED, CommonFunctions.getStringFromDate(CONTEXT, item.getDateCreated(), StaticVariables.dateFormat)); // KEY_DATE_CREATED
        values.put(KEY_PROFILE_IMAGE, item.getProfileImage()); // KEY_PROFILE_IMAGE

        // updating row
        return db.update(TABLE_NAME, values, KEY_ID + " = ?",
                new String[]{String.valueOf(item.getId())});
    }

    // Deleting single item
    public void deleteItem(Specialist item) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_ID + " = ?",
                new String[]{String.valueOf(item.getId())});
        db.close();
    }
}