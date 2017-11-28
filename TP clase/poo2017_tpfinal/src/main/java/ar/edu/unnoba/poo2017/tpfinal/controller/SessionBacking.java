/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.edu.unnoba.poo2017.tpfinal.controller;

import ar.edu.unnoba.poo2017.tpfinal.dao.UsuarioDao;
import ar.edu.unnoba.poo2017.tpfinal.model.Usuario;
import java.io.Serializable;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author jpgm
 */
@SessionScoped
@Named
public class SessionBacking implements Serializable{
    private String userName;
    private String password;
    private Usuario usuario;
    
    @EJB
    private UsuarioDao usuarioDao;
    
    @Inject
    private transient PropertyResourceBundle bundle;
    
    @PostConstruct
    public void init(){
    }
    
    public String login(){
       this.usuario = usuarioDao.getUsuario(this.userName,this.password); 
       if(usuario == null){
           FacesContext context = FacesContext.getCurrentInstance();
           FacesMessage message = new FacesMessage(bundle.getString("user.login.invalid"));
           context.addMessage(null, message);
           return null;
       }
       return "usuarios/index?faces-redirect=true";
    }
    
    public String logout(){
        usuario = new Usuario();
        return "/index?faces-redirect=true";
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
    
    
}
