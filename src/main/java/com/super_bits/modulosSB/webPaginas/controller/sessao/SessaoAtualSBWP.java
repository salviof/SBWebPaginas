/*
 *  Super-Bits.com CODE CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.controller.sessao;

import com.super_bits.editorImagem.util.UtilSBImagemEdicao;
import com.super_bits.modulos.SBAcessosModel.model.UsuarioSB;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringNomeArquivosEDiretorios;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfModuloAcaoSistema;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.ItensGenericos.basico.SessaoOffline;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfSessao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfUsuario;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWPServletTools;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWP_JSFTools;
import java.io.File;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import com.super_bits.modulosSB.SBCore.modulos.view.telas.ItfTelaUsuario;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author Salvio
 */
@SessionScoped
@QlSessaoFacesContext
@Named
public class SessaoAtualSBWP extends SessaoOffline implements ItfSessao, Serializable {

    private boolean tipoViewDefinido = false;
    private ItfTelaUsuario tipoView;
    private ItfModuloAcaoSistema moduloSelecionado;
    @Inject
    private EntityManager entidadePrincipal;

    public SessaoAtualSBWP() {
        super();
    }

    public void defineInfoTela() {
        String infoTela = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("contactfrm:clientinfo");
        tipoView = new TelaWP(infoTela);
        tipoViewDefinido = true;

    }

    @Override
    public ItfTelaUsuario getTelaUsuario() {
        return tipoView;
    }

    @Override
    public boolean isTipoViewDefinido() {
        return tipoViewDefinido;
    }

    @Override
    public String getPastaTempDeSessao() {
        if (pastaTempDeSessao == null) {
            pastaTempDeSessao = UtilSBWPServletTools.getCaminhoLocalServletsResource()
                    + "/arqSessao/" + getUsuario().getEmail().hashCode();
        }
        File pastaTempSessao = new File(pastaTempDeSessao + "/");
        if (!pastaTempSessao.exists()) {
            pastaTempSessao.mkdirs();
        }
        return pastaTempDeSessao;
    }

    @Override
    public ItfUsuario getUsuario() {
        ItfUsuario usuario = (ItfUsuario) UtilSBWP_JSFTools.retirarProxyDeVisualizacaoDoBean(super.getUsuario());

        if (usuario.getClass().getAnnotation(Entity.class) != null) {
            usuario = UtilSBPersistencia.loadEntidade(usuario, entidadePrincipal);

        }
        return usuario;

    }

    @Override
    public void encerrarSessao() {
        super.encerrarSessao();

        UtilSBWP_JSFTools.encerrarSessaoJSessionId();
        UtilSBWP_JSFTools.vaParaPaginaInicial();
    }

    private void atualizarAvatar() {

    }

    public void atualizarMeuAvatar(FileUploadEvent event) {

        try {

            ItfUsuario usuario = getUsuario();
            InputStream arquivo;
            if (!UtilSBCoreStringNomeArquivosEDiretorios.getExtencaoNomeArquivo(event.getFile().getFileName()).equals("png")) {

                BufferedImage imagem = UtilSBImagemEdicao.converterPNGParaJpg(ImageIO.read(event.getFile().getInputstream()), Color.white);
                UtilSBImagemEdicao.reduzirProporcionalAlturaMaxima(imagem, 85, "jpg");
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                ImageIO.write(imagem, "jpg", os);
                arquivo = new ByteArrayInputStream(os.toByteArray());
            } else {
                arquivo = event.getFile().getInputstream();
            }

            usuario.uploadFotoTamanhoPequeno(arquivo);

        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro enviando imagem", t);
        }

    }

    //TODO politica de segurança
    public void atualizarAvatar(FileUploadEvent event) {
        try {

            Object atributo = event.getComponent().getAttributes().get("idUsuario");
            if (atributo == null) {
                return;
            }

            int id = Integer.parseInt(atributo.toString());
            if (id == 0) {
                return;
            }
            EntityManager em = UtilSBPersistencia.getEntyManagerPadraoNovo();
            UsuarioSB usuario = UtilSBPersistencia.getRegistroByID(UsuarioSB.class, id, em);
            InputStream arquivo;
            if (!UtilSBCoreStringNomeArquivosEDiretorios.getExtencaoNomeArquivo(event.getFile().getFileName()).equals("png")) {

                BufferedImage imagem = UtilSBImagemEdicao.converterPNGParaJpg(ImageIO.read(event.getFile().getInputstream()), Color.white);
                UtilSBImagemEdicao.reduzirProporcionalAlturaMaxima(imagem, 85, "jpg");
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                ImageIO.write(imagem, "jpg", os);
                arquivo = new ByteArrayInputStream(os.toByteArray());
            } else {
                arquivo = event.getFile().getInputstream();
            }

            usuario.uploadFotoTamanhoPequeno(arquivo);
            UtilSBPersistencia.fecharEM(em);
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro enviando imagem", t);
        }

    }

}
