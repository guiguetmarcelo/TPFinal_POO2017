package ar.edu.unnoba.poo2017.tpfinal_poo2017.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "gastos")
@NamedQueries({
    @NamedQuery(name = "gastos.disponibles", query = "SELECT g FROM Gasto g WHERE g.empresa=:empresa ORDER BY g.fecha"),
        @NamedQuery(name= "gastos.porperiodo", query= "SELECT g FROM Gasto g WHERE g.fecha BETWEEN :fechaDesde AND :fechaHasta AND g.empresa=:empresa ORDER BY g.fecha"),
        @NamedQuery(name="gastos.porcategoria", query="SELECT g FROM Gasto g WHERE g.subcategoria.categoriaPadre=:categoria AND g.empresa=:empresa ORDER BY g.fecha"),
        @NamedQuery(name="gastos.periodo_y_categoria", query="SELECT g FROM Gasto g WHERE g.empresa=:empresa AND g.subcategoria.categoriaPadre=:categoria AND  g.fecha BETWEEN :fechaDesde AND :fechaHasta ORDER BY g.fecha"),
         @NamedQuery(name="gastos.periodo_y_subcategoria", query="SELECT g FROM Gasto g WHERE g.empresa=:empresa AND g.subcategoria=:subcategoria AND  g.fecha BETWEEN :fechaDesde AND :fechaHasta ORDER BY g.fecha")
})
public class Gasto extends AbstractEntity implements Serializable {

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
