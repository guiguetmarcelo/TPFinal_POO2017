/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.edu.unnoba.poo2017.tpfinal.dao;

import ar.edu.unnoba.poo2017.tpfinal.model.Gasto;
import ar.edu.unnoba.poo2017.tpfinal.model.Usuario;
import java.util.List;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author jpgm
 */
@Stateless
public class GastoDao extends AbstractDAO<Gasto>{
    public GastoDao(){
        super(Gasto.class);
    }
    
    public List<Gasto> getGastos(){
        Query query = em.createNamedQuery("gasto.todos");
        return query.getResultList();
    }
}
