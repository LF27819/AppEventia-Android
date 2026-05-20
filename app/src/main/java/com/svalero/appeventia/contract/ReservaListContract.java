package com.svalero.appeventia.contract;

import com.svalero.appeventia.model.Reserva;

import java.util.List;

public interface ReservaListContract {

    interface View {
        void mostrarReservas(List<Reserva> reservas);
        void mostrarError(String mensaje);
    }

    interface Presenter {
        void cargarReservas();
    }
}