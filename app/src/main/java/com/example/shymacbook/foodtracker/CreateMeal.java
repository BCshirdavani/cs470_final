package com.example.shymacbook.foodtracker;

import android.database.sqlite.SQLiteDatabase;
import com.example.shymacbook.foodtracker.data.MealListDbHelper;
import com.example.shymacbook.foodtracker.data.TestUtil;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

public class CreateMeal extends AppCompatActivity {
    // Create a local field SQLiteDatabase called mDb
    private SQLiteDatabase mDb;
    // COMPLETED (1) Create local EditText fields for mNewGuestNameEditText and mNewPartySizeEditText
    private EditText mMealName_eText;
    private EditText mMealNotes_eText;
    // COMPLETED (13) Create a constant string LOG_TAG that is equal to the class.getSimpleName()
    // ?: I don't know what this is for...
    private final static String LOG_TAG = CreateMeal.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_meal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // COMPLETED (2) Set the Edit texts to the corresponding views using findViewById
        mMealName_eText = (EditText) this.findViewById(R.id.meal_name_editText);
        mMealNotes_eText = (EditText) this.findViewById(R.id.meal_notes_editText);

        // Create a WaitlistDbHelper instance, pass "this" to the constructor
        // Create a DB helper (this will create the DB if run for the first time)
        MealListDbHelper dbHelper = new MealListDbHelper(this);

        // COMPLETED (3) Get a writable database reference using getWritableDatabase and store it in mDb
        // Keep a reference to the mDb until paused or killed. Get a writable database
        // because you will be adding restaurant customers
        mDb = dbHelper.getWritableDatabase();

        // COMPLETED (4) call insertFakeData in TestUtil and pass the database reference mDb
        //Fill the database with fake data
        TestUtil.insertFakeData(mDb);

        // TODO: finish db helper from here..compare with MainActivity from DataBaseStorage project


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void createThisMeal(View view) {
        // TODO: add new meal to database, and recycleView

    }
}
