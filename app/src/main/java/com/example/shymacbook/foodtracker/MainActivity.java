package com.example.shymacbook.foodtracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button mMealButton;
    private Button mDataButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mMealButton = (Button)findViewById(R.id.meal_button);
        mDataButton = (Button)findViewById(R.id.data_button);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                //TODO : popup fragment dialog that shows meals, and has consume button
            }
        });
    }




    //===================================================================================
    //                                                              menu crap pre-loaded START
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    //                                                              menu crap pre-loaded END
    //==================================================================================


    //===================================================================================
    //                                                              navigation onClick buttons
    public void showMeals(View view) {
        Log.d("button click", "showMeals: Meals button clicked from main.");
        goToMeal();
    }
    private void goToMeal() {
        Log.d("function", "launching goToMeal()");
        Toast.makeText(getApplicationContext(), "going to meals", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, Meals.class);
        startActivity(intent);
    }


}
