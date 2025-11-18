package com.super_bits.modulosSB.webPaginas.JSFBeans.PrimeFacesBeanModel.charts;

import com.super_bits.modulosSB.Persistencia.dao.SBNQ;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ComoEntidadeSimples;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.charts.ComoEntidadeSimpleChart;

public class BP_ChartBarras<T extends ComoEntidadeSimples> extends BP_ChartLinear {

    public BP_ChartBarras(String pNome, SBNQ[] dados,
            Class<? extends ComoEntidadeSimpleChart> tipo) {
        super(pNome, dados, tipo);

    }

}
