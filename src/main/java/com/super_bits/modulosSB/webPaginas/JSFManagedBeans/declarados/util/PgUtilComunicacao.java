/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFManagedBeans.declarados.util;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.ComunicacaoAcaoSistema;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.ItfRespostaComunicacao;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.interfaces.ItfPaginaAtual;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWP_JSFTools;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import org.primefaces.event.SelectEvent;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.ItfDialogo;

/**
 *
 * @author desenvolvedor
 */
@RequestScoped
@Named
public class PgUtilComunicacao implements Serializable {

    @Inject
    private PgUtil paginaUtil;
    @Inject
    private ItfPaginaAtual paginaAtual;
    private String codigoComunicacao;
    private String idTipoResposta;

    public void exibirModalComunicacaoCodigo() {
        try {
            if (codigoComunicacao == null || codigoComunicacao.equals("undefined")) {
                throw new UnsupportedOperationException("Defina o Código de comunicação para exeção deste método");
            }
            paginaAtual.getInfoPagina().getComoPaginaDeGestao().adicionarCodigoCoversa(codigoComunicacao);
            paginaUtil.exibirModal(UtilSBWP_JSFTools.FORMULARIO_MODAL_COMUNICACAO);
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro ao exibir comunicação por código ", t);
        }

    }

    public void respostaRapida() {
        try {
            if (codigoComunicacao == null) {
                throw new UnsupportedOperationException("Defina o Código de comunicação para exeção deste método");
            }
            if (idTipoResposta == null) {
                throw new UnsupportedOperationException("Defina o id do tipo de resposta");
            }
            ItfDialogo cm = SBCore.getServicoComunicacao().getComnunicacaoRegistrada(codigoComunicacao);
            ItfRespostaComunicacao resposta = cm.getRepostasPossiveis().stream()
                    .filter(resp -> resp.getTipoResposta().getId() == Long.valueOf(idTipoResposta))
                    .findFirst().get();
            SBCore.getServicoComunicacao().responderComunicacao(cm.getCodigoSelo(), resposta);
            paginaUtil.atualizaTelaPorID("idAreaSBTopoInterface");
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro ao exibir comunicação por código ", t);
        }
    }

    public void removerNotificacoes() {
        SBCore.getServicoComunicacao().getComunicacoesAguardandoRespostaDoDestinatario(SBCore.getUsuarioLogado()).forEach(cm -> {
            SBCore.getServicoComunicacao().responderComunicacao(cm.getCodigoSelo(), cm.getRepostasPossiveis().get(0));

        });
        paginaUtil.atualizaTelaPorID("idAreaSBTopoInterface");
    }

    public void responderComunicacao(ComunicacaoAcaoSistema pComunicacao, ItfRespostaComunicacao pResposta) {
        SBCore.getServicoComunicacao().responderComunicacao(pComunicacao.getCodigoSelo(), pResposta);
    }

    public String getCodigoComunicacao() {
        return codigoComunicacao;
    }

    public void setCodigoComunicacao(String codigoComunicacao) {
        this.codigoComunicacao = codigoComunicacao;
    }

    public void metodoRespostaModal(SelectEvent event) {
        //paginaUtil.atualizaTelaPorID("painelMenuUsuario");
        paginaUtil.enviaMensagem("Teste");
    }

    public String getIdTipoResposta() {
        return idTipoResposta;
    }

    public void setIdTipoResposta(String idTipoResposta) {
        this.idTipoResposta = idTipoResposta;
    }

}
