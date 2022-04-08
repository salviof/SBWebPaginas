/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.interfaces;

import javax.persistence.EntityManager;

/**
 *
 * @author sfurbino
 */
public interface ItfB_PaginaComEntityManager {

    /**
     *
     * @return Obtem O entitymanager atual da página
     */
    public EntityManager getEMPagina();

    /**
     * Fecha a sessão do entity manager vigente e cria uma nova sessão
     */
    //public void renovarEMPagina();
}
