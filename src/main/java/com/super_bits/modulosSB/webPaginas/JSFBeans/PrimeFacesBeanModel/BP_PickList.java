/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.PrimeFacesBeanModel;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.seletores.seletorMultiplo.B_listaComOrigemAbs;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jersey.repackaged.com.google.common.collect.Lists;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import org.primefaces.model.DualListModel;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ComoEntidadeSimplesSomenteLeitura;

/**
 *
 * @author SAlvio furnbino
 * @param <T>
 */
public class BP_PickList<T extends ComoEntidadeSimplesSomenteLeitura> extends B_listaComOrigemAbs<T> implements Serializable {

    private DualListModel<T> dualListPrime;
    private int indiceItemSelecionado;

    /**
     *
     * @param pLista LISTA QUE IRÁ RECEBER OS VALORES
     * @param pOrigem LISTA DE ONDE VEM OS VALORES
     */
    public BP_PickList(List<T> pLista, List<T> pOrigem) {
        super(pLista, pOrigem);
        ajuste(false);
        dualListPrime = new DualListModel<>((List) getOrigem(), (List) getListaObjetosSelecionados());
    }

    public BP_PickList(ItfCampoInstanciado pCampoInstanciado) {
        super(pCampoInstanciado);

    }

    @Override
    public List<T> getOrigem() {
        return super.getOrigem(); //chamada super do metodo (implementação classe pai)
    }

    @Override
    public void atualizarListaCompleta() {

        super.atualizarListaCompleta();
        if (dualListPrime != null) {
            atualizaPickListViewContexto();
        }

    }

    @Override
    public void limparSelecao() {
        super.limparSelecao(); //To change body of generated methods, choose Tools | Templates.
        if (dualListPrime != null) {
            dualListPrime.getTarget().clear();
        }
    }

    public DualListModel<T> getDualListPrime() {
        try {
            if (dualListPrime == null) {
                dualListPrime = new DualListModel<>(Lists.newLinkedList(), Lists.newLinkedList());
                getListaObjetosSelecionados().forEach(dualListPrime.getTarget()::add);
                atualizaPickListViewContexto();
            }
            if (dualListPrime.getSource().isEmpty()
                    && dualListPrime.getTarget().isEmpty()) {
                super.atualizarListaCompleta();
                atualizaPickListViewContexto();
            }

            return dualListPrime;
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro criando " + DualListModel.class.getSimpleName() + " "
                    + t.getMessage(),
                    t
            );
            return null;
        }
    }

    public void setDualListPrime(DualListModel<T> dualListPrime) {

        System.out.println("Dual Lista Selecionada=" + dualListPrime.getTarget());
        System.out.println("Dual Lista Source=" + dualListPrime.getSource());

        this.dualListPrime = dualListPrime;
        atualizaOrigemPelaSelecao();
    }

    @Override
    public void atualizaPickListViewContexto() {
        if (dualListPrime != null) {

            if (!dualListPrime.getTarget().isEmpty()) {
                getListaObjetosSelecionados().clear();
                dualListPrime.getTarget().stream().filter(it -> !getListaObjetosSelecionados().contains(it)).
                        forEach(getListaObjetosSelecionados()::add);

            } else {
                limparSelecao();

            }
            ajuste(true);
            dualListPrime.getSource().clear();
            getOrigem().stream()
                    .filter(it -> !dualListPrime.getTarget().contains(it))
                    .forEach(dualListPrime.getSource()::add);

        }
    }

    @Override
    public void atualizaOrigemPelaSelecao() {

        atualizaPickListViewContexto();

    }

    @Override
    public int getIndiceItemSelecionado() {
        return indiceItemSelecionado;
    }

    @Override
    public void setIndiceItemSelecionado(int pItem) {
        indiceItemSelecionado = pItem;
    }

}
