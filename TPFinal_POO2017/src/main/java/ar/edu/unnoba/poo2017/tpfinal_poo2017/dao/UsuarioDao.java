/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unnoba.poo2017.tpfinal_poo2017.dao;

import ar.edu.unnoba.poo2017.tpfinal_poo2017.model.Usuario;
import java.util.List;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author jpgm
 */
@Stateless
public class UsuarioDao extends AbstractDAO<Usuario> {

    public UsuarioDao() {
        super(Usuario.class);
    }
    
    public List<Usuario> getUsuarios() {
        Query query = em.createNamedQuery("usuario.totales");
        return query.getResultList();
    }
    
    public List<Usuario> getUsuariosActivos() {
        Query query = em.createNamedQuery("usuario.disponibles");
        return query.getResultList();
    }
    
    public Usuario getUsuario(String username, String password) {
        Query query = em.createNamedQuery("usuario.por_username_y_password");
        query.setParameter("username", username);
        query.setParameter("password", password);
        List<Usuario> usuarios = query.getResultList();
        if (usuarios.isEmpty()) {
            return null;
        } else {
            return usuarios.get(0);            
        }
    }
    
    public Usuario getUsuarioActivo(String username, String password) {
        Query query = em.createNamedQuery("usuario.por_username_y_password_activo");
        query.setParameter("username", username);
        query.setParameter("password", password);
        List<Usuario> usuarios = query.getResultList();
        if (usuarios.isEmpty()) {
            return null;
        } else {
            return usuarios.get(0);            
        }
    }

    public void deleteF(Usuario t) throws EJBException{
        super.delete(t);
    }
    
    @Override
    public void delete(Usuario t) throws EJBException {
        t.setActivo(Boolean.FALSE);
        super.update(t);
    }
    
    
}
