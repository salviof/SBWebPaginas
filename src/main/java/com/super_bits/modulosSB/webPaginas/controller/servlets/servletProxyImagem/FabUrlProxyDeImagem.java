/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.controller.servlets.servletProxyImagem;

import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.TIPO_PARTE_URL;
import com.super_bits.modulosSB.webPaginas.controller.servletes.urls.ItfFabUrlServletSBFW;
import com.super_bits.modulosSB.webPaginas.controller.servletes.urls.parametrosURL.InfoParametroURL;

/**
 *
 * @author sfurbino
 */
public enum FabUrlProxyDeImagem implements ItfFabUrlServletSBFW {

    @InfoParametroURL(tipoParametro = TIPO_PARTE_URL.TEXTO, nome = "Url da imagem")
    URL_DA_IMAGEM;
}
