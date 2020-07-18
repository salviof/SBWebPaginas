/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.modal;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoController;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.ComunicacaoAcaoSistema;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.interfaces.ItfB_PaginaComEntityManager;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.interfaces.ItfModalDados;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.reflexao.anotacoes.InfoPagina;
import com.super_bits.modulosSB.webPaginas.controller.paginasDoSistema.FabAcaoPaginasDoSistema;
import com.super_bits.modulosSB.webPaginas.controller.paginasDoSistema.InfoAcaoPaginaDoSistema;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

/**
 *
 * @author SalvioF
 */
@Named
@ViewScoped
@InfoAcaoPaginaDoSistema(acao = FabAcaoPaginasDoSistema.PAGINA_NATIVA_MODAL_JUSTIFICATIVA_MB)
@InfoPagina(nomeCurto = "Justificativa", tags = "Justificativa", umModal = true)
public class PgModalJustificativa extends PgModalDados implements ItfModalDados {

    @PostConstruct
    @Override
    public void inicio() {
        super.inicio();
        comunicacao = new ComunicacaoAcaoSistema(getPaginaVinculada().getAcaoSelecionada().getComoController(), getPaginaVinculada().getBeanSelecionado());
        setCampoSelecionado(getCampoJustificativa());
    }

    @Override
    public void enviarRespostaEExecutarAcao() {

        try {
            if (getRespostaSelecionada().getTipoResposta().isRespostasPosiva()) {
                if (getObServacaoDeLog() == null || getObServacaoDeLog().isEmpty()) {
                    throw new UnsupportedOperationException("A justificatica não foi preenchida");
                }
                getPaginaVinculada().getBeanSelecionado().adicionarJustificativaExecucaoAcao(getPaginaVinculada().getAcaoSelecionada(), getJustificativa());
            }

            super.enviarRespostaEExecutarAcao();

        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro enviando resposta para pagina", t);
        }
    }

    public void setJustificativa(String justificativa) {
        setObServacaoDeLog(justificativa);
    }

    public String getJustificativa() {
        return getObServacaoDeLog();
    }

    public ItfCampoInstanciado getCampoJustificativa() {
        String campoSTR = "campo não Determinado";
        try {

            if (getCampoSelecionado() == null && getPaginaVinculada().getBeanSelecionado() != null) {
                ComunicacaoAcaoSistema comunic = (ComunicacaoAcaoSistema) comunicacao;
                ItfAcaoController acaoCominicacao = comunic.getAcaoVinculada();

                campoSTR = acaoCominicacao.getCampoJustificativa();
                if (campoSTR == null) {
                    return getCampoSelecionado();
                } else {
                    return getPaginaVinculada().getBeanSelecionado().getCampoByNomeOuAnotacao(campoSTR);
                }
            } else {
                return getCampoSelecionado();
            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro obtendo campo " + campoSTR + " em:" + getPaginaVinculada().getBeanSelecionado(), t);
        }
        return null;
    }

    @Override
    public ItfB_PaginaComEntityManager getComoPaginaComEntityManager() {
        return getPaginaVinculada();
    }

}
