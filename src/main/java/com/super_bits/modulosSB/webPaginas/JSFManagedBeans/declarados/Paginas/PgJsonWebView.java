/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFManagedBeans.declarados.Paginas;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.MapaAcoesSistema;
import com.super_bits.modulosSB.SBCore.UtilGeral.MapaDeAcoes;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreJson;
import com.super_bits.modulosSB.SBCore.UtilGeral.json.ErroProcessandoJson;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.qualificadoresCDI.sessao.QlSessaoFacesContext;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfSessao;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.MB_PaginaConversation;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.siteMap.MapaDeFormularios;
import com.super_bits.modulosSB.webPaginas.controller.paginasDoSistema.FabAcaoPaginasDoSistema;
import com.super_bits.modulosSB.webPaginas.controller.paginasDoSistema.InfoAcaoPaginaDoSistema;
import com.super_bits.modulosSB.webPaginas.controller.servlets.WebPaginasServlet;
import com.super_bits.modulosSB.webPaginas.controller.servlets.servletWebPaginas.ConfiguracoesDeFormularioPorUrl;
import com.super_bits.modulosSB.webPaginas.controller.servlets.servletWebPaginas.EstruturaDeFormulario;
import com.super_bits.modulosSB.webPaginas.controller.sessao.SessaoAtualSBWP;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWPServletTools;
import jakarta.json.JsonObjectBuilder;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

/**
 *
 * @author salvio
 */
@RequestScoped
@Named
@InfoAcaoPaginaDoSistema(acao = FabAcaoPaginasDoSistema.PAGINA_NATIVA_JSON_WEBVIEW_MB_GESTAO)
public class PgJsonWebView implements Serializable {

    private ItfAcaoDoSistema acaoVinculada;

    @QlSessaoFacesContext
    @Inject
    private ItfSessao sessao;
    private EstruturaDeFormulario formulario;

    @PostConstruct
    public void inicio() {

        ConfiguracoesDeFormularioPorUrl configuracoesDeUrl = (ConfiguracoesDeFormularioPorUrl) UtilSBWPServletTools.getRequestAtribute(WebPaginasServlet.NOME_BEAN_REQUEST_CONFIG_URL);
        String tagAcaoGestao = configuracoesDeUrl.getStringGestao();
        acaoVinculada = MapaAcoesSistema.getAcaoDoSistemaByNomeUnico(tagAcaoGestao);
        EstruturaDeFormulario formulario = MapaDeFormularios.getPaginaBySlug(tagAcaoGestao);

    }

    public ItfAcaoDoSistema getAcaoVinculada() {
        return acaoVinculada;
    }

    public String getJson() {
        //javax.faces.ViewState:-8236732204433259176:4251409787725462899

        SessaoAtualSBWP sessaoSWP = (SessaoAtualSBWP) sessao;

        String viewSessaoID = FacesContext.getCurrentInstance().getApplication().getStateManager().getViewState(FacesContext.getCurrentInstance());
        if (formulario != null) {
            acaoVinculada = formulario.getAcaoGestaoVinculada();
        }
        try {
            JsonObjectBuilder jsonResposta = UtilSBCoreJson.getJsonBuilderBySequenciaChaveValor(
                    "viewScopedStateID", viewSessaoID,
                    "tipoView", "Não Definida",
                    "urlHostSessao", sessaoSWP.getUrlHostDaSessao(),
                    "emailUsuario", sessao.getUsuario().getEmail(),
                    "dadosFormulario", "Não Definido",
                    "dados", "nãoDefinido"
            );
            String retorno = UtilSBCoreJson.getTextoByJsonObjeect(jsonResposta.build().asJsonObject());
            return retorno;
        } catch (ErroProcessandoJson ex) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Falha gerando visualização Json", ex);
            return "{'resultado': 'FalhaObtendoVisualizaçãoJson'}";
        }

    }

}
