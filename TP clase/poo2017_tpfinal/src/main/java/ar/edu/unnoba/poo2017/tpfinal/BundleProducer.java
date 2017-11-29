/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unnoba.poo2017.tpfinal;

import java.util.PropertyResourceBundle;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;

/**
 *
 * @author jpgm
 */
@RequestScoped
public class BundleProducer {
    @Produces
    public PropertyResourceBundle getBundle() {
        FacesContext context = FacesContext.getCurrentInstance();
        return context.getApplication()
                .evaluateExpressionGet(context, "#{msg}",
                        PropertyResourceBundle.class);
    }
}
