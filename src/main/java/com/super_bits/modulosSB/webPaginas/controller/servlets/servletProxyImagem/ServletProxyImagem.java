/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.controller.servlets.servletProxyImagem;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo.FabTipoArquivoConhecido;
import com.super_bits.modulosSB.webPaginas.ConfigGeral.SBWebPaginas;
import com.super_bits.modulosSB.webPaginas.controller.servlets.ServletArquivosSBWPGenerico;
import com.super_bits.modulosSB.webPaginas.controller.servlets.servletArquivoDeEntidade.FabUrlArquivoDeEntidade;
import com.super_bits.modulosSB.webPaginas.controller.servletes.urls.UrlInterpretada;
import com.super_bits.modulosSB.webPaginas.controller.servletes.util.UtilFabUrlServlet;
import com.super_bits.modulosSB.SBCore.modulos.Controller.qualificadoresCDI.sessao.QlSessaoFacesContext;
import com.super_bits.modulosSB.webPaginas.controller.sessao.SessaoAtualSBWP;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWP_JSFTools;
import java.io.IOException;
import java.io.Serializable;
import java.rmi.ServerException;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

/**
 *
 * @author sfurbino
 */
public class ServletProxyImagem extends ServletArquivosSBWPGenerico implements Serializable {

    public static final String NOME_URL_SERVLET = "proxyImagem";
    public static final String URL_SERVLET = SBWebPaginas.getSiteURL() + "/" + NOME_URL_SERVLET;

    @Inject
    @QlSessaoFacesContext
    public SessaoAtualSBWP sessaoAtual;

    @Override
    protected void doGet(HttpServletRequest requisicao, HttpServletResponse resp) throws ServletException, IOException {
        UrlInterpretada parametrosDeUrl;
        if (!sessaoAtual.isIdentificado()) {
            throw new ServerException("Acesso negado");
        }
        try {

            parametrosDeUrl = UtilFabUrlServlet.getUrlInterpretada(FabUrlProxyDeImagem.class, requisicao);

        } catch (Throwable t) {
            RequestDispatcher wp = requisicao.getRequestDispatcher(UtilSBWP_JSFTools.FORMULARIO_PARAMETRO_URL_INVALIDO);
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro obtendo parametros de URL", t);
            wp.forward(requisicao, resp);
            return;
        }

        String urlRequisicao = requisicao.getRequestURI();
        String referencia = requisicao.getHeader("Referer");
        if (referencia != null) {
            if (referencia.contains("ac-Encontrar_Logo_da_Empresa")) {
                resp.addHeader("Cache-Control", "no-cache");
            } else {
                resp.addHeader("Cache-Control", "max-age=500");
            }
        } else {
            resp.addHeader("Cache-Control", "max-age=500");
        }
        String urlimagem = urlRequisicao.replaceAll("/proxyImagem/", "");
        String nomearquivo = urlimagem.substring(urlimagem.lastIndexOf("/") + 1, urlimagem.length());

        abrirArquivo(urlimagem, nomearquivo, requisicao, resp, FabTipoArquivoConhecido.IMAGEM_WEB);
    }

}
