/*
 *  Super-Bits.com CODE CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.util;

import com.super_bits.modulosSB.webPaginas.controller.servlets.WebPaginasServlet;
import com.sun.faces.component.visit.FullVisitContext;
import com.sun.faces.facelets.el.TagValueExpression;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringValidador;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.token.ItfTokenGestaoOauth;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoFormulario;
import com.super_bits.modulosSB.SBCore.modulos.Mensagens.FabMensagens;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulos.SBAcessosModel.view.FabAcaoPaginasDoSistema;
import com.super_bits.modulosSB.webPaginas.ConfigGeral.SBWebPaginas;
import com.super_bits.modulosSB.webPaginas.JSFBeans.modal.PgModalSBJSF;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.declarados.util.PgUtilTelefone;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.interfaces.ItfPaginaAtual;
import com.super_bits.modulosSB.webPaginas.controller.sessao.SessaoAtualSBWP;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.faces.component.NamingContainer;
import javax.faces.component.UIComponent;
import javax.faces.component.UINamingContainer;
import javax.faces.component.UIViewRoot;
import javax.faces.component.visit.VisitCallback;
import javax.faces.component.visit.VisitContext;
import javax.faces.component.visit.VisitResult;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import org.jboss.weld.interceptor.util.proxy.TargetInstanceProxy;
import org.primefaces.PrimeFaces;
import org.primefaces.context.PrimeFacesContext;
import com.super_bits.modulosSB.webPaginas.JSFBeans.SBBeanModel.controller.PacoteExecucaoControllerJsonSimples;
import javax.servlet.ServletException;
import org.super_bits.tags.inputGenerico.InputGenerico;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 *
 * Utilitários para manipulação em tempo de execução de componentes JSF
 *
 *
 * @see UtilSBWPServletTools
 * @author Salvio
 */
public abstract class UtilSBWP_JSFTools {

    /**
     * Nome do parametro utilizado para recuperar um objeto de pagina através do
     * modal
     *
     * @see PgModalSBJSF
     */
    public final static String NOME_PARAMETRO_CHAVE_ACESSO = "chaveAcesso";

    /**
     * Atenção: Caminho do arquivo sem extenção, o Framework de Dialogo do
     * primefaces não gosta de extenção
     *
     */
    public final static String FORMULARIO_MODAL_COMUNICACAO_ACAO_TRANSIENTE = FabAcaoPaginasDoSistema.FORMULARIO_MODAL_COMUNICACAO_ACAO_TRANSIENTE;
    public final static String FORMULARIO_MODAL_JUSTIFICATIVA = FabAcaoPaginasDoSistema.FORMULARIO_MODAL_JUSTIFICATIVA;
    public final static String FORMULARIO_MODAL_COMUNICACAO = FabAcaoPaginasDoSistema.FORMULARIO_MODAL_COMUNICACAO;

    /**
     * opções Utilitárias do desenvolvedor
     */
    public final static String FORMULARIO_MODAL_UTIL_DESENVOLVEDOR = FabAcaoPaginasDoSistema.FORMULARIO_MODAL_UTIL_DESENVOLVEDOR;

    /**
     * Informações da pagina Atual
     */
    public final static String FORMULARIO_MODAL_INFO_PAGINA = FabAcaoPaginasDoSistema.FORMULARIO_MODAL_INFO_PAGINA;

    public final static String FORMULARIO_MODAL_PESQUISA_ITEM_AVANCADO = FabAcaoPaginasDoSistema.FORMULARIO_MODAL_PESQUISA_ITEM_AVANCADO;

    public final static String FORMULARIO_MODAL_REQUISITO = FabAcaoPaginasDoSistema.FORMULARIO_MODAL_REQUISITO;

    /**
     * Atenção: Caminho do arquivo sem extenção, o Framework de Dialogo do
     * primefaces não gosta de extenção
     */
    public final static String FORMULARIO_MODAL_ENVIO_ARQUIVO = FabAcaoPaginasDoSistema.FORMULARIO_MODAL_ENVIO_ARQUIVO;
    public final static String FORMULARIO_ACESSO_NEGADO = FabAcaoPaginasDoSistema.FORMULARIO_ACESSO_NEGADO;

    public final static String FORMULARIO_OAUTH_LOGIN = FabAcaoPaginasDoSistema.FORMULARIO_OAUTH_LOGIN;

    public final static String FORMULARIO_API_RESTFUL_CONTROLLER = FabAcaoPaginasDoSistema.FORMULARIO_API_RESTFUL_CONTROLLER;

    public final static String FORMULARIO_ACESSO_NEGADO_SUB_FORM = FabAcaoPaginasDoSistema.FORMULARIO_ACESSO_NEGADO_SUB_FORM;

    public final static String FORMULARIO_RECUPERACAO_DE_SENHA = FabAcaoPaginasDoSistema.FORMULARIO_RECUPERACAO_DE_SENHA;
    public final static String FORMULARIO_ACESSO_DINAMICO_VIA_TOKEN = FabAcaoPaginasDoSistema.FORMULARIO_ACESSO_DINAMICO_VIA_TOKEN;
    public final static String FORMULARIO_RECUPERACAO_DE_SENHA_GERAR_SENHA = FabAcaoPaginasDoSistema.FORMULARIO_RECUPERACAO_DE_SENHA_GERAR_SENHA;
    public final static String FORMULARIO_LOGIN = FabAcaoPaginasDoSistema.FORMULARIO_LOGIN;
    public final static String FORMULARIO_SESSAO_EXPIROU = FabAcaoPaginasDoSistema.FORMULARIO_SESSAO_EXPIROU;
    public final static String FORMULARIO_ERRO_CRITICO = FabAcaoPaginasDoSistema.FORMULARIO_ERRO_CRITICO;

    public final static String FORMULARIO_API_JSON_WEB_CONTROLLER = FabAcaoPaginasDoSistema.FORMULARIO_API_JSON_WEB_CONTROLLER;

    public final static String FORMULARIO_HOME = FabAcaoPaginasDoSistema.FORMULARIO_HOME;
    public final static String FORMULARIO_PARAMETRO_URL_INVALIDO = FabAcaoPaginasDoSistema.FORMULARIO_PARAMETRO_URL_INVALIDO;
    public final static String FORMULARIO_API_JSON_WEB_VIEW = FabAcaoPaginasDoSistema.FORMULARIO_API_JSON_WEB_VIEW;

    /**
     * Retorna o caminho absoluto do componente exemplo:
     * :ComponenteAvô:componentePai:componenteFilho.
     *
     * @param componente com nome simples
     * @return The absolute path of the given component
     */
    private static String getAbsoluteComponentPath(UIComponent currentComponent) {
        final char separatorChar = UINamingContainer.getSeparatorChar(FacesContext.getCurrentInstance());

        String path = "";
        if (!(currentComponent instanceof NamingContainer)) {
            path = currentComponent.getId();
        }

        do {
            if (currentComponent instanceof NamingContainer) {
                path = currentComponent.getId() + (!path.isEmpty() ? separatorChar : "") + path;
            }
            currentComponent = currentComponent.getParent();
        } while (currentComponent != null);
        path = separatorChar + path;

        return path;
    }

    /**
     * Retorna o caminho absoluto de cada ID encontrado separado por espaço
     *
     * @param components The list of components
     * @return A whitespace-separated list of all absolute paths.
     * @see ComponentResolver#getAbsoluteComponentPath(UIComponent)
     */
    public static String getAbsoluteComponentPaths(Collection<UIComponent> components) {
        String paths = "";
        for (UIComponent c : components) {

            if (!paths.isEmpty()) {
                paths += " ";
            }
            paths += getAbsoluteComponentPath(c);
        }

        return paths;
    }

    /**
     * Localiza todos os ids dos componentes da pagina, e retorna todos eles
     * separado por espaço (e.g.
     * <code>:j_id1:j_id2:myId :j_id1:j_id3:myId</code>).
     *
     * @param pId
     * @param id O id do componente procurado
     * @return Lista separada por espaço com o caminho absoluto dos componentes
     * que contem este nome
     * @see #resolveList(String)
     */
    public static String getIDSCaminhoAbsoluto(String pId) throws UnsupportedOperationException {
        return getIDSCaminhoAbsoluto(pId, false);

    }

    public static String getIDSCaminhoAbsoluto(String pId, boolean ignorarErro) {
        if (UtilSBCoreStringValidador.isNuloOuEmbranco(pId)) {
            return "";
        }
        if (pId.contains(":")) {
            return pId;
        }
        if (pId.contains("@")) {
            return pId;
        }

        List<UIComponent> components = resolveList(pId);

        if (components.isEmpty()) {
            if (!ignorarErro) {
                if (!SBCore.isEmModoProducao()) {
                    System.out.println("nenhum componente foi reiderizado ainda, sistema ");
                }

                throw new UnsupportedOperationException("Nenhum componente com o id [" + pId + "] foi encontrado");
            }
        } else {

            return getAbsoluteComponentPaths(components);
        }
        return null;
    }

    /**
     * Localiza todos os compoentes com esta ID
     *
     * @param id Id to search for.
     * @return List of components that have got this id.
     */
    private static List<UIComponent> resolveList(String id) {
        return resolveList(id, FacesContext.getCurrentInstance().getViewRoot());
    }

    public static void limparEstadoDaArvoreDosInputs() {

    }

    /**
     * Percorre toda arvore de componentes buscando pelo ID informado
     *
     * @param id Localizado
     * @param currentComponent Arvore inicial onde o componente será localizado
     * @return Accumulation of all components that match the given id
     */
    public static List<UIComponent> resolveList(String id, UIComponent currentComponent) {
        List<UIComponent> accumulator = new LinkedList<UIComponent>();
        //   System.out.println("procurando por" + id + "em:" + currentComponent.getId() + currentComponent.getId());
        if (null != currentComponent.getId() && currentComponent.getId().equals(id)) {

            accumulator.add(currentComponent);
        }

        Iterator<UIComponent> childIt = currentComponent.getFacetsAndChildren();
        while (childIt.hasNext()) {
            UIComponent child = childIt.next();

            accumulator.addAll(resolveList(id, child));
        }

        return accumulator;

    }

    public static void encerrarSessaoJSessionId() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);

        session.invalidate();
    }

    public static void atualizaPorId(String pId) {
        atualizaPorId(pId, false);
    }

    public static void atualizaPorId(String pId, boolean gerarErroSeNaoEncontrar) {
        try {

            if (SBCore.getEstadoAPP() == SBCore.ESTADO_APP.DESENVOLVIMENTO) {
                SBCore.enviarMensagemUsuario("Atualizando id da pagina" + pId, FabMensagens.AVISO);
                return;
            }

            try {

                PrimeFaces contextoPrime = PrimeFaces.current();
                String caminhoCompleto = null;
                try {
                    caminhoCompleto = getIDSCaminhoAbsoluto(pId);
                } catch (Throwable t) {
                    if (!SBCore.isEmModoProducao()) {
                        System.out.println("Nehumm componente com o id " + pId + " foi encontrado, um parametro de requisição foi adicionado para que uma nova tentativa seja reliazada após carregar o formulário");
                        //TODO-> verificar se o servlet já foi carregado
                    }
                    UtilSBWPServletTools.putObjetoRequestScope("atualizar" + pId, pId);
                    if (gerarErroSeNaoEncontrar) {
                        throw new UnsupportedOperationException("Nehumm componente com o id " + pId + " foi encontrado");
                    }
                    return;
                }
                String[] camadas = caminhoCompleto.split(" ");
                List<String> lista = new ArrayList<>();

                if (camadas != null && camadas.length > 0) {
                    for (String comp : camadas) {
                        if (comp.length() > 0) {
                            if (comp.charAt(0) == ':') {
                                lista.add(comp.substring(1, comp.length()));
                            } else {
                                lista.add(comp);
                            }

                        }
                    }
                } else {
                    lista.add(caminhoCompleto);
                }

                if (lista != null) {
                    contextoPrime.ajax().update(lista);

                }
            } catch (UnsupportedOperationException e) {

                SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro Atualizando tela por id de compnente JSF" + pId, e);
            }
        } catch (Exception e) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "erro tentando atualizar ID de componente JSF" + pId, e);
        }

    }

    public static CentralDeMensagensJSFAPP mensagens() {

        return new CentralDeMensagensJSFAPP();
    }

    public static void vaParaPagina(String pURL) {

        try {
            if (SBCore.isEmModoDesenvolvimento()) {
                System.out.println("Enviou comando levando o usuário a:" + pURL);
                return;
            }

            try {
                FacesContext.getCurrentInstance().getExternalContext().responseReset();
                FacesContext.getCurrentInstance().getExternalContext().redirect(pURL);
            } catch (IllegalStateException paginaJaReinderizada) {
                PrimeFaces contextPrime = PrimeFaces.current();
                //execute javascript oncomplete

                contextPrime.executeScript("window.location.replace('" + pURL + "');");
            } catch (Throwable t) {
                SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro Reiniciando resposta e redirecionando, javascript de redurecionamento será enviado", t);

                //execute javascript oncomplete
                PrimeFaces.current().executeScript("window.location.replace('" + pURL + "');");
            }
            //FacesContext.getCurrentInstance().getEx //Timer t=Timer.getInstance();
            //  FacesContext.getCurrentInstance().responseComplete();
            //   FacesContext.getCurrentInstance().renderResponse();
            //	NavigationHandler myNav = FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
            //    myNav.handleNavigation(FacesContext.getCurrentInstance(),null, pURL);

        } catch (Exception e) {
            mensagens().alertaSistema("erro tentando redirecionar pagina" + e.getMessage() + pURL);
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro redirecionando para URL" + pURL, e);
        }
    }

    public static void carregaPagina(String pXHTML) {
        try {

            FacesContext.getCurrentInstance().getExternalContext().dispatch(pXHTML);

        } catch (IOException e) {

            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro redirecionando Load de reinderizaap de XHTML" + pXHTML, e);
        }
    }

    public static void vaParaPaginadeErro(String Mensagem) {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(SBWebPaginas.getSiteURL() + "/resources/SBComp/SBSystemPages/erroCriticoDeSistema.xhtml");
        } catch (IOException e) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro redirecionando para pagina de erro:" + Mensagem, e);
        }
    }

    public static void vaParaPaginaInicial() {
        SessaoAtualSBWP sessao = UtilSBWPServletTools.getSessaoAtual(true);
        if (sessao != null) {
            sessao.getUrlHostDaSessao();
            vaParaPagina(sessao.getUrlHostDaSessao());
        } else {
            vaParaPagina(SBWebPaginas.getSiteURL());
        }
    }

    public static void vaParaPaginadeLogin() {

    }

    public static String getCaminhoLocalRessource() {

        return UtilSBWPServletTools.getCaminhoLocalServlet() + "/resources";
    }

    public static void executarJavaScript(String pcomando) {
        PrimeFaces requestContext = PrimeFaces.current();
        requestContext.executeScript(pcomando);

    }

    public static boolean isExisteEsteFormulario(ItfAcaoFormulario pformulario) {
        return isExisteEsteFormulario(pformulario.getXhtml());
    }

    public static boolean isExisteEsteFormulario(String xhtml) {
        try {
            String caminhoPastaResoureces;
            if (SBCore.isEmModoDesenvolvimento()) {
                caminhoPastaResoureces = SBWebPaginas.getCaminhoWebAppDeveloper();
            } else {
                caminhoPastaResoureces = UtilSBWPServletTools.getCaminhoLocalServlet();
            }
            return new File(caminhoPastaResoureces + xhtml).exists();
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro verificando existencia do XHTML" + xhtml, t);
            return false;
        }

    }

    /**
     *
     * Muitas vezes você pode estar usando CDI e precisar acessar um Bean
     * programaticamente, e ao inspecionar este bean notará que ele não
     * representa a instancia correta do Objeto, e sim uma cópia modificada
     * deste Objeto, o nome da classe será
     * nomeDaClasseOriginalProxyWeldCaralhoA4.class
     *
     * E aí? o Que fazer?
     *
     * Utilize esta função fomidável que verifica se o objeto está instanciado
     * como um TargetInstanceProxy, e utilizando a própria Interface retorna a
     * instancia do proxy
     *
     *
     * @param pObjeto Objeto possivelmente clonado via Proxy
     * @return a instancia ou o póprio objeto caso não seja proxy
     */
    public static Object retirarProxyDeVisualizacaoDoBean(Object pObjeto) {
        try {
            if (pObjeto instanceof TargetInstanceProxy) {
                return ((TargetInstanceProxy) pObjeto).getTargetInstance();
            } else {
                return pObjeto;
            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro tentando extrair a instancia do proxy", t);
            return pObjeto;
        }

    }

    public static EntityManager getEntityManagerDaPaginaAtual() {
        ItfPaginaAtual paginaAtual = (ItfPaginaAtual) UtilSBWPServletTools.getBeanByNamed("paginaAtual", ItfPaginaAtual.class);
        return paginaAtual.getInfoPagina().getComoPaginaComEntityManager().getEMPagina();
    }

    public static InputGenerico getInputGenericoDoComponente(UIComponent pComponente) {

        UIComponent componente = pComponente;
        while (true) {

            if (componente instanceof InputGenerico) {
                return (InputGenerico) componente;
            } else {
                if (componente.getParent() == null) {
                    return null;
                }
                componente = componente.getParent();
            }

        }

    }

    /**
     * Retorna o primeior componente com o id do componente
     *
     * @param id
     * @return
     */
    public static UIComponent getComponenteByIdDoContextoAtual(final String id) {

        FacesContext context = FacesContext.getCurrentInstance();
        UIViewRoot root = context.getViewRoot();
        final UIComponent[] found = new UIComponent[1];

        root.visitTree(new FullVisitContext(context), new VisitCallback() {
            @Override
            public VisitResult visit(VisitContext context, UIComponent component) {
                if (component != null
                        && component.getId() != null
                        && component.getId().equals(id)) {
                    found[0] = component;
                    return VisitResult.COMPLETE;
                }
                return VisitResult.ACCEPT;
            }
        });

        return found[0];

    }

    public static ItfCampoInstanciado getCampoInstanciadoByIdNoCOntextoAtual(String idFinalDoCOmponente) {
        ItfCampoInstanciado campoInstanciado = null;
        PrimeFacesContext.getCurrentInstance().getViewRoot().getComponentResources(PrimeFacesContext.getCurrentInstance());

        UIComponent componente = UtilSBWP_JSFTools.getComponenteByIdDoContextoAtual(idFinalDoCOmponente);
        try {

            TagValueExpression tag = (TagValueExpression) componente.getPassThroughAttributes().get("campoInstanciado");
            campoInstanciado = (ItfCampoInstanciado) tag.getValue(PrimeFacesContext.getCurrentInstance().getELContext());
            return campoInstanciado;
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro obtendo o campo instanciado pelo UiComponent que deveria existir no contexto de execução, o id pesquisado foi: " + idFinalDoCOmponente, t);
            return null;
        }

    }

    public static boolean enviarMensagemViaWhatsapp(String pCelular, String pMensagem) {
        if (UtilSBCoreStringValidador.isNuloOuEmbranco(pCelular)) {
            return false;
        }
        if (UtilSBCoreStringValidador.isNuloOuEmbranco(pMensagem)) {
            return false;
        }
        String javascript = new PgUtilTelefone().gerarJavascriptWhatsqpp(pCelular, pMensagem);
        executarJavaScript(javascript);
        return true;
    }

    public static String getIDJsession() {
        String sessionId = null;
        try {
            FacesContext fCtx = FacesContext.getCurrentInstance();
            if (fCtx == null) {
                return null;
            }
            HttpSession session = (HttpSession) fCtx.getExternalContext().getSession(false);
            sessionId = session.getId();
        } catch (Throwable t) {
            return null;
        }
        return sessionId;
    }

    public static String getIDViewFeceScoped() {
        String idViewState = FacesContext.getCurrentInstance().getApplication().getStateManager().getViewState(FacesContext.getCurrentInstance());
        return idViewState;
    }

    public static boolean abrirJanelaConexaoAplicativo(ItfTokenGestaoOauth pSistema) {
        try {
            String script = "var a = document.createElement(\"a\");\n"
                    + "    \n"
                    + "    a.setAttribute(\"href\", \"#\");\n"
                    + "    a.setAttribute(\"onClick\",\"window.open('" + pSistema.getUrlObterCodigoSolicitacao() + "','" + pSistema.getComoGestaoOauth().getComoGestaoOauth().getClass().getSimpleName() + "','resizable,height=260,width=370')\");\n"
                    + "    a.style.display = \"none\";\n"
                    + "    document.body.appendChild(a);\n"
                    + "    a.click();";
            UtilSBWP_JSFTools.executarJavaScript(script);
            return true;
        } catch (Throwable t) {
            return false;
        }
    }

    public static void dispacharRespostaController(PacoteExecucaoControllerJsonSimples pPacote, HttpServletRequest pRequisicao, HttpServletResponse pResposta) throws ServletException, IOException {

        pRequisicao.setAttribute(WebPaginasServlet.NOME_BEAN_PACOTE_CONTROLLER_REQ, pPacote);
        RequestDispatcher despachadorDeRespostaParaRequisicao = pRequisicao.getRequestDispatcher(UtilSBWP_JSFTools.FORMULARIO_API_JSON_WEB_CONTROLLER);
        despachadorDeRespostaParaRequisicao.forward(pRequisicao, pResposta);
    }

}
