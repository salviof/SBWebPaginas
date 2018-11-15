/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFManagedBeans.declarados.ConstAppFormEComponente.contantesView;

import com.super_bits.modulosSB.webPaginas.JSFBeans.SBBeanModel.modelosPagina.FabModelosPagina;
import com.super_bits.modulosSB.webPaginas.JSFBeans.SBBeanModel.modelosPagina.ModeloPagina;
import java.io.Serializable;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 *
 * @author salvioF
 */
@ApplicationScoped
@Named
public class LayoutsPaginas implements Serializable {

    /**
     *
     * Mostra um formulário de ação atual deixando apenas o conteúdo e botões de
     * ação inferior para serem incluidos
     *
     * Areas de interface possíves:
     * <b>conteudo</b> <br>
     * <b>botoesDeAcao </b><br>
     *
     * ex: * [ui:define name="conteudo"/] Meu conteudo [/ui:define] <br>
     *
     * /resources/SBComp/template/basico/formularioEntidadeAcaoAtual.xhtml
     *
     * @return
     */
    public ModeloPagina getFormularioAcaoAtualDaEntidade() {
        return FabModelosPagina.FORMULARIO_PG_ENTIDADE_ACAO_ATUAL.getRegistro();
    }

    /**
     *
     * Estrutura básica de um site, com importação de todos os javscripts e css
     * nescessários para execução dos comandos básicos,possui estrutura de topo,
     * conteúdo e rodapé
     *
     * Areas definidas posssíveis:  <br>
     * <b>divConteudo </b>  <br>
     * <b>head  </b>    <br>
     * Formulários incorporados: /site/topo.xhtml folhasDeEstiloBasicas.xhtml
     * /site/rodape.xhtml /site/rodape.xhtml     <br>
     *
     * @see FabModelosPagina#MODELO_GERAL
     * @return
     */
    public ModeloPagina getModeloGeral() {
        return FabModelosPagina.MODELO_GERAL.getRegistro();
    }

    /**
     *
     * /resources/SBComp/template/basico/modeloAnonimo.xhtml
     *
     * O mesmo que o modelo geral, porem sem topo.xhtml e rodape.xhtml
     *
     * Areas definidas posssíveis:  <br>
     * <b>divConteudo </b><br>
     * <b>head </b><br>
     * ex: * [ui:define name="divConteudo"/] Meu conteudo [/ui:define] <br>
     *
     *
     *
     * @see LayoutsPaginas#getModeloGeral()
     * @see FabModelosPagina#MODELO_GERAL_ANONIMO
     * @return
     */
    public ModeloPagina getModeloGeralAnonimo() {
        return FabModelosPagina.MODELO_GERAL_ANONIMO.getRegistro();
    }

    /**
     * /resources/SBComp/template/basico/modeloAnonimo.xhtml
     *
     * Areas definidas posssíveis:  <br>
     * <b>conteudo </b><br>
     * <b>head </b><br>
     * ex: * [ui:define name="conteudo"/] Meu conteudo [/ui:define] <br>
     *
     * @return
     */
    public ModeloPagina getModeloGeralModal() {
        return FabModelosPagina.MODELO_GERAL_MODAL.getRegistro();
    }

    /**
     *
     * Exibe um formulário modelo CARD, contendo: icone da ação posicionado no
     * lado esquerdo superior, Um título com subtitulo proximo ao icone, da ação
     * atual e 2 areas para definir: conteudoDireita e conteudoCard
     *
     * Areas definidas posssíveis:  <br>
     *
     * <b>conteudoDireita </b> <br>
     * <b>conteudoCard </b><br>
     * ex: * [ui:define name="conteudo"/] Meu conteudo [/ui:define] <br>
     *
     * @see FabModelosPagina#FORMULARIO_PG_ENTIDADE_ACAO_ATUAL
     * /resources/SBComp/template/basico/formularioAcaoAtual.xhtml
     * @return
     */
    public ModeloPagina getFormularioAcaoAtual() {
        return FabModelosPagina.FORMULARIO_PG_ACAO_ATUAL.getRegistro();
    }

    /**
     *
     *
     * modelo card totalmente personalizado
     *
     *
     * Parametros ui: iconeConteudo                 <br>
     * Areas definidas posssíveis:  <br>
     * <b> parametros</b>(onde o iconeConteudo deve ser configurado) <br>
     * <b>titulo, </b><br>
     * <b>subtitulo,</b> <br>
     * <b> conteudoDireita,</b> <br>
     * <b>conteudoCard </b> <br>
     * ex: * [ui:define name="conteudo"/] Meu conteudo [/ui:define] <br>
     *
     *
     *
     * @see FabModelosPagina#FORMULARIO_PERSONALIZADO
     *
     *  /resources/SBComp/template/basico/conteudo.xhtml
     *
     * @return
     */
    public ModeloPagina getFormularioPersonalizado() {
        return FabModelosPagina.FORMULARIO_PERSONALIZADO.getRegistro();
    }

    /**
     *
     * Areas definidas posssíveis:  <br>
     * <b>conteudo </b>
     *
     * ex: * [ui:define name="conteudo"/] Meu conteudo [/ui:define] <br>
     * /resources/SBComp/template/basico/formularioEntidadeListar.xhtml
     *
     * @see FabModelosPagina#FORMULARIO_PG_ENTIDADE_LISTAR
     *
     * @return
     */
    public ModeloPagina getFormularioListarGenerico() {
        return FabModelosPagina.FORMULARIO_PG_ENTIDADE_LISTAR.getRegistro();
    }

    /**
     *
     *
     * Areas definidas posssíveis:  <br>
     * <b>conteudoDireita </b><br>
     * <b>conteudoCard </b><br>
     * <b>conteudoExtraCard </b> <br>
     * ex: * [ui:define name="conteudo"/] Meu conteudo [/ui:define] <br> <br>
     *
     * /resources/SBComp/template/basico/conteudoEmBranco.xhtml
     *
     *
     * /resources/SBComp/template/basico/formularioAcaoAtual.xhtml
     *
     * @see FabModelosPagina#FORMULARIO_PG_ENTIDADE_EDITAR
     *
     * @return
     */
    public ModeloPagina getFormularioEditarGenerico() {
        return FabModelosPagina.FORMULARIO_PG_ENTIDADE_EDITAR.getRegistro();
    }

    /**
     *
     * Não tem nada no conteúdo em branco por enquanto apenas um include.
     *
     * Areas definidas posssíveis:  <br>
     * <b> conteudo </b> <br>
     * ex: * [ui:define name="conteudo"/] Meu conteudo [/ui:define] <br>
     * /resources/SBComp/template/basico/conteudoEmBranco.xhtml
     *
     * @see FabModelosPagina#CONTEUDO_EM_BRANCO
     *
     * @return
     */
    @Deprecated
    public ModeloPagina getConteudoEmBranco() {
        return FabModelosPagina.CONTEUDO_EM_BRANCO.getRegistro();
    }

}
