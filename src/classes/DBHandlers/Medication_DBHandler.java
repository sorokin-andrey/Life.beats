package classes.DBHandlers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import classes.CommonFunctions;
import classes.DBTables.Medication;
import classes.StaticVariables;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Андрей
 * Date: 05.02.14
 * Time: 17:36
 */
public class Medication_DBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = StaticVariables.databaseName;
    public static final String TABLE_NAME = "tmedication";
    private static Context CONTEXT;

    //region keys name
    private static final String KEY_ID = "iId";
    private static final String KEY_USER_ID = "iUserId";
    private static final String KEY_MEDICATION_ID = "iMedicationId";
    private static final String KEY_DOSAGE = "iDosage";
    private static final String KEY_DOSAGE_UNIT = "iDosageUnit";
    private static final String KEY_QUANTITY = "iQuantity";
    private static final String KEY_QUANTITY_UNIT = "iQuantityUnit";
    private static final String KEY_PERIODICITY = "iPeriodicity";
    private static final String KEY_PERIODICITY_UNIT = "iPeriodicityUnit";
    private static final String KEY_START_DATE = "dtMedicationStartDate";
    private static final String KEY_END_DATE = "dtMedicationEndDate";
    private static final String KEY_DISEASE_ID = "iDiseaseId";
    private static final String KEY_RECOMMENDATION_ID = "iRecommendationId";
    //endregion

    public Medication_DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        CONTEXT = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" +
                KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                KEY_USER_ID + " INT(11) NOT NULL, " +
                KEY_MEDICATION_ID + " INT(11) NOT NULL, " +
                KEY_DOSAGE + " REAL NOT NULL, " +
                KEY_DOSAGE_UNIT + " INT(11) NOT NULL, " +
                KEY_QUANTITY + " INT(11) NOT NULL, " +
                KEY_QUANTITY_UNIT + " INT(11) NOT NULL, " +
                KEY_PERIODICITY + " INT(11) NOT NULL, " +
                KEY_PERIODICITY_UNIT + " INT(11) NOT NULL, " +
                KEY_START_DATE + " DATETIME NOT NULL, " +
                KEY_END_DATE + " DATETIME NOT NULL, " +
                KEY_DISEASE_ID + " INT(11) NOT NULL, " +
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
    public void addItem(Medication item) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            ContentValues values = new ContentValues();
            values.put(KEY_USER_ID, item.getUserId());
            values.put(KEY_MEDICATION_ID, item.getMedicationId());
            values.put(KEY_DOSAGE, item.getDosage());
            values.put(KEY_DOSAGE_UNIT, item.getDosageUnit());
            values.put(KEY_QUANTITY, item.getQuantity());
            values.put(KEY_QUANTITY_UNIT, item.getQuantityUnit());
            values.put(KEY_PERIODICITY, item.getPeriodicity());
            values.put(KEY_PERIODICITY_UNIT, item.getPeriodicityUnit());
            values.put(KEY_START_DATE, CommonFunctions.getStringFromDate(CONTEXT, item.getStartDate(), StaticVariables.dateFormat));
            values.put(KEY_END_DATE, CommonFunctions.getStringFromDate(CONTEXT, item.getEndDate(), StaticVariables.dateFormat));
            values.put(KEY_DISEASE_ID, item.getDiseaseId());
            values.put(KEY_RECOMMENDATION_ID, item.getRecommendationId());
            // Inserting Row
            db.insertOrThrow(TABLE_NAME, null, values);
        } catch (Exception ex) {
            Log.e("SQLException", "Exception in Medication_DBHandler " + String.valueOf(ex.getMessage()));
            ex.printStackTrace();
            db.close(); // Closing database connection
        } finally {
            db.close(); // Closing database connection
        }
    }

    // Getting single item
    public Medication getItem(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Medication item = new Medication();
        try {
            Cursor cursor = db.query(TABLE_NAME, new String[]{KEY_ID, KEY_USER_ID, KEY_MEDICATION_ID, KEY_DOSAGE,
                    KEY_DOSAGE_UNIT, KEY_QUANTITY, KEY_QUANTITY_UNIT, KEY_PERIODICITY, KEY_PERIODICITY_UNIT,
                    KEY_START_DATE, KEY_END_DATE, KEY_DISEASE_ID, KEY_RECOMMENDATION_ID}, KEY_ID + "=?",

                    new String[]{String.valueOf(id)}, null, null, null, null);
            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst();

                item = new Medication(
                        cursor.getInt(0), // KEY_ID
                        Integer.parseInt(cursor.getString(1)), // KEY_USER_ID
                        Integer.parseInt(cursor.getString(2)), // KEY_MEDICATION_ID
                        Integer.parseInt(cursor.getString(3)), // KEY_DOSAGE
                        Integer.parseInt(cursor.getString(4)), // KEY_DOSAGE_UNIT
                        Integer.parseInt(cursor.getString(5)), // KEY_QUANTITY
                        Integer.parseInt(cursor.getString(6)), // KEY_QUANTITY_UNIT
                        Integer.parseInt(cursor.getString(7)), // KEY_PERIODICITY
                        Integer.parseInt(cursor.getString(8)), // KEY_PERIODICITY_UNIT
                        CommonFunctions.getDateFromString(CONTEXT, cursor.getString(9), StaticVariables.dateFormat), // KEY_START_DATE
                        CommonFunctions.getDateFromString(CONTEXT, cursor.getString(10), StaticVariables.dateFormat), // KEY_END_DATE
                        Integer.parseInt(cursor.getString(11)), // KEY_DISEASE_ID
                        Integer.parseInt(cursor.getString(12))); // KEY_RECOMMENDATION_ID
            }
        } catch (NullPointerException ex) {
            Log.e("SQLException", "NullPointerException in Medication_DBHandler.getItem " + String.valueOf(ex.getMessage()));
            ex.printStackTrace();
        } catch (CursorIndexOutOfBoundsException e) {
            Log.e("SQLException", "CursorIndexOutOfBoundsException in Medication_DBHandler.getUserByEmail " + String.valueOf(e.getMessage()));
            e.printStackTrace();
        } catch (Exception ex) {
            Log.e("SQLException", "SQLException in Medication_DBHandler.getItem " + String.valueOf(ex.getMessage()));
            ex.printStackTrace();
        } finally {
            db.close(); // Closing database connection
        }
        return item;
    }

    // Getting All items
    public List<Medication> getAllItems() {
        List<Medication> medicationList = new ArrayList<Medication>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Medication item = new Medication();
                item.setId(Integer.parseInt(cursor.getString(0))); // KEY_ID
                item.setUserId(Integer.parseInt(cursor.getString(1))); // KEY_USER_ID
                item.setMedicationId(Integer.parseInt(cursor.getString(2))); // KEY_MEDICATION_ID
                item.setDosage(Double.parseDouble(cursor.getString(3))); // KEY_DOSAGE
                item.setDosageUnit(Integer.parseInt(cursor.getString(4))); // KEY_DOSAGE_UNIT
                item.setQuantity(Integer.parseInt(cursor.getString(5))); // KEY_QUANTITY
                item.setQuantityUnit(Integer.parseInt(cursor.getString(6))); // KEY_QUANTITY_UNIT
                item.setPeriodicity(Integer.parseInt(cursor.getString(7))); // KEY_PERIODICITY
                item.setPeriodicityUnit(Integer.parseInt(cursor.getString(8))); // KEY_PERIODICITY_UNIT
                item.setStartDate(CommonFunctions.getDateFromString(CONTEXT, cursor.getString(9), StaticVariables.dateFormat)); // KEY_START_DATE
                item.setEndDate(CommonFunctions.getDateFromString(CONTEXT, cursor.getString(10), StaticVariables.dateFormat)); // KEY_END_DATE
                item.setDiseaseId(Integer.parseInt(cursor.getString(11))); // KEY_DISEASE_ID
                item.setRecommendationId(Integer.parseInt(cursor.getString(12))); // KEY_RECOMMENDATION_ID

                // Adding contact to list
                medicationList.add(item);
            } while (cursor.moveToNext());
        }

        // return items list
        return medicationList;
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
            Log.e("SQLException", "NullPointerException in Medication_DBHandler.getItem " + String.valueOf(ex.getMessage()));
            ex.printStackTrace();
        } catch (Exception e) {
            Log.e("SQLException", "Exception in Medication_DBHandler.getItemsCount() " + String.valueOf(e.getMessage()));
            e.printStackTrace();
        } finally {
            db.close();
        }
        return count;
    }

    // Updating single item
    public int updateItem(Medication item) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, item.getId());
        values.put(KEY_USER_ID, item.getUserId()); // KEY_USER_ID
        values.put(KEY_MEDICATION_ID, item.getMedicationId()); // KEY_MEDICATION_ID
        values.put(KEY_DOSAGE, item.getDosage()); // KEY_DOSAGE
        values.put(KEY_DOSAGE_UNIT, item.getDosageUnit()); // KEY_DOSAGE_UNIT
        values.put(KEY_QUANTITY, item.getQuantity()); // KEY_QUANTITY
        values.put(KEY_QUANTITY_UNIT, item.getQuantityUnit()); // KEY_QUANTITY_UNIT
        values.put(KEY_PERIODICITY, item.getPeriodicity()); // KEY_PERIODICITY
        values.put(KEY_PERIODICITY_UNIT, item.getPeriodicityUnit()); // KEY_PERIODICITY_UNIT
        values.put(KEY_START_DATE, CommonFunctions.getStringFromDate(CONTEXT, item.getStartDate(), StaticVariables.dateFormat)); // KEY_START_DATE
        values.put(KEY_END_DATE, CommonFunctions.getStringFromDate(CONTEXT, item.getEndDate(), StaticVariables.dateFormat)); // KEY_END_DATE
        values.put(KEY_DISEASE_ID, item.getDiseaseId()); // KEY_DISEASE_ID
        values.put(KEY_RECOMMENDATION_ID, item.getRecommendationId()); // KEY_RECOMMENDATION_ID

        // updating row
        return db.update(TABLE_NAME, values, KEY_ID + " = ?",
                new String[]{String.valueOf(item.getId())});
    }

    // Deleting single item
    public void deleteItem(Medication item) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_ID + " = ?",
                new String[]{String.valueOf(item.getId())});
        db.close();
    }
}
