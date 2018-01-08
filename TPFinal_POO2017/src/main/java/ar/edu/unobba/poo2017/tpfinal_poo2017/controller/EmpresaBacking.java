/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unobba.poo2017.tpfinal_poo2017.controller;

import ar.edu.unnoba.poo2017.tpfinal_poo2017.dao.EmpresaDao;
import ar.edu.unnoba.poo2017.tpfinal_poo2017.model.Empresa;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Sebastian
 */

@Named
@ViewScoped
public class EmpresaBacking implements Serializable{
    
    private Empresa empresa;

    @PostConstruct
    public void init() {
        this.empresa = new Empresa();
    }

    @EJB
    private EmpresaDao empresaDao;

    public List<Empresa> getEmpresas() {
        return empresaDao.getEmpresas();
    }

    public String create() {
        try {
            empresaDao.create(empresa);
            return "/empresas/index?faces-redirect=true";
        } catch (Exception e) {
            return null;
        }
    }

    public String update() {
        try {
            empresaDao.update(empresa);
            return "/empresas/index?faces-redirect=true";
        } catch (Exception e) {
            return null;
        }
    }

    
     public void delete(Empresa empresa) {
         try{
        empresaDao.delete(empresa);
         }catch(Exception e) {}
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    
}
