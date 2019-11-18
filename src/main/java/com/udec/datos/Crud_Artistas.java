/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udec.datos;

import com.udec.utilitarios.U_Atistas;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 *
 * @author michl
 */
public class Crud_Artistas{
    
   public static List<U_Atistas> TraerArtistas(){
        List<U_Atistas> artista = new ArrayList<U_Atistas>();
        try {
            Conexion cone = new Conexion();
            Statement st;
            ResultSet rs;
            st = cone.con.createStatement();
            rs = st.executeQuery("select * from artistas");
            while(rs.next()){
                int id = (rs.getInt("id"));
                String nombre2 = (rs.getString("nombre"));
                String apellido = (rs.getString("apellido"));
                Date fecha = (rs.getDate("nacimiento"));
                String nacionalidad = (rs.getString("nacionalidad"));
                
                artista.add(new U_Atistas(id,nombre2, apellido, fecha, nacionalidad));
            }
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return artista;
    }
   
    public static List<U_Atistas> traerIdArtistas(){
        List<U_Atistas> artista = new ArrayList<U_Atistas>();
        try {
            Conexion cone = new Conexion();
            Statement st;
            ResultSet rs;
            st = cone.con.createStatement();
            rs = st.executeQuery("select id, nombre from artistas");
            while(rs.next()){
                int id = (rs.getInt("id"));
                String nombre2 = (rs.getString("nombre"));
                
                artista.add(new U_Atistas(id,nombre2));
            }
            
        } catch (Exception e) {
        }
        return artista;
    }
    
     public static void insertarArtistas(U_Atistas artista){
        try {
            Conexion cone = new Conexion();
            String query = "insert into artistas(nombre,apellido,nacimiento,nacionalidad)" + "values(?, ?, ?, ?)";
            PreparedStatement preparedStmt = cone.con.prepareStatement(query);
            preparedStmt.setString(1,artista.getNombre());
            preparedStmt.setString(2,artista.getApellido());
            preparedStmt.setObject(3,artista.getFecha_nacimiento());
            preparedStmt.setString(4,artista.getNacionalidad());
            preparedStmt.execute();
            cone.con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage()); 
        }
    }

     public static int eliminarArtista(int id){
        try {
            Conexion cone = new Conexion();
            String consulta= "delete from artistas where id = ?";
            PreparedStatement preparedStmt = cone.con.prepareStatement(consulta);
            preparedStmt.setInt(1,id);
            preparedStmt.executeUpdate();
            cone.con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        return id;
    }
     
    public static void editarArtista(U_Atistas cantante){
        try {
            Conexion cone = new Conexion();
            String sql = "update artistas set nombre=?, apellido=?, nacimiento=?, nacionalidad=? where id=?";
             PreparedStatement preparedStmt = cone.con.prepareStatement(sql);
             preparedStmt.setString(1, cantante.getNombre());
             preparedStmt.setString(2, cantante.getApellido());
             preparedStmt.setObject(3, cantante.getFecha_nacimiento());
             preparedStmt.setString(4, cantante.getNacionalidad());
             preparedStmt.setInt(5,cantante.getId());
             
             preparedStmt.executeUpdate();
             cone.con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    } 
}
