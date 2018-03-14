package com.example.shymacbook.foodtracker.data;

import android.provider.BaseColumns;

/**
 * Created by shymacbook on 3/13/18.
 */

public class MealListContract {
    public static final class MealListEntry implements BaseColumns {
        public static final String TABLE_NAME = "mealList";
        public static final String COLUMN_MEAL_TITLE = "mealName";
        public static final String COLUMN_MEAL_NOTES = "mealNotes";
        public static final String COLUMN_TIMESTAMP = "timestamp";
    }
}
