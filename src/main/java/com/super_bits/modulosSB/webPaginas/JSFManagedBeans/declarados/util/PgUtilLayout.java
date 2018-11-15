/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFManagedBeans.declarados.util;

import com.google.common.collect.Lists;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreListas;
import com.super_bits.modulosSB.SBCore.modulos.Controller.AcaoTransient;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.ItfGrupoCampos;
import com.super_bits.modulosSB.SBCore.modulos.view.telas.ColunaTela;
import com.super_bits.modulosSB.SBCore.modulos.view.telas.LayoutComponentesEmTela;
import com.super_bits.modulosSB.SBCore.modulos.view.telas.LayoutComponentesEmTelaComGrupoDeAcoes;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.interfaces.ItfPaginaAtual;
import com.super_bits.modulosSB.webPaginas.controller.sessao.SessaoAtualSBWP;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author desenvolvedor
 */
@RequestScoped
@Named
public class PgUtilLayout implements Serializable {

    private static final List<ItfAcaoDoSistema> ACAO_SELECAO_REGISTRO = UtilSBCoreListas.gerarComoLista(new AcaoTransient("nomeAcao", "iconeAcao"));

    @Inject
    private ItfPaginaAtual paginaAtual;

    @Deprecated
    public LayoutComponentesEmTelaComGrupoDeAcoes gerarLayout(ItfGrupoCampos pGrupoCampo, List<ItfAcaoDoSistema> pAcoes) {
        return getLayoutCamposComAcao(pGrupoCampo, pAcoes);
    }

    @Deprecated
    public LayoutComponentesEmTela gerarLayout(ItfGrupoCampos pGrupoCampo) {
        return gerarLayoutGrupoCampo(pGrupoCampo);
    }

    public LayoutComponentesEmTela gerarLayoutGrupoCampo(ItfGrupoCampos pGrupoCampo) {
        return paginaAtual.getInfoPagina().getComoFormularioWeb().getInfoLayout().gerarLayout(pGrupoCampo);
    }

    public LayoutComponentesEmTela gerarLayoutGrupoCampoSeletorItem(ItfGrupoCampos pGrupoCampo) {
        return paginaAtual.getInfoPagina().getComoFormularioWeb().getInfoLayout().gerarLayout(pGrupoCampo, ACAO_SELECAO_REGISTRO);
    }

    public LayoutComponentesEmTelaComGrupoDeAcoes getLayoutCamposComAcao(ItfGrupoCampos pGrupoCampo, List<ItfAcaoDoSistema> pAcoes) {
        return paginaAtual.getInfoPagina().getComoFormularioWeb().getInfoLayout().gerarLayout(pGrupoCampo, pAcoes);
    }

    @Deprecated
    public ColunaTela getColunaLayoutByNomeCampoCompletoComGrupo(String pNomeCampo) {
        return paginaAtual.getInfoPagina().getComoFormularioWeb().getInfoLayout().getColunaLayoutByNomeCampoCompletoComGrupo(pNomeCampo);
    }

    @Deprecated
    public ColunaTela getUltimaColunaDoLayoyt(String pNomeLayout) {
        return paginaAtual.getInfoPagina().getComoFormularioWeb().getInfoLayout().getUltimaColunaDoLayoyt(pNomeLayout);
    }

    @Deprecated
    public boolean verificaExisteLayout(String pNomeLayot) {
        return false;

    }

}
