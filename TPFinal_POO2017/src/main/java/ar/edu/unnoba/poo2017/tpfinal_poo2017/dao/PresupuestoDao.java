/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unnoba.poo2017.tpfinal_poo2017.dao;

import ar.edu.unnoba.poo2017.tpfinal_poo2017.model.Categoria;
import ar.edu.unnoba.poo2017.tpfinal_poo2017.model.Periodo;
import ar.edu.unnoba.poo2017.tpfinal_poo2017.model.Presupuesto;
import ar.edu.unnoba.poo2017.tpfinal_poo2017.model.Subcategoria;
import ar.edu.unobba.poo2017.tpfinal_poo2017.controller.SessionBacking;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.Query;

/**
 *
 * @author Marcelo
 */

@Stateless
public class PresupuestoDao extends AbstractDAO<Presupuesto> {
        
    public PresupuestoDao() {
        super(Presupuesto.class);
    }
    
    @Inject
    private SessionBacking sessionBacking;
    
    public List<Presupuesto> getPresupuestos(){
        Query query = em.createNamedQuery("presupuestos.disponibles").setParameter("empresa", sessionBacking.getUsuario().getEmpresa());
        return query.getResultList();
    }
    
    public List<Presupuesto> getPresupuestosCategoria(Categoria categoria){
        Query query= em.createNamedQuery("presupuestos.porcategoria").setParameter("empresa", sessionBacking.getUsuario().getEmpresa());
        query.setParameter("categoria", categoria);
        return query.getResultList();
    }
    
     public List<Presupuesto> getPresupuestosSubcategoriaPeriodo(Periodo periodo, Subcategoria subcategoria){
        Query query = em.createNamedQuery("presupuestos.porsubcategoriaperiodo").setParameter("empresa", sessionBacking.getUsuario().getEmpresa());
        query.setParameter("subcategoria", subcategoria);
        query.setParameter("periodo", periodo);
        return query.getResultList();
     }
    
}
