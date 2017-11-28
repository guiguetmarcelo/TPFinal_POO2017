package ar.edu.unnoba.poo2017.tpfinal_poo2017.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "gastos")
public class Gasto extends AbstractEntity implements Serializable{

    @Temporal(TemporalType.TIMESTAMP)
    @Basic(optional = false)
    @Column(nullable = false)
    private Date fecha;
    
    @Basic(optional = false)
    @Column(nullable = false)
    private Double importe;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "subcategoria")
    private Subcategoria subcategoria;

    public Gasto() {

    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Double getImporte() {
        return importe;
    }

    public void setImporte(Double importe) {
        this.importe = importe;
    }

    public Subcategoria getSubcategoria() {
        return subcategoria;
    }

    public void setSubcategoria(Subcategoria subcategoria) {
        this.subcategoria = subcategoria;
    }

}
