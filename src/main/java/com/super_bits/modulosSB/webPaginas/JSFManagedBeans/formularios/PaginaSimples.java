package com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios;

import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;
import com.super_bits.modulosSB.webPaginas.ConfigGeral.SBWebPaginas;

@Deprecated
public class PaginaSimples extends B_Pagina {

    public PaginaSimples(String pNomeCurto, String pRecurso, String[] pTags) {

    }

    @Override
    public void abrePagina() {
        super.abrePagina();

    }

    @Override
    public void fecharPagina() {
        super.fecharPagina();

    }

    @Override
    public String defineTitulo() {
        return SBWebPaginas.getTituloApp();
    }

    @Override
    public String defineNomeLink() {
        return "nomelink";
    }

    @Override
    public String defineDescricao() {
        return "Descricao";
    }

    @Override
    public Long getId() {
        return 99l;
    }

    @Override
    public ItfBeanSimples getBeanSelecionado() {
        return null;
    }

    @Override
    public void setBeanSelecionado(ItfBeanSimples pBeanSimples) {

    }

}
