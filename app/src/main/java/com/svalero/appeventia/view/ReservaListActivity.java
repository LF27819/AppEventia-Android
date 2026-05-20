package com.svalero.appeventia.view;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.svalero.appeventia.R;
import com.svalero.appeventia.adapter.ReservaAdapter;
import com.svalero.appeventia.contract.ReservaListContract;
import com.svalero.appeventia.model.Reserva;
import com.svalero.appeventia.presenter.ReservaListPresenter;

import java.util.ArrayList;
import java.util.List;

public class ReservaListActivity extends AppCompatActivity implements ReservaListContract.View {

    private List<Reserva> reservas;
    private ReservaAdapter reservaAdapter;
    private ReservaListContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserva_list);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        reservas = new ArrayList<>();
        presenter = new ReservaListPresenter(this);

        RecyclerView reservasRecyclerView = findViewById(R.id.reservasRecyclerView);
        reservasRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        reservaAdapter = new ReservaAdapter(reservas);
        reservasRecyclerView.setAdapter(reservaAdapter);

        presenter.cargarReservas();
    }

    @Override
    public void mostrarReservas(List<Reserva> nuevasReservas) {
        reservas.clear();
        reservas.addAll(nuevasReservas);
        reservaAdapter.notifyDataSetChanged();
    }

    @Override
    public void mostrarError(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}