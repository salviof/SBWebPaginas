package com.super_bits.modulosSB.webPaginas.JSFBeans.SBBeanModel.chart;

import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeSimples;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.CampoEsperado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.charts.ItfBeanSimpleChart;

public class B_ChartSimples<T> extends EntidadeSimples implements ItfBeanSimpleChart {

    public B_ChartSimples() {
        super();
        adcionaCampoEsperado(new CampoEsperado(FabTipoAtributoObjeto.CHART_LABEL));
        adcionaCampoEsperado(new CampoEsperado(FabTipoAtributoObjeto.CHART_VALOR));
        adcionaCampoEsperado(new CampoEsperado(FabTipoAtributoObjeto.CHART_CATEGORIA));
    }

    @Override
    public String getLabel() {

        return (String) getValorByTipoCampoEsperado(FabTipoAtributoObjeto.CHART_LABEL);

    }

    @Override
    public Double getValor() {

        Double resposta = Double.valueOf(getValorByTipoCampoEsperado(FabTipoAtributoObjeto.CHART_VALOR).toString());
        return resposta;
    }

    @Override
    public String getCategoria() {

        return (String) getValorByTipoCampoEsperado(FabTipoAtributoObjeto.CHART_CATEGORIA);
    }

}
