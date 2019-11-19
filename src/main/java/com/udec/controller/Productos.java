/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udec.controller;

import com.udec.datos.Crud_Canciones;
import com.udec.modelo.CalcularCantidades;
import com.udec.utilitarios.U_Atistas;
import com.udec.utilitarios.U_Canciones;
import com.udec.utilitarios.U_Compras;
import com.udec.utilitarios.U_Discos;
import com.udec.utilitarios.Usuario;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
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
    private U_Compras compras;
    private int contadorProductos;
    private List<U_Canciones> listaCancionesUsuario;
    private List<Object> listaSeleccionados;
    private List<U_Compras> listaCarrito;
    
    public Productos() {
    }
    
    @PostConstruct
    public void init(){
        FacesContext context = FacesContext.getCurrentInstance();
         Usuario usuarioLogin = (Usuario) context.getExternalContext().getSessionMap().get("usuario");
         if(usuarioLogin==null)
             System.out.println("LLEGO AL NULL");
             //llamar SP para crear tabla temporal
             
        listaCancionesUsuario = new ArrayList<U_Canciones>();
        listaSeleccionados = new ArrayList<Object>();
        listaCancionesUsuario = Crud_Canciones.vistaCancionesUsuarios();
        contadorProductos = 0;
    }
    
    public void añadirAlCarrito(Object usuarios){
        if(usuarios instanceof U_Canciones){
            contadorProductos=1;
            U_Canciones aux = (U_Canciones) usuarios;
            if(contadorProductos==1)
                System.out.println("CONTADOR: " + contadorProductos);
                contadorProductos=0;
            System.out.println("CONTADOR: " + contadorProductos);
                //crear tabla temporal
            
        }
        else if(usuarios instanceof U_Atistas){

        }
        else if (usuarios instanceof U_Discos){
            
        }
        else{
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("No ha seleccionado nada"));
        }
    }
    
    public void añadirSeleccionados(){
        for (U_Canciones listaCancionesUsuario1 : listaCancionesUsuario) {
            if(listaCancionesUsuario1.isSeleccionados()){
                listaSeleccionados.add(listaCancionesUsuario1);
            }
        }
    }
    
    
    public int getContadorProductos() {
        return contadorProductos;
    }

    public void setContadorProductos(int contadorProductos) {
        this.contadorProductos = contadorProductos;
    }

    public List<U_Canciones> getListaCancionesUsuario() {
        return listaCancionesUsuario;
    }

    public void setListaCancionesUsuario(List<U_Canciones> listaCancionesUsuario) {
        this.listaCancionesUsuario = listaCancionesUsuario;
    }    
    
    
    public List<Object> getListaSeleccionados() {
        return listaSeleccionados;
    }

    public void setListaSeleccionados(List<Object> listaSeleccionados) {
        this.listaSeleccionados = listaSeleccionados;
    }   
}
