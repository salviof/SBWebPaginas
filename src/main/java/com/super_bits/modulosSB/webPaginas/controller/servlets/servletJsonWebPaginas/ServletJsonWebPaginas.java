/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.controller.servlets.servletJsonWebPaginas;

import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreJson;
import com.super_bits.modulosSB.webPaginas.controller.servlets.ServletArquivosSBWPGenerico;
import static com.super_bits.modulosSB.webPaginas.controller.servlets.WebPaginasServlet.NOME_BEAN_REQUEST_CONFIG_URL;
import com.super_bits.modulosSB.webPaginas.controller.servlets.servletWebPaginas.ConfiguracoesDeFormularioPorUrl;
import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.super_bits.modulosSB.SBCore.modulos.Controller.qualificadoresCDI.sessao.QlSessaoFacesContext;
import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.UtilSBCoreErros;
import com.super_bits.modulosSB.webPaginas.TratamentoDeErros.ErroGenericoProcessandoRespostaJson;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWPJson;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWP_JSFTools;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ComoSessao;

/**
 *
 * @author salvio
 */
public class ServletJsonWebPaginas extends ServletArquivosSBWPGenerico {

    @Override
    protected void doGet(HttpServletRequest requisicao, HttpServletResponse resposta) throws ServletException, IOException {

        try {
            ConfiguracoesDeFormularioPorUrl configuracoes;
            configuracoes = new ConfiguracoesDeFormularioPorUrl(requisicao);
            requisicao.setAttribute(NOME_BEAN_REQUEST_CONFIG_URL, configuracoes);
            RequestDispatcher despachadorDeRespostaParaRequisicao = requisicao.getRequestDispatcher("/resources/json-webview/jsonwebview.xhtml");
            despachadorDeRespostaParaRequisicao.forward(requisicao, resposta);

        } catch (ServletException t) {
            resposta.setStatus(500);
            StringBuilder mensagemErro = new StringBuilder();
            Throwable causa = UtilSBCoreErros.getCausaRaiz(t);
            mensagemErro.append("FALHA PROCESSANDO JSON WEBVIEW  ");
            String strmensagemErro = t.getMessage();
            mensagemErro.append(t.getMessage());
            mensagemErro.append("\n");

            if (causa != null) {
                if (strmensagemErro != null && causa.getMessage() != null) {
                    if (!strmensagemErro.equals(causa.getMessage())) {
                        mensagemErro.append("Causa: ");
                        mensagemErro.append(causa.getMessage());
                    }
                }

            }

            resposta.getWriter().append(UtilSBCoreJson.getTextoByJsonObjeect(UtilSBWPJson.JSON_FALHA_GERANDO_JSONVIEW(mensagemErro.toString())));
        } catch (Throwable t) {

            resposta.getWriter().append(UtilSBCoreJson.getTextoByJsonObjeect(UtilSBWPJson.JSON_FALHA_GERANDO_JSONVIEW("Erro inesperado Servelet JsonWebpaginas")));
            throw new ServletException("Erro inesperado no " + ServletJsonWebPaginas.class.getSimpleName() + " | " + t.getMessage());
        }

    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "POST, PUT, OPTIONS, GET");
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Origin", "*");

    }

    @Override
    protected void doPost(HttpServletRequest requisicao, HttpServletResponse resposta) throws ServletException, IOException {

        try {

            //String caminhoChamada = requisicao.getPathInfo();
            // JsonObject jsonCorpo = UtilSBCoreJson.getJsonObjectByTexto(corpo);
            // String codigoUnico = jsonCorpo.getString("codigoUnicoDispositivo");
            ConfiguracoesDeFormularioPorUrl configuracoes = new ConfiguracoesDeFormularioPorUrl(requisicao);
            requisicao.setAttribute(NOME_BEAN_REQUEST_CONFIG_URL, configuracoes);
            RequestDispatcher despachadorDeRespostaParaRequisicao = requisicao.getRequestDispatcher(UtilSBWP_JSFTools.FORMULARIO_API_JSON_WEB_VIEW);
            despachadorDeRespostaParaRequisicao.forward(requisicao, resposta);

            //   UtilSBCoreJson.UtilSBCoreJson.getValorApartirDoCaminho("codigoDispositivo", UtilSBCoreStringJson.gerarObjetoJsonByString(corpo));
        } catch (Throwable t) {
            throw new ServletException("Erro inesperado no " + ServletJsonWebPaginas.class.getSimpleName() + " | " + t.getMessage());
        }

    }

}
