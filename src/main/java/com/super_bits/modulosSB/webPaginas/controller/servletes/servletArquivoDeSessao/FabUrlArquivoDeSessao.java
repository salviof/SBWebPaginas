/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.controller.servletes.servletArquivoDeSessao;

import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.TIPO_PARTE_URL;
import com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo.FabTipoArquivoConhecido;
import com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo.TipoRecurso;
import com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo.acessoArquivo.FabTipoAcessoArquivo;
import com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo.acessoArquivo.TipoAcessoArquivo;
import com.super_bits.modulosSB.webPaginas.controller.servletes.urls.ItfFabUrlServletSBFW;
import com.super_bits.modulosSB.webPaginas.controller.servletes.urls.parametrosURL.InfoParametroURL;

/**
 *
 * @author desenvolvedor
 */
public enum FabUrlArquivoDeSessao implements ItfFabUrlServletSBFW {
    @InfoParametroURL(tipoParametro = TIPO_PARTE_URL.OBJETO_COM_CONSTRUCTOR, nome = "Tipo Arquivo",
            fabricaObjetosRelacionada = FabTipoArquivoConhecido.class, tipoEntidade = TipoRecurso.class)
    TIPO_ARQUIVO,
    @InfoParametroURL(nome = "Tipo Acesso", tipoParametro = TIPO_PARTE_URL.OBJETO_COM_CONSTRUCTOR,
            fabricaObjetosRelacionada = FabTipoAcessoArquivo.class, tipoEntidade = TipoAcessoArquivo.class)
    TIPO_ACESSO,
    @InfoParametroURL(tipoParametro = TIPO_PARTE_URL.TEXTO, nome = "Identificacao Entidade")
    NOME_DO_ARQUIVO;

}
