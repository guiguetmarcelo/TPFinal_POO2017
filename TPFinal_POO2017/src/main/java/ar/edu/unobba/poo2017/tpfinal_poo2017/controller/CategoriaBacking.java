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
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.Query;

/**
 *
 * @author Sebastian
 */
@Named
@ViewScoped
public class CategoriaBacking implements Serializable {

    private Categoria categoria;

    @PostConstruct
    public void init() {
        this.categoria = new Categoria();
    }

    @EJB
    private CategoriaDao categoriaDao;

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
        categoriaDao.delete(categoria);
        
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

}
