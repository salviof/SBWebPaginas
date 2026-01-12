/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFManagedBeans.declarados.Paginas;

import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.TIPO_PARTE_URL;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoEntidadeSimples;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.MB_PaginaConversation;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.reflexao.anotacoes.InfoPagina;
import com.super_bits.modulos.SBAcessosModel.view.FabAcaoPaginasDoSistema;
import com.super_bits.modulos.SBAcessosModel.view.InfoAcaoPaginaDoSistema;
import com.super_bits.modulosSB.webPaginas.controller.servletes.urls.parametrosURL.InfoParametroURL;
import com.super_bits.modulosSB.webPaginas.controller.servletes.urls.parametrosURL.ParametroURL;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author salvio
 */
@RequestScoped
@Named
@InfoAcaoPaginaDoSistema(acao = FabAcaoPaginasDoSistema.PAGINA_NATIVA_LINK_RAPIDO_MB_GESTAO)
@InfoPagina(tags = "sessao", nomeCurto = "sessao")
public class PgLinkRapido extends MB_PaginaConversation {

    @InfoParametroURL(nome = "Entidade", tipoParametro = TIPO_PARTE_URL.TEXTO)
    private ParametroURL prEntidade;
    @InfoParametroURL(nome = "Entidade", tipoParametro = TIPO_PARTE_URL.TEXTO)
    private ParametroURL prTextoPesquisa;
    private boolean usuarioLogado;

    @Override
    public ComoEntidadeSimples getBeanSelecionado() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setBeanSelecionado(ComoEntidadeSimples pBeanSimples) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
