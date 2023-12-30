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
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.objetos.MapaObjetosProjetoAtual;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.MB_PaginaConversation;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.MB_paginaCadastroEntidades;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.reflexao.anotacoes.InfoPagina;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.siteMap.MapaDeFormularios;
import com.super_bits.modulosSB.webPaginas.controller.paginasDoSistema.FabAcaoPaginasDoSistema;
import com.super_bits.modulosSB.webPaginas.controller.paginasDoSistema.InfoAcaoPaginaDoSistema;
import com.super_bits.modulosSB.webPaginas.controller.servlets.servletWebPaginas.EstruturaDeFormulario;
import com.super_bits.modulosSB.webPaginas.controller.servletes.urls.parametrosURL.InfoParametroURL;
import com.super_bits.modulosSB.webPaginas.controller.servletes.urls.parametrosURL.ParametroURL;
import com.super_bits.modulosSB.SBCore.modulos.Controller.qualificadoresCDI.sessao.QlSessaoFacesContext;
import com.super_bits.modulosSB.webPaginas.controller.sessao.SessaoAtualSBWP;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWP_JSFTools;
import java.util.List;
import java.util.Optional;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

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
    public ItfBeanSimples getBeanSelecionado() {
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

        if (getParametroInstanciado(prCodigoDeAcesso).isValorDoParametroFoiConfigurado()) {
            System.out.println("Vai!");
        }
        if (tokenDinamico == null) {

            acessonegado();
        }

    }

    public String getUrlAcaoDoToken() {
        if (getTokenDinamico() == null) {
            acessonegado();
            return "";
        }
        if (tokenDinamico.getEntidadeDoAcesso() != null) {
            Class tipoEntidade = MapaObjetosProjetoAtual.getClasseDoObjetoByNome(tokenDinamico.getEntidadeDoAcesso());
            ItfBeanSimples entidade = (ItfBeanSimples) UtilSBPersistencia.getRegistroByID(tipoEntidade, Integer.valueOf(tokenDinamico.getCodigoEntidade()), getEMPagina());
            String slugAcao = tokenDinamico.getSlugAcaoFormulario();
            ItfAcaoDoSistema acao = MapaAcoesSistema.getAcaoDoSistemaByNomeUnico(slugAcao);
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
            List<ParametroURL> parametros = estrutura.getParametrosURL();
            Optional<ParametroURL> parametroToken = parametros.stream().filter(pr -> pr.getTipoEntidade().equals(TokenAcessoDinamico.class)).findFirst();
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
    public void setBeanSelecionado(ItfBeanSimples pBeanSimples) {
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
