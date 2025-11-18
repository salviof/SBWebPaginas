/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFManagedBeans.declarados.Paginas.sistema;

import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ComoEntidadeSimples;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.MB_PaginaConversation;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.reflexao.anotacoes.InfoPagina;
import com.super_bits.modulos.SBAcessosModel.view.FabAcaoPaginasDoSistema;
import com.super_bits.modulos.SBAcessosModel.view.InfoAcaoPaginaDoSistema;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author desenvolvedor
 */
@InfoAcaoPaginaDoSistema(acao = FabAcaoPaginasDoSistema.PAGINA_NATIVA_VIEW_EXPIROU_MB_PADRAO)
@ViewScoped
@InfoPagina(nomeCurto = "ex", tags = "expirou")
@Named
public class PgViewExPirou extends MB_PaginaConversation {

    @Override
    public ComoEntidadeSimples getBeanSelecionado() {
        return null;
    }

    @Override
    public void setBeanSelecionado(ComoEntidadeSimples pBeanSimples) {

    }

}
