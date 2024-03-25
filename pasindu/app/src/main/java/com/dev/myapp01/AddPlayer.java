package com.dev.myapp01;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.sql.Blob;

public class AddPlayer extends AppCompatActivity {

    EditText input_name, input_innings, input_score, input_wickets;
    //ImageView input_image;
    Button btnSave, cancelButton;

    private static final int PICK_IMAGE_REQUEST = 99;

    //private Uri imagePath;
    //private Bitmap imageToStore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_player);

        cancelButton = findViewById(R.id.btnCncel);

        input_name = findViewById(R.id.input_name);
        input_innings = findViewById(R.id.input_innings);
        input_score = findViewById(R.id.input_score);
        input_wickets = findViewById(R.id.input_wickets);
        //input_image = findViewById(R.id.input_image);
        btnSave = findViewById(R.id.btnSave);

        /*input_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage();
            }
        });*/


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the name, innings, score, and wickets from the EditText fields
                String name = input_name.getText().toString().trim();
                int innings = Integer.valueOf(input_innings.getText().toString().trim());
                int score = Integer.valueOf(input_score.getText().toString().trim());
                int wickets = Integer.valueOf(input_wickets.getText().toString().trim());

                // Store the player details in the database
                MyDatabaseHelper myDB = new MyDatabaseHelper(AddPlayer.this);
                long id = myDB.addPlayer(name, innings, score, wickets);

                if (id != -1) {
                    // Data saved successfully, send back the newly added data to MainActivity
                    Intent intent = new Intent();
                    intent.putExtra("newDataAdded", true);
                    setResult(RESULT_OK, intent);
                    finish(); // Close AddActivity and go back to MainActivity
                } else {
                    // Failed to save data
                    Toast.makeText(AddPlayer.this, "Failed to save data", Toast.LENGTH_SHORT).show();
                }
            }
        });



        //Cancellation and back
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddPlayer.this, SLTeam.class);
                startActivity(intent);
                // Finish the current activity to go back to the previous one
                finish();
            }
        });
    }

    // Put the image file path into this method
/*    public  String getFileToByte(String filePath){
        Bitmap bmp = null;
        ByteArrayOutputStream bos = null;
        byte[] bt = null;
        String encodeString = null;
        try{
            bmp = BitmapFactory.decodeFile(filePath);
            bos = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bt = bos.toByteArray();
            encodeString = Base64.encodeToString(bt, Base64.DEFAULT);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return encodeString;
    }
/*
    private void chooseImage() {
        try{
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(intent, PICK_IMAGE_REQUEST);
        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        try{
            super.onActivityResult(requestCode, resultCode, data);
            if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null){
                imagePath = data.getData();
                imageToStore = MediaStore.Images.Media.getBitmap(getContentResolver(),imagePath);
                input_image.setImageBitmap(imageToStore);

            }
        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }*/
}
