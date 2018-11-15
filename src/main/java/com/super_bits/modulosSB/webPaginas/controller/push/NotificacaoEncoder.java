/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.controller.push;

import com.google.gson.Gson;
import org.primefaces.push.Encoder;

/**
 *
 * @author SalvioF
 */
public class NotificacaoEncoder
        implements Encoder<Notificacao, String> {

    @Override
    public String encode(Notificacao s) {
        Gson gson = new Gson();
        return gson.toJson(s, Notificacao.class);
    }
}
