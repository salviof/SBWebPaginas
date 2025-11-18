/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFManagedBeans.declarados.Paginas;

import com.super_bits.modulosSB.SBCore.modulos.Controller.permissaoPadrao.MetadadosTokenDinamico;
import jakarta.json.JsonObject;
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
public class MetadadosTokenDinamicoTest {

    /**
     * Test of toString method, of class MetadadosTokenDinamico.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        MetadadosTokenDinamico metadados = new MetadadosTokenDinamico();
        metadados.setAssinaturaSistema("");
        metadados.setSistemaRequisicao("mktFatura");
        metadados.setLeadNome("Apenas teste");
        metadados.setLeadEmail("teste");
    }

    /**
     * Test of getJson method, of class MetadadosTokenDinamico.
     */
    @Test
    public void testGetJson() {
        System.out.println("getJson");
        MetadadosTokenDinamico instance = null;
        JsonObject expResult = null;
        JsonObject result = instance.getJson();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
