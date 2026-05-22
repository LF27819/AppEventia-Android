package com.svalero.appeventia.api;

import com.svalero.appeventia.model.Artista;
import com.svalero.appeventia.model.Evento;
import com.svalero.appeventia.model.Reserva;
import com.svalero.appeventia.model.Recinto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface EventoApiInterface {

    // GET
    @GET("eventos")
    Call<List<Evento>> getEventos();

    @GET("artistas")
    Call<List<Artista>> getArtistas();

    @GET("reservas")
    Call<List<Reserva>> getReservas();

    @GET("recintos")
    Call<List<Recinto>> getRecintos();

    // POST
    @POST("eventos")
    Call<Evento> addEvento(@Body Evento evento);

    @POST("artistas")
    Call<Artista> addArtista(@Body Artista artista);

    // PUT
    @PUT("eventos/{id}")
    Call<Evento> updateEvento(@Path("id") long id, @Body Evento evento);

    @PUT("artistas/{id}")
    Call<Artista> updateArtista(@Path("id") long id, @Body Artista artista);

    // DELETE
    @DELETE("eventos/{id}")
    Call<Void> deleteEvento(@Path("id") long id);

    @DELETE("artistas/{id}")
    Call<Void> deleteArtista(@Path("id") long id);
}