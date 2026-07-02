/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.super_bits.modulosSB.webPaginas.push;

import com.super_bits.modulosSB.SBCore.ConfigGeral.CarameloCode;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.ComoDialogo;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.FabTipoRespostaComunicacao;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.ItfTipoCanalComunicacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.dialogo.resposta.RespostaComunicacao;
import com.super_bits.modulosSB.SBCore.modulos.servicosCore.ComoServicoComunicacaoUI;
import com.super_bits.modulosSB.SBCore.modulos.servicosCore.ErroDetectandoTelaBloqueio;
import com.super_bits.modulosSB.SBCore.modulos.servicosCore.ErroSelandoDialogo;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.declarados.webSite.InfoWebApp;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.B_Pagina;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.interfaces.ItfB_Pagina;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.interfaces.ItfPaginaAtual;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWPServletTools;
import java.util.ArrayList;
import java.util.List;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

/**
 *
 * @author salvio
 */
public class NotificadorIntranetJSF implements ComoServicoComunicacaoUI {

    @Override
    public boolean notificarViaMenu(ComoDialogo pDialogo) {

        InfoWebApp aplicacao = (InfoWebApp) UtilSBWPServletTools.getBeanByNamed("infoWebApp", InfoWebApp.class);
        return aplicacao.publicar(pDialogo);

    }

    @Override
    public boolean notificarViaBloqueioTEla(ComoDialogo pDialogo) {
        InfoWebApp aplicacao = (InfoWebApp) UtilSBWPServletTools.getBeanByNamed("infoWebApp", InfoWebApp.class);
        return aplicacao.publicar(pDialogo);
    }

    public FabTipoRespostaComunicacao aguardarRespostaComunicacaoModalTransitorio(ItfTipoCanalComunicacao pCanal, ComoDialogo pComunicacao, int pTempoAguardar, FabTipoRespostaComunicacao pTipoRespostaTempoFinal) throws ErroDetectandoTelaBloqueio {
        ItfPaginaAtual paginaAtual = null;
        try {
            paginaAtual = (ItfPaginaAtual) UtilSBWPServletTools.getBeanByNamed("paginaAtual", ItfPaginaAtual.class);
            //paginaAtual.getInfoPagina().getComoFormularioWeb().adicionarCodigoCoversa(pComunicacao.getCodigoSelo());
            if (paginaAtual == null) {
                throw new ErroDetectandoTelaBloqueio("Falha pesquisando pagina atual");
            }
        } catch (Throwable t) {
            throw new ErroDetectandoTelaBloqueio("Falha pesquisando pagina atual" + t.getMessage());
        }
        //TODO Implementar capacidade de enviar apenas na aba do disparo
        String codigoInstancia = ((B_Pagina) paginaAtual.getInfoPagina()).getPaginaInstanciaID();
        pComunicacao.getCPinst("paginaInstanciaID").setValor(codigoInstancia);
        ((ItfB_Pagina) paginaAtual.getInfoPagina()).registrarDialogoTransitorio(pComunicacao);

        try {

            CarameloCode.getServicoComunicacao().selarComunicacao(pComunicacao);
            notificarViaBloqueioTEla(pComunicacao);

        } catch (ErroSelandoDialogo ex) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Falha gerando selo do dialogo", ex);
        }
        long deadline = System.currentTimeMillis() + (pTempoAguardar * 1000);
        boolean respondeu = false;
        FabTipoRespostaComunicacao resposta = null;
        while (System.currentTimeMillis() < deadline && !respondeu) {

            try {
                if (paginaAtual.getInfoPagina().getComoFormularioWeb().getRespostaAcaoAtual() != null) {
                    try {
                        List<RespostaComunicacao> respostasListas = new ArrayList();
                        ((ItfB_Pagina) paginaAtual.getInfoPagina()).getRespostasDialogosTransitorios().values().stream().forEach(respostasListas::add);

                        if (((ItfB_Pagina) paginaAtual.getInfoPagina()).getRespostasDialogosTransitorios().containsKey(pComunicacao.getCodigoSelo())) {
                            respondeu = true;
                            resposta = ((ItfB_Pagina) paginaAtual.getInfoPagina()).getRespostasDialogosTransitorios().get(pComunicacao.getCodigoSelo()).getTipoResposta().getFabricaTipoResposta();
                        }
                    } catch (Throwable t) {
                        throw new ErroDetectandoTelaBloqueio("Falha navegando nas telas respostas do formulário");
                    }

                }
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();

            }
        }
        if (resposta == null) {
            resposta = pTipoRespostaTempoFinal;
        }
        return resposta;
    }

}
