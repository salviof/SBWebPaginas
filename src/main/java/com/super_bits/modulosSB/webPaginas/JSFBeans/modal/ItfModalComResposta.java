/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.modal;

import com.super_bits.modulosSB.SBCore.modulos.comunicacao.ItfComunicacao;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.ItfRespostaComunicacao;

/**
 *
 * @author SalvioF
 */
public interface ItfModalComResposta extends ItfModalWebApp {

    public ItfRespostaComunicacao getRespostaSelecionada();

    public void setRespostaSelecionada(ItfRespostaComunicacao respostaSelecionada);

    public void enviarRespostaEExecutarAcao();

    public ItfComunicacao getComunincacaoAguardandoResposta();
}
