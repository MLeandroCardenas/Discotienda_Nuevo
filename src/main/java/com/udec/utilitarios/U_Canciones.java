/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udec.utilitarios;

/**
 *
 * @author michl
 */
public class U_Canciones {
    
    private int id;
    private String nombre_cancion;
    private int duracion;
    private int disco;
    private float precio;
    private int cantidad_stock;

    public U_Canciones() {
    }

    public U_Canciones(int id, String nombre_cancion, int duracion, int disco, float precio, int cantidad_stock) {
        this.id = id;
        this.nombre_cancion = nombre_cancion;
        this.duracion = duracion;
        this.disco = disco;
        this.precio = precio;
        this.cantidad_stock = cantidad_stock;
    }

    public U_Canciones(int id, String nombre_cancion, int disco) {
        this.id = id;
        this.nombre_cancion = nombre_cancion;
        this.disco = disco;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre_cancion() {
        return nombre_cancion;
    }

    public void setNombre_cancion(String nombre_cancion) {
        this.nombre_cancion = nombre_cancion;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public int getDisco() {
        return disco;
    }

    public void setDisco(int disco) {
        this.disco = disco;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getCantidad_stock() {
        return cantidad_stock;
    }

    public void setCantidad_stock(int cantidad_stock) {
        this.cantidad_stock = cantidad_stock;
    }
    
    
    
    
    
}
