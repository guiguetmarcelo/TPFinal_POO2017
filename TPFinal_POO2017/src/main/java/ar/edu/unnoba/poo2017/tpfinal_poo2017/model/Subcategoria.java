package ar.edu.unnoba.poo2017.tpfinal_poo2017.model;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "categorias")
@DiscriminatorValue("subcat")
@NamedQueries({
   @NamedQuery(name = "subcategoria.disponibles",
           query = "Select c From Subcategoria c" ), 
    @NamedQuery(name = "subcategoria.activas",
           query = "Select c From Subcategoria c WHERE c.borrada=FALSE and c.empresa=:empresa")
})
public class Subcategoria extends Categoria implements Serializable {

    private static final long serialVersionUID = 6106926566118308026L;

    @ManyToOne(optional = false)
    @JoinColumn(name = "categoria_id", referencedColumnName = "id")
    private Categoria categoriaPadre;

    public Subcategoria() {

    }

    public Categoria getCategoriaPadre() {
        return categoriaPadre;
    }

    public void setCategoriaPadre(Categoria categoriaPadre) {
        this.categoriaPadre = categoriaPadre;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Subcategoria)) {
            return false;
        }
        Subcategoria other = (Subcategoria) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.getId().equals(other.getId()))) {
            return false;
        }
        return true;
    }

}
