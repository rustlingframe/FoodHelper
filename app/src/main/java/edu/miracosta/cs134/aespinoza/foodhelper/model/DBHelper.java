package edu.miracosta.cs134.aespinoza.foodhelper.model;


import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * DBHelper class used to keep track of FoodResources in a neat table.
 * @Author Alvaro Espinoza Merida & Alfredo Hernandez
 * Project - Final Project CS134
 */
public class DBHelper extends SQLiteOpenHelper {

    private Context mContext;

    //TASK: DEFINE THE DATABASE VERSION AND NAME  (DATABASE CONTAINS MULTIPLE TABLES)
    static final String DATABASE_NAME = "foodresource";
    private static final int DATABASE_VERSION = 1;

    //TASK: DEFINE THE FIELDS (COLUMN NAMES) FOR THE CAFFEINE LOCATIONS TABLE
    private static final String FOODRESOURCE_TABLE = "foodresource";
    private static final String FOODRESOURCE_KEY_FIELD_ID = "_id";
    private static final String FIELD_ORGANIZATION_NAME = "organizationName";
    private static final String FIELD_NAME = "name";
    private static final String FIELD_ADDRESS = "address";
    private static final String FIELD_CITY= "city";
    private static final String FIELD_STATE = "state";
    private static final String FIELD_ZIP_CODE = "zipcode";
    private static final String FIELD_PHONE = "phone";
    private static final String FIELD_LAT = "latitude";
    private static final String FIELD_LONG = "longitude";
    private static final String FIELD_DESCRIPTION = "description";
    private static final String FIELD_IS_DISCOUNTED = "isDiscounted";
    private static final String FIELD_IS_FREE = "isFree";

    /**
     * Default constructor
     * @param context current context
     */
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mContext = context;
    }

    /**
     * Creates a query upon creation of DB
     * @param database dataBase to fill up
     */
    @Override
    public void onCreate(SQLiteDatabase database) {
        String createQuery = "CREATE TABLE " + FOODRESOURCE_TABLE + "("
                + FOODRESOURCE_KEY_FIELD_ID + " INTEGER PRIMARY KEY, "
                + FIELD_ORGANIZATION_NAME+ " TEXT, "
                + FIELD_NAME + " TEXT, "
                + FIELD_ADDRESS + " TEXT,"
                + FIELD_CITY + " TEXT,"
                + FIELD_STATE + " TEXT,"
                + FIELD_ZIP_CODE + " TEXT,"
                + FIELD_PHONE + " TEXT,"
                + FIELD_LAT + " REAL,"
                + FIELD_LONG + " REAL,"
                + FIELD_DESCRIPTION + " TEXT,"
                + FIELD_IS_DISCOUNTED+" INTEGER,"
                +FIELD_IS_FREE+" INTEGER"
                + ")";
        database.execSQL(createQuery);

    }

    /**
     * Used to upgrade the DB's version
     * @param database given database
     * @param oldVersion old version
     * @param newVersion new version
     */
    @Override
    public void onUpgrade(SQLiteDatabase database,
                          int oldVersion,
                          int newVersion) {
        database.execSQL("DROP TABLE IF EXISTS " + FOODRESOURCE_TABLE);

        onCreate(database);
    }

    //********** LOCATIONS TABLE OPERATIONS:  ADD, GETALL, DELETE

    /**
     * Adds a FoodResource to the DataBase
     * @param foodResource FoodResource to be added
     */
    public void addFoodResource(FoodResource foodResource) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FOODRESOURCE_KEY_FIELD_ID,foodResource.getLocation().getId());
        values.put(FIELD_ORGANIZATION_NAME, foodResource.getOrganizationName());


        values.put(FIELD_NAME, foodResource.getLocation().getName());
        values.put(FIELD_ADDRESS, foodResource.getLocation().getAddress());

        values.put(FIELD_CITY, foodResource.getLocation().getCity());
        values.put(FIELD_STATE, foodResource.getLocation().getState());
        values.put(FIELD_ZIP_CODE, foodResource.getLocation().getZipCode());

        values.put(FIELD_PHONE, foodResource.getLocation().getPhone());
        values.put(FIELD_LAT, foodResource.getLocation().getLatitude());
        values.put(FIELD_LONG, foodResource.getLocation().getLongitude());

        values.put(FIELD_DESCRIPTION, foodResource.getEventDescription());
        values.put(FIELD_IS_DISCOUNTED, foodResource.getDiscounted());
        values.put(FIELD_IS_FREE, foodResource.getFree());

        long id = db.insert(FOODRESOURCE_TABLE, null, values);
        foodResource.getLocation().setId(id);
        foodResource.setId(id) ;
        // CLOSE THE DATABASE CONNECTION
        db.close();
    }

    /**
     * Returns all of the FoodResources in the database
     * @return all FoodResources
     */
    public List<FoodResource> getAllFoodResource() {
        List<FoodResource> foodResourceList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                FOODRESOURCE_TABLE,
                new String[]{FOODRESOURCE_KEY_FIELD_ID,
                        FIELD_ORGANIZATION_NAME,
                        FIELD_NAME,

                        FIELD_ADDRESS,
                        FIELD_CITY,
                        FIELD_STATE,

                        FIELD_ZIP_CODE,
                        FIELD_PHONE,
                        FIELD_LAT,

                        FIELD_LONG,
                        FIELD_DESCRIPTION,
                        FIELD_IS_DISCOUNTED,

                        FIELD_IS_FREE},
                null,
                null,
                null, null, null, null);

        //COLLECT EACH ROW IN THE TABLE
        if (cursor.moveToFirst()) {
            do {
                 FoodResource foodResource =
                         new FoodResource(cursor.getLong(0),
                                            cursor.getString(1),
                                            cursor.getString(2),
                                            cursor.getString(3),
                                            cursor.getString(4),
                                            cursor.getString(5),
                                            cursor.getString(6),
                                            cursor.getString(7),
                                            cursor.getDouble(8),
                                            cursor.getDouble(9),
                                            cursor.getString(10),
                                            cursor.getInt(11),
                                            cursor.getInt(12));

                foodResourceList.add(foodResource);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return foodResourceList;
    }

    /**
     * Deletes a given FoodResource from the Database
     * @param foodResource to be deleted
     */
    public void deleteFoodResource(FoodResource foodResource) {
        SQLiteDatabase db = this.getWritableDatabase();

        // DELETE THE TABLE ROW
        db.delete(FOODRESOURCE_TABLE, FOODRESOURCE_KEY_FIELD_ID + " = ?",
                new String[]{String.valueOf(foodResource.getLocation().getId())});
        db.close();
    }

    /**
     * Deletes all FoodResources in the Database
     */
    public void deleteAllFoodResource() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(FOODRESOURCE_TABLE, null, null);
        db.close();
    }

    /**
     * Returns a FoodResource based on it's id
     * @param id of FoodResource
     * @return the deleted FoodResource
     */
    public FoodResource getFoodResource(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
                Cursor cursor = db.query(
                        FOODRESOURCE_TABLE,
                        new String[]{FOODRESOURCE_KEY_FIELD_ID,
                                FIELD_ORGANIZATION_NAME,
                                FIELD_NAME,
                                FIELD_ADDRESS,
                                FIELD_CITY,
                                FIELD_STATE,
                                FIELD_ZIP_CODE,
                                FIELD_PHONE,
                                FIELD_LAT,
                                FIELD_LONG,
                                FIELD_DESCRIPTION,
                                FIELD_IS_DISCOUNTED,
                                FIELD_IS_FREE},
                        FOODRESOURCE_KEY_FIELD_ID+"=?",
                        new String[]{String.valueOf(id)},
                        null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        FoodResource foodResource =
                new FoodResource(cursor.getLong(0),
                        cursor.getString(1),

                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getString(7),
                        cursor.getDouble(8),
                        cursor.getDouble(9),
                        cursor.getString(10),
                        cursor.getInt(11),
                        cursor.getInt(12));
        cursor.close();
        db.close();
        return foodResource;
    }
}