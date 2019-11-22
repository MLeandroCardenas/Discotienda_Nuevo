/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udec.servicios;

import com.udec.datos.Compras;
import com.udec.utilitarios.U_Carrito;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author michl
 */
@Path("/comprasUsuarios")
public class ComprasUsuarios{
    
    
    @Path("/dato/{nombre}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<U_Carrito> obtenerComprasUsuario(@PathParam("nombre") String nombre){
        List<U_Carrito> lista = new ArrayList<U_Carrito>();
        lista = Compras.ObtenerComprasUsuario(nombre);
        return lista;
    }
}
