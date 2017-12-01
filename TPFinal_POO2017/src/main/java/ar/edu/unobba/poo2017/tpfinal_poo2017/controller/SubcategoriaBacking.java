/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unobba.poo2017.tpfinal_poo2017.controller;

import ar.edu.unnoba.poo2017.tpfinal_poo2017.dao.SubcategoriaDao;
import ar.edu.unnoba.poo2017.tpfinal_poo2017.model.Categoria;
import ar.edu.unnoba.poo2017.tpfinal_poo2017.model.Subcategoria;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Sebastian
 */
@Named
@ViewScoped
public class SubcategoriaBacking implements Serializable{
    
    private Categoria categoria;
    private Subcategoria subcategoria;
    
    @PostConstruct
    public void init(){
        this.categoria= new Categoria();
        this.subcategoria = new Subcategoria();
    }
    
    @EJB 
    private SubcategoriaDao subcategoriaDao;
    private SubcategoriaDao categoriaDao;
    
    public List<Subcategoria> getSubcategorias(){
        return subcategoriaDao.getSubcategorias();
    }
    
    public String create(){
        try{
            subcategoriaDao.create(subcategoria);
            return "/subcategorias/index?faces-redirect=true";
        }catch(Exception e){
            return null;
        }
    }
    
    public String update(){
        try{
            subcategoriaDao.update(subcategoria);
            return "/subcategorias/index?faces-redirect=true";
        }catch(Exception e){
            return null;
        }
    }
    
    public void delete(Subcategoria subcategoria){
        subcategoriaDao.delete(subcategoria);
    }

    public Subcategoria getSubcategoria() {
        return subcategoria;
    }

    public void setSubategoria(Subcategoria subcategoria) {
        this.subcategoria = subcategoria;
    }
    
    public Categoria getCategoria() {
        return categoria;
    }
    
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    
}
}
