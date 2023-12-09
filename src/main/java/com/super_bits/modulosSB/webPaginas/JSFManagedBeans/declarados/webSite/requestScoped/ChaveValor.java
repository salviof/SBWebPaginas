/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFManagedBeans.declarados.webSite.requestScoped;

/**
 *
 * @author salvio
 */
public class ChaveValor {

    public ChaveValor(String chave, String valor) {
        this.chave = chave;
        this.valor = valor;
    }

    private final String chave;
    private final String valor;

    public String getChave() {
        return chave;
    }

    public String getValor() {
        return valor;
    }

}
