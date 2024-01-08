/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.modal.abstrato;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.webPaginas.JSFBeans.modal.ItfModalWebApp;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.declarados.util.PgUtilModalControle;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.interfaces.ItfB_Pagina;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWP_JSFTools;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

/**
 *
 * @author salvio
 */
public abstract class PgModalBaseAbs implements Serializable, ItfModalWebApp {

    @Inject
    private PgUtilModalControle modalControle;
    protected String chaveIdentificacaoViewOrigem;

    private ItfB_Pagina paginaVinculada;

    @PostConstruct
    public void inicio() {
        try {

            chaveIdentificacaoViewOrigem = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(UtilSBWP_JSFTools.NOME_PARAMETRO_CHAVE_ACESSO);
            if (chaveIdentificacaoViewOrigem == null) {
                throw new UnsupportedOperationException("A chave de acesso utilizada para identificar a view que chamou o modal não foi encontrada");
            }
            if (FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().containsKey("entidade")) {
                String dadosEntidade = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("entidade");
                System.out.println(dadosEntidade);

            }
            paginaVinculada = modalControle.getPaginaByID(chaveIdentificacaoViewOrigem);

        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, chaveIdentificacaoViewOrigem, t);
        } finally {
            paginaVinculada.setModalAtual(this);
        }

    }

    @PreDestroy
    public void fim() {
        if (paginaVinculada != null) {
            modalControle.removerReferencia(paginaVinculada);
        }
    }

    @Override
    public ItfB_Pagina getPaginaVinculada() {
        return paginaVinculada;
    }

    public PgUtilModalControle getModalControle() {
        return modalControle;
    }

}
