package com.svalero.appeventia.presenter;

import com.svalero.appeventia.api.EventoApiInterface;
import com.svalero.appeventia.api.RetrofitClient;
import com.svalero.appeventia.contract.ArtistaListContract;
import com.svalero.appeventia.model.Artista;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArtistaListPresenter implements ArtistaListContract.Presenter {

    private final ArtistaListContract.View view;

    public ArtistaListPresenter(ArtistaListContract.View view) {
        this.view = view;
    }

    @Override
    public void cargarArtistas() {
        EventoApiInterface api = RetrofitClient.getClient().create(EventoApiInterface.class);

        Call<List<Artista>> call = api.getArtistas();

        call.enqueue(new Callback<List<Artista>>() {
            @Override
            public void onResponse(Call<List<Artista>> call, Response<List<Artista>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    view.mostrarArtistas(response.body());
                } else {
                    view.mostrarError("No se han podido cargar los artistas");
                }
            }

            @Override
            public void onFailure(Call<List<Artista>> call, Throwable t) {
                view.mostrarError("Error de conexión con la API");
            }
        });
    }
}