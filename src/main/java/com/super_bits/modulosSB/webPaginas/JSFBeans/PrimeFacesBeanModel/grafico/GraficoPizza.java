package com.super_bits.modulosSB.webPaginas.JSFBeans.PrimeFacesBeanModel.grafico;

import com.super_bits.modulosSB.SBCore.ConfigGeral.CarameloCode;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ComoFabricaAcoes;
import com.super_bits.modulosSB.SBCore.modulos.grafico.ItemGraficoTotalPorTipo;
import com.super_bits.modulosSB.SBCore.modulos.grafico.ItfDadoGraficoTotal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.optionconfig.title.Title;
import org.primefaces.model.charts.pie.PieChartDataSet;
import org.primefaces.model.charts.pie.PieChartModel;
import org.primefaces.model.charts.pie.PieChartOptions;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWP_JSFTools;

/**
 *
 * @author salvio
 */
public class GraficoPizza {

    private final Map<Integer, String> mapaUrls = new HashMap<>();
    private PieChartModel grafico;
    private final ComoFabricaAcoes acaoUrl;
    private final List<ItfDadoGraficoTotal> itensGrafico;
    private final String titulo;

    public GraficoPizza(final List<ItfDadoGraficoTotal> pValores, String pTitulo, ComoFabricaAcoes pAcaoUrl) {
        itensGrafico = pValores;
        acaoUrl = pAcaoUrl;
        titulo = pTitulo;
        buildPizza();

    }

    public GraficoPizza(final List<ItfDadoGraficoTotal> pValores, String pTitulo) {
        this(pValores, pTitulo, null);
    }

    public void eventoitemSelect(ItemSelectEvent event) {
        int itemSelecionado = event.getItemIndex();
        int dataSetSElecionado = event.getDataSetIndex();
        int serieSelect = event.getSeriesIndex();

        if (mapaUrls.containsKey(itemSelecionado)) {
            String url = mapaUrls.get(itemSelecionado);
            UtilSBWP_JSFTools.vaParaPagina(url);
        } else {
            SBCore.getServicoMensagens().enviarMsgAvisoAoUsuario("Sem formulário vinculado");
        }

    }

    private void buildPizza() {
        ChartData data = new ChartData();
        PieChartDataSet dataSet = new PieChartDataSet();
        List<Number> valores = new ArrayList<>();
        List<String> cores = new ArrayList<>();
        List<String> labels = new ArrayList<>();
        int indice = 0;
        for (ItfDadoGraficoTotal item : this.itensGrafico) {
            valores.add(item.getValor());
            cores.add(UtilGraficoPrimefaces.getCor(indice == 0));
            labels.add(item.getLabel());
            if (acaoUrl != null) {
                if (item.isTotalPorTipo()) {
                    if (item instanceof ItemGraficoTotalPorTipo) {

                        mapaUrls.put(indice, ((ItemGraficoTotalPorTipo) item).getUrlDetalhes());

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
        tituloGrafico.setText(titulo);
        opcoes.setTitle(tituloGrafico);
        PieChartModel graficoPizza = new PieChartModel(data, opcoes);

        grafico = graficoPizza;
    }

    public PieChartModel getGrafico() {
        return grafico;
    }

    @Override
    public String toString() {
        StringBuilder nomeGrafico = new StringBuilder();
        nomeGrafico.append(titulo);
        try {
            itensGrafico.stream().map(it -> it.getNome() + it.getValor()).forEach(nomeGrafico::append);
        } catch (Throwable t) {

        }
        return String.valueOf(nomeGrafico.toString().hashCode());
    }

    public boolean isTemDados() {
        return !(itensGrafico == null || itensGrafico.isEmpty());
    }
}
