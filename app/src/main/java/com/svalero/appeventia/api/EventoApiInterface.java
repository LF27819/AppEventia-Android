package com.svalero.appeventia.api;

import com.svalero.appeventia.model.Evento;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface EventoApiInterface {

    @GET("eventos")
    Call<List<Evento>> getEventos();
}