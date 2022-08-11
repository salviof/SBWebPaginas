/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFManagedBeans.declarados.Paginas;

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
import com.super_bits.modulosSB.webPaginas.util.UtilSBWPServletTools;
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
            return "{'erro': 'falha identificando chamada, utilizando tag: " + configuracoesDeUrl.getStringGestao() + " '}";
        }

        acaoVinculada = formulario.getAcaoGestaoVinculada();
        String email = sessao.getUsuario().getEmail();

        JsonObjectBuilder jsonResposta;
        try {
            jsonResposta = UtilSBCoreJson.getJsonBuilderBySequenciaChaveValor(
                    "viewScopedStateID", viewSessaoID,
                    "tipoView", "Não Definida",
                    "urlHostSessao", sessaoSWP.getUrlHostDaSessao(),
                    "emailUsuario", email
            //         "dadosFormulario", "Não Definido",
            //"dados", "nãoDefinido"
            );
            jsonResposta.add("identificado", sessaoSWP.isIdentificado());
        } catch (ErroProcessandoJson ex) {
            return "{'erro': 'falha gerando json'}";
        }

        if (acaoVinculada.equals(FabAcaoPaginasDoSistema.PAGINA_NATIVA_JSON_WEBVIEW_MB_GESTAO.getRegistro())) {
            if (!configuracoesDeUrl.getStringAcoes().isEmpty()) {
                ItfAcaoDoSistema acao = acaoVinculada.getAcaoDeGestaoEntidade().getSubAcaoByString(configuracoesDeUrl.getStringAcoes().get(0));
                String parametros = configuracoesDeUrl.getCorpo();
                try {
                    if (!UtilSBWPServletTools.getRequestAtual().getMethod().equals("POST")) {
                        return "{'erro': 'Esperado um método post'}";
                    }
                    ItfResposta resposta = SBCore.getServicoController().getResposta(acao.getEnumAcaoDoSistema(), parametros);

                    if (resposta.isSucesso()) {
                        return "{'resultado': '" + ItfResposta.Resultado.SUCESSO.toString() + "'}";
                    } else {
                        return "{'erro': 'a ação falou  ao ser executada " + resposta.getMensagens().get(0).getMenssagem() + " '}";

                    }

                } catch (ErroChamadaController ex) {
                    return "{'erro': 'Erro localizando metodo'}";
                }

            } else {
                return UtilSBCoreJson.getTextoByJsonObjeect(jsonResposta.build());
            }

        }

        Class classeMB = MapaDeFormularios.getClasseMBByAcaoGestaoNomeUnico(acaoVinculada.getNomeUnico());

        if (classeMB != null) {
            try {
                String expressaoBean = "#{" + UtilSBCoreStringsMaiuculoMinusculo.getPrimeiraLetraMinuscula(classeMB.getSimpleName()) + "}";
                ItfB_PaginaSimples paginaDoContexto = (ItfB_PaginaSimples) UtilSBWPServletTools.getObjetoByInstanciadoViewScopedByExpressao(expressaoBean, classeMB);
                return paginaDoContexto.getJsonPagina();
            } catch (ErroGenericoProcessandoRespostaJson pErro) {
                return "{erro: '" + pErro.getMessage() + "'}";
            }
            //= (ItfB_PaginaSimples) UtilSBWPServletTools.getBeanByNamed(UtilSBCoreStringsMaiuculoMinusculo.getPrimeiraLetraMinuscula(classeMB.getSimpleName()), classeMB);

        }
        return "Chamada não Reconhecida";
    }
}
