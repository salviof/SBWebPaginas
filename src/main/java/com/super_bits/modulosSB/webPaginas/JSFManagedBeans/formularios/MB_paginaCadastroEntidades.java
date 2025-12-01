/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios;

import com.super_bits.modulos.SBAcessosModel.model.acoes.AcaoDoSistema;
import com.super_bits.modulosSB.Persistencia.ConfigGeral.SBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.consultaDinamica.ConsultaDinamicaDeEntidade;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCListasObjeto;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCReflexao;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCReflexaoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfParametroRequisicao;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfParametroRequisicaoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfRespostaAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ComoAcaoController;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ComoAcaoControllerEntidade;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ComoAcaoSecundaria;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.estadoFormulario.FabEstadoFormulario;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoEntidade;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoFormulario;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoFormularioEntidade;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoGerenciarEntidade;
import com.super_bits.modulosSB.SBCore.modulos.Controller.UtilFabricaDeAcoesBasico;
import com.super_bits.modulosSB.SBCore.modulos.Controller.fabricas.FabTipoAcaoSistemaGenerica;
import com.super_bits.modulosSB.SBCore.modulos.Mensagens.FabMensagens;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.FabTipoRespostaComunicacao;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ComoFabricaAcoes;
import com.super_bits.modulosSB.SBCore.modulos.geradorCodigo.model.EstruturaDeEntidade;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoPreparacaoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.TIPO_PRIMITIVO;
import com.super_bits.modulosSB.SBCore.modulos.objetos.MapaObjetosProjetoAtual;
import com.super_bits.modulosSB.SBCore.modulos.objetos.estrutura.ItfEstruturaCampoEntidade;
import com.super_bits.modulosSB.SBCore.modulos.objetos.estrutura.ItfLigacaoMuitosParaUm;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ComoEntidadeSimples;
import com.super_bits.modulosSB.webPaginas.JSFBeans.PrimeFacesBeanModel.dataListLasy.BP_DataModelLasy;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.interfaces.ItfPaginaGerenciarEntidade;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.siteMap.MapaDeFormularios;

import com.super_bits.modulosSB.webPaginas.controller.servlets.servletWebPaginas.EstruturaDeFormulario;
import com.super_bits.modulosSB.webPaginas.controller.servletes.urls.parametrosURL.ParametroUrlInstanciado;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWP_JSFTools;
import com.super_bits.modulosSB.webPaginas.util.UtillSBWPReflexoesWebpaginas;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.annotation.PostConstruct;
import javax.persistence.Entity;
import org.coletivojava.fw.api.tratamentoErros.ErroPreparandoObjeto;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ComoDominioEntidadeGenerico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ComoEntidadeSimplesSomenteLeitura;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ComoStatus;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ComoAcaoDoSistema;

/**
 * ATENÇÃO A DOCUMENTAÇÃO DA CLASSE É OBRIGATÓRIA O JAVADOC DOS METODOS PUBLICOS
 * SÃO OBRIGATÓRIOS E QUANDO EXISTIR UMA INTERFACE DOCUMENTADA UMA REFERENCIA
 * DEVE SER CRIADA, A SINTAXE DE REFERENCIA É: @see_ NomeDAClasse#Metodo
 * DOCUMENTE DE FORMA OBJETIVA E EFICIENTE, NÃO ESQUEÇA QUE VOCÊ FAZ PARTE DE
 * UMA EQUIPE.
 *
 * @author <a href="mailto:salviof@gmail.com">Salvio Furbino</a>
 * @since 26/12/2015
 * @version 1.0
 *
 */
public abstract class MB_paginaCadastroEntidades<T extends ComoEntidadeSimples> extends MB_PaginaConversation implements ItfPaginaGerenciarEntidade<T> {

    private T entidadeSelecionada;
    private List<T> entidadesListadas;

    private boolean temPesquisa;
    private boolean temEditar = true;
    private boolean temNovo = true;
    private boolean temAlterarStatus = true;
    private boolean temVisualizar = true;
    protected boolean listarEntidadesLasyMode = true;

    private final List<ComoAcaoDoSistema> acoesRegistros;
    protected ItfAcaoFormularioEntidade acaoListarRegistros;
    protected final ItfAcaoFormularioEntidade acaoNovoRegistro;
    protected final ComoAcaoControllerEntidade acaoSalvarAlteracoes;

    private ItfAcaoFormularioEntidade acaoEntidadeEditar;
    private ComoAcaoControllerEntidade acaoEntidadeAlterarStatus;
    private ItfAcaoFormularioEntidade acaoEntidadeVisualizar;

    private boolean podeEditar;
    private boolean novoRegistro;
    private boolean fecharEntityManagerAoListar = true;
    protected boolean listarApenasRegistroCriadoAoListar = false;
    protected boolean listarApenasRegistrosAtivos = false;
    private Boolean umaEntidadePersistivel;
    private GestaoDeDominioEmPagina<T> gestaoDominio;
    private ItfRespostaAcaoDoSistema ultimaRespostaControllerRecebida;

    /**
     *
     * @return
     */
    public GestaoDeDominioEmPagina getGestaoDeDominio() {
        if (gestaoDominio == null) {
            gestaoDominio = new GestaoDeDominioEmPagina<>();
        }
        return gestaoDominio;
    }

    @Override
    protected void autoExecProximaAcaoAposController(ComoAcaoController pAcaoController, ItfRespostaAcaoDoSistema pResposta) {
        super.autoExecProximaAcaoAposController(pAcaoController, pResposta);
        if (pResposta != null) {
            if (pResposta.isSucesso()) {
                atualizarEntidadeSelecionada();
            }
        }
    }

    @PostConstruct
    protected void inicioPadraoMBEntidade() {
        try {
            if (getAcaoSelecionada() == null) {
                if (acaoListarRegistros != null) {
                    setAcaoSelecionada(acaoListarRegistros);
                    executarAcaoSelecionada();
                }
            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro executando ações iniciais PostCosntruct de " + MB_paginaCadastroEntidades.class.getSimpleName() + " em" + this.getClass().getSimpleName(), t);
        }
    }

    /**
     *
     * Alterna o modo onde status do entityManager utilizado para obter uma
     * lista
     *
     *
     * @param pOpcao Falso->Mantem o entity manager aberto após listar os dados,
     * true Fecha a conexao
     */
    protected void configFecharEMAoListar(boolean pOpcao) {
        fecharEntityManagerAoListar = pOpcao;
    }

    protected void configmostarApenasORegistroCriadoAoListar(boolean pOpcao) {
        listarApenasRegistroCriadoAoListar = pOpcao;
    }

    private boolean isUmaEntidadePersistivel() {
        if (umaEntidadePersistivel == null) {
            if (getAcaoVinculada() != null) {
                Annotation anotacaoEntidade = getAcaoVinculada().getComoGestaoEntidade().getClasseRelacionada().getAnnotation(Entity.class);
                umaEntidadePersistivel = SBPersistencia.isConfigurado() && anotacaoEntidade != null;
            } else {
                umaEntidadePersistivel = false;
            }
        }
        return umaEntidadePersistivel;

    }

    private static ComoAcaoDoSistema[] converterArrayDeAcoes(List<ComoAcaoDoSistema> pParametro) {
        ComoAcaoDoSistema[] acoes = new ComoAcaoDoSistema[pParametro.size()];
        pParametro.toArray(acoes);

        return acoes;
    }

    private static ComoAcaoDoSistema[] converterAcoesSecundariasEmArrayDeAcao(List<ComoAcaoSecundaria> pParametro) {
        ComoAcaoDoSistema[] acoes = new ComoAcaoDoSistema[pParametro.size()];

        pParametro.toArray(acoes);

        return acoes;
    }

    /**
     * INCLUI NA LISTA DE ACOES DA PAGINA
     *
     * @param pAcaoDoSistema
     */
    public void adicionarAcaoDeEntidade(ComoFabricaAcoes pAcaoDoSistema) {
        adicionarAcaoDeEntidade(pAcaoDoSistema.getRegistro());
    }

    public void adicionarAcaoDeEntidade(ComoAcaoDoSistema pAcaoDoSistema) {
        try {
            if (!acoesRegistros.contains(pAcaoDoSistema)) {
                acoesRegistros.add(pAcaoDoSistema);

            } else {
                if (!SBCore.isEmModoProducao()) {
                    SBCore.getCentralDeEventos().registrarLogDeEvento(FabMensagens.ALERTA, "A Ação foi adicionada duas vezes na pagina" + this.getClass().getSimpleName() + " " + pAcaoDoSistema);
                }
            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro adicionando ação de entidade à pagina de gestão", t);
        }
    }

    public void removerAcaoDeEntidade(ComoAcaoDoSistema pAcaoDoSistema) {
        try {
            acoesRegistros.remove(pAcaoDoSistema);
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro adicionando ação de entidade à pagina de gestão", t);
        }
    }

    /**
     * Constructor simples para pagina de Entidades
     *
     * @param pAcoesRegistro Array de ações para cada registro ex.(new
     * AcaoDoSistema[]{Fabrica.acao.getAcaoDoSistema,Fabrica.acao2.getacaoDoSistema})
     * @param pAcaoNovoRegistro Ação para um novo registro
     * @param pAcaoListar Ação para listar os registros
     * @param pAcaoSalvar Ação para Salvar alterações
     * @param pTempesquisa Informa se vai haver pesquisa na tela de
     * gerenciamento
     * @param pSubChamadaDeConstructor Informa se o constructor é uma subchamada
     * de outro constructor
     *
     */
    private MB_paginaCadastroEntidades(
            ComoAcaoDoSistema[] pAcoesRegistro,
            ItfAcaoFormularioEntidade pAcaoNovoRegistro,
            ItfAcaoFormularioEntidade pAcaoListar,
            ComoAcaoControllerEntidade pAcaoSalvar,
            boolean pTempesquisa,
            boolean pSubChamadaDeConstructor
    ) {
        super();
        tipoFormulario = FabTipoFormulario.GESTAO_DE_ENTIDADE;
        acoesRegistros = new ArrayList<>();
        acaoNovoRegistro = pAcaoNovoRegistro;
        acaoListarRegistros = pAcaoListar;
        acaoSalvarAlteracoes = pAcaoSalvar;

        try {

            for (ComoAcaoDoSistema acao : pAcoesRegistro) {
                acoesRegistros.add((ComoAcaoDoSistema) acao);
            }
            if (acoesRegistros.stream().filter(ac -> ac.getTipoAcaoGenerica().equals(FabTipoAcaoSistemaGenerica.FORMULARIO_LISTAR)).findFirst().isPresent()) {
                if (acaoListarRegistros == null) {
                    throw new UnsupportedOperationException("ação Listar não foi configurada em " + this.getClass().getSimpleName());
                }
            }

            entidadesListadas = null;

            temPesquisa = pTempesquisa;

            // se for uma subchamada deixa para configurar as ações depois
            //  TODO verificar possibilidade de lançar uma exceção caso constate via
            // reflection que este constructor seja chamado direto
            if (!pSubChamadaDeConstructor) {
                configuraAcoes();
            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "ouve um erro configurando as ações basica do MB de entidade" + this.getClass().getName(), t);

        }
    }

    /**
     * Constructor simples para pagina de Entidades
     *
     * @param pAcoesRegistro Array de ações para cada registro ex.(new
     * AcaoDoSistema[]{Fabrica.acao.getAcaoDoSistema,Fabrica.acao2.getacaoDoSistema})
     * @param pAcaoNovoRegistro Ação para um novo registro
     * @param pAcaoListar Ação para listar os registros
     * @param pAcaoSalvar Ação para Salvar alterações
     * @param pTempesquisa Informa se vai haver pesquisa na tela de
     * gerenciamento
     *
     */
    public MB_paginaCadastroEntidades(
            ComoAcaoDoSistema[] pAcoesRegistro,
            ItfAcaoFormularioEntidade pAcaoNovoRegistro,
            ItfAcaoFormularioEntidade pAcaoListar,
            ComoAcaoControllerEntidade pAcaoSalvar,
            boolean pTempesquisa
    ) {
        this(pAcoesRegistro, pAcaoNovoRegistro, pAcaoListar, pAcaoSalvar, pTempesquisa, false);

    }

    public MB_paginaCadastroEntidades(ItfAcaoGerenciarEntidade pGestao,
            ComoFabricaAcoes... acoesDeRegistro
    ) {
        this(UtilFabricaDeAcoesBasico.gerarAcoesArrayComEstesEnuns(acoesDeRegistro),
                pGestao.getAcaoFormularioNovo(),
                pGestao.getAcaoFormularioListarPadrao(),
                pGestao.getAcaoSalvarMerge(),
                pGestao.isTemPesquisa(),
                pGestao.isTemVisualizar(),
                pGestao.isTemEditar(),
                pGestao.isTemNovo(),
                pGestao.isTemAlterarStatus());
        acaoEntidadeEditar = pGestao.getAcaoFormularioEditar();

    }

    public MB_paginaCadastroEntidades(ItfAcaoGerenciarEntidade pGestao) {

        this(converterAcoesSecundariasEmArrayDeAcao(pGestao.getAcoesVinculadasAObjetoExistenteSemRepetirTipo()),
                pGestao.getAcaoFormularioNovo(),
                pGestao.getAcaoFormularioListarPadrao(),
                pGestao.getAcaoSalvarMerge(),
                pGestao.isTemPesquisa(),
                pGestao.isTemVisualizar(),
                pGestao.isTemEditar(),
                pGestao.isTemNovo(),
                pGestao.isTemAlterarStatus());
        acaoEntidadeEditar = pGestao.getAcaoFormularioEditar();

    }

    public MB_paginaCadastroEntidades() {
        this(UtillSBWPReflexoesWebpaginas.getAcaoDeGestaoPorClasseDoMetodoChamado());

    }

    public MB_paginaCadastroEntidades(List pAcoesRegistro,
            AcaoDoSistema pAcaoNovoRegistro,
            AcaoDoSistema pAcaoListar,
            AcaoDoSistema pAcaoSalvar,
            boolean pTempesquisa) {
        this((ComoAcaoDoSistema[]) pAcoesRegistro.toArray(new AcaoDoSistema[pAcoesRegistro.size()]), pAcaoNovoRegistro.getComoFormularioEntidade(),
                pAcaoListar.getComoFormularioEntidade(), pAcaoSalvar.getComoControllerEntidade(), pTempesquisa);
    }

    /**
     *
     * @param pAcoesRegistro Array de ações para cada registro ex.(new
     * AcaoDoSistema[]{Fabrica.acao.getAcaoDoSistema,Fabrica.acao2.getacaoDoSistema})
     * @param pAcaoNovoRegistro Ação para um novo registro
     * @param pAcaoListar Ação para listar os registros
     * @param pAcaoSalvar Ação para Salvar alterações
     * @param pTempesquisa Informa se vai haver pesquisa na tela de
     * @param pTemVisualizar
     * @param pTemEditar Informa se o formulario tem opção de edição
     * @param pTemNovo Informa se o formulario tem opção de novo registro
     * @param pTemAlterarStatus Informa se o formulario tem opções de Alterar
     * Status
     */
    public MB_paginaCadastroEntidades(ComoAcaoDoSistema[] pAcoesRegistro,
            ItfAcaoFormularioEntidade pAcaoNovoRegistro,
            ItfAcaoFormularioEntidade pAcaoListar,
            ComoAcaoControllerEntidade pAcaoSalvar,
            boolean pTempesquisa,
            boolean pTemVisualizar,
            boolean pTemEditar,
            boolean pTemNovo,
            boolean pTemAlterarStatus) {

        this(pAcoesRegistro, pAcaoNovoRegistro, pAcaoListar, pAcaoSalvar, pTempesquisa, true);

        temVisualizar = pTemVisualizar;
        temNovo = pTemNovo;
        temEditar = pTemEditar;

        temAlterarStatus = pTemAlterarStatus;

        configuraAcoes();
    }

    @Override
    public void executarAcao(T pEntidadeSelecionada) {
        getGestaoDeDominio().executarAcao(pEntidadeSelecionada);
    }

    @Override
    protected void aplicaValoresURLEmParametros(Map<String, String> valorStringPorParametro) {
        super.aplicaValoresURLEmParametros(valorStringPorParametro); //To change body of generated methods, choose Tools | Templates.
        try {
            for (String pr : getMapaParametros().keySet()) {

                ParametroUrlInstanciado parametro = getMapaParametros().get(pr);
                if (parametro.isValorDoParametroFoiConfigurado()) {
                    if (parametro.isUmParametoEntidadeMBPrincipal()) {
                        if (parametro.getValor() == null) {
                            continue;
                        }
                        if (parametro.isUmParametroDeEntidade()) {

                            T entidadeAtualizada = UtilSBPersistencia.loadEntidade((ComoEntidadeSimples) parametro.getValor(), getEMPagina());
                            if (entidadeAtualizada == null) {
                                try {
                                    setEntidadeSelecionada((T) parametro.getValor());
                                } catch (Throwable t) {
                                    throw new UnsupportedOperationException("O parametro não parece ser compatível com o tipo de entidade da pagina valor=" + parametro.getValor());
                                }
                                throw new UnsupportedOperationException("Erro buscando Bean Atualizado em banco de dados");
                            }
                            setEntidadeSelecionada(entidadeAtualizada);
                        } else {
                            try {
                                setEntidadeSelecionada((T) parametro.getValor());
                            } catch (Throwable t) {
                                throw new UnsupportedOperationException("O parametro não parece ser compatível com o tipo de entidade da pagina valor=" + parametro.getValor());
                            }
                        }
                    }
                }
            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro aplicando paramentros em pagina de Entidade", t);
        }
    }

    public ItfRespostaAcaoDoSistema execucaoAcaoControllerPadrao(ComoAcaoController pAcaoController) {
        return execucaoAcaoControllerPadrao(pAcaoController, true);
    }

    @Override
    public void executarAcaoSelecionada() {
        executarAcao(getEntidadeSelecionada());
    }

    protected void setPodeEditar(boolean pParametro) {
        podeEditar = pParametro;
    }

    protected void setTemNovo(boolean pParametro) {
        temNovo = pParametro;
    }

    @Override
    public boolean isTemEditar() {
        return temEditar;
    }

    @Override
    public boolean isTemNovo() {
        return temNovo;
    }

    @Override
    public boolean isTemVisualizar() {
        return temVisualizar;
    }

    @Override
    public boolean isTemAlterarStatus() {
        return temAlterarStatus;
    }

    @Override
    public List<ComoAcaoDoSistema> getAcoesRegistros() {

        return acoesRegistros;
    }

    // Retorna ação de novo registro
    @Override
    public ItfAcaoFormularioEntidade getAcaoNovoRegistro() {
        return acaoNovoRegistro;
    }

    @Override
    public ItfAcaoFormularioEntidade getAcaoListarRegistros() {

        if (getAcaoUltimaDesteTipo(FabTipoAcaoSistemaGenerica.FORMULARIO_LISTAR) != null) {
            acaoListarRegistros = getAcaoUltimaDesteTipo(FabTipoAcaoSistemaGenerica.FORMULARIO_LISTAR).getComoFormularioEntidade();
        } else {
            if (acaoListarRegistros == null) {
                acaoListarRegistros = getAcaoVinculada().getAcaoFormularioListarPadrao();
            }

        }

        return acaoListarRegistros;
    }

    @Override
    public boolean isPodeEditar() {
        return podeEditar;
    }

    @Override
    public boolean isNovoRegistro() {
        return novoRegistro;
    }

    @Override
    public ComoAcaoDoSistema getAcaoSelecionada() {
        return (ComoAcaoDoSistema) acaoSelecionada;
    }

    // Define a ação selecionada
    @Override
    public void setAcaoSelecionada(ComoAcaoDoSistema acaoSelecionada) {

        this.acaoSelecionada = acaoSelecionada;
        if (acaoSelecionada != null && acaoSelecionada.isUmaAcaoFormulario()) {
            if (!acaoSelecionada.getComoFormulario().getEstadoFormulario().equals(FabEstadoFormulario.INDEFINIDO)) {
                atualizaInformacoesDeEdicao(acaoSelecionada.getComoFormulario().getEstadoFormulario());
            }

        }
    }

    @Override
    public String getXhtmlAcaoAtual() {
        return xhtmlAcaoAtual;
    }

    @Override
    public ComoAcaoDoSistema getAcaoSalvarAlteracoes() {
        try {
            if (acaoSalvarAlteracoes != null) {
                if (!acaoSalvarAlteracoes.getComoControllerEntidade().getClasseRelacionada().getSimpleName().equals(getEntidadeSelecionada().getClass().getSimpleName())) {
                    List acoesDaEntidade = getAcaoVinculada().getAcoesVinculadasDesteTipoComEstaEntidade(FabTipoAcaoSistemaGenerica.CONTROLLER_SALVAR_MODO_MERGE, getEntidadeSelecionada().getClass());
                    if (!acoesDaEntidade.isEmpty()) {
                        return (ComoAcaoDoSistema) acoesDaEntidade.get(0);
                    }
                }
            }
        } catch (Throwable t) {

        }

        return (ComoAcaoDoSistema) acaoSalvarAlteracoes;
    }

    @Override
    public T getEntidadeSelecionada() {
        try {
            //  if (entidadeSelecionada != null && entidadeSelecionada.getId() != null && entidadeSelecionada.getId() > 0) {

            //    entidadeSelecionada = UtilSBPersistencia.loadEntidade(entidadeSelecionada, getEMPagina());
            // }
            return entidadeSelecionada;
        } catch (Throwable t) {

            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro ao selecionar entidade", t);
            return null;
        }
    }

    @Override
    public void setEntidadeSelecionada(T entidadeSelecionada) {
        this.entidadeSelecionada = entidadeSelecionada;

    }

    @Override
    public List<T> getEntidadesListadas() {

        try {
            if (entidadesListadas == null) {
                listarDados(!listarApenasRegistrosAtivos);
                //proteção de  stackoverflow, chamando getEntidadesListadas dentro do listar(dados)
                if (entidadesListadas == null) {
                    entidadesListadas = new ArrayList<>();
                }
                return entidadesListadas;
            }
            if (entidadesListadas != null && entidadesListadas.isEmpty()) {
                entidadesListadas = null;
                return entidadesListadas;
            }

        } catch (Throwable t) {

            return new ArrayList<>();
        }

        return entidadesListadas;

    }

    @Override
    public void setEntidadesListadas(List<T> entidadesListadas) {
        this.entidadesListadas = entidadesListadas;

    }

    @Override
    public boolean isTemPesquisa() {
        return temPesquisa;
    }

    private void configuraAcoes() {
        try {

            for (ComoAcaoDoSistema acao : acoesRegistros) {

                if (acao.isUmaAcaoGenerica()) {

                    if (acao.getTipoAcaoGenerica().equals(FabTipoAcaoSistemaGenerica.FORMULARIO_EDITAR)) {

                        if (!temEditar) {
                            throw new UnsupportedOperationException("A opção TemEditar está false, mas uma ação do tipo formulário editar foi encontrada entre as ações dos registros de entidade, a ação é " + acao.getNomeAcao());
                        } else {
                            acaoEntidadeEditar = (ItfAcaoFormularioEntidade) acao;
                        }

                    }
                    if (acao.getTipoAcaoGenerica().equals(FabTipoAcaoSistemaGenerica.CONTROLLER_ATIVAR_DESATIVAR)) {

                        if (!temAlterarStatus) {
                            throw new UnsupportedOperationException("A opção TemAlterarStatus está false, mas uma ação do tipo controller Ativa_desativar foi encontrada entre as ações dos registros de entidade, verifique o constructor, ou a  config da ação" + acao.getNomeAcao());
                        } else {
                            acaoEntidadeAlterarStatus = (ComoAcaoControllerEntidade) acao;
                        }

                    }
                    if (acao.getTipoAcaoGenerica().equals(FabTipoAcaoSistemaGenerica.FORMULARIO_VISUALIZAR)) {

                        if (!temVisualizar) {
                            throw new UnsupportedOperationException("A opção TemVisualizar está false, mas uma ação do tipo formulário visualisar foi encontrada entre as ações dos registros de entidade, verifique o constructor ou a config da ação " + acao.getNomeAcao());
                        } else {
                            acaoEntidadeVisualizar = (ItfAcaoFormularioEntidade) acao;
                        }

                    }

                }

            }

            if (temEditar & acaoEntidadeEditar == null) {
                System.out.println("TODO corrigir constructor não recebendo ação de edição");
            }

            if (temVisualizar & acaoEntidadeVisualizar == null) {
                System.out.println("TODO corrigir constructor não recebendo ação de visualizacao");
            }

            if (temAlterarStatus & acaoEntidadeAlterarStatus == null) {
                throw new UnsupportedOperationException("A prpriedade temAlterar status é true mas a ação deste tipo não foi encontrada, certifique que exita uma ação do tipo " + FabTipoAcaoSistemaGenerica.CONTROLLER_ATIVAR_DESATIVAR + " nas ações de registro configuradas no constructor da pagina");
            }

            if (temNovo & acaoNovoRegistro == null) {
                throw new UnsupportedOperationException("a prpriedade tem novo é true, mas a ação deste tipo não foi encontrada, certifique que exita uma ação do tipo " + FabTipoAcaoSistemaGenerica.FORMULARIO_NOVO_REGISTRO + " nas ações de registro configuradas no constructor da pagina");
            }

        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Ouve um erro configurando as ações da pagina de gestão de entidade: " + this.getClass().toString(), t);
        }
    }

    @Override
    public ItfAcaoFormularioEntidade getAcaoEditar() {
        return acaoEntidadeEditar;

    }

    @Override
    public ComoAcaoControllerEntidade getAcaoAlterarStatus() {
        return acaoEntidadeAlterarStatus;
    }

    @Override
    public ItfAcaoFormularioEntidade getAcaoVisualizar() {
        return acaoEntidadeVisualizar;
    }

    @Override
    public boolean isSomenteLeitura() {
        return !podeEditar;
    }

    @Override
    protected void renovarEMPagina() {

        if (isUmaEntidadePersistivel()) {
            super.renovarEMPagina(); //To change body of generated methods, choose Tools | Templates.
            limparLista();
        }
    }

    protected void limparLista() {

        setEntidadesListadas(null);

    }

    protected ComoEntidadeSimples getEntidadeSelecionadaComoBeanSimples() {
        return (ComoEntidadeSimples) getEntidadeSelecionada();
    }

    @Override
    public ComoEntidadeSimples getBeanSelecionado() {

        return getEntidadeSelecionadaComoBeanSimples();
    }

    @Override
    public void setBeanSelecionado(ComoEntidadeSimples pBeanSimples) {
        try {
            setEntidadeSelecionada((T) pBeanSimples);
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro atribuindo valor de entidade selecionada com " + pBeanSimples + " em " + this.getClass().getSimpleName(), t);
        }
    }

    @Override
    public void listarDados() {
        listarDados(false);

    }

    protected void listarDados(boolean mostrarInativos) {
        try {

            if (listarApenasRegistroCriadoAoListar && isNovoRegistro() && getEntidadeSelecionada() != null && getEntidadeSelecionadaComoBeanSimples().getId() == null) {
                if (getEntidadesListadas() != null) {
                    getEntidadesListadas().clear();
                    getEntidadesListadas().add(entidadeSelecionada);
                }
            } else {
                try {
                    ComoEntidadeSimples bean = null;
                    Class calssePesquisa = null;
                    if (getAcaoSelecionada().isUmaAcaoDeEntidade()) {
                        calssePesquisa = getAcaoSelecionada().getComoAcaoDeEntidade().getClasseRelacionada();
                    } else {
                        calssePesquisa = getAcaoSelecionada().getAcaoDeGestaoEntidade().getClasseRelacionada();
                    }
                    if (getAcaoSelecionada().isUmaAcaoFormulario() && getAcaoSelecionada().isUmaAcaoDeEntidade()) {
                        bean = (ComoEntidadeSimples) getAcaoSelecionada().getComoFormularioEntidade().getClasseRelacionada().newInstance();
                    } else {
                        bean = (ComoEntidadeSimples) getAcaoVinculada().getClasseRelacionada().newInstance();
                    }
                    ConsultaDinamicaDeEntidade novaConsulta = new ConsultaDinamicaDeEntidade(calssePesquisa, getEMPagina());
                    if (getParametrosURL().size() > 1) {

                        EstruturaDeEntidade estruturaEntidadeListada = MapaObjetosProjetoAtual.getEstruturaObjeto(calssePesquisa);

                        for (ItfParametroRequisicaoInstanciado pr : getParametrosURL()) {

                            if (pr.isUmParametroDeEntidade() && pr.isValorDoParametroFoiConfigurado()) {

                                Optional<ItfEstruturaCampoEntidade> pesquisaCampoTipoParametro = estruturaEntidadeListada.getCampos()
                                        .stream().filter(cp -> cp.getTipoPrimitivoDoValor().equals(TIPO_PRIMITIVO.ENTIDADE)
                                        && MapaObjetosProjetoAtual.isNomeEntidadeRegistrada(cp.getClasseCampoDeclaradoOuTipoLista())
                                        && cp.getClasseCampoDeclaradoOuTipoLista().equals(pr.getClasseObjetoValor().getSimpleName()))
                                        .findFirst();
                                if (pesquisaCampoTipoParametro.isPresent()) {
                                    ItfEstruturaCampoEntidade campoMesmoTipoParametro = pesquisaCampoTipoParametro.get();
                                    novaConsulta.addCondicaoManyToOneIgualA(campoMesmoTipoParametro.getNomeDeclarado(), (ComoEntidadeSimples) pr.getValor());
                                }

                            }
                        }

                    }
                    if (!mostrarInativos && bean.isTemCampoAnotado(FabTipoAtributoObjeto.REG_ATIVO_INATIVO)) {
                        novaConsulta.addCondicaoPositivo(bean.getNomeCampo(FabTipoAtributoObjeto.REG_ATIVO_INATIVO));

                    }
                    setEntidadesListadas(novaConsulta.resultadoRegistros());
                    UtilCRCListasObjeto.ordernarPorCampoReverso(getEntidadesListadas(), "id");

                } catch (Throwable t) {
                    setEntidadesListadas(UtilSBPersistencia.getListaTodos(getAcaoVinculada().getClasseRelacionada(), getEMPagina()));
                }

            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro ao processar lista padrão em" + this.getClass().getSimpleName(), t);
        }
    }

    @Override
    public void metodoRespostaModalPrimefaces(SelectEvent event) {
        //TODO melhorar, validar se é uma resposta de confirmação
        if (!mapaRespostasComunicacaoTransienteDeAcaoByAcoes.isEmpty()) {
            executarAcao(entidadeSelecionada);
            mapaRespostasComunicacaoTransienteDeAcaoByAcoes.clear();
            mapaComunicacaoTransienteDeAcaoByIdModal.clear();
        } else {
            super.metodoRespostaModalPrimefaces(event);
        }
    }

    @Override
    public void atualizarEntidadeSelecionada() {
        try {
            if (getEntidadeSelecionada() != null && isUmaEntidadePersistivel()) {
                ComoEntidadeSimplesSomenteLeitura entidadeComoBeanSimples = (ComoEntidadeSimplesSomenteLeitura) getEntidadeSelecionada();

                renovarEMPagina();

                if (entidadeComoBeanSimples.getId() != 0) {
                    setEntidadeSelecionada(UtilSBPersistencia.loadEntidade(entidadeComoBeanSimples, getEMPagina()));
                }
            }
        } catch (ClassCastException t) {

        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro atualizando Entidade", t);

        }

    }

    private ItfPaginaGerenciarEntidade getPaginaDoDominio() {
        return this;
    }

    /**
     * Substitui a autoExecução para criação de nova entidade
     *
     *
     */
    protected void autoexecEntidadeNova() {
        try {
            if (getAcaoSelecionada() != null) {
                if (!getComoFormularioWeb().getAcaoVinculada().equals(getAcaoSelecionada().getAcaoDeGestaoEntidade())) {

                    return;
                }
            }

            Class classeDaEntidade = null;
            if (getAcaoSelecionada() instanceof ItfAcaoEntidade) {
                ItfAcaoEntidade acao = (ItfAcaoEntidade) getAcaoSelecionada();
                classeDaEntidade = acao.getClasseRelacionada();

            } else {
                classeDaEntidade = getAcaoVinculada().getClasseRelacionada();
            }
            getPaginaDoDominio().setEntidadeSelecionada((T) classeDaEntidade.newInstance());

            InfoPreparacaoObjeto infoPreparacao = UtilCRCReflexaoObjeto.getInfoPreparacaoObjeto(classeDaEntidade);
            if (infoPreparacao != null) {
                if (infoPreparacao.classesPrConstructorPrincipal().length == 0) {
                    ((ComoDominioEntidadeGenerico) getEntidadeSelecionada()).prepararNovoObjeto();
                } else {
                    if (infoPreparacao.classesPrConstructorPrincipal().length == 1) {
                        Class classeParametro = infoPreparacao.classesPrConstructorPrincipal()[0];
                        if (isTemParametrobyTipoEntidade(classeParametro.getSimpleName())) {
                            ItfParametroRequisicaoInstanciado pr = getParametrobyTipoEntidade(classeParametro.getSimpleName());

                            if (pr.getValor() == null) {
                                if (pr.isParametroObrigatorio()) {
                                    throw new UnsupportedOperationException("Impossível encontrar valor de  parametro de url obrigatorio do tipo " + classeParametro.getSimpleName() + " para preparar objeto do tipo " + classeDaEntidade.getSimpleName());
                                }
                            } else {
                                getEntidadeSelecionada().prepararNovoObjeto(UtilSBPersistencia.loadEntidade((ComoEntidadeSimplesSomenteLeitura) pr.getValor(), getEMPagina()));
                            }
                        } else {

                            throw new UnsupportedOperationException("Impossível encontrar parametros do tipo " + classeParametro.getSimpleName() + " para preparar objeto do tipo " + classeDaEntidade.getSimpleName());
                        }
                    } else {
                        // todo Erro buscar em beans declarados
                        throw new UnsupportedOperationException("Impossível encontrar parametros para preparar objeto");
                    }
                }
            } else {
                getEntidadeSelecionada().prepararNovoObjeto();
            }
        } catch (ErroPreparandoObjeto erro) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Falha instanciando objeto automaticamente, ao preparar o objeto" + erro.getMessage(), erro);
        } catch (InstantiationException | IllegalAccessException ex) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Falha instanciando objeto automaticamente, tavez você precise substrituri o método autoExecNovaEntidade" + ex.getMessage(), ex);
        }

    }

    protected void autoExecEntidadeLoad(T pEnTidade) {

        if (getAcaoSelecionada().isUmaAcaoFormulario()) {
            switch (getAcaoSelecionada().getComoFormulario().getEstadoFormulario()) {
                case NOVO:
                    if (getEntidadeSelecionada() == null || getEntidadeSelecionada().getId() != 0) {
                        if (getComoFormularioWeb().getAcaoVinculada().equals(getAcaoSelecionada().getAcaoDeGestaoEntidade())) {
                            autoexecEntidadeNova();
                            break;
                        }
                    }
                    break;
                case EDITAR:
                case VISUALIZAR:
                case INDEFINIDO:
                    boolean mesmoentreEntidadeEAcaoAtual = true;
                    if (getAcaoSelecionada().isUmaAcaoFormulario()) {
                        if (getAcaoSelecionada() instanceof ItfAcaoEntidade) {
                            if (pEnTidade != null) {
                                if (getAcaoSelecionada().getComoAcaoDeEntidade()
                                        .getClasseRelacionada().getSimpleName().
                                        equals(pEnTidade.getClass().getSimpleName())) {
                                    mesmoentreEntidadeEAcaoAtual = false;
                                }
                            }
                        }
                    }
                    // Atualizando EntityManager da Entidade Selecionada
                    if (pEnTidade != null) {

                        /// caso não se trate de um novo registro
                        if (((ComoEntidadeSimplesSomenteLeitura) pEnTidade).getId() != null) {
                            // Caso ESteja mudando de registro selecionado
                            if (getEntidadeSelecionada() == null
                                    || ((ComoEntidadeSimplesSomenteLeitura) getEntidadeSelecionada()).getId() != ((ComoEntidadeSimplesSomenteLeitura) pEnTidade).getId()) {
                                //Atualiza o REgsitro Selecionado
                                if (isUmaEntidadePersistivel()) {
                                    renovarEMPagina();
                                    if (!getAcaoSelecionada().isUmaAcaoController()) {
                                        if (mesmoentreEntidadeEAcaoAtual) {
                                            getPaginaDoDominio().setEntidadeSelecionada((T) UtilSBPersistencia.loadEntidade((ComoEntidadeSimples) pEnTidade, getEMPagina()));
                                        } else {
                                            String strClasseEntidade = UtilCRCReflexaoObjeto.getClasseDiscriminatoriaPolimorfismoDeEntidade(pEnTidade);
                                            if (strClasseEntidade != null) {
                                                try {
                                                    pEnTidade = (T) UtilSBPersistencia.getRegistroByID(MapaObjetosProjetoAtual.getClasseDoObjetoByNome(strClasseEntidade), pEnTidade.getId(), getEMPagina());
                                                    setEntidadeSelecionada(pEnTidade);
                                                } catch (Throwable t) {
                                                    getPaginaDoDominio().setEntidadeSelecionada((T) UtilSBPersistencia.loadEntidade((ComoEntidadeSimples) pEnTidade, getEMPagina()));
                                                }
                                            } else {
                                                getPaginaDoDominio().setEntidadeSelecionada((T) UtilSBPersistencia.loadEntidade((ComoEntidadeSimples) pEnTidade, getEMPagina()));
                                            }
                                        }

                                    } else {
                                        if (getEntidadeSelecionada() == null || !getEntidadeSelecionada().equals(pEnTidade)) {

                                            getPaginaDoDominio().setEntidadeSelecionada((T) UtilSBPersistencia.loadEntidade((ComoEntidadeSimples) pEnTidade, getEMPagina()));
                                        } else {
                                            getPaginaDoDominio().setEntidadeSelecionada(pEnTidade);
                                        }
                                    }

                                } else {
                                    getPaginaDoDominio().setEntidadeSelecionada(pEnTidade);
                                }

                            }// se a entidadeSelecionada anteriormente for nula, ou se a nova entidade for diferente dela

                        } else {
                            //se a nova entidade  a ser selecionada(pEntidade) não for um novo registro (com id 0)
                            setEntidadeSelecionada(pEnTidade);
                        }
                    }// se a nova entidade  a ser selecionada(pEntidade) for diferente de nulo
                    break;
                default:
                    getPaginaDoDominio().setEntidadeSelecionada(pEnTidade);
            }
        } else {
            getPaginaDoDominio().setEntidadeSelecionada(pEnTidade);
        }

    }

    /**
     *
     * @return True quando novoRegistro=false e podeEditar=true
     */
    public boolean isEmEstadoEdicao() {
        return (!novoRegistro && podeEditar);
    }

    /**
     *
     * @return True quando novoRegistro=true;
     */
    public boolean isEmEstadoNovo() {
        return (novoRegistro);
    }

    /**
     *
     * @return True quando podeEditar=false
     */
    public boolean isEmEstadoVisualizacao() {
        return (!novoRegistro && !podeEditar);
    }

    protected void autoExecRotasAlternativasExecucaoDeAcaoAtual(T pEntidade) {
        if (pEntidade == null) {
            return;
        }
        Class classeEntidadeSelecionada = pEntidade.getClass();
        if (getAcaoSelecionada().isUmaAcaoDeEntidade()
                && !getAcaoSelecionada().getComoAcaoDeEntidade().getClasseRelacionada().equals(classeEntidadeSelecionada)) {
            Class entidadeDaAcao = getAcaoSelecionada().getComoAcaoDeEntidade().getClasseRelacionada();
            if (entidadeDaAcao.isInstance(pEntidade)) {
                return;
            }

            ComoAcaoDoSistema acoAlternativa = getAcaoVinculada().getAcaoCompativelEntidadeDivergente(acaoSelecionada, classeEntidadeSelecionada);
            if (acoAlternativa != null) {
                acaoSelecionada = acoAlternativa;
            }
        }

    }

    @Override
    protected void autoExecAlterarFormulario(final ItfAcaoFormulario pAcao, boolean pAlterouEntidadeSelecionada) {

        super.autoExecAlterarFormulario(pAcao, pAlterouEntidadeSelecionada);
        try {
            FabEstadoFormulario statusModoForm = pAcao.getComoFormulario().getEstadoFormulario();

            if (statusModoForm == FabEstadoFormulario.INDEFINIDO) {
                statusModoForm = pAcao.getTipoAcaoGenerica().getEstadoFormularioPadrao();
            }

            atualizaInformacoesDeEdicao(statusModoForm);
            final FabEstadoFormulario statusCampo = statusModoForm;
            if (statusModoForm.equals(FabEstadoFormulario.VISUALIZAR)) {
                if (getAcaoSelecionada().isUmaAcaoFormulario()) {
                    getAcaoSelecionada().getComoFormulario().getCampos().forEach(cp -> {
                        getEntidadeSelecionada().getCampoInstanciadoByNomeOuAnotacao(cp.getCaminhoSemNomeClasse());
                        getEntidadeSelecionada().getCampoInstanciadoByNomeOuAnotacao(cp.getCaminhoSemNomeClasse()).setStatusFormularioExibicao(statusCampo);
                    });
                }
            }

        } catch (NullPointerException n) {
            //ignora caso ação ou entidade nulos
        }

    }

    /**
     * Configura as propriedades pode editar, e novo registro, de acordo com a
     * opção selecionada
     *
     * @param pEstadoEdicao
     */
    public void atualizaInformacoesDeEdicao(FabEstadoFormulario pEstadoEdicao) {

        switch (pEstadoEdicao) {
            case EDITAR:
                novoRegistro = false;
                podeEditar = true;
                break;
            case NOVO:
                novoRegistro = true;
                podeEditar = true;
                break;
            case VISUALIZAR:

                novoRegistro = false;
                podeEditar = false;
                break;
            default:
                throw new AssertionError(pEstadoEdicao.name());

        }

    }

    protected void autoExecMBAlternativoExterno() {

        try {
            EstruturaDeFormulario estruturaForm = MapaDeFormularios.getEstruturaByXHTMLDeGestao(acaoSelecionada.getAcaoDeGestaoEntidade().getXhtml());
            List<ItfParametroRequisicao> prs = estruturaForm.getParametrosURL();

            if (prs.isEmpty()) {
                UtilSBWP_JSFTools.vaParaPagina(MapaDeFormularios.getUrlFormulario(acaoSelecionada));
            } else {
                long quantidadeParametrosObrigatorios = prs.stream().filter(pr -> pr.isParametroObrigatorio()).count();
                if (quantidadeParametrosObrigatorios > 1) {
                    throw new UnsupportedOperationException("A página " + estruturaForm.getAcaoGestaoVinculada().getNomeUnico() + " contem mais de um parametro obrigatório , extenda autoExecMBAlternativoExterno() na classe " + this.getClass().getSimpleName() + " e configure esta ação manualmente");
                }

                if (getEntidadeSelecionada() != null) {
                    String classeEntidadeSel = getEntidadeSelecionada().getClass().getSimpleName();
                    Optional<ItfParametroRequisicao> pesquisaPrDesteTipo = prs.stream().filter(pr -> (pr.isUmParametroDeEntidade() && pr.getClasseObjetoValor().getSimpleName().equals(classeEntidadeSel))).findFirst();
                    if (pesquisaPrDesteTipo.isPresent()) {
                        String urlComEntidade = MapaDeFormularios.getUrlFormulario(acaoSelecionada, getEntidadeSelecionada());
                        UtilSBWP_JSFTools.vaParaPagina(urlComEntidade);
                    } else if (prs.get(0).getTipoEntidade().isInstance(getEntidadeSelecionada())) {
                        String urlComEntidade = MapaDeFormularios.getUrlFormulario(acaoSelecionada, getEntidadeSelecionada());
                        UtilSBWP_JSFTools.vaParaPagina(urlComEntidade);
                    } else {
                        EstruturaDeEntidade est = MapaObjetosProjetoAtual.getEstruturaObjeto(classeEntidadeSel);

                        Optional<ItfLigacaoMuitosParaUm> pesquisaLigacao
                                = est.getMuitosParaUm().stream().filter(
                                        ligacao -> prs.stream().filter(
                                                prPg -> prPg.getTipoEntidade().getSimpleName().equals(ligacao.getNomeEntidade())).findFirst().isPresent())
                                        .findFirst();
                        if (pesquisaLigacao.isPresent()) {
                            UtilSBWP_JSFTools.vaParaPagina(MapaDeFormularios.getUrlFormulario(acaoSelecionada, getEntidadeSelecionada().getCampoInstanciadoByNomeOuAnotacao(pesquisaLigacao.get().getNomeDeclarado()).getValor()));
                        } else {

                            Optional<ItfParametroRequisicaoInstanciado> pesquisaParametroCompativel = getParametrosURL().stream().filter(pr
                                    -> prs.stream().filter(prPaginaExterna -> (prPaginaExterna.isUmParametroDeEntidade() && prPaginaExterna.getTipoEntidade().equals(pr.getTipoEntidade()))).findFirst().isPresent()
                            ).findFirst();
                            if (pesquisaParametroCompativel.isPresent()) {
                                UtilSBWP_JSFTools.vaParaPagina(MapaDeFormularios.getUrlFormulario(acaoSelecionada, pesquisaParametroCompativel.get()));
                            } else {
                                if (quantidadeParametrosObrigatorios == 0) {
                                    String url = MapaDeFormularios.getUrlFormulario(acaoSelecionada);
                                    UtilSBWP_JSFTools.vaParaPagina(url);
                                } else {
                                    throw new UnsupportedOperationException("Impossível determinar uma entidade ou parametro compatível nesta pagina para abrir " + estruturaForm.getAcaoGestaoVinculada().getNomeUnico() + " , extenda autoExecMBAlternativoExterno() na classe " + this.getClass().getSimpleName() + " e configure esta ação manualmente");
                                }
                            }
                        }
                    }
                } else {
                    if (quantidadeParametrosObrigatorios == 0) {
                        String url = MapaDeFormularios.getUrlFormulario(acaoSelecionada);
                        UtilSBWP_JSFTools.vaParaPagina(url);
                    } else {
                        throw new UnsupportedOperationException("Impossível determinar uma entidade ou parametro obrigatório compatível nesta pagina para abrir " + estruturaForm.getAcaoGestaoVinculada().getNomeUnico() + " , extenda autoExecMBAlternativoExterno() na classe " + this.getClass().getSimpleName() + " e configure esta ação manualmente");
                    }
                }
            }

        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro determinando url para execução de ação em outro formulário", t);

        }
    }

    private class GestaoDeDominioEmPagina<TT> implements Serializable {

        private void logExecucao(TT pEntidadeSelecionada) {
            if (SBCore.getEstadoAPP() != SBCore.ESTADO_APP.PRODUCAO) {
                System.out.println("Executando Acao" + acaoSelecionada.getNomeAcao() + "Do tipo" + acaoSelecionada.getTipoAcaoSistema() + " Generico" + acaoSelecionada.getTipoAcaoGenerica());
            }

            if (SBCore.getEstadoAPP() != SBCore.ESTADO_APP.PRODUCAO) {
                System.out.println("O xhtml da ação foi alterado para o XTML:" + xhtmlAcaoAtual + " da açao " + acaoSelecionada.getNomeAcao());

            }

            if ((acaoSelecionada.equals(acaoEntidadeEditar)
                    || acaoSelecionada.equals(acaoEntidadeAlterarStatus)
                    || acaoSelecionada.equals(acaoEntidadeVisualizar))
                    & (pEntidadeSelecionada == null)) {

                if (!SBCore.isEmModoProducao()) {
                    System.out.println("Entidade não selecionada para" + acaoSelecionada.getNomeAcao());
                }

            }
        }

        private void executarAcaoMBAlternativoExteno() {
            autoExecMBAlternativoExterno();

        }

        private void defineEntidadeSelecionada(TT pEntidadeSelecionada) {
            if (!getAcaoSelecionada().getAcaoDeGestaoEntidade().equals(getAcaoVinculada())) {
                setEntidadeSelecionada((T) pEntidadeSelecionada);
                return;
            }
            autoExecEntidadeLoad((T) pEntidadeSelecionada);

        }

        public void executarAcao(TT pEntidadeSelecionada) {
            boolean alterouEntidade = false;
            if (pEntidadeSelecionada != null) {
                if (getBeanSelecionado() == null) {
                    alterouEntidade = true;
                } else {
                    alterouEntidade = !pEntidadeSelecionada.equals(getBeanSelecionado());
                }
            } else {
                if (getBeanSelecionado() != null) {
                    alterouEntidade = true;
                }
            }
            if (acaoSelecionada == null) {

                try {

                    UtilSBWP_JSFTools.mensagens().enviaMensagem(FabMensagens.ALERTA.getMsgUsuario("Nenhuma  ação foi selecionada"));
                    throw new UnsupportedOperationException("A ação selecionada estava nula ao executar ação na pagina" + this.getClass().getSimpleName());

                } catch (Throwable t) {

                    SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Uma ação nula foi selecionada em executar ação", t);

                }

                return;
            }

            defineEntidadeSelecionada(pEntidadeSelecionada);
            if (!getAcaoSelecionada().getAcaoDeGestaoEntidade().equals(getAcaoVinculada())) {
                executarAcaoMBAlternativoExteno();
                return;
            } else {
                autoExecRotasAlternativasExecucaoDeAcaoAtual((T) pEntidadeSelecionada);
            }

            if (!SBCore.isEmModoProducao()) {
                logExecucao(pEntidadeSelecionada);
            }

            adicionarAcaoNoHistorico(acaoSelecionada);

            switch (acaoSelecionada.getTipoAcaoSistema()) {
                case ACAO_DO_SISTEMA:

                    break;
                case ACAO_ENTIDADE_FORMULARIO:
                case ACAO_FORMULARIO:

                    autoExecAlterarFormulario(acaoSelecionada.getComoFormulario(), alterouEntidade);
                    if (autoExecNovaRotaUrlAcaoSelecionada()) {
                        return;
                    }
                    break;
                case ACAO_ENTIDADE_FORMULARIO_MODAL:
                    if (!isPermitidoAbrirFormulario()) {
                        return;
                    }
                    break;
                case ACAO_ENTIDADE_GERENCIAR:

                    break;
                case ACAO_ENTIDADE_CONTROLLER:
                case ACAO_CONTROLLER:
                    try {
                        if (acaoSelecionada.getComoController().isTemComunicacaoTransiente()) {
                            if (!isRespostaComunicacaoTransientPreAcaoEnviada()) {
                                exibeModalComunicacaoTransientPreAcaoAtual();
                            } else {
                                FabTipoRespostaComunicacao respComunicacao = mapaRespostasComunicacaoTransienteDeAcaoByAcoes.get(getAcaoSelecionada().getNomeUnico());
                                if (respComunicacao != null) {

                                    if (respComunicacao.isRespostaPositiva()) {
                                        ItfRespostaAcaoDoSistema respAcao = autoExecAcaoController((T) pEntidadeSelecionada);
                                        respAcao.dispararMensagens();
                                        ultimaRespostaControllerRecebida = respAcao;
                                        autoExecProximaAcaoAposController((ComoAcaoController) acaoSelecionada, respAcao);
                                    } else {
                                        ultimaRespostaControllerRecebida = null;

                                        autoExecProximaAcaoAposController((ComoAcaoController) acaoSelecionada, null);
                                    }
                                }
                            }
                        } else {
                            PrimeFaces.current().executeScript("acoesPosAjax()");
                            ItfRespostaAcaoDoSistema respAcao = autoExecAcaoController((T) pEntidadeSelecionada);
                            if (respAcao != null) {
                                respAcao.dispararMensagens();
                            }
                            autoExecProximaAcaoAposController((ComoAcaoController) acaoSelecionada, respAcao);
                        }

                    } catch (Throwable t) {
                        SBCore.enviarMensagemUsuario("Houve um erro inesperado!, entre em contato com o suporte", FabMensagens.ALERTA);
                        SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro executando ação de controler padrão, a resposta não foi obtida para:" + acaoSelecionada, t);
                        autoExecProximaAcaoAposController((ComoAcaoController) acaoSelecionada, null);
                    }
                    break;

                case ACAO_SELECAO_DE_ACAO:
                    break;
                default:
                    throw new AssertionError(acaoSelecionada.getTipoAcaoSistema().name());

            }

            System.out.println("ALTERANDO A ENTIDADE SELECIONADA TESTE ---------------------- " + pEntidadeSelecionada);

            switch (acaoSelecionada.getTipoAcaoGenerica()) {
                case FORMULARIO_NOVO_REGISTRO:
                    atualizaInformacoesDeEdicao(FabEstadoFormulario.NOVO);
                    break;
                case FORMULARIO_EDITAR:
                    atualizaInformacoesDeEdicao(FabEstadoFormulario.EDITAR);
                    break;
                case FORMULARIO_PERSONALIZADO:

                    break;
                case FORMULARIO_VISUALIZAR:
                    atualizaInformacoesDeEdicao(FabEstadoFormulario.VISUALIZAR);
                    break;
                case FORMULARIO_LISTAR:

                    if (fecharEntityManagerAoListar) {
                        renovarEMPagina();
                    }
                    if (listarEntidadesLasyMode) {
                        setEntidadesListadas(null);
                    } else {
                        listarDados();
                    }
                    setEntidadeSelecionada(null);
                    atualizaInformacoesDeEdicao(FabEstadoFormulario.VISUALIZAR);
                    atualizarIdAreaExibicaoAcaoSelecionada();
                    break;
                case FORMULARIO_MODAL:
                    break;
                case SELECAO_DE_ACAO:
                    atualizarEntidadeSelecionada();
                    break;
                case CONTROLLER_SALVAR_EDICAO:
                    break;
                case CONTROLLER_SALVAR_NOVO:
                    break;
                case CONTROLLER_SALVAR_MODO_MERGE:
                    break;
                case CONTROLLER_PERSONALIZADO:
                    break;
                case CONTROLLER_ATIVAR_DESATIVAR:
                    break;
                case CONTROLLER_ATIVAR:
                    break;
                case CONTROLLER_REMOVER:
                    break;
                case CONTROLLER_DESATIVAR:
                    break;
                case GERENCIAR_DOMINIO:
                    break;
                default:
                    throw new AssertionError(acaoSelecionada.getTipoAcaoGenerica().name());

            }

        }
    }

    protected BP_DataModelLasy<T> listaEntidadeLasy;

    public BP_DataModelLasy<T> getListaRegistrosLasy() {
        if (listaEntidadeLasy == null) {
            listaEntidadeLasy = new BP_DataModelLasy(getEntidadesListadas());
        }
        return listaEntidadeLasy;
    }

    public List<ComoAcaoDoSistema> autoExecObterAcoesDoRegistro(T pRegistro) {
        List<ComoAcaoDoSistema> acoesDoRegistro = new ArrayList<>();
        try {
            if (pRegistro == null) {
                if (temNovo) {
                    acoesDoRegistro.add(getAcaoNovoRegistro());
                }
                if (getAcaoListarRegistros() != null) {
                    acoesDoRegistro.add(getAcaoListarRegistros());
                }
                return acoesDoRegistro;

            }

            List<Field> campos = UtilCRCReflexao.getCamposRecursivoPorInterface(pRegistro.getClass(), ComoStatus.class,
                    ComoEntidadeSimples.class
            );
            if (!campos.isEmpty()) {
                Field campo = campos.get(0);
                ComoStatus statusEntidade = (ComoStatus) campo.get(getEntidadeSelecionada());
                return statusEntidade.getStatusEnum().opcoesPorStatus();
            }
            acoesDoRegistro.addAll(getAcaoVinculada().getAcoesVinculadasAObjetoExistente());
            return acoesDoRegistro;
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro obtendo açoes para " + getEntidadeSelecionada(), t);
            return new ArrayList<>();
        }
    }

    public List<ComoAcaoDoSistema> getAcoesDoRegistroSelecionado() {
        return autoExecObterAcoesDoRegistro(getEntidadeSelecionada());
    }

    @Override
    public void selAcaoDeCtrParaUltimoFrm() {
        super.selAcaoDeCtrParaUltimoFrm(); //To change body of generated methods, choose Tools | Templates.
        if (ultimaRespostaControllerRecebida != null) {
            ultimaRespostaControllerRecebida.dispararMensagens();
        }
    }

}
