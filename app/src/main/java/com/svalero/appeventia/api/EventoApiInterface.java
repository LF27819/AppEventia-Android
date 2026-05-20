package com.svalero.appeventia.api;

import com.svalero.appeventia.model.Evento;
import com.svalero.appeventia.model.Artista;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface EventoApiInterface {

    @GET("eventos")
    Call<List<Evento>> getEventos();

    @GET("artistas")
    Call<List<Artista>> getArtistas();
}