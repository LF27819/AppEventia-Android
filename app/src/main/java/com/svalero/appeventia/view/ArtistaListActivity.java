package com.svalero.appeventia.view;

import android.os.Bundle;
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

        RecyclerView artistasRecyclerView = findViewById(R.id.artistasRecyclerView);
        artistasRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        artistaAdapter = new ArtistaAdapter(artistas);
        artistasRecyclerView.setAdapter(artistaAdapter);

        presenter.cargarArtistas();
    }

    @Override
    public void mostrarArtistas(List<Artista> nuevosArtistas) {
        artistas.clear();
        artistas.addAll(nuevosArtistas);
        artistaAdapter.notifyDataSetChanged();
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