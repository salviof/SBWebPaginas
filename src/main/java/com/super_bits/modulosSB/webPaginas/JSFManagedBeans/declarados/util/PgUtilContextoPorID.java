/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.super_bits.modulosSB.webPaginas.JSFManagedBeans.declarados.util;

import com.super_bits.modulosSB.SBCore.modulos.objetos.dialogo.resposta.RespostaComunicacao;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.B_Pagina;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.interfaces.ItfB_Pagina;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.interfaces.ItfPaginaAtual;
import java.io.Serializable;
import java.util.Enumeration;
import java.util.Map;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/*
 *
 * @author salvio
 */
@Named
@RequestScoped
public class PgUtilContextoPorID implements Serializable {

    private static final String CHAVE_VIEW_MAPS
            = "com.sun.faces.application.view.activeViewMaps";
    private static final String CHAVE_VIEW_CONTEXTS
            = "com.sun.faces.application.view.activeViewContexts";
    private static final String PARAM_PAGINA_INSTANCIA_ID
            = "paginaInstanciaID";

    @Inject
    private HttpServletRequest request;

    @Inject
    private ItfPaginaAtual paginaatual;
    // ── cache — busca feita só uma vez por request ───────────
    private Object paginaDoContextoCache;
    private String escopoEncontrado;
    private boolean buscaRealizada = false;

    // ── métodos públicos ─────────────────────────────────────
    public Object getPaginaDoContexto() {
        if (!buscaRealizada) {
            realizarBusca();
        }
        if (request.getAttribute("respostaObj") != null) {

            RespostaComunicacao r = (RespostaComunicacao) request.getAttribute("respostaObj");

            String codigoDialogo = request.getParameter("codigoSelo");
            ((ItfB_Pagina) paginaDoContextoCache).getRespostasDialogosTransitorios().put(codigoDialogo, r);
        }

        return paginaDoContextoCache;
    }

    public <T> T getPaginaDoContexto(Class<T> classe) {
        Object bean = getPaginaDoContexto();

        return classe.isInstance(bean) ? classe.cast(bean) : null;
    }

    public boolean isPaginaEncontrada() {
        return getPaginaDoContexto() != null;
    }

    public String getEscopoEncontrado() {
        if (!buscaRealizada) {
            realizarBusca();
        }
        return escopoEncontrado;
    }

    public String getPaginaInstanciaID() {
        return request.getParameter(PARAM_PAGINA_INSTANCIA_ID);
    }

    // ── busca ────────────────────────────────────────────────
    private void realizarBusca() {
        buscaRealizada = true;

        String paginaInstanciaID = request.getParameter(PARAM_PAGINA_INSTANCIA_ID);

        if (paginaInstanciaID == null || paginaInstanciaID.isEmpty()) {

            if (paginaDoContextoCache == null) {
                try {
                    paginaInstanciaID = ((B_Pagina) paginaatual.getInfoPagina()).getPaginaInstanciaID();
                } catch (Throwable t) {

                }
            }
            if (paginaInstanciaID == null) {
                return;
            }
        }

        // 1. @ViewScoped — Weld (CDI)
        Object bean = buscarEmViewContexts(paginaInstanciaID);
        if (bean != null) {
            paginaDoContextoCache = bean;
            escopoEncontrado = "@ViewScoped (Weld)";
            return;
        }

        // 2. @ViewScoped — Mojarra (JSF)
        bean = buscarEmViewMaps(paginaInstanciaID);
        if (bean != null) {
            paginaDoContextoCache = bean;
            escopoEncontrado = "@ViewScoped (Mojarra)";
            return;
        }

        // 3. @SessionScoped
        bean = buscarEmSessao(paginaInstanciaID);
        if (bean != null) {
            paginaDoContextoCache = bean;
            escopoEncontrado = "@SessionScoped";
            return;
        }

        // 4. @RequestScoped
        bean = buscarEmRequest(paginaInstanciaID);
        if (bean != null) {
            paginaDoContextoCache = bean;
            escopoEncontrado = "@RequestScoped";
        }
    }

    // ── buscas por escopo ────────────────────────────────────
    private Object buscarEmViewContexts(String paginaInstanciaID) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return null;
        }

        Map<Integer, Map<String, Object>> viewContexts = obterViewContexts(session);
        if (viewContexts == null) {
            return null;
        }

        synchronized (viewContexts) {
            for (Map<String, Object> viewBeans : viewContexts.values()) {
                if (viewBeans == null) {
                    continue;
                }
                for (Object contextObj : viewBeans.values()) {
                    Object instancia = extrairInstancia(contextObj);
                    if (instancia != null && paginaInstanciaIDBate(instancia, paginaInstanciaID)) {
                        return instancia;
                    }
                }
            }
        }
        return null;
    }

    private Object buscarEmViewMaps(String paginaInstanciaID) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return null;
        }

        Map<String, Map<String, Object>> viewMaps = obterViewMaps(session);
        if (viewMaps == null) {
            return null;
        }

        synchronized (viewMaps) {
            for (Map<String, Object> viewBeans : viewMaps.values()) {
                if (viewBeans == null) {
                    continue;
                }
                for (Object bean : viewBeans.values()) {
                    if (paginaInstanciaIDBate(bean, paginaInstanciaID)) {
                        return bean;
                    }
                }
            }
        }
        return null;
    }

    private Object buscarEmSessao(String paginaInstanciaID) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return null;
        }

        Enumeration<String> nomes = session.getAttributeNames();
        while (nomes.hasMoreElements()) {
            Object valor = session.getAttribute(nomes.nextElement());
            if (valor != null && paginaInstanciaIDBate(valor, paginaInstanciaID)) {
                return valor;
            }
        }
        return null;
    }

    private Object buscarEmRequest(String paginaInstanciaID) {
        Enumeration<String> nomes = request.getAttributeNames();
        while (nomes.hasMoreElements()) {
            Object valor = request.getAttribute(nomes.nextElement());
            if (valor != null && paginaInstanciaIDBate(valor, paginaInstanciaID)) {
                return valor;
            }
        }
        return null;
    }

    // ── helpers ──────────────────────────────────────────────
    private static boolean paginaInstanciaIDBate(Object bean, String paginaInstanciaID) {
        try {
            Object id = bean.getClass()
                    .getMethod("getPaginaInstanciaID")
                    .invoke(bean);
            return paginaInstanciaID.equals(id);
        } catch (Exception e) {
            return false;
        }
    }

    private static Object extrairInstancia(Object contextObj) {
        if (contextObj == null) {
            return null;
        }
        try {
            return contextObj.getClass()
                    .getMethod("getInstance")
                    .invoke(contextObj);
        } catch (Exception e1) {
            try {
                return contextObj.getClass()
                        .getMethod("get")
                        .invoke(contextObj);
            } catch (Exception e2) {
                return null;
            }
        }
    }

    @SuppressWarnings("unchecked")
    private static Map<Integer, Map<String, Object>> obterViewContexts(HttpSession session) {
        try {
            return (Map<Integer, Map<String, Object>>) session.getAttribute(CHAVE_VIEW_CONTEXTS);
        } catch (Exception e) {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    private static Map<String, Map<String, Object>> obterViewMaps(HttpSession session) {
        try {
            return (Map<String, Map<String, Object>>) session.getAttribute(CHAVE_VIEW_MAPS);
        } catch (Exception e) {
            return null;
        }
    }
}
