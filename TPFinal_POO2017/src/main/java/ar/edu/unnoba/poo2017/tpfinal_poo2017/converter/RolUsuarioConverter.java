/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unnoba.poo2017.tpfinal_poo2017.converter;

import ar.edu.unnoba.poo2017.tpfinal_poo2017.model.RolUsuario;
import javax.faces.convert.EnumConverter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Marcelo
 */
@FacesConverter(value="rolUsuarioConverter",forClass = RolUsuario.class)
public class RolUsuarioConverter extends EnumConverter{


    public RolUsuarioConverter() {
        super(RolUsuario.class);
    }

}

