package com.svalero.appeventia.model;

public class Recinto {

    private long id;
    private String nombre;
    private String direccion;
    private String ciudad;
    private double latitud;
    private double longitud;

    public Recinto() {
    }


    public long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }
    public String getDireccion() {
        return direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public double getLatitud() { return latitud; }
    public double getLongitud() { return longitud; }

    public void setLatitud(double latitud) { this.latitud = latitud; }
    public void setLongitud(double longitud) { this.longitud = longitud; }
}