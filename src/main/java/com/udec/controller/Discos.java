package com.udec.controller;

import com.udec.datos.Crud_Artistas;
import com.udec.datos.Crud_Discos;
import com.udec.utilitarios.U_Atistas;
import com.udec.utilitarios.U_Discos;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.UploadedFile;
import org.primefaces.model.UploadedFileWrapper;

/**
 *
 * @author michl
 */
@Named
@ViewScoped
public class Discos implements Serializable{

    @Inject
    private U_Discos discos;
    //private UploadedFile file;
    private List<U_Atistas> listaArtistas;
    private List<U_Discos> listaDiscos;
    private Map<String,Integer> mapaArtistas;
    private int valor;
    /**
     * Creates a new instance of Discos
     */
    public Discos() {
    }
    
    @PostConstruct
    public void init(){
        listaArtistas = new ArrayList<U_Atistas>();
        mapaArtistas = new HashMap<String,Integer>();
        listaDiscos = new ArrayList<U_Discos>();
        
        listaArtistas = Crud_Artistas.traerIdArtistas();
        for (U_Atistas listaArtista : listaArtistas) {
            Integer id = listaArtista.getId();
            String nombre = listaArtista.getNombre();
            mapaArtistas.put(nombre,id);
        }
    }
    
    public void insertarDiscos(){
        int aux = Crud_Discos.insertarDiscos(discos);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Agregado exitosamente"));
            this.discos.setFoto(null);
            this.discos.setAlbum(null);
            this.discos.setNombre_Artista("Seleccione uno");
            this.discos.setCantidad_stock(0);
            this.discos.setPrecio(0);
    }
    
     public void editarDisco(RowEditEvent event){
        U_Discos cambio = (U_Discos) event.getObject();
        cambio.setAlbum(discos.getAlbum());
        cambio.setCantidad_stock(discos.getCantidad_stock());
        cambio.setPrecio(discos.getPrecio());
        Crud_Discos.editarDiscos(cambio);
    }
     
     public String eliminarDisco(U_Discos seleccion){
       int id =  Crud_Discos.eliminarDisco(seleccion.getId());
        return "/Administrador/discos";
    }
     
     public void cancelar(RowEditEvent event){
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cancelado"));
    }

     
    public void cambio(ValueChangeEvent e) {
        valor = Integer.parseInt(e.getNewValue().toString());
        listaDiscos = Crud_Discos.vistaDiscos(valor);
    }

    public boolean enablePanel(){
        if(listaDiscos.isEmpty()){
            return false;
        }
        else
            return true;
    }


    public U_Discos getDiscos() {
        return discos;
    }

    public void setDiscos(U_Discos discos) {
        this.discos = discos;
    }
    
    public List<U_Atistas> getListaArtistas() {
        return listaArtistas;
    }

    public void setListaArtistas(List<U_Atistas> listaArtistas) {
        this.listaArtistas = listaArtistas;
    }

    public Map<String, Integer> getMapaArtistas() {
        return mapaArtistas;
    }

    public void setMapaArtistas(Map<String, Integer> mapaArtistas) {
        this.mapaArtistas = mapaArtistas;
    }

    public List<U_Discos> getListaDiscos() {
        return listaDiscos;
    }

    public void setListaDiscos(List<U_Discos> listaDiscos) {
        this.listaDiscos = listaDiscos;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
    
    
}
