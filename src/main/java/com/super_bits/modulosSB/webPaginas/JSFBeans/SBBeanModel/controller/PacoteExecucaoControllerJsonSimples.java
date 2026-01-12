/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.SBBeanModel.controller;

import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ComoAcaoController;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoEntidadeSimples;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoUsuario;

/**
 *
 * @author salvio
 */
public class PacoteExecucaoControllerJsonSimples {

    private final ComoAcaoController acao;
    private final ComoEntidadeSimples entidadeExecucao;
    private final ComoUsuario usuario;

    public PacoteExecucaoControllerJsonSimples(ComoAcaoController pAceo, ComoUsuario pUsuario, ComoEntidadeSimples pValor) {
        acao = pAceo;
        usuario = pUsuario;
        entidadeExecucao = pValor;
    }

    public ComoAcaoController getAcao() {
        return acao;
    }

    public ComoEntidadeSimples getEntidadeExecucao() {
        return entidadeExecucao;
    }

    public ComoUsuario getUsuario() {
        return usuario;
    }

}
