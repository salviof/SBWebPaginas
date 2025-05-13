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
import jakarta.json.JsonObjectBuilder;
import java.util.List;
import java.util.Map;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import com.super_bits.modulosSB.SBCore.modulos.view.formulario.ItfFormularioSimples;

/**
 *
 * @author desenvolvedor
 */
public interface ItfB_PaginaSimples extends ItfFormularioSimples {

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
