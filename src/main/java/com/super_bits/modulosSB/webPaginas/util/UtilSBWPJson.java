/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.util;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreJson;
import com.super_bits.modulosSB.SBCore.UtilGeral.json.ErroProcessandoJson;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfResposta;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfRespostaAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Mensagens.FabMensagens;
import com.super_bits.modulosSB.SBCore.modulos.Mensagens.FabTipoAgenteDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Mensagens.ItfMensagem;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ComoEntidadeSimples;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.interfaces.ItfB_PaginaSimples;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

/**
 *
 * @author salvio
 */
public class UtilSBWPJson {

    public static JsonObjectBuilder getJsonBuilderInfoPagina(ItfB_PaginaSimples pPagina) throws ErroProcessandoJson {
        String strJsonAcaoSelecionada = null;
        if (pPagina.getComoFormularioWeb().getAcaoSelecionada() != null) {
            strJsonAcaoSelecionada = pPagina.getComoFormularioWeb().getAcaoSelecionada().getNomeUnico();
        }
        JsonObjectBuilder jsonInfoPagina = UtilSBCoreJson.getJsonBuilderBySequenciaChaveValor(
                "titulo", pPagina.getTitulo(),
                "acaoVinculada", pPagina.getComoFormularioWeb().getAcaoVinculada().getNomeUnico(),
                "subAcaoSelecionada", strJsonAcaoSelecionada
        );
        return jsonInfoPagina;
    }

    public static JsonObjectBuilder getJsonBuilderSessao() throws ErroProcessandoJson {
        String usuario = SBCore.getUsuarioLogado().getEmail();
        String sessionId = UtilSBWP_JSFTools.getIDJsession();
        String idViewState = UtilSBWP_JSFTools.getIDViewFeceScoped();

        JsonObjectBuilder jsonDadosSessao = null;

        jsonDadosSessao = UtilSBCoreJson.getJsonBuilderBySequenciaChaveValor(
                "jsessionId", sessionId,
                "viewStateId", idViewState,
                "idViewStateRoot", idViewState,
                "emailUsuario", usuario,
                "grupoUsuario", SBCore.getUsuarioLogado().getGrupo().getNomeUnico(),
                "nomeParametroViewState", "javax.faces.ViewState",
                "nomeParametroSessao", "JSESSIONID"
        );

        jsonDadosSessao.add("identificado", SBCore.getServicoSessao().getSessaoAtual().isIdentificado());
        jsonDadosSessao.add("sucesso", true);
        return jsonDadosSessao;
    }

    public static JsonObjectBuilder getJsonBuildeEntidadeSelecionada(ItfB_PaginaSimples pPagina) throws ErroProcessandoJson {
        throw new ErroProcessandoJson("Json padrão entidade selecionada ainda não foi defido");

    }

    public static JsonObjectBuilder getJsonBuilderResposta(ItfResposta pResposta) throws ErroProcessandoJson {
        JsonObjectBuilder jsonPadrao = Json.createObjectBuilder();
        jsonPadrao.add("sucesso", pResposta.isSucesso());
        jsonPadrao.add("resultado", pResposta.getResultado().toString());

        if (pResposta instanceof ItfRespostaAcaoDoSistema) {
            ItfRespostaAcaoDoSistema respAcao = (ItfRespostaAcaoDoSistema) pResposta;
            jsonPadrao.add("acao", respAcao.getAcaoVinculada().getNomeUnico());
            if (respAcao.getAcaoProximoFormulario() != null) {
                jsonPadrao.add("formRedirecionamento", respAcao.getAcaoProximoFormulario().getNomeUnico());
            }
        }
        if (pResposta.getRetorno() != null) {

            try {
                Object retorno = pResposta.getRetorno();
                if (retorno instanceof JsonObject) {
                    jsonPadrao.add("retorno", (JsonObject) retorno);
                }
                if (retorno instanceof String) {
                    jsonPadrao.add("retorno", retorno.toString());
                }
                if (retorno instanceof ComoEntidadeSimples) {
                    Long codigo = ((ComoEntidadeSimples) retorno).getId();
                    jsonPadrao.add("retorno", UtilSBCoreJson.getJsonStringBySequenciaChaveValor("id", codigo));
                }
            } catch (Throwable t) {

            }

        }

        JsonArrayBuilder mensagensJsonArrayBuilder = Json.createArrayBuilder();
        for (ItfMensagem mensagem : pResposta.getMensagens()) {
            JsonObjectBuilder mensagemJson = Json.createObjectBuilder();
            mensagemJson.add("texto", mensagem.getMenssagem());
            mensagemJson.add("tipo", mensagem.getTipoDeMensagem().toString());
            mensagemJson.add("agenteDestino", mensagem.getTipoDestinatario().toString());
            mensagensJsonArrayBuilder.add(mensagemJson.build());
        }
        if (pResposta.isSucesso() && pResposta.getMensagens().isEmpty()) {
            JsonObjectBuilder mensagemJson = Json.createObjectBuilder();
            mensagemJson.add("texto", "ok");
            mensagemJson.add("tipo", FabMensagens.AVISO.toString());
            mensagemJson.add("agenteDestino", FabTipoAgenteDoSistema.USUARIO.toString());
            mensagensJsonArrayBuilder.add(mensagemJson.build());
        }

        JsonArray mensagensJsonArray = mensagensJsonArrayBuilder.build();
        if (!mensagensJsonArray.isEmpty()) {
            jsonPadrao.add("mensagens", mensagensJsonArray);
        }

        return jsonPadrao;
    }

    public static JsonObject getJsonRespostaPadrao(ItfResposta pResposta) throws ErroProcessandoJson {
        JsonObjectBuilder jsonPadrao = Json.createObjectBuilder();
        jsonPadrao.add(ATRIBUTO_JSON_SESSAO, getJsonBuilderSessao().build());
        jsonPadrao.add(ATRIBUTO_JSON_RESPOSTA_CONTROLLER, getJsonBuilderResposta(pResposta));
        return jsonPadrao.build();
    }

    public static JsonObjectBuilder getJsonBuilderPaginaPadrao(ItfB_PaginaSimples pPagina) throws ErroProcessandoJson {
        JsonObjectBuilder jsonPadrao = Json.createObjectBuilder();
        jsonPadrao.add(ATRIBUTO_JSON_SESSAO, getJsonBuilderSessao().build());
        jsonPadrao.add(ATRIBUTO_JSON_PAGINA, getJsonBuilderInfoPagina(pPagina).build());
        return jsonPadrao;
    }

    public static final String ATRIBUTO_JSON_SESSAO = "dadosSessao";
    public static final String ATRIBUTO_JSON_PAGINA = "infoPagina";
    public static final String ATRIBUTO_JSON_RESPOSTA_CONTROLLER = "resposta";
    private static final String FRASE_ERRO_PADRAO = "ERRO NÃO TRATADO, GERANDO JSONVIEW ";

    public static final JsonObject JSON_FALHA_OBTENDO_CONTEXTO = BUILD_FALHA_GERANDO_REPRESENTACAO_VIEW();

    private static JsonObject BUILD_FALHA_GERANDO_REPRESENTACAO_VIEW() {
        return JSON_FALHA_GERANDO_JSONVIEW(FRASE_ERRO_PADRAO);
    }

    public static JsonObject JSON_FALHA_GERANDO_JSONVIEW(String pDescricaoErro) {
        return JSON_BUILDER_FALHA_GERANDO_JSONVIEW(pDescricaoErro).build();

    }

    public static JsonObjectBuilder JSON_BUILDER_FALHA_GERANDO_JSONVIEW(String pDescricaoErro) {
        JsonObjectBuilder jsonDadosSessao = Json.createObjectBuilder();
        jsonDadosSessao.add("sucesso", false);
        jsonDadosSessao.add("erro", pDescricaoErro);
        JsonObjectBuilder jsonPadraoFalha = Json.createObjectBuilder();
        jsonPadraoFalha.add(ATRIBUTO_JSON_SESSAO, jsonDadosSessao.build());
        return jsonPadraoFalha;
    }

}
