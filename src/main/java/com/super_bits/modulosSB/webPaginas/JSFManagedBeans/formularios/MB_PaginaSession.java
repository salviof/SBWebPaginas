package com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios;

import com.super_bits.modulosSB.Persistencia.dao.SBNQ;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

public abstract class MB_PaginaSession extends MB_Pagina {

    protected Map<String, String> idsGerenciaveis = new HashMap<String, String>();
    private String urlAcessada;

    @PostConstruct
    private void initBean() {
        System.out.println("Iniciando InitBeanDePagina Session Scoped" + this.getClass().getSimpleName());
        //   foiInjetado = true;
        //   carregarAnotacoes();

    }

    @Override
    public void abrePagina() {
        super.abrePagina();

    }

    private Map<String, SBNQ> listas;

    public MB_PaginaSession() {
        super();
    }

    public Map<String, SBNQ> getListas() {
        return listas;
    }

    @Override
    public void fecharPagina() {
        try {
            System.out.println("Executando predestroy de " + this.getClass().getSimpleName());
            super.fecharPagina();

        } catch (Exception e) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro fechando pagina " + this.getClass().getSimpleName(), e);
        }
    }

}
