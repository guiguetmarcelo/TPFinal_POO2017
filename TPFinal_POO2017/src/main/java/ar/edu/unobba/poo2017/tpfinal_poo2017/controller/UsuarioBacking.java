package ar.edu.unobba.poo2017.tpfinal_poo2017.controller;

import ar.edu.unnoba.poo2017.tpfinal_poo2017.bundle.MessagesProducer;
import ar.edu.unnoba.poo2017.tpfinal_poo2017.dao.UsuarioDao;
import ar.edu.unnoba.poo2017.tpfinal_poo2017.model.RolUsuario;
import ar.edu.unnoba.poo2017.tpfinal_poo2017.model.Usuario;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Marcelo
 * @author Sebastian
 */
@Named
@ViewScoped
public class UsuarioBacking implements Serializable {

    private static final long serialVersionUID = -1625966198515288546L;

    private Usuario usuario;
    private Usuario usuarioSeleccionado;

    private Boolean changePassword;
    private String password;

    private List<Usuario> listUsuariosActivos;
    private List<Usuario> usuariosFiltrados;

    @Inject
    private SessionBacking session;

    @Inject
    private MessagesProducer msg;

    @PostConstruct
    public void init() {
        setUsuario(new Usuario());
        getUsuario().setRol(RolUsuario.ADMINISTRADOR_EMPRESA);
        setChangePassword(false);
    }

    @EJB
    private UsuarioDao usuarioDao;

    public List<Usuario> getUsuarios() {
        return usuarioDao.getUsuarios();
    }

    public List<Usuario> getUsuariosEmpresa() {
        return usuarioDao.getUsuarios(session.getEmpresa());
    }

    public List<Usuario> getUsuariosActivosEmpresa() {
        if (getListUsuariosActivos() == null) {
            setListUsuariosActivos(usuarioDao.getUsuariosActivos(session.getEmpresa()));
        }
        return getListUsuariosActivos();
    }

    public String create() {
        if (!session.getUsuario().isAdmGen()) {
            getUsuario().setRol(RolUsuario.ADMINISTRADOR_EMPRESA);
            getUsuario().setEmpresa(session.getUsuario().getEmpresa());
        }
        try {
            loadPassword();
            usuarioDao.create(getUsuario());
            return "/usuarios/index?faces-redirect=true";
        } catch (Exception e) {
            return null;
        }
    }

    public String update() {
        try {
            if (getChangePassword()) {
                loadPassword();
            }
            usuarioDao.update(getUsuario());
            if (getUsuario().equals(session.getUsuario())) {
                session.update();
            }
            return "/usuarios/index?faces-redirect=true";
        } catch (Exception e) {
            return null;
        }
    }

    public void delete(Usuario usuario) {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage message;
        if (usuario.getEmail().equals(session.getUsuario().getEmail())) {
            message = new FacesMessage(msg.getString("usuarios_eliminarASiMismo"));
        } else {
            message = new FacesMessage(msg.getString("usuarios_eliminarUsuarioEliminado", usuario.getEmail()));
            usuarioDao.delete(usuario);
            setListUsuariosActivos(null);

        }
        context.addMessage("msgUsuario", message);
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuarioSeleccionado() {
        return usuarioSeleccionado;
    }

    public void setUsuarioSeleccionado(Usuario usuarioSeleccionado) {
        this.usuarioSeleccionado = usuarioSeleccionado;
    }

    public Boolean getChangePassword() {
        return changePassword;
    }

    public void setChangePassword(Boolean changePassword) {
        this.changePassword = changePassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private List<Usuario> getListUsuariosActivos() {
        return listUsuariosActivos;
    }

    private void setListUsuariosActivos(List<Usuario> listUsuariosActivos) {
        this.listUsuariosActivos = listUsuariosActivos;
    }

    public List<Usuario> getUsuariosFiltrados() {
        return usuariosFiltrados;
    }

    public void setUsuariosFiltrados(List<Usuario> usuariosFiltrados) {
        this.usuariosFiltrados = usuariosFiltrados;
    }

    public SelectItem[] getRolesSelectMany() {
        SelectItem[] items = new SelectItem[RolUsuario.values().length];

        int i = 0;
        for (RolUsuario role : RolUsuario.values()) {
            items[i++] = new SelectItem(role, role.toString());
        }
        return items;
    }

    public RolUsuario rolAdmEmp(){
        return RolUsuario.ADMINISTRADOR_EMPRESA;
    }
    
    public RolUsuario rolAdmGen(){
        return RolUsuario.ADMINISTRADOR_GENERAL;
    }
    
    public String rolToString(RolUsuario rol) {
        if (rol.isAdmGen()) {
            return msg.getString("rol_admGen");
        } else {
            return msg.getString("rol_admEmp");
        }
    }

    private void loadPassword() {
        getUsuario().setPassword(getPassword());
    }

}
