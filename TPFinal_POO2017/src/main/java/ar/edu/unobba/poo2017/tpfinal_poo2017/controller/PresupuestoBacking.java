/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unobba.poo2017.tpfinal_poo2017.controller;

import ar.edu.unnoba.poo2017.tpfinal_poo2017.bundle.MessagesProducer;
import ar.edu.unnoba.poo2017.tpfinal_poo2017.dao.PresupuestoDao;
import ar.edu.unnoba.poo2017.tpfinal_poo2017.model.Categoria;
import ar.edu.unnoba.poo2017.tpfinal_poo2017.model.Empresa;
import ar.edu.unnoba.poo2017.tpfinal_poo2017.model.Periodo;
import ar.edu.unnoba.poo2017.tpfinal_poo2017.model.Presupuesto;
import ar.edu.unnoba.poo2017.tpfinal_poo2017.model.Subcategoria;
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
 * @author Marcelo
 */
@Named
@ViewScoped
public class PresupuestoBacking implements Serializable {

    private static final long serialVersionUID = -5125260839458783514L;

    private Presupuesto presupuesto;
    private Presupuesto presupuestoSeleccionado;
    private List<Presupuesto> listPresupuestos;
    private List<Presupuesto> presupuestosFiltrados;

    @EJB
    private PresupuestoDao presupuestoDao;

    @Inject
    private MessagesProducer msg;

    @Inject
    private SessionBacking sessionBacking;

    @PostConstruct
    public void init() {
        setPresupuesto(new Presupuesto());
        getPresupuesto().setEmpresa(sessionBacking.getUsuario().getEmpresa());
        //this.presupuesto.setEmpresa(sessionBacking.getUsuario().getEmpresa());

    }

    public List<Presupuesto> getPresupuestos() {
        if (getListPresupuestos() == null) {
            setListPresupuestos(presupuestoDao.getPresupuestos(sessionBacking.getEmpresa()));
        }
        return getListPresupuestos();
    }

    public Long getCantidadTotal() {
        return presupuestoDao.getCantidad();
    }

    public Long getCantidad() {
        return presupuestoDao.getCantidad(sessionBacking.getEmpresa());
    }

    public String create() {
        try {
            presupuestoDao.create(getPresupuesto());
            return "/presupuestos/index?faces-redirect=true";
        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            FacesMessage message;
            message = new FacesMessage(msg.getString("error"));
            context.addMessage("msgUsuario", message);
            return null;
        }
    }

    public String update() {
        try {
            presupuestoDao.update(getPresupuesto());
            return "/presupuestos/index?faces-redirect=true";
        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            FacesMessage message;
            message = new FacesMessage(msg.getString("error_actualizar"));
            context.addMessage("msgUsuario", message);
            return null;
        }
    }

    public void delete(Presupuesto presupuesto) {
        try {
            presupuestoDao.delete(presupuesto);
            setListPresupuestos(null);
        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            FacesMessage message;
            message = new FacesMessage(msg.getString("error"));
            context.addMessage("msgUsuario", message);
        }
    }

    public Presupuesto getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(Presupuesto presupuesto) {
        this.presupuesto = presupuesto;
    }

    public Presupuesto getPresupuestoSeleccionado() {
        return presupuestoSeleccionado;
    }

    public void setPresupuestoSeleccionado(Presupuesto presupuestoSeleccionado) {
        this.presupuestoSeleccionado = presupuestoSeleccionado;
    }

    private List<Presupuesto> getListPresupuestos() {
        return listPresupuestos;
    }

    private void setListPresupuestos(List<Presupuesto> listPresupuestos) {
        this.listPresupuestos = listPresupuestos;
    }

    public List<Presupuesto> getPresupuestosFiltrados() {
        return presupuestosFiltrados;
    }

    public void setPresupuestosFiltrados(List<Presupuesto> presupuestosFiltrados) {
        this.presupuestosFiltrados = presupuestosFiltrados;
    }

    public List<Presupuesto> getPresupuestosSubcategoriaPeriodo(Periodo periodo, Subcategoria subcategoria, Empresa empresa) {
        return presupuestoDao.getPresupuestosSubcategoriaPeriodo(periodo, subcategoria, empresa);
    }

    public List<Presupuesto> getPresupuestosCategoria(Categoria categoria, Empresa empresa) {
        return presupuestoDao.getPresupuestosCategoria(categoria, empresa);
    }

}
