/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.modal.abstrato;

import com.super_bits.modulosSB.SBCore.modulos.comunicacao.ItfComunicacao;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.ItfComunicacaoAcaoVinculada;
import com.super_bits.modulosSB.webPaginas.JSFBeans.SBBeanModel.InfoMBAcao;
import com.super_bits.modulosSB.webPaginas.JSFBeans.modal.ItfModalWebApp;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.B_Pagina;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.interfaces.ItfB_Pagina;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.interfaces.ItfModalDados;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWP_JSFTools;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;

/**
 *
 * @author desenvolvedor
 */
public abstract class PgModalPaginaAtual extends PgModalBaseAbs implements Serializable, ItfModalWebApp {

    public PgModalPaginaAtual() {
    }

    public ItfComunicacao getComunincacaoAguardandoResposta() {
        return getPaginaVinculada().getComunincacaoAguardandoResposta();
    }

    public ItfComunicacaoAcaoVinculada getComunicacaoTransienteDeAcaoDoModal() {

        return getPaginaVinculada().getComunicacaoTransientAcaoByIdModal(chaveIdentificacaoViewOrigem);

    }

    public void fecharPaginaMatandoViewScoped() {

        FacesContext.getCurrentInstance().getViewRoot().getViewMap().clear();
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
        getPaginaVinculada().executarAcaoSelecionada();
    }

    @Override
    @Deprecated
    public List<InfoMBAcao> getInfoAcoes() {
        return getPaginaVinculada().getInfoAcoes();
    }

    @Override
    public List<B_Pagina.BeanDeclarado> getInfoBeans() {
        return getPaginaVinculada().getInfoBeans();
    }

    @Override
    public Map<String, String> getInfoIds() {
        return getPaginaVinculada().getInfoIds();
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
