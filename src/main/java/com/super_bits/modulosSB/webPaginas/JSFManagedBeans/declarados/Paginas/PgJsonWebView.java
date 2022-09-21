/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFManagedBeans.declarados.Paginas;

import com.sun.tools.internal.ws.wsdl.document.http.HTTPConstants;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.MapaAcoesSistema;
import com.super_bits.modulosSB.SBCore.UtilGeral.MapaDeAcoes;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreJson;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringsMaiuculoMinusculo;
import com.super_bits.modulosSB.SBCore.UtilGeral.json.ErroProcessandoJson;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.RespostaWebServiceAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.ErroChamadaController;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfResposta;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.qualificadoresCDI.sessao.QlSessaoFacesContext;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfSessao;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.MB_PaginaConversation;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.interfaces.ItfB_PaginaSimples;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.reflexao.anotacoes.InfoPagina;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.siteMap.MapaDeFormularios;
import com.super_bits.modulosSB.webPaginas.TratamentoDeErros.ErroGenericoProcessandoRespostaJson;
import com.super_bits.modulosSB.webPaginas.controller.paginasDoSistema.FabAcaoPaginasDoSistema;
import com.super_bits.modulosSB.webPaginas.controller.paginasDoSistema.InfoAcaoPaginaDoSistema;
import com.super_bits.modulosSB.webPaginas.controller.servlets.WebPaginasServlet;
import com.super_bits.modulosSB.webPaginas.controller.servlets.servletWebPaginas.ConfiguracoesDeFormularioPorUrl;
import com.super_bits.modulosSB.webPaginas.controller.servlets.servletWebPaginas.EstruturaDeFormulario;
import com.super_bits.modulosSB.webPaginas.controller.sessao.SessaoAtualSBWP;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWPJson;
import static com.super_bits.modulosSB.webPaginas.util.UtilSBWPJson.ATRIBUTO_JSON_SESSAO;
import static com.super_bits.modulosSB.webPaginas.util.UtilSBWPJson.getJsonBuilderSessao;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWPServletTools;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWP_JSFTools;
import jakarta.json.Json;
import jakarta.json.JsonObjectBuilder;
import jakarta.json.JsonValue;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

/**
 *
 * @author salvio
 */
@RequestScoped
@Named
@InfoAcaoPaginaDoSistema(acao = FabAcaoPaginasDoSistema.PAGINA_NATIVA_JSON_WEBVIEW_MB_GESTAO)
@InfoPagina(tags = "sessao", nomeCurto = "sessao")
public class PgJsonWebView implements Serializable {

    private ItfAcaoDoSistema acaoVinculada;

    @QlSessaoFacesContext
    @Inject
    private ItfSessao sessao;
    private EstruturaDeFormulario formulario;
    private ConfiguracoesDeFormularioPorUrl configuracoesDeUrl;

    @PostConstruct
    public void inicio() {

        configuracoesDeUrl = (ConfiguracoesDeFormularioPorUrl) UtilSBWPServletTools.getRequestAtribute(WebPaginasServlet.NOME_BEAN_REQUEST_CONFIG_URL);

    }

    public ItfAcaoDoSistema getAcaoVinculada() {
        return acaoVinculada;
    }

    public String getJson() {

        SessaoAtualSBWP sessaoSWP = (SessaoAtualSBWP) sessao;

        String viewSessaoID = FacesContext.getCurrentInstance().getApplication().getStateManager().getViewState(FacesContext.getCurrentInstance());
        formulario = MapaDeFormularios.getPaginaBySlug(configuracoesDeUrl.getStringGestao());
        if (formulario == null) {
            return UtilSBCoreJson.getTextoByJsonObjeect(UtilSBWPJson.JSON_FALHA_GERANDO_JSONVIEW("falha identificando chamada, utilizando tag: " + configuracoesDeUrl.getStringGestao()));
        }

        acaoVinculada = formulario.getAcaoGestaoVinculada();
        String email = sessao.getUsuario().getEmail();

        if (acaoVinculada.equals(FabAcaoPaginasDoSistema.PAGINA_NATIVA_JSON_WEBVIEW_MB_GESTAO.getRegistro())) {
            if (!configuracoesDeUrl.getStringAcoes().isEmpty()) {
                ItfAcaoDoSistema acao = acaoVinculada.getAcaoDeGestaoEntidade().getSubAcaoByString(configuracoesDeUrl.getStringAcoes().get(0));
                String parametros = configuracoesDeUrl.getCorpo();
                try {
                    if (!UtilSBWPServletTools.getRequestAtual().getMethod().equals("POST")) {
                        return UtilSBCoreJson.getTextoByJsonObjeect(UtilSBWPJson.JSON_FALHA_GERANDO_JSONVIEW("Esperado um método post"));

                    }
                    ItfResposta resposta = SBCore.getServicoController().getResposta(acao.getEnumAcaoDoSistema(), parametros);

                    if (resposta.isSucesso()) {
                        try {
                            return UtilSBCoreJson.getTextoByJsonObjeect(UtilSBWPJson.getJsonRespostaPadrao(resposta));
                        } catch (ErroProcessandoJson ex) {
                            FacesContext.getCurrentInstance().getExternalContext().setResponseStatus(418);
                            return UtilSBCoreJson.getTextoByJsonObjeect(UtilSBWPJson.JSON_FALHA_GERANDO_JSONVIEW("ação foi executada, mas houve um erro gerando json de resposta"));
                        }
                    } else {
                        FacesContext.getCurrentInstance().getExternalContext().setResponseStatus(403);
                        try {
                            return UtilSBCoreJson.getTextoByJsonObjeect(UtilSBWPJson.getJsonRespostaPadrao(resposta));
                        } catch (ErroProcessandoJson ex) {
                            FacesContext.getCurrentInstance().getExternalContext().setResponseStatus(418);
                            return UtilSBCoreJson.getTextoByJsonObjeect(UtilSBWPJson.JSON_FALHA_GERANDO_JSONVIEW("ação foi executada, mas houve um erro gerando json de resposta"));
                        }
                    }

                } catch (ErroChamadaController ex) {
                    FacesContext.getCurrentInstance().getExternalContext().setResponseStatus(403);
                    return UtilSBCoreJson.getTextoByJsonObjeect(UtilSBWPJson.JSON_FALHA_GERANDO_JSONVIEW("Erro localizando implementação da ação " + acao.getEnumAcaoDoSistema()));
                }

            } else {
                try {
                    JsonObjectBuilder jsonPadrao = Json.createObjectBuilder();
                    jsonPadrao.add(ATRIBUTO_JSON_SESSAO, getJsonBuilderSessao());
                    return UtilSBCoreJson.getTextoByJsonObjeect(jsonPadrao.build());
                } catch (ErroProcessandoJson ex) {
                    Logger.getLogger(PgJsonWebView.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }

        Class classeMB = MapaDeFormularios.getClasseMBByAcaoGestaoNomeUnico(acaoVinculada.getNomeUnico());

        if (classeMB != null) {
            try {
                String expressaoBean = "#{" + UtilSBCoreStringsMaiuculoMinusculo.getPrimeiraLetraMinuscula(classeMB.getSimpleName()) + "}";
                ItfB_PaginaSimples paginaDoContexto = (ItfB_PaginaSimples) UtilSBWPServletTools.getObjetoByInstanciadoViewScopedByExpressao(expressaoBean, classeMB);
                return paginaDoContexto.getJsonPagina();
            } catch (ErroGenericoProcessandoRespostaJson pErro) {
                return UtilSBCoreJson.getTextoByJsonObjeect(UtilSBWPJson.JSON_FALHA_GERANDO_JSONVIEW("Erro acessando: #{" + classeMB.getSimpleName() + "}\n causa:" + pErro.getMessage()));

            }
            //= (ItfB_PaginaSimples) UtilSBWPServletTools.getBeanByNamed(UtilSBCoreStringsMaiuculoMinusculo.getPrimeiraLetraMinuscula(classeMB.getSimpleName()), classeMB);

        }
        return "Chamada não Reconhecida";
    }
}
