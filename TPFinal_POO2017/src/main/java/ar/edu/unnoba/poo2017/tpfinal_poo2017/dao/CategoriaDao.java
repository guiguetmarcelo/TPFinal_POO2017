
package ar.edu.unnoba.poo2017.tpfinal_poo2017.dao;

import ar.edu.unnoba.poo2017.tpfinal_poo2017.model.Categoria;
import ar.edu.unnoba.poo2017.tpfinal_poo2017.model.Empresa;
import java.util.List;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author Sebastian
 */
@Stateless
public class CategoriaDao extends AbstractDAO<Categoria> {
    
    public CategoriaDao() {
        super(Categoria.class);
    }
    
    public List<Categoria> getCategorias(Empresa empresa){
        Query query = em.createNamedQuery("categoria.activas").setParameter("empresa", empresa);
         return query.getResultList();
    }
    
    
    @Override
    public void delete(Categoria c) throws EJBException {
        c.setBorrada(Boolean.TRUE);
        super.update(c);
    }
    
}
