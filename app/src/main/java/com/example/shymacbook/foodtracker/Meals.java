package com.example.shymacbook.foodtracker;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
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

    private final static String LOG_TAG = Meals.class.getSimpleName();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meals);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Recycler View Stuff
        RecyclerView MealListRecyclerView;
        MealListRecyclerView = (RecyclerView) this.findViewById(R.id.meal_recyclerView);
        MealListRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        MealListDbHelper dbHelper = new MealListDbHelper(this);
        mDb = dbHelper.getWritableDatabase();
        TestUtil.insertFakeData(mDb);
        Cursor cursor = getAllGuests();
        mAdapter = new MealListAdapter(this, cursor);
        MealListRecyclerView.setAdapter(mAdapter);

        // COMPLETED (15)
        // TODO: do I need this here? there is no recycleView on this screen, so do I need an adapter here?
//        mAdapter.swapCursor(getAllGuests());


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    // TODO: attempting to refresh data and show new stuff in list here...
    @Override
    protected void onResume() {
        super.onResume();
        Log.d("lifecycle", "onResume: at Meals called");
        // COMPLETED (15)
        // TODO: do I need this here? there is no recycleView on this screen, so do I need an adapter here?
        mAdapter.swapCursor(getAllGuests());
        Log.d("function call", "swapCursor called at onResume Meals act");
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
