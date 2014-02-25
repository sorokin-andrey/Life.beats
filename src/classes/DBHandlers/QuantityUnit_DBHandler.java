package classes.DBHandlers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import classes.DBTables.QuantityUnit;
import classes.StaticVariables;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Андрей
 * Date: 05.02.14
 * Time: 18:34
 */
public class QuantityUnit_DBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = StaticVariables.databaseName;
    public static final String TABLE_NAME = "tquantityunits";

    //region keys name
    private static final String KEY_ID = "iId";
    private static final String KEY_UNITNAME = "sUnitName";
    //endregion

    public QuantityUnit_DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" +
                KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                KEY_UNITNAME + " VARACHAR(32) NOT NULL" +
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
    public void addItem(QuantityUnit item) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            ContentValues values = new ContentValues();
            //values.put(KEY_ID, item.getId());
            values.put(KEY_UNITNAME, item.getUnitName());

            // Inserting Row
            db.insertOrThrow(TABLE_NAME, null, values);
        } catch (Exception ex) {
            Log.e("SQLException", "Exception in QuantityUnit_DBHandler " + String.valueOf(ex.getMessage()));
            ex.printStackTrace();
            db.close(); // Closing database connection
        } finally {
            db.close(); // Closing database connection
        }
    }

    // Getting single item
    public QuantityUnit getItem(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        QuantityUnit item = new QuantityUnit();
        try {
            Cursor cursor = db.query(TABLE_NAME, new String[]{KEY_ID, KEY_UNITNAME}, KEY_ID + "=?",

                    new String[]{String.valueOf(id)}, null, null, null, null);
            if (cursor != null && !cursor.isClosed()) {
                cursor.moveToFirst();

                item = new QuantityUnit(
                        cursor.getInt(0), // KEY_ID
                        cursor.getString(1)); // KEY_UNITNAME
            }
        } catch (NullPointerException ex) {
            Log.e("SQLException", "NullPointerException in QuantityUnit_DBHandler.getItem " + String.valueOf(ex.getMessage()));
            ex.printStackTrace();
        } catch (CursorIndexOutOfBoundsException e) {
            Log.e("SQLException", "CursorIndexOutOfBoundsException in QuantityUnit_DBHandler.getUserByEmail " + String.valueOf(e.getMessage()));
            e.printStackTrace();
        } catch (Exception ex) {
            Log.e("SQLException", "SQLException in QuantityUnit_DBHandler.getItem " + String.valueOf(ex.getMessage()));
            ex.printStackTrace();
        } finally {
            db.close(); // Closing database connection
        }
        return item;
    }

    // Getting single item
    public QuantityUnit getItem(String unitName) {
        SQLiteDatabase db = this.getReadableDatabase();
        QuantityUnit item = new QuantityUnit();
        try {
            Cursor cursor = db.query(TABLE_NAME, new String[]{KEY_ID, KEY_UNITNAME}, KEY_UNITNAME + "=?",

                    new String[]{String.valueOf(unitName)}, null, null, null, null);
            if (cursor != null && !cursor.isClosed()) {
                cursor.moveToFirst();

                item = new QuantityUnit(
                        cursor.getInt(0), // KEY_ID
                        cursor.getString(1)); // KEY_UNITNAME
            }
        } catch (NullPointerException ex) {
            Log.e("SQLException", "NullPointerException in QuantityUnit_DBHandler.getItem " + String.valueOf(ex.getMessage()));
            ex.printStackTrace();
        } catch (CursorIndexOutOfBoundsException e) {
            Log.e("SQLException", "CursorIndexOutOfBoundsException in QuantityUnit_DBHandler.getUserByEmail " + String.valueOf(e.getMessage()));
            e.printStackTrace();
        } catch (Exception ex) {
            Log.e("SQLException", "SQLException in QuantityUnit_DBHandler.getItem " + String.valueOf(ex.getMessage()));
            ex.printStackTrace();
        } finally {
            db.close(); // Closing database connection
        }
        return item;
    }

    // Getting All items
    public List<QuantityUnit> getAllItems() {
        List<QuantityUnit> quantityUnitList = new ArrayList<QuantityUnit>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                QuantityUnit item = new QuantityUnit();
                item.setId(Integer.parseInt(cursor.getString(0))); // KEY_ID
                item.setUnitName(cursor.getString(1)); // KEY_UNITNAME

                // Adding contact to list
                quantityUnitList.add(item);
            } while (cursor.moveToNext());
        }

        // return items list
        return quantityUnitList;
    }


    public List<String> getAllTitles() {
        List<String> listTitles = new ArrayList<String>();
        List<QuantityUnit> list = getAllItems();
        if (list != null && list.size() > 0) {
            for (QuantityUnit item : list) {
                listTitles.add(item.getUnitName());
            }
        }
        return listTitles;
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
            Log.e("SQLException", "NullPointerException in QuantityUnit_DBHandler.getItem " + String.valueOf(ex.getMessage()));
            ex.printStackTrace();
        } catch (Exception e) {
            Log.e("SQLException", "Exception in QuantityUnit_DBHandler.getItemsCount() " + String.valueOf(e.getMessage()));
            e.printStackTrace();
        } finally {
            db.close();
        }
        return count;
    }

    // Updating single item
    public int updateItem(QuantityUnit item) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_ID, item.getId()); // KEY_ID
        values.put(KEY_UNITNAME, item.getUnitName()); // KEY_REG_NUMBER

        // updating row
        return db.update(TABLE_NAME, values, KEY_ID + " = ?",
                new String[]{String.valueOf(item.getId())});
    }

    // Deleting single item
    public void deleteItem(QuantityUnit item) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_ID + " = ?",
                new String[]{String.valueOf(item.getId())});
        db.close();
    }
}