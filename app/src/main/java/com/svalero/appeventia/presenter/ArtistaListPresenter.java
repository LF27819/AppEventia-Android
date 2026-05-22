package com.svalero.appeventia.presenter;

import com.svalero.appeventia.api.EventoApiInterface;
import com.svalero.appeventia.api.RetrofitClient;
import com.svalero.appeventia.contract.ArtistaListContract;
import com.svalero.appeventia.model.Artista;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArtistaListPresenter implements ArtistaListContract.Presenter {

    private final ArtistaListContract.View view;
    private final List<Artista> artistas;

    public ArtistaListPresenter(ArtistaListContract.View view) {
        this.view = view;
        this.artistas = new ArrayList<>();
    }

    @Override
    public void cargarArtistas() {
        EventoApiInterface api = RetrofitClient.getClient().create(EventoApiInterface.class);

        api.getArtistas().enqueue(new Callback<List<Artista>>() {
            @Override
            public void onResponse(Call<List<Artista>> call, Response<List<Artista>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    artistas.clear();
                    artistas.addAll(response.body());
                    view.mostrarArtistas(artistas);
                } else {
                    view.mostrarError("No se han podido cargar los artistas");
                }
            }

            @Override
            public void onFailure(Call<List<Artista>> call, Throwable t) {
                view.mostrarError("Error de conexión con la API");
            }
        });
    }

    @Override
    public void filtrarArtistas(String texto) {
        List<Artista> artistasFiltrados = new ArrayList<>();

        if (texto == null || texto.trim().isEmpty()) {
            artistasFiltrados.addAll(artistas);
        } else {
            String textoBusqueda = texto.toLowerCase().trim();

            for (Artista artista : artistas) {
                boolean coincideNombreArtistico = artista.getNombreArtistico() != null &&
                        artista.getNombreArtistico().toLowerCase().contains(textoBusqueda);

                boolean coincideGenero = artista.getGeneroMusical() != null &&
                        artista.getGeneroMusical().toLowerCase().contains(textoBusqueda);

                if (coincideNombreArtistico || coincideGenero) {
                    artistasFiltrados.add(artista);
                }
            }
        }

        view.mostrarArtistas(artistasFiltrados);
    }

    @Override
    public void eliminarArtista(long id) {

        EventoApiInterface api = RetrofitClient.getClient().create(EventoApiInterface.class);

        api.deleteArtista(id).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    view.artistaEliminado();
                } else {
                    view.mostrarError("No se pudo eliminar el artista");
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                view.mostrarError("Error de conexión con la API");
            }
        });
    }
}