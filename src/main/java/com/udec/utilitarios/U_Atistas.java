/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udec.utilitarios;

import java.io.Serializable;
import java.sql.Date;
import javax.faces.view.ViewScoped;

/**
 *
 * @author michl
 */
@ViewScoped
public class U_Atistas implements Serializable{
    
    private int id;
    private String nombre;
    private String apellido;
    private Object fecha_nacimiento;
    private String nacionalidad;

    public U_Atistas(int id,String nombre) {
        this.id=id;
        this.nombre = nombre;
    }

    public U_Atistas(int id, String nombre, String apellido, Object fecha_nacimiento, String nacionalidad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fecha_nacimiento = fecha_nacimiento;
        this.nacionalidad = nacionalidad;
        this.id = id;
    }

    public U_Atistas() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }



    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }   

    public Object getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Object fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }
    
    
}
