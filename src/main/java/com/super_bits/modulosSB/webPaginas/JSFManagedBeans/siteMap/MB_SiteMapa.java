package com.super_bits.modulosSB.webPaginas.JSFManagedBeans.siteMap;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreReflexao;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringValidador;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringsMaiuculoMinusculo;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoGerenciarEntidade;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.interfaces.ItfB_PaginaSimples;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.interfaces.ItfSiteMapa;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.reflexao.anotacoes.InfoPagina;
import com.super_bits.modulosSB.webPaginas.controller.servletes.servletWebPaginas.EstruturaDeFormulario;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWPServletTools;
import java.util.List;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

public abstract class MB_SiteMapa implements ItfSiteMapa {

    private ItfSiteMapa siteMapa;
    private static boolean siteMapaCriado = false;

    public MB_SiteMapa() {
        System.out.println("");
        if (!siteMapaCriado) {
            try {

                List<Class> paginasEncontradas = UtilSBCoreReflexao.getClassesComEstaAnotacao(InfoPagina.class, "com.super_bits");
                MapaDeFormularios.buildEstrutura(paginasEncontradas);

            } catch (Throwable t) {
                SBCore.RelatarErro(FabErro.PARA_TUDO, "Erro construindo o mapa de paginas do Sitemap", t);
            }
        }
        siteMapaCriado = true;
    }

    @Override
    public ItfB_PaginaSimples getPaginaNoContexto(String xhtmlGerenciarPG) throws UnsupportedOperationException {
        try {

            ItfAcaoGerenciarEntidade acaoGestao = MapaDeFormularios.getEstruturaByXHTMLDeGestao(xhtmlGerenciarPG).getAcaoGestaoVinculada().getAcaoDeGestaoEntidade();

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