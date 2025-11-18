/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFManagedBeans.declarados.ConstAppFormEComponente.contantesView;

import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.componentes.FabCompVisualBotaoAcao;
import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.ComoComponenteVisualSB;

/**
 *
 * @author salvioF
 */
public class LayoutBotoes {

    private final ComoComponenteVisualSB icone = FabCompVisualBotaoAcao.ICONE.getRegistro();
    private final ComoComponenteVisualSB iconeENome = FabCompVisualBotaoAcao.ICONE_E_NOME.getRegistro();
    private final ComoComponenteVisualSB nome = FabCompVisualBotaoAcao.NOME.getRegistro();
    private final ComoComponenteVisualSB gigante = FabCompVisualBotaoAcao.ICONE_GIGANTE.getRegistro();
    private final ComoComponenteVisualSB cardResponsivo = FabCompVisualBotaoAcao.BOTAO_CARD_RESPONSIVO.getRegistro();

    public ComoComponenteVisualSB getIcone() {
        return icone;
    }

    public ComoComponenteVisualSB getIconeENome() {
        return iconeENome;
    }

    public ComoComponenteVisualSB getNome() {
        return nome;
    }

    public ComoComponenteVisualSB getGigante() {
        return gigante;
    }

    public ComoComponenteVisualSB getCardResponsivo() {
        return cardResponsivo;
    }

}
