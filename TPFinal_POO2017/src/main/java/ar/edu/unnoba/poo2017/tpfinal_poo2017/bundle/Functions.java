/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unnoba.poo2017.tpfinal_poo2017.bundle;

import java.text.MessageFormat;
import java.util.Collection;

/**
 *
 * @author Marcelo
 */
public final class Functions {

    public Functions() {
    }

    public static String textFormat(String text, Object p1){
        return MessageFormat.format(text, p1);
    }
    
    public static String textFormat2(String text, Object p1, Object p2){
        return MessageFormat.format(text, new Object[]{p1,p2});
    }
    
    public static String textFormat3(String text, Object p1, Object p2, Object p3){
        return MessageFormat.format(text, new Object[]{p1,p2,p3});
    }
    
    public static String textFormat4(String text, Object p1, Object p2, Object p3, Object p4){
        return MessageFormat.format(text, new Object[]{p1,p2,p3,p4});
    }
   
    public static String textFormatCollection(String text, Collection<Object> params) {
        return MessageFormat.format(text, params.toArray(new Object[0]));
    }
}
