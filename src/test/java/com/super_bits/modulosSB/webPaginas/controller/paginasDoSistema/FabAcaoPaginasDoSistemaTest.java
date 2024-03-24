/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.controller.paginasDoSistema;

import com.super_bits.modulos.SBAcessosModel.model.acoes.AcaoDoSistema;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.webPaginas.ConfiguradorSBCoreWebPaginaTestes;
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
public class FabAcaoPaginasDoSistemaTest {

    public FabAcaoPaginasDoSistemaTest() {
    }

    /**
     * Test of values method, of class FabAcaoPaginasDoSistema.
     */
    @Test
    public void testValues() {
        SBCore.configurar(new ConfiguradorSBCoreWebPaginaTestes(), SBCore.ESTADO_APP.DESENVOLVIMENTO);
        System.out.println(FabAcaoPaginasDoSistema.PAGINA_NATIVA_TOKEN_DINAMICO_MB.getRegistro().getNomeUnico());
        assertEquals(FabAcaoPaginasDoSistema.PAGINA_NATIVA_TOKEN_DINAMICO_MB.getRegistro().getNomeUnico(), "");
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
