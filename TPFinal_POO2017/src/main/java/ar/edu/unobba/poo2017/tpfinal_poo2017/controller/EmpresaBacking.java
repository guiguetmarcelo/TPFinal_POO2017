
package ar.edu.unobba.poo2017.tpfinal_poo2017.controller;

import ar.edu.unnoba.poo2017.tpfinal_poo2017.bundle.MessagesProducer;
import ar.edu.unnoba.poo2017.tpfinal_poo2017.dao.EmpresaDao;
import ar.edu.unnoba.poo2017.tpfinal_poo2017.model.Empresa;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.PersistenceException;
import org.eclipse.persistence.exceptions.EclipseLinkException;

/**
 *
 * @author Sebastian
 */
@Named
@ViewScoped
public class EmpresaBacking implements Serializable {

    private static final long serialVersionUID = -7254678850848580969L;

    private Empresa empresa;
    private Empresa empresaSeleccionada;
    private List<Empresa> listEmpresas;
    private List<Empresa> empresasFiltradas;

    @Inject
    private SessionBacking session;

    @Inject
    private MessagesProducer msg;

    @PostConstruct
    public void init() {
        this.empresa = new Empresa();
    }

    @EJB
    private EmpresaDao empresaDao;

    public List<Empresa> getEmpresas() {
        if (getListEmpresas() == null) {
            setListEmpresas(empresaDao.getEmpresas());
        }
        return getListEmpresas();
    }

    public String create() {
        try {
            empresaDao.create(empresa);
            return "/empresas/index?faces-redirect=true";
        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            FacesMessage message;
            message = new FacesMessage(msg.getString("empresa_existente"));
            context.addMessage("msgEmpresa", message);
            return null;
        }
    }

    public String update() {
        try {
            empresaDao.update(getEmpresa());
            if (session.getEmpresa().equals(getEmpresa())) {
                session.update();
            }
            return "/empresas/index?faces-redirect=true";
        } catch (Exception e) {
            return null;
        }
    }

    public void delete(Empresa empresa) {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            FacesMessage message;
            if (empresa.equals(session.getEmpresa())) {
                message = new FacesMessage(msg.getString("empresas_eliminarASiMismo"));
            } else {
                message = new FacesMessage(msg.getString("empresas_eliminarEmpresaEliminada", empresa.getNombre()));
                empresaDao.delete(empresa);
                setListEmpresas(null);
            }
            context.addMessage("msgEmpresa", message);

        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            FacesMessage message;
            message = new FacesMessage(msg.getString("empresa_enuso"));
            context.addMessage("msgEmpresa", message);
            
        }
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Empresa getEmpresaSeleccionada() {
        return empresaSeleccionada;
    }

    public void setEmpresaSeleccionada(Empresa empresaSeleccionada) {
        this.empresaSeleccionada = empresaSeleccionada;
    }

    public List<Empresa> getEmpresasFiltradas() {
        return empresasFiltradas;
    }

    public void setEmpresasFiltradas(List<Empresa> empresasFiltradas) {
        this.empresasFiltradas = empresasFiltradas;
    }

    private List<Empresa> getListEmpresas() {
        return listEmpresas;
    }

    private void setListEmpresas(List<Empresa> listEmpresas) {
        this.listEmpresas = listEmpresas;
    }

}
