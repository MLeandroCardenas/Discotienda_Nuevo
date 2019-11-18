/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udec.controller;

import com.udec.datos.Crud_Artistas;
import com.udec.utilitarios.U_Atistas;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author michl
 */
@Named
@ViewScoped
public class Cantantes implements Serializable {

    @Inject
    private U_Atistas cantantes;
    
    private List<U_Atistas> listaArtistas;
    private Map<String,String> nacionalidades;
   
    /**
     * Creates a new instance of Cantantes
     */
    public Cantantes() {
    }
    
    @PostConstruct
    public void init(){
        nacionalidades = new HashMap<String,String>();
        listaArtistas = new ArrayList<U_Atistas>();
        nacionalidades.put("Colombiano","Colombiano");
        nacionalidades.put("Venezolano","Venezolano");
        nacionalidades.put("Peruano","Peruano");
        nacionalidades.put("Mexicano","Mexicano");
        nacionalidades.put("Puerto riqueño","Puerto Riqueño");
        nacionalidades.put("Argentino","Argentino");
        nacionalidades.put("Español","Español");
        nacionalidades.put("Estadounidense","Estadounidense");
        nacionalidades.put("Ingles","Ingles");
        nacionalidades.put("Belga","Belga");
        listaArtistas = Crud_Artistas.TraerArtistas();
    }
    
    public void insertarCantantes(){
        Crud_Artistas.insertarArtistas(cantantes);
        this.cantantes.setNombre(null);
        this.cantantes.setApellido(null);
        this.cantantes.setFecha_nacimiento(null);
        this.cantantes.setNacionalidad(null);
        listaArtistas = Crud_Artistas.TraerArtistas();
    }
    
    public boolean enablePanel(){
        if(listaArtistas.isEmpty()){
            return false;
        }
        else
            return true;
    }
    
    public String eliminarCantante(U_Atistas seleccion){
       int nombre =  Crud_Artistas.eliminarArtista(seleccion.getId());
        return "/Administrador/cantantes";
    }
    
    public void editarArtista(RowEditEvent event){
        U_Atistas cambio = (U_Atistas) event.getObject();
        cambio.setNombre(cantantes.getNombre());
        cambio.setApellido(cantantes.getApellido());
        cambio.setFecha_nacimiento(cantantes.getFecha_nacimiento());
        cambio.setNacionalidad(cantantes.getNacionalidad());

        Crud_Artistas.editarArtista(cambio);
    }

    public void cancelar(RowEditEvent event){
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cancelado"));
    }

    public List<U_Atistas> getListaArtistas() {
        return listaArtistas;
    }

    public void setListaArtistas(List<U_Atistas> listaArtistas) {
        this.listaArtistas = listaArtistas;
    }

    public U_Atistas getCantantes() {
        return cantantes;
    }

    public void setCantantes(U_Atistas cantantes) {
        this.cantantes = cantantes;
    }

    public Map<String, String> getNacionalidades() {
        return nacionalidades;
    }

    public void setNacionalidades(Map<String, String> nacionalidades) {
        this.nacionalidades = nacionalidades;
    }
}
