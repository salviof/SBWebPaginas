/*
 *  Super-Bits.com CODE CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.interfaces;

import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfParametroRequisicao;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfParametroRequisicaoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoGerenciarEntidade;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.ComunicacaoAcaoSistema;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.FabTipoRespostaComunicacao;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.ItfTipoRespostaComunicacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.B_Pagina;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.InfoDesignFormulario;
import com.super_bits.modulosSB.webPaginas.controller.servlets.servletWebPaginas.ConfiguracoesDeFormularioPorUrl;
import java.util.List;
import java.util.Map;
import org.primefaces.event.SelectEvent;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.ItfDialogo;

/**
 *
 * @author Salvio
 */
public interface ItfB_Pagina extends ItfB_PaginaSimples, ItfB_PaginaComEntityManager {

    public void fecharPagina();

    /**
     *
     * Fecha a pagina, e mata o viewScope,
     *
     */
    public void fecharPaginaMatandoViewScoped();

    public void abrePagina();

    public void selAcaoDeCtrParaUltimoFrm();

    /**
     *
     * Uma pagina de gestão (PgManagedBean) possui tags associadas a sua função.
     *
     * estas tags servem para montar os links de pagina e para facilitar
     * recursos de SEO
     *
     * @return Tags vinculadas a pagina
     */
    public List<String> getTags();

    /**
     *
     * Um nome curto facilita o acesso via url para determinada pagina de gestão
     *
     * @return Nome curto da pagina
     */
    public String getNomeCurto();

    /**
     *
     * REtorna o nome de chamada para criação de links para esta pagina (Função
     * antiga e hoje praticamente descessária, será excluida no futuro)
     *
     * Motivo principal:(para você que ficou curioso) os componentes para
     * ciração de botão de ação como <sbComp:botaoDeAcao fazem este trabalho com
     * mais eficiencia.
     *
     * @return
     */
    @Deprecated
    public String getLinkRotulo();

    /**
     *
     * Retorna a tag de url (do getTags)que foi utilizada para carregar a pagina
     *
     * @see ItfB_Pagina#getTags()
     *
     * @return tag utilizada para carregar a pagina
     */
    public String getTagUsada();

    /**
     *
     *
     * @deprecated (nomeclatura ruim, pois retorna a Url de acordo com o
     * parametro selecionado e não de acordo com o padrão
     * @return Url padrão para acesso a esta pagina
     */
    @Deprecated
    public String getUrlPadrao();

    /**
     *
     * Parametros de Url são parametros que podem ser configurados via chamada
     * de URL
     *
     * O fato de ser possível executar uma ação da pagina pela url informando um
     * parametro em seguida, tornou este recurso quase obsoleto, porém não
     * chegamos a conclusão sobre sua extinção, comente sobre issso em
     * wiki.superbits.org.br
     *
     * @return a lista de todos os parametros nescessários para esta pagina
     */
    public List<ItfParametroRequisicaoInstanciado> getParametrosURL();

    /**
     *
     * @see ItfB_Pagina#getParametrosURL()
     *
     *
     * @param pNome nome do parametro
     * @return O parametro especifico levando em conta o nome do parametro :P
     */
    public ItfParametroRequisicaoInstanciado getParametroByNome(String pNome);

    /**
     *
     * @see ItfB_Pagina#getParametrosURL()
     *
     * @param pId Inteiro relacionado ao parametro
     * @return retorna um parametro especifico baseado em seu id;
     */
    public String getNomeParametroById(int pId);

    /**
     *
     * Este método altera a tag utilizada, deveria estar protegido, será
     * excluido no futuro..
     *
     * @param tagUsada
     */
    @Deprecated
    public void setTagUsada(String tagUsada);

    /**
     *
     * Retorna o parametro de acordo com o nome da entidade
     *
     * @see ItfB_Pagina#getParametrosURL()
     *
     * @param nomeEntidade nome da entidade vinculada ao parametro
     * @return O parametro vinculado a entidade
     */
    public ItfParametroRequisicaoInstanciado getParametrobyTipoEntidade(String nomeEntidade);

    public ItfParametroRequisicaoInstanciado getParametroInstanciado(ItfParametroRequisicao pParametro);

    /**
     *
     * @return @deprecated O controle de acesso agora é configurado pela
     * AcaoVinculada
     */
    @Deprecated
    public boolean isAcessoLivre();

    /**
     *
     * Todo PgAlgumacoisa é vinculado a auma ação de gestão esta ação de gestão
     * determina quais ações existirão na pagina, nome da pagina e configurações
     * de segurança, este metodo retorna a ação de gestão vinculada a pagina
     *
     *
     * @return Ação de gestão vinculada a pagina
     */
    public ItfAcaoGerenciarEntidade getAcaoVinculada();

    /**
     *
     * Aplica valores de parametros passados por url no MB_pagina
     *
     * #DEveria ser protected
     *
     * @param valorStringPorParametro
     */
    @Deprecated
    public void aplicaValoresDeParametrosModoDesenvolvimento(Map<String, String> valorStringPorParametro);

    /**
     *
     * @return Todas as ações declaradas no managed Bean
     */
    public List<ItfAcaoDoSistema> getAcoesDaPagina();

    /**
     *
     * @return A ação selecionada no momento
     */
    public ItfAcaoDoSistema getAcaoSelecionada();

    public ItfB_PaginaComEtapaVinculada getComoPaginaComEtapa();

    /**
     *
     * Retorna um bean declarado do Managed bean.
     *
     * Um bean declarado possui informações de exibição de icone
     *
     * @param nomeBean
     * @return
     */
    public B_Pagina.BeanDeclarado getBeanDeclarado(String nomeBean);

    public void aplicarUrlDeAcesso(ConfiguracoesDeFormularioPorUrl pConfig);

    public ItfB_Modal getModalAtual();

    public void setModalAtual(ItfB_Modal pModal);

    public String getNomeMB();

    public ItfBeanSimples getBeanSelecionado();

    public void setBeanSelecionado(ItfBeanSimples pBeanSimples);

    public void setCampoInstSelecionado(ItfCampoInstanciado pCampoInstanciado);

    public ItfCampoInstanciado getCampoInstSelecionado();

    public FabTipoRespostaComunicacao getRespostaAcaoAtual();

    public void setTipoRespostaParaAcaoAtual(ItfTipoRespostaComunicacao pTipoResp);

    public ComunicacaoAcaoSistema getComunicacaoTransientAcaoByIdModal(String pIdModal);

    public void adicionarCodigoCoversa(String pCodigoConversa);

    /**
     * --> O Event possui set e get objetc para enviar e catpurar objetos -->
     * Foi desabilitado por falhas em determinadas situações, que não foram
     * possíveis de ser debugadas, precisa de apoio da comunicade do
     * prime-faces, ou aguardar uma versão que torne este recursos funcional
     *
     * @param event Evento que captura a resposta do modal
     */
    public void metodoRespostaModalPrimefaces(SelectEvent event);

    /**
     * Recebe parametros gerados pelo modal, como resposta para comunicação
     * transient e outros
     *
     * A implementação padrão, registra a resposta e em seguida executa a ação
     * selecionada
     *
     * @param pParametros
     */
    public void metodoRespostaModal(Object... pParametros);

    public ItfDialogo getComunincacaoAguardandoResposta();

    public void zerarDadosModal();

    public InfoDesignFormulario getInfoLayout();
}
