package com.svalero.appeventia.contract;

import com.svalero.appeventia.model.Artista;

public interface ArtistaFormContract {

    interface View {
        void artistaGuardado();
        void mostrarError(String mensaje);
    }

    interface Presenter {
        void crearArtista(Artista artista);
        void editarArtista(long id, Artista artista);
    }
}