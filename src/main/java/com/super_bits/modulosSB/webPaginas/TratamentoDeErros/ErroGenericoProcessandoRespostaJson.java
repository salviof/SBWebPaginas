/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.TratamentoDeErros;

import javax.faces.FacesException;

/**
 *
 * @author salvio
 */
public class ErroGenericoProcessandoRespostaJson extends FacesException {

    public ErroGenericoProcessandoRespostaJson(Throwable t) {
        super(t);
    }

    public ErroGenericoProcessandoRespostaJson(String mensagem) {
        super(mensagem);
    }

}
