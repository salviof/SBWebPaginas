package com.super_bits.modulosSB.webPaginas.JSFManagedBeans.siteMap;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.MapaAcoesSistema;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreReflexao;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringsMaiuculoMinusculo;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoGerenciarEntidade;
import com.super_bits.modulosSB.webPaginas.JSFBeans.modal.PgModalSBJSF;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.interfaces.ItfB_PaginaSimples;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.interfaces.ItfSiteMapa;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.reflexao.anotacoes.InfoPagina;
import com.super_bits.modulosSB.webPaginas.controller.servlets.servletWebPaginas.EstruturaDeFormulario;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWPServletTools;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWP_JSFTools;
import java.util.List;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ComoAcaoDoSistema;

public abstract class MB_SiteMapa implements ItfSiteMapa {

    public MB_SiteMapa() {

    }

    @Override
    public ItfB_PaginaSimples getPaginaNoContexto(String xhtmlGerenciarPG) throws UnsupportedOperationException {
        try {
            EstruturaDeFormulario estruturaFormulario = MapaDeFormularios.getEstruturaByXHTMLDeGestao(xhtmlGerenciarPG);
            ItfAcaoGerenciarEntidade acaoGestao = null;
            if (estruturaFormulario == null) {
                if (xhtmlGerenciarPG.equals(UtilSBWP_JSFTools.FORMULARIO_MODAL_PESQUISA_ITEM_AVANCADO)) {
                    return (ItfB_PaginaSimples) UtilSBWPServletTools.getBeanByNamed(UtilSBCoreStringsMaiuculoMinusculo.getPrimeiraLetraMinuscula(PgModalSBJSF.class.getSimpleName()), PgModalSBJSF.class);
                } else {
                    ComoAcaoDoSistema acao = MapaAcoesSistema.getAcaoDoSistemaByFormulario(xhtmlGerenciarPG);

                    acaoGestao = MapaDeFormularios.getEstruturaByXHTMLDeGestao(acao.getAcaoDeGestaoEntidade().getXhtml()).getAcaoGestaoVinculada();
                }
            } else {
                acaoGestao = estruturaFormulario.getAcaoGestaoVinculada().getAcaoDeGestaoEntidade();
            }

            String xhtml = acaoGestao.getXhtml();

            EstruturaDeFormulario estrutura = MapaDeFormularios.getEstruturaByXHTMLDeGestao(xhtml);

            Class classeMB = MapaDeFormularios.getClasseMBByAcaoGestaoNomeUnico(estrutura.getAcaoGestaoVinculada().getNomeUnico());

            if (classeMB != null) {
                ItfB_PaginaSimples paginaDoContexto
                        = (ItfB_PaginaSimples) UtilSBWPServletTools.getBeanByNamed(UtilSBCoreStringsMaiuculoMinusculo.getPrimeiraLetraMinuscula(classeMB.getSimpleName()), classeMB);
                return paginaDoContexto;
            } else {
                throw new UnsupportedOperationException("a classe vinculada a ");
            }

        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro Obtendo Ação vinculada ao xhtml [" + xhtmlGerenciarPG + "]", t);
            return null;
        }

    }

}
