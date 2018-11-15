package com.super_bits.modulosSB.webPaginas.JSFBeans.SBBeanModel.DEPRECIADO;

import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;
import com.super_bits.modulosSB.webPaginas.JSFBeans.SBBeanModel.B_SeletorItem;
import java.util.ArrayList;
import java.util.List;

public class B_SeletorItemFacil<T extends ItfBeanSimples> extends B_SeletorItem<T> {

    private List<B_Lista<? extends ItfBeanSimples>> opcoesOrdenadas = new ArrayList<>();

    boolean modoOrdenado = true;
    boolean modoAvancado = false;

    public List<B_Lista<? extends ItfBeanSimples>> getOpcoesOrdenadas() {
        return opcoesOrdenadas;
    }

    public void setOpcoesOrdenadas(List<B_Lista<? extends ItfBeanSimples>> opcoesOrdenadas) {
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

    public B_SeletorItemFacil(List<? extends ItfBeanSimples> pOpcoes, List<B_Lista<? extends ItfBeanSimples>> pOpcoesOrdenadas) {
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
