package com.svalero.appeventia.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "favoritos")
public class Favorito {

    @PrimaryKey
    private long id;

    private String nombre;
    private String descripcion;
    private String fechaEvento;
    private String horaEvento;
    private float precioEntrada;
    private String categoria;
    private String recinto;

    public Favorito() {
    }

    public Favorito(long id, String nombre, String descripcion, String fechaEvento,
                    String horaEvento, float precioEntrada, String categoria, String recinto) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaEvento = fechaEvento;
        this.horaEvento = horaEvento;
        this.precioEntrada = precioEntrada;
        this.categoria = categoria;
        this.recinto = recinto;
    }

    public long getId() { return id; }
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public String getFechaEvento() { return fechaEvento; }
    public String getHoraEvento() { return horaEvento; }
    public float getPrecioEntrada() { return precioEntrada; }
    public String getCategoria() { return categoria; }
    public String getRecinto() { return recinto; }

    public void setId(long id) { this.id = id; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public void setFechaEvento(String fechaEvento) { this.fechaEvento = fechaEvento; }
    public void setHoraEvento(String horaEvento) { this.horaEvento = horaEvento; }
    public void setPrecioEntrada(float precioEntrada) { this.precioEntrada = precioEntrada; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    public void setRecinto(String recinto) { this.recinto = recinto; }
}