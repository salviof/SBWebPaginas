package com.super_bits.modulosSB.webPaginas.JSFBeans.PrimeFacesBeanModel.charts;

import com.super_bits.modulosSB.Persistencia.dao.DaoGenerico;
import com.super_bits.modulosSB.Persistencia.dao.SBNQ;
import com.super_bits.modulosSB.Persistencia.dao.SBNQ.TipoObj;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoEntidadeSimples;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.charts.ComoEntidadeSimpleChart;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.charts.ChartSeries;
import org.primefaces.model.charts.line.LineChartDataSet;
import org.primefaces.model.charts.line.LineChartModel;

public class BP_ChartLinear<T extends ComoEntidadeSimples> {

    private LineChartModel dadosGrafico;
    private String nomeGrafico = "";
    private double minimo = 0;
    private double maximo = 0;
    private Class<?> classe;

    public BP_ChartLinear(String pNome, List<ComoEntidadeSimpleChart>[] listas,
            Class<? extends ComoEntidadeSimpleChart> tipo) {
        classe = tipo;
        nomeGrafico = pNome;
        setDadosGrafico(new LineChartModel());
        for (List<ComoEntidadeSimpleChart> lista : listas) {
            adcionaSerie(lista);
        }
    }

    public BP_ChartLinear(String pNome,
            Class<? extends ComoEntidadeSimpleChart> tipo) {
        classe = tipo;
        nomeGrafico = pNome;
        setDadosGrafico(new LineChartModel());
    }

    public void adcionaSerie(List<? extends ComoEntidadeSimpleChart> listacat) {
        if (listacat != null & listacat.size() > 0) {
            LineChartDataSet serie = new LineChartDataSet();

            serie.setLabel(listacat.get(0).getCategoria());
            List<Object> valores = new ArrayList<>();
            List<Object> nomes = new ArrayList<>();
            for (ComoEntidadeSimpleChart item : listacat) {
                if (item.getValor() < getMinimo()) {
                    setMinimo(item.getValor());
                }
                if (item.getValor() > getMaximo()) {
                    setMaximo(item.getValor());
                }
                valores.add(item.getValor());
                nomes.add(item.getLabel());
            }// para cada registro
            serie.setData(valores);
            getDadosGrafico().getData().addChartDataSet(serie);
        }// se tiver algo na lista
    }

    public void adcionaSeries(List<? extends ComoEntidadeSimpleChart> plistas[]) {
        for (List<? extends ComoEntidadeSimpleChart> serie : plistas) {
            adcionaSerie(serie);
        }
    }

    public void adcionaSerie(SBNQ qr) {

        DaoGenerico<T> daoRe = new DaoGenerico<T>(classe);
        List<ComoEntidadeSimpleChart>[] catgraficoLiner = new ArrayList[1];

        if (qr.getTipolista() == TipoObj.ENTIDADE) {
            catgraficoLiner = new ArrayList[1];
            catgraficoLiner[0] = (List<ComoEntidadeSimpleChart>) daoRe.achaItemPorSBNQ(qr);
        }
        if (qr.getTipolista() == TipoObj.SBQUERY) {

            if (qr.isMinMedMaximo()) {
                catgraficoLiner = new ArrayList[3];
                catgraficoLiner[0] = (List<ComoEntidadeSimpleChart>) qr.getSbQuery()
                        .getMinimo();
                catgraficoLiner[1] = (List<ComoEntidadeSimpleChart>) qr.getSbQuery()
                        .getMedia();
                catgraficoLiner[2] = (List<ComoEntidadeSimpleChart>) qr.getSbQuery()
                        .getMaximo();
            } else {
                catgraficoLiner = new ArrayList[1];
                catgraficoLiner[0] = (List<ComoEntidadeSimpleChart>) qr.getSbQuery()
                        .retorno();
            }

        }
        for (List<ComoEntidadeSimpleChart> serie : catgraficoLiner) {
            adcionaSerie(serie);
        }
    }

    public BP_ChartLinear(String pNome, SBNQ[] dados,
            Class<? extends ComoEntidadeSimpleChart> tipo) {
        classe = tipo;
        setDadosGrafico(new LineChartModel());
        nomeGrafico = pNome;

        for (SBNQ qr : dados) {
            adcionaSerie(qr);
        }
    }

    public LineChartModel getDadosGrafico() {
        return dadosGrafico;
    }

    public void setDadosGrafico(LineChartModel dadosGrafico) {
        this.dadosGrafico = dadosGrafico;
    }

    public double getMinimo() {
        return minimo;
    }

    public void setMinimo(double minimo) {
        this.minimo = minimo;
    }

    public double getMaximo() {
        return maximo;
    }

    public void setMaximo(double maximo) {
        this.maximo = maximo;
    }

    public String getNomeGrafico() {
        return nomeGrafico;
    }

    public void setNomeGrafico(String nomeGrafico) {
        this.nomeGrafico = nomeGrafico;
    }

}
