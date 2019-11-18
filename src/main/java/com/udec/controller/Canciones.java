/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udec.controller;

import com.udec.datos.Crud_Artistas;
import com.udec.datos.Crud_Canciones;
import com.udec.datos.Crud_Discos;
import com.udec.utilitarios.U_Atistas;
import com.udec.utilitarios.U_Canciones;
import com.udec.utilitarios.U_Discos;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author michl
 */
@Named
@ViewScoped
public class Canciones implements Serializable{

    
    @Inject
    private U_Canciones canciones;
    private Map<String,Integer> mapaDiscos;
    private List<U_Discos> listaDiscos;
    private List<U_Canciones> listaCanciones;
    private int aux,cantidades;
    
    /**
     * Creates a new instance of Canciones
     */
    public Canciones(){
    }
    
    @PostConstruct
    public void init(){
        mapaDiscos = new HashMap<String,Integer>();
        listaDiscos = new ArrayList<U_Discos>();
        listaCanciones = new ArrayList<U_Canciones>();
        listaDiscos = Crud_Canciones.traerIdDiscos();
        for (U_Discos listaDisco : listaDiscos) {
            Integer id = listaDisco.getId();
            String nombre = listaDisco.getAlbum();
            mapaDiscos.put(nombre, id);
        }
    }
    
    public void cambio(ValueChangeEvent e) {
        aux = Integer.parseInt(e.getNewValue().toString());
        cantidades = Crud_Canciones.traerCantidades(aux);
        canciones.setCantidad_stock(cantidades);
    }
    
    public void cambioDos(ValueChangeEvent e) {
        aux = Integer.parseInt(e.getNewValue().toString());
        listaCanciones = Crud_Canciones.vistaCanciones(aux);
    }
    
     public void insertarCanciones(){
         
       int i = Crud_Canciones.insertarCanciones(canciones);
       FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Agregado exitosamente"));
        this.canciones.setDisco(null);
        this.canciones.setDuracion(0);
        this.canciones.setCantidad_stock(0);
        this.canciones.setNombre_cancion(null);
        this.canciones.setPrecio(0);
    }
     
    public boolean enablePanel(){
        if(listaCanciones.isEmpty())
            return false;
        else 
            return true;
    }
    
    public void editarCancion(RowEditEvent event){
        U_Canciones cambio = (U_Canciones) event.getObject();
        cambio.setNombre_cancion(canciones.getNombre_cancion());
        cambio.setDuracion(canciones.getDuracion());
        cambio.setPrecio(canciones.getPrecio());
        cambio.setCantidad_stock(canciones.getCantidad_stock());
        Crud_Canciones.editarCancion(cambio);
    }
    
    public void cancelar(RowEditEvent event){
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cancelado"));
    }
    
    public String eliminarCancion(U_Canciones seleccion){
       int nombre =  Crud_Canciones.eliminarCancion(seleccion.getId());
        return "/Administrador/canciones";
    }

    public U_Canciones getCanciones() {
        return canciones;
    }

    public void setCanciones(U_Canciones canciones) {
        this.canciones = canciones;
    }

    public Map<String, Integer> getMapaDiscos() {
        return mapaDiscos;
    }

    public void setMapaDiscos(Map<String, Integer> mapaDiscos) {
        this.mapaDiscos = mapaDiscos;
    }

    public List<U_Discos> getListaDiscos() {
        return listaDiscos;
    }

    public void setListaDiscos(List<U_Discos> listaDiscos) {
        this.listaDiscos = listaDiscos;
    }

    public int getAux() {
        return aux;
    }

    public void setAux(int aux) {
        this.aux = aux;
    }

    public int getCantidades() {
        return cantidades;
    }

    public void setCantidades(int cantidades) {
        this.cantidades = cantidades;
    }

    public List<U_Canciones> getListaCanciones() {
        return listaCanciones;
    }

    public void setListaCanciones(List<U_Canciones> listaCanciones) {
        this.listaCanciones = listaCanciones;
    }
    
    
    
}
