package com.svalero.appeventia.model;

public class Usuario {

    private long id;
    private String nombre;
    private String email;
    private String rol;

    public Usuario() {
    }

    public long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public String getRol() {
        return rol;
    }
}