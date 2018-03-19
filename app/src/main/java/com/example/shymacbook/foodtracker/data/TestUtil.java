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
    private static String tacoBytes;
    private static String burgerBytes;

    public static void insertFakeData(SQLiteDatabase db){
        if(db == null){
            return;
        }
        //create a list of fake guests
        List<ContentValues> list = new ArrayList<ContentValues>();
        tacoBytes = "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";
        burgerBytes = "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";

        ContentValues cv = new ContentValues();
        cv.put(MealListContract.MealListEntry.COLUMN_MEAL_TITLE, "Cheese Burger");
        cv.put(MealListContract.MealListEntry.COLUMN_MEAL_NOTES, "beef, cheese, bread");
        cv.put(MealListContract.MealListEntry.COLUMN_PIC_BYTE_ARR, burgerBytes);

        list.add(cv);

        cv = new ContentValues();
        cv.put(MealListContract.MealListEntry.COLUMN_MEAL_TITLE, "Beef Taco");
        cv.put(MealListContract.MealListEntry.COLUMN_MEAL_NOTES, "beef, tortlla, cheese, beans");
        cv.put(MealListContract.MealListEntry.COLUMN_PIC_BYTE_ARR, tacoBytes);
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