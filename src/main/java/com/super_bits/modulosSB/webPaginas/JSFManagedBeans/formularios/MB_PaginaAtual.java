package com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.MapaAcoesSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfSessao;
import com.super_bits.modulosSB.SBCore.modulos.servicosCore.ItfControleDeSessao;
import com.super_bits.modulosSB.webPaginas.ConfigGeral.SBWebPaginas;
import com.super_bits.modulosSB.webPaginas.JSFBeans.modal.PgModalRespostaAcaoTransient;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.declarados.Paginas.ErroCritico.InfoErroCritico;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.interfaces.ItfB_Pagina;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.interfaces.ItfB_PaginaRecepcaoPush;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.interfaces.ItfB_PaginaSimples;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.siteMap.AcaoDeContexto;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.siteMap.MB_SiteMapa;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.siteMap.MapaDeFormularios;
import com.super_bits.modulosSB.webPaginas.controller.sessao.ControleDeSessaoWeb;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWPServletTools;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWP_JSFTools;
import java.io.Serializable;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

public abstract class MB_PaginaAtual implements Serializable {

    private String nomePagina;
    private Date datahoraAbertura;
    private ItfB_PaginaSimples infoPagina;

    @Inject
    private InfoErroCritico erroCriticoDoSistema;

    @Inject
    private ControleDeSessaoWeb controleDeSessao;

    protected abstract MB_SiteMapa getSiteMap();

    @PostConstruct
    public void init() {
        try {
            SBCore.soutInfoDebug("Iniciando pagina Atual");
            datahoraAbertura = new Date();
            if (infoPagina != null) {
                System.out.println("Pagina Atual infoPagina carregado");
            }
            if (infoPagina == null) {

                FacesContext contexto = FacesContext.getCurrentInstance();
                if (contexto == null) {
                    throw new UnsupportedOperationException("O contexto do XHTML não pode ser determinando em PGPaginaAtual");
                }

                String viewId = contexto.getViewRoot().getViewId();
                if (viewId == null) {
                    throw new UnsupportedOperationException("O XHTML principal do contexto atual não pode ser determinado");
                }

                try {
                    if (viewId.endsWith("comunicacaoAcaoTransiente.xhtml")) {
                        PgModalRespostaAcaoTransient modal = contexto.getApplication().evaluateExpressionGet(contexto, "#{pgModalRespostaAcaoTransient}", PgModalRespostaAcaoTransient.class);
                        viewId = modal.getPaginaVinculada().getAcaoVinculada().getXhtml();
                    }
                    setInfoPagina(getSiteMap().getPaginaNoContexto(viewId));

                } catch (Throwable e) {
                    throw new UnsupportedOperationException("Não foi possível identificar a pagina vinculada ao xhtml:" + viewId);

                }
            }

            if (getInfoPagina() == null) {
                throw new UnsupportedOperationException("PAGINA ATUAL NÃO PODE SER DETERMINADA PELO URL DE SOLICITACAO", null);

            } else {
                if (infoPagina.isPaginaDeGestao()) {
                    if (infoPagina.getComoPaginaDeGestao().getAcaoVinculada() != null) {
                        if (infoPagina.getComoPaginaDeGestao().getAcaoVinculada().getXhtml().equals("/site/home.xhtml")) {
                            if (controleDeSessao.getSessaoAtual().isIdentificado()) {
                                String paginaInicialDoGrupo
                                        = controleDeSessao.getSessaoAtual().getUsuario().getGrupo().
                                                getPaginaInicial().getRegistro().getComoFormulario().getXhtml();

                                if (paginaInicialDoGrupo != null && !paginaInicialDoGrupo.equals("/site/home.xhtml")) {
                                    ItfAcaoDoSistema acao = MapaAcoesSistema.getAcaoDoSistemaByFormulario(paginaInicialDoGrupo);
                                    String url = MapaDeFormularios.getUrlFormulario(acao);
                                    UtilSBWP_JSFTools.vaParaPagina(url);
                                    //UtilSBWP_JSFTools.vaParaPagina(WebPaginasServlet.getAcaoComLinkByXHTML(paginaInicialDoGrupo).getUrlDeAcesso());
                                }
                            } else {

                            }

                        }
                    }
                }

                // System.out.println("executou abre pagina pelo pagina Atual" + infoPagina.getRecursoXHTML());
            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro Instanciando Pagina atual", t);
            if (SBCore.getEstadoAPP() != SBCore.ESTADO_APP.PRODUCAO) {

                erroCriticoDoSistema.setBeanErroCritico(new InfoErroCritico("Erro criando informações da Pagina Atual,", t));
                UtilSBWP_JSFTools.vaParaPaginadeErro(t.getMessage());
            }

        }
    }

    public ItfB_Pagina getFormulario() {
        return infoPagina.getComoFormularioWeb();
    }

    public AcaoDeContexto getAcaoNoCotexto(ItfAcaoDoSistema pAcao) {
        if (getInfoPagina().isPaginaDeGestao()) {
            return new AcaoDeContexto(pAcao, FacesContext.getCurrentInstance(), getInfoPagina().getComoPaginaDeGestao());
        } else {
            return new AcaoDeContexto(pAcao, FacesContext.getCurrentInstance());
        }
    }

    public boolean isPermitidoExecutarAcao(ItfAcaoDoSistema pAcao) {
        return SBCore.getControleDeSessao().getSessaoAtual().isAcessoPermitido(pAcao);
    }

    public void iniciaConvesa() {
        try {
            FacesContext contexto = FacesContext.getCurrentInstance();

            //     if (contexto == null) {
//                if (conversa == null) {
            //           conversa.begin();
            //        }
            //      } else if (!contexto.isPostback() && conversa.isTransient()) {
            //           conversa.begin();
            //      }
        } catch (Exception e) {

            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro iniciando conversa de:" + this.getClass().getSimpleName(), e);
        }

    }

    public void fechaPagina() {
        if (getInfoPagina().isPaginaDeGestao()) {
            getInfoPagina().getComoPaginaDeGestao().fecharPagina();
        }
    }

    public ItfB_PaginaSimples getInfoPagina() {
        return infoPagina;
    }

    public void setInfoPagina(ItfB_PaginaSimples infoPagina) {
        this.infoPagina = infoPagina;
    }

    public void mudarDePaginaPorEntidade(ActionEvent event) {
//        String pPagina = UtilSBWPServletTools.getRequestParametro("pPagina");
        ItfBeanSimples pEntidade = UtilSBWPServletTools.getActionBeanSimples(event, "registro");

        // ItfB_Pagina novaPg = getSiteMap().getPaginaByTagOuNome(pPagina);
        //   novaPg.getParametrobyTipoEntidade(pEntidade.getClass().getSimpleName()).setValor(pEntidade.getNomeCurto());
        fechaPagina();
        //  UtilSBWP_JSFTools.vaParaPagina(novaPg.getUrlPadrao());
        throw new UnsupportedOperationException("Ainda não implementado, mas estamos quase lá, devido ao mapa de ações do sistema");
    }

    public void mudarDePagina() {
        fechaPagina();
    }

    public ItfSessao getSessao() {
        return controleDeSessao.getSessaoAtual();
    }

    public ItfControleDeSessao getControleDeSessao() {
        return controleDeSessao;
    }

    public String getEnderecoSite() {
        return SBWebPaginas.getSiteURL();
    }

    public String getNomePagina() {
        return nomePagina;
    }

    public void setNomePagina(String nomePagina) {
        this.nomePagina = nomePagina;
    }

    public Date getDatahoraAbertura() {
        return datahoraAbertura;
    }

    public void setDatahoraAbertura(Date datahoraAbertura) {
        this.datahoraAbertura = datahoraAbertura;
    }

    public boolean isUmaPaginaEdicaoOuNovoRegistro() {
        try {
            if (!getInfoPagina().isPaginaDeGestao()) {
                return false;
            }
            if (!getInfoPagina().getComoPaginaDeGestao().isUmaPaginaGestaoEntidade()) {
                return false;
            } else {
                return getInfoPagina().getComoPaginaDeGestao().getComoPaginaEntidade().isPodeEditar();
            }
        } catch (Throwable t) {
            return false;
        }
    }

    public boolean isUmaPaginaNovoRegistro() {
        try {
            if (!getInfoPagina().isPaginaDeGestao()) {
                return false;
            }
            if (!getInfoPagina().getComoPaginaDeGestao().isUmaPaginaGestaoEntidade()) {
                return false;
            } else {
                return getInfoPagina().getComoPaginaDeGestao().getComoPaginaEntidade().isNovoRegistro();
            }
        } catch (Throwable t) {
            return false;
        }
    }

    public void recepcaoNotificacaoPush() {
        if (getInfoPagina() instanceof ItfB_PaginaRecepcaoPush) {

            ItfB_PaginaRecepcaoPush pagina = (ItfB_PaginaRecepcaoPush) getInfoPagina();
            pagina.recepcaoNotificacaoPush();
        }
    }

}
