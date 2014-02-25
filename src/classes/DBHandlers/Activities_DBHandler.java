package classes.DBHandlers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import classes.CommonFunctions;
import classes.DBTables.Activities;
import classes.StaticVariables;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Андрей
 * Date: 05.02.14
 * Time: 16:14
 */
public class Activities_DBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = StaticVariables.databaseName;
    private static final String TABLE_NAME = "tactivity";
    private static Context CONTEXT;

    //region keys name
    private static final String KEY_ID = "iId";
    private static final String KEY_USER_ID = "iUserId";
    private static final String KEY_DESCRIPTION = "sActivityDescr";
    private static final String KEY_QUANTITY = "iActivityQuantity";
    private static final String KEY_QUANTITY_UNIT = "iActivityQuantityUnit";
    private static final String KEY_START_DATE = "dtActivityStartDate";
    private static final String KEY_END_DATE = "dtActivityEndDate";
    private static final String KEY_RECOMMENDATION_ID = "iRecommendationId";
    //endregion

    public Activities_DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        CONTEXT = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" +
                KEY_ID + " INT(11) PRIMARY KEY AUTOINCREMENT NOT NULL," +
                KEY_USER_ID + " INT(11) NOT NULL," +
                KEY_DESCRIPTION + " VARACHAR(128) NOT NULL," +
                KEY_QUANTITY + " INT(11) NOT NULL," +
                KEY_QUANTITY_UNIT + " INT(11) NOT NULL," +
                KEY_START_DATE + " DATETIME NOT NULL," +
                KEY_END_DATE + " DATETIME NOT NULL," +
                KEY_RECOMMENDATION_ID + " INT(11) NOT NULL" +
                ") ";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    // Adding new item
    public void addItem(Activities item) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            ContentValues values = new ContentValues();
            values.put(KEY_USER_ID, item.getUserId());
            values.put(KEY_DESCRIPTION, item.getDescription());
            values.put(KEY_QUANTITY, item.getQuantity());
            values.put(KEY_QUANTITY_UNIT, item.getQuantityUnit());
            values.put(KEY_START_DATE, CommonFunctions.getStringFromDate(CONTEXT, item.getStartDate(), StaticVariables.dateFormat));
            values.put(KEY_END_DATE, CommonFunctions.getStringFromDate(CONTEXT, item.getEndDate(), StaticVariables.dateFormat));
            values.put(KEY_RECOMMENDATION_ID, item.getRecommendationId());

            // Inserting Row
            db.insert(TABLE_NAME, null, values);
        } catch (Exception ex) {
            Log.e("SQLException", "Exception in Activities_DBHandler " + String.valueOf(ex.getMessage()));
            ex.printStackTrace();
            db.close(); // Closing database connection
        } finally {
            db.close(); // Closing database connection
        }
    }

    // Getting single item
    public Activities getItem(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Activities item = new Activities();
        try {
            Cursor cursor = db.query(TABLE_NAME, new String[]{KEY_ID, KEY_USER_ID, KEY_DESCRIPTION, KEY_QUANTITY, KEY_QUANTITY_UNIT,
                    KEY_START_DATE, KEY_END_DATE, KEY_RECOMMENDATION_ID}, KEY_ID + "=?",

                    new String[]{String.valueOf(id)}, null, null, null, null);
            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst();

                item = new Activities(
                        cursor.getInt(0), // KEY_ID
                        Integer.parseInt(cursor.getString(1)), // KEY_USER_ID
                        cursor.getString(2), // KEY_DESCRIPTION
                        Integer.parseInt(cursor.getString(3)), // KEY_QUANTITY
                        Integer.parseInt(cursor.getString(4)), // KEY_QUANTITY_UNIT
                        new Date(), // KEY_START_DATE
                        new Date(), // KEY_END_DATE
                        Integer.parseInt(cursor.getString(7))); // KEY_RECOMMENDATION_ID
            }
        } catch (NullPointerException ex) {
            Log.e("SQLException", "NullPointerException in Activities_DBHandler.getItem " + String.valueOf(ex.getMessage()));
            ex.printStackTrace();
        } catch (CursorIndexOutOfBoundsException e) {
            Log.e("SQLException", "CursorIndexOutOfBoundsException in Activities_DBHandler.getUserByEmail " + String.valueOf(e.getMessage()));
            e.printStackTrace();
        } catch (Exception ex) {
            Log.e("SQLException", "SQLException in Activities_DBHandler.getItem " + String.valueOf(ex.getMessage()));
            ex.printStackTrace();
        } finally {
            db.close(); // Closing database connection
        }
        return item;
    }

    // Getting All items
    public List<Activities> getAllItems() {
        List<Activities> contactList = new ArrayList<Activities>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Activities item = new Activities();
                item.setId(Integer.parseInt(cursor.getString(0))); // KEY_ID
                item.setUserId(Integer.parseInt(cursor.getString(1))); // KEY_USER_ID
                item.setDescription(cursor.getString(2)); // KEY_DESCRIPTION
                item.setQuantity(Integer.parseInt(cursor.getString(3))); // KEY_QUANTITY
                item.setQuantityUnit(Integer.parseInt(cursor.getString(4))); // KEY_QUANTITY_UNIT
                item.setStartDate(CommonFunctions.getDateFromString(CONTEXT, cursor.getString(5), StaticVariables.dateFormat)); // KEY_START_DATE
                item.setEndDate(CommonFunctions.getDateFromString(CONTEXT, cursor.getString(6), StaticVariables.dateFormat)); // KEY_END_DATE
                item.setRecommendationId(Integer.parseInt(cursor.getString(7))); // KEY_RECOMMENDATION_ID

                // Adding contact to list
                contactList.add(item);
            } while (cursor.moveToNext());
        }

        // return items list
        return contactList;
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
            Log.e("SQLException", "NullPointerException in Activities_DBHandler.getItem " + String.valueOf(ex.getMessage()));
            ex.printStackTrace();
        } catch (Exception e) {
            Log.e("SQLException", "Exception in Activities_DBHandler.getItemsCount() " + String.valueOf(e.getMessage()));
            e.printStackTrace();
        } finally {
            db.close();
        }
        return count;
    }

    // Updating single item
    public int updateItem(Activities item) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_USER_ID, item.getUserId()); // KEY_USER_ID
        values.put(KEY_DESCRIPTION, item.getDescription()); // KEY_DESCRIPTION
        values.put(KEY_QUANTITY, item.getQuantity()); // KEY_QUANTITY
        values.put(KEY_QUANTITY_UNIT, item.getQuantityUnit()); // KEY_QUANTITY_UNIT
        values.put(KEY_START_DATE, CommonFunctions.getStringFromDate(CONTEXT, item.getStartDate(), StaticVariables.dateFormat)); // KEY_START_DATE
        values.put(KEY_END_DATE, CommonFunctions.getStringFromDate(CONTEXT, item.getEndDate(), StaticVariables.dateFormat)); // KEY_END_DATE
        values.put(KEY_RECOMMENDATION_ID, item.getRecommendationId()); // KEY_RECOMMENDATION_ID

        // updating row
        return db.update(TABLE_NAME, values, KEY_ID + " = ?",
                new String[]{String.valueOf(item.getId())});
    }

    // Deleting single item
    public void deleteItem(Activities item) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_ID + " = ?",
                new String[]{String.valueOf(item.getId())});
        db.close();
    }
}
