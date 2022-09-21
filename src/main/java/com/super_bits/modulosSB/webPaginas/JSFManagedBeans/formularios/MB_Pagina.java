package com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios;

import com.super_bits.modulosSB.Persistencia.dao.SBNQ;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.Controller.ControllerAppAbstratoSBCore;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ItfFabricaAcoes;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.CampoInstanciadoGenerico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfAtributoObjetoEditavel;
import com.super_bits.modulosSB.webPaginas.controller.sessao.SessaoAtualSBWP;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import com.super_bits.modulosSB.SBCore.modulos.Controller.qualificadoresCDI.sessao.QlSessaoFacesContext;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWP_JSFTools;
import javax.faces.context.FacesContext;

public abstract class MB_Pagina extends B_Pagina {

    protected Map<String, String> idsGerenciaveis = new HashMap<>();

    private String urlAcessada;
    @Inject
    @QlSessaoFacesContext
    private SessaoAtualSBWP sessaoAtual;

    public MB_Pagina() {
        super();
    }

    @PostConstruct
    private void initBean() {
        try {
            System.out.println("Iniciando InitBeanDePagina" + this.getClass().getSimpleName());
            try {
                //  String identificadorVire = UtilSBWP_JSFTools.getIDViewFeceScoped();
                // if (identificadorVire != null) {
                //  FacesContext.getCurrentInstance().getExternalContext().addResponseHeader("estadoSessaoViewScoped", identificadorVire);
                // }
            } catch (Throwable t) {

            }
            configParametros();

        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro executando inito principal do bean", t);

        }

    }

    protected <Y extends ItfFabricaAcoes> Y getEnumAcaoAtual() {
        if (getAcaoSelecionada() == null) {
            return null;
        }
        return (Y) getAcaoSelecionada().getEnumAcaoDoSistema();
    }

    @Override
    public void abrePagina() {
        super.abrePagina();
    }

    private Map<String, SBNQ> listas;

    public Map<String, SBNQ> getListas() {
        return listas;
    }

    public boolean isAcessoPermitido() {
        if (getAcaoVinculada() != null) {
            return ControllerAppAbstratoSBCore.isAcessoPermitido((ItfAcaoDoSistema) getAcaoVinculada());
        }
        return false;
    }

    protected boolean isAcaoSelecionadaTipoFormulario() {
        if (acaoSelecionada == null) {
            return false;
        }
        return acaoSelecionada.isUmaAcaoFormulario();
    }

    @Override
    public void executarAcaoSelecionada() {
        if (!isAcaoSelecionadaTipoFormulario() && !isPermitidoAbrirFormulario()) {
            return;
        }
        super.executarAcaoSelecionada();
    }

    @Override
    protected String defineTitulo() {
        try {

            if (getAcaoVinculada() != null) {
                return getAcaoVinculada().getDescricao();
            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro obendo titulo da pagina", t);
        }
        return "Título da pagina " + this.getClass().getSimpleName() + " não pode ser definido";
    }

    @Override
    protected String defineNomeLink() {
        try {
            if (getAcaoVinculada() != null) {
                return getAcaoVinculada().getNomeAcao();
            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro obendo titulo da pagina", t);
        }
        return "Errro, obtendo nome do link da pagina";
    }

    @Override
    protected String defineDescricao() {
        try {
            if (getAcaoVinculada() != null) {
                return getAcaoVinculada().getDescricao();
            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro obendo titulo da pagina", t);
        }
        return "Errro, obtendo Descricao  da pagina";
    }

    @Override
    public int getId() {
        try {
            return getAcaoVinculada().getId();
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro obendo titulo da pagina", t);
        }
        return -1;
    }

    /**
     *
     * Permite editar atributos de um campo, do bean selecionado em tempo de
     * execução ( Caso de uso: tornar um campo somenteLeitura no formulário de
     * maneira condicional)
     *
     * @param pAtributo
     * @return
     */
    protected ItfAtributoObjetoEditavel getAtributoEditavelBeanSelecionado(String pAtributo) {
        return ((ItfAtributoObjetoEditavel) ((CampoInstanciadoGenerico) getBeanSelecionado().getCPinst(pAtributo)).getAtributosDoObjeto());
    }

}
