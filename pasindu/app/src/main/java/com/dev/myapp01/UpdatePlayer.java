package com.dev.myapp01;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class UpdatePlayer extends AppCompatActivity {

    EditText update_name, update_innings, update_score, update_wickets;
    ImageView update_image;
    Button btnUpdate, btnDelete;
    String id, name, innings, score, wickets, image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_player);

        update_name = findViewById(R.id.update_name);
        update_innings = findViewById(R.id.update_innings);
        update_score = findViewById(R.id.update_score);
        update_wickets = findViewById(R.id.update_wickets);
        //update_image = findViewById(R.id.update_image);
        btnUpdate =  findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);

        updateData();

        ActionBar ab = getSupportActionBar();
        if(ab != null){
            ab.setTitle(name);
        }

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdatePlayer.this);

                name = update_name.getText().toString().trim();
                innings = update_innings.getText().toString().trim();
                score = update_score.getText().toString().trim();
                wickets =   update_wickets.getText().toString().trim();
                ///image =   update_image.getDrawable().toString().trim();
                myDB.updatePlayer(id, name, innings, score, wickets, image);

                Intent intent = new Intent();
                intent.putExtra("newDataAdded", true);
                setResult(RESULT_OK, intent);
                finish(); // Close AddActivity and go back to MainActivity

            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog();
            }
        });


    }
    void updateData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("name") && getIntent().hasExtra("innings") &&
                getIntent().hasExtra("score") && getIntent().hasExtra("wickets")){
            //Getting data from intent
            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");
            innings = getIntent().getStringExtra("innings");
            score = getIntent().getStringExtra("score");
            wickets = getIntent().getStringExtra("wickets");
            //image = getIntent().getStringExtra("image");

            //setting data
            update_name.setText(name);
            update_innings.setText(innings);
            update_score.setText(score);
            update_wickets.setText(wickets);
            //update_image.setImageResource(Integer.parseInt(image));
            /*
            try {
                int resourceId = Integer.parseInt(image); // Assuming image is a string representing a resource ID
                update_image.setImageResource(resourceId);
            } catch (NumberFormatException e) {
                // Handle the case where image is not a valid resource ID
                e.printStackTrace();
            }*/



        }else{
            Toast.makeText(this, "No Data to Show", Toast.LENGTH_SHORT).show();
        }
    }
    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + name + " ?");
        builder.setMessage("Are you sure you want to delete " + name + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdatePlayer.this);
                myDB.deletePlayer(id);
                finish();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {

            }
        });
        builder.create().show();
    }
}