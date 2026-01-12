/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.controller.servlets.servletRecepcaoOauth;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.implementacao.UtilSBApiRestClient;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.implementacao.erro.ErroRecebendoCodigoDeAcesso;
import com.super_bits.modulosSB.SBCore.modulos.Controller.qualificadoresCDI.sessao.QlSessaoFacesContext;
import com.super_bits.modulosSB.webPaginas.controller.servlets.ServletArquivosSBWPGenerico;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWP_JSFTools;

import java.io.IOException;
import java.io.Serializable;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoSessao;

/**
 *
 *
 *
 *
 *
 * @author SalvioF
 */
public class ServletRecepcaoOauth extends ServletArquivosSBWPGenerico implements Serializable {

    public static final String NOME_URL_SERVLET = "solicitacaoAuth2Recept";

    @Inject
    @QlSessaoFacesContext
    private ComoSessao sessaoAtual;

    private String respostaTestReader;

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ComoSessao sessao = null;
        if (!SBCore.isEmModoDesenvolvimento()) {
            sessao = sessaoAtual;
        } else {
            sessao = SBCore.getServicoSessao().getSessaoAtual();
        }

        try {
            UtilSBApiRestClient.servletReceberCodigoConcessao(req, resp, sessao);
        } catch (ErroRecebendoCodigoDeAcesso pErro) {
            RequestDispatcher wp = req.getRequestDispatcher(UtilSBWP_JSFTools.FORMULARIO_PARAMETRO_URL_INVALIDO);
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro obtendo parametros de URL", pErro);
            wp.forward(req, resp);

        }
    }

    public String getRespostaTestReader() {
        return respostaTestReader;
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().print("Oq você está fazendo da sua vida dando um post aqui?");
    }

}
