package classes.DBHandlers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import classes.DBTables.Mkb10_Proba;
import classes.StaticVariables;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Андрей
 * Date: 04.02.14
 * Time: 16:57
 */
public class Mkb10_Proba_DBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = StaticVariables.databaseName;
    public static final String TABLE_NAME = "mkb10proba";

    //region keys name
    private static final String KEY_ID = "ID";
    private static final String KEY_PARENT = "PARENT";
    private static final String KEY_CODE = "KOD";
    private static final String KEY_NAME = "NAME";
    //endregion

    public Mkb10_Proba_DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" +
                KEY_ID + " REAL PRIMARY KEY DEFAULT NULL, " +
                KEY_PARENT + " REAL DEFAULT NULL, " +
                KEY_CODE + " VARACHAR(255) DEFAULT NULL, " +
                KEY_NAME + " VARACHAR(255) DEFAULT NULL" +
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
    public void addItem(Mkb10_Proba item) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            ContentValues values = new ContentValues();
            //values.put(KEY_ID, item.getId());
            values.put(KEY_PARENT, item.getParrent());
            values.put(KEY_CODE, item.getCode());
            values.put(KEY_NAME, item.getName());
// Inserting Row
            db.insertOrThrow(TABLE_NAME, null, values);
        } catch (Exception ex) {
            Log.e("SQLException", "Exception in Mkb10_Proba_DBHandler " + String.valueOf(ex.getMessage()));
            ex.printStackTrace();
            db.close(); // Closing database connection
        } finally {
            db.close(); // Closing database connection
        }
    }

    // Getting single item
    public Mkb10_Proba getItem(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Mkb10_Proba item = new Mkb10_Proba();
        try {
            Cursor cursor = db.query(TABLE_NAME, new String[]{KEY_ID, KEY_PARENT, KEY_CODE, KEY_NAME}, KEY_ID + "=?",
                    new String[]{String.valueOf(id)}, null, null, null, null);
            if (cursor != null && !cursor.isClosed()) {
                cursor.moveToFirst();

                item = new Mkb10_Proba(
                        cursor.getInt(0), // KEY_ID
                        Integer.parseInt(cursor.getString(1)), // KEY_PARENT
                        cursor.getString(2), // KEY_CODE
                        cursor.getString(3)); // KEY_NAME
            }
        } catch (NullPointerException ex) {
            Log.e("SQLException", "NullPointerException in Mkb10_Proba_DBHandler.getItem " + String.valueOf(ex.getMessage()));
            ex.printStackTrace();
        } catch (CursorIndexOutOfBoundsException e) {
            Log.e("SQLException", "CursorIndexOutOfBoundsException in Mkb10_Proba_DBHandler.getUserByEmail " + String.valueOf(e.getMessage()));
            e.printStackTrace();
        } catch (Exception ex) {
            Log.e("SQLException", "SQLException in Mkb10_Proba_DBHandler.getItem " + String.valueOf(ex.getMessage()));
            ex.printStackTrace();
        } finally {
            db.close(); // Closing database connection
        }
        return item;
    }

    // Getting All items
    public List<Mkb10_Proba> getAllItems() {
        List<Mkb10_Proba> contactList = new ArrayList<Mkb10_Proba>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Mkb10_Proba item = new Mkb10_Proba(
                        Integer.parseInt(cursor.getString(0)), // KEY_ID
                        Integer.parseInt(cursor.getString(1)), // KEY_PARENT
                        cursor.getString(2), // KEY_CODE
                        cursor.getString(3)); // KEY_NAME

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
            Log.e("SQLException", "NullPointerException in Mkb10_Proba_DBHandler.getItem " + String.valueOf(ex.getMessage()));
            ex.printStackTrace();
        } catch (Exception e) {
            Log.e("SQLException", "Exception in Mkb10_Proba_DBHandler.getItemsCount() " + String.valueOf(e.getMessage()));
            e.printStackTrace();
        } finally {
            db.close();
        }
        return count;
    }

    // Updating single item
    public int updateItem(Mkb10_Proba item) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_ID, item.getId()); // KEY_ID
        values.put(KEY_PARENT, item.getParrent()); // KEY_PARENT
        values.put(KEY_CODE, item.getCode()); // KEY_CODE
        values.put(KEY_NAME, item.getName()); // KEY_NAME

        // updating row
        return db.update(TABLE_NAME, values, KEY_ID + " = ?",
                new String[]{String.valueOf(item.getId())});
    }

    // Deleting single item
    public void deleteItem(Mkb10_Proba item) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_ID + " = ?",
                new String[]{String.valueOf(item.getId())});
        db.close();
    }
}
