
package ar.edu.unobba.poo2017.tpfinal_poo2017.controller;

import ar.edu.unnoba.poo2017.tpfinal_poo2017.bundle.MessagesProducer;
import ar.edu.unnoba.poo2017.tpfinal_poo2017.dao.CategoriaDao;
import ar.edu.unnoba.poo2017.tpfinal_poo2017.model.Categoria;
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
 * @author Sebastian
 */
@Named
@ViewScoped
public class CategoriaBacking implements Serializable {

    private static final long serialVersionUID = -9078581278059597718L;

    private Categoria categoria;
    private Categoria categoriaSeleccionada;
    private List<Categoria> listCategorias;
    private List<Categoria> categoriasFiltradas;

    @PostConstruct
    public void init() {
        this.categoria = new Categoria();
        this.categoria.setEmpresa(sessionBacking.getUsuario().getEmpresa());

    }

    @EJB
    private CategoriaDao categoriaDao;

    @Inject
    private MessagesProducer msg;

    @Inject
    private SessionBacking sessionBacking;

    public List<Categoria> getCategorias() {
        if (getListCategorias() == null) {
            setListCategorias(categoriaDao.getCategorias(sessionBacking.getEmpresa()));
        }
        return getListCategorias();

    }

    public String create() {
        try {
            categoriaDao.create(categoria);
            return "/categorias/index?faces-redirect=true";
        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            FacesMessage message;
            message = new FacesMessage(msg.getString("categorias_existente"));
            context.addMessage("msgCategoria", message);
            return null;
        }
    }

    public String update() {
        try {
            categoriaDao.update(categoria);
            return "/categorias/index?faces-redirect=true";
        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            FacesMessage message;
            message = new FacesMessage(msg.getString("error_actualizar"));
            context.addMessage("msgCategoria", message);
            return null;
        }
    }

    public void delete(Categoria categoria) {
        try {
            categoriaDao.delete(categoria);
            setListCategorias(null);
        } catch (Exception e) {

            FacesContext context = FacesContext.getCurrentInstance();
            FacesMessage message;
            message = new FacesMessage(msg.getString("categorias_enuso"));
            context.addMessage("msgCategoria", message);
        }
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Categoria getCategoriaSeleccionada() {
        return categoriaSeleccionada;
    }

    public void setCategoriaSeleccionada(Categoria categoriaSeleccionada) {
        this.categoriaSeleccionada = categoriaSeleccionada;
    }

    private List<Categoria> getListCategorias() {
        return listCategorias;
    }

    private void setListCategorias(List<Categoria> listCategorias) {
        this.listCategorias = listCategorias;
    }

    public List<Categoria> getCategoriasFiltradas() {
        return categoriasFiltradas;
    }

    public void setCategoriasFiltradas(List<Categoria> categoriasFiltradas) {
        this.categoriasFiltradas = categoriasFiltradas;
    }

}
