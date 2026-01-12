package com.super_bits.modulosSB.webPaginas.JSFBeans.SBBeanModel;

import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoEntidadeSimples;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class B_SeletorItem<T> implements Serializable {

    private List<? extends ComoEntidadeSimples> opcoes;
    public ComoEntidadeSimples itemSelecionado;

    public List<? extends ComoEntidadeSimples> getOpcoes() {
        return opcoes;
    }

    public void setOpcoes(List<? extends ComoEntidadeSimples> opcoes) {
        this.opcoes = opcoes;
    }

    public ComoEntidadeSimples getItemSelecionado() {
        return itemSelecionado;
    }

    public void setItemSelecionado(ComoEntidadeSimples itemSelecionado) {
        this.itemSelecionado = itemSelecionado;
    }

    public B_SeletorItem(List<? extends ComoEntidadeSimples> pOpcoes) {
        opcoes = pOpcoes;
    }

    public List<ComoEntidadeSimples> autocompletUpdate(String query) {
        List<ComoEntidadeSimples> sugestoes = new ArrayList<>();

        for (ComoEntidadeSimples item : opcoes) {
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
