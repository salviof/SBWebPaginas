/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFManagedBeans.declarados.ConstAppFormEComponente.contantesView;

import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.ItfComponenteVisualSB;
import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.componentes.FabCompVisualSeletorItem;
import org.coletivojava.fw.api.objetoNativo.view.componente.ComponenteVisualSB;

/**
 *
 *
 *
 *
 * @author salvioF
 */
public class LayoutSeletorItem {

    private final ItfComponenteVisualSB combo = FabCompVisualSeletorItem.COMBO.getRegistro();
    private final ItfComponenteVisualSB grade = FabCompVisualSeletorItem.GRADE.getRegistro();
    private final ItfComponenteVisualSB autocomplete = FabCompVisualSeletorItem.AUTO_COMPLETE.getRegistro();
    private final ItfComponenteVisualSB radio = FabCompVisualSeletorItem.RADIO.getRegistro();
    private final ItfComponenteVisualSB carrousel = FabCompVisualSeletorItem.CARROULSEL.getRegistro();
    private final ItfComponenteVisualSB menu = FabCompVisualSeletorItem.BOTOES_MENU.getRegistro();

    public ItfComponenteVisualSB getCombo() {
        return combo;
    }

    public ItfComponenteVisualSB getGrade() {
        return grade;
    }

    public ItfComponenteVisualSB getAutocomplete() {
        return autocomplete;
    }

    public ItfComponenteVisualSB getRadio() {
        return radio;
    }

    public ItfComponenteVisualSB getCarrousel() {
        return carrousel;
    }

    public ItfComponenteVisualSB getMenu() {
        return menu;
    }

}
