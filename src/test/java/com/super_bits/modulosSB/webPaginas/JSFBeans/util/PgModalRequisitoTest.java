/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.util;

import com.super_bits.modulosSB.webPaginas.JSFBeans.modal.PgModalRequisito;
import com.super_bits.modulosSB.webPaginas.JSFBeans.declarados.Paginas.TesteWebPagina;
import org.junit.Test;

/**
 *
 * @author desenvolvedor
 */
public class PgModalRequisitoTest extends TesteWebPagina {

    public PgModalRequisitoTest() {
    }

    /**
     * Test of getNomeUnicoAcao method, of class PgModalRequisito.
     */
    @Test
    public void testGetNomeUnicoAcao() {

        PgModalRequisito instance = new PgModalRequisito();
        instance.atualizarRequisito();
    }

}
