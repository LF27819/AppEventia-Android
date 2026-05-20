package com.svalero.appeventia.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.svalero.appeventia.R;
import com.svalero.appeventia.contract.EventoDetailContract;
import com.svalero.appeventia.presenter.EventoDetailPresenter;
import com.svalero.appeventia.utils.CoordenadasRecintoUtil;

public class EventoDetailActivity extends AppCompatActivity implements EventoDetailContract.View {

    private EventoDetailContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evento_detail);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        presenter = new EventoDetailPresenter(this, this);

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

            presenter.guardarFavorito(
                    id,
                    nombre,
                    descripcion,
                    fecha,
                    hora,
                    precio,
                    categoria,
                    recinto
            );
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
    public void mostrarMensaje(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}