/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.controller.servlets.servletRecepcaoOauth;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.implementacao.UtilSBApiRestClient;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.implementacao.UtilSBApiRestClientOauth2;
import com.super_bits.modulosSB.webPaginas.controller.servlets.ServletArquivosSBWPGenerico;

import com.super_bits.modulosSB.webPaginas.util.UtilSBWP_JSFTools;
import java.io.IOException;
import java.io.Serializable;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

/**
 *
 * @author SalvioF
 */
public class ServletRecepcaoOauth extends ServletArquivosSBWPGenerico implements Serializable {

    public static final String NOME_URL_SERVLET = "solicitacaoAuth2Recept";

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            if (req.getRequestURI().contains(UtilSBApiRestClientOauth2.PATH_TESTE_DE_VIDA_SERVICO_RECEPCAO)) {
                resp.setStatus(200);
                resp.getWriter().append("EUTÔVIVO");
                resp.getWriter().close();
                return;
            }
            String tipoAplicacao = req.getParameter("tipoAplicacao");

            if (!UtilSBApiRestClient.receberCodigoSolicitacaoOauth(req, tipoAplicacao)) {
                throw new UnsupportedOperationException("falha recebendo codigo de solictação de token Oauth");
            }
            resp.getWriter().append("Chave de Aceso gerada com sucesso");
        } catch (Throwable t) {
            RequestDispatcher wp = req.getRequestDispatcher(UtilSBWP_JSFTools.FORMULARIO_PARAMETRO_URL_INVALIDO);
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro obtendo parametros de URL", t);
            wp.forward(req, resp);

        }

    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Recebendo token de acesso");
        System.out.println("");
    }

}
