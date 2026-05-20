package com.svalero.appeventia.view;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.svalero.appeventia.R;
import com.svalero.appeventia.adapter.EventoAdapter;
import com.svalero.appeventia.contract.EventoListContract;
import com.svalero.appeventia.model.Evento;
import com.svalero.appeventia.presenter.EventoListPresenter;

import java.util.ArrayList;
import java.util.List;

public class EventoListActivity extends AppCompatActivity implements EventoListContract.View {

    private List<Evento> eventos;
    private EventoAdapter eventoAdapter;
    private EventoListContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evento_list);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        eventos = new ArrayList<>();
        presenter = new EventoListPresenter(this);

        RecyclerView eventosRecyclerView = findViewById(R.id.eventosRecyclerView);
        eventosRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        eventoAdapter = new EventoAdapter(eventos);
        eventosRecyclerView.setAdapter(eventoAdapter);

        EditText searchEditText = findViewById(R.id.searchEditText);

        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence text, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence text, int start, int before, int count) {
                presenter.filtrarEventos(text.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        presenter.cargarEventos();
    }

    @Override
    public void mostrarEventos(List<Evento> nuevosEventos) {
        eventos.clear();
        eventos.addAll(nuevosEventos);
        eventoAdapter.notifyDataSetChanged();
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