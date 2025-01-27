/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFManagedBeans.declarados.util;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author salvio
 */
@RequestScoped
@Named
public class PgUtilFiltroOrdanacao {

    public boolean filtrarColuna(Object item, Object filtro, Object coluna) {
        System.out.println(item);
        System.out.println(filtro);
        System.out.println(coluna);
        return true;
    }

    public boolean filtrarColuna(Object item, Object filtro) {
        System.out.println(item);
        System.out.println(filtro);

        return true;
    }

    public int ordernarPorColuna(Object comparacao1, Object comparacao2) {
        System.out.println(comparacao1);
        System.out.println(comparacao2);

        return 0;
    }
}
