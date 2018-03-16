package com.example.shymacbook.foodtracker.data;

import android.content.ContentValues;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shymacbook on 3/13/18.
 */

public class TestUtil {
    public static void insertFakeData(SQLiteDatabase db){
        if(db == null){
            return;
        }
        //create a list of fake guests
        List<ContentValues> list = new ArrayList<ContentValues>();

        ContentValues cv = new ContentValues();
        cv.put(MealListContract.MealListEntry.COLUMN_MEAL_TITLE, "Cheese Burger");
        cv.put(MealListContract.MealListEntry.COLUMN_MEAL_NOTES, "beef, cheese, bread");
        list.add(cv);

        cv = new ContentValues();
        cv.put(MealListContract.MealListEntry.COLUMN_MEAL_TITLE, "Beef Taco");
        cv.put(MealListContract.MealListEntry.COLUMN_MEAL_NOTES, "beef, tortlla, cheese, beans");
        list.add(cv);

        cv = new ContentValues();
        cv.put(MealListContract.MealListEntry.COLUMN_MEAL_TITLE, "Orange Juice");
        cv.put(MealListContract.MealListEntry.COLUMN_MEAL_NOTES, "orange, water");
        list.add(cv);

        cv = new ContentValues();
        cv.put(MealListContract.MealListEntry.COLUMN_MEAL_TITLE, "Lemonade");
        cv.put(MealListContract.MealListEntry.COLUMN_MEAL_NOTES, "lemon, water, sugar");
        list.add(cv);

        cv = new ContentValues();
        cv.put(MealListContract.MealListEntry.COLUMN_MEAL_TITLE, "Spaghetti");
        cv.put(MealListContract.MealListEntry.COLUMN_MEAL_NOTES, "tomato, pasta noodles");
        list.add(cv);

        cv = new ContentValues();
        cv.put(MealListContract.MealListEntry.COLUMN_MEAL_TITLE, "Top Ramen");
        cv.put(MealListContract.MealListEntry.COLUMN_MEAL_NOTES, "dry noodles, flavor powder, water");
        list.add(cv);

        cv = new ContentValues();
        cv.put(MealListContract.MealListEntry.COLUMN_MEAL_TITLE, "Hot Pocket");
        cv.put(MealListContract.MealListEntry.COLUMN_MEAL_NOTES, "Garbage, hot lava");
        list.add(cv);

        cv = new ContentValues();
        cv.put(MealListContract.MealListEntry.COLUMN_MEAL_TITLE, "Ice Cream");
        cv.put(MealListContract.MealListEntry.COLUMN_MEAL_NOTES, "milk, fat, eggs, sugar");
        list.add(cv);

        cv = new ContentValues();
        cv.put(MealListContract.MealListEntry.COLUMN_MEAL_TITLE, "Jello");
        cv.put(MealListContract.MealListEntry.COLUMN_MEAL_NOTES, "sugar, water");
        list.add(cv);

        cv = new ContentValues();
        cv.put(MealListContract.MealListEntry.COLUMN_MEAL_TITLE, "Croissant");
        cv.put(MealListContract.MealListEntry.COLUMN_MEAL_NOTES, "Bread, butter");
        list.add(cv);

        //insert all guests in one transaction
        try
        {
            db.beginTransaction();
            //clear the table first
            db.delete (MealListContract.MealListEntry.TABLE_NAME,null,null);
            //go through the list and add one by one
            for(ContentValues c:list){
                db.insert(MealListContract.MealListEntry.TABLE_NAME, null, c);
            }
            db.setTransactionSuccessful();
        }
        catch (SQLException e) {
            //too bad :(
        }
        finally
        {
            db.endTransaction();
        }

    }
}
