/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFManagedBeans.declarados.ConstAppFormEComponente.contantesView;

import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.ItfComponenteVisualSB;
import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.componentes.FabCompVIsualInputsLayout;
import org.coletivojava.fw.api.objetoNativo.view.componente.ComponenteVisualSB;

/**
 *
 * @author salvioF
 */
public class LayoutInputCampo {

    private final ItfComponenteVisualSB resumido = FabCompVIsualInputsLayout.LABEL_RESUMIDO.getRegistro();
    private final ItfComponenteVisualSB esquerda = FabCompVIsualInputsLayout.LABEL_ESQUEDA.getRegistro();
    private final ItfComponenteVisualSB superior = FabCompVIsualInputsLayout.LABEL_SUPERIOR.getRegistro();
    private final ItfComponenteVisualSB semLabel = FabCompVIsualInputsLayout.INPUTSEM_LABEL.getRegistro();
    private final ItfComponenteVisualSB automatico = FabCompVIsualInputsLayout.AUTOMATICO.getRegistro();

    public ItfComponenteVisualSB getResumido() {
        return resumido;
    }

    public ItfComponenteVisualSB getEsquerda() {
        return esquerda;
    }

    public ItfComponenteVisualSB getSuperior() {
        return superior;
    }

    public ItfComponenteVisualSB getAutomatico() {
        return automatico;
    }

    public ItfComponenteVisualSB getSemLabel() {
        return semLabel;
    }

}
