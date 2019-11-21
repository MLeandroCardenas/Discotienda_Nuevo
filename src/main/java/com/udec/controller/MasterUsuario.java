/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udec.controller;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

/**
 *
 * @author michl
 */
@Named
@SessionScoped
public class MasterUsuario implements Serializable {

    /**
     * Creates a new instance of MasterUsuario
     */
    
    private String localidad;
    private String newLanguage;
    
    
    public MasterUsuario() {
    }
    
    @PostConstruct
    public void init(){
    }

    private static final Map<String, Object> listaPaises;
       
    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }  

    public Map<String, Object> getListaPaises() {
        return listaPaises;
    }
  
    
    static {
        listaPaises = new LinkedHashMap<>();        
        Locale espanol = new Locale("ES");
        listaPaises.put("Español", espanol);
        listaPaises.put("Ingles", Locale.ENGLISH);      
    }    
        
    public void  localidadChanged(ValueChangeEvent e) {
        newLanguage = e.getNewValue().toString();
        for (Map.Entry<String, Object> entrySet : listaPaises.entrySet()) {
            if(entrySet.getValue().toString().equals(newLanguage)) {
                FacesContext.getCurrentInstance().getViewRoot()
                        .setLocale((Locale)entrySet.getValue());
                // FacesContext context = FacesContext.getCurrentInstance();
                 //context.getExternalContext().getSessionMap().put("idioma",entrySet.getValue());
            }  
        }
    }
}
