/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.interfaces;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreJson;
import com.super_bits.modulosSB.SBCore.UtilGeral.json.ErroProcessandoJson;
import com.super_bits.modulosSB.webPaginas.JSFBeans.SBBeanModel.InfoMBAcao;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.B_Pagina;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWPJson;
import groovy.json.JsonBuilder;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

/**
 *
 * @author desenvolvedor
 */
public interface ItfB_PaginaSimples {

    /**
     * Por padrão o titulo da pagina é a descrição da ação vinculada a pagina
     *
     * @return Titulo da Pagina
     */
    public String getTitulo();

    /**
     *
     * Descreve a utilidade da pagina.
     *
     * Ajuda o usuário a encontrar esta pagina, e a entender o que ela faz
     *
     * @return descrição da pagina
     */
    public String getDescricao();

    /**
     *
     * Por motivos de segurança na reinderização dos ajax realizados via JSF, e
     * melhor organização cada Pggina (Managed Beans de paginas), possuem uma
     * pagina principal que executa includes das ações sendo executadas no
     * momento.
     *
     * O Recurso XHTML é o XHTMl fixo que nunca muda e é associado diretamenta a
     * ação vinculada ao managed bean
     *
     * @return Xhtml principal da pagina
     */
    public String getRecursoXHTML();

    /**
     *
     * A area de exibição selecionada é a área que será atualizada após executar
     * uma ação atraves do
     *
     * @see ItfB_Pagina#executarAcaoSelecionada() , ou do
     * executarAcaoSelecionada(Entidade)
     *
     * @return O id da area que será atualizada automaticamente
     */
    public String getIdAreaExbicaoAcaoSelecionada();

    //public Conversation getConversa();
    /**
     *
     * O xhtml da ação atual é o xhtml referente a ação do momento este xhtml é
     * diferente do
     *
     * @see ItfB_Pagina#getRecursoXHTML() , normalmente este é um xhtml filho de
     * uma ação de gestao (vinculada diretamente ao pg) e está vinculado a uma
     * ação de formulario
     *
     *
     * @return
     */
    public String getXhtmlAcaoAtual();

    /**
     *
     * Identificação numérica da pagina, não precisa ser único (Criado para
     * utilizar em menus)
     *
     * @return
     */
    public int getId();

    public void executarAcaoSelecionada();

    /**
     * Método criando antes de existir o Objeto ItfAcaoDoSistema, TODO:Criar
     * InfMBAção compatível
     *
     * @return
     * @deprecated
     */
    @Deprecated
    public List<InfoMBAcao> getInfoAcoes();

    public List<B_Pagina.BeanDeclarado> getInfoBeans();

    public Map<String, String> getInfoIds();

    public default ItfB_Pagina getComoPaginaDeGestao() {
        return getComoFormularioWeb();

    }

    public default ItfB_PaginaComEntityManager getComoPaginaComEntityManager() {
        try {
            if (this instanceof ItfB_PaginaComEntityManager) {
                return (ItfB_PaginaComEntityManager) this;
            } else {
                throw new UnsupportedOperationException(this.getClass().getSimpleName() + " Não implementa " + ItfB_Pagina.class.getSimpleName());
            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro obtendo pagina como pagnina com entity manager", t);
            return null;
        }

    }

    public default ItfB_Pagina getComoFormularioWeb() {
        try {
            if (isPaginaDeGestao()) {
                return (ItfB_Pagina) this;
            } else {
                throw new UnsupportedOperationException(this.getClass().getSimpleName() + " Não implementa " + ItfB_Pagina.class.getSimpleName());
            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro obtendo pagina como gestão", t);
            return null;
        }
    }

    public default boolean isPaginaDeGestao() {
        return this instanceof ItfB_Pagina;
    }

    /**
     *
     * Caso a página atual não seja compatível com gestão de entidade, um erro
     * será gerado
     *
     * @return Pagina como Pagina de entidade
     */
    public default ItfPaginaGerenciarEntidade<?> getComoPaginaEntidade() {
        try {
            ItfPaginaGerenciarEntidade paginaDeEntidade = (ItfPaginaGerenciarEntidade) this;
            return paginaDeEntidade;
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "A pagina" + this.getClass().getSimpleName() + " não implementa Pagina de Entidade", t);
            return null;
        }
    }

    public default boolean isUmaPaginaGestaoEntidade() {
        return (this instanceof ItfPaginaGerenciarEntidade);
    }

    public default String getJsonPagina() {
        JsonObjectBuilder jsonPagina;
        try {
            jsonPagina = UtilSBWPJson.getJsonBuilderPaginaPadrao(this);
            return UtilSBCoreJson.getTextoByJsonObjeect(jsonPagina.build());
        } catch (ErroProcessandoJson ex) {
            return UtilSBCoreJson.getTextoByJsonObjeect(UtilSBWPJson.JSON_FALHA_OBTENDO_CONTEXTO);
        }

    }

}
