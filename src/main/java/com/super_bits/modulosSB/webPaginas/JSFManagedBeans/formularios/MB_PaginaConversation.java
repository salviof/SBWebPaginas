package com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios;

import com.super_bits.modulosSB.Persistencia.dao.SBNQ;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import java.io.Serializable;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

public abstract class MB_PaginaConversation extends MB_Pagina implements Serializable {

    private String urlAcessada;

    @PostConstruct
    private void initBean() {
        System.out.println("Iniciando InitBeanDePagina Conversation" + this.getClass().getSimpleName());
        //     foiInjetado = true;
        //     carregarAnotacoes();
        iniciaConvesa();
    }

    @Override
    public void abrePagina() {
        super.abrePagina();

    }

    private Map<String, SBNQ> listas;

    public void iniciaConvesa() {
        try {
            //   if (!FacesContext.getCurrentInstance().isPostback() //                    && conversation.isTransient()) {
            //           //           conversation.begin();
            //       }
        } catch (Exception e) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro iniciando conversa de:" + this.getClass().getSimpleName(), e);

        }

    }

    public void terminaConvesa() {
        // if (conversation != null) {
        try {
            //           if (!getConversation().isTransient()) {
            //             conversation.end();
            // }
        } catch (Exception e) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro Encerrando Conversation", e);
        }
        //   }
    }

    public MB_PaginaConversation() {
        super();
    }

    public Map<String, SBNQ> getListas() {
        return listas;
    }

    @Override
    @PreDestroy
    public void fecharPagina() {
        try {
            System.out.println("Executando predestroy de " + this.getClass().getSimpleName());
            super.fecharPagina();

            terminaConvesa();
        } catch (Exception e) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro fechando pagina " + this.getClass().getSimpleName(), e);
        }
    }

}
