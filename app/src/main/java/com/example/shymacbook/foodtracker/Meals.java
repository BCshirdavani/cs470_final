package com.example.shymacbook.foodtracker;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.shymacbook.foodtracker.data.MealListContract;
import com.example.shymacbook.foodtracker.data.MealListDbHelper;
import com.example.shymacbook.foodtracker.data.TestUtil;

public class Meals extends AppCompatActivity {
    private MealListAdapter mAdapter;
    private Button mCreateMealButton;
    private SQLiteDatabase mDb;
    private String mNewMealExtra[];
    private String mNewMealName;
    private String mNewMealNote;
    private String mNewMealByteArr;
    private byte[] mNewMealBitMapBytes;

    private final static String LOG_TAG = Meals.class.getSimpleName();

    //sdfhsdlfjsd;klfjsd;f


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("onCreate", "onCreate: Meals.java");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meals);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mNewMealExtra = new String[3];


        // Recycler View Stuff
        RecyclerView MealListRecyclerView;
        MealListRecyclerView = (RecyclerView) this.findViewById(R.id.meal_recyclerView);
        MealListRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        MealListDbHelper dbHelper = new MealListDbHelper(this);
        mDb = dbHelper.getWritableDatabase();
        Log.d("onCreate", "onCreate: Meals.java about to call inserFakeData()");
        // TODO: fix bitmap to byte[] to bitmap conversions brefore inserting fake data...
//        TestUtil.insertFakeData(mDb);
        Log.d("onCreate", "onCreate: about to make cursor = getAllGuests()");
        Cursor cursor = getAllGuests();
        mAdapter = new MealListAdapter(this, cursor);
        MealListRecyclerView.setAdapter(mAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    // DB updates by taking the putExtra from the create meal activity
    //  then running the db insert statements here, with that EXTRA
    @Override
    protected void onResume() {
        super.onResume();
        Log.d("lifecycle", "onResume: at Meals called");
        Intent intentThatStartedThisAct = getIntent();
        if (intentThatStartedThisAct.hasExtra(Intent.EXTRA_TEXT)) {
            mNewMealExtra = intentThatStartedThisAct.getStringArrayExtra(Intent.EXTRA_TEXT);    // String or String[] ??

            mNewMealName = mNewMealExtra[0];
            mNewMealNote = mNewMealExtra[1];
            mNewMealByteArr = mNewMealExtra[2];
            // TODO: add to database here!
            mNewMealBitMapBytes = intentThatStartedThisAct.getByteArrayExtra(Intent.EXTRA_PROCESS_TEXT);
            addNewGuest(mNewMealName, mNewMealNote, mNewMealBitMapBytes);
            mAdapter.swapCursor(getAllGuests());
        }
        Log.d("function call", "swapCursor called at onResume Meals act");
    }

    private Long addNewGuest(String name, String note, byte[] byteArr) {
        ContentValues cv = new ContentValues();
        cv.put(MealListContract.MealListEntry.COLUMN_MEAL_TITLE, name);
        cv.put(MealListContract.MealListEntry.COLUMN_MEAL_NOTES, note);
        cv.put(MealListContract.MealListEntry.COLUMN_PIC_BYTE_ARR, byteArr);
        return mDb.insert(MealListContract.MealListEntry.TABLE_NAME, null, cv);
    }

    public void showCreateMeals(View view) {
        Log.d("button click", "showCreateMeals: Create Meals button clicked from main.");
        goToCreateMeal();
    }

    private void goToCreateMeal() {
        Log.d("function", "launching goToCreateMeal()");
        Toast.makeText(getApplicationContext(), "going to create meals", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, CreateMeal.class);
        startActivity(intent);
    }

    private Cursor getAllGuests() {
        // COMPLETED (6) Inside, call query on mDb passing in the table name and projection String [] order by COLUMN_TIMESTAMP
        return mDb.query(
                 MealListContract.MealListEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                MealListContract.MealListEntry.COLUMN_TIMESTAMP
        );
    }
}
