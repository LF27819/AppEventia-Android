package com.svalero.appeventia.presenter;

import com.svalero.appeventia.contract.ProfileContract;

public class ProfilePresenter implements ProfileContract.Presenter {

    private final ProfileContract.View view;

    public ProfilePresenter(ProfileContract.View view) {
        this.view = view;
    }

    @Override
    public void guardarPreferencias(boolean notificacionesActivas) {
        view.mostrarMensaje("Perfil guardado");
    }
}