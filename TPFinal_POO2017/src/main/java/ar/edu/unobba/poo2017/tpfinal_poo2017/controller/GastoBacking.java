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
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

/**
 *
 * @author Marcelo
 */
@Named
@ViewScoped
public class GastoBacking implements Serializable {

    private static final long serialVersionUID = 3153075978563526072L;

    private Gasto gasto;
    private Gasto gastoSeleccionado;
    private List<Gasto> gastosFiltrados;
    private List<Gasto> listGastos;

    @EJB
    private GastoDao gastoDao;

    public Gasto getGasto() {
        return gasto;
    }

    public void setGasto(Gasto gasto) {
        this.gasto = gasto;
    }

    @Inject
    private SessionBacking sessionBacking;

    @PostConstruct
    public void init() {
        setGasto(new Gasto());
        this.gasto.setEmpresa(sessionBacking.getUsuario().getEmpresa());

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
        setListGastos(null);
    }

    public List<Gasto> getGastos() {
        if(getListGastos() == null)
        {
            setListGastos(gastoDao.getGastos());
        }
        return getListGastos();
    }

    public Gasto getGastoSeleccionado() {
        return gastoSeleccionado;
    }

    public void setGastoSeleccionado(Gasto gastoSeleccionado) {
        this.gastoSeleccionado = gastoSeleccionado;
    }
    
    
     public Long getCantidadTotal(){
        return gastoDao.getCantidad();
    }
    
    public Long getCantidad(){
        return gastoDao.getCantidad(sessionBacking.getEmpresa());
    }

    public List<Gasto> getGastosFiltrados() {
        return gastosFiltrados;
    }

    public void setGastosFiltrados(List<Gasto> gastosFiltrados) {
        this.gastosFiltrados = gastosFiltrados;
    }

    private List<Gasto> getListGastos() {
        return listGastos;
    }

    private void setListGastos(List<Gasto> listGastos) {
        this.listGastos = listGastos;
    }
    
    

}
