package com.svalero.appeventia.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.svalero.appeventia.R;
import com.svalero.appeventia.database.AppDatabase;
import com.svalero.appeventia.database.Favorito;
import com.svalero.appeventia.utils.DatabaseClient;

import java.util.List;

public class FavoritoAdapter extends RecyclerView.Adapter<FavoritoAdapter.FavoritoHolder> {

    private final List<Favorito> favoritos;
    private final Context context;

    public FavoritoAdapter(List<Favorito> favoritos, Context context) {
        this.favoritos = favoritos;
        this.context = context;
    }

    @NonNull
    @Override
    public FavoritoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_favorito, parent, false);

        return new FavoritoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoritoHolder holder, int position) {
        Favorito favorito = favoritos.get(position);

        holder.favoritoNombreText.setText(favorito.getNombre());
        holder.favoritoCategoriaText.setText(favorito.getCategoria());
        holder.favoritoFechaText.setText(favorito.getFechaEvento() + " - " + favorito.getHoraEvento());

        holder.deleteFavoriteButton.setOnClickListener(v -> {
            int adapterPosition = holder.getAdapterPosition();

            if (adapterPosition == RecyclerView.NO_POSITION) {
                return;
            }

            Favorito favoritoEliminar = favoritos.get(adapterPosition);

            AppDatabase db = DatabaseClient.getInstance(context);
            db.favoritoDao().delete(favoritoEliminar);

            favoritos.remove(adapterPosition);
            notifyItemRemoved(adapterPosition);

            Toast.makeText(context, "Favorito eliminado", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return favoritos.size();
    }

    public static class FavoritoHolder extends RecyclerView.ViewHolder {

        TextView favoritoNombreText;
        TextView favoritoCategoriaText;
        TextView favoritoFechaText;
        Button deleteFavoriteButton;

        public FavoritoHolder(@NonNull View itemView) {
            super(itemView);

            favoritoNombreText = itemView.findViewById(R.id.favoritoNombreText);
            favoritoCategoriaText = itemView.findViewById(R.id.favoritoCategoriaText);
            favoritoFechaText = itemView.findViewById(R.id.favoritoFechaText);
            deleteFavoriteButton = itemView.findViewById(R.id.deleteFavoriteButton);
        }
    }
}