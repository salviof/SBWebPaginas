/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.controller.servletes.servletRecepcaoOauth;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringValidador;
import com.super_bits.modulosSB.SBCore.modulos.Controller.WS.oauth.MapaInfoOauthEmAndamento;
import com.super_bits.modulosSB.SBCore.modulos.Controller.WS.oauth.Oath2Conexao;
import com.super_bits.modulosSB.SBCore.modulos.Controller.WS.oauth.TipoClienteOauth;
import com.super_bits.modulosSB.webPaginas.controller.servletes.ServletArquivosSBWPGenerico;
import com.super_bits.modulosSB.webPaginas.controller.servletes.urls.UrlInterpretada;
import com.super_bits.modulosSB.webPaginas.controller.servletes.urls.UtilFabUrlServlet;
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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        UrlInterpretada parametrosDeUrl;
        try {

            parametrosDeUrl = UtilFabUrlServlet.getUrlInterpretada(FabUrlServletRecepcaoOauth.class, req);

            String nomeParametroRetorno = parametrosDeUrl.getValorComoString(FabUrlServletRecepcaoOauth.NOME_PARAMETRO);
            String nomeModulo = parametrosDeUrl.getValorComoString(FabUrlServletRecepcaoOauth.IDENTIFICADOR_API);
            String nomeParametro = req.getParameter(nomeParametroRetorno);
            String codigoSolicitacoa = req.getParameter(nomeParametroRetorno);

            if (nomeParametro != null) {
                TipoClienteOauth tipoCliente = (TipoClienteOauth) parametrosDeUrl.getValorComoBeanSimples(FabUrlServletRecepcaoOauth.TIPO_CLIENTE_OAUTH);
                Oath2Conexao conexao = null;
                switch (tipoCliente.getEnumVinculado()) {
                    case USUARIO:
                        System.out.println("Procurando Conexão de usuário para modulo" + nomeModulo);
                        conexao = MapaInfoOauthEmAndamento.getAutenticadorUsuarioLogado(nomeModulo);
                        break;
                    case SISTEMA:
                        System.out.println("Procurando Conexão de sistema para modulo" + nomeModulo);
                        conexao = MapaInfoOauthEmAndamento.getAutenticadorSistemaAtual(nomeModulo);
                        break;
                    default:
                        throw new AssertionError(tipoCliente.getEnumVinculado().name());

                }

                if (conexao != null) {
                    System.out.println("Conexão encontrada para o modulo" + nomeModulo);
                    if (UtilSBCoreStringValidador.isNAO_NuloNemBranco(codigoSolicitacoa)) {

                        conexao.gerarNovoToken(codigoSolicitacoa);
                    } else {
                        System.out.println("A conexão Oath existe, porém, o parametro [" + nomeParametro + "] não foi encontrado com o código de soliciação");
                    }
                } else {
                    System.out.println("COnexões não encontradas, as conexoões deste modulo registradas são:");
                    MapaInfoOauthEmAndamento.printConexoesAtivas();
                }

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
