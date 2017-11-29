/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.edu.unnoba.poo2017.tpfinal.dao;

import javax.ejb.EJBException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author poo
 * @param <T>
 */
public abstract class AbstractDAO<T> {
    final Class<T> typeParameterClass;

    @PersistenceContext(unitName = "PU")
    protected  EntityManager em;
    
    public AbstractDAO(Class<T> type){
        this.typeParameterClass = type;
    }
    
    public void create(T t) throws EJBException{
        em.persist(t);
    }
    
    public void update(T t) throws EJBException{
        em.merge(t);
    }
    
    public void delete(T t) throws EJBException{
        em.remove(em.contains(t) ? t : em.merge(t));
    }
    
    public T find(Long id){
        return em.find(typeParameterClass, id);
    }
}
