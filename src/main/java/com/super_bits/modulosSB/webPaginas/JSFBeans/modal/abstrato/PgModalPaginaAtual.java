/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.modal.abstrato;

import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfParametroRequisicaoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoGerenciarEntidade;
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
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.ItfDialogo;
import com.super_bits.modulosSB.SBCore.modulos.view.formulario.ItfFormularioAcao;
import com.super_bits.modulosSB.SBCore.modulos.view.formulario.ItfFormularioSimples;

/**
 *
 * @author desenvolvedor
 */
public abstract class PgModalPaginaAtual extends PgModalBaseAbs implements Serializable, ItfModalWebApp, ItfFormularioAcao {

    public PgModalPaginaAtual() {
    }

    public ItfDialogo getComunincacaoAguardandoResposta() {
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
    public Long getId() {
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

    @Override
    public List<ItfParametroRequisicaoInstanciado> getParametrosURL() {
        return getPaginaVinculada().getParametrosURL();
    }

    @Override
    public ItfAcaoDoSistema getAcaoSelecionada() {
        return getPaginaVinculada().getAcaoSelecionada();
    }

    @Override
    public ItfAcaoGerenciarEntidade getAcaoVinculada() {
        return getPaginaVinculada().getAcaoVinculada();
    }

}
