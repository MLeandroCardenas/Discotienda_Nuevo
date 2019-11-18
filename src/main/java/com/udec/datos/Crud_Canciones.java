/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udec.datos;

import com.udec.utilitarios.U_Canciones;
import com.udec.utilitarios.U_Discos;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author michl
 */
public class Crud_Canciones {
    
    public static List<U_Discos> traerIdDiscos(){
        List<U_Discos> artista = new ArrayList<U_Discos>();
        try {
            Conexion cone = new Conexion();
            Statement st;
            ResultSet rs;
            st = cone.con.createStatement();
            rs = st.executeQuery("select id, album from discosdos");
            while(rs.next()){
                int id = (rs.getInt("id"));
                String nombre2 = (rs.getString("album"));
                
                artista.add(new U_Discos(id,nombre2));
            }
            
        } catch (Exception e) {
        }
        return artista;
    }
    
    
    public static int traerCantidades(int id){
        int cantidades = 0;
        try {
            Conexion cone = new Conexion();
            CallableStatement cst = cone.con.prepareCall("{call SP_CANTIDADES (?)}");
            cst.setInt(1,id);
            ResultSet rs = cst.executeQuery();
            
            if(rs.next()){
                cantidades = (rs.getInt("cantidad_stock"));
                return cantidades;
            }
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return cantidades;
    }
    
    public static int insertarCanciones(U_Canciones artista){
        int i = 0;
        try {
            Conexion cone = new Conexion();
            String query = "insert into cancionesdos(nombre,duracion,id_disco,precio,cantidad_stock)" + "values(?, ?, ?, ?, ?)";
            PreparedStatement preparedStmt = cone.con.prepareStatement(query);
            preparedStmt.setString(1,artista.getNombre_cancion());
            preparedStmt.setInt(2,artista.getDuracion());
            preparedStmt.setInt(3,artista.getId_disco());
            preparedStmt.setFloat(4,artista.getPrecio());
            preparedStmt.setInt(5,artista.getCantidad_stock());
            preparedStmt.execute();
            cone.con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage()); 
        }
        return i;
    }
    
    
    public static List<U_Canciones> vistaCanciones(int id){
        List<U_Canciones> discos = new ArrayList<U_Canciones>();
        try {
            Conexion cone = new Conexion();
            CallableStatement cst = cone.con.prepareCall("{call SP_CANCIONES(?)}");
            cst.setInt(1,id);
            ResultSet rs = cst.executeQuery();
            
            while(rs.next()){
                int id2 = rs.getInt(1);
                String nombre = rs.getString(2);
                int duracion = rs.getInt(3);
                float precio = rs.getFloat(4);
                int cantidad_stock = rs.getInt(5);
                discos.add(new U_Canciones(id2, nombre, duracion, precio, cantidad_stock));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return discos;
    }
    
    
    
     public static int eliminarCancion(int id){
        try {
            Conexion cone = new Conexion();
            String consulta= "delete from cancionesdos where id = ?";
            PreparedStatement preparedStmt = cone.con.prepareStatement(consulta);
            preparedStmt.setInt(1,id);
            preparedStmt.executeUpdate();
            cone.con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return id;
    }
     
     public static void editarCancion(U_Canciones cancion){
        try {
            Conexion cone = new Conexion();
            String sql = "update cancionesdos set  nombre=?,duracion=?, precio=?, cantidad_stock=? where id=?";
             PreparedStatement preparedStmt = cone.con.prepareStatement(sql);
             preparedStmt.setString(1, cancion.getNombre_cancion());
             preparedStmt.setInt(2, cancion.getDuracion());
             preparedStmt.setFloat(3, cancion.getPrecio());
             preparedStmt.setInt(4, cancion.getCantidad_stock());
             preparedStmt.setInt(5, cancion.getId());
             
             preparedStmt.executeUpdate();
             cone.con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
}
