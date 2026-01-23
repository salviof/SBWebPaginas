/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFManagedBeans.declarados.Paginas;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCJson;
import com.super_bits.modulosSB.SBCore.UtilGeral.json.ErroProcessandoJson;
import com.super_bits.modulosSB.SBCore.modulos.Controller.ErroChamadaController;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfResposta;
import com.super_bits.modulosSB.SBCore.modulos.Controller.qualificadoresCDI.sessao.QlSessaoFacesContext;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.reflexao.anotacoes.InfoPagina;
import com.super_bits.modulos.SBAcessosModel.view.FabAcaoPaginasDoSistema;
import com.super_bits.modulos.SBAcessosModel.view.InfoAcaoPaginaDoSistema;
import com.super_bits.modulosSB.webPaginas.controller.servlets.WebPaginasServlet;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWPServletTools;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import com.super_bits.modulosSB.webPaginas.JSFBeans.SBBeanModel.controller.PacoteExecucaoControllerJsonSimples;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWPJson;
import javax.annotation.PreDestroy;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoSessao;

@RequestScoped
@Named
@InfoAcaoPaginaDoSistema(acao = FabAcaoPaginasDoSistema.PAGINA_NATIVA_JSON_WEBCONTROLLER_MB_GESTAO)
@InfoPagina(tags = "pgJsonController", nomeCurto = "jsonController")
public class PgJsonReqController {

    @QlSessaoFacesContext
    @Inject
    private ComoSessao sessao;

    private PacoteExecucaoControllerJsonSimples pacoteAcaoController;

    @PostConstruct
    public void inicio() {
        //todo impedir acesso direto sem intermediação de servlet para melhor segurança
        pacoteAcaoController = (PacoteExecucaoControllerJsonSimples) UtilSBWPServletTools.getRequestAtribute(WebPaginasServlet.NOME_BEAN_PACOTE_CONTROLLER_REQ);
        if (pacoteAcaoController != null) {
            System.out.println("pacote controller Jsonsso definido");
            if (pacoteAcaoController.getUsuario() != null) {
                System.out.println("Usuário=" + pacoteAcaoController.getUsuario().getEmail());
            }
        }
    }

    public String getJson() {
        sessao.setUsuario(pacoteAcaoController.getUsuario());
        try {

            ItfResposta resposta = null;
            if (pacoteAcaoController.getEntidadeExecucao() != null) {
                resposta = SBCore.getServicoController().getResposta(pacoteAcaoController.getAcao().getEnumAcaoDoSistema(), pacoteAcaoController.getEntidadeExecucao());
            } else {
                resposta = SBCore.getServicoController().getResposta(pacoteAcaoController.getAcao().getEnumAcaoDoSistema());
            }
            return UtilCRCJson.getTextoByJsonObjeect(UtilSBWPJson.getJsonRespostaPadrao(resposta));
        } catch (ErroChamadaController ex) {
            return UtilCRCJson.getTextoByJsonObjeect(UtilSBWPJson.JSON_FALHA_GERANDO_JSONVIEW("Falha localizando ação controller" + ex.getMessage()));
        } catch (ErroProcessandoJson ex) {
            return UtilCRCJson.getTextoByJsonObjeect(UtilSBWPJson.JSON_FALHA_GERANDO_JSONVIEW("Falha processando resposta da ação de controller" + ex.getMessage()));
        }
    }

    @PreDestroy
    public void fim() {
        sessao.encerrarSessao();
    }

}
