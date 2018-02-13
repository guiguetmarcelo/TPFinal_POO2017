
package ar.edu.unnoba.poo2017.tpfinal_poo2017.dao;

import ar.edu.unnoba.poo2017.tpfinal_poo2017.model.Empresa;
import ar.edu.unnoba.poo2017.tpfinal_poo2017.model.Usuario;
import java.util.List;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author jpgm
 */
@Stateless
public class UsuarioDao extends AbstractDAO<Usuario> {

    public UsuarioDao() {
        super(Usuario.class);
    }
    
    public List<Usuario> getUsuarios() {
        Query query = em.createNamedQuery("usuario.totales");
        return query.getResultList();
    }
    
    public List<Usuario> getUsuariosActivos() {
        Query query = em.createNamedQuery("usuario.disponibles");
        return query.getResultList();
    }
    
    public List<Usuario> getUsuarios(Empresa empresa) {
        Query query = em.createNamedQuery("usuario.totales.por_empresa");
        query.setParameter("empresa", empresa);
        return query.getResultList();
    }
    
    public List<Usuario> getUsuariosActivos(Empresa empresa) {
        Query query = em.createNamedQuery("usuario.disponibles.por_empresa");
        query.setParameter("empresa", empresa);
        return query.getResultList();
    }
    
    public Usuario getUsuarioActivo(String email) {
        Query query = em.createNamedQuery("usuario.por_email_activo");
        query.setParameter("email", email);
        List<Usuario> usuarios = query.getResultList();
        if (usuarios.isEmpty()) {
            return null;
        } else {
            return usuarios.get(0);            
        }
    }
    
    public Usuario getUsuarioActivo(long id) {
        Query query = em.createNamedQuery("usuario.por_id_activo");
        query.setParameter("id", id);
        List<Usuario> usuarios = query.getResultList();
        if (usuarios.isEmpty()) {
            return null;
        } else {
            return usuarios.get(0);            
        }
    }

    public void deleteF(Usuario t) throws EJBException{
        super.delete(t);
    }
    
    @Override
    public void delete(Usuario t) throws EJBException {
        t.setActivo(Boolean.FALSE);
        super.update(t);
    }
    
    
}
