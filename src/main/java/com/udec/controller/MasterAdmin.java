/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udec.controller;

import com.udec.utilitarios.Usuario;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

/**
 *
 * @author michl
 */
@Named(value = "masterAdmin")
@SessionScoped
public class MasterAdmin implements Serializable {

    /**
     * Creates a new instance of MasterAdmin
     */
    public MasterAdmin() {
    }
    
    @PostConstruct
    public void init(){
        verificarSession();
    }
    
    public void verificarSession(){
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            Usuario estado = (Usuario) context.getExternalContext().getSessionMap().get("usuario");
            if(estado==null){
                context.getExternalContext().redirect("Permiso.xhtml");
            }
        } catch (Exception e) {
            
        }
    }
    
    public String artistas(){
        return"/Administrador/cantantes?faces-redirect=true";
    }
    
    public String discos(){
        return "/Administrador/discos?faces-redirect=true";
    }
    
    public String canciones(){
        return "/Administrador/canciones?faces-redirect=true";
    }
    
    public String reportes(){
        return "/Administrador/reportes?faces-redirect=true";
    }
    
    public String salir(){
        return "/Usuario/productos?faces-redirect=true";
    }
    
}
