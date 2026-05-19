package com.svalero.appeventia.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.svalero.appeventia.R;
import com.svalero.appeventia.model.Evento;

import java.util.List;

public class EventoAdapter extends RecyclerView.Adapter<EventoAdapter.EventoHolder> {

    private final List<Evento> eventos;

    public EventoAdapter(List<Evento> eventos) {
        this.eventos = eventos;
    }

    @NonNull
    @Override
    public EventoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_evento, parent, false);

        return new EventoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventoHolder holder, int position) {
        Evento evento = eventos.get(position);

        holder.eventoNombreText.setText(evento.getNombre());
        holder.eventoCategoriaText.setText(evento.getCategoria());
        holder.eventoFechaText.setText(evento.getFechaEvento() + " - " + evento.getHoraEvento());

        if (evento.getRecinto() != null) {
            holder.eventoRecintoText.setText(evento.getRecinto().getNombre());
        } else {
            holder.eventoRecintoText.setText("Recinto no disponible");
        }
    }

    @Override
    public int getItemCount() {
        return eventos.size();
    }

    public static class EventoHolder extends RecyclerView.ViewHolder {

        TextView eventoNombreText;
        TextView eventoCategoriaText;
        TextView eventoFechaText;
        TextView eventoRecintoText;

        public EventoHolder(@NonNull View itemView) {
            super(itemView);

            eventoNombreText = itemView.findViewById(R.id.eventoNombreText);
            eventoCategoriaText = itemView.findViewById(R.id.eventoCategoriaText);
            eventoFechaText = itemView.findViewById(R.id.eventoFechaText);
            eventoRecintoText = itemView.findViewById(R.id.eventoRecintoText);
        }
    }
}