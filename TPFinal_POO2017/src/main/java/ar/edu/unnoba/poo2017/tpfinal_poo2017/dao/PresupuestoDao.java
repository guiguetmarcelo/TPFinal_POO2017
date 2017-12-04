/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unnoba.poo2017.tpfinal_poo2017.dao;

import ar.edu.unnoba.poo2017.tpfinal_poo2017.model.Presupuesto;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author Sebastian
 */
public class PresupuestoDao extends AbstractDAO<Presupuesto> {
        
    public PresupuestoDao() {
        super(Presupuesto.class);
    }
    
    public List<Presupuesto> getPresupuestos(){
        Query query = em.createNamedQuery("presupuestos.disponibles");
        return query.getResultList();
    }
}
