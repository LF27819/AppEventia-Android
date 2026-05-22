package com.svalero.appeventia.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
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

        eventoAdapter = new EventoAdapter(eventos, new EventoAdapter.OnEventoClickListener() {

            @Override
            public void onEditEvento(Evento evento) {

                Intent intent = new Intent(EventoListActivity.this, EventoFormActivity.class);

                intent.putExtra("id", evento.getId());
                intent.putExtra("nombre", evento.getNombre());
                intent.putExtra("descripcion", evento.getDescripcion());
                intent.putExtra("fecha", evento.getFechaEvento());
                intent.putExtra("hora", evento.getHoraEvento());
                intent.putExtra("precio", evento.getPrecioEntrada());
                intent.putExtra("categoria", evento.getCategoria());

                startActivity(intent);
            }

            @Override
            public void onDeleteEvento(Evento evento) {
                new androidx.appcompat.app.AlertDialog.Builder(EventoListActivity.this)
                        .setTitle("Eliminar evento")
                        .setMessage("¿Seguro que quieres eliminar " + evento.getNombre() + "?")
                        .setPositiveButton("Eliminar", (dialog, which) -> {
                            presenter.eliminarEvento(evento.getId());
                        })
                        .setNegativeButton("Cancelar", null)
                        .show();
            }

        });

        eventosRecyclerView.setAdapter(eventoAdapter);

        EditText searchEditText = findViewById(R.id.searchEditText);
        Button addEventoButton = findViewById(R.id.addEventoButton);

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

        addEventoButton.setOnClickListener(v -> {
            Intent intent = new Intent(EventoListActivity.this, EventoFormActivity.class);
            startActivity(intent);
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
    public void mostrarMensaje(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}