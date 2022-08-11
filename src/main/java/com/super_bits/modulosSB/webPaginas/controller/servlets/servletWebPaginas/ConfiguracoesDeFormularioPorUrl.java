/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.controller.servlets.servletWebPaginas;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.webPaginas.ConfigGeral.SBWebPaginas;
import com.super_bits.modulosSB.webPaginas.controller.servlets.FabTipoInformacaoUrl;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWPServletTools;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWP_JSFTools;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

/**
 *
 * @author salvioF
 */
public class ConfiguracoesDeFormularioPorUrl {

    private final List<String> stringsParametros = new ArrayList();
    private final List<String> stringAcoes = new ArrayList();
    private FabTipoInformacaoUrl tipoInformacao = FabTipoInformacaoUrl.URL_ACESSO;
    private String stringGestao;
    private String sTringUrlAcesso;
    private String sTringUrlOrgem;
    private String corpo;

    private void adicionarInformacao(String informacao) {
        switch (tipoInformacao) {
            case GESTAO:
                stringGestao = informacao;
                break;
            case PARAMETRO:
                stringsParametros.add(informacao);
                break;
            case ACAO:
                stringAcoes.add(informacao);
                break;
            case URL_ACESSO:
                sTringUrlAcesso = informacao;
                break;
            case URL_ORIGEM:
                sTringUrlOrgem = informacao;
                break;
            default:
                throw new AssertionError(tipoInformacao.name());

        }
    }

    private static String buildUrlBaseByHttpServeletResquest(HttpServletRequest pRequest) {
        String dominio = null;
        try {
            URL url = new URL(pRequest.getRequestURL().toString());
            dominio = url.getHost();
        } catch (MalformedURLException t) {

        }

        StringBuilder urlBaseBuilder = new StringBuilder();
        if (SBCore.isEmModoProducao()) {
            urlBaseBuilder.append("https://");
            urlBaseBuilder.append(dominio);
        } else {
            urlBaseBuilder.append(SBWebPaginas.getURLBase());
        }
        return urlBaseBuilder.toString();
    }

    public ConfiguracoesDeFormularioPorUrl(HttpServletRequest pRequisicao) {
        this(pRequisicao.getRequestURL().toString(), buildUrlBaseByHttpServeletResquest(pRequisicao));
        if (pRequisicao.getMethod().equals("POST") || pRequisicao.getMethod().equals("PUT")) {
            corpo = UtilSBWPServletTools.getCorpoRequisicaoI(pRequisicao);
        }

    }

    public ConfiguracoesDeFormularioPorUrl(final String pURLReqsuisicao, final String urlDominio) {

        try {

            if (SBWebPaginas.isParametrosEmSubdominios()) {
                throw new UnsupportedOperationException("Parametro em subdominio ainda não foi implementado");
            } else {

                String urlPagina = urlDominio;// SBWebPaginas.getURLBase();
                adicionarInformacao(urlPagina);
                int inicioParametro = pURLReqsuisicao.substring(9).indexOf("/") + 10;

                if (pURLReqsuisicao.length() <= inicioParametro) {
                    throw new UnsupportedOperationException("Ipossível determinar os parametros de urla para " + pURLReqsuisicao);
                }
                int indiceInicioStrExtencao = pURLReqsuisicao.lastIndexOf(".");

                String urlSemExtencao = pURLReqsuisicao.substring(0, indiceInicioStrExtencao);

                try {
                    String parametrosStr = urlSemExtencao.substring(inicioParametro);
                    String[] parametros = parametrosStr.split("/");
                    int i = 0;
                    for (String pr : parametros) {
                        String valor = pr;
                        if (i == 0) {
                            tipoInformacao = FabTipoInformacaoUrl.GESTAO;
                        } else if (pr.startsWith("ac-")) {
                            valor = pr.split("-")[1];
                            tipoInformacao = FabTipoInformacaoUrl.ACAO;

                        } else {
                            tipoInformacao = FabTipoInformacaoUrl.PARAMETRO;
                        }
                        adicionarInformacao(valor);
                        i++;
                    }

                } catch (Exception e) {
                    UtilSBWP_JSFTools.vaParaPaginadeErro("Erro localizando valores para paramentor de pagina, verifique a configuração do url do frameworkWebpaginas");
                }

            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "erro obtendo parametros da pagina atravéz da utl" + pURLReqsuisicao, t);

        }

    }

    public List<String> getStringsParametros() {
        return stringsParametros;
    }

    public List<String> getStringAcoes() {
        return stringAcoes;
    }

    public String getStringGestao() {
        return stringGestao;
    }

    public String getsTringUrlAcesso() {
        return sTringUrlAcesso;
    }

    public String getsTringUrlOrgem() {
        return sTringUrlOrgem;
    }

    public String getCorpo() {
        return corpo;
    }

}
