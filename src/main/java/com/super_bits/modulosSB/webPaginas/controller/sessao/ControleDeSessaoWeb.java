/*
 *  Super-Bits.com CODE CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.controller.sessao;

import com.sun.faces.config.InitFacesContext;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreEmail;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.token.ItfTokenRecuperacaoEmail;
import com.super_bits.modulosSB.SBCore.modulos.Mensagens.FabMensagens;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.ItensGenericos.basico.UsuarioSistemaRoot;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfGrupoUsuario;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfSessao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfUsuario;
import com.super_bits.modulosSB.SBCore.modulos.sessao.ControleDeSessaoAbstratoSBCore;
import com.super_bits.modulosSB.webPaginas.ConfigGeral.SBWebPaginas;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.siteMap.AcaoComLink;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.siteMap.MapaDeFormularios;
import com.super_bits.modulosSB.webPaginas.controller.paginasDoSistema.FabAcaoPaginasDoSistema;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWPServletTools;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWP_JSFTools;
import com.super_bits.modulosSB.webPaginas.util.UtillSBWPReflexoesWebpaginas;
import java.io.File;
import java.io.Serializable;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Default;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import org.coletivojava.fw.utilCoreBase.UtilSBCoreComunicacao;

/**
 *
 *
 *
 *
 * @author Salvio
 */
@RequestScoped
@Named
public class ControleDeSessaoWeb extends ControleDeSessaoAbstratoSBCore implements Serializable {

    @Inject
    @QlSessaoFacesContext
    private ItfSessao sessaoAtual;

    private String usuarioLogar;
    private String senhaLogar;

    public ControleDeSessaoWeb() {

    }

    @Override
    public ItfSessao getSessaoAtual() {

        if (sessaoAtual == null) {
            Object instanciaFaces = FacesContext.getCurrentInstance();
            if (instanciaFaces != null && !(instanciaFaces instanceof InitFacesContext)) {
                sessaoAtual = UtilSBWPServletTools.getSessaoAtual();

            } else {
                sessaoAtual = new SessaoAtualWebDoApllicativo();

            }
            if (sessaoAtual != null) {
                return sessaoAtual;
            }

        } else {
            return sessaoAtual;

        }

        SBCore.RelatarErro(FabErro.PARA_TUDO, "ERRO-SESSAO ATUAL NULA, O CONTRLOE DE SESSAO DEVERIA ESTAR SENDO REQUISITADO VIA CDI", null);
        return null;
    }

    @Override
    public void efetuarLogIn() {
        logarEmailESenha(usuarioLogar, senhaLogar);
        UtilSBWP_JSFTools.atualizaPorId("infoLoginSB");
        if (sessaoAtual.isIdentificado()) {

            UtilSBWP_JSFTools.executarJavaScript("location.reload();");
            String pastaTEmporariaStr = SBCore.getCentralDeSessao().getSessaoAtual().getPastaTempDeSessao();
            Thread exclusaoArquivosTemporarios = new Thread() {
                public void run() {
                    try {
                        File pastaTemporaria = new File(pastaTEmporariaStr);
                        //Proteção besta para evitar exclusão de pastas do sistema
                        if (pastaTEmporariaStr.length() > 15) {
                            if (pastaTemporaria.exists()) {
                                if (pastaTemporaria.isDirectory()) {
                                    for (File arq : pastaTemporaria.listFiles()) {
                                        arq.delete();
                                    }
                                }
                            }
                        }
                    } catch (Throwable t) {

                    }
                }
            };

            exclusaoArquivosTemporarios.start();

        }
        SBCore.getCentralPermissao().configuraPermissoes();
        ItfGrupoUsuario grupoUsuarioLogado = sessaoAtual.getUsuario().getGrupo();

        ((SessaoAtualSBWP) sessaoAtual).setMenusDaSessao(SBCore.getCentralPermissao().definirMenu(grupoUsuarioLogado));

    }

    public void esqueceuaSenha() {

        enviarSenhaParaEmail(usuarioLogar);
    }

    @Override
    protected void enviarSenhaParaEmail(String pEmail) {
        ItfUsuario usuarioEncontrado = SBCore.getCentralPermissao().getUsuarioByEmail(pEmail);

        if (usuarioEncontrado == null) {
            SBCore.enviarMensagemUsuario("O email " + pEmail + " não foi encontrado no sistema", FabMensagens.ALERTA);
        } else {

            ItfTokenRecuperacaoEmail token = SBCore.getServicoPermissao().gerarTokenRecuperacaoDeSenha(usuarioEncontrado, 10080);
            if (token == null) {
                SBCore.enviarMensagemUsuario("Erro gerando token de acesso", FabMensagens.ERRO);
                return;
            }
            if (UtilSBCoreEmail.enviarPorServidorPadrao(
                    pEmail,
                    UtilSBCoreComunicacao.getSaudacao() + ", " + usuarioEncontrado.getNome()
                    + ", utilize <a href=\" " + MapaDeFormularios.
                            getUrlFormulario(FabAcaoPaginasDoSistema.PAGINA_NATIVA_RECUPERACAO_SENHA_MB.getRegistro(), token)
                    + "\" > este link,</a> para recuperar sua senha.",
                    "Recuperação de senha")) {
                SBCore.enviarAvisoAoUsuario("Um e-mail com um link para alterar a senha foi  enviado para " + pEmail);
            } else {
                SBCore.enviarMensagemUsuario("Um erro ocorreu ao tentar enviar o e-mail com a senha para: " + pEmail + " entre em contato conosco para recuperar a senha", FabMensagens.ALERTA);
            }
        }
    }

    @Override
    public void efetuarLogOut() {
        getSessaoAtual().encerrarSessao();

    }

    public String getUsuarioLogar() {
        return usuarioLogar;
    }

    public void setUsuarioLogar(String usuario) {
        this.usuarioLogar = usuario;
    }

    public String getSenhaLogar() {
        return senhaLogar;
    }

    public void setSenhaLogar(String senha) {
        this.senhaLogar = senha;
    }

    public String getSessionID() {

        try {
            FacesContext fCtx = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) fCtx.getExternalContext().getSession(false);
            return session.getId();
        } catch (Exception e) {
            System.out.println("FaceCOntexto não encontrado, retornando sessao 00000");
            return "00000000000000000000000000000000";
        }
    }

    @Override
    public void logarComoRoot() {

        if (SBCore.getEstadoAPP() != SBCore.ESTADO_APP.DESENVOLVIMENTO) {
            throw new UnsupportedOperationException("O método logar como Root não é suportado no ambiente web, por questões de segurança");
        } else {
            getSessaoAtual().setUsuario(new UsuarioSistemaRoot());
        }
    }

    @Override
    public void logarComoAnonimo() {
        efetuarLogOut();
    }

    @PreDestroy
    public void fecharSessao() {

    }

}
