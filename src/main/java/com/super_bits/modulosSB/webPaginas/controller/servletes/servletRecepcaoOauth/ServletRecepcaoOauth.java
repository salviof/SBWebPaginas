/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.controller.servletes.servletRecepcaoOauth;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.implementacao.UtilSBApiRestClient;
import com.super_bits.modulosSB.webPaginas.ConfigGeral.SBWebPaginas;
import com.super_bits.modulosSB.webPaginas.controller.servletes.ServletArquivosSBWPGenerico;

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
    public static final String URL_SERVLET = SBWebPaginas.getSiteURL() + "/" + NOME_URL_SERVLET;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            if (UtilSBApiRestClient.receberCodigoSolicitacaoOauth(req)) {
                throw new UnsupportedOperationException("falha recebendo codigo de solictação de token Oauth");
            }
        } catch (Throwable t) {
            RequestDispatcher wp = req.getRequestDispatcher(UtilSBWP_JSFTools.FORMULARIO_PARAMETRO_URL_INVALIDO);
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro obtendo parametros de URL", t);
            wp.forward(req, resp);
            return;
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp); //To change body of generated methods, choose Tools | Templates.
    }

}
