/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFManagedBeans.declarados.util;

import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreListas;
import com.super_bits.modulosSB.SBCore.modulos.Controller.AcaoTransient;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.ItfGrupoCampos;
import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.ItfComponenteVisualSB;
import com.super_bits.modulosSB.SBCore.modulos.view.telas.ColunaTela;
import com.super_bits.modulosSB.SBCore.modulos.view.telas.LayoutComponentesEmTela;
import com.super_bits.modulosSB.SBCore.modulos.view.telas.LayoutComponentesEmTelaComGrupoDeAcoes;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.interfaces.ItfPaginaAtual;
import com.super_bits.modulosSB.SBCore.modulos.Controller.qualificadoresCDI.sessao.QlSessaoFacesContext;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author desenvolvedor
 */
@RequestScoped
@Named
public class PgUtilLayout implements Serializable {

    private static final List<ItfAcaoDoSistema> ACAO_SELECAO_REGISTRO = UtilSBCoreListas.gerarComoLista(new AcaoTransient("nomeAcao", "iconeAcao"));

    @Inject
    private ItfPaginaAtual paginaAtual;

    @Inject
    private PgUtil paginaUtil;

    @Deprecated
    public LayoutComponentesEmTelaComGrupoDeAcoes gerarLayout(ItfGrupoCampos pGrupoCampo, List<ItfAcaoDoSistema> pAcoes) {
        return getLayoutCamposComAcao(pGrupoCampo, pAcoes);
    }

    @Deprecated
    public LayoutComponentesEmTela gerarLayout(ItfGrupoCampos pGrupoCampo) {
        return gerarLayoutGrupoCampo(pGrupoCampo);
    }

    public LayoutComponentesEmTela gerarLayoutGrupoCampo(ItfGrupoCampos pGrupoCampo) {
        return paginaAtual.getInfoPagina().getComoFormularioWeb().getInfoLayout().gerarLayout(pGrupoCampo);
    }

    public LayoutComponentesEmTela gerarLayoutGrupoCampoSeletorItem(ItfGrupoCampos pGrupoCampo) {
        return paginaAtual.getInfoPagina().getComoFormularioWeb().getInfoLayout().gerarLayout(pGrupoCampo, ACAO_SELECAO_REGISTRO);
    }

    public LayoutComponentesEmTelaComGrupoDeAcoes getLayoutCamposComAcao(ItfGrupoCampos pGrupoCampo, List<ItfAcaoDoSistema> pAcoes) {
        return paginaAtual.getInfoPagina().getComoFormularioWeb().getInfoLayout().gerarLayout(pGrupoCampo, pAcoes);
    }

    @Deprecated
    public ColunaTela getColunaLayoutByNomeCampoCompletoComGrupo(String pNomeCampo) {
        return paginaAtual.getInfoPagina().getComoFormularioWeb().getInfoLayout().getColunaLayoutByNomeCampoCompletoComGrupo(pNomeCampo);
    }

    @Deprecated
    public ColunaTela getUltimaColunaDoLayoyt(String pNomeLayout) {
        return paginaAtual.getInfoPagina().getComoFormularioWeb().getInfoLayout().getUltimaColunaDoLayoyt(pNomeLayout);
    }

    @Deprecated
    public boolean verificaExisteLayout(String pNomeLayot) {
        return false;

    }

    public String gerarContainer12SeFalse(boolean condicao, String pContainerOriginaol) {
        return condicao ? pContainerOriginaol : "Container12";
    }

    public int numeroMaximoColunasNaTelaComponentePeso3() {
        int mx = getColunaMaximoNaTela();
        return (mx == 0 || mx <= 3) ? 1 : mx / 3;
    }

    public int numeroMaximoColunasDoComponentePeso3CondicionalAlternativo(boolean usarlternativo, int alternativo) {
        int max = getColunaMaximoNaTela();
        if (usarlternativo) {
            return alternativo;
        }
        if (alternativo > max) {
            return max;
        }

        return (max <= 3) ? 1 : max / 3;
    }

    public int numeroMaximoColunasDoComponentePeso3CondicionalAlternativo(boolean usarlternativo, int alternativo, int pTamanhoContainerPai) {
        int max = pTamanhoContainerPai;
        if (usarlternativo) {
            return alternativo;
        }
        if (alternativo > max) {
            return max;
        }

        return (max <= 3) ? 1 : max / 3;
    }

    public int numeroMaximoColunasDoComponentePeso2CondicionalAlternativo(boolean usarlternativo, int alternativo, int pTamanhoContainerPai) {
        int max = pTamanhoContainerPai;
        if (usarlternativo) {
            return alternativo;
        }
        if (alternativo > max) {
            return max;
        }

        return (max <= 2) ? 1 : max / 2;
    }

    public int numeroMaximoColunasDoComponentePeso2p2CondicionalAlternativo(boolean usarlternativo, int alternativo) {
        int max = getColunaMaximoNaTela();
        if (usarlternativo) {
            return alternativo;
        }
        if (alternativo > max) {
            return max;
        }
        Double colunas = max / 2.2;

        return (max <= 3)
                ? 1
                : colunas.intValue();
    }

    public int numeroMaximoColunasDoComponenteNaTela(ItfComponenteVisualSB pComponente) {
        int mx = getColunaMaximoNaTela();
        return (mx == 0 || pComponente.getPesoLargura() == 0) ? 1 : mx / pComponente.getPesoLargura();

    }

    private int numeroMaximotela;

    public int getColunaMaximoNaTela() {
        if (numeroMaximotela == 0) {
            numeroMaximotela = paginaUtil.getSessao().getTelaUsuario().getNumeroMaximoColunas();
            if (numeroMaximotela == 0) {
                return 1;
            }
        }
        return numeroMaximotela;
    }

    public boolean isUmaTelaMobile() {
        return paginaUtil.getSessao().getTelaUsuario().isUmMobile();
    }

    public boolean isUmaTelaDesktopReduzida() {
        return paginaUtil.getSessao().getTelaUsuario().getNumeroMaximoColunas() < 13;
    }

    public boolean isUmaTelaTablet() {
        return paginaUtil.getSessao().getTelaUsuario().isUmMobile();
    }

    private ContainerResponsivo calculosContainerResponsivo;

    public ContainerResponsivo getContainerResponsivo() {
        if (calculosContainerResponsivo == null) {
            calculosContainerResponsivo = new ContainerResponsivo(paginaUtil.getSessao().getTelaUsuario());
        }
        return calculosContainerResponsivo;
    }

}
