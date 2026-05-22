package com.svalero.appeventia.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.svalero.appeventia.R;
import com.svalero.appeventia.model.Artista;

import java.util.List;

public class ArtistaAdapter extends RecyclerView.Adapter<ArtistaAdapter.ArtistaHolder> {

    public interface ArtistaListener {
        void editarArtista(Artista artista);
        void eliminarArtista(Artista artista);
    }

    private final List<Artista> artistas;
    private final ArtistaListener listener;

    public ArtistaAdapter(List<Artista> artistas, ArtistaListener listener) {
        this.artistas = artistas;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ArtistaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_artista, parent, false);

        return new ArtistaHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArtistaHolder holder, int position) {
        Artista artista = artistas.get(position);

        holder.artistaNombreText.setText(artista.getNombreArtistico());
        holder.artistaNombreRealText.setText("Nombre real: " + artista.getNombreReal());
        holder.artistaGeneroText.setText("Género: " + artista.getGeneroMusical());
        holder.artistaFechaText.setText("Nacimiento: " + artista.getFechaNacimiento());
        holder.artistaCacheText.setText("Caché: " + artista.getCache() + " €");
        holder.artistaEventosText.setText("Eventos realizados: " + artista.getEventosRealizados());

        holder.artistaActivoText.setText(artista.isActivo() ? "Activo" : "Inactivo");

        holder.editArtistaButton.setOnClickListener(v -> listener.editarArtista(artista));
        holder.deleteArtistaButton.setOnClickListener(v -> listener.eliminarArtista(artista));
    }

    @Override
    public int getItemCount() {
        return artistas.size();
    }

    public static class ArtistaHolder extends RecyclerView.ViewHolder {

        TextView artistaNombreText;
        TextView artistaNombreRealText;
        TextView artistaGeneroText;
        TextView artistaFechaText;
        TextView artistaCacheText;
        TextView artistaEventosText;
        TextView artistaActivoText;
        Button editArtistaButton;
        Button deleteArtistaButton;

        public ArtistaHolder(@NonNull View itemView) {
            super(itemView);

            artistaNombreText = itemView.findViewById(R.id.artistaNombreText);
            artistaNombreRealText = itemView.findViewById(R.id.artistaNombreRealText);
            artistaGeneroText = itemView.findViewById(R.id.artistaGeneroText);
            artistaFechaText = itemView.findViewById(R.id.artistaFechaText);
            artistaCacheText = itemView.findViewById(R.id.artistaCacheText);
            artistaEventosText = itemView.findViewById(R.id.artistaEventosText);
            artistaActivoText = itemView.findViewById(R.id.artistaActivoText);
            editArtistaButton = itemView.findViewById(R.id.editArtistaButton);
            deleteArtistaButton = itemView.findViewById(R.id.deleteArtistaButton);
        }
    }
}