package com.super_bits.modulosSB.webPaginas.JSFBeans.PrimeFacesBeanModel.charts;

import com.super_bits.modulosSB.Persistencia.dao.SBNQ;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoEntidadeSimples;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.charts.ComoEntidadeSimpleChart;

public class BP_ChartBarras<T extends ComoEntidadeSimples> extends BP_ChartLinear {

    public BP_ChartBarras(String pNome, SBNQ[] dados,
            Class<? extends ComoEntidadeSimpleChart> tipo) {
        super(pNome, dados, tipo);

    }

}
