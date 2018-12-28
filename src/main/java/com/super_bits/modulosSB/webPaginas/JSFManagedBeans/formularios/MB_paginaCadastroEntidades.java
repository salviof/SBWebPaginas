/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios;

import com.super_bits.modulos.SBAcessosModel.model.acoes.AcaoDoSistema;
import com.super_bits.modulosSB.Persistencia.ConfigGeral.SBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.consultaDinamica.ConsultaDinamicaDeEntidade;
import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeSimples;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreReflexao;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreReflexaoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfParametroRequisicaoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfRespostaAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoController;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoControllerEntidade;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoSecundaria;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.estadoFormulario.FabEstadoFormulario;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoEntidade;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoFormulario;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoFormularioEntidade;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoGerenciarEntidade;
import com.super_bits.modulosSB.SBCore.modulos.Controller.UtilFabricaDeAcoesBasico;
import com.super_bits.modulosSB.SBCore.modulos.Controller.UtilSBController;
import com.super_bits.modulosSB.SBCore.modulos.Controller.fabricas.FabTipoAcaoSistemaGenerica;
import com.super_bits.modulosSB.SBCore.modulos.Mensagens.FabMensagens;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.FabTipoRespostaComunicacao;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ItfFabricaAcoes;
import com.super_bits.modulosSB.SBCore.modulos.geradorCodigo.model.EstruturaDeEntidade;
import com.super_bits.modulosSB.SBCore.modulos.geradorCodigo.model.LigacaoMuitosParaUm;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoPreparacaoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.MapaObjetosProjetoAtual;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanGenerico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimplesSomenteLeitura;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanStatus;
import com.super_bits.modulosSB.webPaginas.JSFBeans.PrimeFacesBeanModel.dataListLasy.BP_DataModelLasy;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.declarados.util.PgUtil;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.interfaces.ItfPaginaGerenciarEntidade;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.siteMap.MapaDeFormularios;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.siteMap.parametrosURL.ParametroURL;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.siteMap.parametrosURL.ParametroUrlInstanciado;
import com.super_bits.modulosSB.webPaginas.controller.servletes.servletWebPaginas.EstruturaDeFormulario;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWP_JSFTools;
import com.super_bits.modulosSB.webPaginas.util.UtillSBWPReflexoesWebpaginas;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
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
public abstract class MB_paginaCadastroEntidades<T extends ItfBeanSimples> extends MB_PaginaConversation implements ItfPaginaGerenciarEntidade<T> {

    private T entidadeSelecionada;
    private List<T> entidadesListadas;

    private boolean temPesquisa;
    private boolean temEditar = true;
    private boolean temNovo = true;
    private boolean temAlterarStatus = true;
    private boolean temVisualizar = true;

    private final List<ItfAcaoDoSistema> acoesRegistros;
    protected ItfAcaoFormularioEntidade acaoListarRegistros;
    protected final ItfAcaoFormularioEntidade acaoNovoRegistro;
    protected final ItfAcaoControllerEntidade acaoSalvarAlteracoes;

    private ItfAcaoFormularioEntidade acaoEntidadeEditar;
    private ItfAcaoControllerEntidade acaoEntidadeAlterarStatus;
    private ItfAcaoFormularioEntidade acaoEntidadeVisualizar;

    private boolean podeEditar;
    private boolean novoRegistro;
    private boolean fecharEntityManagerAoListar = true;
    private boolean mostarApenasORegistroCriadoAoListar = false;
    private Boolean umaEntidadePersistivel;
    private GestaoDeDominioEmPagina<T> gestaoDominio;

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
    protected void autoExecProximaAcaoAposController(ItfAcaoController pAcaoController, ItfRespostaAcaoDoSistema pResposta) {
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
                setAcaoSelecionada(acaoListarRegistros);
                executarAcaoSelecionada();
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
        mostarApenasORegistroCriadoAoListar = pOpcao;
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

    private static ItfAcaoDoSistema[] converterArrayDeAcoes(List<ItfAcaoDoSistema> pParametro) {
        ItfAcaoDoSistema[] acoes = new ItfAcaoDoSistema[pParametro.size()];
        pParametro.toArray(acoes);

        return acoes;
    }

    private static ItfAcaoDoSistema[] converterAcoesSecundariasEmArrayDeAcao(List<ItfAcaoSecundaria> pParametro) {
        ItfAcaoDoSistema[] acoes = new ItfAcaoDoSistema[pParametro.size()];

        pParametro.toArray(acoes);

        return acoes;
    }

    /**
     * INCLUI NA LISTA DE ACOES DA PAGINA
     *
     * @param pAcaoDoSistema
     */
    public void adicionarAcaoDeEntidade(ItfFabricaAcoes pAcaoDoSistema) {
        adicionarAcaoDeEntidade(pAcaoDoSistema.getRegistro());
    }

    public void adicionarAcaoDeEntidade(ItfAcaoDoSistema pAcaoDoSistema) {
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

    public void removerAcaoDeEntidade(ItfAcaoDoSistema pAcaoDoSistema) {
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
            ItfAcaoDoSistema[] pAcoesRegistro,
            ItfAcaoFormularioEntidade pAcaoNovoRegistro,
            ItfAcaoFormularioEntidade pAcaoListar,
            ItfAcaoControllerEntidade pAcaoSalvar,
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

            for (ItfAcaoDoSistema acao : pAcoesRegistro) {
                acoesRegistros.add((ItfAcaoDoSistema) acao);
            }
            if (acaoListarRegistros == null) {
                throw new UnsupportedOperationException("ação Listar não foi configurada em " + this.getClass().getSimpleName());
            }

            entidadesListadas = new ArrayList<>();

            if (SBCore.getEstadoAPP() == SBCore.ESTADO_APP.DESENVOLVIMENTO) {
                paginaUtil = new PgUtil();
            }

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
            ItfAcaoDoSistema[] pAcoesRegistro,
            ItfAcaoFormularioEntidade pAcaoNovoRegistro,
            ItfAcaoFormularioEntidade pAcaoListar,
            ItfAcaoControllerEntidade pAcaoSalvar,
            boolean pTempesquisa
    ) {
        this(pAcoesRegistro, pAcaoNovoRegistro, pAcaoListar, pAcaoSalvar, pTempesquisa, false);

    }

    public MB_paginaCadastroEntidades(ItfAcaoGerenciarEntidade pGestao,
            ItfFabricaAcoes... acoesDeRegistro
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
        this((ItfAcaoDoSistema[]) pAcoesRegistro.toArray(new AcaoDoSistema[pAcoesRegistro.size()]), pAcaoNovoRegistro.getComoFormularioEntidade(),
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
    public MB_paginaCadastroEntidades(ItfAcaoDoSistema[] pAcoesRegistro,
            ItfAcaoFormularioEntidade pAcaoNovoRegistro,
            ItfAcaoFormularioEntidade pAcaoListar,
            ItfAcaoControllerEntidade pAcaoSalvar,
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

                            T entidadeAtualizada = UtilSBPersistencia.loadEntidade((ItfBeanSimples) parametro.getValor(), getEMPagina());
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

    public boolean isAcaoAtualAcaoControllerPadrao() {
        if (getEntidadeSelecionada() == null) {
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
        String tipoEntidadeSelecionada = getEntidadeSelecionada().getClass().getSimpleName();

        if (tipoMetodoParametro.equals(tipoEntidadeSelecionada)) {
            return true;
        } else {
            try {
                return metodo.getParameterTypes()[0].isInstance(getEntidadeSelecionada());

            } catch (Throwable t) {
                return false;
            }

        }

    }

    public ItfRespostaAcaoDoSistema execucaoAcaoControllerPadrao(ItfAcaoController pAcaoController) {
        return execucaoAcaoControllerPadrao(pAcaoController, true);
    }

    public ItfRespostaAcaoDoSistema execucaoAcaoControllerPadrao(ItfAcaoController pAcaoController, boolean pExecutarPosAcaoPadrao) {

        try {

            Method metodo = UtilSBController.getMetodoByAcaoController(pAcaoController);

            ItfRespostaAcaoDoSistema resp = (ItfRespostaAcaoDoSistema) metodo.invoke(null, getEntidadeSelecionada());
            if (pExecutarPosAcaoPadrao) {
                resp.dispararMensagens();
                autoExecProximaAcaoAposController(pAcaoController, resp);

            }
            return resp;
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro ao executar método por implementação padrão", t);
            return null;
        }
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
    public List<ItfAcaoDoSistema> getAcoesRegistros() {

        return acoesRegistros;
    }

    // Retorna ação de novo registro
    @Override
    public ItfAcaoFormularioEntidade getAcaoNovoRegistro() {
        return acaoNovoRegistro;
    }

    @Override
    public ItfAcaoFormularioEntidade getAcaoListarRegistros() {

        if (acaoListarRegistros == null) {
            acaoListarRegistros = getAcaoVinculada().getAcaoFormularioListarPadrao();
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
    public ItfAcaoDoSistema getAcaoSelecionada() {
        return (ItfAcaoDoSistema) acaoSelecionada;
    }

    // Define a ação selecionada
    @Override
    public void setAcaoSelecionada(ItfAcaoDoSistema acaoSelecionada) {

        this.acaoSelecionada = acaoSelecionada;

    }

    @Override
    public String getXhtmlAcaoAtual() {
        return xhtmlAcaoAtual;
    }

    @Override
    public ItfAcaoDoSistema getAcaoSalvarAlteracoes() {
        try {
            if (acaoSalvarAlteracoes != null) {
                if (!acaoSalvarAlteracoes.getComoControllerEntidade().getClasseRelacionada().getSimpleName().equals(getEntidadeSelecionada().getClass().getSimpleName())) {
                    List acoesDaEntidade = getAcaoVinculada().getAcoesVinculadasDesteTipoComEstaEntidade(FabTipoAcaoSistemaGenerica.CONTROLLER_SALVAR_MODO_MERGE, getEntidadeSelecionada().getClass());
                    if (!acoesDaEntidade.isEmpty()) {
                        return (ItfAcaoDoSistema) acoesDaEntidade.get(0);
                    }
                }
            }
        } catch (Throwable t) {

        }

        return (ItfAcaoDoSistema) acaoSalvarAlteracoes;
    }

    @Override
    public T getEntidadeSelecionada() {
        try {
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
            return entidadesListadas;
        } catch (Throwable t) {

            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro selecionando entidades listadas", t);
            return new ArrayList<>();
        }
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

            for (ItfAcaoDoSistema acao : acoesRegistros) {

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
                            acaoEntidadeAlterarStatus = (ItfAcaoControllerEntidade) acao;
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
                System.out.println("TODO corrigir constructor recebendo ação de edição");
            }

            if (temVisualizar & acaoEntidadeVisualizar == null) {
                System.out.println("TODO corrigir constructor recebendo ação de visualizacao");
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
    public ItfAcaoControllerEntidade getAcaoAlterarStatus() {
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

    protected ItfBeanSimples getEntidadeSelecionadaComoBeanSimples() {
        return (ItfBeanSimples) getEntidadeSelecionada();
    }

    @Override
    public ItfBeanSimples getBeanSelecionado() {

        return getEntidadeSelecionadaComoBeanSimples();
    }

    @Override
    public void setBeanSelecionado(ItfBeanSimples pBeanSimples) {
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
            if (mostarApenasORegistroCriadoAoListar && isNovoRegistro() && getEntidadeSelecionada() != null && getEntidadeSelecionadaComoBeanSimples().getId() == 0) {
                if (getEntidadesListadas() != null) {
                    getEntidadesListadas().clear();
                    getEntidadesListadas().add(entidadeSelecionada);
                }
            } else {
                try {
                    ItfBeanSimples bean = (ItfBeanSimples) getAcaoVinculada().getClasseRelacionada().newInstance();
                    if (!mostrarInativos && bean.isTemCampoAnotado(FabTipoAtributoObjeto.REG_ATIVO_INATIVO)) {
                        setEntidadesListadas(new ConsultaDinamicaDeEntidade(getAcaoVinculada().getClasseRelacionada(), getEMPagina()).addCondicaoPositivo(bean.getNomeCampo(FabTipoAtributoObjeto.REG_ATIVO_INATIVO)).resultadoRegistros());

                    } else {
                        setEntidadesListadas(UtilSBPersistencia.getListaTodos(getAcaoVinculada().getClasseRelacionada(), getEMPagina()));
                    }
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
        if (!mapaRespostasComunicacaoTransienteDeAcaoByAcoes.isEmpty()) {
            executarAcao(entidadeSelecionada);
            mapaRespostasComunicacaoTransienteDeAcaoByAcoes.clear();
            mapaComunicacaoTransienteDeAcaoByIdModal.clear();
        }
    }

    @Override
    public void atualizarEntidadeSelecionada() {
        try {
            if (getEntidadeSelecionada() != null) {
                ItfBeanSimplesSomenteLeitura entidadeComoBeanSimples = (ItfBeanSimplesSomenteLeitura) getEntidadeSelecionada();

                renovarEMPagina();
                setEntidadeSelecionada(UtilSBPersistencia.loadEntidade(entidadeComoBeanSimples, getEMPagina()));
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
            Class classeDaEntidade = null;
            if (getAcaoSelecionada() instanceof ItfAcaoEntidade) {
                ItfAcaoEntidade acao = (ItfAcaoEntidade) getAcaoSelecionada();
                classeDaEntidade = acao.getClasseRelacionada();

            } else {
                classeDaEntidade = getAcaoVinculada().getClasseRelacionada();
            }
            getPaginaDoDominio().setEntidadeSelecionada((T) classeDaEntidade.newInstance());
            InfoPreparacaoObjeto infoPreparacao = UtilSBCoreReflexaoObjeto.getInfoPreparacaoObjeto(classeDaEntidade);
            if (infoPreparacao != null) {
                if (infoPreparacao.classesPrConstructorPrincipal().length == 0) {
                    ((ItfBeanGenerico) getEntidadeSelecionada()).prepararNovoObjeto();
                } else {
                    if (infoPreparacao.classesPrConstructorPrincipal().length == 1) {
                        Class classeParametro = infoPreparacao.classesPrConstructorPrincipal()[0];
                        if (isTemParametrobyTipoEntidade(classeParametro.getSimpleName())) {
                            ItfParametroRequisicaoInstanciado pr = getParametrobyTipoEntidade(classeParametro.getSimpleName());
                            getEntidadeSelecionada().prepararNovoObjeto(pr.getValor());
                        } else {

                            throw new UnsupportedOperationException("Impossível encontrar parametros do tipo " + classeParametro.getSimpleName() + " para preparar objeto do tipo " + classeDaEntidade.getSimpleName());
                        }
                    } else {
                        // todo Erro buscar em beans declarados
                        throw new UnsupportedOperationException("Impossível encontrar parametros para prepara objeto");
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

        switch (getAcaoSelecionada().getTipoAcaoGenerica()) {

            case FORMULARIO_NOVO_REGISTRO:

                autoexecEntidadeNova();

                break;
            case FORMULARIO_EDITAR:
            case FORMULARIO_VISUALIZAR:
            case FORMULARIO_PERSONALIZADO:
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
                    if (((ItfBeanSimplesSomenteLeitura) pEnTidade).getId() != 0) {
                        // Caso ESteja mudando de registro selecionado
                        if (getEntidadeSelecionada() == null
                                || ((ItfBeanSimplesSomenteLeitura) getEntidadeSelecionada()).getId() != ((ItfBeanSimplesSomenteLeitura) pEnTidade).getId()) {
                            //Atualiza o REgsitro Selecionado
                            if (isUmaEntidadePersistivel()) {
                                renovarEMPagina();
                                if (!getAcaoSelecionada().isUmaAcaoController()) {
                                    if (mesmoentreEntidadeEAcaoAtual) {
                                        getPaginaDoDominio().setEntidadeSelecionada((T) UtilSBPersistencia.loadEntidade((ItfBeanSimples) pEnTidade, getEMPagina()));
                                    } else {
                                        String strClasseEntidade = UtilSBCoreReflexaoObjeto.getClasseDiscriminatoriaPolimorfismoDeEntidade(pEnTidade);
                                        if (strClasseEntidade != null) {
                                            try {
                                                pEnTidade = (T) UtilSBPersistencia.getRegistroByID(MapaObjetosProjetoAtual.getClasseDoObjetoByNome(strClasseEntidade), pEnTidade.getId(), getEMPagina());
                                                setEntidadeSelecionada(pEnTidade);
                                            } catch (Throwable t) {
                                                getPaginaDoDominio().setEntidadeSelecionada((T) UtilSBPersistencia.loadEntidade((ItfBeanSimples) pEnTidade, getEMPagina()));
                                            }
                                        } else {
                                            getPaginaDoDominio().setEntidadeSelecionada((T) UtilSBPersistencia.loadEntidade((ItfBeanSimples) pEnTidade, getEMPagina()));
                                        }
                                    }

                                } else {
                                    if (getEntidadeSelecionada() == null || !getEntidadeSelecionada().equals(pEnTidade)) {

                                        getPaginaDoDominio().setEntidadeSelecionada((T) UtilSBPersistencia.loadEntidade((ItfBeanSimples) pEnTidade, getEMPagina()));
                                    } else {
                                        getPaginaDoDominio().setEntidadeSelecionada(pEnTidade);
                                    }
                                }

                            } else {
                                getPaginaDoDominio().setEntidadeSelecionada(pEnTidade);
                            }

                        }// se a entidadeSelecionada anteriormente for nula, ou se a nova entidade for diferente dela

                    }//se a nova entidade  a ser selecionada(pEntidade) não for um novo registro (com id 0)

                }// se a nova entidade  a ser selecionada(pEntidade) for diferente de nulo
                break;
            default:
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
            ItfAcaoDoSistema acoAlternativa = getAcaoVinculada().getAcaoCompativelEntidadeDivergente(acaoSelecionada, classeEntidadeSelecionada);
            if (acoAlternativa != null) {
                acaoSelecionada = acoAlternativa;
            }
        }

    }

    @Override
    protected void autoExecAlterarFormulario(final ItfAcaoFormulario pAcao) {

        super.autoExecAlterarFormulario(pAcao);
        if (pAcao != null) {
            atualizaInformacoesDeEdicao(pAcao.getTipoAcaoGenerica().getEstadoFormularioPadrao());

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
            List<ParametroURL> prs = estruturaForm.getParametrosURL();

            if (prs.isEmpty()) {
                UtilSBWP_JSFTools.vaParaPagina(MapaDeFormularios.getUrlFormulario(acaoSelecionada));
            } else {
                long quantidade = prs.stream().filter(pr -> pr.isParametroObrigatorio()).count();
                if (quantidade > 1) {
                    throw new UnsupportedOperationException("A página " + estruturaForm.getAcaoGestaoVinculada().getNomeUnico() + " contem mais de um parametro obrigatório , extenda autoExecMBAlternativoExterno() na classe " + this.getClass().getSimpleName() + " e configure esta ação manualmente");
                }

                if (getEntidadeSelecionada() != null) {
                    String classeEntidadeSel = getEntidadeSelecionada().getClass().getSimpleName();
                    Optional<ParametroURL> pesquisaPrDesteTipo = prs.stream().filter(pr -> (pr.isUmParametroDeEntidade() && pr.getClasseObjetoValor().getSimpleName().equals(classeEntidadeSel))).findFirst();
                    if (pesquisaPrDesteTipo.isPresent()) {
                        String urlComEntidade = MapaDeFormularios.getUrlFormulario(acaoSelecionada, getEntidadeSelecionada());
                        UtilSBWP_JSFTools.vaParaPagina(urlComEntidade);
                    } else if (prs.get(0).getTipoEntidade().isInstance(getEntidadeSelecionada())) {
                        String urlComEntidade = MapaDeFormularios.getUrlFormulario(acaoSelecionada, getEntidadeSelecionada());
                        UtilSBWP_JSFTools.vaParaPagina(urlComEntidade);
                    } else {
                        EstruturaDeEntidade est = MapaObjetosProjetoAtual.getEstruturaObjeto(classeEntidadeSel);

                        Optional<LigacaoMuitosParaUm> pesquisaLigacao
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
                                throw new UnsupportedOperationException("Impossível determinar uma entidade ou parametro compatível nesta pagina para abrir " + estruturaForm.getAcaoGestaoVinculada().getNomeUnico() + " , extenda autoExecMBAlternativoExterno() na classe " + this.getClass().getSimpleName() + " e configure esta ação manualmente");
                            }
                        }
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

            autoExecEntidadeLoad((T) pEntidadeSelecionada);

        }

        public void executarAcao(TT pEntidadeSelecionada) {

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

                    autoExecAlterarFormulario(acaoSelecionada.getComoFormulario());

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
                        if (!verificaModalAcaoAtual()) {
                            PrimeFaces.current().executeScript("acoesPosAjax()");

                            ItfRespostaAcaoDoSistema respAcao = autoExecAcaoController((T) pEntidadeSelecionada);
                            autoExecProximaAcaoAposController((ItfAcaoController) acaoSelecionada, respAcao);

                        } else {
                            FabTipoRespostaComunicacao respComunicacao = mapaRespostasComunicacaoTransienteDeAcaoByAcoes.get(getAcaoSelecionada().getNomeUnico());
                            if (respComunicacao != null) {
                                if (respComunicacao.isRespostaPositiva()) {
                                    ItfRespostaAcaoDoSistema respAcao = autoExecAcaoController((T) pEntidadeSelecionada);
                                    autoExecProximaAcaoAposController((ItfAcaoController) acaoSelecionada, respAcao);
                                } else {

                                    autoExecProximaAcaoAposController((ItfAcaoController) acaoSelecionada, null);
                                }
                            }

                        }
                    } catch (Throwable t) {
                        SBCore.enviarAvisoAoUsuario("Houve um erro inesperado!, entre em contato com o suporte");
                        SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro executando ação de controler padrão, a resposta não foi obtida para:" + acaoSelecionada, t);
                        autoExecProximaAcaoAposController((ItfAcaoController) acaoSelecionada, null);
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
                    if (SBCore.getEstadoAPP() != SBCore.ESTADO_APP.PRODUCAO) {
                        System.out.println("Definindo modo somente leitura e Atualizando os dados da lista");

                    }
                    if (fecharEntityManagerAoListar) {
                        renovarEMPagina();
                    }

                    listarDados();
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
    public ItfRespostaAcaoDoSistema autoExecAcaoController(T pEntidade) {

        ItfRespostaAcaoDoSistema respExecucao = null;
        if (pEntidade == null || getEntidadeSelecionada() == null) {
            throw new UnsupportedOperationException("Entidade selecionada é nula durante chamada de método controller" + acaoSelecionada.getNomeUnico());
        }
        if (isAcaoAtualAcaoControllerPadrao()) {
            try {
                respExecucao = execucaoAcaoControllerPadrao(acaoSelecionada.getComoController(), false);

                respExecucao.dispararMensagens();
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

    protected BP_DataModelLasy<T> listaEntidadeLasy;

    public BP_DataModelLasy<T> getListaRegistrosLasy() {
        if (listaEntidadeLasy == null) {
            listaEntidadeLasy = new BP_DataModelLasy(getEntidadesListadas());
        }
        return listaEntidadeLasy;
    }

    public List<ItfAcaoDoSistema> autoExecObterAcoesDoRegistro(T pRegistro) {
        List<ItfAcaoDoSistema> acoesDoRegistro = new ArrayList<>();
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

            List<Field> campos = UtilSBCoreReflexao.getCamposRecursivoPorInterface(pRegistro.getClass(), ItfBeanStatus.class,
                    EntidadeSimples.class
            );
            if (!campos.isEmpty()) {
                Field campo = campos.get(0);
                ItfBeanStatus statusEntidade = (ItfBeanStatus) campo.get(getEntidadeSelecionada());
                return statusEntidade.getStatusEnum().opcoesPorStatus();
            }
            acoesDoRegistro.addAll(getAcaoVinculada().getAcoesVinculadasAObjetoExistente());
            return acoesDoRegistro;
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro obtendo açoes para " + getEntidadeSelecionada(), t);
            return new ArrayList<>();
        }
    }

    public List<ItfAcaoDoSistema> getAcoesDoRegistroSelecionado() {
        return autoExecObterAcoesDoRegistro(getEntidadeSelecionada());
    }

}
