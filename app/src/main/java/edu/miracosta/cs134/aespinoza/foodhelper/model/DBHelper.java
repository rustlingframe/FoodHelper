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

public class DBHelper extends SQLiteOpenHelper {

    private Context mContext;

    //TASK: DEFINE THE DATABASE VERSION AND NAME  (DATABASE CONTAINS MULTIPLE TABLES)
    static final String DATABASE_NAME = "FoodResource";
    private static final int DATABASE_VERSION = 1;

    //TASK: DEFINE THE FIELDS (COLUMN NAMES) FOR THE CAFFEINE LOCATIONS TABLE
    private static final String FOODRESOURCE_TABLE = "foodresource";
    public static final String FIELD_ORGANIZATION_NAME = "organizationName";
    private static final String FOODRESOURCE_KEY_FIELD_ID = "id";
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

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        String createQuery = "CREATE TABLE " + FOODRESOURCE_TABLE + "("
                + FIELD_ORGANIZATION_NAME+ " TEXT, "
                + FOODRESOURCE_KEY_FIELD_ID + " INTEGER PRIMARY KEY, "
                + FIELD_NAME + " TEXT, "
                + FIELD_ADDRESS + " TEXT,"
                + FIELD_CITY + " TEXT,"
                + FIELD_STATE + " TEXT,"
                + FIELD_ZIP_CODE + " TEXT,"
                + FIELD_PHONE + " TEXT,"
                + FIELD_LAT + " REAL,"
                + FIELD_LONG + " REAL,"
                + FIELD_DESCRIPTION + " TEXT,"
                + FIELD_IS_DISCOUNTED+" BIT,"
                +FIELD_IS_FREE+" BIT"
                + ")";
        database.execSQL(createQuery);

    }

    @Override
    public void onUpgrade(SQLiteDatabase database,
                          int oldVersion,
                          int newVersion) {
        database.execSQL("DROP TABLE IF EXISTS " + FOODRESOURCE_TABLE);

        onCreate(database);
    }

    //********** LOCATIONS TABLE OPERATIONS:  ADD, GETALL, DELETE

    public void addPokemon(FoodResource foodResource) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(FIELD_ORGANIZATION_NAME, foodResource.getOrganizationName());
        values.put(FOODRESOURCE_KEY_FIELD_ID,foodResource.getLocation().getId());
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
        // CLOSE THE DATABASE CONNECTION
        db.close();
    }

    private String arrayToCSV(String[] array)
    {
        String csv = "";
        for (int i = 0; i < array.length; i++)
            csv += ((i == array.length-1) ? array[i] : array[i] + ",");
        return csv;
    }

    private String[] csvToArray(String csv)
    {
        return csv.split(",");
    }

    public List<FoodResource> getAllFoodResource() {
        List<FoodResource> foodResourceList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                FOODRESOURCE_TABLE,
                new String[]{FIELD_ORGANIZATION_NAME,
                        FOODRESOURCE_KEY_FIELD_ID,
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
                 pokemon =
                        new Pokemon(cursor.getLong(0),
                                cursor.getString(1),
                                Uri.parse(cursor.getString(2)),
                                csvToArray(cursor.getString(3)),
                                cursor.getString(4),
                                cursor.getString(5),
                                cursor.getInt(6),
                                cursor.getString(7),
                                cursor.getDouble(8),
                                cursor.getDouble(9),
                                cursor.getString(10),
                                csvToArray(cursor.getString(11)));

                pokemonList.add(pokemon);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return pokemonList;
    }

    public void deletePokemon(Pokemon pokemon) {
        SQLiteDatabase db = this.getWritableDatabase();

        // DELETE THE TABLE ROW
        db.delete(POKEMON_TABLE, POKEMON_KEY_FIELD_ID + " = ?",
                new String[]{String.valueOf(pokemon.getId())});
        db.close();
    }

    public void deleteAllPokemon() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(POKEMON_TABLE, null, null);
        db.close();
    }

    public Pokemon getPokemon(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                POKEMON_TABLE,
                new String[]{POKEMON_KEY_FIELD_ID,
                        FIELD_NAME,
                        FIELD_IMG_URI,
                        FIELD_TYPE,
                        FIELD_HEIGHT,
                        FIELD_WEIGHT,
                        FIELD_CANDY_COUNT,
                        FIELD_EGG,
                        FIELD_SPAWN_CHANCE,
                        FIELD_AVERAGE_SPAWNS,
                        FIELD_SPAWN_TIME,
                        FIELD_WEAKNESSES},
                POKEMON_KEY_FIELD_ID + "=?",
                new String[]{String.valueOf(id)},
                null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        Pokemon pokemon =
                new Pokemon(cursor.getLong(0),
                        cursor.getString(1),
                        Uri.parse(cursor.getString(2)),
                        csvToArray(cursor.getString(3)),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getInt(6),
                        cursor.getString(7),
                        cursor.getDouble(8),
                        cursor.getDouble(9),
                        cursor.getString(10),
                        csvToArray(cursor.getString(11)));
        cursor.close();
        db.close();
        return pokemon;
    }
}