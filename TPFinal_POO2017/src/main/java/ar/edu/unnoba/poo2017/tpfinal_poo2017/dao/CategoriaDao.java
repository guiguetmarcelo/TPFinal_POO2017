/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unnoba.poo2017.tpfinal_poo2017.dao;

import ar.edu.unnoba.poo2017.tpfinal_poo2017.model.Categoria;
import ar.edu.unobba.poo2017.tpfinal_poo2017.controller.SessionBacking;
import java.util.List;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.Query;

/**
 *
 * @author Sebastian
 */
@Stateless
public class CategoriaDao extends AbstractDAO<Categoria> {
    
    public CategoriaDao() {
        super(Categoria.class);
    }
        
    @Inject 
    private SessionBacking sessionBacking;
    
    public List<Categoria> getCategorias(){
        Query query = em.createNamedQuery("categoria.activas").setParameter("empresa", sessionBacking.getUsuario().getEmpresa());
         return query.getResultList();
    }
    
    
    @Override
    public void delete(Categoria c) throws EJBException {
        c.setBorrada(Boolean.TRUE);
        super.update(c);
    }
    
}
