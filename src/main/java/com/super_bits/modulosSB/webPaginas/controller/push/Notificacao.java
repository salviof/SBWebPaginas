/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulosSB.webPaginas.controller.push;

import com.super_bits.modulosSB.SBCore.modulos.comunicacao.ItfComunicacao;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import org.primefaces.json.JSONObject;

/**
 *
 * @author SalvioF
 */
public class Notificacao implements Serializable {

    private String mensagemJson;
    private String codigoConversa;
    private boolean temCodigoConversa;
    private boolean temMensagem;

    @Deprecated
    private Notificacao() {

    }

    public Notificacao(FacesMessage pMensagem) {
        temCodigoConversa = false;
        temMensagem = true;
        mensagemJson = new JSONObject(pMensagem).toString();
    }

    public Notificacao(ItfComunicacao pComunicacao) {
        temCodigoConversa = true;
        temMensagem = false;
        codigoConversa = pComunicacao.getCodigoSelo();
    }

    public String getMensagemJson() {
        return mensagemJson;
    }

    public String getCodigoConversa() {
        return codigoConversa;
    }

    public boolean isTemCodigoConversa() {
        return temCodigoConversa;
    }

    public boolean isTemMensagem() {
        return temMensagem;
    }

}
