/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unobba.poo2017.tpfinal_poo2017.controller;

import ar.edu.unnoba.poo2017.tpfinal_poo2017.dao.GastoDao;
import ar.edu.unnoba.poo2017.tpfinal_poo2017.model.Categoria;
import ar.edu.unnoba.poo2017.tpfinal_poo2017.model.Gasto;
import ar.edu.unnoba.poo2017.tpfinal_poo2017.model.Periodo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Marcelo
 */
@Named
@ViewScoped
public class GastoBacking implements Serializable {

    private Gasto gasto;
    private Periodo periodo;
    private Categoria categoria;
    private List<Gasto> filtrados;

    

    @EJB
    private GastoDao gastoDao;
    
    @Inject
    private SessionBacking sessionBacking;
    
    
    @PostConstruct
    public void init() {
        setGasto(new Gasto());
        setPeriodo(new Periodo());
        setCategoria(new Categoria());
        setFiltrados(new ArrayList<Gasto>());
        this.gasto.setEmpresa(sessionBacking.getUsuario().getEmpresa());
    }
    
     public Periodo getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;
    }
    
    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    
    public List<Gasto> getGastos() {
        return gastoDao.getGastos();
    }
    
     public List<Gasto> getGastosPeriodo(){
        return gastoDao.getGastosPeriodo(periodo);
    }
     
     public List<Gasto> getGastosCategoria(Categoria categoria){
        this.setFiltrados(gastoDao.getGastosCategoria(categoria));
        return filtrados;
    }
     
     public List<Gasto> getGastosPeriodoCategoria(Periodo periodo, Categoria categoria){
        this.setFiltrados(gastoDao.getGastosPeriodoCategoria(periodo, categoria));
        return filtrados;
    }
     
     

    public String create() {
        try {
            gastoDao.create(getGasto());
            return "/gastos/index?faces-redirect=true";
        } catch (Exception e) {
            return null;
        }
    }

    public String update() {
        try {
            gastoDao.update(getGasto());
            return "/gastos/index?faces-redirect=true";
        } catch (Exception e) {
            return null;
        }
    }

    public void delete(Gasto gasto) {
        gastoDao.delete(gasto);
    }

    public Gasto getGasto() {
        return gasto;
    }

    public void setGasto(Gasto gasto) {
        this.gasto = gasto;
    }
    
    public List<Gasto> getFiltrados() {
        return filtrados;
    }

    public void setFiltrados(List<Gasto> filtrados) {
        this.filtrados = filtrados;
    }

}
