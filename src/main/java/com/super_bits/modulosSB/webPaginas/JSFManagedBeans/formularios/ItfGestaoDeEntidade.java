/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios;

import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoGerenciarEntidade;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ItfFabricaAcoes;

/**
 *
 * @author SalvioF
 */
public interface ItfGestaoDeEntidade<T> {

    public T getEntidadeSelecionada();

    public void setEntidadeSelecionada();

    public void ListarEntidades(ItfFabricaAcoes pAcao);

    public ItfAcaoGerenciarEntidade getAcaoGestaoVinculada();

}
