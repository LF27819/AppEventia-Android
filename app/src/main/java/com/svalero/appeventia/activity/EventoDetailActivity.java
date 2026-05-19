package com.svalero.appeventia.activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.svalero.appeventia.R;

import android.widget.Button;
import android.widget.Toast;

import com.svalero.appeventia.database.AppDatabase;
import com.svalero.appeventia.database.Favorito;
import com.svalero.appeventia.utils.CoordenadasRecintoUtil;
import com.svalero.appeventia.utils.DatabaseClient;

import android.content.Intent;


public class EventoDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evento_detail);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

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



        Button addFavoriteButton = findViewById(R.id.addFavoriteButton);
        Button openMapButton = findViewById(R.id.openMapButton);

        addFavoriteButton.setOnClickListener(v -> {
            long id = getIntent().getLongExtra("id", 0);
            String nombre = getIntent().getStringExtra("nombre");
            String categoria = getIntent().getStringExtra("categoria");
            String fecha = getIntent().getStringExtra("fecha");
            String hora = getIntent().getStringExtra("hora");
            float precio = getIntent().getFloatExtra("precio", 0);
            String recinto = getIntent().getStringExtra("recinto");
            String descripcion = getIntent().getStringExtra("descripcion");

            AppDatabase db = DatabaseClient.getInstance(EventoDetailActivity.this);

            Favorito favoritoExistente = db.favoritoDao().findById(id);

            if (favoritoExistente != null) {
                Toast.makeText(EventoDetailActivity.this,
                        "Este evento ya está en favoritos",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            Favorito favorito = new Favorito(
                    id,
                    nombre,
                    descripcion,
                    fecha,
                    hora,
                    precio,
                    categoria,
                    recinto,
                    ""
            );

            db.favoritoDao().insert(favorito);

            Toast.makeText(EventoDetailActivity.this,
                    "Evento añadido a favoritos",
                    Toast.LENGTH_SHORT).show();
        });

        openMapButton.setOnClickListener(v -> {

            Intent intent = new Intent(EventoDetailActivity.this, MapActivity.class);

            String recinto = getIntent().getStringExtra("recinto");
            double[] coordenadas = CoordenadasRecintoUtil.obtenerCoordenadas(recinto);

            intent.putExtra("nombreRecinto", recinto);
            intent.putExtra("latitud", coordenadas[0]);
            intent.putExtra("longitud", coordenadas[1]);

            startActivity(intent);
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

}