package ar.edu.unnoba.poo2017.tpfinal_poo2017.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "presupuestos")
public class Presupuesto extends AbstractEntity implements Serializable{
    
    @Basic(optional = false)
    @Column(nullable = false)
    private Double monto;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "periodo")
    private Periodo periodo;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "subcategoria")
    private Subcategoria subcategoria;

    public Presupuesto() {

    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public Periodo getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;
    }

    public Subcategoria getSubcategoria() {
        return subcategoria;
    }

    public void setSubcategoria(Subcategoria subcategoria) {
        this.subcategoria = subcategoria;
    }

}
