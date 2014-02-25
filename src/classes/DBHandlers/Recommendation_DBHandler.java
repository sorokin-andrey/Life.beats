package classes.DBHandlers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import classes.CommonFunctions;
import classes.DBTables.Recommendation;
import classes.StaticVariables;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Андрей
 * Date: 05.02.14
 * Time: 18:41
 */
public class Recommendation_DBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = StaticVariables.databaseName;
    public static final String TABLE_NAME = "trecommendations";
    private static Context CONTEXT;

    //region keys name
    private static final String KEY_ID = "iId";
    private static final String KEY_USER_ID = "iUserId";
    private static final String KEY_CREATION_DATE = "dtCreationDate";
    private static final String KEY_CREATION_USER_ID = "creationUserId";
    //endregion

    public Recommendation_DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        CONTEXT = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" +
                KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                KEY_USER_ID + " INT(11) NOT NULL, " +
                KEY_CREATION_DATE + " DATETIME NOT NULL, " +
                KEY_CREATION_USER_ID + " INT(11) NOT NULL" +
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
    public void addItem(Recommendation item) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            ContentValues values = new ContentValues();
            //values.put(KEY_ID, item.getId());
            values.put(KEY_USER_ID, item.getUserId());
            values.put(KEY_CREATION_DATE, CommonFunctions.getStringFromDate(CONTEXT, item.getCreationDate(), StaticVariables.dateFormat));
            values.put(KEY_CREATION_USER_ID, item.getCreationUserId());

            // Inserting Row
            db.insertOrThrow(TABLE_NAME, null, values);
        } catch (Exception ex) {
            Log.e("SQLException", "Exception in Recommendation_DBHandler " + String.valueOf(ex.getMessage()));
            ex.printStackTrace();
            db.close(); // Closing database connection
        } finally {
            db.close(); // Closing database connection
        }
    }

    // Getting single item
    public Recommendation getItem(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Recommendation item = new Recommendation();
        try {
            Cursor cursor = db.query(TABLE_NAME, new String[]{KEY_ID, KEY_USER_ID, KEY_CREATION_DATE, KEY_CREATION_USER_ID}, KEY_ID + "=?",

                    new String[]{String.valueOf(id)}, null, null, null, null);
            if (cursor != null && !cursor.isClosed()) {
                cursor.moveToFirst();

                item = new Recommendation(
                        cursor.getInt(0), // KEY_ID
                        Integer.parseInt(cursor.getString(1)), // KEY_USER_ID
                        CommonFunctions.getDateFromString(CONTEXT, cursor.getString(1), StaticVariables.dateFormat), // KEY_CREATION_DATE
                        Integer.parseInt(cursor.getString(1))); // KEY_CREATION_USER_ID
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
    public List<Recommendation> getAllItems() {
        List<Recommendation> recommendationList = new ArrayList<Recommendation>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Recommendation item = new Recommendation();
                item.setId(Integer.parseInt(cursor.getString(0))); // KEY_ID
                item.setUserId(Integer.parseInt(cursor.getString(1))); // KEY_USER_ID
                item.setCreationDate(CommonFunctions.getDateFromString(CONTEXT, cursor.getString(1), StaticVariables.dateFormat)); // KEY_CREATION_DATE
                item.setCreationUserId(Integer.parseInt(cursor.getString(1))); // KEY_CREATION_USER_ID

                // Adding contact to list
                recommendationList.add(item);
            } while (cursor.moveToNext());
        }

        // return items list
        return recommendationList;
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
            Log.e("SQLException", "NullPointerException in Recommendation_DBHandler.getItem " + String.valueOf(ex.getMessage()));
            ex.printStackTrace();
        } catch (Exception e) {
            Log.e("SQLException", "Exception in Recommendation_DBHandler.getItemsCount() " + String.valueOf(e.getMessage()));
            e.printStackTrace();
        } finally {
            db.close();
        }
        return count;
    }

    // Updating single item
    public int updateItem(Recommendation item) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_ID, item.getId()); // KEY_ID
        values.put(KEY_USER_ID, item.getUserId()); // KEY_USER_ID
        values.put(KEY_CREATION_DATE, CommonFunctions.getStringFromDate(CONTEXT, item.getCreationDate(), StaticVariables.dateFormat)); // KEY_CREATION_DATE
        values.put(KEY_CREATION_USER_ID, item.getCreationUserId()); // KEY_CREATION_USER_ID

        // updating row
        return db.update(TABLE_NAME, values, KEY_ID + " = ?",
                new String[]{String.valueOf(item.getId())});
    }

    // Deleting single item
    public void deleteItem(Recommendation item) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_ID + " = ?",
                new String[]{String.valueOf(item.getId())});
        db.close();
    }
}
