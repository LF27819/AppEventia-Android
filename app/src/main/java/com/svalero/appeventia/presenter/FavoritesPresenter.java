package com.svalero.appeventia.presenter;

import android.content.Context;

import com.svalero.appeventia.contract.FavoritesContract;
import com.svalero.appeventia.database.AppDatabase;
import com.svalero.appeventia.database.Favorito;
import com.svalero.appeventia.utils.DatabaseClient;

import java.util.List;

public class FavoritesPresenter implements FavoritesContract.Presenter {

    private final FavoritesContract.View view;
    private final AppDatabase db;

    public FavoritesPresenter(FavoritesContract.View view, Context context) {
        this.view = view;
        this.db = DatabaseClient.getInstance(context);
    }

    @Override
    public void cargarFavoritos() {
        List<Favorito> favoritos = db.favoritoDao().findAll();
        view.mostrarFavoritos(favoritos);
    }

    @Override
    public void eliminarFavorito(Favorito favorito) {
        db.favoritoDao().delete(favorito);
        view.mostrarMensaje("Favorito eliminado");
        cargarFavoritos();
    }
}