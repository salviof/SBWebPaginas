/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFManagedBeans.declarados.webSite.requestScoped;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWPServletTools;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map.Entry;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author salvio
 */
@RequestScoped
@Named
public class InfoRequest implements Serializable {

    private List<ChaveValor> cookies = new ArrayList<>();
    private List<ChaveValor> parametros = new ArrayList<>();
    private List<ChaveValor> headers = new ArrayList<>();
    private String uri;
    private String url;
    private String servletPath;
    private String usuarioPrincipal;
    private String ipCliente;
    private String hostCliente;

    @PostConstruct
    public void inicio() {
        HttpServletRequest request = UtilSBWPServletTools.getRequestAtual();
        for (Cookie biscoito : request.getCookies()) {
            cookies.add(new ChaveValor(biscoito.getName(), biscoito.getValue()));
        }
        for (Entry parametro : request.getParameterMap().entrySet()) {
            parametros.add(new ChaveValor(parametro.getKey().toString(), String.valueOf(parametro.getValue().toString())));
        }
        for (Enumeration<?> e = request.getHeaderNames(); e.hasMoreElements();) {
            String nextHeaderName = (String) e.nextElement();
            headers.add(new ChaveValor(nextHeaderName, request.getHeader(nextHeaderName)));
        }

        uri = request.getRequestURI();
        url = request.getRequestURL().toString();
        servletPath = request.getServletPath();
        if (request.getUserPrincipal() != null) {
            usuarioPrincipal = request.getUserPrincipal().getName();
        }
        ipCliente = request.getRemoteAddr();
        hostCliente = request.getRemoteHost();
    }

    public String getUsuarioPrincipal() {
        return usuarioPrincipal;
    }

    public List<ChaveValor> getCookies() {
        return cookies;
    }

    public List<ChaveValor> getParametros() {
        return parametros;
    }

    public String getUri() {
        return uri;
    }

    public String getUrl() {
        return url;
    }

    public List<ChaveValor> getHeaders() {
        return headers;
    }

    public String getServletPath() {
        return servletPath;
    }

    public String getIpCliente() {
        return ipCliente;
    }

    public String getHostCliente() {
        return hostCliente;
    }

}
