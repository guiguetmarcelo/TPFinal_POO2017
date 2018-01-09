/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author jpgm
 */
@Named
@ViewScoped
public class UsuarioBacking implements Serializable {

    private Usuario usuario;

    @Inject
    private SessionBacking session;

    @Inject
    private MessagesProducer msg;

    @PostConstruct
    public void init() {
        this.usuario = new Usuario();
    }

    @EJB
    private UsuarioDao usuarioDao;

    public List<Usuario> getUsuarios() {
        return usuarioDao.getUsuarios();
    }
    
      public List<Usuario> getUsuariosActivos() {
        return usuarioDao.getUsuariosActivos();
    }

    public String create() {
        usuario.setRol(RolUsuario.ADMINISTRADOR_GENERAL);
        try {
            usuarioDao.create(usuario);
            return "/usuarios/index?faces-redirect=true";
        } catch (Exception e) {
            return null;
        }
    }

    public String update() {
        try {
            usuarioDao.update(usuario);
            return "/usuarios/index?faces-redirect=true";
        } catch (Exception e) {
            return null;
        }
    }

    public void delete(Usuario usuario) {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage message;
        if (usuario.getUsername().equals(session.getUsuario().getUsername())) {
            message = new FacesMessage(msg.getString("usuarios_eliminarASiMismo"));
        } else {
            message = new FacesMessage(msg.getString("usuarios_eliminarUsuarioEliminado", usuario.getUsername()));
            usuarioDao.delete(usuario);

        }
        context.addMessage("msgUsuario", message);
    }

    public String delete1(Usuario usuario) {
        if (!usuario.equals(session.getUsuario())) {
            usuarioDao.delete(usuario);
            FacesContext context = FacesContext.getCurrentInstance();
            FacesMessage message = new FacesMessage(msg.getString("usuarios_eliminarASiMismo"));
            context.addMessage(null, message);

            return null;
        } else {
            return null;
        }

    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
