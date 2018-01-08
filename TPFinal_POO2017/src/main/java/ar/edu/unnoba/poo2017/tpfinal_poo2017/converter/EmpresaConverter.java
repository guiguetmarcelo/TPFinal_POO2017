/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unnoba.poo2017.tpfinal_poo2017.converter;

import ar.edu.unnoba.poo2017.tpfinal_poo2017.model.Empresa;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Sebastian
 */
@FacesConverter(forClass=Empresa.class)
public class EmpresaConverter extends AbstractConverter<Empresa>{
    @Override
    public String getDAOName() {
        return "EmpresaDao";
    }
    
    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Long id = ((Empresa) value).getId();
        return id == null ? "0" : id.toString();
    }
    
}
