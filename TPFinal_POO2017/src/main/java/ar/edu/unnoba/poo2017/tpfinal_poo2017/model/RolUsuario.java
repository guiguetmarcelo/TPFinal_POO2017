
package ar.edu.unnoba.poo2017.tpfinal_poo2017.model;

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
