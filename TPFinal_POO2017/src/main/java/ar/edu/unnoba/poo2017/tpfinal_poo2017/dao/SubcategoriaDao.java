
package ar.edu.unnoba.poo2017.tpfinal_poo2017.dao;

import ar.edu.unnoba.poo2017.tpfinal_poo2017.model.Empresa;
import ar.edu.unnoba.poo2017.tpfinal_poo2017.model.Subcategoria;

import java.util.List;
import javax.ejb.Stateless;

import javax.persistence.Query;

/**
 *
 * @author Sebastian
 */
@Stateless
public class SubcategoriaDao extends AbstractDAO<Subcategoria> {

    public SubcategoriaDao() {
        super(Subcategoria.class);
    }

    public List<Subcategoria> getSubcategorias(Empresa empresa) {
        Query query = em.createNamedQuery("subcategoria.activas").setParameter("empresa", empresa);
        return query.getResultList();
    }

}
