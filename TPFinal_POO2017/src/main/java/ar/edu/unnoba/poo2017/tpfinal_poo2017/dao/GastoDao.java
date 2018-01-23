/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unnoba.poo2017.tpfinal_poo2017.dao;

import ar.edu.unnoba.poo2017.tpfinal_poo2017.model.Categoria;
import ar.edu.unnoba.poo2017.tpfinal_poo2017.model.Gasto;
import ar.edu.unnoba.poo2017.tpfinal_poo2017.model.Periodo;
import ar.edu.unobba.poo2017.tpfinal_poo2017.controller.PeriodoBacking;
import ar.edu.unobba.poo2017.tpfinal_poo2017.controller.SessionBacking;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.Query;
import javax.persistence.TemporalType;

/**
 *
 * @author Sebastian
 */

@Stateless
public class GastoDao extends AbstractDAO<Gasto>{
    
    public GastoDao() {
        super(Gasto.class);
    }
    
    @Inject
    private SessionBacking sessionBacking;
    
    public List<Gasto> getGastos(){
        Query query = em.createNamedQuery("gastos.disponibles").setParameter("empresa", sessionBacking.getUsuario().getEmpresa());
        return query.getResultList();
    }
    
    public List<Gasto> getGastosPeriodo(Periodo periodo){
        
        Query query = em.createNamedQuery("gastos.porperiodo");
         query.setParameter("empresa", sessionBacking.getUsuario().getEmpresa());
        query.setParameter("fechaDesde", periodo.getFechaDesde(),TemporalType.TIMESTAMP);
        query.setParameter("fechaHasta", periodo.getFechaHasta(),TemporalType.TIMESTAMP);
        return query.getResultList();
    }
    
    public List<Gasto> getGastosCategoria(Categoria categoria){
        
        Query query = em.createNamedQuery("gastos.porcategoria");
         query.setParameter("empresa", sessionBacking.getUsuario().getEmpresa());
        query.setParameter("categoria", categoria);
        return query.getResultList();
    }
    
    public List<Gasto> getGastosPeriodoCategoria(Periodo periodo, Categoria categoria){
        
        Query query = em.createNamedQuery("gastos.periodo_y_categoria");
        query.setParameter("empresa", sessionBacking.getUsuario().getEmpresa());
        query.setParameter("fechaDesde", periodo.getFechaDesde(),TemporalType.TIMESTAMP);
        query.setParameter("fechaHasta", periodo.getFechaHasta(),TemporalType.TIMESTAMP);
        query.setParameter("categoria", categoria);
        return query.getResultList();
    }
}
