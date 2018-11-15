/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.util.testes;

import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.interfaces.ItfB_Pagina;
import org.junit.Test;
import testesFW.TesteJunitSBPersistencia;

/**
 *
 * @author desenvolvedor
 */
public abstract class TestePaginaBasico extends TesteJunitSBPersistencia {

    /**
     *
     * Retorna a pagina que será testada, já instanciada
     *
     * @return
     */
    public abstract ItfB_Pagina getPaginaBasico();

    @Test
    public void testeAcaoVinculada() {
        ItfB_Pagina pagina = null;

    }

}
