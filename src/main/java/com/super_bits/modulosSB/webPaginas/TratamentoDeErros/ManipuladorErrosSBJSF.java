/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.TratamentoDeErros;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import java.util.Iterator;
import javax.faces.FacesException;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
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
                try {
                    Throwable throwable = unhandledExceptionQueuedEvents.next().getContext().getException();
                    SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro processando JSF", throwable);

                } catch (Exception ex) {
                    SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro manipulando mensagem de erro JSF!", ex);
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
