package com.svalero.appeventia.presenter;

import android.content.Context;

import com.svalero.appeventia.contract.EventoDetailContract;
import com.svalero.appeventia.database.AppDatabase;
import com.svalero.appeventia.database.Favorito;
import com.svalero.appeventia.utils.DatabaseClient;

public class EventoDetailPresenter implements EventoDetailContract.Presenter {

    private final EventoDetailContract.View view;
    private final AppDatabase db;

    public EventoDetailPresenter(EventoDetailContract.View view, Context context) {
        this.view = view;
        this.db = DatabaseClient.getInstance(context);
    }

    @Override
    public void guardarFavorito(long id, String nombre, String descripcion, String fecha,
                                String hora, float precio, String categoria, String recinto) {

        Favorito favoritoExistente = db.favoritoDao().findById(id);

        if (favoritoExistente != null) {
            view.mostrarMensaje("Este evento ya está en favoritos");
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

        view.mostrarMensaje("Evento añadido a favoritos");
    }
}