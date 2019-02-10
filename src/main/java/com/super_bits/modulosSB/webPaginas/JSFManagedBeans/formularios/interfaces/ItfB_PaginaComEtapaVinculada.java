/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.interfaces;

import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.modulosSB.webPaginas.JSFBeans.SBBeanModel.etapas.B_Etapas;

/**
 *
 * @author desenvolvedor
 */
public interface ItfB_PaginaComEtapaVinculada extends ItfB_Pagina {

    public B_Etapas getEtapas();

    public boolean isLiberadoProximaEtapa();

    public boolean isLiberadoEtapaAnterior();

    public ItfAcaoDoSistema getAcaoFinal();

    public void irParaProximaEtapa();

    public void irParaEtapaAnterior();

}
