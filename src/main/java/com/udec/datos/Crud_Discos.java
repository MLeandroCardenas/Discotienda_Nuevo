/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udec.datos;

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
public class Crud_Discos {
    
    public  static int insertarDiscos(U_Discos discos){
        int i = 0;
        try {
            Conexion cone = new Conexion();
            String query = "insert into discosdos(album,foto,id_artista,cantidad_stock,precio)" + "values(?, ?, ?, ?, ?)";
            PreparedStatement preparedStmt = cone.con.prepareStatement(query);
            preparedStmt.setString(1,discos.getAlbum());
            preparedStmt.setString(2,discos.getFoto());
            preparedStmt.setInt(3,discos.getArtista());
            preparedStmt.setInt(4,discos.getCantidad_stock());
            preparedStmt.setFloat(5,discos.getPrecio());
            preparedStmt.execute();
            cone.con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage()); 
        }
        
        return i;
    }
    
    public static List<U_Discos> traerDiscos(){
        List<U_Discos> discos = new ArrayList<U_Discos>();
        try {
            Conexion cone = new Conexion();
            Statement st;
            ResultSet rs;
            st = cone.con.createStatement();
            rs = st.executeQuery("select * from discosdos");
            while(rs.next()){
                int id = (rs.getInt("id"));
                String foto = (rs.getString("foto"));
                String album = (rs.getString("album"));
                int artista = (rs.getInt("artista"));
                int cantidad_stock = (rs.getInt("cantidad_stock"));
                
                discos.add(new U_Discos(id, foto, album, artista, cantidad_stock));
            }
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return discos;
    }
    
    public static List<U_Discos> vistaDiscos(int id){
        List<U_Discos> discos = new ArrayList<U_Discos>();
        try {
            Conexion cone = new Conexion();
            CallableStatement cst = cone.con.prepareCall("{call SP_DISCOS (?)}");
            cst.setInt(1,id);
            ResultSet rs = cst.executeQuery();
            
            while(rs.next()){
                int id2 = rs.getInt(1);
                String album = rs.getString(2);
                String foto = rs.getString(3);
                int cantidad_stock = rs.getInt(4);
                float precio = rs.getFloat(5);
                discos.add(new U_Discos(id, album, foto, cantidad_stock,precio));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return discos;
        
        /*
            SELECT artistas.nombre, discos.id, discos.album, discos.foto, discos.cantidad_stock FROM discos INNER JOIN artistas ON artistas.id=4
        */
    }
    
    public static void editarDiscos(U_Discos discos){
        try {
            Conexion cone = new Conexion();
            String sql = "update discosdos set  album=?,cantidad_stock=?, precio=? where id=?";
             PreparedStatement preparedStmt = cone.con.prepareStatement(sql);
             preparedStmt.setString(1, discos.getAlbum());
             preparedStmt.setInt(2, discos.getCantidad_stock());
             preparedStmt.setFloat(3, discos.getPrecio());
             preparedStmt.setInt(4, discos.getId());
             
             preparedStmt.executeUpdate();
             cone.con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static int eliminarDisco(int id){
        try {
            Conexion cone = new Conexion();
            String consulta= "delete from discosdos where id = ?";
            PreparedStatement preparedStmt = cone.con.prepareStatement(consulta);
            preparedStmt.setInt(1,id);
            preparedStmt.executeUpdate();
            cone.con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return id;
    }
}
