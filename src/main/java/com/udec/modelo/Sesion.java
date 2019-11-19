/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udec.modelo;

import com.udec.utilitarios.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author michl
 */
public class Sesion implements Serializable{

    
    private List<Usuario> listaUsuario;
    
    
    public Sesion() {
        listaUsuario = new ArrayList<Usuario>();
        listaUsuario.add(new Usuario("admin", "123",1));
    }

    public Usuario validarSesion(String usuario, String contrasena) {
        Usuario usuarioLogeado = null;
        for (Usuario usu : listaUsuario) {
            if(usu.getUsuario().equals(usuario) && usu.getClave().equals(contrasena)){
                usuarioLogeado = usu;
                return usuarioLogeado;
            }
        }
        return usuarioLogeado;
    }
}
