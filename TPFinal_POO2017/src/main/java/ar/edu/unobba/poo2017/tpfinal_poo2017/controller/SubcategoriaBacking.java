
package ar.edu.unobba.poo2017.tpfinal_poo2017.controller;

import ar.edu.unnoba.poo2017.tpfinal_poo2017.bundle.MessagesProducer;
import ar.edu.unnoba.poo2017.tpfinal_poo2017.dao.SubcategoriaDao;
import ar.edu.unnoba.poo2017.tpfinal_poo2017.model.Categoria;
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
 * @author Sebastian
 */
@Named
@ViewScoped
public class SubcategoriaBacking implements Serializable {

    private static final long serialVersionUID = -655351001157459375L;

    private Subcategoria subcategoria;

    private List<Subcategoria> listSubcategorias;
    private List<Subcategoria> subcategoriasFiltradas;

    @PostConstruct
    public void init() {
        setSubcategoria(new Subcategoria());
        getSubcategoria().setEmpresa(sessionBacking.getUsuario().getEmpresa());
    }

    @EJB
    private SubcategoriaDao subcategoriaDao;

    @Inject
    private SessionBacking sessionBacking;

    @Inject
    private MessagesProducer msg;

    public List<Subcategoria> getSubcategorias() {
        if (getListSubcategorias() == null) {
            setListSubcategorias(subcategoriaDao.getSubcategorias(sessionBacking.getEmpresa()));
        }
        return getListSubcategorias();
    }

    public String create() {
        try {
            subcategoriaDao.create(getSubcategoria());

            return "/subcategorias/index?faces-redirect=true";
        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            FacesMessage message;
            message = new FacesMessage(msg.getString("subcategorias_existente"));
            context.addMessage("msgSubcategoria", message);
            return null;
        }
    }

    public String update() {
        try {
            subcategoriaDao.update(subcategoria);
            return "/subcategorias/index?faces-redirect=true";
        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            FacesMessage message;
            message = new FacesMessage(msg.getString("error_actualizar"));
            context.addMessage("msgSubcategoria", message);
            return null;
        }
    }

    public void delete(Subcategoria subcategoria) {
        try {
            subcategoriaDao.delete(subcategoria);
            setListSubcategorias(null);
        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            FacesMessage message;
            message = new FacesMessage(msg.getString("subcategorias_enuso"));
            context.addMessage("msgSubcategoria", message);

        }
    }

    public Subcategoria getSubcategoria() {
        return subcategoria;
    }

    public void setSubcategoria(Subcategoria subcategoria) {
        this.subcategoria = subcategoria;
    }

    private List<Subcategoria> getListSubcategorias() {
        return listSubcategorias;
    }

    private void setListSubcategorias(List<Subcategoria> listSubcategorias) {
        this.listSubcategorias = listSubcategorias;
    }

    public List<Subcategoria> getSubcategoriasFiltradas() {
        return subcategoriasFiltradas;
    }

    public void setSubcategoriasFiltradas(List<Subcategoria> subcategoriasFiltradas) {
        this.subcategoriasFiltradas = subcategoriasFiltradas;
    }

}
