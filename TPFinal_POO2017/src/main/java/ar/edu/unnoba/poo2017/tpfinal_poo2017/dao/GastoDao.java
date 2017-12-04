/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unnoba.poo2017.tpfinal_poo2017.dao;

import ar.edu.unnoba.poo2017.tpfinal_poo2017.model.Gasto;
import javax.ejb.Stateless;

/**
 *
 * @author Sebastian
 */

@Stateless
public class GastoDao extends AbstractDAO<Gasto>{
    
    public GastoDao() {
        super(Gasto.class);
    }
    
}
