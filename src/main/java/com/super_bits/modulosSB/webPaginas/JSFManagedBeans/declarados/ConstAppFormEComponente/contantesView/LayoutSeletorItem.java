/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFManagedBeans.declarados.ConstAppFormEComponente.contantesView;

import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.componentes.FabCompVisualSeletorItem;
import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.ComoComponenteVisualSB;

/**
 *
 *
 *
 *
 * @author salvioF
 */
public class LayoutSeletorItem {

    private final ComoComponenteVisualSB combo = FabCompVisualSeletorItem.COMBO.getRegistro();
    private final ComoComponenteVisualSB grade = FabCompVisualSeletorItem.GRADE.getRegistro();
    private final ComoComponenteVisualSB autocomplete = FabCompVisualSeletorItem.AUTO_COMPLETE.getRegistro();
    private final ComoComponenteVisualSB radio = FabCompVisualSeletorItem.RADIO.getRegistro();
    private final ComoComponenteVisualSB carrousel = FabCompVisualSeletorItem.CARROULSEL.getRegistro();
    private final ComoComponenteVisualSB menu = FabCompVisualSeletorItem.BOTOES_MENU.getRegistro();

    public ComoComponenteVisualSB getCombo() {
        return combo;
    }

    public ComoComponenteVisualSB getGrade() {
        return grade;
    }

    public ComoComponenteVisualSB getAutocomplete() {
        return autocomplete;
    }

    public ComoComponenteVisualSB getRadio() {
        return radio;
    }

    public ComoComponenteVisualSB getCarrousel() {
        return carrousel;
    }

    public ComoComponenteVisualSB getMenu() {
        return menu;
    }

}
