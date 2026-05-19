package com.svalero.appeventia.model;

public class Evento {

    private long id;
    private String nombre;
    private String descripcion;
    private String fechaEvento;
    private String horaEvento;
    private float precioEntrada;
    private int aforoMaximo;
    private int entradasDisponibles;
    private boolean cancelado;
    private boolean presencial;
    private String categoria;

    private Usuario usuario;
    private Artista artista;
    private Recinto recinto;

    public Evento() {
    }

    public Evento(String nombre, String descripcion, String fechaEvento, String horaEvento,
                  float precioEntrada, int aforoMaximo, int entradasDisponibles,
                  boolean cancelado, boolean presencial, String categoria) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaEvento = fechaEvento;
        this.horaEvento = horaEvento;
        this.precioEntrada = precioEntrada;
        this.aforoMaximo = aforoMaximo;
        this.entradasDisponibles = entradasDisponibles;
        this.cancelado = cancelado;
        this.presencial = presencial;
        this.categoria = categoria;
    }

    public long getId() { return id; }
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public String getFechaEvento() { return fechaEvento; }
    public String getHoraEvento() { return horaEvento; }
    public float getPrecioEntrada() { return precioEntrada; }
    public int getAforoMaximo() { return aforoMaximo; }
    public int getEntradasDisponibles() { return entradasDisponibles; }
    public boolean isCancelado() { return cancelado; }
    public boolean isPresencial() { return presencial; }
    public String getCategoria() { return categoria; }
    public Usuario getUsuario() { return usuario; }
    public Artista getArtista() { return artista; }
    public Recinto getRecinto() { return recinto; }

    public void setId(long id) { this.id = id; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public void setFechaEvento(String fechaEvento) { this.fechaEvento = fechaEvento; }
    public void setHoraEvento(String horaEvento) { this.horaEvento = horaEvento; }
    public void setPrecioEntrada(float precioEntrada) { this.precioEntrada = precioEntrada; }
    public void setAforoMaximo(int aforoMaximo) { this.aforoMaximo = aforoMaximo; }
    public void setEntradasDisponibles(int entradasDisponibles) { this.entradasDisponibles = entradasDisponibles; }
    public void setCancelado(boolean cancelado) { this.cancelado = cancelado; }
    public void setPresencial(boolean presencial) { this.presencial = presencial; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
    public void setArtista(Artista artista) { this.artista = artista; }
    public void setRecinto(Recinto recinto) { this.recinto = recinto; }
}