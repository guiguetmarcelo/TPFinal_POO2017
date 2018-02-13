/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unobba.poo2017.tpfinal_poo2017.controller;

import ar.edu.unnoba.poo2017.tpfinal_poo2017.bundle.MessagesBundle;
import ar.edu.unnoba.poo2017.tpfinal_poo2017.bundle.MessagesProducer;
import ar.edu.unnoba.poo2017.tpfinal_poo2017.dao.UsuarioDao;
import ar.edu.unnoba.poo2017.tpfinal_poo2017.model.Empresa;
import ar.edu.unnoba.poo2017.tpfinal_poo2017.model.Usuario;
import java.io.Serializable;
import java.util.PropertyResourceBundle;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author jpgm
 * @author Marcelo
 */
@SessionScoped
@Named
public class SessionBacking implements Serializable {

    private static final long serialVersionUID = 5687471496874331768L;

    private String email;
    private String password;
    private Usuario usuario;
    private Empresa empresa;

    @EJB
    private UsuarioDao usuarioDao;

    @Inject
    @MessagesBundle
    private transient PropertyResourceBundle msg;

    // @Inject
    //private MessagesProducer h;
    @PostConstruct
    public void init() {
    }

    /*
    public String login() {
        this.usuario = usuarioDao.getUsuarioActivo(this.username, this.password, this.empresa);
        if (this.usuario == null) {
            FacesContext context = FacesContext.getCurrentInstance();
            FacesMessage message = new FacesMessage(msg.getString("usuarios_loginUCIncorrectos"));
            context.addMessage(null, message);
            return null;
        }
        return "/index?faces-redirect=true";
    }*/
    
    public String login() {
        setUsuario(usuarioDao.getUsuarioActivo(getEmail()));
        if (getUsuario() != null && BCrypt.checkpw(getPassword(), getUsuario().getPassword())) {
            setEmpresa(getUsuario().getEmpresa());
            return "/index?faces-redirect=true";
        }else{
            FacesContext context = FacesContext.getCurrentInstance();
            FacesMessage message = new FacesMessage(msg.getString("usuarios_loginUCIncorrectos"));
            context.addMessage(null, message);
            setUsuario(null);
            return null;
        }
        
    }

    public String logout() {
        setUsuario(null);
        return "/login.xhtml?faces-redirect=true";
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }


}
