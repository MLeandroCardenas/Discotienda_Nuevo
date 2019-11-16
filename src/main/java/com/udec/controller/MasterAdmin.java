/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udec.controller;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

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
