/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.modal;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.ItfComunicacao;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.ItfRespostaComunicacao;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import org.primefaces.PrimeFaces;
import com.super_bits.modulosSB.webPaginas.JSFBeans.modal.abstrato.PgModalPaginaAtual;

/**
 *
 * @author SalvioF
 */
public abstract class PgModalRespostaAbstrato extends PgModalPaginaAtual implements ItfModalRespostaComComunicacao {

    private ItfRespostaComunicacao respostaSelecionada;
    protected ItfComunicacao comunicacao;

    @Override
    public ItfRespostaComunicacao getRespostaSelecionada() {
        return respostaSelecionada;
    }

    @Override
    public void setRespostaSelecionada(ItfRespostaComunicacao respostaSelecionada) {
        this.respostaSelecionada = respostaSelecionada;
    }

    /**
     *
     * @return @deprecated Utilze comunicaçãoAguardandoREsposta
     */
    @Override
    @Deprecated
    public ItfComunicacao getComunicacao() {
        return getComunincacaoAguardandoResposta();

    }

    @Override
    public ItfComunicacao getComunincacaoAguardandoResposta() {
        try {
            if (comunicacao != null) {
                return comunicacao;
            } else {
                throw new UnsupportedOperationException("a comunicação não foi definida");
            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro obtendo comunicação vinculada ao modal.", t);
            return null;
        }
    }

    @Override
    public void enviarRespostaEExecutarAcao() {
        try {
            if (getRespostaSelecionada() == null) {
                throw new UnsupportedOperationException("A resposta não foi configurada");
            }
            getPaginaVinculada().setTipoRespostaParaAcaoAtual(getRespostaSelecionada().getTipoResposta());

            if (getPaginaVinculada().getModalAtual().isUmModalComDadados()) {
                if (getPaginaVinculada().getModalAtual().getComoModalDados().getCampoSelecionado() != null) {
                    getPaginaVinculada().metodoRespostaModal(getPaginaVinculada().getModalAtual().getComoModalDados().getCampoSelecionado().getValor());
                    // RequestContext.getCurrentInstance().closeDialog(getPaginaVinculada().getModalDados().getCampoSelecionado().getValor());
                } else {
                    if (getPaginaVinculada().getModalAtual().getComoModalDados().getEntidadeSelecionada() != null) {
                        //    RequestContext.getCurrentInstance().closeDialog(getPaginaVinculada().getModalDados().getEntidadeSelecionada());
                        getPaginaVinculada().metodoRespostaModal(getPaginaVinculada().getModalAtual().getComoModalDados().getEntidadeSelecionada());
                    } else {
                        getPaginaVinculada().metodoRespostaModal();
                    }
                }
            } else {
                // getPaginaVinculada().metodoRespostaModal();
            }

            PrimeFaces.current().dialog().closeDynamic(getRespostaSelecionada());
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro enviando resposta para formulario principal", t);
        }
    }

}
