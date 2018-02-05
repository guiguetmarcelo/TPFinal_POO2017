package ar.edu.unnoba.poo2017.tpfinal_poo2017.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "periodos")
@NamedQueries({
   @NamedQuery(name = "periodo.disponibles",
           query = "Select p From Periodo p WHERE p.empresa=:empresa ORDER BY p.fechaDesde")})
public class Periodo extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = 1262288828394353574L;

    @Temporal(TemporalType.TIMESTAMP)
    @Basic(optional = false)
    @Column(name = "fecha_desde", nullable = false)
    private Date fechaDesde;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Basic
    @Column(name = "fecha_hasta")
    private Date fechaHasta;
    
    @Basic(optional = false)
    @Column(nullable = false)
    private String descripcion;

    public Periodo() {

    }

    public Date getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(Date fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public Date getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(Date fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    @Override
    public String toString(){
        String desde = new SimpleDateFormat("dd-MM-yyyy").format(fechaDesde);
        String hasta = new SimpleDateFormat("dd-MM-yyyy").format(fechaHasta);
        return "Desde "+desde+" hasta "+hasta;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Periodo other = (Periodo) obj;
        if (this.getId() == null || other.getId() == null || !Objects.equals(this.getId(), other.getId())) {
            return false;
        }
        return true;
    }

    
}
