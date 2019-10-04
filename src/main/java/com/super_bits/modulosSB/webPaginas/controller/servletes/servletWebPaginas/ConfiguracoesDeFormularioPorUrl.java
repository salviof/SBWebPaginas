/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.controller.servletes.servletWebPaginas;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.webPaginas.ConfigGeral.SBWebPaginas;
import com.super_bits.modulosSB.webPaginas.controller.servletes.FabTipoInformacaoUrl;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWP_JSFTools;
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

    public ConfiguracoesDeFormularioPorUrl(HttpServletRequest pRequisicao) {
        this(pRequisicao.getRequestURL().toString());

    }

    public ConfiguracoesDeFormularioPorUrl(String pURL) {

        try {

            if (SBWebPaginas.isParametrosEmSubdominios()) {
                throw new UnsupportedOperationException("Parametro em subdominio ainda não foi implementado");
            } else {

                String urlPagina = SBWebPaginas.getURLBase();
                adicionarInformacao(urlPagina);
                int inicioParametro = urlPagina.length() + 1;

                if (pURL.length() <= inicioParametro) {
                    throw new UnsupportedOperationException("Ipossível determinar os parametros de urla para " + pURL);
                }
                int indiceInicioStrExtencao = pURL.lastIndexOf(".");
                String fimUrl = pURL.substring(indiceInicioStrExtencao, pURL.length() - 1);

                pURL = pURL.substring(0, indiceInicioStrExtencao);

                try {
                    String parametrosStr = pURL.substring(inicioParametro);
                    String[] parametros = parametrosStr.split("/");
                    int i = 0;
                    for (String pr : parametros) {
                        String valor = pr;
                        if (i == 0) {
                            tipoInformacao = FabTipoInformacaoUrl.GESTAO;
                        } else if (pr.contains("ac-")) {
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
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "erro obtendo parametros da pagina atravéz da utl" + pURL, t);

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

}
