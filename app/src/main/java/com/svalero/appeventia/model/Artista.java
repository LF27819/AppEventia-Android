package com.svalero.appeventia.model;

public class Artista {

    private long id;
    private String nombreArtistico;
    private String generoMusical;

    public Artista() {
    }

    public long getId() {
        return id;
    }

    public String getNombreArtistico() {
        return nombreArtistico;
    }

    public String getGeneroMusical() {
        return generoMusical;
    }

    @Override
    public String toString() {
        return nombreArtistico;
    }
}