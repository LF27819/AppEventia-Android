package com.svalero.appeventia.presenter;

import com.svalero.appeventia.api.EventoApiInterface;
import com.svalero.appeventia.api.RetrofitClient;
import com.svalero.appeventia.contract.EventoListContract;
import com.svalero.appeventia.model.Evento;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventoListPresenter implements EventoListContract.Presenter {

    private final EventoListContract.View view;
    private final List<Evento> eventos;

    public EventoListPresenter(EventoListContract.View view) {
        this.view = view;
        this.eventos = new ArrayList<>();
    }

    @Override
    public void cargarEventos() {
        EventoApiInterface api = RetrofitClient.getClient().create(EventoApiInterface.class);

        Call<List<Evento>> call = api.getEventos();

        call.enqueue(new Callback<List<Evento>>() {
            @Override
            public void onResponse(Call<List<Evento>> call, Response<List<Evento>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    eventos.clear();
                    eventos.addAll(response.body());
                    view.mostrarEventos(eventos);
                } else {
                    view.mostrarError("No se han podido cargar los eventos");
                }
            }

            @Override
            public void onFailure(Call<List<Evento>> call, Throwable t) {
                view.mostrarError("Error de conexión con la API");
            }
        });
    }

    @Override
    public void filtrarEventos(String texto) {
        List<Evento> eventosFiltrados = new ArrayList<>();

        if (texto == null || texto.trim().isEmpty()) {
            eventosFiltrados.addAll(eventos);
        } else {
            String textoBusqueda = texto.toLowerCase().trim();

            for (Evento evento : eventos) {
                boolean coincideNombre = evento.getNombre() != null &&
                        evento.getNombre().toLowerCase().contains(textoBusqueda);

                boolean coincideCategoria = evento.getCategoria() != null &&
                        evento.getCategoria().toLowerCase().contains(textoBusqueda);

                boolean coincideRecinto = evento.getRecinto() != null &&
                        evento.getRecinto().getNombre() != null &&
                        evento.getRecinto().getNombre().toLowerCase().contains(textoBusqueda);

                if (coincideNombre || coincideCategoria || coincideRecinto) {
                    eventosFiltrados.add(evento);
                }
            }
        }

        view.mostrarEventos(eventosFiltrados);
    }
}