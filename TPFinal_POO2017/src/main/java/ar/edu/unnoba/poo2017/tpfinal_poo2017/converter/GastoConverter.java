/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.edu.unnoba.poo2017.tpfinal_poo2017.converter;

import ar.edu.unnoba.poo2017.tpfinal_poo2017.model.Gasto;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author jpgm
 */
@FacesConverter(forClass=Gasto.class)
public class GastoConverter extends AbstractConverter<Gasto>{

    @Override
    public String getDAOName() {
        return "GastoDao";
    }
    
}
