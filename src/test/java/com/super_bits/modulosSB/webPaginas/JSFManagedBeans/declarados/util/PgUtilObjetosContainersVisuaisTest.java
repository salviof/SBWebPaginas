/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFManagedBeans.declarados.util;

import com.super_bits.modulosSB.webPaginas.JSFBeans.declarados.Paginas.TesteWebPagina;
import org.junit.Test;

/**
 *
 * @author desenvolvedor
 */
public class PgUtilObjetosContainersVisuaisTest extends TesteWebPagina {

    public PgUtilObjetosContainersVisuaisTest() {
    }

    @Test
    public void testInicio() {
        try {
            PgUtilObjetosContainersVisuais utilCriacaoVisualizacao = new PgUtilObjetosContainersVisuais();
            utilCriacaoVisualizacao.inicio();
            System.out.println(utilCriacaoVisualizacao.getLista());
            System.out.println(utilCriacaoVisualizacao.getLista().get(0));
            utilCriacaoVisualizacao.setObjetoSelecionado(utilCriacaoVisualizacao.getLista().get(0));
            utilCriacaoVisualizacao.criarContainer();

        } catch (Throwable t) {
            lancarErroJUnit(t);
        }
    }

}
