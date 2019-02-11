/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.TratamentoDeErros;

import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerFactory;
import org.primefaces.application.exceptionhandler.PrimeExceptionHandlerFactory;

/**
 *
 * @author SalvioF
 */
public class FabricaManipuladorErrosJsf extends PrimeExceptionHandlerFactory {

    public FabricaManipuladorErrosJsf(final ExceptionHandlerFactory wrapped) {
        super(wrapped);
    }

    @Override
    public ExceptionHandler getExceptionHandler() {
        return new ManipuladorErrosSBJSF(getWrapped().getExceptionHandler());
    }

}
