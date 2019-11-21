/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udec.controller;

import com.udec.datos.Compras;
import com.udec.datos.Crud_Canciones;
import com.udec.datos.Crud_Discos;
import com.udec.utilitarios.U_Canciones;
import com.udec.utilitarios.U_Carrito;
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
    private U_Canciones canciones;
    
    private int cantidadNueva;
    
   
    private List<U_Carrito> listaAñadidos;
    
    private float precioProducto;
    
    float precioTotal;
    
    
    public Carrito() {
    }
    
    @PostConstruct
    public void init(){
        listaAñadidos = new ArrayList<U_Carrito>();
        listaAñadidos = productos.getAux();
        precioTotal = 0;
        calcularPrecioTotal();
    }
    
    public void calcularPrecioTotal(){
        for (U_Carrito listaAñadido : listaAñadidos) {
            carrito.setPrecioTotal(listaAñadido.getPrecio()); 
        }
    }
    
    public void comprar(){
        Compras.registrarCompra(listaAñadidos,carrito);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("¡COMPRA EXITOSA!"));
        U_Carrito auxExistente = new U_Carrito();
        for (U_Carrito existente : listaAñadidos){
            int cantidades = Crud_Canciones.traerCantidadesCanciones(existente.getId());
            int resultado = cantidades-carrito.getCantidad();
            Compras.actualizarStockCanciones(resultado,existente.getId());
        }
        productos.getListaCanciones().clear();
        listaAñadidos.clear();
        this.carrito.setNombreCliente(null);
        productos.setContadorProductos(0);
        this.carrito.setPrecioTotal(0);
    }
    
    public void eliminarCarrito(U_Carrito seleccion){
        int contAux = productos.getContadorProductos();
        listaAñadidos.remove(seleccion);
        productos.getListaCanciones().remove(seleccion.getId());
        contAux--;
        productos.setContadorProductos(contAux);
        this.carrito.setPrecioTotal(0);
    }
    
    public void eliminarTodoElCarrito(){
        listaAñadidos.clear();
        productos.setContadorProductos(0);
        productos.getListaCanciones().clear();
        this.carrito.setPrecioTotal(0);
    }
    
    public void editarCantidad(RowEditEvent event){
        U_Carrito cambio = (U_Carrito) event.getObject();
        int cantidad_stock = Crud_Canciones.traerCantidadesCanciones(cambio.getId());
        int cantidad_StockDiscos = Crud_Discos.traerCantidadDiscos(cambio.getId());
        if(carrito.getCantidad()>cantidad_stock){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("NO HAY SUFICIENTES PRODUCTOS"));
        }else{
            cambio.setCantidad(carrito.getCantidad());
            precioProducto = cambio.getPrecio()*cambio.getCantidad();
            cambio.setPrecio(precioProducto);
        }
        if(carrito.getCantidad()>cantidad_StockDiscos){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("NO HAY SUFICIENTES PRODUCTOS"));
        }else{
            cambio.setCantidad(carrito.getCantidad());
            precioProducto = cambio.getPrecio()*cambio.getCantidad();
            cambio.setPrecio(precioProducto);
        }
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

    public float getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(float precioProducto) {
        this.precioProducto = precioProducto;
    }

    public float getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(float precioTotal) {
        this.precioTotal = precioTotal;
    }   
}
