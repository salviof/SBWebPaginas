/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.centralDados;

import com.super_bits.modulosSB.Persistencia.ConfigGeral.CentralDadosJPAPadrao;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.MapaControllerEmExecucao;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfRespostaAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.fonteDados.ItfTokenAcessoDados;
import com.super_bits.modulosSB.SBCore.modulos.fonteDados.TokenAcessoDados;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWP_JSFTools;
import testesFW.TesteJunitSBPersistencia;

/**
 *
 * @author desenvolvedor
 */
public class CentralDadosWebApp extends CentralDadosJPAPadrao {

    enum TipoContextoDados {
        FORMULARIO_WEB, CONTROOLER_SERVIDOR, CONTROLLER_CLIENTE, TESTES
    }

    @Override
    public ItfTokenAcessoDados getAcessoDadosDoContexto() {
        TipoContextoDados tipoContexto = null;
        if (!SBCore.isEmModoDesenvolvimento()) {
            ItfRespostaAcaoDoSistema respostaDoContexto = MapaControllerEmExecucao.getRespostaDoContexto();
            if (respostaDoContexto != null) {
                tipoContexto = TipoContextoDados.CONTROOLER_SERVIDOR;

                return new TokenAcessoDados(respostaDoContexto.getComoRepostaGestaoEM().getEMResposta());
            } else {

                tipoContexto = TipoContextoDados.FORMULARIO_WEB;
                return new TokenAcessoDados(UtilSBWP_JSFTools.getEntityManagerDaPaginaAtual());
            }

        } else {
            tipoContexto = TipoContextoDados.TESTES;
            return new TokenAcessoDados(TesteJunitSBPersistencia.getEM());
        }

    }

}
