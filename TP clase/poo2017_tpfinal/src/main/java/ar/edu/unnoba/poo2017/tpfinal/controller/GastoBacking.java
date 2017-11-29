/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unnoba.poo2017.tpfinal.controller;

import ar.edu.unnoba.poo2017.tpfinal.dao.CategoriaDao;
import ar.edu.unnoba.poo2017.tpfinal.dao.GastoDao;
import ar.edu.unnoba.poo2017.tpfinal.model.Categoria;
import ar.edu.unnoba.poo2017.tpfinal.model.Gasto;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author jpgm
 */
@Named
@ViewScoped
public class GastoBacking implements Serializable {
    private Gasto gasto;
    
    @PostConstruct
    public void init(){
        this.gasto = new Gasto();
    }
    
    @EJB
    private GastoDao gastoDao;
    
    @EJB
    private CategoriaDao categoriaDao;
    
    public List<Categoria> getCategorias(){
        return categoriaDao.getCategorias();
    }
    
    public List<Gasto> getGastos(){
        return gastoDao.getGastos();
    }
    
    public String create(){
        try{
            gastoDao.create(gasto);
            return "/gastos/index?faces-redirect=true";
        }catch(Exception e){
            return null;
        }
    }
    
    public Gasto getGasto() {
        return gasto;
    }

    public void setGasto(Gasto gasto) {
        this.gasto = gasto;
    }
    
    
}
