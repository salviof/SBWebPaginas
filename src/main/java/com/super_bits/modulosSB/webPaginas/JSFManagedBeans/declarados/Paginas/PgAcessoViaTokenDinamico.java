/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFManagedBeans.declarados.Paginas;

import com.super_bits.modulos.SBAcessosModel.model.UsuarioSB;
import com.super_bits.modulos.SBAcessosModel.model.tokens.tokenLoginDinamico.TokenAcessoDinamico;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.consultaDinamica.ConsultaDinamicaDeEntidade;
import com.super_bits.modulosSB.SBCore.UtilGeral.MapaAcoesSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.TIPO_PARTE_URL;
import com.super_bits.modulosSB.SBCore.modulos.objetos.MapaObjetosProjetoAtual;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ComoEntidadeSimples;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.MB_paginaCadastroEntidades;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.reflexao.anotacoes.InfoPagina;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.siteMap.MapaDeFormularios;
import com.super_bits.modulos.SBAcessosModel.view.FabAcaoPaginasDoSistema;
import com.super_bits.modulos.SBAcessosModel.view.InfoAcaoPaginaDoSistema;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.implementacao.AcaoApiIntegracaoRestAbstratoBasico;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfParametroRequisicao;
import com.super_bits.modulosSB.webPaginas.controller.servlets.servletWebPaginas.EstruturaDeFormulario;
import com.super_bits.modulosSB.webPaginas.controller.servletes.urls.parametrosURL.InfoParametroURL;
import com.super_bits.modulosSB.webPaginas.controller.servletes.urls.parametrosURL.ParametroURL;
import com.super_bits.modulosSB.SBCore.modulos.Controller.qualificadoresCDI.sessao.QlSessaoFacesContext;
import com.super_bits.modulosSB.webPaginas.controller.sessao.SessaoAtualSBWP;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWPServletTools;
import java.util.List;
import java.util.Optional;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ComoAcaoDoSistema;

/**
 *
 * @author sfurbino
 */
@InfoAcaoPaginaDoSistema(acao = FabAcaoPaginasDoSistema.PAGINA_NATIVA_TOKEN_DINAMICO_MB)
@Named
@ViewScoped
@InfoPagina(nomeCurto = "acessoExclusivo", tags = "acessoExclusivo")
public class PgAcessoViaTokenDinamico extends MB_paginaCadastroEntidades<TokenAcessoDinamico> {

    @InfoParametroURL(nome = "Código de acesso", tipoParametro = TIPO_PARTE_URL.ENTIDADE,
            tipoEntidade = TokenAcessoDinamico.class, representaEntidadePrincipalMB = true
    )
    private ParametroURL prCodigoDeAcesso;

    private TokenAcessoDinamico tokenDinamico;

    private boolean acessonegado = false;

    @Inject
    @QlSessaoFacesContext
    private SessaoAtualSBWP sessaoAtual;

    @Override
    public ComoEntidadeSimples getBeanSelecionado() {
        return tokenDinamico;
    }

    private void acessonegado() {
        acessonegado = true;
        tokenDinamico = null;
        getParametroInstanciado(prCodigoDeAcesso).setValor(null);

        setAcaoSelecionada(FabAcaoPaginasDoSistema.PAGINA_NATIVA_ACESSO_NEGADO_FRM_SUB_FORM.getRegistro());
        xhtmlAcaoAtual = FabAcaoPaginasDoSistema.PAGINA_NATIVA_ACESSO_NEGADO_FRM_SUB_FORM.getRegistro().getComoFormulario().getXhtml();
    }

    @PostConstruct
    public void inicio() {
        tokenDinamico = getTokenDinamico();

        String nome = (String) UtilSBWPServletTools.getRequestParametro("nome");
        String telefone = (String) UtilSBWPServletTools.getRequestParametro("telefone");
        //  String whatsappid = (String) UtilSBWPServletTools.getRequestParametro("whatsappid");

        if (nome != null && telefone != null) {
            UtilSBWPServletTools.cookieAdicionar("LEAD_NOME", nome, 0);
            UtilSBWPServletTools.cookieAdicionar("LEAD_TELEFONE", telefone, 0);
            //    UtilSBWPServletTools.cookieAdicionar("LEAD_WATSAPP_ID", whatsappid, 0);
        }

        if (tokenDinamico == null) {

            acessonegado();
        }

    }

    public String getUrlAcaoDoToken() {
        if (getTokenDinamico() == null) {
            acessonegado();
            return sessaoAtual.getUrlHostDaSessao();
        }
        if (tokenDinamico.getEntidadeDoAcesso() != null) {
            Class tipoEntidade = MapaObjetosProjetoAtual.getClasseDoObjetoByNome(tokenDinamico.getEntidadeDoAcesso());
            ComoEntidadeSimples entidade = (ComoEntidadeSimples) UtilSBPersistencia.getRegistroByID(tipoEntidade, Long.valueOf(tokenDinamico.getCodigoEntidade()), getEMPagina());
            String slugAcao = tokenDinamico.getSlugAcaoFormulario();
            ComoAcaoDoSistema acao = MapaAcoesSistema.getAcaoDoSistemaByNomeUnico(slugAcao);
            String emailUsuario = tokenDinamico.getEmail();
            if (emailUsuario != null) {
                ConsultaDinamicaDeEntidade consultaUsuario = new ConsultaDinamicaDeEntidade(UsuarioSB.class, getEMPagina());
                consultaUsuario.addcondicaoCampoIgualA("email", tokenDinamico.getEmail());
                List<UsuarioSB> usuarios = consultaUsuario.resultadoRegistros();

                if (!usuarios.isEmpty()) {
                    sessaoAtual.setUsuario(usuarios.get(0));
                } else {

                    if (sessaoAtual.isIdentificado()) {
                        sessaoAtual.encerrarSessao(false);
                    }
                }
            } else {
                sessaoAtual.encerrarSessao(false);
            }

            EstruturaDeFormulario estrutura = MapaDeFormularios.getEstruturaByNomeAcao(acao.getAcaoDeGestaoEntidade().getNomeUnico());
            List<ItfParametroRequisicao> parametros = estrutura.getParametrosURL();
            Optional<ItfParametroRequisicao> parametroToken = parametros.stream().filter(pr -> pr.getTipoEntidade().equals(TokenAcessoDinamico.class)).findFirst();
            String url = "";
            if (parametroToken.isPresent()) {
                url = MapaDeFormularios.getUrlFormulario(acao.getComoFormulario(), entidade, tokenDinamico);
            } else {

                url = MapaDeFormularios.getUrlFormulario(acao.getComoFormulario(), entidade);

            }

            //UtilSBWP_JSFTools.vaParaPagina(url);
            return replaceURL(url);

        } else {
            String url = MapaDeFormularios.getUrlFormulario(MapaAcoesSistema.getAcaoDoSistemaByNomeUnico(tokenDinamico.getSlugAcaoFormulario()).getComoFormulario());
            // UtilSBWP_JSFTools.vaParaPagina(url);
            return replaceURL(url);

        }

    }

    private String replaceURL(String pUrl) {
        if (sessaoAtual.getUsuario().getClass().getSimpleName().contains("Cliente")) {
            return pUrl.replace("crm.", "atendimento.");
        } else {
            return pUrl;
        }
    }

    @Override
    public void setBeanSelecionado(ComoEntidadeSimples pBeanSimples) {
        tokenDinamico = (TokenAcessoDinamico) pBeanSimples;
    }

    public void setPrCodigoDeAcesso(ParametroURL prCodigoDeAcesso) {
        this.prCodigoDeAcesso = prCodigoDeAcesso;
    }

    public TokenAcessoDinamico getTokenDinamico() {
        try {
            if (getParametroInstanciado(prCodigoDeAcesso).isValorDoParametroFoiConfigurado()) {
                String chaveAcessoUtilizada = getParametroInstanciado(prCodigoDeAcesso).getTextoEnviadoUrl();
                chaveAcessoUtilizada = chaveAcessoUtilizada.substring(0, chaveAcessoUtilizada.lastIndexOf("-"));
                TokenAcessoDinamico token = (TokenAcessoDinamico) getParametroInstanciado(prCodigoDeAcesso).getValor();
                if (!chaveAcessoUtilizada.equals(token.getCodigo())) {
                    acessonegado();
                    return null;
                }
                tokenDinamico = token;
            }
        } catch (Throwable t) {
            acessonegado();
        }
        return tokenDinamico;
    }

    public void setTokenDinamico(TokenAcessoDinamico tokenDinamico) {
        this.tokenDinamico = tokenDinamico;
    }

}
