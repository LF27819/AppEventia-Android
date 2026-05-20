package com.svalero.appeventia.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.svalero.appeventia.R;

import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button eventsButton = findViewById(R.id.eventsButton);
        Button favoritesButton = findViewById(R.id.favoritesButton);
        Button profileButton = findViewById(R.id.profileButton);
        Button artistsButton = findViewById(R.id.artistsButton);
        Button reservationsButton = findViewById(R.id.reservationsButton);


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

        artistsButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ArtistaListActivity.class);
            startActivity(intent);
        });

        reservationsButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ReservaListActivity.class);
            startActivity(intent);
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_events) {
            startActivity(new Intent(this, EventoListActivity.class));
            return true;
        }

        if (id == R.id.menu_artists) {
            startActivity(new Intent(this, ArtistaListActivity.class));
            return true;
        }

        if (id == R.id.menu_reservations) {
            startActivity(new Intent(this, ReservaListActivity.class));
            return true;
        }

        if (id == R.id.menu_favorites) {
            startActivity(new Intent(this, FavoritesActivity.class));
            return true;
        }

        if (id == R.id.menu_profile) {
            startActivity(new Intent(this, ProfileActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}