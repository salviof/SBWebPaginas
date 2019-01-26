/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulosSB.webPaginas.controller.servletes;

import com.super_bits.modulos.SBAcessosModel.model.acoes.acaoDeEntidade.AcaoGestaoEntidade;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.MapaAcoesSistema;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreReflexao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfUsuario;
import com.super_bits.modulosSB.webPaginas.ConfigGeral.SBWebPaginas;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.declarados.Paginas.ErroCritico.InfoErroCritico;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.interfaces.ItfSiteMapa;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.siteMap.AcaoComLink;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.siteMap.MB_SiteMapa;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.siteMap.MapaDeFormularios;
import com.super_bits.modulosSB.webPaginas.controller.servletes.servletWebPaginas.ConfiguracoesDeFormularioPorUrl;
import com.super_bits.modulosSB.webPaginas.controller.servletes.servletWebPaginas.EstruturaDeFormulario;
import com.super_bits.modulosSB.webPaginas.controller.sessao.ControleDeSessaoWeb;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWPServletTools;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWP_JSFTools;
import java.io.IOException;
import java.io.Serializable;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

/**
 *
 *
 *
 * @author Salvio
 */
//@WebServlet("*.wp")
public class WebPaginasServlet extends HttpServlet implements Serializable {

    @Inject
    private ControleDeSessaoWeb controleDeSessao;
    @Inject
    private ItfSiteMapa siteMapa;
    @Inject
    private InfoErroCritico erroCritico;

    public static final String NOME_BEAN_REQUEST_CONFIG_URL = "CfgURLFrm";

    public final static Map<String, AcaoComLink> MAPA_ACOESMANAGED_BEAN = new HashMap<>();

    public final void buildMapaUrlsModoJunut(Class<? extends ItfSiteMapa> mapa) {
        try {
            siteMapa = mapa.newInstance();
            buildMapaUrls();
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro Instanciando Sitemapa", t);
        }
    }

    public WebPaginasServlet() {
        try {
            if (SBCore.isEmModoDesenvolvimento()) {
                Class sitMapEncontado = UtilSBCoreReflexao.getClasseQueEstendeIsto(MB_SiteMapa.class, "com.super_bits.config.webPaginas");
                buildMapaUrlsModoJunut(sitMapEncontado);
            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Errro criando mapa de URL para contexto de Desenvolvedor", t);
        }
    }

    private void buildMapaUrls() {

        siteMapa.getFabricaMenu();

        try {
            for (EstruturaDeFormulario pagina : MapaDeFormularios.getTodasEstruturas()) {
                if (pagina.getAcaoGestaoVinculada() != null) {
                    if (MAPA_ACOESMANAGED_BEAN.get(pagina.getAcaoGestaoVinculada().getNomeUnico()) != null) {
                        throw new UnsupportedOperationException("Uma ação de gestão só pode ser vinculada a uma Pgina, no entando a pagina"
                                + pagina.getAcaoGestaoVinculada() + "está vinculada a "
                                + MAPA_ACOESMANAGED_BEAN.get(pagina.getAcaoGestaoVinculada().getNomeUnico()).getUrlParcialGestao() + " e a"
                                + pagina.getUrlPadrao());
                    }
                    MAPA_ACOESMANAGED_BEAN.put(pagina.getAcaoGestaoVinculada().getNomeUnico(),
                            new AcaoComLink(pagina));
                }

            }

            switch (SBCore.getEstadoAPP()) {
                case DESENVOLVIMENTO:
                    break;
                case PRODUCAO:
                    getServletConfig().getServletContext().setInitParameter("javax.faces.PROJECT_STAGE", "Production");
                    getServletConfig().getServletContext().setInitParameter("FACELETS_REFRESH_PERIOD", "-1");

                    break;
                case HOMOLOGACAO:
                    getServletConfig().getServletContext().setInitParameter("javax.faces.PROJECT_STAGE", "Development");
                    getServletConfig().getServletContext().setInitParameter("FACELETS_REFRESH_PERIOD", "0");
                    break;
                default:
                    throw new AssertionError(SBCore.getEstadoAPP().name());

            }

        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.PARA_TUDO, "Erro Criando Ações MB", t);
        }

        System.out.println("Contexto Inicializado");

    }

    @Override
    public void init() throws ServletException {
        super.init(); //To change body of generated methods, choose Tools | Templates.
        // Instanciado site mapa para construção de Formulario
        buildMapaUrls();

    }

    public static AcaoComLink getAcaoComLinkByXHTML(String pXhtml) {
        try {
            EstruturaDeFormulario paginaVinculada = MapaDeFormularios.getEstruturaByXHTMLDeGestao(pXhtml);
            if (paginaVinculada == null) {
                throw new UnsupportedOperationException("Nenguma pagina vinculada ao xhtml" + pXhtml + "Certifique que a pagina tenha sido declarada no sitemap");
            }
            return MAPA_ACOESMANAGED_BEAN.get(paginaVinculada.getAcaoGestaoVinculada().getNomeUnico());
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro Obtendo ação com link para xhtml[" + pXhtml + "]", t);
            return null;
        }

    }

    private void carregarFormulario(String recurso, HttpServletRequest requisicao, HttpServletResponse resposta) {

        if (!SBCore.isEmModoProducao()) {
            System.out.println("Processando Formulario" + recurso);
        }

        RequestDispatcher despachadorDeRespostaParaRequisicao = requisicao.getRequestDispatcher(recurso);

        try {
            despachadorDeRespostaParaRequisicao.forward(requisicao, resposta);
            Enumeration<String> parametros = requisicao.getParameterNames();
            while (parametros.hasMoreElements()) {
                String pr = parametros.nextElement();
                if (pr.contains("atualizar")) {
                    UtilSBWP_JSFTools.atualizaPorId(requisicao.getAttribute(pr).toString(), true);
                }
            }

        } catch (Throwable erro) {
            System.out.println("Erro Reidenrizando pagina>>>" + recurso);
            //erroCritico.setBeanErroCritico(new InfoErroCritico("Erro gerenado" + recurso, erro));

            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro executando forward de Recurso " + recurso, erro);
            //UtilSBWP_JSFTools.vaParaPaginadeErro("Erro reiderizando pagina");

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void doGet(HttpServletRequest requisicao, HttpServletResponse resposta) throws ServletException, IOException {
        System.out.println("Iniciando servlet WP");

        try {
            controleDeSessao.getSessaoAtual();
            System.out.println(controleDeSessao.getSessaoAtual().getUsuario().getNome());
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Controle de sessão não pode ser encontrado,"
                    + "A causa mais provavel é que a injeção via CDI não esteja funcionando no ambiente de Servlet"
                    + "Caso seu projeto esteja rodando no container homologado, isto pode ser resolvido  "
                    + "verificando as configurações do seu jetty-web.xml e web.xml "
                    + " se você não tem experiencia neste assunto, você pode executar o seguinte script na pasta webApp:"
                    + " -> atualizarRecursosWebBasicosSBCompEWebapp.sh <- "
                    + "Mas ATENÇÃO! O web.xml e jettyweb serão substituídos! "
                    + "e os composite-componentes do projeto na pasta SBComp, serão atualizados", t);
        }

        ItfUsuario usuario = controleDeSessao.getSessaoAtual().getUsuario();
        String recurso = null;
        try {
            List<String> partes = UtilSBWPServletTools.getSlugsDeUrl(requisicao);
            //String recurso = "/resources/SBComp/SBSystemPages/paginaNaoEncontrada.xhtml";

            for (String parteUrl : partes) {
                EstruturaDeFormulario pagina = MapaDeFormularios.getPaginaBySlug(parteUrl);

                if (pagina != null) {
                    if (pagina.isUmModal()) {
                        continue;
                    }
                    try {
                        if (SBCore.isIgnorarPermissoes()) {
                            recurso = MapaAcoesSistema.getAcaoDoSistemaByNomeUnico(pagina.getAcaoGestaoVinculada().getNomeUnico()).getComoFormulario().getXhtml();
                        } else if (pagina.getAcaoGestaoVinculada() != null) {
                            AcaoGestaoEntidade acaoGestVinculada = pagina.getAcaoGestaoVinculada();
                            recurso = MapaAcoesSistema.getAcaoDoSistemaByNomeUnico(acaoGestVinculada.getNomeUnico()).getComoFormulario().getXhtml();
                            if (pagina.getAcaoGestaoVinculada().isPrecisaPermissao()) {
                                if (!SBCore.getCentralPermissao().isAcaoPermitidaUsuario(usuario, pagina.getAcaoGestaoVinculada())) {
                                    carregarFormulario(UtilSBWP_JSFTools.FORMULARIO_ACESSO_NEGADO, requisicao, resposta);
                                    return;
                                }
                            }
                        }

                        break;
                    } catch (Throwable e) {
                        SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "erro tentnado aplicar paramentros de URL padrão", e);
                        break;
                    }
                }
            }
            if (recurso == null) {
                throw new UnsupportedOperationException("Não foi possível determinar a pagina vincula a url" + requisicao.getRequestURL().toString());
            }

        } catch (Throwable t) {
            recurso = controleDeSessao.getSessaoAtual().getUsuario().getGrupo().getPaginaInicial().getRegistro().getComoFormulario().getXhtml();
            try {
                if (recurso == null) {
                    recurso = MapaAcoesSistema.getAcaoDoSistemaByNomeUnico(SBWebPaginas.getAcaoPAginaInicial().getNomeUnico()).getComoFormulario().getXhtml();
                    if (recurso == null) {

                        recurso = "/site/home.xhtml";
                    }

                }
            } catch (Throwable tt) {
                SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro verificando site padrão pagina padrão do usuario logado", tt);
                recurso = "/site/home.xhtml";
            }
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro tentando Obter pagina vincula ao url" + requisicao.getRequestURL().toString(), t);
        }

        ConfiguracoesDeFormularioPorUrl configuracoes = new ConfiguracoesDeFormularioPorUrl(requisicao);
        requisicao.setAttribute(NOME_BEAN_REQUEST_CONFIG_URL, configuracoes);
        carregarFormulario(recurso, requisicao, resposta);
    }

}
