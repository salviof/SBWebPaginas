/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.SBBeanModel.modelosPagina;

import com.super_bits.modulosSB.SBCore.modulos.fabrica.ItfFabrica;

/**
 *
 * @author salvioF
 */
public enum FabModelosPagina implements ItfFabrica {

    /**
     *
     *
     * Estrutura básica de um site, com importação de todos os javscripts e css
     * nescessários para execução dos comandos básicos,possui estrutura de topo,
     * conteúdo e rodapé
     *
     * /resources/SBComp/template/basico/modeloGeral.xhtml
     *
     *
     */
    MODELO_GERAL,
    /**
     *
     * O mesmo do modelo geral sem cabeçalho e rodabe
     * /resources/SBComp/template/basico/modeloAnonimo.xhtml
     *
     * @see FabModelosPagina#MODELO_GERAL
     */
    MODELO_GERAL_ANONIMO,
    /**
     * Modelo padrão para modais
     */
    MODELO_GERAL_MODAL,
    /**
     *
     *
     * /resources/SBComp/template/basico/conteudo.xhtml
     *
     */
    FORMULARIO_PERSONALIZADO,
    /**
     *
     *
     * /resources/SBComp/template/basico/formularioEntidadeListar.xhtml
     *
     */
    FORMULARIO_PG_ENTIDADE_LISTAR,
    /**
     *
     *
     * /resources/SBComp/template/basico/formularioAcaoAtual.xhtml
     *
     */
    FORMULARIO_PG_ENTIDADE_EDITAR,
    /**
     *
     *
     * /resources/SBComp/template/basico/formularioAcaoAtual.xhtml
     */
    FORMULARIO_PG_ACAO_ATUAL,
    /**
     *
     *
     * /resources/SBComp/template/basico/formularioEntidadeAcaoAtual.xhtml
     */
    FORMULARIO_PG_ENTIDADE_ACAO_ATUAL,
    /**
     *
     *  /resources/SBComp/template/basico/conteudoEmBranco.xhtml
     */
    @Deprecated
    CONTEUDO_EM_BRANCO;

    @Override
    public ModeloPagina getRegistro() {
        ModeloPagina mp = new ModeloPagina();
        mp.setId((long) this.ordinal());
        switch (this) {
            case MODELO_GERAL:
                mp.setNome("Modelo Geral");
                mp.setDescricao("Inclui, toda estrutura basica de CSS topo com menu, conteúdo e rodapé");
                mp.getAreas().add("conteudo");
                mp.getAreas().add("topo");
                mp.getAreas().add("rodape");
                mp.setXhtmlVinculado("/resources/SBComp/template/basico/modeloGeral.xhtml");
                break;
            case MODELO_GERAL_ANONIMO:
                mp.setNome("Modelo Geral Anonimo");
                mp.setDescricao("Inclui o toda estrutura basica de css, topo sem menu e conteúdo");
                mp.getAreas().add("divConteudo");
                mp.setXhtmlVinculado("/resources/SBComp/template/basico/modeloAnonimo.xhtml");
                break;
            case FORMULARIO_PERSONALIZADO:
                mp.setNome("Conteúdo personalizado ");
                mp.setDescricao("inclui  (Icone, Cabeçalho, botão a Direita e conteúdo) é preciso setar o parametro  UI iconeConteudo  na area de parametros");
                mp.getAreas().add("parametros");
                mp.getAreas().add("iconeConteudo");
                mp.getAreas().add("titulo");
                mp.getAreas().add("subtitulo");
                mp.getAreas().add("conteudoDireita");
                mp.getAreas().add("conteudoCard");
                mp.setXhtmlVinculado("/resources/SBComp/template/basico/conteudo.xhtml");
                break;
            case FORMULARIO_PG_ENTIDADE_LISTAR:
                mp.setNome("Conteúdo listar Automático");
                mp.setDescricao("Apresenta listar automatico, não possui areas para serem devinidas, precisa ser usuaro em uma Pagina do tipo Entidade");
                mp.setXhtmlVinculado("/resources/SBComp/template/basico/formularioEntidadeListar.xhtml");
                break;
            case FORMULARIO_PG_ACAO_ATUAL:
                mp.setNome("Formulario da ação Atual");
                mp.setDescricao("Mostra o formato padrão de formulário, contendo o icone da ação atual, o titulo, como o nome da ação,"
                        + " o subtitulo a descrição da ação");
                mp.getAreas().add("conteudo");
                mp.getAreas().add("direita");
                mp.setXhtmlVinculado("/resources/SBComp/template/basico/formularioAcaoAtual.xhtml");
                break;
            case FORMULARIO_PG_ENTIDADE_ACAO_ATUAL:
                mp.setDescricao("Mostra o formato padrão de formulário, contendo o icone da ação atual, o titulo, como o nome da ação,"
                        + " o subtitulo a descrição da ação, e o botão da direita definido como: novo em caso de listar e pagina.temNovo == true, e listar nos outros casos ");
                mp.getAreas().add("conteudo");
                mp.setXhtmlVinculado("/resources/SBComp/template/basico/formularioEntidadeAcaoAtual.xhtml");
                break;
            case FORMULARIO_PG_ENTIDADE_EDITAR:
                mp.setXhtmlVinculado("/resources/SBComp/template/basico/formularioEntidadeEditar.xhtml");
                break;
            case CONTEUDO_EM_BRANCO:
                mp.setDescricao("Um formulario em branco");
                mp.getAreas().add("conteudo");
                mp.setXhtmlVinculado("/resources/SBComp/template/basico/exemplo/conteudoEmBranco.xhtml");
                break;
            case MODELO_GERAL_MODAL:
                mp.setDescricao("Modelo Geral Modais");
                mp.getAreas().add("conteudo");
                mp.setXhtmlVinculado("/resources/SBComp/template/basico/modeloGeralModal.xhtml");
                break;
            default:
                throw new AssertionError(this.name());

        }
        return mp;
    }

}
