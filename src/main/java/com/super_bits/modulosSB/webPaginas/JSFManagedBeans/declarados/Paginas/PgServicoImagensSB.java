/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulosSB.webPaginas.JSFManagedBeans.declarados.Paginas;

import com.super_bits.editorImagem.util.UtilSBImagemEdicao;
import com.super_bits.modulosSB.Persistencia.dao.ExecucaoComGestaoEntityManager;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UTilSBCoreInputs;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCInputOutputConversoes;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringNomeArquivosEDiretorios;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoEntidadeSimples;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.imageio.ImageIO;
import javax.inject.Named;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author desenvolvedor
 */
@Named
@RequestScoped
public class PgServicoImagensSB implements Serializable {

    private ComoEntidadeSimples entidadeSelecionada;

    private void ajustarTamanho(ComoEntidadeSimples pEntidade, String pUrl, int larguraMaxima, int alturaMaxima) {
        try {
            BufferedImage imagem = UTilSBCoreInputs.getImagemBufferedbyURL(pUrl);
            String extencao = UtilCRCStringNomeArquivosEDiretorios.getExtencaoNomeArquivoSemPonto(pUrl);
            imagem = UtilSBImagemEdicao.reduzirProporcionalAlturaMaxima(imagem, 85, extencao);
            InputStream inputImagem = UtilCRCInputOutputConversoes.BufferedImageToInputStream(imagem, extencao);

            pEntidade.uploadFotoTamanhoPequeno(inputImagem);
        } catch (Throwable t) {

            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro processanod imagem", t);
        }
    }

    public void atualizarImagemTamanhoMedio(FileUploadEvent event) {

        new ExecucaoComGestaoEntityManager() {
            @Override
            public void regraDeNegocio() {

                try {
                    Object atributo = event.getComponent().getAttributes().get("entidadeIMG");
                    if (!(atributo instanceof ComoEntidadeSimples)) {
                        throw new UnsupportedOperationException("A entidade precisa ser um bean Simples");
                    }

                    ComoEntidadeSimples entidadeImagem = (ComoEntidadeSimples) atributo;

                    if (entidadeImagem.getId() == null) {

                    } else {
                        entidadeImagem = UtilSBPersistencia.loadEntidade(entidadeImagem, getEm());
                    }

                    InputStream arquivo;
                    if (!UtilCRCStringNomeArquivosEDiretorios.getExtencaoNomeArquivo(event.getFile().getFileName()).equals("png")) {

                        BufferedImage imagem = UtilSBImagemEdicao.converterPNGParaJpg(ImageIO.read(event.getFile().getInputStream()), Color.white);
                        ByteArrayOutputStream os = new ByteArrayOutputStream();
                        ImageIO.write(imagem, "jpg", os);
                        arquivo = new ByteArrayInputStream(os.toByteArray());
                    } else {
                        arquivo = event.getFile().getInputStream();
                    }
                    entidadeImagem.uploadFotoTamanhoMedio(arquivo);
                } catch (Throwable t) {
                    SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro enviando imagem", t);
                }

            }
        };

    }

    public void atualizarImagemTamanhoPequeno(FileUploadEvent event) {

        new ExecucaoComGestaoEntityManager() {
            @Override
            public void regraDeNegocio() {

                try {
                    Object atributo = event.getComponent().getAttributes().get("idProspecto");
                    Long id = Long.parseLong(atributo.toString());
                    ComoEntidadeSimples entidadeAtualizada = UtilSBPersistencia.getRegistroByID(entidadeSelecionada.getClass(), id, getEm());
                    InputStream arquivo;
                    if (!UtilCRCStringNomeArquivosEDiretorios.getExtencaoNomeArquivo(event.getFile().getFileName()).equals("png")) {

                        BufferedImage imagem = UtilSBImagemEdicao.converterPNGParaJpg(ImageIO.read(event.getFile().getInputStream()), Color.white);
                        ByteArrayOutputStream os = new ByteArrayOutputStream();
                        ImageIO.write(imagem, "jpg", os);
                        arquivo = new ByteArrayInputStream(os.toByteArray());
                    } else {
                        arquivo = event.getFile().getInputStream();
                    }

                    entidadeAtualizada.uploadFotoTamanhoPequeno(arquivo);

                } catch (Throwable t) {
                    SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro enviando imagem", t);
                }

            }
        };

    }

    public void atualizarImagemTamanhoGrande(FileUploadEvent event) {

        new ExecucaoComGestaoEntityManager() {
            @Override
            public void regraDeNegocio() {

                try {
                    Object atributo = event.getComponent().getAttributes().get("idProspecto");
                    Long id = Long.parseLong(atributo.toString());
                    ComoEntidadeSimples entidadeAtualizada = UtilSBPersistencia.getRegistroByID(entidadeSelecionada.getClass(), id, getEm());
                    InputStream arquivo;
                    if (!UtilCRCStringNomeArquivosEDiretorios.getExtencaoNomeArquivo(event.getFile().getFileName()).equals("png")) {

                        BufferedImage imagem = UtilSBImagemEdicao.converterPNGParaJpg(ImageIO.read(event.getFile().getInputStream()), Color.white);
                        ByteArrayOutputStream os = new ByteArrayOutputStream();
                        ImageIO.write(imagem, "jpg", os);
                        arquivo = new ByteArrayInputStream(os.toByteArray());
                    } else {
                        arquivo = event.getFile().getInputStream();
                    }

                    entidadeAtualizada.uploadFotoTamanhoGrande(arquivo);

                } catch (Throwable t) {
                    SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro enviando imagem", t);
                }

            }
        };

    }

    public ComoEntidadeSimples getEntidadeSelecionada() {
        return entidadeSelecionada;
    }

    public void setEntidadeSelecionada(ComoEntidadeSimples entidadeSelecionada) {
        this.entidadeSelecionada = entidadeSelecionada;
    }

}
