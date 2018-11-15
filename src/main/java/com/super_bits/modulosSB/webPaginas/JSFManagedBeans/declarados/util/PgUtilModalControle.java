/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFManagedBeans.declarados.util;

import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.interfaces.ItfB_Pagina;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.enterprise.context.SessionScoped;
import org.jboss.weld.interceptor.util.proxy.TargetInstanceProxy;

/**
 *
 * Este Bean de sessão foi criado para permitir a comunicação entre o formidável
 * FrameWork de Dialogo do Primefaces, e a view que executou a chamada do modal.
 *
 * Através dele é possível recuperar a partir de um registro único o Bean
 * chamado no modal
 *
 *
 *
 *
 * @author Sálvio Furbino
 */
@SessionScoped
public class PgUtilModalControle implements Serializable {

    private final Map<String, ItfB_Pagina> mapaViews;

    public PgUtilModalControle() {
        mapaViews = new HashMap<>();
    }

    public void adicionarAoMapa(ItfB_Pagina pPagina) {
        if (pPagina instanceof TargetInstanceProxy) {
            pPagina = (ItfB_Pagina) ((TargetInstanceProxy) pPagina).getTargetInstance();
        }
        mapaViews.put(pPagina.toString(), pPagina);
    }

    public void removerReferencia(ItfB_Pagina pPagina) {
        if (pPagina != null) {
            if (pPagina instanceof TargetInstanceProxy) {
                pPagina = (ItfB_Pagina) ((TargetInstanceProxy) pPagina).getTargetInstance();
            }
            String identificador = pPagina.toString();
            mapaViews.remove(identificador);
        }

    }

    public ItfB_Pagina getPaginaByID(String codigo) {
        if (mapaViews.containsKey(codigo)) {
            return mapaViews.get(codigo);
        } else {
            return mapaViews.values().iterator().next();
        }
    }

}
