/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.controller.push;

import javax.faces.application.FacesMessage;
import org.primefaces.json.JSONObject;
import org.primefaces.push.Decoder;
import org.primefaces.push.Encoder;

/**
 *
 * @author SalvioF
 */
public class NotificacaoDecoder implements Decoder<String, Notificacao> {

    @Override
    public Notificacao decode(String s) {
        String[] userAndMessage = s.split(":");
        FacesMessage fMessage = new FacesMessage(userAndMessage[0]);
        if (userAndMessage.length >= 2) {
            return new Notificacao(fMessage);
        } else {
            return new Notificacao(fMessage);
        }

    }

}
