/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.controller.listenners;

import com.sun.faces.application.view.ViewScopeManager;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 *
 * @author sfurbino
 */
public class SessaoHttpListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent hse) {
        Object viewmap = hse.getSession().getAttribute(ViewScopeManager.ACTIVE_VIEW_MAPS_SIZE);
        //A configuração do web.inf é ignorada, não sabemos o motivo, sem essa configuração imposta não executar o fechar pagina das views, e deixa muito entitymanager aberto, causando tomanyconnections no banco de dados
        hse.getSession().setAttribute(ViewScopeManager.ACTIVE_VIEW_MAPS_SIZE, 2);

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent hse) {

    }

}
