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
        public static final String COLUMN_PIC_BYTE_ARR = "mealPic";
        public static final String COLUMN_TIMESTAMP = "timestamp";
    }

    public static final class MealListPreConsumed implements BaseColumns {
        public static final String TABLE_NAME = "MealListPreConsumed";
        public static final String COLUMN_MEAL_TITLE = "mealName";
    }

    public static final class MealsConsumed implements BaseColumns {
        public static final String TABLE_NAME = "MealListPreConsumed";
        public static final String COLUMN_MEAL_TITLE = "mealName";
        public static final String COLUMN_TIMESTAMP = "timestamp";
        public static final String COLUMN_EATTIME = "eattime";
        public static final String CULUMN_FEELS = "feels";
    }


}
