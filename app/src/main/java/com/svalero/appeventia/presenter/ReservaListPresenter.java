package com.svalero.appeventia.presenter;

import com.svalero.appeventia.api.EventoApiInterface;
import com.svalero.appeventia.api.RetrofitClient;
import com.svalero.appeventia.contract.ReservaListContract;
import com.svalero.appeventia.model.Reserva;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReservaListPresenter implements ReservaListContract.Presenter {

    private final ReservaListContract.View view;

    public ReservaListPresenter(ReservaListContract.View view) {
        this.view = view;
    }

    @Override
    public void cargarReservas() {
        EventoApiInterface api = RetrofitClient.getClient().create(EventoApiInterface.class);

        Call<List<Reserva>> call = api.getReservas();

        call.enqueue(new Callback<List<Reserva>>() {
            @Override
            public void onResponse(Call<List<Reserva>> call, Response<List<Reserva>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    view.mostrarReservas(response.body());
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
}