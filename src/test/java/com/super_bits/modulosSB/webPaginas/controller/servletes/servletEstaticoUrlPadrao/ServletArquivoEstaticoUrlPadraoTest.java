/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.controller.servletes.servletEstaticoUrlPadrao;

import com.super_bits.modulosSB.webPaginas.JSFBeans.declarados.Paginas.TesteWebPagina;
import org.junit.Test;

/**
 *
 * @author SalvioF
 */
public class ServletArquivoEstaticoUrlPadraoTest extends TesteWebPagina {

    public ServletArquivoEstaticoUrlPadraoTest() {
    }

    /**
     * Test of doGet method, of class ServletArquivoEstaticoUrlPadrao.
     */
    @Test
    public void testDoGet() throws Exception {
        try {
            String caminhoRecurso = "nivel1/nivel2/nivel3";
            System.out.println();
            caminhoRecurso = caminhoRecurso.substring(caminhoRecurso.indexOf("/"));
            System.out.println("CAminbho recuros=" + caminhoRecurso);
        } catch (Throwable t) {
            lancarErroJUnit(t);
        }

    }

}
