/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unnoba.poo2017.tpfinal_poo2017.dao;

import ar.edu.unnoba.poo2017.tpfinal_poo2017.model.Subcategoria;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author Sebastian
 */
@Stateless
public class SubcategoriaDao extends AbstractDAO<Subcategoria> {
    
    public SubcategoriaDao() {
        super(Subcategoria.class);
    }
    
    public List<Subcategoria> getSubcategorias(){
        Query query = em.createNamedQuery("subcategoria.disponibles");
        return query.getResultList();
    }
    
}
