/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unobba.poo2017.tpfinal_poo2017.controller;

import ar.edu.unnoba.poo2017.tpfinal_poo2017.dao.GastoDao;
import ar.edu.unnoba.poo2017.tpfinal_poo2017.dao.PresupuestoDao;
import ar.edu.unnoba.poo2017.tpfinal_poo2017.model.Categoria;
import ar.edu.unnoba.poo2017.tpfinal_poo2017.model.Gasto;
import ar.edu.unnoba.poo2017.tpfinal_poo2017.model.Periodo;
import ar.edu.unnoba.poo2017.tpfinal_poo2017.model.Presupuesto;
import ar.edu.unnoba.poo2017.tpfinal_poo2017.model.Subcategoria;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;

/**
 *
 * @author Sebastian
 */
@Named
@ViewScoped
public class reporteBacking implements Serializable {

    private Gasto gasto;
    private Periodo periodo;
    private Categoria categoria;
    private List<Gasto> filtrados;
    private LineChartModel reporteGrafico;
    private BarChartModel reporteComparativo;

    @EJB
    private GastoDao gastoDao;

    @EJB
    private PresupuestoDao presupuestoDao;

    @PostConstruct
    public void init() {
        setGasto(new Gasto());
        setPeriodo(new Periodo());
        setFiltrados(new ArrayList<Gasto>());
        setCategoria(new Categoria());

    }

    public BarChartModel getReporteComparativo() {
        return reporteComparativo;
    }

    public void setReporteComparativo(BarChartModel reporteComparativo) {
        this.reporteComparativo = reporteComparativo;
    }

    public LineChartModel getReporteGrafico() {
        return reporteGrafico;
    }

    public void setReporteGrafico(LineChartModel reporteGrafico) {
        this.reporteGrafico = reporteGrafico;
    }

    public Periodo getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public List<Gasto> getGastos() {
        return gastoDao.getGastos();
    }

    public List<Presupuesto> getPresupuestos() {
        return presupuestoDao.getPresupuestos();
    }

    public List<Gasto> getGastosPeriodo(Periodo periodo) {
        this.setFiltrados(gastoDao.getGastosPeriodo(periodo));
        return filtrados;
    }

    public List<Gasto> getGastosCategoria(Categoria categoria) {
        return gastoDao.getGastosCategoria(categoria);
    }

    public List<Gasto> getGastosPeriodoCategoria(Periodo periodo, Categoria categoria) {
        this.setFiltrados(gastoDao.getGastosPeriodoCategoria(periodo, categoria));
        return filtrados;
    }

    public Gasto getGasto() {
        return gasto;
    }

    public void setGasto(Gasto gasto) {
        this.gasto = gasto;
    }

    public List<Gasto> getFiltrados() {
        return filtrados;
    }

    public void setFiltrados(List<Gasto> filtrados) {
        this.filtrados = filtrados;
    }

    public float getTotalFiltrados(List<Gasto> elementos) {
        float total = 0;
        for (Gasto unGasto : elementos) {
            total += unGasto.getImporte();
        }
        return total;
    }
    
    public List<Presupuesto> getPresupuestosSubcategoriaPeriodo(Periodo periodo, Subcategoria subcategoria){
        return presupuestoDao.getPresupuestosSubcategoriaPeriodo(periodo, subcategoria);
    }

    public void graficoLineas(Categoria categoria) {
        LineChartModel model = new LineChartModel();
        ChartSeries presupuesto = new ChartSeries();
        ChartSeries gastos = new ChartSeries();
        model.setTitle("Desvio de gastos por categoria");
        List<Presupuesto> presupuestos = presupuestoDao.getPresupuestosCategoria(categoria);
        if (!presupuestos.isEmpty()) {
            for (Presupuesto pre : presupuestos) {
                presupuesto.set(pre.getPeriodo().toString(), pre.getMonto());
                this.getGastosPeriodoCategoria(pre.getPeriodo(), categoria);
                if (filtrados.isEmpty()) {
                    gastos.set(pre.getPeriodo().toString(), 0);
                }
                float total = this.getTotalFiltrados(filtrados);
                gastos.set(pre.getPeriodo().toString(), total);

            }
        } else {
            model.setTitle("La categoria seleccionada no presenta presupuestos");
            presupuesto.set("No hay datos", 0);
            gastos.set("No hay datos", 0);
        }

        gastos.setLabel("Gastado");
        model.addSeries(gastos);
        presupuesto.setLabel("Presupuestado");
        model.addSeries(presupuesto);
        model.setLegendPosition("e");
        Axis yAxis = model.getAxis(AxisType.Y);
        Axis xAxis = model.getAxis(AxisType.X);
        yAxis.setLabel("Importe");
        yAxis.setMin(0);
        model.setShowPointLabels(true);
        model.getAxes().put(AxisType.X, new CategoryAxis("Periodo"));
        this.setReporteGrafico(model);
    }

    public void graficoComparativo(Periodo periodo) {

        BarChartModel model = new BarChartModel();
        model.setExtender("ext1");
        model.setTitle("Comparativo de gastos por categoria en un periodo");
        model.setLegendPosition("ne");
        Axis xAxis = model.getAxis(AxisType.X);
        xAxis.setLabel("Categorias");
        Axis yAxis = model.getAxis(AxisType.Y);
        yAxis.setLabel("Total de gastos");
        yAxis.setMin(0);

        ChartSeries categorias = new ChartSeries();
        categorias.setLabel("Categorias");
        List<Gasto> elementos = this.getGastosPeriodo(periodo);
        if (!elementos.isEmpty()) {
            for (Gasto unGasto : elementos) {
                this.getGastosPeriodoCategoria(periodo, unGasto.getSubcategoria().getCategoriaPadre());
                float total = this.getTotalFiltrados(filtrados);
                categorias.set(unGasto.getSubcategoria().getCategoriaPadre().getNombre(), total);
            }
        } else {
            xAxis.setLabel("No hay datos para mostrar");
            categorias.set("0", 0);
            model.setTitle("No hay gastos en el periodo");
        }

        model.addSeries(categorias);
        this.setReporteComparativo(model);

    }
    
    public List<Gasto> getGastosPeriodoSubcategoria(Periodo periodo, Subcategoria subcategoria){
        return gastoDao.getGastosPeriodoSubcategoria(periodo, subcategoria);
    }
    
    public float getSubtotalGastos(Subcategoria sub){
        float total=0;
        for (Gasto unGasto: filtrados){
        if (unGasto.getSubcategoria().getNombre().equals(sub.getNombre()))
            total+=unGasto.getImporte();
        }
        return total;
        
    }
    
    public float getTotalPresupuestosFiltrados(Periodo periodo, Subcategoria sub){
        List<Presupuesto> filtrados= presupuestoDao.getPresupuestosSubcategoriaPeriodo(periodo, sub);
        float total=0;
        for(Presupuesto pre: filtrados){
            total+=pre.getMonto();
        }
        return total;
    }
            

}
