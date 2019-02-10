/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulosSB.webPaginas.controller.push;

import javax.faces.application.FacesMessage;

import org.primefaces.push.EventBus;
import org.primefaces.push.EventBusFactory;
import org.primefaces.push.RemoteEndpoint;
import org.primefaces.push.annotation.OnMessage;
import org.primefaces.push.annotation.OnOpen;
import org.primefaces.push.annotation.PathParam;
import org.primefaces.push.annotation.PushEndpoint;

/**
 *
 * @author SalvioF
 */
@PushEndpoint("/mensagens/{pagina}/{grupo}/{usuarioID}")
public abstract class NotificacoesPushAbstrato {

    @PathParam(value = "pagina")
    private String pagina = "*";
    @PathParam(value = "grupo")
    private String grupo = "*";
    @PathParam(value = "usuario")
    private String usuario = "*";

    @OnOpen
    public void onOpen(RemoteEndpoint r, EventBus eventBus) {
        System.out.println(r.path());
        String[] caminhoSplit = r.path().split("/");
        int indice = 0;
        for (String parte : caminhoSplit) {
            switch (indice) {
                case 2:
                    pagina = parte;
                case 3:
                    grupo = parte;
                case 4:
                    usuario = parte;
            }
            indice++;

        }

        //System.out.println("Entrou " + usuario);
        //EventBus evento = EventBusFactory.getDefault().eventBus();
        //  evento.publish("/mensagens/*/*/*", new Notificacao(new FacesMessage(String.format(" Sessao de  '%s' foi iniciada", usuario))));
    }

    @OnMessage(encoders = NotificacaoEncoder.class, decoders = NotificacaoDecoder.class)
    public Notificacao onMessage(Notificacao pNotificacao) {
        if (pNotificacao.isTemCodigoConversa()) {
            System.out.println(pNotificacao.getMensagemJson());
        }

        return pNotificacao;
    }

}
