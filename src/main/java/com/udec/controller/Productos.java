/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udec.controller;

import com.udec.datos.Crud_Canciones;
import com.udec.utilitarios.U_Canciones;
import com.udec.utilitarios.U_Carrito;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author michl
 */
@Named
@SessionScoped
public class Productos implements Serializable {

    /**
     * Creates a new instance of Productos
     */
    
    @Inject
    private U_Carrito compras;
    
    @Inject
    private U_Canciones canciones;

    private List<U_Canciones> listaCancionesUsuario;
    private List<U_Canciones> listaSeleccionados;
    
    private List<U_Carrito> aux;
   
    private HashMap<Integer,U_Carrito> listaCanciones;
    
    private int contadorProductos;
    
    public Productos() {
    }
    
    @PostConstruct
    public void init(){
        aux = new ArrayList<U_Carrito>();
        listaCancionesUsuario = new ArrayList<U_Canciones>();
        listaCancionesUsuario = Crud_Canciones.vistaCancionesUsuarios();
        listaSeleccionados = new ArrayList<U_Canciones>();
        listaCanciones = new HashMap<Integer,U_Carrito>();
        contadorProductos = 0;
    }
    
    public void añadirAlCarrito(U_Canciones usuarios){
        if (listaCanciones.containsKey(usuarios.getId())==true){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("YA ESTA EN CARRITO"));        
        }else{
            listaCanciones.put(usuarios.getId(), new U_Carrito(usuarios.getId(), usuarios.getNombre_cancion(), usuarios.getPrecio(), 1));
            aux.add(listaCanciones.get(usuarios.getId()));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("AÑADIDO AL CARRITO"));
            contadorProductos++;
        }
    }
    
    
    public void añadirSeleccionados(){
        for (U_Canciones auxLista : listaCancionesUsuario) {
            if(auxLista.isSeleccionados()){
                if(listaCanciones.containsKey(auxLista.getId())==true){
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("YA ESTA EN CARRITO")); 
                }else{
                    listaCanciones.put(auxLista.getId(),new U_Carrito(auxLista.getId(), auxLista.getNombre_cancion(),auxLista.getPrecio(),1));
                    aux.add(listaCanciones.get(auxLista.getId()));
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("AÑADIDOS AL CARRITO"));
                    contadorProductos++;
                }
            }
        }
    }
    
    
    public int getContadorProductos() {
        return contadorProductos;
    }

    public void setContadorProductos(int contadorProductos) {
        this.contadorProductos = contadorProductos;
    }

    public U_Carrito getCompras() {
        return compras;
    }

    public void setCompras(U_Carrito compras) {
        this.compras = compras;
    }

    public U_Canciones getCanciones() {
        return canciones;
    }

    public void setCanciones(U_Canciones canciones) {
        this.canciones = canciones;
    }

    public List<U_Canciones> getListaCancionesUsuario() {
        return listaCancionesUsuario;
    }

    public void setListaCancionesUsuario(List<U_Canciones> listaCancionesUsuario) {
        this.listaCancionesUsuario = listaCancionesUsuario;
    }

    public List<U_Carrito> getAux() {
        return aux;
    }

    public void setAux(List<U_Carrito> aux) {
        this.aux = aux;
    }
    
    public HashMap<Integer, U_Carrito> getListaCanciones() {
        return listaCanciones;
    }

    public void setListaCanciones(HashMap<Integer, U_Carrito> listaCanciones) {
        this.listaCanciones = listaCanciones;
    }
    
    
}
