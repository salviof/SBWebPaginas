package com.super_bits.modulosSB.webPaginas.JSFBeans.SBBeanModel.DEPRECIADO;

import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ComoEntidadeSimples;
import com.super_bits.modulosSB.webPaginas.JSFBeans.SBBeanModel.B_SeletorItem;
import java.util.ArrayList;
import java.util.List;

public class B_SeletorItemFacil<T extends ComoEntidadeSimples> extends B_SeletorItem<T> {

    private List<B_Lista<? extends ComoEntidadeSimples>> opcoesOrdenadas = new ArrayList<>();

    boolean modoOrdenado = true;
    boolean modoAvancado = false;

    public List<B_Lista<? extends ComoEntidadeSimples>> getOpcoesOrdenadas() {
        return opcoesOrdenadas;
    }

    public void setOpcoesOrdenadas(List<B_Lista<? extends ComoEntidadeSimples>> opcoesOrdenadas) {
        this.opcoesOrdenadas = opcoesOrdenadas;
    }

    public boolean isModoOrdenado() {
        return modoOrdenado;
    }

    public void setModoOrdenado(boolean modoOrdenado) {
        this.modoOrdenado = modoOrdenado;
    }

    public boolean isModoAvancado() {
        return modoAvancado;
    }

    public void setModoAvancado(boolean modoAvancado) {
        this.modoAvancado = modoAvancado;
    }

    public B_SeletorItemFacil(List<? extends ComoEntidadeSimples> pOpcoes, List<B_Lista<? extends ComoEntidadeSimples>> pOpcoesOrdenadas) {
        super(pOpcoes);
        opcoesOrdenadas = pOpcoesOrdenadas;
    }

    public void mostrarTodos() {
        modoOrdenado = false;
        modoAvancado = true;
    }

    public void mostrarOrdenado() {
        modoOrdenado = true;
        modoAvancado = false;
    }

    protected void zerarItemSelecionado() {
        itemSelecionado = null;
    }
}
