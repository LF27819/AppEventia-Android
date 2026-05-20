package com.svalero.appeventia.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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

    public interface OnFavoritoClickListener {
        void onDeleteFavorito(Favorito favorito);
    }

    private final List<Favorito> favoritos;
    private final Context context;
    private final OnFavoritoClickListener listener;

    public FavoritoAdapter(List<Favorito> favoritos, Context context, OnFavoritoClickListener listener) {
        this.favoritos = favoritos;
        this.context = context;
        this.listener = listener;
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
            listener.onDeleteFavorito(favoritoEliminar);
        });

        if (favorito.getComentario() == null || favorito.getComentario().isEmpty()) {
            holder.comentarioText.setText(R.string.no_comments);
        } else {
            holder.comentarioText.setText(favorito.getComentario());
        }

        holder.editCommentButton.setOnClickListener(v -> {
            EditText input = new EditText(context);
            input.setHint(context.getString(R.string.dialog_comment_hint));
            input.setText(favorito.getComentario());

            new AlertDialog.Builder(context)
                    .setTitle(R.string.dialog_edit_comment)
                    .setView(input)
                    .setPositiveButton(R.string.dialog_save, (dialog, which) -> {
                        String comentario = input.getText().toString();

                        favorito.setComentario(comentario);

                        AppDatabase db = DatabaseClient.getInstance(context);
                        db.favoritoDao().update(favorito);

                        notifyItemChanged(holder.getAdapterPosition());

                        Toast.makeText(context, R.string.toast_comment_updated, Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton(R.string.dialog_cancel, null)
                    .show();
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
        Button editCommentButton;
        TextView comentarioText;

        public FavoritoHolder(@NonNull View itemView) {
            super(itemView);

            favoritoNombreText = itemView.findViewById(R.id.favoritoNombreText);
            favoritoCategoriaText = itemView.findViewById(R.id.favoritoCategoriaText);
            favoritoFechaText = itemView.findViewById(R.id.favoritoFechaText);
            deleteFavoriteButton = itemView.findViewById(R.id.deleteFavoriteButton);
            editCommentButton = itemView.findViewById(R.id.editCommentButton);
            comentarioText = itemView.findViewById(R.id.comentarioText);
        }
    }
}