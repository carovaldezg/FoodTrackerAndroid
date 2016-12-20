package com.example.caro.foodtrackerandroid;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageButton;
import android.view.View;
import android.view.View.OnClickListener;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import static android.widget.Toast.*;


public class AddMealMainActivity extends AppCompatActivity {

    ImageButton photoImageButton;
    RatingBar ratingbar;
    EditText edittext;
    private static int RESULT_LOAD_IMG = 1;
    String imgDecodableString;
    String ratingstars;
    Integer MY_PERMISSIONS_REQUEST_READ_INTERNAL_STORAGE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meal_main);
        addListenerOnButton();
        addListenerOnRatingBar();
        addListenerOnEditText();

    }

    public void addListenerOnButton() {
        photoImageButton = (ImageButton) findViewById(R.id.photoImageButton);
        photoImageButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                //After clicking in the button, create a new intent to show the photo gallery
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                // Start the Intent
                startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
            }

        });


    }


    public void addListenerOnEditText()
    {
        edittext = (EditText) findViewById(R.id.editText);
        edittext.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                edittext.setText("");
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {


            // When an Image is picked
            if (requestCode == RESULT_LOAD_IMG && resultCode == RESULT_OK
                    && null != data) {
                // Get the Image from data

                Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};

                // Get the cursor
                Cursor cursor = getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);
                // Move to first row
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                imgDecodableString = cursor.getString(columnIndex);
                cursor.close();
                //   ImageView imgView = (ImageView) findViewById(R.id.imgView);
                // Set the Image in ImageView after decoding the String
                photoImageButton.setImageBitmap(BitmapFactory
                        .decodeFile(imgDecodableString));

            } else {
                makeText(this, "You haven't picked Image",
                        LENGTH_LONG).show();
            }
        } catch (Exception e) {
            makeText(this, "Something went wrong" + e.getMessage(), LENGTH_LONG)
                    .show();
            Log.e("MYAPP", "exception", e);
            System.out.print(e.getMessage().toString());

        }

    }

    public void addListenerOnRatingBar()
    {
        ratingbar = (RatingBar) findViewById(R.id.ratingBar);
        ratingbar.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ratingstars=String.valueOf(ratingbar.getRating());
                ratingbar.setNumStars(Integer.valueOf(ratingstars));
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item)
    {
        switch (item.getItemId()) {
            case R.id.action_save:
                //add code to save the new meal in the meal list
                return true;


            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }


}//END CLASS