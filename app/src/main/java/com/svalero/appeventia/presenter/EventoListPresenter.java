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

        api.getEventos().enqueue(new Callback<List<Evento>>() {
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

    @Override
    public void crearEvento(Evento evento) {
        EventoApiInterface api = RetrofitClient.getClient().create(EventoApiInterface.class);

        api.addEvento(evento).enqueue(new Callback<Evento>() {
            @Override
            public void onResponse(Call<Evento> call, Response<Evento> response) {
                if (response.isSuccessful()) {
                    view.mostrarMensaje("Evento creado correctamente");
                    cargarEventos();
                } else {
                    view.mostrarError("No se ha podido crear el evento");
                }
            }

            @Override
            public void onFailure(Call<Evento> call, Throwable t) {
                view.mostrarError("Error al crear evento");
            }
        });
    }

    @Override
    public void editarEvento(long id, Evento evento) {
        EventoApiInterface api = RetrofitClient.getClient().create(EventoApiInterface.class);

        api.updateEvento(id, evento).enqueue(new Callback<Evento>() {
            @Override
            public void onResponse(Call<Evento> call, Response<Evento> response) {
                if (response.isSuccessful()) {
                    view.mostrarMensaje("Evento actualizado correctamente");
                    cargarEventos();
                } else {
                    view.mostrarError("No se ha podido actualizar el evento");
                }
            }

            @Override
            public void onFailure(Call<Evento> call, Throwable t) {
                view.mostrarError("Error al actualizar evento");
            }
        });
    }

    @Override
    public void eliminarEvento(long id) {
        EventoApiInterface api = RetrofitClient.getClient().create(EventoApiInterface.class);

        api.deleteEvento(id).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    view.mostrarMensaje("Evento eliminado correctamente");
                    cargarEventos();
                } else {
                    view.mostrarError("No se ha podido eliminar el evento");
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                view.mostrarError("Error al eliminar evento");
            }
        });
    }
}