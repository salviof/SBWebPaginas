package com.super_bits.modulosSB.webPaginas.JSFBeans.SBBeanModel.depreciado.filtro.old;

import com.super_bits.modulosSB.Persistencia.ConfigGeral.CSBNQ;
import com.super_bits.modulosSB.Persistencia.dao.DaoGenerico;
import com.super_bits.modulosSB.Persistencia.dao.SBNQ;
import com.super_bits.modulosSB.SBCore.UtilGeral.ClasseTipada;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;

//TODO ANALIZAR E EXCLUIR AS CLASSES DE FILTRO DE PAGINA CASO NESCESSÁRIO
@Deprecated
public abstract class MB_FiltrosPagina<T extends ItfBeanSimples> extends ClasseTipada implements
        Serializable {

    private List<T> selecao;
    private Boolean foiInjetado = false;

    private List<MB_Filtro<?>> filtros;

    private String teste = "beanCriado";
    private HashMap<String, Object> parametros = new HashMap<String, Object>();
    private int quantidadeSubFiltro = 0;

    protected abstract CSBNQ.Qr qrySelecao();

    protected abstract String[] relacoes();

    @PostConstruct
    private void startBean() {
        foiInjetado = true;
        setFiltros();
        setParametros();
    }

    @SuppressWarnings("unchecked")
    private boolean houveAlteracao() {
        boolean resp = false;
        for (MB_Filtro<?> filtro : filtros) {
            Object param = parametros.get("p"
                    + filtro.getTipo().getSimpleName());
            List<Integer> teste = (List<Integer>) param;

            if (!teste.equals(filtro.getSelecaoSimples())) {
                resp = true;
            }
        }
        return resp;
    }

    private void setParametros() {

        for (MB_Filtro<?> filtro : filtros) {
            List<Long> teste
                    = filtro.getSelecaoSimples();
            parametros.put(filtro.getNomeParametroFiltroPagina(),
                    filtro.getSelecaoSimples());
        }
        int i = 0;
    }

    private void configSelecao() {
        // se nao ouve alteracao e ja existe selecao sai fora
        if (selecao != null && !houveAlteracao()) {
            System.out.println("não ouve alteração");
            return;
        }
        if (parametros == null || parametros.size() == 0) {
            setParametros();
        }
        DaoGenerico<T> daoLista = new DaoGenerico<T>(getTipo());
        selecao = (List<T>) daoLista.achaItensPorSBNQ(new SBNQ(qrySelecao(),
                getParametros()));

    }

    @SuppressWarnings("unchecked")
    protected void setFiltros() {
        filtros = null;
        if (foiInjetado) {
            //      filtros = (List<MB_Filtro<?>>) UtilSBWPServletTools.getObjetosInjetados(
            //            MB_Filtro.class, this);
        } else {
            // filtros = (List<MB_Filtro<?>>) UtilSBWPServletTools.getObjetosInjetadosModoOffline(MB_Filtro.class, this);
        }
        quantidadeSubFiltro = filtros.size();
    }

    public MB_FiltrosPagina() {
        // apenas para compatibilidade com CDI
        super();
    }

    public MB_FiltrosPagina(Class<?> pClasse) {
        super(pClasse);
    }

    public List<T> getSelecao() {
        if (selecao == null) {
            configSelecao();
        }
        return selecao;
    }

    public String getTeste() {
        try {
            System.out.println("colé já mandou um return");
            return teste;

        } finally {
            System.out.println("continuando a situação");
        }

    }

    public void setTeste(String teste) {

        this.teste = teste;

    }

    public List<MB_Filtro<?>> getFiltrosSemConfig() {
        if (filtros == null) {
            setFiltros();
        }
        return filtros;
    }

    public List<MB_Filtro<?>> getFiltros() {
        try {
            return filtros;
        } finally {
            configSelecao();
        }
    }

    public HashMap<String, Object> getParametros() {
        return parametros;
    }

    public void setParametros(HashMap<String, Object> parametros) {
        this.parametros = parametros;
    }

    public int getQuantidadeSubFiltro() {
        return quantidadeSubFiltro;
    }

    public void setQuantidadeSubFiltro(int quantidadeSubFiltro) {
        this.quantidadeSubFiltro = quantidadeSubFiltro;
    }

}
