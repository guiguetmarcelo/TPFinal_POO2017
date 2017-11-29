/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unnoba.poo2017.tpfinal.controller;

import ar.edu.unnoba.poo2017.tpfinal.dao.UsuarioDao;
import ar.edu.unnoba.poo2017.tpfinal.model.Usuario;
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
    
    @PostConstruct
    public void init(){
        this.usuario = new Usuario();
    }
    
    @EJB
    private UsuarioDao usuarioDao;
    
    @Inject
    private SessionBacking sessionBacking;
    
    public List<Usuario> getUsuarios(){
        return usuarioDao.getUsuarios();
    }
    
    public String create(){
        try{
            usuarioDao.create(usuario);
            return "/usuarios/index?faces-redirect=true";
        }catch(Exception e){
            return null;
        }
    }
    
    public String update(){
        try{
            usuarioDao.update(usuario);
            return "/usuarios/index?faces-redirect=true";
        }catch(Exception e){
            return null;
        }
    }
    
    public void delete(Usuario usuario){
        if(!sessionBacking.getUsuario().equals(usuario)){
            usuarioDao.delete(usuario);
        }else{
           FacesContext context = FacesContext.getCurrentInstance();
           FacesMessage message = new FacesMessage("No puede borrar este usuario");
           context.addMessage(null, message);
        }
        
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    
}
