/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.controller.servlets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author salvio
 */
public class ServletNotionTest {

    public ServletNotionTest() {
    }

    /**
     * Test of getArquivoXHTMLByUrl method, of class ServletNotion.
     */
    @Test
    public void testGetArquivoXHTMLByUrl() {

        ServletNotion instance = new ServletNotion();
        final String resultadoEsperado = ServletNotion.PASTA_NOTION_WIDGETS_PADRAO + "meuWidiget.xhtml";
        String result = instance.getArquivoXHTMLByUrl("qualquercoisa/qualquer/meuWidiget.notion");
        assertEquals(resultadoEsperado, result);

    }

}
