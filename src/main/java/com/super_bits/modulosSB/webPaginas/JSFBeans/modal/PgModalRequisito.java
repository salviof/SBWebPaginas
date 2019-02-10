/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.modal;

import com.google.gson.Gson;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.ConfigGeral.arquivosConfiguracao.ConfigModulo;
import com.super_bits.modulosSB.SBCore.modulos.Controller.WS.RespostaWebService;
import com.super_bits.modulosSB.SBCore.modulos.requisito.ComentarioRequisitoEnvioNovo;
import com.super_bits.modulosSB.SBCore.modulos.requisito.FabConfigModuloJiraRequisitos;
import com.super_bits.modulosSB.SBCore.modulos.requisito.Requisito;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.interfaces.ItfB_Pagina;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import requisito.ClienteComentarioRequisito;

/**
 *
 * @author desenvolvedor
 */
@ViewScoped
@Named
public class PgModalRequisito extends PgModalAbstrato {

    private String nomeUnicoAcao;
    private Requisito requisitoVinculadoAcao;
    private Requisito requisitoVinculadoAcaoGestao;
    private String comentario;
    private final ClienteComentarioRequisito wsRequisito = new ClienteComentarioRequisito();
    private final ConfigModulo configmodulo;
    private String urlJira;

    public PgModalRequisito() {
        configmodulo = SBCore.getConfigModulo(FabConfigModuloJiraRequisitos.class);
    }

    public String getUrlJira() {
        if (urlJira == null) {
            urlJira = configmodulo.getPropriedade(FabConfigModuloJiraRequisitos.URL_JIRA);
        }
        return urlJira;
    }

    public String getNomeUnicoAcao() {
        try {
            if (getPaginaVinculada() != null) {
                if (nomeUnicoAcao == null) {
                    ItfB_Pagina paginavinculada = getPaginaVinculada();
                    if (paginavinculada == null) {
                        throw new UnsupportedOperationException("A pagina vinculada não pôde ser determinada no modal");
                    }
                    if (paginavinculada.getAcaoSelecionada() != null) {
                        nomeUnicoAcao = paginavinculada.getAcaoSelecionada().getNomeUnico();
                    } else {
                        nomeUnicoAcao = getPaginaVinculada().getAcaoVinculada().getNomeUnico();
                    }
                }
            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro determinando nome unico da ação", t);
        }

        return nomeUnicoAcao;
    }

    public void setNomeUnicoAcao(String nomeUnicoAcao) {
        this.nomeUnicoAcao = nomeUnicoAcao;
    }

    public void atualizarRequisito() {
        try {
            if (nomeUnicoAcao == null) {
                throw new UnsupportedOperationException("Erro Nome unicio não difinido");
            }
            RespostaWebService resp = (RespostaWebService) wsRequisito.obterRequisitoDeAcaoDoProjeto(nomeUnicoAcao);
            requisitoVinculadoAcao = new Gson().fromJson(resp.getRetornoJson(), Requisito.class);
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, t.getMessage(), t);
        }
    }

    public void adicionarComentario() {
        try {
            if (nomeUnicoAcao == null) {
                throw new UnsupportedOperationException("Erro Nome unicio não difinido");
            }
            ComentarioRequisitoEnvioNovo novoComentario = new ComentarioRequisitoEnvioNovo();
            novoComentario.setUsuario(configmodulo.getPropriedade(FabConfigModuloJiraRequisitos.USUARIO_COMENTARIO_PADRAO));
            novoComentario.setSenha(configmodulo.getPropriedade(FabConfigModuloJiraRequisitos.SENHA_USUARIO_COMENTARIO_PADRAO));
            novoComentario.setComentario(comentario);
            novoComentario.setAcaoDoSistema(nomeUnicoAcao);

            RespostaWebService resp = (RespostaWebService) wsRequisito.adicionarComentario(novoComentario);
            if (resp != null) {
                requisitoVinculadoAcao = new Gson().fromJson(resp.getRetornoJson(), Requisito.class);
            }
            atualizarRequisito();
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, t.getMessage(), t);
        }
    }

    public Requisito getRequisitoVinculadoAcao() {
        return requisitoVinculadoAcao;
    }

    public void setRequisitoVinculadoAcao(Requisito requisitoVinculadoAcao) {
        this.requisitoVinculadoAcao = requisitoVinculadoAcao;
    }

    public Requisito getRequisitoVinculadoAcaoGestao() {
        return requisitoVinculadoAcaoGestao;
    }

    public void setRequisitoVinculadoAcaoGestao(Requisito requisitoVinculadoAcaoGestao) {
        this.requisitoVinculadoAcaoGestao = requisitoVinculadoAcaoGestao;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public ClienteComentarioRequisito getWsRequisito() {
        return wsRequisito;
    }

}
