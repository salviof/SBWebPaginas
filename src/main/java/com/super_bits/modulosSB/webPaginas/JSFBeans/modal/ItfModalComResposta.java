/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.modal;

import com.super_bits.modulosSB.SBCore.modulos.comunicacao.ItfRespostaComunicacao;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.ItfDialogo;

/**
 *
 * @author SalvioF
 */
public interface ItfModalComResposta extends ItfModalWebApp {

    public ItfRespostaComunicacao getRespostaSelecionada();

    public void setRespostaSelecionada(ItfRespostaComunicacao respostaSelecionada);

    public void enviarRespostaEExecutarAcao();

    public ItfDialogo getComunincacaoAguardandoResposta();
}
