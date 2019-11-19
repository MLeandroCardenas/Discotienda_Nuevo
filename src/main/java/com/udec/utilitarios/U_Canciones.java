/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udec.utilitarios;

import java.io.Serializable;
import javax.faces.view.ViewScoped;

/**
 *
 * @author michl
 */
@ViewScoped
public class U_Canciones implements Serializable {
    
    private int id;
    private String nombre_cancion;
    private int duracion;
    private String disco;
    private int id_disco;
    private float precio;
    private int cantidad_stock;
    private boolean seleccionados;

    public U_Canciones() {
    }

    public U_Canciones(int id, String nombre_cancion, int duracion, String disco, int id_disco, float precio, int cantidad_stock) {
        this.id = id;
        this.nombre_cancion = nombre_cancion;
        this.duracion = duracion;
        this.disco = disco;
        this.id_disco = id_disco;
        this.precio = precio;
        this.cantidad_stock = cantidad_stock;
    }
    
    public U_Canciones(int id, String nombre_cancion, int duracion, float precio, int cantidad_stock) {
        this.id=id;
        this.nombre_cancion=nombre_cancion;
        this.duracion=duracion;
        this.precio=precio;
        this.cantidad_stock=cantidad_stock;
    }
    
    public U_Canciones(int id, String nombre_cancion, int duracion, float precio, String disco) {
        this.id=id;
        this.nombre_cancion=nombre_cancion;
        this.duracion=duracion;
        this.precio=precio;
        this.disco=disco;
    }
    

    public U_Canciones(int id, String nombre_cancion, String disco) {
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

    public String getDisco() {
        return disco;
    }

    public void setDisco(String disco) {
        this.disco = disco;
    }

    public int getId_disco() {
        return id_disco;
    }

    public void setId_disco(int id_disco) {
        this.id_disco = id_disco;
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

    public boolean isSeleccionados() {
        return seleccionados;
    }

    public void setSeleccionados(boolean seleccionados) {
        this.seleccionados = seleccionados;
    }
    
    
}
