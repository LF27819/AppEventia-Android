package com.svalero.appeventia.presenter;

import com.svalero.appeventia.api.EventoApiInterface;
import com.svalero.appeventia.api.RetrofitClient;
import com.svalero.appeventia.contract.EventoFormContract;
import com.svalero.appeventia.model.Artista;
import com.svalero.appeventia.model.Evento;
import com.svalero.appeventia.model.Recinto;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventoFormPresenter implements EventoFormContract.Presenter {

    private final EventoFormContract.View view;
    private final EventoApiInterface api;

    public EventoFormPresenter(EventoFormContract.View view) {
        this.view = view;
        this.api = RetrofitClient.getClient().create(EventoApiInterface.class);
    }

    @Override
    public void cargarDatosFormulario() {
        api.getArtistas().enqueue(new Callback<List<Artista>>() {
            @Override
            public void onResponse(Call<List<Artista>> call, Response<List<Artista>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    view.mostrarArtistas(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Artista>> call, Throwable t) {
                view.mostrarMensaje("No se han podido cargar los artistas");
            }
        });

        api.getRecintos().enqueue(new Callback<List<Recinto>>() {
            @Override
            public void onResponse(Call<List<Recinto>> call, Response<List<Recinto>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    view.mostrarRecintos(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Recinto>> call, Throwable t) {
                view.mostrarMensaje("No se han podido cargar los recintos");
            }
        });
    }

    @Override
    public void crearEvento(Evento evento) {
        api.addEvento(evento).enqueue(new Callback<Evento>() {
            @Override
            public void onResponse(Call<Evento> call, Response<Evento> response) {
                if (response.isSuccessful()) {
                    view.mostrarMensaje("Evento creado correctamente");
                    view.volverAlListado();
                } else {
                    view.mostrarMensaje("No se ha podido crear el evento");
                }
            }

            @Override
            public void onFailure(Call<Evento> call, Throwable t) {
                view.mostrarMensaje("Error al crear evento");
            }
        });
    }

    @Override
    public void editarEvento(long id, Evento evento) {
        api.updateEvento(id, evento).enqueue(new Callback<Evento>() {
            @Override
            public void onResponse(Call<Evento> call, Response<Evento> response) {
                if (response.isSuccessful()) {
                    view.mostrarMensaje("Evento actualizado correctamente");
                    view.volverAlListado();
                } else {
                    view.mostrarMensaje("No se ha podido actualizar el evento");
                }
            }

            @Override
            public void onFailure(Call<Evento> call, Throwable t) {
                view.mostrarMensaje("Error al actualizar evento");
            }
        });
    }
}