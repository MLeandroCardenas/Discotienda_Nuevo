/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udec.datos;

import com.udec.utilitarios.U_Carrito;
import java.sql.PreparedStatement;
import java.util.List;

/**
 *
 * @author michl
 */
public class Compras {
    
    public static void registrarCompra(List<U_Carrito>carrito,U_Carrito usuario){
        try {
            Conexion cone = new Conexion();
            for (U_Carrito aux : carrito) {
                String query = "insert into compras(nombreCliente,producto,cantidad,precioTotal)" + "values(?, ?, ?, ?)";
                PreparedStatement preparedStmt = cone.con.prepareStatement(query);
                preparedStmt.setString(1,usuario.getNombreCliente());
                preparedStmt.setString(2,aux.getProducto());
                preparedStmt.setInt(3,aux.getCantidad());
                preparedStmt.setFloat(4,aux.getPrecio());
                preparedStmt.execute();
            }
            cone.con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    
    public static void actualizarStockCanciones(int resultado,int id){
        try{
             Conexion cone = new Conexion();
             String sql = "update cancionesdos set cantidad_stock=? where id=?";
             PreparedStatement preparedStmt = cone.con.prepareStatement(sql);
             preparedStmt.setInt(1,resultado);
             preparedStmt.setInt(2,id);
             preparedStmt.executeUpdate();
             cone.con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
    