/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.SBBeanModel.controller;

import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoController;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfUsuario;

/**
 *
 * @author salvio
 */
public class PacoteExecucaoControllerJsonSimples {

    private final ItfAcaoController acao;
    private final ItfBeanSimples entidadeExecucao;
    private final ItfUsuario usuario;

    public PacoteExecucaoControllerJsonSimples(ItfAcaoController pAceo, ItfUsuario pUsuario, ItfBeanSimples pValor) {
        acao = pAceo;
        usuario = pUsuario;
        entidadeExecucao = pValor;
    }

    public ItfAcaoController getAcao() {
        return acao;
    }

    public ItfBeanSimples getEntidadeExecucao() {
        return entidadeExecucao;
    }

    public ItfUsuario getUsuario() {
        return usuario;
    }

}
