package com.svalero.appeventia.presenter;

import com.svalero.appeventia.api.EventoApiInterface;
import com.svalero.appeventia.api.RetrofitClient;
import com.svalero.appeventia.contract.ArtistaFormContract;
import com.svalero.appeventia.model.Artista;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArtistaFormPresenter implements ArtistaFormContract.Presenter {

    private final ArtistaFormContract.View view;

    public ArtistaFormPresenter(ArtistaFormContract.View view) {
        this.view = view;
    }

    @Override
    public void crearArtista(Artista artista) {
        EventoApiInterface api = RetrofitClient.getClient().create(EventoApiInterface.class);

        api.addArtista(artista).enqueue(new Callback<Artista>() {
            @Override
            public void onResponse(Call<Artista> call, Response<Artista> response) {
                if (response.isSuccessful()) {
                    view.artistaGuardado();
                } else {
                    view.mostrarError("No se pudo crear el artista");
                }
            }

            @Override
            public void onFailure(Call<Artista> call, Throwable t) {
                view.mostrarError("Error de conexión con la API");
            }
        });
    }

    @Override
    public void editarArtista(long id, Artista artista) {
        EventoApiInterface api = RetrofitClient.getClient().create(EventoApiInterface.class);

        api.updateArtista(id, artista).enqueue(new Callback<Artista>() {
            @Override
            public void onResponse(Call<Artista> call, Response<Artista> response) {
                if (response.isSuccessful()) {
                    view.artistaGuardado();
                } else {
                    view.mostrarError("No se pudo editar el artista");
                }
            }

            @Override
            public void onFailure(Call<Artista> call, Throwable t) {
                view.mostrarError("Error de conexión con la API");
            }
        });
    }
}