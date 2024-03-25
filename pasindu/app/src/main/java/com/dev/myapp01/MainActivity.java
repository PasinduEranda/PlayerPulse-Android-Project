package com.dev.myapp01;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.ListFragment;

import android.os.PersistableBundle;
import android.view.View;
import android.widget.ImageView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.dev.myapp01.HomeFragment;
import com.dev.myapp01.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Initial fragment
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.section, new HomeFragment())
                    .commit();
        }

        ImageView homeImageView = findViewById(R.id.imghome);
        ImageView listImageView = findViewById(R.id.imglist);
        ImageView aboutImageView = findViewById(R.id.imghinfo);

        homeImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(new HomeFragment());
            }
        });

        listImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(new TeamFragment());
            }
        });
        aboutImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(new AboutFragment());
            }
        });
    }



    private void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.section, fragment)
                .addToBackStack(null)
                .commit();
    }
}
