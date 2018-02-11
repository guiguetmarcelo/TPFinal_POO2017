/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unobba.poo2017.tpfinal_poo2017.controller;

import ar.edu.unnoba.poo2017.tpfinal_poo2017.dao.CategoriaDao;
import ar.edu.unnoba.poo2017.tpfinal_poo2017.model.Categoria;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedProperty;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.Query;

/**
 *
 * @author Sebastian
 */
@Named
@ViewScoped
public class CategoriaBacking implements Serializable {

    private static final long serialVersionUID = -9078581278059597718L;
    
    private Categoria categoria;
    private List<Categoria> categoriasFiltradas;

    @PostConstruct
    public void init() {
        this.categoria = new Categoria();
        this.categoria.setEmpresa(sessionBacking.getUsuario().getEmpresa());
  
    }

    @EJB
    private CategoriaDao categoriaDao;
    
    @Inject
    private SessionBacking sessionBacking;

    public List<Categoria> getCategorias() {
        return categoriaDao.getCategorias();
        
    }

    public String create() {
        try {
            categoriaDao.create(categoria);
            return "/categorias/index?faces-redirect=true";
        } catch (Exception e) {
            return null;
        }
    }

    public String update() {
        try {
            categoriaDao.update(categoria);
            return "/categorias/index?faces-redirect=true";
        } catch (Exception e) {
            return null;
        }
    }

    
     public void delete(Categoria categoria) {
         try{
        categoriaDao.delete(categoria);
         }catch(Exception e) {}
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public List<Categoria> getCategoriasFiltradas() {
        return categoriasFiltradas;
    }

    public void setCategoriasFiltradas(List<Categoria> categoriasFiltradas) {
        this.categoriasFiltradas = categoriasFiltradas;
    }

    
}
