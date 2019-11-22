/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udec.datos;

import com.udec.utilitarios.U_Carrito;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import sun.text.normalizer.UBiDiProps;

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
    
    
    public static List<U_Carrito> ObtenerComprasUsuario(String nombre){
        List<U_Carrito> compras = new ArrayList<U_Carrito>();
        try {
            Conexion cone = new Conexion();
            CallableStatement cst = cone.con.prepareCall("{call SP_COMPRAS_CLIENTE(?)}");
            cst.setString(1,nombre);
            ResultSet rs = cst.executeQuery();
            while(rs.next()){
                String producto=rs.getString(1);
                int cantidad = rs.getInt(2);
                float precioTotal = rs.getFloat(3);
                Date fechaCompra = rs.getDate(4);
                
                compras.add(new U_Carrito(producto, cantidad, precioTotal, fechaCompra)); 
            }
        } catch (Exception e) {
        }
        return  compras;
    }
}
    