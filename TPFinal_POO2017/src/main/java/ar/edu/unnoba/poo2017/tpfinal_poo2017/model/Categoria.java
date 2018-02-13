package ar.edu.unnoba.poo2017.tpfinal_poo2017.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author Marcelo
 */
@Entity
@Table(name = "categorias",
         uniqueConstraints={@UniqueConstraint(columnNames={"nombre", "empresa", "tipo"})})
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("cat")
@NamedQueries({
   @NamedQuery(name = "categoria.disponibles",
           query = "Select c From Categoria c WHERE TYPE(c)= Categoria"), 
     @NamedQuery(name = "categoria.activas",
           query = "Select c From Categoria c WHERE TYPE(c)= Categoria AND c.borrada=FALSE AND c.empresa=:empresa")

})
public class Categoria extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = -1295040675146840881L;
    
    @Basic(optional = false)
    @Column(nullable = false)
    private String nombre;
    
    @Basic(optional = false)
    @Column(nullable = false)
    private String descripcion;
    
    @Basic(optional = false)
    @Column(nullable = false)
    private Boolean borrada = false;

    public Categoria() {

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getBorrada() {
        return borrada;
    }

    public void setBorrada(Boolean borrada) {
        this.borrada = borrada;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Categoria)) {
            return false;
        }
        Categoria other = (Categoria) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.getId().equals(other.getId()))) {
            return false;
        }
        return true;
    }

}
