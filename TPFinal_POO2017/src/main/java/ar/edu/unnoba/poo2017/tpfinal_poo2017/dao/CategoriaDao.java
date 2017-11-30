/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unnoba.poo2017.tpfinal_poo2017.dao;

import ar.edu.unnoba.poo2017.tpfinal_poo2017.model.Categoria;
import java.util.List;
import javax.ejb.Stateless;
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
    
    public List<Categoria> getCategorias(){
        Query query = em.createNamedQuery("categoria.disponibles");
        return query.getResultList();
    }
    
}
