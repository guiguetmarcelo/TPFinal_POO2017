/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unnoba.poo2017.tpfinal_poo2017.model;

import javax.persistence.Basic;
import javax.persistence.Column;

/**
 *
 * @author Marcelo
 */

public enum RolUsuario {
    ADMINISTRADOR_GENERAL, 
    ADMINISTRADOR_EMPRESA;
    
    RolUsuario() {
    }
    
    public Boolean isAdmGen(){
        return ADMINISTRADOR_GENERAL == this;
    }
    
    public Boolean isAdmEmp(){
        return ADMINISTRADOR_EMPRESA == this;
    }
    
}
