/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFManagedBeans.declarados.Paginas;

import com.super_bits.modulos.SBAcessosModel.model.UsuarioSB;
import com.super_bits.modulos.SBAcessosModel.model.tokens.tokenRecuperacaoDeSenha.TokenRecuperacaoSenha;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfParametroRequisicaoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.TIPO_PARTE_URL;
import com.super_bits.modulosSB.SBCore.modulos.Mensagens.FabMensagens;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfUsuario;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ErroValidacao;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.MB_PaginaConversation;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.reflexao.anotacoes.InfoPagina;

import com.super_bits.modulos.SBAcessosModel.view.FabAcaoPaginasDoSistema;
import com.super_bits.modulos.SBAcessosModel.view.InfoAcaoPaginaDoSistema;
import com.super_bits.modulosSB.webPaginas.controller.servletes.urls.parametrosURL.InfoParametroURL;
import com.super_bits.modulosSB.webPaginas.controller.servletes.urls.parametrosURL.ParametroURL;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWP_JSFTools;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 * ATENÇÃO A DOCUMENTAÇÃO DA CLASSE É OBRIGATÓRIA O JAVADOC DOS METODOS PUBLICOS
 * SÃO OBRIGATÓRIOS E QUANDO EXISTIR UMA INTERFACE DOCUMENTADA UMA REFERENCIA
 * DEVE SER CRIADA, A SINTAXE DE REFERENCIA É: @see_ NomeDAClasse#Metodo
 * DOCUMENTE DE FORMA OBJETIVA E EFICIENTE, NÃO ESQUEÇA QUE VOCÊ FAZ PARTE DE
 * UMA EQUIPE.
 *
 * @author <a href="mailto:salviof@gmail.com">Salvio Furbino</a>
 * @since 05/01/2016
 * @version 1.0
 *
 */
@InfoAcaoPaginaDoSistema(acao = FabAcaoPaginasDoSistema.PAGINA_NATIVA_RECUPERACAO_SENHA_MB)
@Named
@ViewScoped
@InfoPagina(nomeCurto = "trocarsenha", tags = "TrocarMinhaSenha")
public class PgRecuperacaoSenha extends MB_PaginaConversation {

    @InfoParametroURL(tipoParametro = TIPO_PARTE_URL.ENTIDADE,
            tipoEntidade = TokenRecuperacaoSenha.class,
            nome = "token Recuperação de senha")
    private ParametroURL tokenAcesso;

    private boolean acessonegado;

    private String novaSenha;

    private void acessonegado() {
        setAcaoSelecionada(FabAcaoPaginasDoSistema.PAGINA_NATIVA_ACESSO_NEGADO_FRM_SUB_FORM.getRegistro());
        xhtmlAcaoAtual = FabAcaoPaginasDoSistema.PAGINA_NATIVA_ACESSO_NEGADO_FRM_SUB_FORM.getRegistro().getComoFormulario().getXhtml();
    }

    public UsuarioSB getUsuario() {
        ItfParametroRequisicaoInstanciado pr = getParametroInstanciado(tokenAcesso);
        if (!getParametroInstanciado(tokenAcesso).isValorDoParametroFoiConfigurado()) {
            return null;
        }
        TokenRecuperacaoSenha token = (TokenRecuperacaoSenha) pr.getValor();
        ItfUsuario usuario = SBCore.getServicoPermissao().getUsuarioByEmail(token.getEmail());
        return (UsuarioSB) usuario;
    }

    public boolean alterarSenha() {

        boolean senhaEsperada = false;
        try {

            UsuarioSB usuario = getUsuario();
            if (usuario == null) {
                SBCore.enviarMensagemUsuario("Usuário não encontrado ", FabMensagens.ERRO);
            } else {
                if (acessonegado) {
                    throw new UnsupportedOperationException("O acesso não foi validado");
                }
                UsuarioSB usr = (UsuarioSB) usuario;
                try {
                    usr.getCPinst("senha").setValorSeValido(novaSenha);
                    if (UtilSBPersistencia.mergeRegistro(usr, getEMPagina()) != null) {
                        senhaEsperada = true;
                    }
                    UtilSBPersistencia.exluirRegistro(getParametroInstanciado(tokenAcesso).getValor());
                } catch (ErroValidacao ex) {
                    SBCore.enviarMensagemUsuario(ex.getMessage(), FabMensagens.ALERTA);
                }

                UtilSBWP_JSFTools.vaParaPaginaInicial();
                SBCore.enviarAvisoAoUsuario("A senha foi alterada");
            }
        } catch (Throwable t) {
            SBCore.enviarMensagemUsuario("Erro alterando senha", FabMensagens.ERRO);
        }
        return senhaEsperada;
    }

    @PostConstruct
    public void inicio() {
        acessonegado = true;

        if (false) {
            setAcaoSelecionada(FabAcaoPaginasDoSistema.PAGINA_NATIVA_RECUPERACAO_SENHA_FRM_GERAR_NOVA_SENHA.getRegistro().getComoFormulario());
            xhtmlAcaoAtual = FabAcaoPaginasDoSistema.PAGINA_NATIVA_RECUPERACAO_SENHA_FRM_GERAR_NOVA_SENHA.getRegistro().getComoFormulario().getXhtml();
            return;
        }
        try {
            ItfParametroRequisicaoInstanciado pr = getParametroInstanciado(tokenAcesso);
            if (!pr.isValorDoParametroFoiConfigurado()) {
                throw new UnsupportedOperationException("token não foi enviado");
            }

            int fimCodigo = pr.getTextoEnviadoUrl().lastIndexOf("-");
            String codigo = pr.getTextoEnviadoUrl().substring(0, fimCodigo);

            TokenRecuperacaoSenha token = (TokenRecuperacaoSenha) pr.getValor();
            if (token.getValidade().getTime() <= new Date().getTime()) {
                throw new UnsupportedOperationException("Expirado");
            }
            if (codigo.equals(token.getCodigo())) {
                acessonegado = false;
                setAcaoSelecionada(FabAcaoPaginasDoSistema.PAGINA_NATIVA_RECUPERACAO_SENHA_FRM_GERAR_NOVA_SENHA.getRegistro().getComoFormulario());
                xhtmlAcaoAtual = FabAcaoPaginasDoSistema.PAGINA_NATIVA_RECUPERACAO_SENHA_FRM_GERAR_NOVA_SENHA.getRegistro().getComoFormulario().getXhtml();
            }
            if (acessonegado) {
                acessonegado();
            }
        } catch (Throwable t) {
            acessonegado();
        }
    }

    @Override
    public ItfBeanSimples getBeanSelecionado() {
        ItfParametroRequisicaoInstanciado pr = getParametroInstanciado(tokenAcesso);
        return (TokenRecuperacaoSenha) pr.getValor();
    }

    @Override
    public void setBeanSelecionado(ItfBeanSimples pBeanSimples) {
        throw new UnsupportedOperationException("O METODO AINDA N\u00c3O FOI IMPLEMENTADO.");
    }

    public String getNovaSenha() {
        return novaSenha;
    }

    public void setNovaSenha(String novaSenha) {
        this.novaSenha = novaSenha;
    }

}
