/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.interfaces;

/**
 *
 * @author desenvolvedor
 */
public interface ItfB_Modal extends ItfB_PaginaSimples {

    public void fecharModal();

    public ItfModalDados getComoModalDados();

    public default boolean isUmModalComDadados() {
        return (this instanceof ItfModalDados);
    }

}
