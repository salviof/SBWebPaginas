/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFManagedBeans.declarados.Paginas;

import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.InfoErroSBBasico;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import org.primefaces.application.exceptionhandler.ExceptionInfo;

/**
 *
 * Bean do tipo request Scoped para ajudar a exibir erros
 *
 * @author <a href="mailto:salviof@gmail.com">Salvio Furbino</a>
 * @since 15/01/2015
 * @version 1.0
 */
@RequestScoped
@Named
public class InfoErro implements Serializable {

    public static enum ORIGEM_ERRO_WEBPAGINA {
        SERVLET, PRIMEFACES, ERRO_CRITICO_FRAMEWORK, JSF_GENERICO, MULTIPLOS, ERRO_NAO_ENCONTRADO
    }

    private Throwable erroServlet;
    private ExceptionInfo erroPrimeFaces;

    @PostConstruct
    public void inicio() {
        buildErroServlet();
        buildErroPrimeFaces();
        testePesquisaArquivoXhtml();
    }

    private void buildErroServlet() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            Map<String, Object> requestMap = context.getExternalContext().getRequestMap();
            for (String atributo : requestMap.keySet()) // Fetch the exception
            {
                System.out.println("Atributo:" + atributo + " " + requestMap.get(atributo).getClass().getSimpleName());
            }
            erroServlet = (Throwable) requestMap.get("javax.servlet.error.exception");
        } catch (Throwable t) {
            System.out.println("Impossível obter erro do tipo Servlet");
        }
    }

    public void testePesquisaArquivoXhtml() {

        List<String> mensagensDeFormulario = getErro().getMensagensContendoIsto("xhtml");
        System.out.println(mensagensDeFormulario);
    }

    private void buildErroPrimeFaces() {
        FacesContext context = FacesContext.getCurrentInstance();
        erroPrimeFaces = context.getApplication().evaluateExpressionGet(context, "#{pfExceptionHandler}", ExceptionInfo.class);

    }

    public ORIGEM_ERRO_WEBPAGINA getTipoOrigem() {

        boolean temErroServlet = erroServlet != null;
        boolean temErroPrime = erroPrimeFaces != null;

        if (temErroPrime & temErroServlet) {
            return ORIGEM_ERRO_WEBPAGINA.MULTIPLOS;
        }
        if (temErroPrime) {
            return ORIGEM_ERRO_WEBPAGINA.PRIMEFACES;
        }
        if (temErroServlet) {
            return ORIGEM_ERRO_WEBPAGINA.JSF_GENERICO;
        }

        return ORIGEM_ERRO_WEBPAGINA.ERRO_NAO_ENCONTRADO;

    }

    public InfoErroSBBasico getErro() {

        switch (getTipoOrigem()) {
            case SERVLET:
                return new InfoErroSBBasico("Erro de Servlet", FabErro.SOLICITAR_REPARO, erroServlet);

            case PRIMEFACES:
                return new InfoErroSBBasico("Erro JSF Prime", FabErro.SOLICITAR_REPARO, erroPrimeFaces.getException());

            case ERRO_CRITICO_FRAMEWORK:

                break;
            case JSF_GENERICO:

                break;
            case MULTIPLOS:
                return new InfoErroSBBasico("Erro multiplo", FabErro.SOLICITAR_REPARO, erroPrimeFaces.getException());

            case ERRO_NAO_ENCONTRADO:

                break;
            default:
                throw new AssertionError(getTipoOrigem().name());

        }
        try {
            throw new UnsupportedOperationException("Aconteceu um erro, porém o sistema não encontrou um bean com informações sobre ele");
        } catch (Throwable t) {
            return new InfoErroSBBasico("Erro misterioso desconhecido, (Veerifique a saída do tomcat com Ctr+4", FabErro.SOLICITAR_REPARO, t);
        }
    }

    public ExceptionInfo getErroPrimefaces() {
        return erroPrimeFaces;

    }

    public Throwable getErroServlet() {
        return erroServlet;
    }

    public ExceptionInfo getErroPrimeFaces() {
        return erroPrimeFaces;
    }

    public boolean isUmErroTipoPrime() {
        return erroPrimeFaces != null;
    }

    public boolean isUmErroTipoServlet() {
        return erroServlet != null;
    }

}
