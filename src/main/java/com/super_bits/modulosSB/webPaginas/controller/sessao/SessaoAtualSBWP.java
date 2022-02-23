/*
 *  Super-Bits.com CODE CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.controller.sessao;

import com.super_bits.modulosSB.SBCore.modulos.Controller.qualificadoresCDI.sessao.QlSessaoFacesContext;
import com.sun.jndi.toolkit.url.UrlUtil;
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
import com.super_bits.modulosSB.webPaginas.ConfigGeral.SBWebPaginas;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import org.apache.http.protocol.RequestContent;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import org.jboss.weld.context.RequestContext;
import org.jboss.weld.context.http.Http;
import org.jboss.weld.context.http.HttpRequestContextImpl;
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
    private String urlHostDaSessao;

    @Inject
    @Http
    private RequestContext requestContext;

    public SessaoAtualSBWP() {
        super();
    }

    public String getUrlHostDaSessao() {
        return urlHostDaSessao;
    }

    @PostConstruct
    public void inicio() {

        URL enderecoAnalise;
        try {
            FacesContext contextoJSF = FacesContext.getCurrentInstance();

            HttpServletRequest origRequest = null;
            if (contextoJSF != null) {
                origRequest = (HttpServletRequest) contextoJSF.getExternalContext().getRequest();

            } else {
                HttpRequestContextImpl teste = (HttpRequestContextImpl) requestContext;
                origRequest = teste.getHttpServletRequest();
            }
            String url = origRequest.getRequestURL().toString();
            String urlDigitada = url;
            System.out.println("URLDIGITADA:" + urlDigitada);
            enderecoAnalise = new URL(url);
            String host = enderecoAnalise.getHost();
            String protocolo = enderecoAnalise.getProtocol();
            int porta = enderecoAnalise.getPort();

            if (SBCore.isEmModoProducao()) {
                urlHostDaSessao = "https" + "://" + host;
            } else {
                urlHostDaSessao = protocolo + "://" + host + ":" + String.valueOf(porta);
            }

            System.out.println("URL host construida como: " + urlHostDaSessao);
            System.out.println("URLS permitidas=" + SBWebPaginas.getURLSHostsPermitidos());
            if (!SBWebPaginas.getURLSHostsPermitidos().contains(urlHostDaSessao)) {
                urlHostDaSessao = SBWebPaginas.getSiteURL();
            }
            System.out.println("A url foi definida como " + urlHostDaSessao);

        } catch (MalformedURLException ex) {
            Logger.getLogger(SessaoAtualSBWP.class.getName()).log(Level.SEVERE, null, ex);
        }

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

        encerrarSessao(true);

    }

    public void encerrarSessao(boolean pRedirecionarPagina) {
        super.encerrarSessao();
        UtilSBWP_JSFTools.encerrarSessaoJSessionId();
        if (pRedirecionarPagina) {
            UtilSBWP_JSFTools.vaParaPaginaInicial();
        }
    }

    private void atualizarAvatar() {

    }

    public void atualizarMeuAvatar(FileUploadEvent event) {

        try {

            ItfUsuario usuario = getUsuario();
            InputStream arquivo;
            if (!UtilSBCoreStringNomeArquivosEDiretorios.getExtencaoNomeArquivo(event.getFile().getFileName()).equals("png")) {

                BufferedImage imagem = UtilSBImagemEdicao.converterPNGParaJpg(ImageIO.read(event.getFile().getInputStream()), Color.white);
                UtilSBImagemEdicao.reduzirProporcionalAlturaMaxima(imagem, 85, "jpg");
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                ImageIO.write(imagem, "jpg", os);
                arquivo = new ByteArrayInputStream(os.toByteArray());
            } else {
                arquivo = event.getFile().getInputStream();
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

                BufferedImage imagem = UtilSBImagemEdicao.converterPNGParaJpg(ImageIO.read(event.getFile().getInputStream()), Color.white);
                UtilSBImagemEdicao.reduzirProporcionalAlturaMaxima(imagem, 85, "jpg");
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                ImageIO.write(imagem, "jpg", os);
                arquivo = new ByteArrayInputStream(os.toByteArray());
            } else {
                arquivo = event.getFile().getInputStream();
            }

            usuario.uploadFotoTamanhoPequeno(arquivo);
            UtilSBPersistencia.fecharEM(em);
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro enviando imagem", t);
        }

    }

}
