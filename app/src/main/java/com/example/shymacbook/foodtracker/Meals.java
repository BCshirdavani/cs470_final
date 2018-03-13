package com.example.shymacbook.foodtracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Meals extends AppCompatActivity {
    private MealListAdapter mAdapter;

    private Button mCreateMealButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meals);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
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
}
