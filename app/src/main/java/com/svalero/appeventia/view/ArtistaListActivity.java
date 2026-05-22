package com.svalero.appeventia.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.svalero.appeventia.R;
import com.svalero.appeventia.adapter.ArtistaAdapter;
import com.svalero.appeventia.contract.ArtistaListContract;
import com.svalero.appeventia.model.Artista;
import com.svalero.appeventia.presenter.ArtistaListPresenter;

import java.util.ArrayList;
import java.util.List;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class ArtistaListActivity extends AppCompatActivity implements ArtistaListContract.View {

    private List<Artista> artistas;
    private ArtistaAdapter artistaAdapter;
    private ArtistaListContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artista_list);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        artistas = new ArrayList<>();
        presenter = new ArtistaListPresenter(this);

        Button addArtistaButton = findViewById(R.id.addArtistaButton);

        RecyclerView artistasRecyclerView = findViewById(R.id.artistasRecyclerView);
        artistasRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        artistaAdapter = new ArtistaAdapter(artistas, new ArtistaAdapter.ArtistaListener() {
            @Override
            public void editarArtista(Artista artista) {
                Intent intent = new Intent(ArtistaListActivity.this, ArtistaFormActivity.class);
                intent.putExtra("id", artista.getId());
                intent.putExtra("nombreArtistico", artista.getNombreArtistico());
                intent.putExtra("nombreReal", artista.getNombreReal());
                intent.putExtra("generoMusical", artista.getGeneroMusical());
                intent.putExtra("fechaNacimiento", artista.getFechaNacimiento());
                intent.putExtra("activo", artista.isActivo());
                intent.putExtra("cache", artista.getCache());
                intent.putExtra("eventosRealizados", artista.getEventosRealizados());
                startActivity(intent);
            }

            @Override
            public void eliminarArtista(Artista artista) {
                new androidx.appcompat.app.AlertDialog.Builder(ArtistaListActivity.this)
                        .setTitle("Eliminar artista")
                        .setMessage("¿Seguro que quieres eliminar a " + artista.getNombreArtistico() + "?")
                        .setPositiveButton("Eliminar", (dialog, which) -> {
                            presenter.eliminarArtista(artista.getId());
                        })
                        .setNegativeButton("Cancelar", null)
                        .show();
            }
        });

        artistasRecyclerView.setAdapter(artistaAdapter);

        addArtistaButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, ArtistaFormActivity.class);
            startActivity(intent);
        });

        EditText searchArtistasEditText = findViewById(R.id.searchArtistasEditText);

        searchArtistasEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence text, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence text, int start, int before, int count) {
                presenter.filtrarArtistas(text.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.cargarArtistas();
    }

    @Override
    public void mostrarArtistas(List<Artista> nuevosArtistas) {
        artistas.clear();
        artistas.addAll(nuevosArtistas);
        artistaAdapter.notifyDataSetChanged();
    }

    @Override
    public void artistaEliminado() {
        Toast.makeText(this, "Artista eliminado", Toast.LENGTH_SHORT).show();
        presenter.cargarArtistas();
    }

    @Override
    public void mostrarError(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}