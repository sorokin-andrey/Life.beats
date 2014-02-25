package classes.DBHandlers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import classes.CommonFunctions;
import classes.DBTables.MedicalLs;
import classes.StaticVariables;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//  usage
//  MedicalLs_DBHandler medicalLsDBHandler = new MedicalLs_DBHandler(this);
//  medicalLsDBHandler.addItem(new MedicalLs(1, "РК-ЛС-5№000989", "ЛС", "Но-Шпа®", "Перерегистрация", new Date(), 5, new Date(), "Хиноин Завод фармацевтических и", "ВЕНГРИЯ", "Лекарственный препарат", "Дротаверин", "A03AD02 Дротаверин", "40 мг", "5 год", "True", "True", "False", "True", "False", "True", "False", "СП РК", "42-4581-08"));
//  medicalLsDBHandler.addItem(new MedicalLs(2, "РК-ЛС-5№000987", "ЛС", "Но-Шпа®", "Перерегистрация", new Date(), 5, new Date(), "Хиноин Завод фармацевтических и", "ВЕНГРИЯ", "Лекарственный препарат", "Дротаверин", "A03AD02 Дротаверин", "40 мг/2мл", "5 год", "True", "True", "True", "False", "False", "False", "False", "СП РК", "42-4582-08"));
//  medicalLsDBHandler.addItem(new MedicalLs(3, "РК-ЛС-5№008963", "ЛС", "Лимонидин", "Перерегистрация", new Date(), 5, new Date(), "Химфарм АО", "КАЗАХСТАН", "Лекарственная субстанция", "Нет данных", "0 Нет данных", "", "3 год", "False", "True", "False", "False", "False", "False", "False", "ФС РК", "42-1259-08"));
//  medicalLsDBHandler.addItem(new MedicalLs(4, "РК-ЛС-5№012525", "ЛС", "Вагинорм С®", "Регистрация", new Date(), 5, new Date(), "Артезан Фарма ГмбХ и Ко. КГ", "ГЕРМАНИЯ", "Лекарственный препарат", "Аскорбиновая кислота", "G01AD03 Аскорбиновая кислота", "250 мг", "3 год", "True", "True", "False", "False", "False", "False", "False", "СП РК", "42-4672-08"));
//  medicalLsDBHandler.addItem(new MedicalLs(5, "РК-ЛС-5№012530", "ЛС", "Витамин В комплекс", "Регистрация", new Date(), 5, new Date(), "СОФАРМА АО", "БОЛГАРИЯ", "Лекарственный препарат", "Нет данных", "A11EX Витамины группы B в комбин", "2 мл", "2 год", "True", "True", "True", "False", "False", "False", "False", "СП РК", "42-4668-08"));

/**
 * Created with IntelliJ IDEA.
 * User: Андрей
 * Date: 31.01.14
 * Time: 13:34
 */
public class MedicalLs_DBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = StaticVariables.databaseName;
    public static final String TABLE_NAME = "medicalls";
    private static Context CONTEXT;

    //region keys name
    private static final String KEY_ID = "iid";
    private static final String KEY_REG_NUMBER = "reg_number";
    private static final String KEY_TYPE = "type";
    private static final String KEY_TRADENAME = "trade_name";
    private static final String KEY_TYPE_VIEW = "type_view";
    private static final String KEY_REG_DATE = "reg_date";
    private static final String KEY_EXPIRE_TERM = "expire_term";
    private static final String KEY_EXPIRE_DATE = "expire_date";
    private static final String KEY_MANUFACTURER = "manufacturer";
    private static final String KEY_COUNTRY = "country";
    private static final String KEY_CLASSIFICATION_LS = "classification_LS";
    private static final String KEY_MNN = "MNN";
    private static final String KEY_ATX_CLASSIFICATION = "ATX_classification";
    private static final String KEY_DOSAGE_CONCENTRATION = "dosage_concentration";
    private static final String KEY_SHELF_LIFE = "shelf_life";
    private static final String KEY_GMP = "GMP";
    private static final String KEY_GENERIC = "generic";
    private static final String KEY_RECIPE = "recipe";
    private static final String KEY_OZHVLS = "OZHVLS";
    private static final String KEY_NPP = "NPP";
    private static final String KEY_BRAND_NAME = "brand_name";
    private static final String KEY_PATENT_NUMBER = "patent_number";
    private static final String KEY_TYPE_ND = "type_ND";
    private static final String KEY_ND_NUMBER = "ND_number";
    //endregion

    public MedicalLs_DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        CONTEXT = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //db.execSQL("DROP TABLE IF EXISTS '" + TABLE_NAME + "'");
        String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" +
                KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                KEY_REG_NUMBER + " VARACHAR(64) NOT NULL," +
                KEY_TYPE + " VARACHAR(32) NOT NULL," +
                KEY_TRADENAME + " VARACHAR(128) NOT NULL," +
                KEY_TYPE_VIEW + " VARACHAR(64) NOT NULL," +
                KEY_REG_DATE + " DATETIME NOT NULL," +
                KEY_EXPIRE_TERM + " INT(3) NOT NULL," +
                KEY_EXPIRE_DATE + " DATETIME NOT NULL," +
                KEY_MANUFACTURER + " VARACHAR(32) NOT NULL," +
                KEY_COUNTRY + " VARACHAR(32) NOT NULL," +
                KEY_CLASSIFICATION_LS + " VARACHAR(32) NOT NULL," +
                KEY_MNN + " VARACHAR(32) NOT NULL," +
                KEY_ATX_CLASSIFICATION + " VARACHAR(32) NOT NULL," +
                KEY_DOSAGE_CONCENTRATION + " VARACHAR(32) NOT NULL," +
                KEY_SHELF_LIFE + " VARACHAR(32) NOT NULL," +
                KEY_GMP + " VARACHAR(32) NOT NULL," +
                KEY_GENERIC + " VARACHAR(32) NOT NULL," +
                KEY_RECIPE + " VARACHAR(32) NOT NULL," +
                KEY_OZHVLS + " VARACHAR(32) NOT NULL," +
                KEY_NPP + " VARACHAR(32) NOT NULL," +
                KEY_BRAND_NAME + " VARACHAR(32) NOT NULL," +
                KEY_PATENT_NUMBER + " VARACHAR(32) NOT NULL," +
                KEY_TYPE_ND + " VARACHAR(32) NOT NULL," +
                KEY_ND_NUMBER + " VARACHAR(32) NOT NULL" +
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
    public void addItem(MedicalLs item) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            ContentValues values = new ContentValues();
            //values.put(KEY_ID, item.getIid());
            values.put(KEY_REG_NUMBER, item.getRegNumber());
            values.put(KEY_TYPE, item.getType());
            values.put(KEY_TRADENAME, item.getTradeName());
            values.put(KEY_TYPE_VIEW, item.getTypeView());
            values.put(KEY_REG_DATE, CommonFunctions.getStringFromDate(CONTEXT, item.getRegDate(), StaticVariables.dateFormat));
            values.put(KEY_EXPIRE_TERM, item.getExpireTerm());
            values.put(KEY_EXPIRE_DATE, CommonFunctions.getStringFromDate(CONTEXT, item.getExpireDate(), StaticVariables.dateFormat));
            values.put(KEY_MANUFACTURER, item.getManufacturer());
            values.put(KEY_COUNTRY, item.getCountry());
            values.put(KEY_CLASSIFICATION_LS, item.getClassificationLS());
            values.put(KEY_MNN, item.getMnn());
            values.put(KEY_ATX_CLASSIFICATION, item.getAtxClassification());
            values.put(KEY_DOSAGE_CONCENTRATION, item.getDosageConcentration());
            values.put(KEY_SHELF_LIFE, item.getShelfLife());
            values.put(KEY_GMP, item.getGmp());
            values.put(KEY_GENERIC, item.getGeneric());
            values.put(KEY_RECIPE, item.getRecipe());
            values.put(KEY_OZHVLS, item.getOzhvls());
            values.put(KEY_NPP, item.getNpp());
            values.put(KEY_BRAND_NAME, item.getBrandName());
            values.put(KEY_PATENT_NUMBER, item.getPatentNumber());
            values.put(KEY_TYPE_ND, item.getTypeNd());
            values.put(KEY_ND_NUMBER, item.getNdNumber());

            // Inserting Row
            db.insertOrThrow(TABLE_NAME, null, values);
        } catch (Exception ex) {
            Log.e("SQLException", "Exception in MedicalLs_DBHandler " + String.valueOf(ex.getMessage()));
            ex.printStackTrace();
            db.close(); // Closing database connection
        } finally {
            db.close(); // Closing database connection
        }
    }

    // Getting single item
    public MedicalLs getItem(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        MedicalLs item = new MedicalLs();
        try {
            Cursor cursor = db.query(TABLE_NAME, new String[]{KEY_ID, KEY_REG_NUMBER, KEY_TYPE, KEY_TRADENAME, KEY_TYPE_VIEW,
                    KEY_REG_DATE, KEY_EXPIRE_TERM, KEY_EXPIRE_DATE, KEY_MANUFACTURER, KEY_COUNTRY, KEY_CLASSIFICATION_LS,
                    KEY_MNN, KEY_ATX_CLASSIFICATION, KEY_DOSAGE_CONCENTRATION, KEY_SHELF_LIFE, KEY_GMP, KEY_GENERIC,
                    KEY_RECIPE, KEY_OZHVLS, KEY_NPP, KEY_BRAND_NAME, KEY_PATENT_NUMBER, KEY_TYPE_ND, KEY_ND_NUMBER}, KEY_ID + "=?",

                    new String[]{String.valueOf(id)}, null, null, null, null);
            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst();

                item = new MedicalLs(
                        cursor.getInt(0), // KEY_ID
                        cursor.getString(1), // KEY_REG_NUMBER
                        cursor.getString(2), // KEY_TYPE
                        cursor.getString(3), // KEY_TRADENAME
                        cursor.getString(4), // KEY_TYPE_VIEW
                        new Date(), // KEY_REG_DATE
                        Integer.parseInt(cursor.getString(6)), // KEY_EXPIRE_TERM
                        new Date(), // KEY_EXPIRE_DATE
                        cursor.getString(8), // KEY_MANUFACTURER
                        cursor.getString(9), // KEY_COUNTRY
                        cursor.getString(10), // KEY_CLASSIFICATION_LS
                        cursor.getString(11), // KEY_MNN
                        cursor.getString(12), // KEY_ATX_CLASSIFICATION
                        cursor.getString(13), // KEY_DOSAGE_CONCENTRATION
                        cursor.getString(14), // KEY_SHELF_LIFE
                        cursor.getString(15), // KEY_GMP
                        cursor.getString(16), // KEY_GENERIC
                        cursor.getString(17), // KEY_RECIPE
                        cursor.getString(18), // KEY_OZHVLS
                        cursor.getString(19), // KEY_NPP
                        cursor.getString(20), // KEY_BRAND_NAME
                        cursor.getString(21), // KEY_PATENT_NUMBER
                        cursor.getString(22), // KEY_TYPE_ND
                        cursor.getString(23)); // KEY_ND_NUMBER
            }
        } catch (NullPointerException ex) {
            Log.e("SQLException", "NullPointerException in MedicalLs_DBHandler.getItem " + String.valueOf(ex.getMessage()));
            ex.printStackTrace();
        } catch (CursorIndexOutOfBoundsException e) {
            Log.e("SQLException", "CursorIndexOutOfBoundsException in MedicalLs_DBHandler.getUserByEmail " + String.valueOf(e.getMessage()));
            e.printStackTrace();
        } catch (Exception ex) {
            Log.e("SQLException", "SQLException in MedicalLs_DBHandler.getItem " + String.valueOf(ex.getMessage()));
            ex.printStackTrace();
        } finally {
            db.close(); // Closing database connection
        }
        return item;
    }

    // Getting single item
    public MedicalLs getItem(String tradeName) {
        MedicalLs item = new MedicalLs();
        List<MedicalLs> list = getAllItems();
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getTradeName().equals(tradeName)) {
                    item = list.get(i);
                    break;
                }
            }
        }
        return item;
    }

    // Getting All items
    public List<MedicalLs> getAllItems() {
        List<MedicalLs> medicalLsList = new ArrayList<MedicalLs>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                MedicalLs item = new MedicalLs();
                item.setIid(Integer.parseInt(cursor.getString(0))); // KEY_ID
                item.setRegNumber(cursor.getString(1)); // KEY_REG_NUMBER
                item.setType(cursor.getString(2)); // KEY_TYPE
                item.setTradeName(cursor.getString(3)); // KEY_TRADENAME
                item.setTypeView(cursor.getString(4)); // KEY_TYPE_VIEW
                item.setRegDate(CommonFunctions.getDateFromString(CONTEXT, cursor.getString(5), StaticVariables.dateFormat)); // KEY_REG_DATE
                item.setExpireTerm(Integer.parseInt(cursor.getString(6))); // KEY_EXPIRE_TERM
                item.setExpireDate(CommonFunctions.getDateFromString(CONTEXT, cursor.getString(7), StaticVariables.dateFormat)); // KEY_EXPIRE_DATE
                item.setManufacturer(cursor.getString(8)); // KEY_MANUFACTURER
                item.setCountry(cursor.getString(9)); // KEY_COUNTRY
                item.setClassificationLS(cursor.getString(10)); // KEY_CLASSIFICATION_LS
                item.setMnn(cursor.getString(11)); // KEY_MNN
                item.setAtxClassification(cursor.getString(12)); // KEY_ATX_CLASSIFICATION
                item.setDosageConcentration(cursor.getString(13)); // KEY_DOSAGE_CONCENTRATION
                item.setShelfLife(cursor.getString(14)); // KEY_SHELF_LIFE
                item.setGmp(cursor.getString(15)); // KEY_GMP
                item.setGeneric(cursor.getString(16)); // KEY_GENERIC
                item.setRecipe(cursor.getString(17)); // KEY_RECIPE
                item.setOzhvls(cursor.getString(18)); // KEY_OZHVLS
                item.setNpp(cursor.getString(19)); // KEY_NPP
                item.setBrandName(cursor.getString(20)); // KEY_BRAND_NAME
                item.setPatentNumber(cursor.getString(21)); // KEY_PATENT_NUMBER
                item.setTypeNd(cursor.getString(22)); // KEY_TYPE_ND
                item.setNdNumber(cursor.getString(23)); // KEY_ND_NUMBER

                // Adding contact to list
                medicalLsList.add(item);
            } while (cursor.moveToNext());
        }

        // return items list
        return medicalLsList;
    }

    // Getting All items title
    public List<String> getAllItemsTradeNameList() {
        List<MedicalLs> medicalLsItemsTradeNameList = getAllItems();
        List<String> list = new ArrayList<String>();
        for (MedicalLs item : medicalLsItemsTradeNameList) {
            list.add(item.getTradeName());
        }
        // return items list
        return list;
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
            Log.e("SQLException", "NullPointerException in MedicalLs_DBHandler.getItem " + String.valueOf(ex.getMessage()));
            ex.printStackTrace();
        } catch (Exception e) {
            Log.e("SQLException", "Exception in MedicalLs_DBHandler.getItemsCount() " + String.valueOf(e.getMessage()));
            e.printStackTrace();
        } finally {
            db.close();
        }
        return count;
    }

    // Updating single item
    public int updateItem(MedicalLs item) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_ID, item.getIid()); // KEY_ID
        values.put(KEY_REG_NUMBER, item.getRegNumber()); // KEY_REG_NUMBER
        values.put(KEY_TYPE, item.getType()); // KEY_TYPE
        values.put(KEY_TRADENAME, item.getTradeName()); // KEY_TRADENAME
        values.put(KEY_TYPE_VIEW, item.getTypeView()); // KEY_TYPE_VIEW
        values.put(KEY_REG_DATE, "2013-04-24"); // KEY_REG_DATE
        values.put(KEY_EXPIRE_TERM, item.getExpireTerm()); // KEY_EXPIRE_TERM
        values.put(KEY_EXPIRE_DATE, "2013-04-24"); // KEY_EXPIRE_DATE
        values.put(KEY_MANUFACTURER, item.getManufacturer()); // KEY_MANUFACTURER
        values.put(KEY_COUNTRY, item.getCountry()); // KEY_COUNTRY
        values.put(KEY_CLASSIFICATION_LS, item.getClassificationLS()); // KEY_CLASSIFICATION_LS
        values.put(KEY_MNN, item.getMnn()); // KEY_MNN
        values.put(KEY_ATX_CLASSIFICATION, item.getAtxClassification()); // KEY_ATX_CLASSIFICATION
        values.put(KEY_DOSAGE_CONCENTRATION, item.getDosageConcentration()); // KEY_DOSAGE_CONCENTRATION
        values.put(KEY_SHELF_LIFE, item.getShelfLife()); // KEY_SHELF_LIFE
        values.put(KEY_GMP, item.getGmp()); // KEY_GMP
        values.put(KEY_GENERIC, item.getGeneric()); // KEY_GENERIC
        values.put(KEY_RECIPE, item.getRecipe()); // KEY_RECIPE
        values.put(KEY_OZHVLS, item.getOzhvls()); // KEY_OZHVLS
        values.put(KEY_NPP, item.getNpp()); // KEY_NPP
        values.put(KEY_BRAND_NAME, item.getBrandName()); // KEY_BRAND_NAME
        values.put(KEY_PATENT_NUMBER, item.getPatentNumber()); // KEY_PATENT_NUMBER
        values.put(KEY_TYPE_ND, item.getTypeNd()); // KEY_TYPE_ND
        values.put(KEY_ND_NUMBER, item.getNdNumber()); // KEY_ND_NUMBER

        // updating row
        return db.update(TABLE_NAME, values, KEY_ID + " = ?",
                new String[]{String.valueOf(item.getIid())});
    }

    // Deleting single item
    public void deleteItem(MedicalLs item) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_ID + " = ?",
                new String[]{String.valueOf(item.getIid())});
        db.close();
    }
}