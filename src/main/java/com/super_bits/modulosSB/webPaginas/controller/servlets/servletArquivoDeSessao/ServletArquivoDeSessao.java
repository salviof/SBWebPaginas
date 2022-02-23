/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.controller.servlets.servletArquivoDeSessao;

import com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo.TipoRecurso;
import com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo.acessoArquivo.FabTipoAcessoArquivo;
import com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo.acessoArquivo.TipoAcessoArquivo;
import com.super_bits.modulosSB.webPaginas.ConfigGeral.SBWebPaginas;
import com.super_bits.modulosSB.webPaginas.controller.servlets.ServletArquivosSBWPGenerico;
import com.super_bits.modulosSB.webPaginas.controller.servletes.urls.UrlInterpretada;
import com.super_bits.modulosSB.webPaginas.controller.servletes.util.UtilFabUrlServlet;

import com.super_bits.modulosSB.SBCore.modulos.Controller.qualificadoresCDI.sessao.QlSessaoFacesContext;
import com.super_bits.modulosSB.webPaginas.controller.sessao.SessaoAtualSBWP;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWP_JSFTools;
import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author desenvolvedor
 */
public class ServletArquivoDeSessao extends ServletArquivosSBWPGenerico {

    public static final String NOME_URL_SERVLET = "arquivosDeSessao";
    public static final String URL_SERVLET = SBWebPaginas.getSiteURL() + "/" + NOME_URL_SERVLET;

    @Inject
    @QlSessaoFacesContext
    private SessaoAtualSBWP sessaoAtual;

    @Override
    protected void doGet(HttpServletRequest requisicao, HttpServletResponse resp) throws ServletException, IOException {
        UrlInterpretada parametrosDeUrl;
        try {

            parametrosDeUrl = UtilFabUrlServlet.getUrlInterpretada(FabUrlArquivoDeSessao.class, requisicao);

        } catch (Throwable t) {
            RequestDispatcher wp = requisicao.getRequestDispatcher(UtilSBWP_JSFTools.FORMULARIO_PARAMETRO_URL_INVALIDO);
            wp.forward(requisicao, resp);
            return;
        }
        String pNomeArquivo = (String) parametrosDeUrl.getValor(FabUrlArquivoDeSessao.NOME_DO_ARQUIVO);
        TipoAcessoArquivo pTipoAcesso = (TipoAcessoArquivo) parametrosDeUrl.getValor(FabUrlArquivoDeSessao.TIPO_ACESSO);
        TipoRecurso pTipoRecurso = (TipoRecurso) parametrosDeUrl.getValorComoBeanSimples(FabUrlArquivoDeSessao.TIPO_ARQUIVO);
        FabTipoAcessoArquivo tipoAcesso = (FabTipoAcessoArquivo) pTipoAcesso.getFabricaObjeto();
        switch (tipoAcesso) {
            case VISUALIZAR:
                abrirArquivo(sessaoAtual.getPastaTempDeSessao() + "/" + pNomeArquivo, pNomeArquivo, requisicao, resp, pTipoRecurso.getFabipoArquivo());
                break;
            case BAIXAR:
                baixarArquivo(sessaoAtual.getPastaTempDeSessao() + "/" + pNomeArquivo, pNomeArquivo, requisicao, resp);
                break;
            default:
                throw new AssertionError(tipoAcesso.name());

        }

    }

}
