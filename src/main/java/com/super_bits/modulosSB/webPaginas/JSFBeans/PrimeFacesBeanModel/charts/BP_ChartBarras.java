package com.super_bits.modulosSB.webPaginas.JSFBeans.PrimeFacesBeanModel.charts;

import com.super_bits.modulosSB.Persistencia.dao.SBNQ;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.charts.ItfBeanSimpleChart;

public class BP_ChartBarras<T extends ItfBeanSimples> extends BP_ChartLinear {

    public BP_ChartBarras(String pNome, SBNQ[] dados,
            Class<? extends ItfBeanSimpleChart> tipo) {
        super(pNome, dados, tipo);

    }

}
