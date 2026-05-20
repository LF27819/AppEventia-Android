package com.svalero.appeventia.contract;

import com.svalero.appeventia.model.Evento;

import java.util.List;

public interface EventoListContract {

    interface View {
        void mostrarEventos(List<Evento> eventos);
        void mostrarError(String mensaje);
    }

    interface Presenter {
        void cargarEventos();
        void filtrarEventos(String texto);
    }
}