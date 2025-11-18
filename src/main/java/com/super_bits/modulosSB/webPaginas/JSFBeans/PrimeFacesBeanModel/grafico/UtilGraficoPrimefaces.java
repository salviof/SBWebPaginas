/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.PrimeFacesBeanModel.grafico;

import com.super_bits.modulosSB.SBCore.modulos.fabrica.ComoFabricaAcoes;
import com.super_bits.modulosSB.SBCore.modulos.grafico.ItfDadoGraficoTotal;
import com.super_bits.modulosSB.SBCore.modulos.grafico.ItfDadosGraficoSequencia;
import java.util.List;
import org.primefaces.model.charts.line.LineChartDataSet;
import org.primefaces.model.charts.line.LineChartModel;

/**
 *
 * @author salvio
 */
public class UtilGraficoPrimefaces {

    private static int indice = 0;

    private static final String[] cores = {"#223A59", "#D5E7F2", "#91A672", "#AFBF36", "#CDD977", "#F2E85C", "#F2C777", "#A65874", "#A68D44", "#F27B13", "#D97014", "#59342C"};

    public synchronized static String getCor(boolean pReiniciar) {
        if (pReiniciar) {
            indice = 0;
        }
        try {
            try {

                return cores[indice];
            } catch (Throwable t) {
                indice = 0;
                return cores[indice];
            }
        } finally {
            indice++;
            if (indice > cores.length) {
                indice = 0;
            }
        }
    }

    public static GraficoPizza gerarGraficoPizza(final List<ItfDadoGraficoTotal> pDadoGrafico, String pTitulo) {

        return new GraficoPizza(pDadoGrafico, pTitulo, null);
    }

    public static GraficoPizza gerarGraficoPizza(final List<ItfDadoGraficoTotal> pDadoGrafico, String pTitulo, ComoFabricaAcoes pAcao) {

        return new GraficoPizza(pDadoGrafico, pTitulo, pAcao);
    }

    public static LineChartModel gerarGraficoSPgclienteAdminequenciaLinear(final List<ItfDadosGraficoSequencia> pDadosGraficoLinear) {
        LineChartModel lineModel = new LineChartModel();
        LineChartDataSet dataSet = new LineChartDataSet();
        return null;
    }

}
