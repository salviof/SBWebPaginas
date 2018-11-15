package com.super_bits.modulosSB.webPaginas.JSFBeans.SBBeanModel;

import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class B_SeletorItem<T> implements Serializable {

    private List<? extends ItfBeanSimples> opcoes;
    public ItfBeanSimples itemSelecionado;

    public List<? extends ItfBeanSimples> getOpcoes() {
        return opcoes;
    }

    public void setOpcoes(List<? extends ItfBeanSimples> opcoes) {
        this.opcoes = opcoes;
    }

    public ItfBeanSimples getItemSelecionado() {
        return itemSelecionado;
    }

    public void setItemSelecionado(ItfBeanSimples itemSelecionado) {
        this.itemSelecionado = itemSelecionado;
    }

    public B_SeletorItem(List<? extends ItfBeanSimples> pOpcoes) {
        opcoes = pOpcoes;
    }

    public List<ItfBeanSimples> autocompletUpdate(String query) {
        List<ItfBeanSimples> sugestoes = new ArrayList<>();

        for (ItfBeanSimples item : opcoes) {
            if (item.getNomeCurto().toLowerCase().contains(query)) {
                sugestoes.add(item);
            }
        }
        return sugestoes;
    }

    public void adicionarItemSelecionado() {
        System.out.println("selecionado" + itemSelecionado.getNomeCurto());
    }
}
