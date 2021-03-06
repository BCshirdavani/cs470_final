package com.example.shymacbook.foodtracker.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.shymacbook.foodtracker.data.MealListContract.*;

/**
 * Created by shymacbook on 3/13/18.
 */

public class MealListDbHelper extends SQLiteOpenHelper {

    // COMPLETED (2) Create a static final String called DATABASE_NAME and set it to "waitlist.db"
    // The database name
    private static final String DATABASE_NAME = "MealList.db";

    // COMPLETED (3) Create a static final int called DATABASE_VERSION and set it to 1
    // If you change the database schema, you must increment the database version
    private static final int DATABASE_VERSION = 4;

    // COMPLETED (4) Create a Constructor that takes a context and calls the parent constructor
    // Constructor
    public MealListDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // TODO: make meal name or title UNIQUE constraint
    @Override
    public void onCreate(SQLiteDatabase db) {
        // COMPLETED (6) Inside, create an String query called SQL_CREATE_WAITLIST_TABLE that will create the table
        // Create a table to hold waitlist data
        final String SQL_CREATE_MEALLIST_TABLE = "CREATE TABLE " + MealListEntry.TABLE_NAME + " (" +
                MealListEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                MealListEntry.COLUMN_MEAL_TITLE + " TEXT NOT NULL UNIQUE, " +
                MealListEntry.COLUMN_MEAL_NOTES + " TEXT NOT NULL, " +
                MealListEntry.COLUMN_PIC_BYTE_ARR + " BLOB, " +
                MealListEntry.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                "); ";
        // TODO: make table for recycler click, adds meal to FAB fragment dialog
//        final String SQL_CREATE_PRECONSUMED_TABLE = "CREATE TABLE " + MealListPreConsumed.TABLE_NAME + " (" +
//                MealListPreConsumed._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
//                MealListPreConsumed.COLUMN_MEAL_TITLE + " TEXT NOT NULL UNIQUE" +
//                "); ";
        // TODO: make function in FAB, that adds meal to consumed table, then clears temp table
        // TODO: make function to put UNIX time into eattime integer var
        final String SQL_CREATE_EAT_TABLE = "CREATE TABLE " + MealsConsumed.TABLE_NAME + " (" +
                MealsConsumed._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                MealsConsumed.COLUMN_MEAL_TITLE + " TEXT NOT NULL UNIQUE, " +
                MealsConsumed.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
                MealsConsumed.COLUMN_EATTIME + " INTEGER, " +
                MealsConsumed.CULUMN_FEELS + " INTEGER, " +
                "FOREIGN KEY (" + MealsConsumed.COLUMN_MEAL_TITLE + ") REFERENCES " +  MealListEntry.TABLE_NAME + "(" + MealListEntry.COLUMN_MEAL_TITLE + ")" +
                "); ";

        // COMPLETED (7) Execute the query by calling execSQL on sqLiteDatabase and pass the string query SQL_CREATE_WAITLIST_TABLE
        db.execSQL(SQL_CREATE_MEALLIST_TABLE);
//        db.execSQL(SQL_CREATE_PRECONSUMED_TABLE);
        db.execSQL(SQL_CREATE_EAT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // COMPLETED (9) Inside, execute a drop table query, and then call onCreate to re-create it
        db.execSQL("DROP TABLE IF EXISTS " + MealListEntry.TABLE_NAME);
//        db.execSQL("DROP TABLE IF EXISTS " + MealListPreConsumed.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + MealsConsumed.TABLE_NAME);
        onCreate(db);
    }
}
