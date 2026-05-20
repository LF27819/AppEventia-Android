package com.svalero.appeventia.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.svalero.appeventia.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button eventsButton = findViewById(R.id.eventsButton);
        Button favoritesButton = findViewById(R.id.favoritesButton);
        Button profileButton = findViewById(R.id.profileButton);


        eventsButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, EventoListActivity.class);
            startActivity(intent);
        });

        favoritesButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, FavoritesActivity.class);
            startActivity(intent);
        });

        profileButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
            startActivity(intent);
        });
    }
}