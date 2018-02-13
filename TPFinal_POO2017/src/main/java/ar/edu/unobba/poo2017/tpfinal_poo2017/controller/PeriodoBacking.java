/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unobba.poo2017.tpfinal_poo2017.controller;

import ar.edu.unnoba.poo2017.tpfinal_poo2017.dao.PeriodoDao;
import ar.edu.unnoba.poo2017.tpfinal_poo2017.model.Periodo;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Sebastian
 */
@Named
@ViewScoped
public class PeriodoBacking implements Serializable {

    private static final long serialVersionUID = -6161957923995873981L;
    
    private Periodo periodo;
    private List<Periodo> listPeriodos;
    private List<Periodo> periodosFiltrados;
    
    @PostConstruct
    public void init(){
        this.periodo = new Periodo();
        this.periodo.setEmpresa(sessionBacking.getUsuario().getEmpresa());
    }
    
    @EJB
    private PeriodoDao periodoDao;
    
    @Inject
    private SessionBacking sessionBacking;
    
    public List<Periodo> getPeriodos(){
        if(getListPeriodos() == null){
            setListPeriodos(periodoDao.getPeriodos(sessionBacking.getEmpresa()));
        } 
        return getListPeriodos();
    }
    
    public String create(){
        try{
            periodoDao.create(periodo);
            return "/periodos/index?faces-redirect=true";
        }catch(Exception e){
            return null;
        }
    }
    
    public String update(){
        try{
            periodoDao.update(periodo);
            return "/periodos/index?faces-redirect=true";
        }catch(Exception e){
            return null;
        }
    }
    
    public void delete(Periodo periodo){
        periodoDao.delete(periodo);
        setListPeriodos(null);
    }

    public Periodo getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Periodo periodo) {
        this.periodo=periodo;
    }

    private List<Periodo> getListPeriodos() {
        return listPeriodos;
    }

    private void setListPeriodos(List<Periodo> listPeriodos) {
        this.listPeriodos = listPeriodos;
    }
    
    

    public List<Periodo> getPeriodosFiltrados() {
        return periodosFiltrados;
    }

    public void setPeriodosFiltrados(List<Periodo> periodosFiltrados) {
        this.periodosFiltrados = periodosFiltrados;
    }
    
  
}
