/* 
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90 

 */
package com.super_bits.modulosSB.webPaginas.JSFManagedBeans.declarados.util;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.grafico.ItfDadoGraficoTotal;
import com.super_bits.modulosSB.webPaginas.JSFBeans.PrimeFacesBeanModel.grafico.UtilGraficoPrimefaces;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWP_JSFTools;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.ChartModel;
import org.primefaces.model.charts.line.LineChartDataSet;
import org.primefaces.model.charts.line.LineChartModel;
import org.primefaces.model.charts.line.LineChartOptions;
import org.primefaces.model.charts.optionconfig.title.Title;
import org.primefaces.model.charts.pie.PieChartDataSet;
import org.primefaces.model.charts.pie.PieChartModel;
import org.primefaces.model.charts.pie.PieChartOptions;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ComoAcaoDoSistema;

/**
 *
 * @author salvio
 */
@RequestScoped
@Named
public class PgUtilGraficos {
    
    public void eventoitemSelect(ItemSelectEvent event) {
        int itemSelecionado = event.getItemIndex();
        int dataSetSElecionado = event.getDataSetIndex();
        
        int serieSelect = event.getSeriesIndex();
        
        Object origem= event.getSource();
        System.out.println(origem);
        //    UtilSBWP_JSFTools.vaParaPagina(url);
        

    }
    
    
    public LineChartModel gerarGraficoLinear(String pTitulo,List<ItfDadoGraficoTotal> pItensGrafico,ComoAcaoDoSistema pAcao){
        ChartData data = new ChartData();
        
        LineChartDataSet dataSet = new LineChartDataSet();
        List<Object> valores = new ArrayList<>();
        List<String> cores = new ArrayList<>();
        List<String> labels = new ArrayList<>();
        int indice = 0;
        for (ItfDadoGraficoTotal item : pItensGrafico) {
            valores.add(item.getValor());
            
            cores.add(UtilGraficoPrimefaces.getCor(indice == 0));
            labels.add(item.getLabel());
            if (item.getItemRelacionado()!=null){
                
            }
            if (pAcao != null) {
                if (item.isTotalPorTipo()) {
                    if (item.getItemRelacionado()==null){
                        String url = SBCore.getServicoVisualizacao().getEndrRemotoFormulario(pAcao.getEnumAcaoDoSistema());
                    }else {
                        String url = SBCore.getServicoVisualizacao().getEndrRemotoFormulario(pAcao.getEnumAcaoDoSistema(),
                            item);
                    }                   
                }
            }
            indice++;

        }
        dataSet.setBackgroundColor("#ffffff");
        dataSet.setData(valores);
        dataSet.setLabel(pTitulo);
        data.setLabels(labels);
        
        data.addChartDataSet(dataSet);
        
        LineChartOptions opcoes = new LineChartOptions();
        Title tituloGrafico = new Title();
        tituloGrafico.setText(pTitulo);
        opcoes.setTitle(tituloGrafico);
        LineChartModel graficoLinerar = new LineChartModel(data, opcoes);
          
        return graficoLinerar;
    }
    
    public PieChartModel gerarGraficoPizza(String pTitulo,List<ItfDadoGraficoTotal> pItensGrafico,ComoAcaoDoSistema pAcao) {
        
        ChartData data = new ChartData();
        
        PieChartDataSet dataSet = new PieChartDataSet();
        List<Number> valores = new ArrayList<>();
        List<String> cores = new ArrayList<>();
        List<String> labels = new ArrayList<>();
        int indice = 0;
        for (ItfDadoGraficoTotal item : pItensGrafico) {
            valores.add(item.getValor());
            
            cores.add(UtilGraficoPrimefaces.getCor(indice == 0));
            labels.add(item.getLabel());
            if (item.getItemRelacionado()!=null){
                
            }
            if (pAcao != null) {
                if (item.isTotalPorTipo()) {
                    if (item.getItemRelacionado()==null){
                        String url = SBCore.getServicoVisualizacao().getEndrRemotoFormulario(pAcao.getEnumAcaoDoSistema());
                    }else {
                        String url = SBCore.getServicoVisualizacao().getEndrRemotoFormulario(pAcao.getEnumAcaoDoSistema(),
                            item);
                    }                   
                }
            }
            indice++;

        }
        dataSet.setBackgroundColor(cores);
        dataSet.setData(valores);
    
        data.setLabels(labels);
        
        data.addChartDataSet(dataSet);
        
        PieChartOptions opcoes = new PieChartOptions();
        Title tituloGrafico = new Title();
        tituloGrafico.setText(pTitulo);
        opcoes.setTitle(tituloGrafico);
        PieChartModel graficoPizza = new PieChartModel(data, opcoes);
          
        return graficoPizza;
    }

    
    
}
