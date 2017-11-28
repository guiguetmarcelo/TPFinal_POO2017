/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.edu.unnoba.poo2017.tpfinal.dao;

import ar.edu.unnoba.poo2017.tpfinal.model.Usuario;
import java.util.List;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author jpgm
 */
@Stateless
public class UsuarioDao extends AbstractDAO<Usuario>{
    public UsuarioDao(){
        super(Usuario.class);
    }
    
    public List<Usuario> getUsuarios(){
        Query query = em.createNamedQuery("usuario.disponibles");
        return query.getResultList();
    }
    
    public Usuario getUsuario(String username, String password){
        Query query = em.createNamedQuery("usuario.por_username_y_password");
        query.setParameter("username", username);
        query.setParameter("password", password);
        List<Usuario> usuarios = query.getResultList();
        if(usuarios.isEmpty()){
            return null;
        }else{
            return usuarios.get(0);    
        }
    }
    
    
}
