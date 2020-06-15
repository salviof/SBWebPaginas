/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulosSB.webPaginas.JSFManagedBeans.declarados.util;

import com.google.common.collect.Lists;
import com.sun.faces.facelets.el.TagValueExpression;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreDataHora;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreNumeros;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringFiltros;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringGerador;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringGerador.TIPO_LOREN;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringValidador;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo.UtilSBCoreArquivos;
import com.super_bits.modulosSB.SBCore.modulos.Mensagens.FabMensagens;
import com.super_bits.modulosSB.SBCore.modulos.geradorCodigo.model.EstruturaCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.ItensGenericos.basico.BeanTodosSelecionados;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.CaminhoCampoExibicaoFormulario;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.CaminhoCampoReflexao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.GrupoCampos;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.ItfCampoExibicaoFormulario;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.ItfGrupoCampos;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.CampoNaoImplementado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.MapaObjetosProjetoAtual;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;
import com.super_bits.modulosSB.SBCore.modulos.view.telas.LayoutTelaAreaConhecida;
import com.super_bits.modulosSB.webPaginas.ConfigGeral.SBWebPaginas;
import com.super_bits.modulosSB.webPaginas.JSFBeans.temas.Tema;
import com.super_bits.modulosSB.webPaginas.JSFBeans.util.Cores;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.declarados.webSite.InfoWebApp;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.interfaces.ItfB_Pagina;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.interfaces.ItfPaginaAtual;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.interfaces.ItfPaginaGerenciarEntidade;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.reflexao.anotacoes.beans.InfoMB_Acao;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.siteMap.AcaoComLink;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.siteMap.AcaoDeContexto;
import com.super_bits.modulosSB.webPaginas.controller.servletes.WebPaginasServlet;
import com.super_bits.modulosSB.webPaginas.controller.sessao.QlSessaoFacesContext;
import com.super_bits.modulosSB.webPaginas.controller.sessao.SessaoAtualSBWP;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWPServletTools;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWP_JSFTools;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.el.ELContext;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.component.UINamingContainer;
import javax.faces.component.html.HtmlPanelGroup;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import org.coletivojava.fw.utilCoreBase.UtilSBCoreComunicacao;
import org.primefaces.PrimeFaces;
import org.primefaces.component.fieldset.FieldsetRenderer;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.expression.SearchExpressionUtils;

/**
 *
 * @author Salvio
 */
@RequestScoped
@Named
public class PgUtil implements Serializable {

    @Inject
    private Cores cores;
    @Inject
    private Tema tema;

    @Inject
    @QlSessaoFacesContext
    private SessaoAtualSBWP sessao;

    @Inject
    private InfoWebApp infoWeb;

    @Inject
    private ItfPaginaAtual paginaAtual;

    @Inject
    private PgUtilModalControle controleModal;

    private Boolean loadDaPaginaRealizado = null;

    public void testeMuitoLouco(ItfB_Pagina pagina) {
        pagina.executarAcaoSelecionada();
    }

    public boolean isLoadDaPaginaRealizado() {
        if (SBCore.isEmModoDesenvolvimento()) {
            return true;
        }
        if (UtilSBWPServletTools.getRequestBean(WebPaginasServlet.NOME_BEAN_REQUEST_CONFIG_URL) == null) {
            loadDaPaginaRealizado = true;
            return true;
        } else {
            loadDaPaginaRealizado = false;
            return false;
        }

    }

    private final CampoNaoImplementado camponaoImplementado = new CampoNaoImplementado();

    private BeanTodosSelecionados beanTodosSelecionados = new BeanTodosSelecionados();

    public void mensagemAlerta(String pMensagem) {
        SBCore.enviarMensagemUsuario(pMensagem, FabMensagens.ALERTA);
    }

    public void mensagemErro(String pMensagem) {
        SBCore.enviarMensagemUsuario(pMensagem, FabMensagens.ERRO);
    }

    public void mensagemInfo(String pMensagem) {
        SBCore.enviarMensagemUsuario(pMensagem, FabMensagens.AVISO);
    }

    @Deprecated
    public BeanTodosSelecionados getBeanTodosSelecionados() {
        return beanTodosSelecionados;
    }

    /**
     *
     * @param beanTodosSelecionados
     */
    @Deprecated
    public void setBeanTodosSelecionados(BeanTodosSelecionados beanTodosSelecionados) {
        this.beanTodosSelecionados = beanTodosSelecionados;
    }

    /**
     *
     * @deprecated @param pXHTML
     * @return
     */
    public String abrirXHTML(String pXHTML) {
        return pXHTML;
    }

    public Tema getTema() {

        if (tema == null) {
            return new Tema();
        }
        return tema;
    }

    /**
     *
     * Retorna a lista de cores padrão do sistema
     *
     * @return
     */
    public Cores getCores() {
        if (cores == null) {
            cores = new Cores();
        }
        return cores;
    }

    /**
     *
     * @return Data atual Do servidor em long
     */
    public long getDataHoraLong() {

        return new Date().getTime();
    }

    public Date getDataHora() {
        return new Date();
    }

    @InfoMB_Acao(descricao = "Evento de ajax que recebe o atributo idAtualizacao  <p ajax event='onAlgumaCoisa') que atualiza uma parte da tela pelo ID")
    public static void eventAtualizaTelaPorID(AjaxBehaviorEvent event) {

        String id = (String) event.getComponent().getAttributes().get("idAtualizacao");
        System.out.println("Atualizando o id" + id);
        if (id == null) {
            UtilSBWP_JSFTools.mensagens().erroSistema("o atributo idAtualizacao não foi encontrado, é necessário criar o atributo no componente" + event.getComponent().toString());
        }
        UtilSBWP_JSFTools.atualizaPorId(id);

    }

    /**
     *
     * Atualiza uma area da pagina a partir de um id
     *
     * o caminho do id não precisa ser único, tudo que tiver este id será
     * atualizado
     *
     * @param idAtualizacao
     */
    public void atualizaTelaPorID(String idAtualizacao) {

        if (SBCore.isEmModoDesenvolvimento()) {
            SBCore.enviarAvisoAoUsuario("Id pagina atualizado:" + idAtualizacao);
            return;
        }

        if (!isLoadDaPaginaRealizado()) {
            return;
        }
        try {
            String id = idAtualizacao;
            if (SBCore.getEstadoAPP() != SBCore.ESTADO_APP.PRODUCAO) {
                System.out.println("Atualizando a exibição dos componentes nomeados com: " + id);

            }

            if (id == null) {

            }
            UtilSBWP_JSFTools.atualizaPorId(id);
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro na tentativa de atualizar o ID" + idAtualizacao, t);
        }
    }

    /**
     *
     * Mostra uma mensagem no formato Aviso na tela do usuário
     *
     * @param pMensagem Mensagem que deseja exibir
     */
    public void enviaMensagem(String pMensagem) {
        SBCore.enviarMensagemUsuario(pMensagem, FabMensagens.AVISO);
        atualizaTelaPorID(LayoutTelaAreaConhecida.AREA_MENSAGEM_INTERFACE);
    }

    /**
     *
     * @param titulo Titulo do Grupo
     * @param campos Campos relacionados
     * @return Um gropo de campos com os campos instanciados
     */
    public GrupoCampos gerarGrupoCamposEntidadePaginaAtual(String titulo, String... campos) {
        ItfPaginaGerenciarEntidade pagina;
        try {
            pagina = (ItfPaginaGerenciarEntidade) paginaAtual.getInfoPagina();
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "A paginaatual não é do tipo Gerenciamento de Entidade, impossível determinar a entidade vinculada ao grupo", t);
            return new GrupoCampos("Grupo de Campos inreconhesível");
        }
        String entidade = pagina.getBeanDeclarado("entidadeSelecionada").getInfoBean().getClasse();
        return gerarGrupoCamposEntidade(entidade, titulo, campos);

    }

    /**
     *
     * @param entidade Entidade onde os Campos estão armazenados
     * @param titulo Título do Grupo
     * @param campos Camnpos
     * @return
     */
    public GrupoCampos gerarGrupoCamposEntidade(String entidade, String titulo, String... campos) {
        GrupoCampos grupoCampo = new GrupoCampos(titulo);
        try {
            for (String campo : campos) {
                grupoCampo.adicionarCampo(new CaminhoCampoExibicaoFormulario(new CaminhoCampoReflexao(entidade + "." + campo), grupoCampo.getNomeIdentificadorSlug()));
            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Não foi possível gerar um grup de campos com " + campos, t);
        }

        return grupoCampo;

    }

    public String gerarNomeSlug(String pTextoNormal) {
        if (pTextoNormal == null) {
            return null;
        }
        return UtilSBCoreStringFiltros.gerarUrlAmigavel(pTextoNormal);
    }

    /**
     *
     *
     *
     *
     * Procura pelos componentes com este id, e retorna o caminho completo do
     * componente
     *
     *
     * Ex. um componente com o id = areaParaAtualizacao pode durante a
     * reinderização do jsf ficar com o nome
     * conteudo.divDaEsquerda.jstdid12.areaparaAtualizacao
     *
     *
     * @param pId O id de componente que deseja saber o caminho completo
     *
     *
     * @return O caminho complento ex:
     * componenteAvó.componentePai.componenteFilho
     */
    public String gerarCaminhoCompletoID(String pId) {
        return gerarCaminhoCompletoIDParaJavaScript(pId);
    }

    /**
     * TODO: remover todos os metodos auxiliares do inputSB.xmhl para classe
     * relacionada ao um render gernérico
     *
     * @param pId
     * @param pComponente
     * @return
     */
    public String gerarCaminhoCompletoIDRelativoSBInput(String pId, UIComponent pComponente) {
        try {

            if (pId.contains("@this")) {
                String idAtualizaca = getIdInputSBComponentePai(pComponente);
                if (pId.equals("@this")) {
                    //String idinput = getNomeIdComponenteInput(componenteAdequado);
                    return idAtualizaca;
                } else {
                    pId = pId.replace("@this", idAtualizaca);
                }

            }
            StringBuilder caminhoCompleto = new StringBuilder();
            int x = 0;
            for (String parte : pId.split(" ")) {
                if (parte.startsWith("@")) {
                    parte = SearchExpressionUtils.resolveClientId(pId, pComponente);
                }
                if (x > 0) {
                    caminhoCompleto.append(" ");
                }
                caminhoCompleto.append(parte);
                x++;
            }

            return gerarCaminhoCompletoIDParaJavaScript(caminhoCompleto.toString());
        } catch (Throwable t) {
            return null;
        }
    }

    /**
     *
     * TODO: remover todos os metodos auxiliares do input.xmhl para classe
     * relacionada ao um render gernérico
     *
     * @param pid
     * @param pCpInst
     * @return
     */
    public String gerarIdAtualizacaoPadraoInput(String pid, ItfCampoInstanciado pCpInst) {

        if (pCpInst.isTemValidadacaoLogica() || pCpInst.getFabricaTipoAtributo().isPossuiValidacaoLogicaNativa()) {
            if (UtilSBCoreStringValidador.isNuloOuEmbranco(pid)) {
                return "@this";
            }
            if (!pid.contains("@this")) {
                return pid + " @this";
            }

        } else {
            return pid;
        }
        return pid;
    }

    public String gerarCaminhoCompletoIDParaJavaScript(String pId) {
        if (UtilSBCoreStringValidador.isNuloOuEmbranco(pId)) {
            return pId;
        }
        if (pId.contains(":")) {
            if (!pId.contains(" ")) {
                return pId;
            }
        }

        String caminhosCOmpletos = "";
        int i = 0;
        String[] caminhosEnviados = pId.split(" ");
        for (String caminho : pId.split(" ")) {
            String separadorCaminhosEncontradors = " ";
            if (i == caminhosEnviados.length - 1) {
                separadorCaminhosEncontradors = "";
            }
            if (!UtilSBCoreStringValidador.isNuloOuEmbranco(caminho)) {

                if (caminho.contains("@") || caminho.contains(":")) {

                    caminhosCOmpletos += caminho + separadorCaminhosEncontradors;
                } else {
                    caminhosCOmpletos += UtilSBWP_JSFTools.getIDSCaminhoAbsoluto(caminho).substring(1) + separadorCaminhosEncontradors;
                }
            }
            i++;
        }

        return caminhosCOmpletos;
    }

    /**
     *
     *
     *
     * @param pId
     * @deprecated Nome de método muito feio, será substituido por
     * gerarCaminhoCompletoID
     *
     * @return
     */
    public String makeCaminhoCompletoID(String pId) {

        return gerarCaminhoCompletoID(pId);
    }

    /**
     *
     * @return A URL inicial deste projeto
     */
    public String getEnderecoPagina() {
        return SBWebPaginas.getURLBase();
    }

    /**
     *
     * Copia todos os resources do WebApp atual para pasta de desenvolvimento do
     * projeto
     *
     */
    public void copiarRessource() {
        if (UtilSBCoreArquivos.copiarArquivos(UtilSBWP_JSFTools.getCaminhoLocalRessource(), SBCore.getCaminhoDesenvolvimento() + "/src/main/webapp/resources")) {
            SBCore.enviarMensagemUsuario("Arquivos da pasta Ressource copiados com sucesso", FabMensagens.AVISO);
        } else {
            SBCore.enviarMensagemUsuario("Aconteceu um erro ao copiar os resources", FabMensagens.ERRO);
        }
    }

    /**
     *
     * Função axiliar para eencomtrar p caminho completo do componente, atravez
     * do component.clientID,
     *
     * @param pClientID O nome completo (onde o ultimo componente será removido
     * do nome)
     * @return O caminho do ID obtido sem nome do ultimo componente
     */
    public String makeCaminhoComponenteByClientID(String pClientID) {

        boolean fim = false;
        boolean encontrouDoisPontos = false;
        String novoCaminho = new String();
        for (int i = pClientID.length() - 1; i >= 0; i--) {
            Character novo = pClientID.charAt(i);

            if (encontrouDoisPontos) {
                novoCaminho = novo.toString() + novoCaminho;
            }
            if (novo.equals(':')) {
                encontrouDoisPontos = true;
            }

        }

        return novoCaminho;
    }

    /**
     * @deprecated
     *
     * @param pId
     * @return
     */
    public String getInfoComponente(String pId) {
        try {
            UIComponent componenteRaiz = FacesContext.getCurrentInstance().getViewRoot();
            UIComponent componenteEncontrador = componenteRaiz.findComponent(pId);
            for (UIComponent comp : componenteRaiz.getChildren()) {
                System.out.println(comp.getId());

            }

            System.out.println("");
            return pId;
        } catch (Throwable t) {
            return "Aconteceu Um Erro";
        }
    }

    public String getIdInputPai(UIComponent comp) {
        try {
            if (comp == null) {
                return null;
            }
            if (isComponentDeInput(comp)) {
                return comp.getClientId();
            } else {
                return getIdInputSBComponentePai(comp.getParent());
            }

        } catch (Throwable t) {
            return null;
        }
    }

    public String getIdInputSBComponentePai(UIComponent comp) {
        try {
            if (comp == null) {
                return null;
            }
            if (comp.getClientId().endsWith(LayoutTelaAreaConhecida.AREA_INPUT_GENERICO)) {
                return comp.getClientId();
            } else {
                return getIdInputSBComponentePai(comp.getParent());
            }

        } catch (Throwable t) {
            return null;
        }
    }

    // Retorna true se o componente for um componente inpt do primefaces
    private boolean isComponentDeInput(UIComponent comp) {
        if (comp == null || comp.getRendererType() == null) {
            return false;
        }

        //System.out.println("Verificando tipo de Componente:" + comp.getRendererType());
        //TODO Testar abordagem mais elegante : || comp instanceof UIInput;
        return comp.getRendererType().contains("org.primefaces.component.InputTextRenderer")
                || comp.getRendererType().contains("org.primefaces.component.SelectOneMenuRenderer")
                || comp.getRendererType().contains("Input")
                || comp.getRendererType().contains("AutoComplete")
                || comp.getRendererType().contains("select")
                || comp.getRendererType().contains("Select")
                || comp.getRendererType().contains("Password")
                || comp.getRendererType().contains("PickList")
                || comp.getRendererType().contains("ColorPicker")
                || comp.getRendererType().contains("Calendar")
                || comp.getRendererType().contains("Slider")
                || comp.getRendererType().contains("Numbe")
                || comp.getRendererType().contains("Numbe")
                || comp.getRendererType().contains("FileUpload")
                || comp.getRendererType().contains("TextEditor")
                || comp.getRendererType().contains("CKEditor")
                || comp.getRendererType().contains("CkEditor");

    }

    private UIComponent getComponeneInputMaster(UIComponent componente) {
        if (componente == null) {
            return null;
        }
        if (!componente.getAttributes().isEmpty()) {
            if (componente.getAttributes().get("inputColetivoJAvaGui") != null) {
                return componente;
            }
            return getComponeneInputMaster(componente.getParent());
        } else {
            return getComponeneInputMaster(componente.getParent());
        }
    }

    private UIComponent getComponenteInputParent(UIComponent componente) {
        if (componente == null) {
            return null;
        } else {
            if (isComponentDeInput(componente)) {
                return componente;
            } else {
                return getComponenteInputParent(componente.getParent());
            }
        }
    }

    private UIComponent getComponenteInputChilds(UIComponent componente) {
        if (componente == null) {
            return null;
        }
        Iterator<UIComponent> children = componente.getFacetsAndChildren();
        for (Iterator iterator = children; children.hasNext();) {
            Object next = iterator.next();
            if (next instanceof UIInput) {
                return componente;
            }
        }
        return null;

    }

    public String getNomeIdComponenteInput(UIComponent componente) {
        return getNomeIdComponenteInput(componente, true);
    }

    public UIComponent getComponentInputFormPadraoChildRecursivo(UIComponent componente) {
        if (componente.getClientId().endsWith(LayoutTelaAreaConhecida.AREA_INPUT_GENERICO)) {
            return componente;
        }
        for (Iterator iterator = componente.getFacetsAndChildren(); iterator.hasNext();) {
            UIComponent comp = (UIComponent) iterator.next();
            if (comp.getClientId().endsWith(LayoutTelaAreaConhecida.AREA_INPUT_GENERICO)) {
                return comp;
            } else {
                UIComponent compRec = getComponentInputFormPadraoChildRecursivo(comp);
                if (compRec != null) {
                    return compRec;
                }
            }
        }
        return null;
    }

    public String getNomeIdComponenteInputParaCompositeComp(UIComponent componente) {

        UIComponent componenteInput = componente.findComponent(LayoutTelaAreaConhecida.AREA_INPUT_GENERICO);
        for (UIComponent cp : componenteInput.getChildren()) {
            if (cp.isRendered()) {
                return getNomeIdComponenteInput(cp, false);
            }
        }
        return getNomeIdComponenteInput(componente, false);
    }

    private UIComponent getComponenteRecursivoIntput(UIComponent componente) {

        if (componente == null) {
            return null;
        }
        if (isComponentDeInput(componente)) {
            return componente;
        } else {
            if (componente.getChildCount() == 0) {
                return null;
            }
            UIComponent compEncontrado = null;
            Iterator<UIComponent> componentes = componente.getFacetsAndChildren();
            UIComponent comp;
            while (componentes.hasNext()) {
                comp = componentes.next();
                if (isComponentDeInput(comp)) {
                    return comp;
                } else {
                    if (comp != null) {
                        compEncontrado = getComponenteRecursivoIntput(comp);
                        if (compEncontrado != null) {
                            return compEncontrado;
                        }

                    } else {
                        return compEncontrado;
                    }
                }
            }
        }
        return null;

    }

    public String getNomeIdComponenteInput(UIComponent componente, boolean componenteInicial) {
        try {
            if (componenteInicial && componente.getParent() != null) {
                UIComponent componenteencontrado = getComponenteRecursivoIntput(componente.getParent());
                if (componenteencontrado != null) {
                    return componenteencontrado.getClientId();
                } else {
                    return null;
                }

            } else {
                return getComponenteRecursivoIntput(componente).getClientId();

            }

        } catch (Throwable t) {

            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "erro obtendo id do input " + componente.getId() + t.getMessage(), t);
            UIComponent componenteMater = getComponeneInputMaster(componente);
            UIComponent compFilho = getComponenteInputChilds(componenteMater);
            if (compFilho != null) {
                return compFilho.getId();
            }
            for (UIComponent comp : componente.getParent().getChildren()) {

                if (isComponentDeInput(comp)) {
                    return comp.getId();
                }
                for (UIComponent compNivel2 : comp.getChildren()) {

                    System.out.println("Nivel 1: " + comp + " nivel 2: " + compNivel2);

                }
            }
            if (componente.getParent() != null) {
                return componente.getParent().getId();
            } else {
                return null;
            }
        }
    }

    public String getIDComponenteFilhoPorCordenada(UIComponent componente, int... indices) {
        if (componente == null) {
            System.out.println("Enviado componente nulo buscando id por cordenada");
            return null;
        }
        try {
            UIComponent componenteAtual = componente;

            for (int id : indices) {
                componenteAtual = componente.getChildren().get(id);
            }
            return componenteAtual.getClientId();
        } catch (Throwable t) {
            System.out.println("Inposível encontrar componente pela cordenada " + indices);
            return componente.getClientId();
        }
    }

    public String buscaIdDestaClasse(UIComponent componente, String atributo) {
        UIComponent comp = componente.getChildren().get(3).getChildren().get(1);

        System.out.println(comp.getId());
        System.out.println(comp.getAttributes().keySet());
        System.out.println(comp.getAttributes().values());
        System.out.println(comp.getFamily());
        System.out.println(comp.getRendererType());

        return "Ainda não Implmentado mas o id é:" + comp.getClientId();
    }

    public void preencherEndereco(String pcep) {
        System.out.println("CEP ENVIADO:" + pcep);

        // UtilSBCoreCEP.configuraEndereco(pcep, pLocal);
    }

    public String buscaFilhoComEsteID(UIComponent componente, String atributo) {
        String resultado = UtilSBWP_JSFTools.getAbsoluteComponentPaths(UtilSBWP_JSFTools.resolveList(atributo, componente));
        return resultado.replace(":" + componente.getClientId(), resultado);

    }

    /**
     *
     * Informa se um atributo do composite componente foi preenchido com algum
     * texto ex: <p:botaoDeAcao label="textoFoiPrenchido" />
     * #{pgUtil.isAtributoPreenchidoComExpressao(component)} retornaria
     * Verdadeiro
     *
     *
     * @param component O componente que será analizado
     * @param atributo O nome do atributo
     * @return
     */
    public boolean isAtributoPreenchidoComExpressao(UINamingContainer component, String atributo) {

        try {

            if (component == null) {
                throw new UnsupportedOperationException("O componente não foi enviado para verificação de atributo preenchido");
            }
            Object valor = component.getValueExpression(atributo);
            if (valor == null) {
                if (!component.getAttributes().containsKey(atributo)) {
                    return false;
                    //   throw new UnsupportedOperationException("O componente" + component.getRendererType() + " não possui o atributo enviado para verficação: " + atributo);
                }
                // Este médo passou a retornar nulo na versão 2.3 do mojarra
                //  ValueExpression valor = component.getValueExpression(atributo);
                valor = component.getAttributes().get(atributo);
            }
            if (valor != null) {
                return (valor.toString().length() > 1);
            } else {
                return false;
            }

        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro verificando se o atributo [" + atributo + "]do componente [" + component + "]  foi configurado", t);
            return false;
        }
    }

    public static List<String> getCepEncontrados() {
        List<String> ceps = new ArrayList<>();
        ceps.add("30190030 - Rua goias ");
        ceps.add("30190030 - Rua Juca fontes ");

        return ceps;
    }

    /**
     *
     * Retonrna a URL vinculada a ação (Está deprecated)
     *
     * @param pAcao Acao
     * @return XHTML que deve ser carregado
     * @deprecated Metodo será substituido por Carregar XHTML
     */
    @Deprecated
    public String navegar(ItfAcaoDoSistema pAcao) {

        if (pAcao != null) {
            return infoWeb.getAcaoComLink(pAcao).getUrlDeAcesso();
        } else {
            return null;
        }
    }

    /**
     *
     * redireciona o usuário da sessão para a pagina correspondente a ação
     * enviada no parametro
     *
     * @param pAcao Ação correspondente a URL desejada
     */
    public void irParaURL(ItfAcaoDoSistema pAcao) {
        try {

            if (!SBCore.isEmModoDesenvolvimento()) {
                if (pAcao == null) {
                    throw new UnsupportedOperationException("Ação não enviada para navegação de URL");
                }
                AcaoComLink acaoComLinkVinculada = infoWeb.getAcaoComLink(pAcao);
                if (acaoComLinkVinculada != null) {
                    String url = acaoComLinkVinculada.getUrlDeAcesso();

                    irParaURL(url);
                } else {
                    throw new UnsupportedOperationException("Não foi possível Localizar uma ação com link a partir de " + pAcao.getNomeUnico());
                }

            } else {
                System.out.println("Enviando usuario para url da anção" + pAcao.getNomeUnico());
            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "ação não enviada para navegação", t);
        }

    }

    /**
     *
     *
     *
     * @param pUrl Redireciona o usuário para uma nova URL
     */
    public void irParaURL(String pUrl) {

        if (!SBCore.isEmModoDesenvolvimento()) {
            UtilSBWP_JSFTools.vaParaPagina(pUrl);
        } else {
            System.out.println("Enviando usuário para" + pUrl);
        }

    }

    /**
     *
     * Exibe um campo não implementado (um campo com todas as propriedades de
     * campo, desvinculado a qualquer campo existente
     *
     * @return Um campo não implementado
     */
    public CampoNaoImplementado getCampoNaoImplementado() {
        return camponaoImplementado;
    }

    /**
     *
     * Função que retorna true caso todos os booleans enviados no parametros
     * sejam true. (Util para simplificar a utlização do JTDSL em alguns casos)
     *
     * @param pCondicoes Array de booleans para nálise
     * @return Vertadeiro se tudo for true
     */
    public boolean isTudoVerdadeiro(boolean... pCondicoes) {

        for (boolean condicao : pCondicoes) {
            if (!condicao) {
                return false;
            }
        }

        return true;
    }

    /**
     *
     * Função que retorna true caso todos os booleans enviados no parametros
     * sejam true. (Util para simplificar a utlização do JTDSL em alguns casos)
     *
     * @param pObjetos Array de Objetos para analise
     * @return Vertadeiro se tudo for true
     */
    public boolean isTudoPreenchido(Object... pObjetos) {

        for (Object objeto : pObjetos) {

            try {

                objeto.toString();

            } catch (Throwable t) {

                return false;

            }

        }

        return true;
    }

    /**
     *
     * @return Retorna um loren Ipsum de uma palavra
     */
    public String getLorrenIpsUmaPalavra() {
        return UtilSBCoreStringGerador.GetLorenIpsilum(1, TIPO_LOREN.PALAVRAS);
    }

    /**
     *
     * @return Retorna 5 palavras do lorenIpsum
     */
    public String getLorrenIpsUmaFrase() {
        return UtilSBCoreStringGerador.GetLorenIpsilum(3, TIPO_LOREN.PALAVRAS);
    }

    /**
     *
     * @return Um paragrafro de palavras LorenIpsum
     */
    public String getLorrenIpsUmParagrafo() {
        return UtilSBCoreStringGerador.GetLorenIpsilum(1, TIPO_LOREN.PARAGRAFO);
    }

    /**
     *
     * @return 3 paragrafos lorenIpsum
     */
    public String getLorrenIps3Paragrafos() {
        return UtilSBCoreStringGerador.GetLorenIpsilum(3, UtilSBCoreStringGerador.TIPO_LOREN.PARAGRAFO);
    }

    public String getSaudacao() {
        return UtilSBCoreComunicacao.getSaudacao();
    }

    public AcaoDeContexto getAcaoDeContexto(ItfAcaoDoSistema pAcao) {
        return new AcaoDeContexto(pAcao, FacesContext.getCurrentInstance(), paginaAtual.getInfoPagina());
    }

    /**
     *
     * Exibe um modal utilizando o Dialog Framwork do Primefaces
     *
     * Injeta a pagina atual do view no PgModaSBJSF para ser possível acessar as
     * informações da pagina neste bean
     *
     * @param xhtml
     */
    public void exibirModal(String xhtml) {
        exibirModal(getOpcoesModalPrimePadrao(), xhtml);
    }

    public void exibirModalComBotaoFechar(String xhtml) {
        Map<String, Object> opcoes = getOpcoesModalPrimePadrao();
        opcoes.put("closable", true);
        exibirModal(opcoes, xhtml);
    }

    public String exibirModalRetornandoIdModal(String xhtml) {
        return exibirModal(getOpcoesModalPrimePadrao(), xhtml);
    }

    public String exibirModalConfirmacaoAcaoComColetaDeDado(String pXhtml) {
        Map<String, Object> opcoes = getOpcoesModalPrimeConfirmaAcao();
        if (sessao.getTelaUsuario().isUmMobile()) {
            opcoes.put("height", sessao.getTelaUsuario().getY() + "px");
        } else {

            opcoes.put("height", "300px");
        }
        return exibirModal(opcoes, pXhtml);
    }

    public String exibirModalConfirmacaoAcao(String pXhtml) {
        return exibirModal(getOpcoesModalPrimeConfirmaAcao(), pXhtml);
    }

    private Map<String, Object> getOpcoesModalPrimeConfirmaAcao() {
        Map<String, Object> options = new HashMap<>();
        options.put("resizable", false);
        options.put("draggable", false);
        options.put("closable", false);
        options.put("modal", true);
        options.put("width", "80%");
        if (sessao.getTelaUsuario().isUmMobile()) {
            options.put("height", sessao.getTelaUsuario().getY() + "px");
        } else {

            options.put("height", "150px");
        }
        options.put("contentWidth", "100%");
        options.put("contentHeight", "100%");
        options.put("headerElement", "customheader");
        return options;
    }

    private Map<String, Object> getOpcoesModalPrimePadrao() {
        Map<String, Object> options = new HashMap<>();
        options.put("resizable", true);
        options.put("draggable", true);
        options.put("closable", true);
        options.put("modal", true);
        options.put("width", "80%");
        options.put("height", sessao.getTelaUsuario().getY() - 200 + "px");
        options.put("contentWidth", "100%");
        options.put("contentHeight", "100%");
        options.put("headerElement", "customheader");
        return options;
    }

    private String exibirModal(Map<String, Object> pOpcoesPrimefaces, Map<String, List<String>> parametrosModalRequestGet, String pFormularioModal) {
        String idView = paginaAtual.getInfoPagina().toString();
        List<String> parametrosRequestModal = new ArrayList<>();
        parametrosRequestModal.add(idView);
        parametrosModalRequestGet.put(UtilSBWP_JSFTools.NOME_PARAMETRO_CHAVE_ACESSO, parametrosRequestModal);

        pFormularioModal = pFormularioModal.replace(".xhtml", "");
        controleModal.adicionarAoMapa(paginaAtual.getInfoPagina().getComoPaginaDeGestao());
        // O formato do primefaces para enviar parametros para o modal é Um Map de String por lista
        // Mais informações em https://primefaces.github.io/primefaces/8_0/#/core/dialogframework
        PrimeFaces.current().dialog().openDynamic(pFormularioModal, pOpcoesPrimefaces, parametrosModalRequestGet);
        return idView;
    }

    public String exibirModal(Map<String, Object> pOpcoesPrimefaces, String pFormularioModal) {
        return exibirModal(pOpcoesPrimefaces, new HashMap<>(), pFormularioModal);
    }

    /**
     * Abre um formulário para envio de arquivo de entidade
     */
    public void exibirModalEnvioArquivo() {
        Map<String, Object> opcoes = getOpcoesModalPrimePadrao();
        opcoes.put("closable", true);
        if (sessao.getTelaUsuario().isUmMobile()) {
            opcoes.put("height", sessao.getTelaUsuario().getY() - 200 + "px");
            opcoes.put("width", "90%");
        } else {
            opcoes.put("height", "100px");
            opcoes.put("width", "50%");
        }

        exibirModal(opcoes, UtilSBWP_JSFTools.FORMULARIO_MODAL_ENVIO_ARQUIVO);
    }

    /**
     * Abre o furmlário de comunicação
     */
    public void exibirModalComunicacao() {
        Map<String, Object> opcoes = getOpcoesModalPrimePadrao();
        opcoes.put("closable", false);
        exibirModal(opcoes, UtilSBWP_JSFTools.FORMULARIO_MODAL_COMUNICACAO_ACAO_TRANSIENTE);
    }

    /**
     * Abre o Modal com InfoPagina
     */
    public void exibirModalInfopagina() {
        Map<String, Object> options = new HashMap<>();
        options.put("resizable", true);
        options.put("draggable", true);
        options.put("modal", true);
        options.put("width", 1000);
        options.put("height", 500);
        options.put("contentWidth", "100%");
        options.put("contentHeight", "100%");
        options.put("headerElement", "customheader");
        exibirModal(options, UtilSBWP_JSFTools.FORMULARIO_MODAL_INFO_PAGINA);
    }

    public void exibirModalObjetos() {
        Map<String, Object> options = new HashMap<>();
        options.put("resizable", true);
        options.put("draggable", true);
        options.put("modal", true);
        options.put("width", 1000);
        options.put("height", 500);
        options.put("contentWidth", "100%");
        options.put("contentHeight", "100%");
        options.put("headerElement", "customheader");
        exibirModal(options, UtilSBWP_JSFTools.FORMULARIO_MODAL_INFO_PAGINA);
    }

    public void exibirModalFormularios() {
        Map<String, Object> options = new HashMap<>();
        options.put("resizable", true);
        options.put("draggable", true);
        options.put("modal", true);
        options.put("width", 1000);
        options.put("height", 500);
        options.put("contentWidth", "100%");
        options.put("contentHeight", "100%");
        options.put("headerElement", "customheader");
        exibirModal(options, UtilSBWP_JSFTools.FORMULARIO_MODAL_INFO_PAGINA);
    }

    public void exibirModalImportacao() {
        Map<String, Object> options = new HashMap<>();
        options.put("resizable", true);
        options.put("draggable", true);
        options.put("modal", true);
        options.put("width", 1000);
        options.put("height", 500);
        options.put("contentWidth", "100%");
        options.put("contentHeight", "100%");
        options.put("headerElement", "customheader");
        exibirModal(options, UtilSBWP_JSFTools.FORMULARIO_MODAL_INFO_PAGINA);
    }

    public void exibirModalImportacaoPesquisaAvancada() {
        Map<String, Object> options = new HashMap<>();
        options.put("resizable", true);
        options.put("draggable", true);
        options.put("modal", true);
        options.put("width", 1000);
        options.put("height", 500);
        options.put("contentWidth", "100%");
        options.put("contentHeight", "100%");
        options.put("headerElement", "customheader");
        exibirModal(options, UtilSBWP_JSFTools.FORMULARIO_MODAL_PESQUISA_ITEM_AVANCADO);
    }

    public void exibirModalRequisito() {
        Map<String, Object> options = new HashMap<>();
        options.put("resizable", true);
        options.put("draggable", true);
        options.put("modal", true);
        options.put("width", 1000);
        options.put("height", 500);
        options.put("contentWidth", "100%");
        options.put("contentHeight", "100%");
        options.put("headerElement", "customheader");
        exibirModal(options, UtilSBWP_JSFTools.FORMULARIO_MODAL_REQUISITO);
    }

    private boolean isComponentPainel(UIComponent comp) {
        if (comp == null || comp.getRendererType() == null) {
            return false;
        }
        //System.out.println("Verificando tipo de Componente:" + comp.getRendererType());
        return comp.getRendererType().contains("FieldsetRenderer");

    }

    public String getIdDoComponenteAtual(UIComponent pComponente) {

        UIComponent componente = pComponente;

        return componente.getClientId();

    }

    public String getIdDoComponentePai(UIComponent pComponente) {

        UIComponent componente = pComponente.getParent();

        if (componente != null) {
            return componente.getClientId();
        } else {
            return pComponente.getClientId();
        }

    }

    public String getNomeIdPainelDoComponente(UIComponent pComponente) {
        boolean encontrou = false;
        UIComponent componente = pComponente;
        while (!encontrou) {
            if (isComponentPainel(componente)) {
                return componente.getClientId();
            } else {
                componente = componente.getParent();
                if (componente == null) {
                    return "";
                }
            }
        }
        return "";

    }

    private boolean isComponentByComponenteEstrategico(FabComponentesJSFBaseEstrategicos tipoComp, UIComponent comp) {

        switch (tipoComp) {
            case P_FIELD_SET:
                if (comp.getRendererType() == null) {
                    return false;
                }
                return (comp.getRendererType().endsWith(FieldsetRenderer.class.getSimpleName()));

            case H_PANEL_GROUP:
                if (comp instanceof HtmlPanelGroup) {
                    return true;
                } else {
                    return false;
                }

            case H_PANEL_GROUP_INPUTSB:
                if (comp instanceof HtmlPanelGroup) {
                    return comp.getId().equals("inputFormPadrao");

                } else {
                    return false;
                }

            default:
                throw new AssertionError(tipoComp.name());

        }

    }

    public String getNomeIdPainelGroupInputSB(UIComponent pComponente) {
        boolean encontrou = false;
        UIComponent componente = pComponente;

        while (!encontrou) {
            if (isComponentByComponenteEstrategico(FabComponentesJSFBaseEstrategicos.H_PANEL_GROUP_INPUTSB, componente)) {
                return componente.getClientId();
            } else {
                componente = componente.getParent();
                if (componente == null) {
                    return "";
                }
            }
        }
        return "";
    }

    public boolean getNomeIdHPanelPaiComIdentificacaoToBackAction(UIComponent pComponente, String pParamentro, ELContext contexto) {
        if (pComponente.getPassThroughAttributes().isEmpty()) {
            return false;
        }
        Object valor = pComponente.getPassThroughAttributes().get("idToBackAct");
        if (valor == null) {
            return false;
        }
        if (valor instanceof TagValueExpression) {

            return ((TagValueExpression) valor).getValue(contexto).toString().contains(pParamentro);
        }
        return valor.equals(pParamentro);

    }

    public String getNomeIdHPanelPaiComIdentifiadorPassThroughToBackAction(UIComponent pComponente, String pClasse, ELContext contexto) {
        boolean encontrou = false;
        UIComponent componente = pComponente;

        while (!encontrou) {
            if (isComponentByComponenteEstrategico(FabComponentesJSFBaseEstrategicos.H_PANEL_GROUP, componente) && getNomeIdHPanelPaiComIdentificacaoToBackAction(componente, pClasse, contexto)) {
                return componente.getClientId();
            } else {
                componente = componente.getParent();
                if (componente == null) {
                    return "";
                }
            }
        }
        return "";

    }

    public String getNomeIdHPanelPai(UIComponent pComponente) {
        boolean encontrou = false;
        UIComponent componente = pComponente;

        while (!encontrou) {
            if (isComponentByComponenteEstrategico(FabComponentesJSFBaseEstrategicos.H_PANEL_GROUP, componente)) {
                return componente.getClientId();
            } else {
                componente = componente.getParent();
                if (componente == null) {
                    return "";
                }
            }
        }
        return "";

    }

    public String getNomeIdPFieldSetPai(UIComponent pComponente) {
        boolean encontrou = false;
        UIComponent componente = null;

        try {
            if (pComponente == null) {
                throw new UnsupportedOperationException("Para encontrar o FieldSet pai do componente, o parametro componente é nescessário");
            }
            componente = pComponente;
            while (!encontrou) {
                if (isComponentByComponenteEstrategico(FabComponentesJSFBaseEstrategicos.P_FIELD_SET, componente)) {
                    return componente.getClientId();
                } else {
                    componente = componente.getParent();
                    if (componente == null) {
                        String idAlternativo = gerarCaminhoCompletoID(paginaAtual.getInfoPagina().getIdAreaExbicaoAcaoSelecionada());
                        if (UtilSBCoreStringValidador.isNAO_NuloNemBranco(idAlternativo)) {
                            return idAlternativo;
                        }
                        throw new UnsupportedOperationException();

                    }
                }
            }
        } catch (Throwable t) {
            String idComponente = "Id Não Definido";
            if (pComponente != null) {
                idComponente = pComponente.getClientId();
            }
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro Obtendo FieldSet pai para o componente" + idComponente, t);
        }
        return "";

    }

    public void removerItem(List<ItfBeanSimples> pLista, ItfBeanSimples pItem) {
        try {
            int idremover = pItem.getId();

            int indiceRemocao = 0;

            for (int i = 0; i < pLista.size(); i++) {
                ItfBeanSimples item = (ItfBeanSimples) pLista.get(i);
                if (item.getId() == idremover) {
                    indiceRemocao = i;
                    break;
                }
            }

            pLista.remove(indiceRemocao);
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro ao remover o item", t);
        }
    }

    public String gerarHoraTextoSimples(Date pDataHora) {
        if (pDataHora == null) {
            return null;
        }
        SimpleDateFormat formatador = new SimpleDateFormat("HH':'mm");
        return formatador.format(pDataHora);
    }

    public String gerarHoraTexto(Date pDataHora) {
        if (pDataHora == null) {
            return null;
        }
        SimpleDateFormat formatador = new SimpleDateFormat("HH 'Horas e' mm 'minutos'");
        return formatador.format(pDataHora);
    }

    public String gerarDataHoraTextoResumido(Date pDataHora) {
        try {
            if (pDataHora == null) {
                return null;
            }
            Locale local = new Locale("pt", "BR");

            SimpleDateFormat formatador = new SimpleDateFormat("EE, dd, 'de' MMMM 'às' HH:mm", local);
            return formatador.format(pDataHora);
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro formantando data", t);
            return null;
        }
    }

    public String gerarDataHoraTexto(Date pDataHora) {
        if (pDataHora == null) {
            return null;
        }
        Locale local = new Locale("pt", "BR");
        String diaDasemana = UtilSBCoreDataHora.getDiaDaSemana(pDataHora);

        Calendar c = Calendar.getInstance();
        //objeto d foi atribuido a Calendar
        c.setTime(pDataHora);

        SimpleDateFormat formatador = new SimpleDateFormat("'" + diaDasemana + "' dd 'de' MMM 'de' yy 'às' HH:mm", local);
        return formatador.format(pDataHora);
    }

    public String gerarDataTexto(Date pDataHora) {
        if (pDataHora == null) {
            return null;
        }
        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yy ");
        return formatador.format(pDataHora);
    }

    @Deprecated
    public void testePush() {

        try {
            // Thread.sleep(5000);
            //     EventBus evento2 = EventBusFactory.getDefault().eventBus();
            //     evento2.publish("/mensagens/" + paginaAtual.getInfoPagina().getComoPaginaDeGestao().getAcaoVinculada().getNomeUnico() + "/*/*", new Notificacao(new FacesMessage(FacesMessage.SEVERITY_WARN, "Colé!!! olha o push2 aee!!!", "Colé!!! olha o push aee!!!")));
            //exibirModalComunicacao();
            //    Thread.sleep(5000);
            //   evento.publish("/notify", new FacesMessage(FacesMessage.SEVERITY_WARN, "Colé!!! olha o push aee!!!", "Colé!!! olha o push aee!!!"));
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro enviando push" + t.getMessage(), t);
        }
    }

    @Deprecated
    public void testePushConversa() {
        // EventBus evento2 = EventBusFactory.getDefault().eventBus();
        // evento2.publish("/mensagens/" + paginaAtual.getInfoPagina().getComoPaginaDeGestao().getAcaoVinculada().getNomeUnico() + "/*/*",
        //          new Notificacao(SBCore.getCentralComunicacao().iniciarComunicacaoSistema_Usuairo(FabTipoComunicacao.NOTIFICAR, sessao.getUsuario(), "Teste Nova comunicação", ERPTransporteComunicacao.INTRANET_MODAL)));
    }

    public boolean isNuloOuEmbranco(Object pVariavel) {
        if (pVariavel == null) {
            return true;
        }
        return pVariavel.toString().length() < 1;
    }

    public boolean isValorVerdadeiro(Object pVariavel) {
        if (pVariavel == null) {
            return false;
        }
        try {

            boolean valorBoolean = (boolean) pVariavel;
            return valorBoolean;
        } catch (Throwable tboolean) {
            try {
                int valorInteiro = (int) pVariavel;
                if (valorInteiro > 0) {
                    return true;
                } else {
                    return false;
                }
            } catch (Throwable t) {

                String valoString = pVariavel.toString();

                switch (valoString) {
                    case "true":
                    case "verdadeiro":
                    case "v":
                    case "V":
                        return true;
                    default:
                        return false;

                }

            }
        }

    }

    public List converterListaEmListaSimples(List pLista) {
        return Lists.newArrayList(pLista);
    }

    public String converterNuloEmZero(Object pValor) {
        if (pValor == null) {
            return "0";
        } else {
            return String.valueOf(pValor);
        }

    }

    /**
     *
     *
     *
     * @param pValor
     * @return
     * @deprecated Utilize PgUtilFormatar
     */
    public String gerarMoeda(Double pValor) {

        return UtilSBCoreNumeros.converterMoeda(pValor);
    }

    /**
     *
     * @param pValor
     * @return Utilize PgUtilFormatar
     */
    //@Deprecated
    //public String gerarMoeda(long pValor) {
    //    return UtilSBCoreNumeros.converterMoeda(pValor);
    //}
    public void preencherAleatorioBeanSelecionado() {
        try {
            if (paginaAtual.getInfoPagina().isPaginaDeGestao()) {
                for (ItfGrupoCampos grp : paginaAtual.getInfoPagina().getComoPaginaDeGestao().getAcaoSelecionada().getComoFormulario().getGruposDeCampos()) {
                    for (ItfCampoExibicaoFormulario campo : grp.getCampos()) {
                        try {
                            ItfCampoInstanciado cp = paginaAtual.getInfoPagina().getComoPaginaDeGestao().getBeanSelecionado().getCampoInstanciadoByNomeOuAnotacao(campo.getCaminhoSemNomeClasse());
                            cp.preenchimentoAleatorio();
                        } catch (Throwable t) {

                        }
                    }
                }
            }

            atualizaTelaPorID(paginaAtual.getInfoPagina().getIdAreaExbicaoAcaoSelecionada());
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro preenchendo dados aleatórios", t);
        }
    }

    public List<String> autoCompletLivre(String query) {
        List<String> results = new ArrayList<>();

        results.add(query);

        return results;
    }

    public EstruturaCampo getInfoCampo(String pCaminhoCompleto) {
        return MapaObjetosProjetoAtual.getEstruturaCampoPorCaminhoCompleto(pCaminhoCompleto);
    }

    public SessaoAtualSBWP getSessao() {
        return sessao;
    }

    public void enviarImagemPequenaUpload(FileUploadEvent event) {
        try {
            paginaAtual.getInfoPagina().getComoFormularioWeb().getBeanSelecionado().uploadFotoTamanhoPequeno(event.getFile().getInputStream());
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro enviando imagem tamanho curto", t);
        }
    }

    public String getHorarioHHmmSSAgoraStr() {

        SimpleDateFormat formatador = new SimpleDateFormat("HH:mm:ss");
        return formatador.format(new Date());

    }

    public String getHoraHHAgoraStr() {
        SimpleDateFormat formatador = new SimpleDateFormat("HH");
        return formatador.format(new Date());

    }

    public int gerarValorInteiroCondicional(boolean condicao, int valorPositivo, int valorNegativo) {
        return condicao ? valorPositivo : valorNegativo;
    }

}
