/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulosSB.webPaginas.centralComunicacao;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.CentralComunicaoAbstrato;

import com.super_bits.modulosSB.SBCore.modulos.comunicacao.FabTipoComunicacao;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.FabTipoRespostaComunicacao;
import com.super_bits.modulosSB.SBCore.modulos.servicosCore.ItfArmazenamentoComunicacao;

import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfUsuario;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import com.super_bits.modulosSB.SBCore.modulos.servicosCore.ItfServicoComunicacao;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.ItfTipoCanalComunicacao;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.ItfDialogo;

/**
 *
 * @author desenvolvedor
 */
public class CentralComunicaoWebPadrao extends CentralComunicaoAbstrato implements ItfServicoComunicacao {

    private final List<ItfDialogo> comunicacoesAtivas = new ArrayList<>();
    private final List<ItfDialogo> comunicacoesHistorico = new ArrayList<>();
    private final ArmazenamentoComunicacaoWeb aramazenamento;

    public CentralComunicaoWebPadrao() {
        aramazenamento = new ArmazenamentoComunicacaoWeb();
    }

    @Override
    public FabTipoRespostaComunicacao aguardarRespostaComunicacao(ItfTipoCanalComunicacao pTransporte,
            ItfDialogo pComunicacao, int tempoAguardar, FabTipoRespostaComunicacao pTipoRespostaTempoFinal) {
        FabTipoComunicacao tipocomunicacao = pComunicacao.getTipoComunicacao().getFabTipoComunicacao();
        if (SBCore.isEmModoDesenvolvimento()) {
            int dialogResult
                    = JOptionPane.showConfirmDialog(null, pComunicacao.getMensagem(),
                            "Deseja continuar?", JOptionPane.YES_OPTION);
            if (dialogResult
                    == JOptionPane.YES_OPTION) {
                return FabTipoRespostaComunicacao.SIM;
            } else {
                System.out.println("não");
                return FabTipoRespostaComunicacao.NAO;
            }
        }
        throw new UnsupportedOperationException("aguardar resposta comunicação web não foi implementado");
    }

    @Override
    public ItfArmazenamentoComunicacao getArmazenamento() {
        return aramazenamento;
    }

    @Override
    public String getTokenDispositivoNotificacao(ItfUsuario pUsuario) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
