/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.controller.servlets;

/**
 *
 * @author sfurbino
 */
public class ErroRequisicaoServlet extends Throwable {

    private FabTipoErroRequisicao tipoErro;

    public ErroRequisicaoServlet(FabTipoErroRequisicao pTipoErro, String pTexto) {
        super(pTexto);
        tipoErro = pTipoErro;
    }

    public FabTipoErroRequisicao getTipoErro() {
        return tipoErro;
    }

}
