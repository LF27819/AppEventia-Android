package com.svalero.appeventia.contract;

public interface EventoDetailContract {

    interface View {
        void mostrarMensaje(String mensaje);
    }

    interface Presenter {
        void guardarFavorito(
                long id,
                String nombre,
                String descripcion,
                String fecha,
                String hora,
                float precio,
                String categoria,
                String recinto
        );
    }
}