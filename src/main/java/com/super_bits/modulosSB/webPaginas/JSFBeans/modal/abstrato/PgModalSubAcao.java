/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.modal.abstrato;

import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.MapaAcoesSistema;
import com.super_bits.modulosSB.SBCore.modulos.objetos.MapaObjetosProjetoAtual;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoEntidadeSimples;
import com.super_bits.modulosSB.webPaginas.JSFBeans.modal.ItfModalWebApp;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.declarados.util.PgUtilModalControle;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.MB_Pagina;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.interfaces.ItfB_Pagina;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.interfaces.ItfModalDados;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWP_JSFTools;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import org.primefaces.PrimeFaces;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ComoAcaoDoSistema;

/**
 *
 * @author salvio
 */
public abstract class PgModalSubAcao extends MB_Pagina implements Serializable, ItfModalWebApp {

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
            if (FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().containsKey("tipoEntidade")) {
                String tipoEntidade = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("tipoEntidade");
                String codigoEntidade = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codigoEntidade");
                Long codigo = Long.valueOf(codigoEntidade);
                Class classeEntidade = MapaObjetosProjetoAtual.getClasseDoObjetoByNome(tipoEntidade);
                ComoEntidadeSimples entidade = (ComoEntidadeSimples) UtilSBPersistencia.getRegistroByID(classeEntidade, codigo, getEMPagina());
                setBeanSelecionado(entidade);
            }
            if (FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().containsKey("acao")) {
                String acaoSlug = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("acao");
                ComoAcaoDoSistema acao = MapaAcoesSistema.getAcaoDoSistemaByNomeUnico(acaoSlug);
                setAcaoSelecionada(acao);
                executarAcaoSelecionada();
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

    @Override
    public void fecharModal() {
        PrimeFaces.current().dialog().closeDynamic(getBeanSelecionado());
    }

    @Override
    public ItfModalDados getComoModalDados() {
        if (this instanceof ItfModalDados) {
            return (ItfModalDados) this;
        } else {
            return null;
        }
    }

}
