package com.svalero.appeventia.model;

public class Artista {

    private long id;
    private String nombreArtistico;
    private String nombreReal;
    private String generoMusical;
    private String fechaNacimiento;
    private boolean activo;
    private float cache;
    private int eventosRealizados;

    public Artista() {
    }

    public long getId() {
        return id;
    }

    public String getNombreArtistico() {
        return nombreArtistico;
    }

    public String getNombreReal() {
        return nombreReal;
    }

    public String getGeneroMusical() {
        return generoMusical;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public boolean isActivo() {
        return activo;
    }

    public float getCache() {
        return cache;
    }

    public int getEventosRealizados() {
        return eventosRealizados;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setNombreArtistico(String nombreArtistico) {
        this.nombreArtistico = nombreArtistico;
    }

    public void setNombreReal(String nombreReal) {
        this.nombreReal = nombreReal;
    }

    public void setGeneroMusical(String generoMusical) {
        this.generoMusical = generoMusical;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public void setCache(float cache) {
        this.cache = cache;
    }

    public void setEventosRealizados(int eventosRealizados) {
        this.eventosRealizados = eventosRealizados;
    }

    @Override
    public String toString() {
        return nombreArtistico;
    }
}