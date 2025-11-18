/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.interfaces;

import java.io.Serializable;
import com.super_bits.modulosSB.SBCore.modulos.view.menu.ComoFabricaMenu;

/**
 *
 *
 *
 * @author desenvolvedor
 */
public interface ItfSiteMapa extends Serializable {

    /**
     *
     * Uma fabrica de menu é uma classe do tipo enum, contendo os diversos menus
     * do sistema
     *
     * @return Fabrica de menus do sistema
     */
    Class<? extends ComoFabricaMenu> getFabricaMenu();

    ItfB_PaginaSimples getPaginaNoContexto(String xhtmlGerenciarPG) throws UnsupportedOperationException;

}
