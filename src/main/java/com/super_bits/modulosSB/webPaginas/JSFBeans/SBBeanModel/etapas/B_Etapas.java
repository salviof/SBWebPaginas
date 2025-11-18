/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.SBBeanModel.etapas;

import com.super_bits.modulos.SBAcessosModel.model.acoes.AcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.icones.FabIconeFontAwesome;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.interfaces.ItfPaginaAtual;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ComoAcaoDoSistema;

/**
 *
 * BEAN DE ETAPAS QUE GERENCIA UMA LISTA DE ACOES E A AÇÃO ATUAL DESSA LISTA.
 * IDEAL PARA DESENVOLVIMENTO DE WIZARDS.
 *
 *
 *
 *
 * @author desenvolvedor
 */
public class B_Etapas implements Serializable {

    private List<ComoAcaoDoSistema> listaDeAcoes;

    private ComoAcaoDoSistema acaoAtual;

    private final ComoAcaoDoSistema acaoProximo;

    private final ComoAcaoDoSistema acaoVoltar;

    @Inject
    private ItfPaginaAtual paginaAtual;

    /**
     *
     *
     *
     * RECEBE UMA SEQUENCIA DE AÇÕES
     *
     * @param acaoInicial AÇÃO DA ETAPA INICIAL
     * @param pAcoes AÇÕES DAS ETAPAS SEGUINTES
     */
    public B_Etapas(ComoAcaoDoSistema acaoInicial, ComoAcaoDoSistema... pAcoes) {

        acaoProximo = new AcaoDoSistema();
        acaoProximo.setNomeAcao("Proximo");
        acaoProximo.setDescricao("Avança para proxima pagina");
        acaoProximo.setIconeAcao(FabIconeFontAwesome.REG_PROXIMO.getIcone().getTagHtml());

        acaoVoltar = new AcaoDoSistema();
        acaoVoltar.setNomeAcao("Voltar");
        acaoVoltar.setDescricao("Retorna para pagina anterior");
        acaoVoltar.setIconeAcao(FabIconeFontAwesome.REG_ANTERIOR.getIcone().getTagHtml());

        listaDeAcoes = new ArrayList<>();

        acaoAtual = acaoInicial;
        listaDeAcoes.add(acaoAtual);
        FabIconeFontAwesome.COMERCIO_BITCOINS.getIcone().getTagHtml();
        for (int i = 0; i < pAcoes.length; i++) {

            listaDeAcoes.add(pAcoes[i]);

        }

    }

    public boolean isEmUltimaEtapa() {
        return (getIndiceEtapaAtual() == listaDeAcoes.size() - 1);

    }

    public boolean isEmPrimeiraEtapa() {
        return (getIndiceEtapaAtual() == 0);
    }

    public List<ComoAcaoDoSistema> getListaDeAcoes() {
        return listaDeAcoes;
    }

    public void setListaDeAcoes(List<ComoAcaoDoSistema> listaDeAcoes) {
        this.listaDeAcoes = listaDeAcoes;
    }

    public ComoAcaoDoSistema getAcaoAtual() {
        return acaoAtual;
    }

    public void setAcaoAtual(ComoAcaoDoSistema acaoAtual) {
        this.acaoAtual = acaoAtual;
    }

    /**
     *
     * @param pAcao
     * @return ESTE METODO RETORNA SE UMA AÇÃO É A AÇÃO ATUAL (TRUE SE SIM /
     * FALSE SE NÃO)
     */
    public boolean isSelecionadaAtual(ComoAcaoDoSistema pAcao) {

        if (pAcao.equals(acaoAtual)) {

            return true;
        } else {

            return false;
        }

    }

    public ComoAcaoDoSistema getAcaoProximo() {
        return acaoProximo;
    }

    public ComoAcaoDoSistema getAcaoVoltar() {
        return acaoVoltar;
    }

    public ArrayList listaEtapasMobile() {
        final int idxinicial = 0;
        final int idxfinal = listaDeAcoes.size() - 1;

        ArrayList<ComoAcaoDoSistema> listaMobile = new ArrayList();

        for (int i = 0; i < listaDeAcoes.size(); i++) {

            if (acaoAtual == listaDeAcoes.get(i)) {

                if (i == idxinicial) {

                    listaMobile.add(listaDeAcoes.get(i));

                    if (i + 1 < idxfinal) {
                        listaMobile.add(listaDeAcoes.get(i + 1));
                    }

                    if (i + 2 < idxfinal) {
                        listaMobile.add(listaDeAcoes.get(i + 2));
                    }

                }

                if (i > 0 && i < idxfinal) {

                    listaMobile.add(listaDeAcoes.get(i - 1));

                    listaMobile.add(listaDeAcoes.get(i));

                    if (i + 1 < idxfinal) {
                        listaMobile.add(listaDeAcoes.get(i + 1));
                    }

                }

                if (i == idxfinal) {

                    if (i - 2 >= 0) {

                        listaMobile.add(listaDeAcoes.get(i - 2));

                    }

                    if (i - 1 >= 0) {

                        listaMobile.add(listaDeAcoes.get(i - 1));

                    }

                    listaMobile.add(listaDeAcoes.get(i));

                }

            }

        }

        return listaMobile;

    }

    public void irParaProximaEtapa() {

        for (int i = 0; i < listaDeAcoes.size(); i++) {

            if (acaoAtual == listaDeAcoes.get(i)) {

                if (i == listaDeAcoes.size() - 1) {

                    acaoAtual = listaDeAcoes.get(i);

                } else {
                    acaoAtual = listaDeAcoes.get(i + 1);
                }

                break;

            }

        }

    }

    public void irParaEtapaAnterior() {

        int idxAtual = getIndiceEtapaAtual();

        if (idxAtual == 0) {
            acaoAtual = listaDeAcoes.get(idxAtual);
        } else {
            acaoAtual = listaDeAcoes.get(idxAtual - 1);
        }

    }

    public void irParaProximaEtapaExecutandoAcaoPaginaAtual() {

        irParaProximaEtapa();

        paginaAtual.getInfoPagina().getComoPaginaDeGestao().getComoPaginaEntidade().setAcaoSelecionada(acaoAtual);

        paginaAtual.getInfoPagina().getComoFormularioWeb().executarAcaoSelecionada();
    }

    private int getIndiceEtapa(ComoAcaoDoSistema pAcao) {
        for (int i = 0; i < listaDeAcoes.size(); i++) {

            if (pAcao == listaDeAcoes.get(i)) {
                return i;
            }

        }
        return 0;
    }

    private int getIndiceEtapaAtual() {
        return getIndiceEtapa(acaoAtual);
    }

    /**
     *
     * @param pAcao
     * @return
     */
    public int getNumeroEtapa(ComoAcaoDoSistema pAcao) {
        return getIndiceEtapa(pAcao) + 1;
    }

}
