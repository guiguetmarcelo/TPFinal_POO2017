
package ar.edu.unnoba.poo2017.tpfinal_poo2017.dao;

import ar.edu.unnoba.poo2017.tpfinal_poo2017.model.Categoria;
import ar.edu.unnoba.poo2017.tpfinal_poo2017.model.Empresa;
import ar.edu.unnoba.poo2017.tpfinal_poo2017.model.Gasto;
import ar.edu.unnoba.poo2017.tpfinal_poo2017.model.Periodo;
import ar.edu.unnoba.poo2017.tpfinal_poo2017.model.Subcategoria;
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
    
    public List<Gasto> getGastos(Empresa empresa){
        Query query = em.createNamedQuery("gastos.disponibles").setParameter("empresa", empresa);
        return query.getResultList();
    }
    
    public List<Gasto> getGastosPeriodo(Periodo periodo, Empresa empresa){
        
        Query query = em.createNamedQuery("gastos.porperiodo");
         query.setParameter("empresa", empresa);
        query.setParameter("fechaDesde", periodo.getFechaDesde(),TemporalType.TIMESTAMP);
        query.setParameter("fechaHasta", periodo.getFechaHasta(),TemporalType.TIMESTAMP);
        return query.getResultList();
    }
    
    public List<Gasto> getGastosCategoria(Categoria categoria, Empresa empresa){
        
        Query query = em.createNamedQuery("gastos.porcategoria");
         query.setParameter("empresa", empresa);
        query.setParameter("categoria", categoria);
        return query.getResultList();
    }
    
    public List<Gasto> getGastosPeriodoCategoria(Periodo periodo, Categoria categoria, Empresa empresa){
        
        Query query = em.createNamedQuery("gastos.periodo_y_categoria");
        query.setParameter("empresa", empresa);
        query.setParameter("fechaDesde", periodo.getFechaDesde(),TemporalType.TIMESTAMP);
        query.setParameter("fechaHasta", periodo.getFechaHasta(),TemporalType.TIMESTAMP);
        query.setParameter("categoria", categoria);
        return query.getResultList();
    }
    
     public List<Gasto> getGastosPeriodoSubcategoria(Periodo periodo, Subcategoria subcategoria, Empresa empresa){
        
        Query query = em.createNamedQuery("gastos.periodo_y_subcategoria");
        query.setParameter("empresa", empresa);
        query.setParameter("fechaDesde", periodo.getFechaDesde(),TemporalType.TIMESTAMP);
        query.setParameter("fechaHasta", periodo.getFechaHasta(),TemporalType.TIMESTAMP);
        query.setParameter("subcategoria", subcategoria);
        return query.getResultList();
    }
     
       public Long getCantidad(){
         Query query = em.createNamedQuery("gastos.cantidad");
         return (Long) query.getSingleResult();
     }
     
     public Long getCantidad(Empresa empresa){
         Query query = em.createNamedQuery("gastos.cantidad.empresa").setParameter("empresa",empresa);
         return (Long) query.getSingleResult();
     }
}
