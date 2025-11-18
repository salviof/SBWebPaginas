/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFManagedBeans.declarados.ConstAppFormEComponente.contantesView;

import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.componentes.FabCompVIsualInputsLayout;
import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.ComoComponenteVisualSB;

/**
 *
 * @author salvioF
 */
public class LayoutInputCampo {

    private final ComoComponenteVisualSB resumido = FabCompVIsualInputsLayout.LABEL_RESUMIDO.getRegistro();
    private final ComoComponenteVisualSB esquerda = FabCompVIsualInputsLayout.LABEL_ESQUEDA.getRegistro();
    private final ComoComponenteVisualSB superior = FabCompVIsualInputsLayout.LABEL_SUPERIOR.getRegistro();
    private final ComoComponenteVisualSB semLabel = FabCompVIsualInputsLayout.INPUTSEM_LABEL.getRegistro();
    private final ComoComponenteVisualSB automatico = FabCompVIsualInputsLayout.AUTOMATICO.getRegistro();

    public ComoComponenteVisualSB getResumido() {
        return resumido;
    }

    public ComoComponenteVisualSB getEsquerda() {
        return esquerda;
    }

    public ComoComponenteVisualSB getSuperior() {
        return superior;
    }

    public ComoComponenteVisualSB getAutomatico() {
        return automatico;
    }

    public ComoComponenteVisualSB getSemLabel() {
        return semLabel;
    }

}
