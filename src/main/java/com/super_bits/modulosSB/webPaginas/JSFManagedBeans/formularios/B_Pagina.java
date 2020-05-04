package com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios;

import com.google.common.collect.Lists;
import com.super_bits.modulos.SBAcessosModel.model.acoes.acaoDeEntidade.AcaoGestaoEntidade;
import com.super_bits.modulosSB.Persistencia.ConfigGeral.SBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreReflexao;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringFiltros;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringValidador;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfParametroRequisicao;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfParametroRequisicaoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfRespostaAcaoDoSistema;
import static com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.TIPO_PARTE_URL.ENTIDADE;
import static com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.TIPO_PARTE_URL.OBJETO_COM_CONSTRUCTOR;
import static com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.TIPO_PARTE_URL.TEXTO;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoController;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoFormulario;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoGerenciarEntidade;
import com.super_bits.modulosSB.SBCore.modulos.Controller.UtilSBController;
import com.super_bits.modulosSB.SBCore.modulos.Controller.fabricas.FabTipoAcaoSistemaGenerica;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.ComunicacaoAcaoSistema;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.FabTipoRespostaComunicacao;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.ItfComunicacao;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.ItfRespostaComunicacao;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.ItfTipoRespostaComunicacao;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ItfFabricaAcoes;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.TIPO_PRIMITIVO;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.reflexao.ReflexaoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;
import com.super_bits.modulosSB.webPaginas.JSFBeans.SBBeanModel.DEPRECIADO.InfoMBBean;
import com.super_bits.modulosSB.webPaginas.JSFBeans.SBBeanModel.InfoMBAcao;
import com.super_bits.modulosSB.webPaginas.JSFBeans.modal.ItfModalRespostaComComunicacao;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.declarados.util.PgUtil;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.declarados.util.PgUtilModalControle;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.interfaces.ItfB_Modal;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.interfaces.ItfB_Pagina;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.interfaces.ItfB_PaginaComEtapaVinculada;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.interfaces.ItfPaginaGerenciarEntidade;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.modal.ModalDadosPaginaSimples;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.reflexao.ItfBeanDeclarado;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.reflexao.anotacoes.beans.InfoMBIdComponente;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.reflexao.anotacoes.beans.InfoMB_Acao;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.reflexao.anotacoes.beans.InfoMB_Bean;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.reflexao.anotacoes.beans.InfoMB_IdWidget;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.siteMap.MapaDeFormularios;

import com.super_bits.modulosSB.webPaginas.controller.servletes.tratamentoErro.ErroSBCriticoWeb;
import com.super_bits.modulosSB.webPaginas.controller.paginasDoSistema.FabAcaoPaginasDoSistema;
import com.super_bits.modulosSB.webPaginas.controller.servletes.WebPaginasServlet;
import com.super_bits.modulosSB.webPaginas.controller.servletes.servletWebPaginas.ConfiguracoesDeFormularioPorUrl;
import com.super_bits.modulosSB.webPaginas.controller.servletes.servletWebPaginas.EstruturaDeFormulario;
import com.super_bits.modulosSB.webPaginas.controller.servletes.util.UtilFabUrlServlet;
import com.super_bits.modulosSB.webPaginas.controller.servletes.urls.parametrosURL.ParametroURL;
import com.super_bits.modulosSB.webPaginas.controller.servletes.urls.parametrosURL.ParametroUrlInstanciado;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWPServletTools;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWP_JSFTools;
import com.super_bits.modulosSB.webPaginas.util.UtillSBWPReflexoesWebpaginas;
import com.super_bits.modulosSB.webPaginas.visualizacao.ServicoVisuaslizacaoWebResponsivo;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.primefaces.event.SelectEvent;
import javax.persistence.EntityManager;
import org.coletivojava.fw.api.objetoNativo.comunicacao.RespostaComunicacao;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

public abstract class B_Pagina implements Serializable, ItfB_Pagina {

    @Deprecated
    public static final String PAGINAINICIALID = "inicial";
    private int id;
    private Boolean abriuPagina = false;
    private Map<String, ParametroUrlInstanciado> parametrosURL;
    private List<ItfParametroRequisicaoInstanciado> parametrosOrdenados;
    private boolean parametrosDeUrlPreenchido = false;
    @Inject()
    protected PgUtil paginaUtil;

    protected abstract String defineTitulo();

    protected abstract String defineNomeLink();

    protected abstract String defineDescricao();
    private boolean anotacoesConfiguradas = false;
    private String tagUsada;
    @Deprecated
    private String titulo;
    @Deprecated
    private String linkRotulo;
    @Deprecated
    private String descricao;
    @InfoMB_Bean(descricao = "Div onde será exibido o xhtml da ação selecionada", exemplo = "<h:painelGroup id={#PaginaAtual.infoPagina.idConteudoPagina}")
    protected String idAreaExbicaoAcaoSelecionada = "idSBLayotAreaAcaoSelecionada";
    protected String xhtmlAcaoAtual;
    protected ItfAcaoDoSistema acaoSelecionada;
    private List<ItfAcaoDoSistema> acoesDaPagina;
    private Map<Integer, ItfAcaoDoSistema> historico_acoes_Executadas;
    private static List<AcaoGestaoEntidade> acoesMB;
    private String nomeMB;
    private final Map<String, String> infoIds = new HashMap<>();
    private final Map<String, String> infoWidget = new HashMap<>();
    private final Map<String, BeanDeclarado> beansDeclarados = new HashMap<>();
    private final List<InfoMBAcao> infoAcoes = new ArrayList<>();
    protected final Map<String, FabTipoRespostaComunicacao> mapaRespostasComunicacaoTransienteDeAcaoByAcoes = new HashMap();
    protected final Map<String, ComunicacaoAcaoSistema> mapaComunicacaoTransienteDeAcaoByIdModal = new HashMap();
    protected final Map<String, ComunicacaoAcaoSistema> mapaComunicacoesTransienteDeAcaoAguardandoResposta = new HashMap();
    private String codigoComunicacaoAguardandoRespostaAtual;
    private final Map<String, ItfComunicacao> mapaComunicacoesAguardandoResposta = new HashMap<>();

    private EntityManager emPagina;
    protected FabTipoFormulario tipoFormulario;

    private EstruturaDeFormulario estruturaFormulario;

    @InfoMB_Bean(descricao = "Campo instanciado Selecionado")
    private ItfCampoInstanciado campoInstanciadoSelecionado;

    private ItfB_Modal modalAtual;
    @Inject
    private PgUtilModalControle modalControle;
    private InfoDesignFormulario informacoesDesign;

    protected Boolean getAbriuPagina() {
        return abriuPagina;
    }

    /**
     * Configura se ao
     *
     * @param pOpcao
     */
    protected void configFecharEntityManagerAoListar(boolean pOpcao) {

    }

    public B_Pagina() {
        tipoFormulario = FabTipoFormulario.PAGINA_SIMPLES;
        UtilSBCoreReflexao.instanciarListas(this);
    }

    private boolean isPaginaEmProcessoDeAberturaInicial() {
        if (SBCore.isEmModoDesenvolvimento()) {
            return false;
        }
        return (UtilSBWPServletTools.getRequestBean("CfgURLFrm") != null);
    }

    protected PgUtil getPaginaUtil() {
        if (SBCore.isEmModoDesenvolvimento()) {
            paginaUtil = new PgUtil();
        }
        return paginaUtil;
    }

    protected void setAcaoSelecionadaPorEnum(ItfFabricaAcoes fabrica) {
        if (fabrica != null) {
            setAcaoSelecionada(fabrica.getRegistro());
        }
    }

    protected void executarAcaoSelecionadaPorString(String pAcao) {
        try {
            ItfAcaoDoSistema acao = getAcaoVinculada().getSubAcaoByString(pAcao);
            if (acao != null) {
                setAcaoSelecionada(acao);
                if (acao.isUmaAcaoFormulario()) {
                    xhtmlAcaoAtual = acao.getComoFormulario().getXhtml();
                }
                executarAcaoSelecionada();
            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro executando subAção por String" + pAcao + " em " + this.getClass().getSimpleName(), t);
        }
    }

    protected void executaAcaoSelecionadaPorEnum(ItfFabricaAcoes fabrica) {
        if (fabrica != null) {
            setAcaoSelecionada(fabrica.getRegistro());
            executarAcaoSelecionada();
        }
    }

    protected void setEExecutaAcaoSelecionada(ItfAcaoDoSistema pAcao) {

        setAcaoSelecionada(pAcao);
        executarAcaoSelecionada();
    }

    protected boolean isAcaoSelecionadaIgualA(ItfFabricaAcoes fabrica) {
        if (acaoSelecionada == null) {
            return false;

        }
        return acaoSelecionada.getEnumAcaoDoSistema().equals(fabrica);
    }

    public Object getInstanciaPagina() {
        return this;
    }

    protected void atualizarIdAreaExibicaoAcaoSelecionada() {
        if (!SBCore.isEmModoDesenvolvimento()) {
            if (!paginaUtil.isLoadDaPaginaRealizado()) {
                return;
            }
            if (isPaginaEmProcessoDeAberturaInicial()) {
                xhtmlAcaoAtual = acaoSelecionada.getComoFormulario().getXhtml();
            } else {
                getPaginaUtil().atualizaTelaPorID(idAreaExbicaoAcaoSelecionada);
            }
        } else {
            SBCore.enviarAvisoAoUsuario("Atualizada area de exibição: " + idAreaExbicaoAcaoSelecionada);

        }
    }

    public class BeanDeclarado extends ReflexaoCampo implements ItfBeanDeclarado {

        private final InfoMBBean infoBean;

        public BeanDeclarado(Field campoReflection) {
            super(campoReflection);
            infoBean = new InfoMBBean(campoReflection);

        }

        @Override
        public InfoMBBean getInfoBean() {
            return infoBean;
        }

        @Override
        public Object getInstancia() {
            return getInstanciaPagina();
        }

        @Override
        public String getVisualizacaoItem() {
            if (getValor() == null) {
                return ServicoVisuaslizacaoWebResponsivo.CAMINHO_ITEM_SIMPLES_NULO;
            } else {
                return infoBean.getVisualizacaoItem();
            }
        }
    }

    @Override
    public ItfAcaoGerenciarEntidade getAcaoVinculada() {
        try {
            return getEstruturaFormulario().getAcaoGestaoVinculada().getEnumAcaoDoSistema().getRegistro().getComoGestaoEntidade();
        } catch (Throwable e) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro Obtendo ação vinculada a pagina" + this.getClass().getSimpleName(), e);
        }
        return null;
    }

    @Override
    public EntityManager getEMPagina() {
        if (!SBCore.isEmModoDesenvolvimento()) {
            //  return emPagina;
        }
        try {
            if (emPagina == null) {
                emPagina = UtilSBPersistencia.getNovoEM();
            } else if (!emPagina.isOpen()) {
                emPagina = UtilSBPersistencia.getNovoEM();
            }
            return emPagina;
        } catch (Exception e) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro Obtendo entity manager para pagina", e);
        }
        return null;
    }

    private void renovarEMPagina(boolean segundaExcucao) {

        try {
            if (!SBPersistencia.isConfigurado()) {
                return;
            }
            try {
                if (emPagina != null) {

                    if (emPagina.getTransaction().isActive()) {
                        emPagina.getTransaction().rollback();
                    }
                    emPagina.clear();
                    emPagina.close();

                }

            } catch (Throwable t) {
                SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro renovando Entitymanager da PAgina talvez você tenha encerrado o entity manager manualmente..", t);
            }

            emPagina = null;
            emPagina = getEMPagina();

        } catch (Exception e) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro ao renovar EM", null);
            if (segundaExcucao) {
                renovarEMPagina(true);
            }
        }
    }

    protected void renovarEMPagina() {
        renovarEMPagina(false);

    }

    /**
     * Beans de ações JSF: actions e actionsListeners
     *
     * @return Retorna a lista de ações da Pagina
     */
    @Override
    public List<InfoMBAcao> getInfoAcoes() {
        return infoAcoes;
    }

    /**
     * Beans de exibição
     *
     * @return Retorna a lista de beans da Pagina
     */
    @Override
    public List<BeanDeclarado> getInfoBeans() {
        return Lists.newArrayList(beansDeclarados.values());
    }

    /**
     * Retorna a lista de IDS de componentes dinamicos da pagina
     *
     * @return id dinamico
     */
    @Override
    public Map<String, String> getInfoIds() {
        return infoIds;
    }

    @Override
    public String getNomeMB() {
        return nomeMB;
    }

    public void setNomeMB(String nomeMB) {
        this.nomeMB = nomeMB;
    }

    protected void configParametros() {

        try {

            if (parametrosURL != null) {
                return;
            }

            parametrosURL = new HashMap<>();
            parametrosURL.clear();
            parametrosOrdenados = new LinkedList<>();

            List<Field> lista = UtilSBCoreReflexao.procuraCamposPorTipo(this, ParametroURL.class
            );
            for (Field cp : lista) {
                ParametroUrlInstanciado novoParametro = new ParametroUrlInstanciado(UtilFabUrlServlet.getInfoParametroDeUrl(cp));
                cp.set(this, novoParametro);
                if (parametrosURL.get(novoParametro.getNome()) != null) {
                    throw new UnsupportedOperationException("Dois parametros com o mesmo nome foram definidos no MB de formulário" + this.getClass().getSimpleName(), null);
                }
                parametrosURL.put(UtilSBCoreStringFiltros.gerarUrlAmigavel(novoParametro.getNome()), novoParametro);
                parametrosOrdenados.add(novoParametro);

            }
            if (parametrosURL.isEmpty()) {
                parametrosDeUrlPreenchido = true;
            }

        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.PARA_TUDO, "Erro configurando parametros da pagina" + this.getClass().getSimpleName(), t);
        }

    }

    private void aplicarAnotacoes() {
        try {
            if (anotacoesConfiguradas) {
                return;
            }
            anotacoesConfiguradas = true;
            configEstruturaFormulario();
            SBCore.soutInfoDebug("Configurando Anotações de Bean");

            nomeMB = "#{" + this.getClass().getSimpleName() + "}";
            SBCore.soutInfoDebug("Configurando Anotações de Pagnia");
            SBCore.soutInfoDebug("Constructor da pagina " + this.getClass().getName() + " finalizado");
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro aplicando anotações da pagina" + this.getClass().getName(), t);
        }
    }

    @Override
    public void aplicaValoresDeParametrosModoDesenvolvimento(Map<String, String> valorStringPorParametro) {
        if (SBCore.getEstadoAPP() != SBCore.ESTADO_APP.DESENVOLVIMENTO) {
            throw new UnsupportedOperationException("O metodo aplicaValores de parametros modo DESENVOLVIMENTO só pode ser chamado com a aplicação no modo desenvolvimento");
        }
        aplicaValoresURLEmParametros(valorStringPorParametro);

    }

    public void processarURL(String url) {

    }

    protected void aplicaValoresURLEmParametros(Map<String, String> valorStringPorParametro) {

        try {
            for (String pr : getMapaParametros().keySet()) {

                String valorStringURL = valorStringPorParametro.get(pr);
                ParametroUrlInstanciado parametro = parametrosURL.get(pr);

                if (UtilSBCoreStringValidador.isNuloOuEmbranco(valorStringURL)) {
                    if (parametro.isParametroObrigatorio()) {

                        throw new UnsupportedOperationException("O valor do parametro obrigatorio nao foi enviado" + parametro.getNome());

                    } else {
                        continue;
                    }
                }

                if (valorStringURL != null) {
                    switch (parametro.getTipoParametro()) {
                        case TEXTO:
                            parametro.aplicarParteURLenviada(valorStringURL);
                            break;
                        case ENTIDADE:
                            parametro.aplicarParteURLenviada(valorStringURL, getEMPagina());

                            break;
                        case OBJETO_COM_CONSTRUCTOR:
                            parametro.aplicarParteURLenviada(valorStringURL);
                            break;

                        default:
                            throw new AssertionError(parametro.getTipoParametro().name());

                    }
                    switch (parametro.getTipoParametro()) {
                        case OBJETO_COM_CONSTRUCTOR:
                        case ENTIDADE:
                            if (parametro.isUmParametoEntidadeMBPrincipal()) {
                                setBeanSelecionado((ItfBeanSimples) parametro.getValor());
                            }
                            break;
                    }

                }
            }

        } catch (Throwable ex) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro aplicando Valores de parametros da pagina" + this.getClass() + " pela URL", ex);
        }

    }

    private void verificaAbriuPagina() {
        if (!abriuPagina) {
            try {
                throw new ErroSBCriticoWeb("pagina" + estruturaFormulario.getNomeCurto()
                        + "não herdou AbrePagina");
            } catch (ErroSBCriticoWeb e) {
                SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Utilizando bean Pagina Atual sem Chamar método abrir pagina", e);
            }
        }
    }

    protected EstruturaDeFormulario getEstruturaFormulario() {
        if (estruturaFormulario == null) {
            return MapaDeFormularios.getEstruturaByClasseMB(this.getClass().getName());
        }
        return estruturaFormulario;
    }

    private void configEstruturaFormulario() {
        if (estruturaFormulario == null) {
            MapaDeFormularios.getEstruturaByClasseMB(this.getClass().getName());
        }
    }

    private void buldBeansDeclarados() {
        Field[] camposDeclarados = this.getClass().getDeclaredFields();
        Method[] metodos = this.getClass().getDeclaredMethods();

        for (Method metodo : metodos) {
            InfoMB_Acao acao = metodo.getAnnotation(InfoMB_Acao.class
            );
            InfoMB_IdWidget widget = metodo.getAnnotation(InfoMB_IdWidget.class
            );
            if (acao != null) {
                infoAcoes.add(new InfoMBAcao("#{" + this.getClass().getSimpleName() + "." + metodo.getName() + "}", acao.descricao()));
            }
            if (widget != null) {
                infoWidget.put(metodo.getName(), widget.descricao());

            }
        }
        for (Field campo : camposDeclarados) {

            InfoMBIdComponente idcomp = campo.getAnnotation(InfoMBIdComponente.class
            );
            try {
                if (TIPO_PRIMITIVO.getTIPO_PRIMITIVO(campo).equals(TIPO_PRIMITIVO.ENTIDADE)) {
                    beansDeclarados.put(campo.getName(), new BeanDeclarado(campo));
                }
            } catch (Throwable t) {
                System.out.println("todo retirar criação de beandeclarado em modo criação offiline");
            }
            //            if (infoBean != null) {

            //          }
            if (idcomp != null) {
                infoIds.put(campo.getName(), idcomp.descricao());
                campo.setAccessible(true);
                try {
                    campo.set(this, campo.getName());
                } catch (IllegalArgumentException | IllegalAccessException ex) {
                    SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro configurando anotações de InfoBean de " + this.getClass().getSimpleName(), ex);
                }
            }

        }
    }

    @Override
    @PreDestroy
    public void fecharPagina() {
        if (emPagina != null) {
            if (emPagina.isOpen()) {
                emPagina.close();
            }
        }
        SBCore.soutInfoDebug("Bean de escopo" + this.getClass().getSimpleName() + " Encerrado (B_Pagina.FecharPagina)");
    }

    @Override
    public String getTitulo() {
        return titulo;
    }

    @Override
    public String getDescricao() {
        return descricao;
    }

    @Override
    public String getRecursoXHTML() {
        return estruturaFormulario.getRecursoXHTML();
    }

    @Override
    public List<String> getTags() {

        return estruturaFormulario.getTagsPalavraChave();
    }

    /**
     *
     * @return
     */
    @Override
    public String getNomeCurto() {

        return estruturaFormulario.getNomeCurto();
    }

    @Override
    public String getLinkRotulo() {
        return linkRotulo;
    }

    public void setLinkRotulo(String linkRotulo) {
        this.linkRotulo = linkRotulo;
    }

    @Override
    public String getTagUsada() {
        return tagUsada;
    }

    @Override
    public void setTagUsada(String tagUsada) {

        this.tagUsada = tagUsada;
    }

    @Override
    public String getUrlPadrao() {
        //if (!permitirUsarObjetoInjetadoIgualANulo)	verificaAbriuPagina();
        if (SBCore.isEmModoDesenvolvimento()) {
            //    System.out.println("Acesso a URL padrao da pagina" + this.getClass().getSimpleName());
            return "http://www." + this.getClass().getSimpleName() + ".com.br";
        } else {
            return estruturaFormulario.getUrlPadrao();
        }
    }

    @Override
    public ItfParametroRequisicaoInstanciado getParametroInstanciado(ItfParametroRequisicao pParametro) {
        try {
            if (pParametro == null) {
                throw new UnsupportedOperationException("Enviou parametro nulo na tentativa de obter o parametro instanciado getParametrroInstanciado");
            }
            return getParametroByNome(pParametro.getNome());
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro obtendo parametro instanciado, vertifique o " + PostConstruct.class.getSimpleName() + " da pagina", t);
            return null;
        }
    }

    public String getUrlAtual() {
        return estruturaFormulario.gerarUrlPorParametro(Lists.newArrayList(parametrosURL.values()), acaoSelecionada, tagUsada);
    }

    public void setUrlPadrao(String urlCompleta) {
        estruturaFormulario.getUrlPadrao();
    }

    @Override
    public List<ItfParametroRequisicaoInstanciado> getParametrosURL() {
        if (parametrosURL == null) {
            configParametros();
        }

        return parametrosOrdenados;
    }

    protected Map<String, ParametroUrlInstanciado> getMapaParametros() {
        if (parametrosURL == null) {
            configParametros();
        }
        return parametrosURL;
    }

    @Override
    public ItfParametroRequisicaoInstanciado getParametroByNome(String pNome) {
        try {
            String indicePR = UtilSBCoreStringFiltros.gerarUrlAmigavel(pNome);
            if (getMapaParametros().containsKey(indicePR)) {
                return getMapaParametros().get(indicePR);
            } else {
                throw new UnsupportedOperationException("O nome não foi encontrado");
            }

        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro localizando parametro" + pNome, t);
            UtilSBWP_JSFTools.mensagens().erroSistema("Parametro de Pagina (" + pNome + ") nao encontrado para a pagina" + getNomeCurto());
            return null;
        }

    }

    public boolean exiteEsteParametro(String pNome) {
        return getMapaParametros().containsKey(UtilSBCoreStringFiltros.gerarUrlAmigavel(pNome));
    }

    public boolean isTemParametrobyTipoEntidade(String nomeEntidade) {
        Optional<ParametroUrlInstanciado> pesquisaPr = parametrosURL.values().stream().filter(pr -> pr.getTipoEntidade().getSimpleName().equals(nomeEntidade)).findFirst();
        return pesquisaPr.isPresent();
    }

    @Override
    public ItfParametroRequisicaoInstanciado getParametrobyTipoEntidade(String nomeEntidade) {
        try {
            Optional<ParametroUrlInstanciado> pesquisaPr = parametrosURL.values().stream().filter(pr -> pr.getTipoEntidade().getSimpleName().equals(nomeEntidade)).findFirst();
            if (!pesquisaPr.isPresent()) {
                throw new UnsupportedOperationException("a pagina " + this.getClass().getSimpleName() + " não contem um parametro do tipo" + nomeEntidade);
            } else {
                return pesquisaPr.get();
            }

        } catch (Throwable t) {
            UtilSBWP_JSFTools.mensagens().erroSistema("a pagina " + this.getClass().getSimpleName() + " não contem um parametro do tipo" + nomeEntidade, t);
            return null;
        }

    }

    public boolean isParametrosDeUrlPreenchido() {
        return parametrosDeUrlPreenchido;
    }

    public Map<String, String> getInfoWidget() {
        return infoWidget;
    }

    @Override
    public boolean isAcessoLivre() {

        return !estruturaFormulario.isAcessoLivre();

    }

    @Override
    public String getNomeParametroById(int pId) {
        try {
            return getParametrosURL().get(pId).getNome();
        } catch (Exception e) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Não existe parametro com id" + pId, null);
            return null;
        }

    }

    @PostConstruct
    public void inicioAberturaDePagina() {

        try {
            estruturaFormulario = MapaDeFormularios.getEstruturaByClasseMB(this.getClass());

            aplicarAnotacoes();
            titulo = defineTitulo();
            descricao = defineDescricao();

            if (SBCore.getEstadoAPP() == SBCore.ESTADO_APP.DESENVOLVIMENTO) {
                UtillSBWPReflexoesWebpaginas.instanciarInjecoes(this);
                paginaUtil = new PgUtil();
            }

            configParametros();

            try {
                if (!SBCore.isEmModoDesenvolvimento()) {
                    ConfiguracoesDeFormularioPorUrl configuracoesDeUrl = (ConfiguracoesDeFormularioPorUrl) UtilSBWPServletTools.getRequestBean(WebPaginasServlet.NOME_BEAN_REQUEST_CONFIG_URL);

                    if (configuracoesDeUrl == null) {
                        //       System.out.println("Abandonando ações de abertura de pagina (Informações de Url que deveriam estar no request não foram encontradas)");
                        //     return;
                        // Verificação de configurações de URL ignoradas aguardando adequação do servlet com objeto de estrutura de formulario
                        //   throw new UnsupportedOperationException("A configuração de URL não foi encontrado no escopo de requisição");
                    } else {
                        //        System.out.println("As informações de url serão determinadas");
                        aplicarUrlDeAcesso(configuracoesDeUrl);
                    }
                }
            } catch (Throwable t) {
                SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro obtendo configurações de url ao abrir a pagina" + this.getClass().getSimpleName(), t);
            }

            abrePagina();
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro iniciando abertura de pagina (PostConstructor Generico), da pagina" + this.getClass().getSimpleName(), t);
        }
    }

    /**
     * -> Não precisa exibir modal quando: A ação precisa de uma comunicação
     * transiente - > Verifica o modal já foi exibido, caso não tenha sido
     * exibido chama o modal
     *
     * @return True se tiver que aparcer um modal
     */
    protected void exibeModalComunicacaoTransientPreAcaoAtual() {

        if (!(acaoSelecionada.isUmaAcaoController() && acaoSelecionada.getComoController().isTemComunicacaoTransiente())) {
            return;
        }

        String idModal = null;
        if (mapaRespostasComunicacaoTransienteDeAcaoByAcoes.isEmpty()) {
            if (acaoSelecionada.getComoController().isPrecisaJustificativa()) {
                if (acaoSelecionada.getComoController().isTemXHTMLModalVinculado()) {
                    idModal = paginaUtil.exibirModalConfirmacaoAcao(acaoSelecionada.getComoController().getXhtmlModalVinculado());
                } else {
                    idModal = paginaUtil.exibirModalConfirmacaoAcaoComColetaDeDado(UtilSBWP_JSFTools.FORMULARIO_MODAL_JUSTIFICATIVA);
                }

            }
            if (acaoSelecionada.getComoController().isPrecisaComunicacao()) {
                idModal = paginaUtil.exibirModalConfirmacaoAcao(UtilSBWP_JSFTools.FORMULARIO_MODAL_COMUNICACAO_ACAO_TRANSIENTE);

            }
        }
        if (idModal != null) {
            mapaRespostasComunicacaoTransienteDeAcaoByAcoes.clear();
            mapaComunicacaoTransienteDeAcaoByIdModal.clear();
            mapaComunicacaoTransienteDeAcaoByIdModal.put(idModal, new ComunicacaoAcaoSistema((ItfAcaoController) getAcaoSelecionada(), getBeanSelecionado()));
        }

    }

    protected boolean isRespostaComunicacaoTransientPreAcaoEnviada() {
        return mapaRespostasComunicacaoTransienteDeAcaoByAcoes.containsKey(getAcaoSelecionada().getNomeUnico());
    }

    protected void adicionarAcaoNoHistorico(ItfAcaoDoSistema pAcao) {
        if (historico_acoes_Executadas == null) {
            historico_acoes_Executadas = new HashMap<>();
        }
        ItfAcaoDoSistema ultimaAcao = historico_acoes_Executadas.get(historico_acoes_Executadas.size());
        if (ultimaAcao != null) {
            if (!ultimaAcao.equals(pAcao)) {
                historico_acoes_Executadas.put(historico_acoes_Executadas.size() + 1, pAcao);
            }
        } else {
            historico_acoes_Executadas.put(1, pAcao);
        }

    }

    /**
     *
     * -> Para obter a ultima ação de formulario executada, utilize
     * #getAcaoUltimoFormularioExecutado()
     *
     * @see #getAcaoUltimoFormularioExecutado()
     *
     * @return A ação anterior Executada
     */
    public ItfAcaoDoSistema getAcaoAnteriorExecutada() {
        return historico_acoes_Executadas.get(historico_acoes_Executadas.size() - 2);
    }

    public ItfAcaoFormulario getAcaoUltimoFormularioExecutado() {
        List<Integer> lista = Lists.newArrayList(historico_acoes_Executadas.keySet());
        Collections.reverse(lista);
        Optional<Integer> indice = lista.stream().filter(posicao -> historico_acoes_Executadas.get(posicao).isUmaAcaoFormulario()).findFirst();
        if (indice.isPresent()) {
            int idx = indice.get();
            return historico_acoes_Executadas.get(idx).getComoFormulario();
        } else {
            return null;
        }

    }

    public ItfAcaoDoSistema getAcaoUltimaDesteTipo(FabTipoAcaoSistemaGenerica pTipo) {
        if (historico_acoes_Executadas == null) {
            return null;
        }
        List<Integer> lista = Lists.newArrayList(historico_acoes_Executadas.keySet());
        Collections.reverse(lista);
        Optional<Integer> indice = lista.stream().filter(posicao -> historico_acoes_Executadas.get(posicao).getTipoAcaoGenerica().equals(pTipo)).findFirst();
        if (indice.isPresent()) {
            return historico_acoes_Executadas.get(indice.get());
        } else {
            return null;
        }
    }

    protected boolean isPermitidoAbrirFormulario() {
        if (SBCore.isIgnorarPermissoes()) {
            return true;
        }
        if (acaoSelecionada != null) {
            if (acaoSelecionada.isUmaAcaoFormulario()) {
                if (!acaoSelecionada.isPrecisaPermissao()) {
                    return true;
                }
                if (!SBCore.getCentralDeSessao().getSessaoAtual().isAcessoPermitido(acaoSelecionada)) {
                    xhtmlAcaoAtual = FabAcaoPaginasDoSistema.PAGINA_NATIVA_ACESSO_NEGADO_FRM_SUB_FORM.getRegistro().getComoFormulario().getXhtml();
                    atualizarIdAreaExibicaoAcaoSelecionada();
                    return false;
                } else {
                    return true;
                }
            } else {
                return true;
            }
        } else {
            return true;
        }
    }

    protected void autoExecAlterarFormulario(ItfAcaoFormulario pAcao) {
        try {
            if (isPermitidoAbrirFormulario()) {

                if (!acaoSelecionada.getComoFormulario().getXhtml().equals(xhtmlAcaoAtual)) {
                    xhtmlAcaoAtual = acaoSelecionada.getComoFormulario().getXhtml();

                    atualizarIdAreaExibicaoAcaoSelecionada();
                    if (!SBCore.isEmModoProducao()) {
                        SBCore.soutInfoDebug("Xhtml alterado para" + xhtmlAcaoAtual + " Solicitado alteração Ajax Area:" + idAreaExbicaoAcaoSelecionada);
                    }
                } else {
                    //        System.out.println("Info: O Managebean já estava no estado da ação:" + acaoSelecionada.getNomeUnico());
                }
            } else {
                xhtmlAcaoAtual = FabAcaoPaginasDoSistema.PAGINA_NATIVA_ACESSO_NEGADO_FRM_SUB_FORM.getRegistro().getComoFormulario().getXhtml();
            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro alterando formulario", t);
        }
    }

    /**
     *
     * Este metodo por padrão troca o xhtml atual caso nescessário, e atualiza o
     * conteudo, normalmente é substituído por um método superior, mas pode ser
     * utilizado de forma combinada (Chamando super.executarAcaoSelecionada)
     * dentro do metodo com override
     *
     *
     *
     *
     */
    @Override
    public void executarAcaoSelecionada() {
        try {
            if (acaoSelecionada == null) {
                throw new UnsupportedOperationException("A ação selecionada não pode ser nula");
            }
            if (tipoFormulario.equals(FabTipoFormulario.PAGINA_SIMPLES)) {
                if (!acaoSelecionada.getAcaoDeGestaoEntidade().equals(getAcaoVinculada())) {
                    try {
                        EstruturaDeFormulario estrutura = MapaDeFormularios.getEstruturaByNomeAcao(acaoSelecionada.getAcaoDeGestaoEntidade().getNomeUnico(), true);
                        if (estrutura.getParametrosURL().isEmpty() || getBeanSelecionado() == null) {

                            UtilSBWP_JSFTools.vaParaPagina(MapaDeFormularios.getUrlFormulario(acaoSelecionada));
                        } else {

                            Optional<ParametroURL> p = estrutura.getParametrosURL().stream().
                                    filter(pr -> pr.getTipoEntidade().getSimpleName().equals(getBeanSelecionado().getClass().getSimpleName())).findFirst();
                            if (p.isPresent()) {
                                MapaDeFormularios.getUrlFormulario(acaoSelecionada, getBeanSelecionado());
                            } else {

                                ItfCampoInstanciado cpprvinculo = null;
                                for (ParametroURL pr : estrutura.getParametrosURL()) {
                                    Optional<ItfCampoInstanciado> cp = getBeanSelecionado().getCamposInstanciados().stream()
                                            .filter(cpinst -> cpinst.getTipoCampoSTR().equals(FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA.toString())
                                            && cpinst.getValor() != null
                                            && UtilSBCoreReflexao.isClasseIgualOuExetende(cpinst.getValor().getClass(), pr.getTipoEntidade())
                                            ).findFirst();
                                    if (cp.isPresent()) {
                                        cpprvinculo = cp.get();
                                        break;
                                    }

                                }
                                if (cpprvinculo != null) {
                                    String urlAutoRedirecionamento = MapaDeFormularios.getUrlFormulario(acaoSelecionada, cpprvinculo.getValor());
                                    UtilSBWP_JSFTools.vaParaPagina(urlAutoRedirecionamento);
                                } else {
                                    UtilSBWP_JSFTools.vaParaPagina(MapaDeFormularios.getUrlFormulario(acaoSelecionada));
                                }
                            }
                        }
                    } catch (Throwable t) {
                        UtilSBWP_JSFTools.vaParaPagina(MapaDeFormularios.getUrlFormulario(acaoSelecionada));
                    }
                }
            }
            adicionarAcaoNoHistorico(acaoSelecionada);

            if (acaoSelecionada.isUmaAcaoFormulario()) {
                autoExecAlterarFormulario(acaoSelecionada.getComoFormulario());
            }

            if (acaoSelecionada.isUmaAcaoController()) {

                exibeModalComunicacaoTransientPreAcaoAtual();

            }

        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro executando ação selecionada da pagina" + this.getClass().getSimpleName(), t);
        }

    }

    /**
     *
     *
     *
     *
     *
     * @return Todas as ações do sistema declaradas no Managed Bean da pagina
     */
    @Override
    public List<ItfAcaoDoSistema> getAcoesDaPagina() {
        if (acoesDaPagina != null) {
            return acoesDaPagina;
        }
        try {
            acoesDaPagina = new ArrayList<>();

            List<Field> camposDeAcao = UtilSBCoreReflexao.getCamposRecursivoPorInterface(this.getClass(), ItfAcaoDoSistema.class, B_Pagina.class, MB_PaginaConversation.class
            );
            for (Field cp : camposDeAcao) {
                cp.setAccessible(true);
                acoesDaPagina.add((ItfAcaoDoSistema) cp.get(this));
            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.PARA_TUDO, "Erro obtendo Ação do sistema", t);
        }

        return acoesDaPagina;
    }

    @Override
    public String getIdAreaExbicaoAcaoSelecionada() {
        return idAreaExbicaoAcaoSelecionada;
    }

    @Override
    public ItfAcaoDoSistema getAcaoSelecionada() {
        return acaoSelecionada;
    }

    @Override
    public String getXhtmlAcaoAtual() {
        return xhtmlAcaoAtual;
    }

    public void setIdAreaExbicaoAcaoSelecionada(String idAreaExbicaoAcaoSelecionada) {
        this.idAreaExbicaoAcaoSelecionada = idAreaExbicaoAcaoSelecionada;
    }

    public void setAcaoSelecionada(ItfAcaoDoSistema acaoSelecionada) {
        this.acaoSelecionada = acaoSelecionada;
    }

    @Override
    public ItfPaginaGerenciarEntidade<?> getComoPaginaEntidade() {
        return ItfB_Pagina.super.getComoPaginaEntidade(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BeanDeclarado getBeanDeclarado(String nomeBean) {
        if (beansDeclarados == null || beansDeclarados.isEmpty()) {
            buldBeansDeclarados();
        }
        return beansDeclarados.get(nomeBean);
    }

    @Override
    public void aplicarUrlDeAcesso(ConfiguracoesDeFormularioPorUrl pConfig) {

        configParametros();
        if (pConfig.getStringsParametros().size() < estruturaFormulario.getQuantidadeParametrosObrigatorios()) {
            parametrosDeUrlPreenchido = false;
            return;
        } else {
            parametrosDeUrlPreenchido = true;
        }
        if (estruturaFormulario.getParametrosURL().isEmpty()) {
            parametrosDeUrlPreenchido = true;
        }

        Map<String, String> valoresStrPorParametro = new HashMap<>();
        for (int idParametro = 0; idParametro < getMapaParametros().size(); idParametro++) {
            if (idParametro <= pConfig.getStringsParametros().size() - 1) {
                String indiceParametro = UtilSBCoreStringFiltros.gerarUrlAmigavel(parametrosOrdenados.get(idParametro).getNome());
                valoresStrPorParametro.put(indiceParametro, pConfig.getStringsParametros().get(idParametro));

            }
        }
        aplicaValoresURLEmParametros(valoresStrPorParametro);
        for (String acao : pConfig.getStringAcoes()) {
            executarAcaoSelecionadaPorString(acao);
        }

    }

    @Override
    public void fecharPaginaMatandoViewScoped() {
        fecharPagina();
        FacesContext.getCurrentInstance().getViewRoot().getViewMap().clear();
    }

    @Override
    public void abrePagina() {
        try {
            if (abriuPagina) {
                SBCore.soutInfoDebug("Comando Abre PAgina já foi executado, saindo do método");
                //throw new UnsupportedOperationException("Comando abre pagina foi chamado 2 vezes");
                return;
            }

            abriuPagina = true;
            SBCore.soutInfoDebug("Comando Abre Pagina de " + this.getClass() + "Sendo executado pela primeira vez, os parametros iniciais serão definidos:");

            // DEFININDO OS VALORES DE PARAMETROS POR URL
            if (!isParametrosDeUrlPreenchido()) {
                //    System.out.println("Os parametros não estavam preenchidos, redirecionando a pagina");
                //UtilSBWP_JSFTools.vaParaPagina(getUrlPadrao());
            } else {

            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro execuntando metodo abre pagina de " + this.getClass().getSimpleName(), t);
        }
    }

    @Override
    public ItfB_Modal getModalAtual() {
        try {
            UIViewRoot rootview = null;
            if (!SBCore.isEmModoDesenvolvimento()) {

                if (modalAtual != null) {
                    return modalAtual;
                } else {

                    throw new UnsupportedOperationException("O modal atual da pagina ainda não foi instanciado");
                }

            } else {

                modalAtual = new ModalDadosPaginaSimples(rootview, this.getClass());
                ((ModalDadosPaginaSimples) modalAtual).setEntidadeSelecionada(getBeanSelecionado());
            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro Obtendo modal Atual da pagina atual" + getClass().getSimpleName(), t);
        }
        return modalAtual;
    }

    @Override
    public FabTipoRespostaComunicacao getRespostaAcaoAtual() {
        FabTipoRespostaComunicacao resp = mapaRespostasComunicacaoTransienteDeAcaoByAcoes.get(getAcaoSelecionada().getNomeUnico());

        if (resp == null) {
            return FabTipoRespostaComunicacao.FECHAR;
        }
        return resp;
    }

    @Override
    public void setTipoRespostaParaAcaoAtual(ItfTipoRespostaComunicacao pTipoResp) {
        mapaRespostasComunicacaoTransienteDeAcaoByAcoes.put(getAcaoSelecionada().getNomeUnico(), pTipoResp.getFabricaTipoResposta());

    }

    @Override
    public ComunicacaoAcaoSistema getComunicacaoTransientAcaoByIdModal(String pIdModal) {
        return mapaComunicacaoTransienteDeAcaoByIdModal.get(pIdModal);
    }

    @Override
    public void metodoRespostaModalPrimefaces(SelectEvent event) {
        if (event.getObject() != null) {
            metodoRespostaModal(event.getObject());
        } else {
            metodoRespostaModal();
        }
    }

    @Override
    public void setModalAtual(ItfB_Modal pModal) {
        modalAtual = pModal;
    }

    @Override
    public void metodoRespostaModal(Object... pParametros) {

        if (pParametros != null) {
            for (Object p : pParametros) {
                if (p instanceof ItfTipoRespostaComunicacao) {
                    setTipoRespostaParaAcaoAtual((ItfTipoRespostaComunicacao) p);
                }
            }
        }

        if (pParametros != null) {
            for (Object p : pParametros) {
                if (p instanceof ItfRespostaAcaoDoSistema) {
                    setTipoRespostaParaAcaoAtual(((ItfRespostaComunicacao) p).getTipoResposta());
                }
            }
        }
        if (!mapaRespostasComunicacaoTransienteDeAcaoByAcoes.isEmpty()) {
            if (mapaRespostasComunicacaoTransienteDeAcaoByAcoes.containsKey(getAcaoSelecionada().getNomeUnico())) {
                executarAcaoSelecionada();
                mapaRespostasComunicacaoTransienteDeAcaoByAcoes.clear();
                mapaComunicacaoTransienteDeAcaoByIdModal.clear();
            }

        }
    }

    @Override
    public void adicionarCodigoCoversa(String pCodigoConversa) {
        try {
            ItfComunicacao comunicacao = SBCore.getCentralComunicacao().getComnunicacaoRegistrada(pCodigoConversa);
            if (comunicacao == null) {
                throw new UnsupportedOperationException("Impossível responder ao pedido de resposta solicitado, o codigodeComunicação não foi encontrado");
            }
            mapaComunicacoesAguardandoResposta.put(pCodigoConversa, comunicacao);

            codigoComunicacaoAguardandoRespostaAtual = comunicacao.getCodigoSelo();

        } catch (Throwable t) {

            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Errro adicionando conversa à pagina", t);
        }
    }

    @Override
    public ItfComunicacao getComunincacaoAguardandoResposta() {

        return SBCore.getCentralComunicacao().getComnunicacaoRegistrada(codigoComunicacaoAguardandoRespostaAtual);

    }

    @Override
    public void zerarDadosModal() {
        modalAtual = null;
    }

    @Override
    public ItfB_PaginaComEtapaVinculada getComoPaginaComEtapa() {
        try {
            return (ItfB_PaginaComEtapaVinculada) this;
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "A pagina não implementa" + ItfB_PaginaComEtapaVinculada.class.getSimpleName(), t);
            return null;
        }
    }

    @Override
    public ItfB_Pagina getComoPaginaDeGestao() {
        try {
            if (this instanceof ItfB_Pagina) {
                return this;
            } else {

                throw new UnsupportedOperationException(this.getClass().getSimpleName() + " não implementa " + ItfB_Pagina.class.getSimpleName());
            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.PARA_TUDO, "Erro obtendo pagina como pagina de Gestão MB", t);
            return null;
        }
    }

    @Override
    public ItfCampoInstanciado getCampoInstSelecionado() {
        return campoInstanciadoSelecionado;
    }

    @Override
    public void setCampoInstSelecionado(ItfCampoInstanciado pCampoInstanciado) {
        campoInstanciadoSelecionado = pCampoInstanciado;
    }

    @Override
    public InfoDesignFormulario getInfoLayout() {
        if (informacoesDesign == null) {
            informacoesDesign = new InfoDesignFormulario(this, paginaUtil.getSessao().getTelaUsuario());
        }
        return informacoesDesign;
    }

    @Override
    public ItfB_Pagina getComoFormularioWeb() {
        return ItfB_Pagina.super.getComoFormularioWeb(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isUmaPaginaGestaoEntidade() {
        return ItfB_Pagina.super.isUmaPaginaGestaoEntidade(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void selAcaoDeCtrParaUltimoFrm() {
        ItfAcaoFormulario ultimoForm = getAcaoUltimoFormularioExecutado();
        if (getAcaoSelecionada() != null && getAcaoSelecionada().isUmaAcaoController() && ultimoForm != null) {
            acaoSelecionada = ultimoForm;
            xhtmlAcaoAtual = ultimoForm.getXhtml();
        }
        //  paginaUtil.enviaMensagem("teste");
    }

    public boolean isAcaoAtualAcaoControllerPadrao() {
        if (getBeanSelecionado() == null) {
            return false;
        }
        if (!getAcaoSelecionada().isUmaAcaoController()) {
            return false;
        }
        Method metodo = UtilSBController.getMetodoByAcaoController(getAcaoSelecionada().getComoController());
        if (metodo == null) {
            throw new UnsupportedOperationException("Nenhum método foi encontrado vinculado a ação" + getAcaoSelecionada().getNomeUnico());
        }
        if (metodo.getParameterCount() > 1) {
            return false;

        }

        if (metodo.getParameterCount() > 1) {

            return false;
        }
        String tipoMetodoParametro = metodo.getParameterTypes()[0].getSimpleName();
        String tipoEntidadeSelecionada = getBeanSelecionado().getClass().getSimpleName();

        if (tipoMetodoParametro.equals(tipoEntidadeSelecionada)) {
            return true;
        } else {
            try {
                return metodo.getParameterTypes()[0].isInstance(getBeanSelecionado());

            } catch (Throwable t) {
                return false;
            }

        }

    }

    /**
     * Substitua este método para executar a chamada ao método de ação
     * controller de forma manual
     *
     * Para subistituir as que tratam o Objeto Resposta, Substituir
     *
     * @see
     * #autoExecProximaAcaoAposController(com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoController,
     * com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfRespostaAcaoDoSistema)
     *
     * @param pEntidade Entidade selecionada
     * @return A resposta da execução de ação controller
     */
    public ItfRespostaAcaoDoSistema autoExecAcaoController(ItfBeanSimples pEntidade) {

        ItfRespostaAcaoDoSistema respExecucao = null;
        if (pEntidade == null || getBeanSelecionado() == null) {
            throw new UnsupportedOperationException("Bean  selecionado é nulo durante chamada de método controller padrão: " + acaoSelecionada.getNomeUnico());
        }
        if (isAcaoAtualAcaoControllerPadrao()) {
            try {
                respExecucao = execucaoAcaoControllerPadrao(acaoSelecionada.getComoController(), false);

                if (respExecucao.isSucesso()) {
                    renovarEMPagina();
                }

            } catch (Throwable t) {
                SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro Obtendo Método vinculado a ação de controller da ação" + acaoSelecionada, t);
            }
        } else {
            throw new UnsupportedOperationException("Não foi encontrado um método padrão para a ação " + acaoSelecionada);
        }
        if (respExecucao == null) {
            return null;
        }

        return respExecucao;
    }

    public ItfRespostaAcaoDoSistema execucaoAcaoControllerPadrao(ItfAcaoController pAcaoController, boolean pExecutarPosAcaoPadrao) {

        try {

            Method metodo = UtilSBController.getMetodoByAcaoController(pAcaoController);

            ItfRespostaAcaoDoSistema resp = (ItfRespostaAcaoDoSistema) metodo.invoke(null, getBeanSelecionado());
            if (pExecutarPosAcaoPadrao) {

                autoExecProximaAcaoAposController(pAcaoController, resp);

            }
            return resp;
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro ao executar método por implementação padrão", t);
            return null;
        }
    }

    protected void autoExecProximaAcaoAposController(ItfAcaoController pAcaoController, ItfRespostaAcaoDoSistema pResposta) {

        if ((pResposta == null)) {
            ItfAcaoFormulario ultimoForm = getAcaoUltimoFormularioExecutado();
            if (ultimoForm != null) {
                setAcaoSelecionada(ultimoForm);
                executarAcaoSelecionada();
            } else {
                throw new UnsupportedOperationException("Ultima ação de formulário não encontrada");
            }
        } else {
            if (pResposta.isTemUrlDestino()) {
                UtilSBWP_JSFTools.vaParaPagina(pResposta.getUrlDestino());
                return;
            }
            if (pResposta.isSucesso()) {
                if (pResposta.getTipoRetorno().isInstance(getBeanSelecionado())) {
                    try {
                        if ((pResposta.getRetorno() != null)) {
                            setBeanSelecionado((ItfBeanSimples) pResposta.getRetorno());

                        }

                    } catch (Throwable t) {
                        SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro configurando Bean selecionado por retorno em resposta", t);
                    }
                }
            }
            if (pResposta.isTemProximoFormulario()) {
                setAcaoSelecionada(pResposta.getAcaoProximoFormulario());
                executarAcaoSelecionada();
                return;
            }
            if (!pResposta.isSucesso()) {

                ItfAcaoFormulario ultimaAcaoForm = getAcaoUltimoFormularioExecutado();
                if (ultimaAcaoForm != null) {
                    setAcaoSelecionada(ultimaAcaoForm);
                    xhtmlAcaoAtual = ultimaAcaoForm.getXhtml();
                    return;
                } else {

                    throw new UnsupportedOperationException("Ultima ação de formulário não encontrada");
                }

            } else {

                switch (pAcaoController.getTipoAcaoGenerica()) {

                    case CONTROLLER_SALVAR_EDICAO:
                    case CONTROLLER_SALVAR_NOVO:
                    case CONTROLLER_ATIVAR_DESATIVAR:
                    case CONTROLLER_SALVAR_MODO_MERGE:
                    case CONTROLLER_ATIVAR:
                    case CONTROLLER_REMOVER:
                    case CONTROLLER_DESATIVAR:
                        ItfAcaoDoSistema ultimaAcaoListagem = getAcaoUltimaDesteTipo(FabTipoAcaoSistemaGenerica.FORMULARIO_LISTAR);
                        if (ultimaAcaoListagem != null) {
                            setAcaoSelecionada(ultimaAcaoListagem);
                            executarAcaoSelecionada();
                            return;
                        } else {
                            ItfAcaoDoSistema acaoListagemPadrao = pAcaoController.getAcaoDeGestaoEntidade().getAcaoFormularioListarPadrao();
                            if (acaoListagemPadrao != null) {
                                setAcaoSelecionada(acaoListagemPadrao);
                                executarAcaoSelecionada();
                                return;
                            }
                        }

                    default:
                        List acapSelecaoProximaAcao = (getAcaoVinculada().getComoGestaoEntidade().getAcoesVinculadasDesteTipo(FabTipoAcaoSistemaGenerica.SELECAO_DE_ACAO));
                        if (!acapSelecaoProximaAcao.isEmpty()) {
                            setAcaoSelecionada((ItfAcaoDoSistema) acapSelecaoProximaAcao.get(0));
                            xhtmlAcaoAtual = ((ItfAcaoDoSistema) acapSelecaoProximaAcao.get(0)).getComoFormulario().getXhtml();

                        } else {

                            ItfAcaoFormulario ultimaAcaoForm = getAcaoUltimoFormularioExecutado();
                            if (ultimaAcaoForm != null) {
                                setAcaoSelecionada(ultimaAcaoForm);
                                executarAcaoSelecionada();
                            } else {
                                ItfAcaoFormulario acaoGEstao = pAcaoController.getAcaoDeGestaoEntidade();
                                throw new UnsupportedOperationException("Ultima ação de formulário não encontrada");
                                //    setAcaoSelecionada(acaoGEstao);
                                //  executarAcaoSelecionada();
                            }
                        }
                }

            }
        }
    }
}
