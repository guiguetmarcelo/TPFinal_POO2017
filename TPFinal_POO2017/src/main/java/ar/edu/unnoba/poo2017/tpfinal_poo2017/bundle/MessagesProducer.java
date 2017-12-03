/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unnoba.poo2017.tpfinal_poo2017.bundle;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.PropertyResourceBundle;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;

/**
 *
 * @author Marcelo
 */
@SessionScoped
public class MessagesProducer implements Serializable {

    @Produces
    @MessagesBundle
    public PropertyResourceBundle getBundle() {
        FacesContext context = FacesContext.getCurrentInstance();
        return context.getApplication().evaluateExpressionGet(context, "#{msg}", PropertyResourceBundle.class);
    }

    public String getString(String key) {
        try {
            return getBundle().getString(key);
        } catch (MissingResourceException e) {
            return '!' + key + '!';
        }
    }

    public String getString(String key, Object... params) {
        try {
            return MessageFormat.format(getBundle().getString(key), params);
        } catch (MissingResourceException e) {
            return '!' + key + '!';
        }
    }

}
