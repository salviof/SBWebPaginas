/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.controller.listenners;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.Controller.qualificadoresCDI.sessao.QlSessaoFacesContext;
import java.io.IOException;
import java.security.Principal;
import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ComoSessao;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

/**
 *
 * @author salvio
 */
@WebFilter("/*")
public class UserPrincipalFilter implements Filter {

    @Inject
    @QlSessaoFacesContext
    private ComoSessao sessaoUsuario;

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;

        if (sessaoUsuario.isIdentificado()) {
            Principal principal = new SimplePrincipal(sessaoUsuario.getUsuario().getApelido());
            request = new HttpServletRequestWrapper(request) {
                @Override
                public Principal getUserPrincipal() {
                    return principal;
                }

                @Override
                public String getRemoteUser() {
                    return principal.getName();
                }
            };
        }
        try {
            chain.doFilter(request, res);
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Falha: " + t.getMessage() + " processando " + request.getRequestURI(), t);
        }
    }

    @Override
    public void init(FilterConfig fc) throws ServletException {
        //nada aqui
    }

    @Override
    public void destroy() {
        // Nenhum recurso a liberar
    }

    static class SimplePrincipal implements Principal {

        private final String name;

        public SimplePrincipal(String name) {
            this.name = name;
        }

        @Override
        public String getName() {
            return name;
        }
    }
}
