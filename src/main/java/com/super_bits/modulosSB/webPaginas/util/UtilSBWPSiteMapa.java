/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.util;

import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringFiltros;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringValidador;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;
import com.super_bits.modulosSB.webPaginas.controller.servletes.urls.ItfFabUrlServletSBFW;
import com.super_bits.modulosSB.webPaginas.controller.servletes.urls.UrlInterpretada;
import java.util.List;

/**
 *
 * @author desenvolvedor
 */
public class UtilSBWPSiteMapa {

    public static String getUrlParaAcao(ItfAcaoDoSistema pAcao) {
        return null;
    }

    /**
     *
     * @param objSimples
     * @return
     */
    public static String getSlugDoObjeto(ItfBeanSimples objSimples) {
        return UtilSBCoreStringFiltros.gerarUrlAmigavel(objSimples.getNome())
                + UtilSBCoreStringFiltros.gerarUrlAmigavel("-" + objSimples.getId());
    }

    public static UrlInterpretada getUrlInterpretada(Class<? extends ItfFabUrlServletSBFW> pFabrica, List<String> parametros) {

        return null;

    }

}
