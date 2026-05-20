package com.svalero.appeventia.model;

public class Reserva {

    private long id;
    private String fechaReserva;
    private int cantidadEntradas;
    private float precioTotal;
    private String metodoPago;
    private String codigoReserva;
    private boolean confirmada;
    private Usuario usuario;
    private Evento evento;

    public Reserva() {}

    public long getId() { return id; }
    public String getFechaReserva() { return fechaReserva; }
    public int getCantidadEntradas() { return cantidadEntradas; }
    public float getPrecioTotal() { return precioTotal; }
    public String getMetodoPago() { return metodoPago; }
    public String getCodigoReserva() { return codigoReserva; }
    public boolean isConfirmada() { return confirmada; }
    public Usuario getUsuario() { return usuario; }
    public Evento getEvento() { return evento; }
}