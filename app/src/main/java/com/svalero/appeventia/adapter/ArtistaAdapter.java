package com.svalero.appeventia.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.svalero.appeventia.R;
import com.svalero.appeventia.model.Artista;

import java.util.List;

public class ArtistaAdapter extends RecyclerView.Adapter<ArtistaAdapter.ArtistaHolder> {

    private final List<Artista> artistas;

    public ArtistaAdapter(List<Artista> artistas) {
        this.artistas = artistas;
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
        holder.artistaGeneroText.setText(artista.getGeneroMusical());
    }

    @Override
    public int getItemCount() {
        return artistas.size();
    }

    public static class ArtistaHolder extends RecyclerView.ViewHolder {

        TextView artistaNombreText;
        TextView artistaGeneroText;

        public ArtistaHolder(@NonNull View itemView) {
            super(itemView);

            artistaNombreText = itemView.findViewById(R.id.artistaNombreText);
            artistaGeneroText = itemView.findViewById(R.id.artistaGeneroText);
        }
    }
}