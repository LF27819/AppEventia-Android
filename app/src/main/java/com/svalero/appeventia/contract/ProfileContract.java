package com.svalero.appeventia.contract;

public interface ProfileContract {

    interface View {
        void mostrarMensaje(String mensaje);
    }

    interface Presenter {
        void guardarPreferencias(boolean notificacionesActivas);
    }
}