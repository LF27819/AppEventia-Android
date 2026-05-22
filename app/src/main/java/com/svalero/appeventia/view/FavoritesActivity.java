package com.svalero.appeventia.view;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.svalero.appeventia.R;
import com.svalero.appeventia.adapter.FavoritoAdapter;
import com.svalero.appeventia.contract.FavoritesContract;
import com.svalero.appeventia.database.Favorito;
import com.svalero.appeventia.presenter.FavoritesPresenter;

import java.util.ArrayList;
import java.util.List;

public class FavoritesActivity extends AppCompatActivity implements FavoritesContract.View {

    private List<Favorito> favoritos;
    private FavoritoAdapter favoritoAdapter;
    private FavoritesContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        favoritos = new ArrayList<>();
        presenter = new FavoritesPresenter(this, this);

        RecyclerView favoritesRecyclerView = findViewById(R.id.favoritesRecyclerView);
        favoritesRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        favoritoAdapter = new FavoritoAdapter(
                favoritos,
                this,
                favorito -> new androidx.appcompat.app.AlertDialog.Builder(FavoritesActivity.this)
                        .setTitle("Eliminar favorito")
                        .setMessage("¿Seguro que quieres eliminar este favorito?")
                        .setPositiveButton("Eliminar", (dialog, which) -> {
                            presenter.eliminarFavorito(favorito);
                        })
                        .setNegativeButton("Cancelar", null)
                        .show()
        );

        favoritesRecyclerView.setAdapter(favoritoAdapter);

        presenter.cargarFavoritos();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.cargarFavoritos();
    }

    @Override
    public void mostrarFavoritos(List<Favorito> nuevosFavoritos) {
        favoritos.clear();
        favoritos.addAll(nuevosFavoritos);
        favoritoAdapter.notifyDataSetChanged();
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