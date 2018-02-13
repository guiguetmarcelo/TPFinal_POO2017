/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unnoba.poo2017.tpfinal_poo2017.dao;

import ar.edu.unnoba.poo2017.tpfinal_poo2017.model.Categoria;
import ar.edu.unnoba.poo2017.tpfinal_poo2017.model.Empresa;
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
    
    public List<Presupuesto> getPresupuestos(Empresa empresa){
        Query query = em.createNamedQuery("presupuestos.disponibles").setParameter("empresa", empresa);
        return query.getResultList();
    }
    
    public List<Presupuesto> getPresupuestosCategoria(Categoria categoria,Empresa empresa){
        Query query= em.createNamedQuery("presupuestos.porcategoria").setParameter("empresa", empresa);
        query.setParameter("categoria", categoria);
        return query.getResultList();
    }
    
     public List<Presupuesto> getPresupuestosSubcategoriaPeriodo(Periodo periodo, Subcategoria subcategoria,Empresa empresa){
        Query query = em.createNamedQuery("presupuestos.porsubcategoriaperiodo").setParameter("empresa", empresa);
        query.setParameter("subcategoria", subcategoria);
        query.setParameter("periodo", periodo);
        return query.getResultList();
     }
     
     public Long getCantidad(){
         Query query = em.createNamedQuery("presupuestos.cantidad");
         return (Long) query.getSingleResult();
     }
     
     public Long getCantidad(Empresa empresa){
         Query query = em.createNamedQuery("presupuestos.cantidad.empresa").setParameter("empresa",empresa);
         return (Long) query.getSingleResult();
     }
    
}
