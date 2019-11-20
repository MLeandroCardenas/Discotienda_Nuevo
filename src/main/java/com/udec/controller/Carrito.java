/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udec.controller;

import com.udec.utilitarios.U_Canciones;
import com.udec.utilitarios.U_Carrito;
import com.udec.utilitarios.U_Compras;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.primefaces.event.RowEditEvent;

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
    private U_Carrito carrito;
    
    @Inject
    private Productos productos;
    
    @Inject
    private U_Compras compras;
    
    @Inject
    private U_Canciones canciones;
    
    private int cantidadNueva;
    
    private float precioTotal;
    
    private List<U_Carrito> listaAñadidos;
    
    
    public Carrito() {
    }
    
    @PostConstruct
    public void init(){
        listaAñadidos = new ArrayList<U_Carrito>();
        listaAñadidos = productos.getAux();
    }
    
    public String eliminarCarrito(U_Compras seleccion){
        return "/Usuario/carrito";
    }
    
    public void eliminarTodoElCarrito(){
        listaAñadidos.clear();
        productos.setContadorProductos(0);
    }
    
    public void editarCantidad(RowEditEvent event){
        for (U_Carrito listaAñadido : listaAñadidos){
            int idFila = carrito.getId();
            if(listaAñadido.getId()==idFila)
                listaAñadido.setCantidad(cantidadNueva);
        }
    }
    
    public void actualizarPrecioTotal(){
        precioTotal = precioTotal*cantidadNueva;
    }
    
    public void cancelar(RowEditEvent event){
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cancelado"));
    }

    

    public Productos getProductos() {
        return productos;
    }

    public void setProductos(Productos productos) {
        this.productos = productos;
    }

    public U_Carrito getCarrito() {
        return carrito;
    }

    public void setCarrito(U_Carrito carrito) {
        this.carrito = carrito;
    }

    public U_Compras getCompras() {
        return compras;
    }

    public void setCompras(U_Compras compras) {
        this.compras = compras;
    }

    public List<U_Carrito> getListaAñadidos() {
        return listaAñadidos;
    }

    public void setListaAñadidos(List<U_Carrito> listaAñadidos) {
        this.listaAñadidos = listaAñadidos;
    }

    public U_Canciones getCanciones() {
        return canciones;
    }

    public void setCanciones(U_Canciones canciones) {
        this.canciones = canciones;
    }

    public int getCantidadNueva() {
        return cantidadNueva;
    }

    public void setCantidadNueva(int cantidadNueva) {
        this.cantidadNueva = cantidadNueva;
    } 

    public float getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(float precioTotal) {
        this.precioTotal = precioTotal;
    }
    
    
}
