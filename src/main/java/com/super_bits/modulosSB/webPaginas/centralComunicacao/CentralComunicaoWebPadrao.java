/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulosSB.webPaginas.centralComunicacao;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.CentralComunicaoAbstrato;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.ERPTipoCanalComunicacao;

import com.super_bits.modulosSB.SBCore.modulos.comunicacao.FabTipoComunicacao;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.FabTipoRespostaComunicacao;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.ItfTipoCanalComunicacao;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.ComoDialogo;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.ItffabricaCanalComunicacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoUsuario;
import com.super_bits.modulosSB.SBCore.modulos.servicosCore.ComoArmazenamentoComunicacao;
import com.super_bits.modulosSB.SBCore.modulos.servicosCore.ComoServicoComunicacao;

/**
 *
 * @author desenvolvedor
 */
public class CentralComunicaoWebPadrao extends CentralComunicaoAbstrato implements ComoServicoComunicacao {

    private final List<ComoDialogo> comunicacoesAtivas = new ArrayList<>();
    private final List<ComoDialogo> comunicacoesHistorico = new ArrayList<>();
    protected ComoArmazenamentoComunicacao armazenamento;

    public CentralComunicaoWebPadrao() {

    }

    @Override
    public FabTipoRespostaComunicacao aguardarRespostaComunicacao(ItfTipoCanalComunicacao pTransporte,
            ComoDialogo pComunicacao, int tempoAguardar, FabTipoRespostaComunicacao pTipoRespostaTempoFinal) {
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
    public ComoArmazenamentoComunicacao getArmazenamento() {
        if (armazenamento == null) {
            armazenamento = new ArmazenamentoComunicacaoWeb();
        }
        return armazenamento;
    }

    @Override
    public String getTokenDispositivoNotificacao(ComoUsuario pUsuario) {
        return null;
    }

    @Override
    public ItffabricaCanalComunicacao getCanalPadrao() {
        return ERPTipoCanalComunicacao.INTRANET_MENU;
    }

    @Override
    public boolean notificarViaMenu(ComoDialogo pDialogo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean notificarViaBloqueioTEla(ComoDialogo pDialogo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
