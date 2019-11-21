/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udec.controller;

import com.udec.datos.Crud_Canciones;
import com.udec.datos.Crud_Discos;
import com.udec.utilitarios.U_Canciones;
import com.udec.utilitarios.U_Carrito;
import com.udec.utilitarios.U_Discos;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
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
    
    @Inject
    private U_Discos discos;

    private List<U_Canciones> listaCancionesUsuario;
    
    private List<U_Discos>listaDiscosUsuario;
    
    private List<U_Canciones>listaCancionesDiscos;
    
    private List<U_Canciones> listaSeleccionados;
    
    private List<U_Carrito> aux;
   
    private HashMap<Integer,U_Carrito> listaCanciones;
    
    private int contadorProductos;
    
    private int valorDisco,valorCanciones;
    
    public Productos() {
    }
    
    @PostConstruct
    public void init(){
        aux = new ArrayList<U_Carrito>();
        listaDiscosUsuario = new ArrayList<U_Discos>();
        listaCancionesDiscos = new ArrayList<U_Canciones>();
        listaSeleccionados = new ArrayList<U_Canciones>();
        listaCanciones = new HashMap<Integer,U_Carrito>();
        contadorProductos = 0;
        
        listaCancionesUsuario = new ArrayList<U_Canciones>();
        listaCancionesUsuario = Crud_Canciones.vistaCancionesUsuarios();
    }
    

    public boolean panelDiscos(){
        if(listaDiscosUsuario.isEmpty()){
            return false;
        }
        else{
            return true;
        }
    }
    
    public boolean panelCanciones(){
        if(listaCancionesDiscos.isEmpty()){
            return false;
        }
        else{
            return true;
        }
    }
    
    public void cambioDiscos(ValueChangeEvent e){
        valorDisco = Integer.parseInt(e.getNewValue().toString());
        listaDiscosUsuario = Crud_Discos.vistaDiscos(valorDisco);
    }
    
    public void cambioCancionesDisco(ValueChangeEvent e){
        valorCanciones = Integer.parseInt(e.getNewValue().toString());
        listaCancionesDiscos = Crud_Canciones.vistaCanciones(valorCanciones);
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
    
    public void añadirDiscosCarrito(U_Discos discosCarrito){
        if (listaCanciones.containsKey(discosCarrito.getId())==true){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("YA ESTA EN CARRITO"));  
        }else{
            listaCanciones.put(discosCarrito.getId(),new U_Carrito(discosCarrito.getId(),discosCarrito.getAlbum(),discosCarrito.getPrecio(),1));
            aux.add(listaCanciones.get(discosCarrito.getId()));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("AÑADIDO AL CARRITO"));
            contadorProductos++;
        }
    }
    
    public void añadirCancionesDiscos(U_Canciones auxCanciones){
        if (listaCanciones.containsKey(auxCanciones.getId())==true){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("YA ESTA EN CARRITO"));  
        }else{
            listaCanciones.put(auxCanciones.getId(),new U_Carrito(auxCanciones.getId(),auxCanciones.getNombre_cancion(),auxCanciones.getPrecio(),1));
            aux.add(listaCanciones.get(auxCanciones.getId()));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("AÑADIDO AL CARRITO"));
            contadorProductos++;
        }
    }
    
    

    
    public void añadirDiscosSeleccionados(){
         for (U_Discos auxLista : listaDiscosUsuario) {
             if(auxLista.isSeleccionados()){
                 if(listaCanciones.containsKey(auxLista.getId())==true){
                     FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("YA ESTA EN CARRITO")); 
                 }else{
                    listaCanciones.put(auxLista.getId(),new U_Carrito(auxLista.getId(), auxLista.getAlbum(), auxLista.getPrecio(),1));
                    aux.add(listaCanciones.get(auxLista.getId()));
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("AÑADIDOS AL CARRITO"));
                    contadorProductos++;
                 }
             }
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
    
    public void añadirCancionesDiscoSeleccionadas(){
        for (U_Canciones auxLista : listaCancionesDiscos) {
             if(auxLista.isSeleccionados()){
                 if(listaCanciones.containsKey(auxLista.getId())==true){
                     FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("YA ESTA EN CARRITO")); 
                 }else{
                    listaCanciones.put(auxLista.getId(),new U_Carrito(auxLista.getId(), auxLista.getNombre_cancion(), auxLista.getPrecio(),1));
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

    public List<U_Discos> getListaDiscosUsuario() {
        return listaDiscosUsuario;
    }

    public void setListaDiscosUsuario(List<U_Discos> listaDiscosUsuario) {
        this.listaDiscosUsuario = listaDiscosUsuario;
    }

    public int getValorDisco() {
        return valorDisco;
    }

    public void setValorDisco(int valorDisco) {
        this.valorDisco = valorDisco;
    }

    public int getValorCanciones() {
        return valorCanciones;
    }

    public void setValorCanciones(int valorCanciones) {
        this.valorCanciones = valorCanciones;
    }

    public U_Discos getDiscos() {
        return discos;
    }

    public void setDiscos(U_Discos discos) {
        this.discos = discos;
    }

    public List<U_Canciones> getListaCancionesDiscos() {
        return listaCancionesDiscos;
    }

    public void setListaCancionesDiscos(List<U_Canciones> listaCancionesDiscos) {
        this.listaCancionesDiscos = listaCancionesDiscos;
    }
    
    
    
    
    
    
    
    
}
