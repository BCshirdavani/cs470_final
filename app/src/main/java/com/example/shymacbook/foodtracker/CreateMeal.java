package com.example.shymacbook.foodtracker;

import android.content.Context;
import android.content.Intent;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class CreateMeal extends AppCompatActivity {
    private String arrayExtra[];
    private EditText mMealName_eText;
    private EditText mMealNotes_eText;
    private Button mAddMeal_Button;
    private ImageView mBitMapImView;
    private BitmapDrawable mBitMapDrawable;
    private Bitmap mBitMap;
    private byte[] mByteArray;

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
        mBitMapImView = (ImageView) this.findViewById(R.id.Mealphoto);


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
                // get bitmap bytes, to send to db for picture
                mBitMapDrawable = (BitmapDrawable) mBitMapImView.getDrawable();
                mBitMap = mBitMapDrawable.getBitmap();
                mByteArray = dbBitmapUtility.getBytes(mBitMap);
                arrayExtra[2] = "coming from CreareMeal act.";
                Log.d("function execution: ", "arrayExtra was made...");
                Context context = CreateMeal.this;
                Class destinationAct = Meals.class;
                Intent intent = new Intent(context, destinationAct);
                Log.d("function execution: ", "about to putExtra");
                intent.putExtra(Intent.EXTRA_TEXT, arrayExtra);
                // TODO: byte[] for bitmaps
                intent.putExtra(Intent.EXTRA_PROCESS_TEXT, mByteArray);
                Log.d("intent.EXTRA", "createThisMeal_buttonClick: " + arrayExtra[0] + ", " + arrayExtra[1] + ", " + arrayExtra[2]);
                Log.d("IMAGE check", "onClick: mByteArray.length = " + mByteArray.length);
                Log.d("IMAGE check", "onClick: extra mByteArray[0] = " + mByteArray[0]);
                Log.d("IMAGE check", "onClick: extra mByteArray = " + mByteArray);
                Log.d("IMAGE check", "onClick: extra mByteArray.toString() = " + mByteArray.toString());
                startActivity(intent);
            }
        });
    }

    // allow user to take pic of the new Meal
    // Create a constant int request code
    static final int REQUEST_IMAGE_CAPTURE = 30210;
    public void dispatchTakePictureIntent(View view) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ImageView mImageView= (ImageView) findViewById(R.id.Mealphoto);
            mImageView.setImageBitmap(imageBitmap);
        }
    }

}
