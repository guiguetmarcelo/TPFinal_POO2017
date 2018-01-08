/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unnoba.poo2017.tpfinal_poo2017.dao;

import ar.edu.unnoba.poo2017.tpfinal_poo2017.model.Empresa;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author Sebastian
 */

@Stateless
public class EmpresaDao extends AbstractDAO<Empresa>{
    
    public EmpresaDao() {
        super(Empresa.class);
    }
    
    public List<Empresa> getEmpresas(){
        Query query = em.createNamedQuery("empresas.disponibles");
        return query.getResultList();
    }
    
}
