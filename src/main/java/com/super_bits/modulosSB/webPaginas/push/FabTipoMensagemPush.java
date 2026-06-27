/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.super_bits.modulosSB.webPaginas.push;

import com.super_bits.modulosSB.SBCore.modulos.comunicacao.ComoDialogo;
import com.super_bits.modulosSB.SBCore.modulos.erpCore.ErpCarameloCore;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoEntidadeSimples;
import com.super_bits.modulosSB.SBCore.modulos.view.widgetsFormulario.WidgetsFormulario;
import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author salvio
 */
public enum FabTipoMensagemPush {

    ATUALIZAR_AREA,
    EXIBIR_DIALOGO_TRANSITORIO,
    EXECUTAR_JAVASCRIPT,
    ATUALIZAR_CAMPOS,
    VALIDAR_CAMPOS,
    ATUALIZAR_NOTIFICACOES;

    private Map<String, Object> getPaylouadPush() {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("tipo", this.toString());
        parametros.put("timestamp", System.currentTimeMillis());
        return parametros;
    }

    public Map<String, Object> gerarPayloadPush(ComoDialogo pDialogo) {
        Map<String, Object> parametros = getPaylouadPush();
        switch (this) {

            case EXIBIR_DIALOGO_TRANSITORIO:
                if (pDialogo.getPaginaInstanciaID() == null) {
                    throw new UnsupportedOperationException("Informe o instacia da página onde o dialogo será reinderizado");
                }
                parametros.put("paginaInstanciaID", pDialogo.getPaginaInstanciaID());
                parametros.put("mensagemTransitoria", ErpCarameloCore.CORE_PADRAO.getJsonEntidade((ComoEntidadeSimples) pDialogo).add("paginaInstanciaID", pDialogo.getPaginaInstanciaID()).build().toString());
                break;
            case ATUALIZAR_NOTIFICACOES:
                break;
            default:
                throw new AssertionError();
        }

        return parametros;

    }

    public Map<String, Object> gerarPayloadPush(WidgetsFormulario... pAreas) {

        Map<String, Object> parametros = getPaylouadPush();
        JsonArrayBuilder jba = Json.createArrayBuilder();

        switch (this) {
            case VALIDAR_CAMPOS:
            case ATUALIZAR_NOTIFICACOES:
                for (WidgetsFormulario area : pAreas) {
                    jba.add(area.getClientID());
                }
                break;
            default:
                throw new AssertionError();
        }
        parametros.put("areas", jba.build().toString());
        return parametros;
    }

    public Map<String, Object> gerarPayloadPush(ItfCampoInstanciado... pCamposinstanciados) {
        Map<String, Object> parametros = getPaylouadPush();
        JsonArrayBuilder jba = Json.createArrayBuilder();

        switch (this) {
            case VALIDAR_CAMPOS:
            case ATUALIZAR_NOTIFICACOES:
                for (ItfCampoInstanciado cpInst : pCamposinstanciados) {
                    jba.add(cpInst.getNomeCompostoIdentificador());
                }
                break;
            default:
                throw new AssertionError();
        }
        parametros.put("campos", jba.build().toString());
        return parametros;

    }

}
