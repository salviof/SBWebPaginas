/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFManagedBeans.declarados.daoCDI;

import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;

/**
 *
 * @author SalvioF
 */
public class ProdutorEntityManager {

    @Produces
    @RequestScoped
    public EntityManager criarEntityManager() {
        return UtilSBPersistencia.getNovoEM();
    }

    public void finaliza(@Disposes EntityManager manager) {
        manager.close();
        //   System.out.println("EntityManager fechado");
    }
}
