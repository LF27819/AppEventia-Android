package com.svalero.appeventia.activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.svalero.appeventia.R;

public class EventoDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evento_detail);

        TextView nombreText = findViewById(R.id.detailNombreText);
        TextView categoriaText = findViewById(R.id.detailCategoriaText);
        TextView fechaText = findViewById(R.id.detailFechaText);
        TextView precioText = findViewById(R.id.detailPrecioText);
        TextView recintoText = findViewById(R.id.detailRecintoText);
        TextView descripcionText = findViewById(R.id.detailDescripcionText);

        nombreText.setText(getIntent().getStringExtra("nombre"));
        categoriaText.setText(getIntent().getStringExtra("categoria"));
        fechaText.setText(getIntent().getStringExtra("fecha") + " - " + getIntent().getStringExtra("hora"));
        precioText.setText(getIntent().getFloatExtra("precio", 0) + " €");
        recintoText.setText(getIntent().getStringExtra("recinto"));
        descripcionText.setText(getIntent().getStringExtra("descripcion"));
    }
}