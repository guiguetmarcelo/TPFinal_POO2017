package ar.edu.unnoba.poo2017.tpfinal_poo2017.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "periodos")
public class Periodo extends AbstractEntity implements Serializable {

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

}
