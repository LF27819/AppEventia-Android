package com.svalero.appeventia.contract;

import com.svalero.appeventia.model.Artista;
import com.svalero.appeventia.model.Evento;
import com.svalero.appeventia.model.Recinto;

import java.util.List;

public interface EventoFormContract {

    interface View {
        void mostrarArtistas(List<Artista> artistas);
        void mostrarRecintos(List<Recinto> recintos);
        void mostrarMensaje(String mensaje);
        void volverAlListado();
    }

    interface Presenter {
        void cargarDatosFormulario();
        void crearEvento(Evento evento);
        void editarEvento(long id, Evento evento);
    }
}