package com.example.shymacbook.foodtracker;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.shymacbook.foodtracker.data.MealListContract;
import com.example.shymacbook.foodtracker.data.MealListDbHelper;
import com.example.shymacbook.foodtracker.data.TestUtil;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CreateMeal extends AppCompatActivity {
//    private MealListAdapter mAdapter;
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

        // COMPLETED (2)
        mMealName_eText = (EditText) this.findViewById(R.id.meal_name_editText);
        mMealNotes_eText = (EditText) this.findViewById(R.id.meal_notes_editText);

        // Create a WaitlistDbHelper instance
        // Create a DB helper
        MealListDbHelper dbHelper = new MealListDbHelper(this);

        // COMPLETED (3)
        mDb = dbHelper.getWritableDatabase();

        // COMPLETED (4) call insertFakeData in TestUtil and pass the database reference mDb
        //Fill the database with fake data
//        TestUtil.insertFakeData(mDb);

        // COMPLETED (7)
//        Cursor cursor = getAllGuests();

        // COMPLETED (12)
//        mAdapter = new MealListAdapter(this, cursor);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }



    // TODO: figure out why new meals in db do not refresh in the recycleView screen
    public void createThisMeal(View view) {
        Log.d("function execution", "createThisMeal(view) executing");
        //First thing, check if any of the EditTexts are empty, return if so
        if (mMealName_eText.getText().length() == 0 || mMealNotes_eText.getText().length() == 0) {
            return;
        }
        // COMPLETED (14)
        addNewMeal(mMealName_eText.getText().toString(), mMealNotes_eText.getText().toString());

        // COMPLETED (15)
        // TODO: do I need this here? there is no recycleView on this screen, so do I need an adapter here?
//        mAdapter.swapCursor(getAllGuests());

        // COMPLETED (16)
        //clear UI text fields
        mMealNotes_eText.clearFocus();
        mMealNotes_eText.getText().clear();
        mMealName_eText.getText().clear();
    }

    // TODO: figure out why new meals in db do not refresh in the recycleView screen
    private Long addNewMeal(String name, String notes) {
        Log.d("function execution", "addNewMeal(name, notes) executing");
        // COMPLETED (5) Inside, create a ContentValues instance to pass the values onto the insert query
        ContentValues cv = new ContentValues();
        // COMPLETED (6) call put to insert the name value with the key COLUMN_GUEST_NAME
        cv.put(MealListContract.MealListEntry.COLUMN_MEAL_TITLE, name);
        // COMPLETED (7) call put to insert the party size value with the key COLUMN_PARTY_SIZE
        cv.put(MealListContract.MealListEntry.COLUMN_MEAL_NOTES, notes);
        // COMPLETED (8) call insert to run an insert query on TABLE_NAME with the ContentValues created
        Log.d("to db preview", "title: " + name);
        Log.d("to db preview", "note: " + notes);
        // TODO: this manual insert statement should actually insert a row into the db...
        String sqlStatement = "INSERT INTO " + MealListContract.MealListEntry.TABLE_NAME + " (" + MealListContract.MealListEntry.COLUMN_MEAL_TITLE + ", " + MealListContract.MealListEntry.COLUMN_MEAL_NOTES + ") Values (" + name + ", " + notes + ", '')";
        mDb.execSQL(sqlStatement);
        return mDb.insert(MealListContract.MealListEntry.TABLE_NAME, null, cv);
    }

//    private Cursor getAllGuests() {
//        // COMPLETED (6) Inside, call query on mDb passing in the table name and projection String [] order by COLUMN_TIMESTAMP
//        return mDb.query(
//                MealListContract.MealListEntry.TABLE_NAME,
//                null,
//                null,
//                null,
//                null,
//                null,
//                MealListContract.MealListEntry.COLUMN_TIMESTAMP
//        );
//    }

    // TODO: figure out why new meals in db do not refresh in the recycleView screen
    public void createThisMeal_buttonClick(View view) {
        Toast.makeText(getApplicationContext(), "calling createThisMeal(view)", Toast.LENGTH_SHORT).show();
        Log.d("button click", "createThisMeal_buttonClick function called");
        createThisMeal(view);
        Intent intent = new Intent(this, Meals.class);
        startActivity(intent);
    }
}
