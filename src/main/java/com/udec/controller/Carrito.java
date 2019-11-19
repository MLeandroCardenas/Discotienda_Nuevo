/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udec.controller;

import com.udec.utilitarios.U_Compras;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

/**
 *
 * @author michl
 */
@Named
@SessionScoped
public class Carrito implements Serializable {

    /**
     * Creates a new instance of Carrito
     */
    
    @Inject
    private U_Compras compras;
    
    @Inject
    private Productos productos;
    
    private List<Object> listaAñadidos;
    
    
    public Carrito() {
    }
    
    @PostConstruct
    public void init(){
        
    }
    
    public String eliminarCarrito(U_Compras seleccion){
        return "/Usuario/carrito";
    }

    public U_Compras getCompras() {
        return compras;
    }

    public void setCompras(U_Compras compras) {
        this.compras = compras;
    }

    public Productos getProductos() {
        return productos;
    }

    public void setProductos(Productos productos) {
        this.productos = productos;
    }
    
    
}
