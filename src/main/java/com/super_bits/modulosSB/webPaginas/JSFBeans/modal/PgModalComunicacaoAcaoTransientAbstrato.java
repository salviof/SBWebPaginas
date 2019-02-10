/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.modal;

import com.super_bits.modulosSB.SBCore.modulos.comunicacao.ItfComunicacaoAcaoVinculada;
import javax.annotation.PostConstruct;
import org.primefaces.context.RequestContext;

/**
 *
 * @author SalvioF
 */
public abstract class PgModalComunicacaoAcaoTransientAbstrato extends PgModalRespostaAbstrato {

    @PostConstruct
    public void defineConversa() {
        comunicacao = getPaginaVinculada().getComunicacaoTransientAcaoByIdModal(getChaveAcesso());
    }

    @Override
    public ItfComunicacaoAcaoVinculada getComunicacao() {
        return (ItfComunicacaoAcaoVinculada) super.getComunicacao(); //To change body of generated methods, choose Tools | Templates.
    }

}
