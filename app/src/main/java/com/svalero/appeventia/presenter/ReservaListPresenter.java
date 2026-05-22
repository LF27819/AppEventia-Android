package com.svalero.appeventia.presenter;

import com.svalero.appeventia.api.EventoApiInterface;
import com.svalero.appeventia.api.RetrofitClient;
import com.svalero.appeventia.contract.ReservaListContract;
import com.svalero.appeventia.model.Reserva;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReservaListPresenter implements ReservaListContract.Presenter {

    private final ReservaListContract.View view;
    private final List<Reserva> reservas;

    public ReservaListPresenter(ReservaListContract.View view) {
        this.view = view;
        this.reservas = new ArrayList<>();
    }

    @Override
    public void cargarReservas() {
        EventoApiInterface api = RetrofitClient.getClient().create(EventoApiInterface.class);

        api.getReservas().enqueue(new Callback<List<Reserva>>() {
            @Override
            public void onResponse(Call<List<Reserva>> call, Response<List<Reserva>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    reservas.clear();
                    reservas.addAll(response.body());
                    view.mostrarReservas(reservas);
                } else {
                    view.mostrarError("No se han podido cargar las reservas");
                }
            }

            @Override
            public void onFailure(Call<List<Reserva>> call, Throwable t) {
                view.mostrarError("Error de conexión con la API");
            }
        });
    }

    @Override
    public void filtrarReservas(String texto) {
        List<Reserva> reservasFiltradas = new ArrayList<>();

        if (texto == null || texto.trim().isEmpty()) {
            reservasFiltradas.addAll(reservas);
        } else {
            String textoBusqueda = texto.toLowerCase().trim();

            for (Reserva reserva : reservas) {
                boolean coincideCodigo = reserva.getCodigoReserva() != null &&
                        reserva.getCodigoReserva().toLowerCase().contains(textoBusqueda);

                boolean coincideMetodoPago = reserva.getMetodoPago() != null &&
                        reserva.getMetodoPago().toLowerCase().contains(textoBusqueda);

                boolean coincideEvento = reserva.getEvento() != null &&
                        reserva.getEvento().getNombre() != null &&
                        reserva.getEvento().getNombre().toLowerCase().contains(textoBusqueda);

                if (coincideCodigo || coincideMetodoPago || coincideEvento) {
                    reservasFiltradas.add(reserva);
                }
            }
        }

        view.mostrarReservas(reservasFiltradas);
    }
}