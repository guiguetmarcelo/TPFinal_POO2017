/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unobba.poo2017.tpfinal_poo2017.controller;

import ar.edu.unnoba.poo2017.tpfinal_poo2017.dao.GastoDao;
import ar.edu.unnoba.poo2017.tpfinal_poo2017.model.Gasto;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Marcelo
 */

@Named
@ViewScoped
public class GastoBacking implements Serializable{
    private Gasto gasto;
    
    @EJB
    private GastoDao gastoDao;
    
    @PostConstruct
    public void init(){
        setGasto(new Gasto());
    }
    
    public List<Gasto> getGastos(){
        return gastoDao.getGastos();
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

    
    
}
