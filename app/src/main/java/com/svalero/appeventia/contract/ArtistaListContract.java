package com.svalero.appeventia.contract;

import com.svalero.appeventia.model.Artista;

import java.util.List;

public interface ArtistaListContract {

    interface View {
        void mostrarArtistas(List<Artista> artistas);
        void artistaEliminado();
        void mostrarError(String mensaje);
    }

    interface Presenter {
        void cargarArtistas();
        void eliminarArtista(long id);
    }
}