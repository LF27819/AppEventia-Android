package com.svalero.appeventia.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.svalero.appeventia.R;
import com.svalero.appeventia.adapter.EventoAdapter;
import com.svalero.appeventia.model.Evento;
import com.svalero.appeventia.model.Recinto;

import java.util.ArrayList;
import java.util.List;

public class EventoListActivity extends AppCompatActivity {

    private List<Evento> eventos;
    private EventoAdapter eventoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evento_list);

        eventos = new ArrayList<>();

        RecyclerView eventosRecyclerView = findViewById(R.id.eventosRecyclerView);
        eventosRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        eventoAdapter = new EventoAdapter(eventos);
        eventosRecyclerView.setAdapter(eventoAdapter);

        cargarEventosDePrueba();
    }

    private void cargarEventosDePrueba() {
        Recinto recinto = new Recinto();
        recinto.setNombre("Auditorio de Zaragoza");

        Evento evento1 = new Evento(
                "Concierto de rock",
                "Evento musical en directo",
                "2026-06-10",
                "21:00",
                25.50f,
                500,
                120,
                false,
                true,
                "Música"
        );
        evento1.setRecinto(recinto);

        Evento evento2 = new Evento(
                "Festival cultural",
                "Festival con actividades culturales",
                "2026-07-15",
                "18:30",
                12.00f,
                300,
                80,
                false,
                true,
                "Cultura"
        );
        evento2.setRecinto(recinto);

        eventos.add(evento1);
        eventos.add(evento2);

        eventoAdapter.notifyDataSetChanged();
    }
}