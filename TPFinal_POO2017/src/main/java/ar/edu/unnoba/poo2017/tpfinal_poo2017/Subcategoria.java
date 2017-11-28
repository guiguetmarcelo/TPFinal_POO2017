package ar.edu.unnoba.poo2017.tpfinal_poo2017;

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
import javax.persistence.Table;

@Entity
@Table(name = "categorias")
@DiscriminatorValue("subcat")
public class Subcategoria extends Categoria implements Serializable {

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

}
