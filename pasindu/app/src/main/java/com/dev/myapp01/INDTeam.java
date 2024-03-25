package com.dev.myapp01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class INDTeam extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indteam);

        ImageView backButton = findViewById(R.id.back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the current activity to go back to the previous one
                finish();
            }
        });
        View viewAddPlayer = findViewById(R.id.viewAddPlayer);

        // Set OnClickListener to navigate to AddPlayer activity
        viewAddPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start AddPlayer activity
                Intent intent = new Intent(INDTeam.this, AddPlayer.class);
                startActivity(intent);
            }
        });
    }
}