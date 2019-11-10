/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.PrimeFacesBeanModel;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.seletores.seletorMultiplo.B_listaComOrigemAbs;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimplesSomenteLeitura;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jersey.repackaged.com.google.common.collect.Lists;
import org.primefaces.model.DualListModel;

/**
 *
 * @author SAlvio furnbino
 * @param <T>
 */
public class BP_PickList<T extends ItfBeanSimplesSomenteLeitura> extends B_listaComOrigemAbs<T> implements Serializable {

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

    }

    public DualListModel<T> getDualListPrime() {
        if (dualListPrime == null) {
            dualListPrime = new DualListModel<>(Lists.newLinkedList(), (List) getListaObjetosSelecionados());
            atualizaPickListViewContexto();
        }

        return dualListPrime;
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
                dualListPrime.getTarget().stream().filter(it -> !getListaObjetosSelecionados().contains(it)).
                        forEach(getListaObjetosSelecionados()::add);
                dualListPrime.setTarget(getListaObjetosSelecionados());
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
