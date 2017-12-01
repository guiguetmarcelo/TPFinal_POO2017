/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unnoba.poo2017.tpfinal_poo2017.dao;

import ar.edu.unnoba.poo2017.tpfinal_poo2017.model.Periodo;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author Sebastian
 */
@Stateless
public class PeriodoDao extends AbstractDAO<Periodo> {
    
    public PeriodoDao() {
        super(Periodo.class);
    }
    
    public List<Periodo> getPeriodos(){
        Query query = em.createNamedQuery("periodo.disponibles");
        return query.getResultList();
    }
    
    
}
