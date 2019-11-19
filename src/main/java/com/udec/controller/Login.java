/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udec.controller;

import com.udec.modelo.Sesion;
import com.udec.utilitarios.Usuario;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author michl
 */
@Named
@ViewScoped
public class Login implements Serializable{

    
    private String usuario,clave;
    /**
     * Creates a new instance of Login
     */
    public Login() {
    }
    
    public String iniciarSession() {
        String redireccion = null;
        Sesion sesion = new Sesion();
        Usuario usuarioLogin = sesion.validarSesion(usuario, clave);
        FacesContext context = FacesContext.getCurrentInstance();
        if (usuarioLogin != null) {
            context.getExternalContext().getSessionMap().put("usuario", usuarioLogin);
            if (usuarioLogin.getClave().equals("123")&&usuarioLogin.getUsuario().equals("admin")) {
                redireccion = "/Administrador/cantantes?faces-redirect=true";
            } else {
                redireccion = "/Usuario/login?faces-redirect=true";
            }
        }else{
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Acceso denegado", "Usuario y/o clave incorrecta"));
        }
        return redireccion;
    }
    
    public String cerrarSesion(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/faces/productos.xhtml";
    }

    public void validarPermiso() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest req = (HttpServletRequest) context.getExternalContext().getRequest();
        Usuario usuarioLogin = (Usuario) context.getExternalContext().getSessionMap().get("usuario");
        if (!req.getRequestURI().contains("/faces/Login.xhtml")) {

            if (req.getRequestURI().contains("/Administrador/")) {
                if (usuarioLogin == null) {
                    try {
                        context.getExternalContext().redirect("./../login.xhtml");
                    } catch (IOException ex) {
                        Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
}
