package com.svalero.appeventia.activity;

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
import com.svalero.appeventia.api.EventoApiInterface;
import com.svalero.appeventia.api.RetrofitClient;
import com.svalero.appeventia.model.Evento;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventoListActivity extends AppCompatActivity {

    private List<Evento> eventos;
    private List<Evento> eventosFiltrados;
    private EventoAdapter eventoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evento_list);

        eventos = new ArrayList<>();
        eventosFiltrados = new ArrayList<>();

        RecyclerView eventosRecyclerView = findViewById(R.id.eventosRecyclerView);
        eventosRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        eventoAdapter = new EventoAdapter(eventosFiltrados);
        eventosRecyclerView.setAdapter(eventoAdapter);

        EditText searchEditText = findViewById(R.id.searchEditText);

        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence text, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence text, int start, int before, int count) {
                filtrarEventos(text.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        cargarEventos();
    }

    private void cargarEventos() {
        EventoApiInterface api = RetrofitClient.getClient().create(EventoApiInterface.class);

        Call<List<Evento>> call = api.getEventos();

        call.enqueue(new Callback<List<Evento>>() {
            @Override
            public void onResponse(Call<List<Evento>> call, Response<List<Evento>> response) {

                if (response.isSuccessful() && response.body() != null) {

                    eventos.clear();
                    eventos.addAll(response.body());

                    eventosFiltrados.clear();
                    eventosFiltrados.addAll(eventos);

                    eventoAdapter.notifyDataSetChanged();

                } else {

                    Toast.makeText(EventoListActivity.this,
                            "No se han podido cargar los eventos",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Evento>> call, Throwable t) {

                Toast.makeText(EventoListActivity.this,
                        "Error de conexión con la API",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void filtrarEventos(String texto) {
        eventosFiltrados.clear();

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

        eventoAdapter.notifyDataSetChanged();
    }
}