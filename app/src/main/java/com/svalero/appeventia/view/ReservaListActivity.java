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

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

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

        EditText searchReservasEditText = findViewById(R.id.searchReservasEditText);

        searchReservasEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence text, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence text, int start, int before, int count) {
                presenter.filtrarReservas(text.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
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