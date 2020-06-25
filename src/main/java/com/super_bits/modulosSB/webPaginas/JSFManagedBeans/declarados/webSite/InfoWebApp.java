/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFManagedBeans.declarados.webSite;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.modulosSB.webPaginas.ConfigGeral.SBWebPaginas;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.siteMap.AcaoComLink;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.siteMap.MapaDeFormularios;
import com.super_bits.modulosSB.webPaginas.controller.servletes.WebPaginasServlet;
import static com.super_bits.modulosSB.webPaginas.controller.servletes.WebPaginasServlet.MAPA_ACOESMANAGED_BEAN;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.inject.Named;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

/**
 *
 * @author desenvolvedor
 */
@ApplicationScoped
@Named
public class InfoWebApp implements Serializable {

    /**
     *
     * @param pAcao
     * @return Ação managed Ben contendo a URL para acesso
     */
    public AcaoComLink getAcaoComLink(ItfAcaoDoSistema pAcao) {
        try {
            if (pAcao == null) {
                System.out.println("Enviou ação nula como parametro para obtenção de link");
                return null;
                //          throw new UnsupportedOperationException();
            }

            if (pAcao.isUmaAcaoGestaoDominio()) {
                //System.out.println("ação de dominio, retornando url para" + pAcao.getNomeUnico());
                String chaveLocalizacao = pAcao.getNomeUnico();
                AcaoComLink acao = MAPA_ACOESMANAGED_BEAN.get(chaveLocalizacao);
                if (acao == null) {
                    throw new UnsupportedOperationException("Ação " + pAcao.getNomeUnico() + "Não encontrada, certifique que a ação de gestão vinculada a esta ação esteja vinculada a uma pagina declarada no Sitemap");
                }
                return acao;
            } else {

                String aux = pAcao.getComoSecundaria().getAcaoPrincipal().getNomeUnico();

                AcaoComLink acaoGestao = WebPaginasServlet.MAPA_ACOESMANAGED_BEAN.get(aux);

                if (acaoGestao == null) {

                    throw new UnsupportedOperationException("Certifique a ação de gestão não foi encontrada pelo nome unico " + aux + " CErtifique que existe um PG vinculado a ele e que este PG esteja no SITEMAP");

                }

                //       System.out.println("ação secundaria, retornando url para" + pAcao.getComoSecundaria().getAcaoPrincipal().getNomeUnico());
                return new AcaoComLink(pAcao, acaoGestao);
            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro solicitando link de ação" + pAcao, t);
        }
        return null;
    }

    public String getUrlDaAcao(ItfAcaoDoSistema pAcao) {
        try {
            if (pAcao == null) {
                System.out.println("Enviou ação nula como parametro para obtenção de link");
                return null;
                //          throw new UnsupportedOperationException();
            }

            String url = MapaDeFormularios.getUrlFormulario(pAcao);
            return url;

        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro solicitando link de ação" + pAcao, t);
        }
        return null;
    }

    public String getUrlDaAcao(ItfAcaoDoSistema pAcao, Object... pParametros) {
        try {
            if (pAcao == null) {
                System.out.println("Enviou ação nula como parametro para obtenção de link");
                return null;
                //          throw new UnsupportedOperationException();
            }

            String url = MapaDeFormularios.getUrlFormulario(pAcao, pParametros);
            return url;

        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro solicitando link de ação" + pAcao, t);
        }
        return null;
    }

    public boolean isAceosMBConfiguradas() {
        return !WebPaginasServlet.MAPA_ACOESMANAGED_BEAN.isEmpty();
    }

    public List<AcaoComLink> getAcoesMB() {
        List<AcaoComLink> acoes = new ArrayList<>();
        for (AcaoComLink acao : WebPaginasServlet.MAPA_ACOESMANAGED_BEAN.values()) {
            acoes.add(acao);
        }
        return acoes;
    }

    public boolean isEmModoDesenvolvimentoHomologacao() {
        return !SBCore.isEmModoProducao();
    }

    public String getUrlPagina() {
        return SBWebPaginas.getSiteURL();
    }

    public String getFormularioPaginaInicial() {
        try {
            String formInicial = SBWebPaginas.getAcaoPAginaInicial().getXhtml();
            if (formInicial != null) {
                return formInicial;
            }
        } catch (Throwable t) {
            return "/site/home.xhtml";
        }
        return "/site/home.xhtml";
    }

}
