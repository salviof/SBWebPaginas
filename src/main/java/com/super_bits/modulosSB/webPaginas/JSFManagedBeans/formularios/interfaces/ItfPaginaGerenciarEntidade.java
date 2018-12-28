/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.interfaces;

import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoControllerEntidade;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoFormularioEntidade;
import java.util.List;

/**
 *
 * @author cristopher
 */
public interface ItfPaginaGerenciarEntidade<T> extends ItfB_Pagina {

    /**
     *
     * Caso o managed bean da pagina possua uma ação de listagem configurada em
     * seu constructor esta ação pode ser obtida por aqui
     *
     * ex utilizacao:
     * <SBComp:botaoDeAcaoPagina acao=#{PgClientes.acaoListarRegistro} />
     *
     * @return Ação listar registro da pagina de gestão
     */
    public ItfAcaoFormularioEntidade getAcaoListarRegistros();

    /**
     *
     * Caso o managed bean da pagina possua uma ação para formulário de novo
     * registro configurada em seu constructor esta ação pode ser obtida por
     * aqui
     *
     * ex utilizacao:
     * <SBComp:botaoDeAcaoPagina acao=#{PgClientes.acaoNovoRegistro} />
     *
     * @return Ação novo registro
     */
    public ItfAcaoFormularioEntidade getAcaoNovoRegistro();

    /**
     * Caso o managed bean da pagina possua uma ação para salvar alterações
     * configurada em seu constructor esta ação pode ser obtida por aqui
     *
     * ex utilizacao:
     * <SBComp:botaoDeAcaoPagina acao=#{PgClientes.acaoSalvarAlteracoes} />
     *
     *
     *
     * @return Ação salvar alterações da entidade
     */
    public ItfAcaoDoSistema getAcaoSalvarAlteracoes();

    /**
     *
     * Caso o managed bean da pagina possua uma lista de ações de registro pré
     * configuradas em seu constructor esta ação pode ser obtida por aqui
     *
     * ex utilizacao: <SBComp:seletorDeAcoes
     * opcoes=#{PgClientes.acaoAcoesRegistros} ../>*
     *
     * @return Ações do sistema associadas ao registro selecionado
     */
    public List<ItfAcaoDoSistema> getAcoesRegistros();

    /**
     *
     *
     *
     * @return entidade Selecionada
     */
    public T getEntidadeSelecionada();

    /**
     *
     * Lista de entidades para executar alguma ação
     *
     * @return lista de entidades disponíveis no managed bean
     */
    public List<T> getEntidadesListadas();

    /**
     *
     * @return True se Modo novo Registro
     */
    public boolean isNovoRegistro();

    /**
     *
     * @return True se pode editar
     */
    public boolean isPodeEditar();

    /**
     *
     * @return True se o managed bean possuir formulario de visualização de
     * entidade
     */
    public boolean isTemVisualizar();

    /**
     *
     * @return Verdadeiro se o managed bean possuir alçai vinculada a formulario
     * de novo registro
     */
    public boolean isTemNovo();

    /**
     *
     * @return Verdadeiro se possui ação de formulario do tipo generico edição
     * do registro
     */
    public boolean isTemEditar();

    /**
     *
     * @return Verdadeiro se o managed ben possui formulario de controller para
     * alteração de status
     */
    public boolean isTemAlterarStatus();

    public void setAcaoSelecionada(ItfAcaoDoSistema acaoSelecionada);

    public void setEntidadeSelecionada(T entidadeSelecionada);

    public void setEntidadesListadas(List<T> entidadesListadas);

    /**
     *
     * Executa a ação selecionada, enviando o Item selecionado como parametro
     *
     * @param pCompradorSelecionado
     */
    public void executarAcao(T pCompradorSelecionado);

    /**
     * Atualiza a lista de dados
     */
    public void listarDados();

    /**
     *
     * @return Verdadeiro se possui possibilidade de filtro por pesquisa
     */
    public boolean isTemPesquisa();

    public ItfAcaoFormularioEntidade getAcaoVisualizar();

    public ItfAcaoControllerEntidade getAcaoAlterarStatus();

    public ItfAcaoFormularioEntidade getAcaoEditar();

    /**
     *
     * @return Verdadeiro se bloequeado para edição
     */
    public boolean isSomenteLeitura();

    public void atualizarEntidadeSelecionada();

}
