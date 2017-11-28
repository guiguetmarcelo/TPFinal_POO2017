/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unobba.poo2017.tpfinal_poo2017.controller;

import ar.edu.unnoba.poo2017.tpfinal_poo2017.dao.UsuarioDao;
import ar.edu.unnoba.poo2017.tpfinal_poo2017.model.Usuario;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
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
        usuarioDao.delete(usuario);
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    
}
