/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.PrimeFacesBeanModel;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.seletores.seletorMultiplo.B_listaComOrigemAbs;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimplesSomenteLeitura;
import java.io.Serializable;
import java.util.List;
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

    public DualListModel<T> getDualListPrime() {
        if (dualListPrime == null) {
            dualListPrime = new DualListModel<>((List) getOrigem(), (List) getListaObjetosSelecionados());
        }
        if (getOrigem().isEmpty() && getListaObjetosSelecionados().isEmpty()) {

            reloadOrigem();
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
            dualListPrime.setSource((List) getOrigem());
            dualListPrime.setTarget((List) getListaObjetosSelecionados());
        }
    }

    @Override
    public void atualizaOrigemPelaSelecao() {

        if (dualListPrime != null) {
            getOrigem().clear();
            getListaObjetosSelecionados().clear();
            for (T itemSrc : dualListPrime.getSource()) {
                getOrigem().add((T) itemSrc);
            }
            for (T itemLista : dualListPrime.getTarget()) {
                getListaObjetosSelecionados().add((T) itemLista);
            }
        }
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
