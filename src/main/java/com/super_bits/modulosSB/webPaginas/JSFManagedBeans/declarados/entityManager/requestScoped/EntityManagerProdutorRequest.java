/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.super_bits.modulosSB.webPaginas.JSFManagedBeans.declarados.entityManager.requestScoped;

import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import javax.enterprise.context.RequestScoped;

import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;

/**
 *
 * @author salvio
 */
public class EntityManagerProdutorRequest {

    @Produces()
    @RequestScoped()
    @EMRequestModo()
    public EntityManager createEntityManager() {
        return UtilSBPersistencia.getEntyManagerPadraoNovo();
    }

    public void close(@Disposes @EMRequestModo EntityManager em) {
        if (em.isOpen()) {
            em.close();
        }
    }

}
