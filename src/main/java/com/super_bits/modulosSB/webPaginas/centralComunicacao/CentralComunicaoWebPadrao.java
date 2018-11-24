/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulosSB.webPaginas.centralComunicacao;

import br.org.coletivojava.erp.comunicacao.transporte.ERPTransporteComunicacao;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.ArmazenamentoComunicacaoTransient;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.CentralComunicaoAbstrato;

import com.super_bits.modulosSB.SBCore.modulos.comunicacao.ComunicacaoTransient;

import com.super_bits.modulosSB.SBCore.modulos.comunicacao.FabTipoComunicacao;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.FabTipoRespostaComunicacao;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.ItfArmazenamentoComunicacao;

import com.super_bits.modulosSB.SBCore.modulos.comunicacao.ItfCentralComunicacao;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.ItfComunicacao;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.ItfDestinatario;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.ItfDisparoComunicacao;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.ItfModeloMensagem;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.ItfTipoTransporteComunicacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.ItensGenericos.basico.UsuarioOSistema;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfUsuario;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.ItffabricaTrasporteComunicacao;

/**
 *
 * @author desenvolvedor
 */
public class CentralComunicaoWebPadrao extends CentralComunicaoAbstrato implements ItfCentralComunicacao {

    private final List<ItfComunicacao> comunicacoesAtivas = new ArrayList<>();
    private final List<ItfComunicacao> comunicacoesHistorico = new ArrayList<>();
    private final ArmazenamentoComunicacaoTransient aramazenamento;

    public CentralComunicaoWebPadrao() {
        aramazenamento = new ArmazenamentoComunicacaoTransient();
    }

    @Override
    public ItfComunicacao gerarComunicacaoEntre_Usuairos(FabTipoComunicacao tipocomunicacao,
            ItfUsuario pRemetente, ItfDestinatario pDestinatario,
            String mensagem,
            ItffabricaTrasporteComunicacao... tiposTransporte
    ) {

        try {
            ItfComunicacao comunicacao
                    = new ComunicacaoTransient(pRemetente, pDestinatario, tipocomunicacao.getRegistro(), gerarListaTransportes(tiposTransporte));

            comunicacao.setMensagem(mensagem);
            comunicacao.setNome(mensagem);
            if (getAramazenamento().registrarInicioComunicacao(comunicacao)) {
                return comunicacao;
            } else {
                return null;
            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro gerando comunicação entre usuários", t);
            return null;
        }
    }

    @Override
    public ItfComunicacao iniciarComunicacaoEntre_Usuairos(FabTipoComunicacao tipocomunicacao, ItfUsuario pRemetente, ItfDestinatario pDestinatario, ItfModeloMensagem mensagem, ItffabricaTrasporteComunicacao... tiposTransporte) {
        ItfComunicacao comunicacao = new ComunicacaoTransient(pRemetente, pDestinatario, tipocomunicacao.getRegistro(), gerarListaTransportes(tiposTransporte));

        getAramazenamento().registrarInicioComunicacao(comunicacao);

        for (ItffabricaTrasporteComunicacao tipoTranposporte : tiposTransporte) {
            try {

                ItfDisparoComunicacao disparo = (ItfDisparoComunicacao) tipoTranposporte.getImplementacaoDoContexto();
                disparo.dispararInicioComunicacao(comunicacao);
            } catch (Throwable t) {
                SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro enviando mensagem de comunicação", t);
            }
        }
        throw new UnsupportedOperationException("Processador de mensagem ainda não implementado;");

    }

    @Override
    public FabTipoRespostaComunicacao aguardarRespostaComunicacao(ItfTipoTransporteComunicacao pTransporte,
            ItfComunicacao pComunicacao, int tempoAguardar, FabTipoRespostaComunicacao pTipoRespostaTempoFinal) {
        FabTipoComunicacao tipocomunicacao = pComunicacao.getTipoComunicacao().getFabTipoComunicacao();

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

    @Override
    public ItfComunicacao iniciarComunicacaoSistema_Usuairo(FabTipoComunicacao tipocomunicacao,
            ItfUsuario pUsuario, String mensagem, ItffabricaTrasporteComunicacao... pTiposTransporte) {
        ItfComunicacao comunicacao = new ComunicacaoTransient(pUsuario, pUsuario, tipocomunicacao.getRegistro(), gerarListaTransportes(pTiposTransporte));
        comunicacao.setMensagem(mensagem);
        comunicacao.setNome(mensagem);
        if (getAramazenamento().registrarInicioComunicacao(comunicacao)) {
            return comunicacao;
        } else {
            return null;
        }
    }

    @Override
    public ItfComunicacao gerarComunicacaoSistema_UsuairoLogado(FabTipoComunicacao tipocomunicacao, String mensagem, ItffabricaTrasporteComunicacao... tiposTransporte) {
        ItfComunicacao comunicacao
                = new ComunicacaoTransient(new UsuarioOSistema(), SBCore.getUsuarioLogado(),
                        tipocomunicacao.getRegistro(), gerarListaTransportes(tiposTransporte));
        comunicacao.setMensagem(mensagem);
        comunicacao.setNome(mensagem);
        if (getAramazenamento().registrarInicioComunicacao(comunicacao)) {

            return comunicacao;
        } else {
            return null;
        }
    }

    @Override
    public ItfArmazenamentoComunicacao getAramazenamento() {
        return aramazenamento;
    }

    @Override
    public ItffabricaTrasporteComunicacao getFabricaTransportePadrao() {
        return ERPTransporteComunicacao.INTRANET_MENU;
    }

}
