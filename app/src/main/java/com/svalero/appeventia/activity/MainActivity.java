package com.svalero.appeventia.activity;

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

        eventsButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, EventoListActivity.class);
            startActivity(intent);
        });
    }
}