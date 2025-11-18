/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFManagedBeans.declarados.Paginas.sistema;

import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreJson;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.TIPO_PARTE_URL;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ComoEntidadeSimples;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.MB_PaginaConversation;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.reflexao.anotacoes.InfoPagina;

import com.super_bits.modulos.SBAcessosModel.view.FabAcaoPaginasDoSistema;
import com.super_bits.modulos.SBAcessosModel.view.InfoAcaoPaginaDoSistema;
import com.super_bits.modulosSB.webPaginas.controller.servletes.urls.parametrosURL.InfoParametroURL;
import com.super_bits.modulosSB.webPaginas.controller.servletes.urls.parametrosURL.ParametroURL;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWPServletTools;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author desenvolvedor
 */
@InfoAcaoPaginaDoSistema(acao = FabAcaoPaginasDoSistema.PAGINA_NATIVA_ERRO_CRITICO_MB_PADRAO)
@ViewScoped
@InfoPagina(nomeCurto = "EC", tags = {"Erro Critico"})
@Named
public class PgErroCRitico extends MB_PaginaConversation {

    @InfoParametroURL(nome = "TituloErro", tipoParametro = TIPO_PARTE_URL.TEXTO)
    private ParametroURL prTitulo;

    private boolean formatoJson;

    @PostConstruct
    public void inicio() {
        HttpServletRequest requisicao = UtilSBWPServletTools.getRequestAtual();
        String caminho = requisicao.getRequestURI();
        if (caminho.endsWith(".json")) {
            formatoJson = true;
        }
    }

    @Override
    public ComoEntidadeSimples getBeanSelecionado() {
        return null;
    }

    @Override
    public void setBeanSelecionado(ComoEntidadeSimples pBeanSimples) {

    }

    public String getTituloErro() {
        try {
            return (String) getParametroInstanciado(prTitulo).getValor();
        } catch (Throwable t) {
            return "Titulo não enviado";
        }
    }

    public boolean isFormatoJson() {
        return formatoJson;
    }

}
