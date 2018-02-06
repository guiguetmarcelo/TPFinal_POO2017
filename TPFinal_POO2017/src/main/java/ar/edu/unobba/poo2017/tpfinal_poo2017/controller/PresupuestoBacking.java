/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unobba.poo2017.tpfinal_poo2017.controller;

import ar.edu.unnoba.poo2017.tpfinal_poo2017.dao.PresupuestoDao;
import ar.edu.unnoba.poo2017.tpfinal_poo2017.model.Presupuesto;
import java.io.Serializable;
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
public class PresupuestoBacking implements Serializable {

    private static final long serialVersionUID = -5125260839458783514L;

    private Presupuesto presupuesto;

    @EJB
    private PresupuestoDao presupuestoDao;
    
    @Inject
    private SessionBacking sessionBacking;

    @PostConstruct
    public void init() {
        setPresupuesto(new Presupuesto());
        this.presupuesto.setEmpresa(sessionBacking.getUsuario().getEmpresa());
    }

    public List<Presupuesto> getPresupuestos() {
        return presupuestoDao.getPresupuestos();
    }
    
    public Long getCantidadTotal(){
        return presupuestoDao.getCantidad();
    }
    
    public Long getCantidad(){
        return presupuestoDao.getCantidad(sessionBacking.getEmpresa());
    }

    public String create() {
        try {
            presupuestoDao.create(getPresupuesto());
            return "/presupuestos/index?faces-redirect=true";
        } catch (Exception e) {
            return null;
        }
    }

    public String update() {
        try {
            presupuestoDao.update(getPresupuesto());
            return "/presupuestos/index?faces-redirect=true";
        } catch (Exception e) {
            return null;
        }
    }

    public void delete(Presupuesto presupuesto) {
        presupuestoDao.delete(presupuesto);
    }

    public Presupuesto getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(Presupuesto presupuesto) {
        this.presupuesto = presupuesto;
    }

}
