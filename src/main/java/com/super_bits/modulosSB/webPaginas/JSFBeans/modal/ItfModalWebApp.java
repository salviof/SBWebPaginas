/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.modal;

import com.super_bits.modulosSB.SBCore.modulos.comunicacao.ItfComunicacao;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.interfaces.ItfB_Pagina;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.interfaces.ItfB_Modal;

/**
 *
 * @author SalvioF
 */
public interface ItfModalWebApp extends ItfB_Modal {

    public ItfB_Pagina getPaginaVinculada();

    public ItfComunicacao getComunincacaoAguardandoResposta();
}
