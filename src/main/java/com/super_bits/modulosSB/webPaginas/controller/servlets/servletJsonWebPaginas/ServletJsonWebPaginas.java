/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.controller.servlets.servletJsonWebPaginas;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreJson;
import com.super_bits.modulosSB.SBCore.UtilGeral.json.ErroProcessandoJson;
import com.super_bits.modulosSB.SBCore.modulos.Controller.qualificadoresCDI.sessao.QlSessaoFacesContext;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfSessao;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.interfaces.ItfSiteMapa;
import com.super_bits.modulosSB.webPaginas.controller.paginasDoSistema.FabAcaoPaginasDoSistema;
import com.super_bits.modulosSB.webPaginas.controller.servlets.ServletArquivosSBWPGenerico;
import static com.super_bits.modulosSB.webPaginas.controller.servlets.WebPaginasServlet.NOME_BEAN_REQUEST_CONFIG_URL;
import com.super_bits.modulosSB.webPaginas.controller.servlets.servletWebPaginas.ConfiguracoesDeFormularioPorUrl;
import com.super_bits.modulosSB.webPaginas.controller.sessao.SessaoAtualSBWP;
import jakarta.json.JsonObjectBuilder;
import jakarta.json.JsonValue;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

/**
 *
 * @author salvio
 */
public class ServletJsonWebPaginas extends ServletArquivosSBWPGenerico {

    private ItfSiteMapa mapaSite;

    @Override
    protected void doGet(HttpServletRequest requisicao, HttpServletResponse resposta) throws ServletException, IOException {

        try {
            ConfiguracoesDeFormularioPorUrl configuracoes = new ConfiguracoesDeFormularioPorUrl(requisicao);
            requisicao.setAttribute(NOME_BEAN_REQUEST_CONFIG_URL, configuracoes);

            RequestDispatcher despachadorDeRespostaParaRequisicao = requisicao.getRequestDispatcher("/resources/json-webview/jsonwebview.xhtml");
            despachadorDeRespostaParaRequisicao.forward(requisicao, resposta);

        } catch (Throwable t) {
            throw new ServletException("Erro inesperado no " + ServletJsonWebPaginas.class.getSimpleName() + " | " + t.getMessage());
        }

    }

}
