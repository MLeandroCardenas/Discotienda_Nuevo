/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udec.utilitarios;

/**
 *
 * @author michl
 */
public class U_Discos {
    
    private int id;
    private String foto;
    private String album;
    private int artista;
    private int cantidad_stock;

    public U_Discos() {
    }
    
    

    public U_Discos(int id, String foto, String album, int artista, int cantidad_stock) {
        this.id = id;
        this.foto = foto;
        this.album = album;
        this.artista = artista;
        this.cantidad_stock = cantidad_stock;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public int getArtista() {
        return artista;
    }

    public void setArtista(int artista) {
        this.artista = artista;
    }

    public int getCantidad_stock() {
        return cantidad_stock;
    }

    public void setCantidad_stock(int cantidad_stock) {
        this.cantidad_stock = cantidad_stock;
    }
    
    
    
}
