package com.super_bits.modulosSB.webPaginas.JSFBeans.SBBeanModel.depreciado.seletorItem;

import com.super_bits.modulosSB.Persistencia.dao.DaoGenerico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ComoEntidadeSimples;
import com.super_bits.modulosSB.webPaginas.JSFBeans.SBBeanModel.depreciado.B_ItemGenerico;
import java.util.ArrayList;
import java.util.List;

public abstract class B_selecaoOld<T extends ComoEntidadeSimples> extends B_ItemGenerico {

    private List<T> todos;
    private List<T> selecionados;
    private List<T> naoselecionados;
    private DaoGenerico<T> daogen;
    Boolean carregaTodosIni = false;

    public void startBean() {
        DaoGenerico<T> teste = new DaoGenerico<T>(classe) {
        };
        if (carregaTodosIni == true) {
            carregaTodos();
        }

    }

    public B_selecaoOld(Class<?> pClasse) {
        super(pClasse);
        Class<?> putamerda = getTipo();

    }

    public void carregaTodos() {

        System.out.println(getTipo().getCanonicalName() + getTipo().getSimpleName() + getTipo().getFields());

        todos = daogen.todos();
        selecionados = todos;
        naoselecionados = new ArrayList<T>();
    }

    public List<T> getTodos() {
        return todos;
    }

    public void setSelecionados(List<T> selecionados) {
        this.selecionados = selecionados;
    }

    public void selecionaNovo(T novoSelecionado) {
        // TODO APAGAR ESTA CLASSE
    }

    public List<T> getSelecionados() {
        return selecionados;
    }

    public void setNaoselecionados(List<T> naoselecionados) {
        //TODO APAGAR ESTA CLASSE
        this.naoselecionados = naoselecionados;
    }

    public List<T> getNaoselecionados() {
        return naoselecionados;
    }

}
