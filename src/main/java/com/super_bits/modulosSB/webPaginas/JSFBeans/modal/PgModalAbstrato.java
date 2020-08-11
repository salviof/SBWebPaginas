/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.modal;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.ItfComunicacao;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.ItfComunicacaoAcaoVinculada;
import com.super_bits.modulosSB.webPaginas.JSFBeans.SBBeanModel.InfoMBAcao;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.declarados.util.PgUtilModalControle;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.B_Pagina;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.interfaces.ItfB_Pagina;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.interfaces.ItfModalDados;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWP_JSFTools;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import org.primefaces.PrimeFaces;

/**
 *
 * @author desenvolvedor
 */
public class PgModalAbstrato implements Serializable, ItfModalWebApp {

    private String chaveAcesso;
    private ItfB_Pagina paginaVinculada;
    @Inject
    private PgUtilModalControle modalControle;

    public PgModalAbstrato() {
    }

    @PostConstruct
    public void inicio() {
        try {

            chaveAcesso = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(UtilSBWP_JSFTools.NOME_PARAMETRO_CHAVE_ACESSO);
            if (chaveAcesso == null) {
                throw new UnsupportedOperationException("A chave de acesso utilizada para identificar a view que chamou o modal não foi encontrada");
            }
            paginaVinculada = modalControle.getPaginaByID(chaveAcesso);

        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, chaveAcesso, t);
        } finally {
            paginaVinculada.setModalAtual(this);
        }

    }

    @Override
    public ItfComunicacao getComunincacaoAguardandoResposta() {
        return getPaginaVinculada().getComunincacaoAguardandoResposta();
    }

    public ItfComunicacaoAcaoVinculada getComunicacaoTransienteDeAcaoDoModal() {

        return getPaginaVinculada().getComunicacaoTransientAcaoByIdModal(chaveAcesso);

    }

    public String getChaveAcesso() {
        return chaveAcesso;
    }

    @Override
    public ItfB_Pagina getPaginaVinculada() {
        return paginaVinculada;
    }

    public void setPaginaVinculada(ItfB_Pagina paginaVinculada) {
        this.paginaVinculada = paginaVinculada;
    }

    public void fecharPaginaMatandoViewScoped() {

        FacesContext.getCurrentInstance().getViewRoot().getViewMap().clear();
    }

    public PgUtilModalControle getModalControle() {
        return modalControle;
    }

    public void setModalControle(PgUtilModalControle modalControle) {
        this.modalControle = modalControle;
    }

    @Override
    public String getTitulo() {
        return getPaginaVinculada().getTitulo() + ":Justificativa";
    }

    @Override
    public String getDescricao() {
        return getPaginaVinculada().getDescricao();
    }

    @Override
    public String getRecursoXHTML() {
        return UtilSBWP_JSFTools.FORMULARIO_MODAL_JUSTIFICATIVA;
    }

    @Override
    public String getIdAreaExbicaoAcaoSelecionada() {
        return getPaginaVinculada().getIdAreaExbicaoAcaoSelecionada();
    }

    @Override
    public String getXhtmlAcaoAtual() {
        return getRecursoXHTML();
    }

    @Override
    public int getId() {
        return getPaginaVinculada().getId();
    }

    @Override
    public void executarAcaoSelecionada() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<InfoMBAcao> getInfoAcoes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<B_Pagina.BeanDeclarado> getInfoBeans() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Map<String, String> getInfoIds() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void fecharModal() {
        //Depreciado pelo PrimeFaces ATENÇÂO: <br/>
        //RequestContext.getCurrentInstance().closeDialog() foi depreciado pelo Primefaces
        //PrimeFaces.current().dialog().closeDynamic(getRespostaSelecionada()); Parou de funcionar em alguns casos na versão 6.22

        PrimeFaces.current().dialog().closeDynamic(null);

    }

    @Override
    public ItfModalDados getComoModalDados() {
        if (this instanceof ItfModalDados) {
            return (ItfModalDados) this;
        } else {
            return null;
        }
    }

    @Override
    @Deprecated
    public ItfB_Pagina getComoPaginaDeGestao() {
        return getPaginaVinculada();
    }

}
