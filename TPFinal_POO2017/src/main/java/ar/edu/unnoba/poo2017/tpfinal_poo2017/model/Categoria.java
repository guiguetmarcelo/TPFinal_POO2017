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

@Entity
@Table(name = "categorias")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("cat")
@NamedQueries({
   @NamedQuery(name = "categoria.disponibles",
           query = "Select c From Categoria c" )})
public class Categoria extends AbstractEntity implements Serializable {
    
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

}
