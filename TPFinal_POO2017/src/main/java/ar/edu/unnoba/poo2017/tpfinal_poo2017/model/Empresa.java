
package ar.edu.unnoba.poo2017.tpfinal_poo2017.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Sebastian
 */

@Entity
@Table(name = "empresas")
@NamedQueries({
    @NamedQuery(name = "empresas.disponibles", query = "SELECT e FROM Empresa e")
})
public class Empresa implements Serializable{

    private static final long serialVersionUID = 1064660939313009277L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    @Basic(optional = false)
    @Column(nullable = false, unique = true)
    private String nombre;
    
    @Basic(optional = false)
    @Column(nullable = false)
    private long telefono;
    
    @Basic(optional = false)
    @Column(nullable = false)
    private String descripcion;

    public Empresa(String nombre, long telefono, String descripcion) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.descripcion = descripcion;
    }

    public Empresa(){
    }
    
    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the telefono
     */
    public long getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Empresa)) {
            return false;
        }
        Empresa other = (Empresa) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.getId().equals(other.getId()))) {
            return false;
        }
        return true;
    }

    
    
}
