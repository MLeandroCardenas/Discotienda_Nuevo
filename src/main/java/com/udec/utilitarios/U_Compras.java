/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udec.utilitarios;

import java.io.Serializable;
import java.sql.Date;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author michl
 */

@SessionScoped
public class U_Compras implements Serializable {
    
    private int id;
    private String nombre_Cliente;
    private String productos;
    private int cantidad;
    private float precio_total;
    private Date fecha_Compra;

    public U_Compras() {
    }

    public U_Compras(int id, String nombre_Cliente, String productos, int cantidad, float precio_total, Date fecha_Compra) {
        this.id = id;
        this.nombre_Cliente = nombre_Cliente;
        this.productos = productos;
        this.cantidad = cantidad;
        this.precio_total = precio_total;
        this.fecha_Compra = fecha_Compra;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre_Cliente() {
        return nombre_Cliente;
    }

    public void setNombre_Cliente(String nombre_Cliente) {
        this.nombre_Cliente = nombre_Cliente;
    }

    public String getProductos() {
        return productos;
    }

    public void setProductos(String productos) {
        this.productos = productos;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getPrecio_total() {
        return precio_total;
    }

    public void setPrecio_total(float precio_total) {
        this.precio_total = precio_total;
    }

    public Date getFecha_Compra() {
        return fecha_Compra;
    }

    public void setFecha_Compra(Date fecha_Compra) {
        this.fecha_Compra = fecha_Compra;
    }
    
    
}
