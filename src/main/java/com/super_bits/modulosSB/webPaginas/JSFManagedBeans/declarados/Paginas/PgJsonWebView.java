/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFManagedBeans.declarados.Paginas;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreJson;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringsMaiuculoMinusculo;
import com.super_bits.modulosSB.SBCore.UtilGeral.json.ErroProcessandoJson;
import com.super_bits.modulosSB.SBCore.modulos.Controller.ErroChamadaController;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfResposta;
import com.super_bits.modulosSB.SBCore.modulos.Controller.qualificadoresCDI.sessao.QlSessaoFacesContext;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.interfaces.ItfB_PaginaSimples;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.reflexao.anotacoes.InfoPagina;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.siteMap.MapaDeFormularios;
import com.super_bits.modulosSB.webPaginas.TratamentoDeErros.ErroGenericoProcessandoRespostaJson;
import com.super_bits.modulos.SBAcessosModel.view.FabAcaoPaginasDoSistema;
import com.super_bits.modulos.SBAcessosModel.view.InfoAcaoPaginaDoSistema;
import com.super_bits.modulosSB.webPaginas.controller.servlets.WebPaginasServlet;
import com.super_bits.modulosSB.webPaginas.controller.servlets.servletWebPaginas.ConfiguracoesDeFormularioPorUrl;
import com.super_bits.modulosSB.webPaginas.controller.servlets.servletWebPaginas.EstruturaDeFormulario;
import com.super_bits.modulosSB.webPaginas.controller.sessao.SessaoAtualSBWP;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWPJson;
import static com.super_bits.modulosSB.webPaginas.util.UtilSBWPJson.ATRIBUTO_JSON_SESSAO;
import static com.super_bits.modulosSB.webPaginas.util.UtilSBWPJson.getJsonBuilderSessao;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWPServletTools;
import jakarta.json.Json;
import jakarta.json.JsonObjectBuilder;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ComoSessao;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ComoAcaoDoSistema;

/**
 *
 * @author salvio
 */
@RequestScoped
@Named
@InfoAcaoPaginaDoSistema(acao = FabAcaoPaginasDoSistema.PAGINA_NATIVA_JSON_WEBVIEW_MB_GESTAO)
@InfoPagina(tags = "pgJsonWebView", nomeCurto = "pgJsonWebView")
public class PgJsonWebView implements Serializable {

    private ComoAcaoDoSistema acaoVinculada;

    @QlSessaoFacesContext
    @Inject
    private ComoSessao sessao;
    private EstruturaDeFormulario formulario;
    private ConfiguracoesDeFormularioPorUrl configuracoesDeUrl;

    @PostConstruct
    public void inicio() {

        configuracoesDeUrl = (ConfiguracoesDeFormularioPorUrl) UtilSBWPServletTools.getRequestAtribute(WebPaginasServlet.NOME_BEAN_REQUEST_CONFIG_URL);

    }

    public ComoAcaoDoSistema getAcaoVinculada() {
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
                ComoAcaoDoSistema acao = acaoVinculada.getAcaoDeGestaoEntidade().getSubAcaoByString(configuracoesDeUrl.getStringAcoes().get(0));
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
                            if (!resposta.getMensagens().isEmpty()) {
                                System.out.println("Erro de regra de negocio obtendo dados de sessao");
                                System.out.println(resposta.getMensagens().get(0).getMenssagem());
                            }

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
