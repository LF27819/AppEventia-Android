package com.svalero.appeventia.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.svalero.appeventia.R;
import com.svalero.appeventia.model.Reserva;

import java.util.List;

public class ReservaAdapter extends RecyclerView.Adapter<ReservaAdapter.ReservaHolder> {

    private final List<Reserva> reservas;

    public ReservaAdapter(List<Reserva> reservas) {
        this.reservas = reservas;
    }

    @NonNull
    @Override
    public ReservaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_reserva, parent, false);

        return new ReservaHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReservaHolder holder, int position) {
        Reserva reserva = reservas.get(position);

        holder.reservaCodigoText.setText("Código: " + reserva.getCodigoReserva());

        if (reserva.getEvento() != null) {
            holder.reservaEventoText.setText("Evento: " + reserva.getEvento().getNombre());
        } else {
            holder.reservaEventoText.setText("Evento no disponible");
        }

        if (reserva.getUsuario() != null) {
            holder.reservaUsuarioText.setText("Usuario: " + reserva.getUsuario().getNombre());
        } else {
            holder.reservaUsuarioText.setText("Usuario no disponible");
        }

        holder.reservaEntradasText.setText("Entradas: " + reserva.getCantidadEntradas());
        holder.reservaPrecioText.setText("Total: " + reserva.getPrecioTotal() + " €");

        String fechaBonita = reserva.getFechaReserva();
        if (fechaBonita != null && fechaBonita.contains("T")) {
            fechaBonita = fechaBonita.split("T")[0];
        }

        holder.reservaFechaText.setText("Fecha: " + fechaBonita);

        if (reserva.isConfirmada()) {
            holder.reservaEstadoText.setText("Estado: Confirmada");
        } else {
            holder.reservaEstadoText.setText("Estado: Pendiente");
        }
    }

    @Override
    public int getItemCount() {
        return reservas.size();
    }

    public static class ReservaHolder extends RecyclerView.ViewHolder {

        TextView reservaCodigoText;
        TextView reservaEventoText;
        TextView reservaFechaText;
        TextView reservaPrecioText;
        TextView reservaUsuarioText;
        TextView reservaEntradasText;
        TextView reservaEstadoText;

        public ReservaHolder(@NonNull View itemView) {
            super(itemView);

            reservaCodigoText = itemView.findViewById(R.id.reservaCodigoText);
            reservaEventoText = itemView.findViewById(R.id.reservaEventoText);
            reservaFechaText = itemView.findViewById(R.id.reservaFechaText);
            reservaPrecioText = itemView.findViewById(R.id.reservaPrecioText);
            reservaUsuarioText = itemView.findViewById(R.id.reservaUsuarioText);
            reservaEntradasText = itemView.findViewById(R.id.reservaEntradasText);
            reservaEstadoText = itemView.findViewById(R.id.reservaEstadoText);
        }
    }
}