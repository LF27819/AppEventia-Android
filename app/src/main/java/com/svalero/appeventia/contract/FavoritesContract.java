package com.svalero.appeventia.contract;

import com.svalero.appeventia.database.Favorito;

import java.util.List;

public interface FavoritesContract {

    interface View {
        void mostrarFavoritos(List<Favorito> favoritos);
        void mostrarMensaje(String mensaje);
    }

    interface Presenter {
        void cargarFavoritos();
        void eliminarFavorito(Favorito favorito);
    }
}