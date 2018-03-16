package com.example.shymacbook.foodtracker;

import android.content.Context;
import android.content.Intent;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateMeal extends AppCompatActivity {
    private String arrayExtra[];
    private EditText mMealName_eText;
    private EditText mMealNotes_eText;
    private Button mAddMeal_Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_meal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mMealName_eText = (EditText) this.findViewById(R.id.meal_name_editText);
        mMealNotes_eText = (EditText) this.findViewById(R.id.meal_notes_editText);
        mAddMeal_Button = (Button) this.findViewById(R.id.createThisMeal_button);
        arrayExtra = new String[3];


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        mAddMeal_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("function execution: ", "about to make arrayExtra");
                arrayExtra[0] = (String) mMealName_eText.getText().toString();
                arrayExtra[1] = (String) mMealNotes_eText.getText().toString();
                arrayExtra[2] = "fromCreateMeal";
                Log.d("function execution: ", "arrayExtra was made...");
                Context context = CreateMeal.this;
                Class destinationAct = Meals.class;
                Intent intent = new Intent(context, destinationAct);
                Log.d("function execution: ", "about to putExtra");
                intent.putExtra(Intent.EXTRA_TEXT, arrayExtra);
                Log.d("intent.EXTRA", "createThisMeal_buttonClick: " + arrayExtra[0] + ", " + arrayExtra[1] + ", " + arrayExtra[2]);
                startActivity(intent);
            }
        });
    }

}
