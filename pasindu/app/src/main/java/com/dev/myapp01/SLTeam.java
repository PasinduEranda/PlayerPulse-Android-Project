package com.dev.myapp01;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.NoCopySpan;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class SLTeam extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton btnAdd;
    MyDatabaseHelper myDB;
    ArrayList<String> id, name, innings, score, wickets;
    CustomAdapter customAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sl_team);

        // back to previous page
        ImageView backButton = findViewById(R.id.back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the current activity to go back to the previous one
                finish();
            }
        });

        recyclerView = findViewById(R.id.recyclerView);
        btnAdd = findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SLTeam.this, AddPlayer.class);
                startActivity(intent);
            }
        });

        myDB = new MyDatabaseHelper(SLTeam.this);
        id = new ArrayList<>();
        name = new ArrayList<>();
        innings = new ArrayList<>();
        score = new ArrayList<>();
        wickets = new ArrayList<>();
        //image = new ArrayList<>();

        displayData();
        customAdapter = new CustomAdapter(SLTeam.this, this, id, name, innings, score, wickets);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(SLTeam.this));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            recreate();
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onResume() {
        super.onResume();
        id.clear();
        name.clear();
        innings.clear();
        score.clear();
        wickets.clear();
        //image.clear();
        displayData();
        customAdapter.notifyDataSetChanged();
    }
    void displayData() {
        Cursor cursor = myDB.readAllData();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No Data to Show", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                id.add(cursor.getString(0));
                name.add(cursor.getString(1));
                innings.add(cursor.getString(2));
                score.add(cursor.getString(3));
                wickets.add(cursor.getString(4));
                //image.add(cursor.getString(5));
            }
        }
    }




}