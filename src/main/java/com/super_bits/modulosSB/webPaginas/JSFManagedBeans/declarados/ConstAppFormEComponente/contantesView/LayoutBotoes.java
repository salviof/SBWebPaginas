/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFManagedBeans.declarados.ConstAppFormEComponente.contantesView;

import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.ItfComponenteVisualSB;
import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.componentes.FabCompVisualBotaoAcao;

/**
 *
 * @author salvioF
 */
public class LayoutBotoes {

    private final ItfComponenteVisualSB icone = FabCompVisualBotaoAcao.ICONE.getRegistro();
    private final ItfComponenteVisualSB iconeENome = FabCompVisualBotaoAcao.ICONE_E_NOME.getRegistro();
    private final ItfComponenteVisualSB nome = FabCompVisualBotaoAcao.NOME.getRegistro();
    private final ItfComponenteVisualSB gigante = FabCompVisualBotaoAcao.ICONE_GIGANTE.getRegistro();
    private final ItfComponenteVisualSB cardResponsivo = FabCompVisualBotaoAcao.BOTAO_CARD_RESPONSIVO.getRegistro();

    public ItfComponenteVisualSB getIcone() {
        return icone;
    }

    public ItfComponenteVisualSB getIconeENome() {
        return iconeENome;
    }

    public ItfComponenteVisualSB getNome() {
        return nome;
    }

    public ItfComponenteVisualSB getGigante() {
        return gigante;
    }

    public ItfComponenteVisualSB getCardResponsivo() {
        return cardResponsivo;
    }

}
