/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.modal;

import com.super_bits.modulosSB.SBCore.modulos.comunicacao.ComoDialogoAcaoVinculada;
import javax.annotation.PostConstruct;

/**
 *
 * @author SalvioF
 */
public abstract class PgModalComunicacaoAcaoTransientAbstrato extends PgModalRespostaAbstrato {

    @PostConstruct
    public void defineConversa() {
        comunicacao = getPaginaVinculada().getComunicacaoTransientAcaoByIdModal(chaveIdentificacaoViewOrigem);
    }

    @Override
    public ComoDialogoAcaoVinculada getComunicacao() {
        return (ComoDialogoAcaoVinculada) super.getComunicacao(); //To change body of generated methods, choose Tools | Templates.
    }

}
