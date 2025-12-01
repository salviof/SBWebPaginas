/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.TratamentoDeErros;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.UtilCRCErros;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWPServletTools;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWP_JSFTools;
import java.util.Iterator;
import javax.faces.FacesException;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.servlet.http.HttpServletRequest;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import org.primefaces.application.exceptionhandler.PrimeExceptionHandler;

/**
 *
 * @author SalvioF
 */
public class ManipuladorErrosSBJSF extends PrimeExceptionHandler {

    public ManipuladorErrosSBJSF(ExceptionHandler wrapped) {
        super(wrapped);
    }

    @Override
    public void handle() throws FacesException {

        FacesContext context = FacesContext.getCurrentInstance();

        if (context == null || context.getResponseComplete()) {
            return;
        }

        Iterable<ExceptionQueuedEvent> exceptionQueuedEvents = getUnhandledExceptionQueuedEvents();
        if (exceptionQueuedEvents != null && exceptionQueuedEvents.iterator() != null) {
            Iterator<ExceptionQueuedEvent> unhandledExceptionQueuedEvents = getUnhandledExceptionQueuedEvents().iterator();

            if (unhandledExceptionQueuedEvents.hasNext()) {
                Throwable throwable = unhandledExceptionQueuedEvents.next().getContext().getException();
                try {

                    String mensagemErro = "Erro processando JSF" + throwable.getMessage();
                    Throwable causaraiz = UtilCRCErros.getCausaRaiz(throwable);
                    if (causaraiz != null) {
                        mensagemErro = mensagemErro + "-->" + causaraiz.getMessage();
                    }
                    SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, mensagemErro, throwable);

                } catch (Exception ex) {
                    SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro manipulando mensagem de erro JSF!", ex);
                }

                HttpServletRequest requisicao = UtilSBWPServletTools.getRequestAtual();
                if (requisicao != null) {
                    String caminho = requisicao.getRequestURI();
                    if (caminho.endsWith("jsonwebview.xhtml")) {
                        requisicao.setAttribute("erro", throwable);

                        throw new ErroGenericoProcessandoRespostaJson(throwable);
                    }
                }
            }

            while (unhandledExceptionQueuedEvents.hasNext()) {
                // Any remaining unhandled exceptions are not interesting. First fix the first.
                unhandledExceptionQueuedEvents.next();
                unhandledExceptionQueuedEvents.remove();
            }
        }
        super.handle(); //To change body of generated methods, choose Tools | Templates.

    }

}
