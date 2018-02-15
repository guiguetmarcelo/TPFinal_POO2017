
package ar.edu.unobba.poo2017.tpfinal_poo2017.controller;

import ar.edu.unnoba.poo2017.tpfinal_poo2017.bundle.MessagesProducer;
import ar.edu.unnoba.poo2017.tpfinal_poo2017.dao.GastoDao;
import ar.edu.unnoba.poo2017.tpfinal_poo2017.model.Categoria;

import ar.edu.unnoba.poo2017.tpfinal_poo2017.model.Gasto;
import ar.edu.unnoba.poo2017.tpfinal_poo2017.model.Periodo;
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
public class GastoBacking implements Serializable {

    private static final long serialVersionUID = 3153075978563526072L;

    private Gasto gasto;
    private Gasto gastoSeleccionado;
    private List<Gasto> gastosFiltrados;
    private List<Gasto> listGastos;

    @EJB
    private GastoDao gastoDao;
    
        @Inject
    private MessagesProducer msg;

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
            FacesContext context = FacesContext.getCurrentInstance();
            FacesMessage message;
            message = new FacesMessage(msg.getString("error"));
            context.addMessage("msgUsuario", message);
            return null;
        }
    }

    public String update() {
        try {
            gastoDao.update(getGasto());
            return "/gastos/index?faces-redirect=true";
        } catch (Exception e) {
                        FacesContext context = FacesContext.getCurrentInstance();
            FacesMessage message;
            message = new FacesMessage(msg.getString("error"));
            context.addMessage("msgUsuario", message);
            return null;
        }
    }

    public void delete(Gasto gasto) {
        try{
        gastoDao.delete(gasto);
        setListGastos(null);
        } catch(Exception e){
                    FacesContext context = FacesContext.getCurrentInstance();
            FacesMessage message;
            message = new FacesMessage(msg.getString("error"));
            context.addMessage("msgUsuario", message);
        }
    }

    public List<Gasto> getGastos() {
        if (getListGastos() == null) {
            setListGastos(gastoDao.getGastos(sessionBacking.getEmpresa()));
        }
        return getListGastos();
    }

    public Gasto getGastoSeleccionado() {
        return gastoSeleccionado;
    }

    public void setGastoSeleccionado(Gasto gastoSeleccionado) {
        this.gastoSeleccionado = gastoSeleccionado;
    }

    public Long getCantidadTotal() {
        return gastoDao.getCantidad();
    }

    public Long getCantidad() {
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

    public List<Gasto> getGastosPeriodo(Periodo periodo) {
        return gastoDao.getGastosPeriodo(periodo, sessionBacking.getEmpresa());
    }

    public List<Gasto> getGastosCategoria(Categoria categoria) {
        return gastoDao.getGastosCategoria(categoria, sessionBacking.getEmpresa());
    }

    public List<Gasto> getGastosPeriodoCategoria(Periodo periodo, Categoria categoria) {
        return gastoDao.getGastosPeriodoCategoria(periodo, categoria, sessionBacking.getEmpresa());
    }

    public List<Gasto> getGastosPeriodoSubcategoria(Periodo periodo, Subcategoria subcategoria) {
        return gastoDao.getGastosPeriodoSubcategoria(periodo, subcategoria, sessionBacking.getEmpresa());
    }

}
