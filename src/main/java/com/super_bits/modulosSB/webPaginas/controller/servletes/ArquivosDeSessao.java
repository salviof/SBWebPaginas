/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.controller.servletes;

import com.super_bits.editorImagem.ItfResolucao;
import com.super_bits.editorImagem.Resolucao;
import com.super_bits.editorImagem.util.UtilSBImagemEdicao;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.webPaginas.controller.sessao.ArquivoDeSessao;
import com.super_bits.modulosSB.webPaginas.controller.sessao.GestaoDeArquivoSessao;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

/**
 *
 * ATENÇÃO A DOCUMENTAÇÃO DA CLASSE É OBRIGATÓRIA O JAVADOC DOS METODOS PUBLICOS
 * SÓ SÃO OBRIGATÓRIOS QUANDO NÃO EXISTIR UMA INTERFACE DOCUMENTADA, DESCREVA DE
 * FORMA OBJETIVA E EFICIENTE, NÃO ESQUEÇA QUE VOCÊ FAZ PARTE DE UMA EQUIPE.
 *
 *
 * @author <a href="mailto:salviof@gmail.com">Salvio Furbino</a>
 * @since 31/03/2015
 * @version 1.0
 */
public class ArquivosDeSessao extends ServletImagemGenerico {

    @Inject
    private GestaoDeArquivoSessao arqSession;

    @Override
    protected BufferedImage trataimagem(BufferedImage pImagemOriginal, HttpServletRequest pRequest) {
        try {
            Object parametro = pRequest.getAttribute("resolucao");
            if (parametro != null) {

                Resolucao resolucao = (Resolucao) parametro;
                pImagemOriginal = ImageIO.read(UtilSBImagemEdicao.reduzImagemTamanhoEspecifico(pImagemOriginal, resolucao.getLargura(), resolucao.getAltura()));
            } else {
                System.out.println("Objeto Resolução não Encontrado");
            }
        } catch (Throwable e) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro gerando Imagem reduzida", e);
        }
        return pImagemOriginal;
    }

    @Override
    protected String getCaminho(HttpServletRequest pRequest) {

        String comando = getParametrosURL(pRequest);
        String[] parametros = comando.split("/");
        String arquivoLocal = "";

        System.out.println("Parametros Encontrados no comando:" + comando);
        for (String prr : parametros) {
            System.out.println(prr);
        }

        String pasta = parametros[1];
        arquivoLocal = parametros[2];
        System.out.println("Arquivo" + arquivoLocal);
        System.out.println("Pasta:" + pasta);

        if (parametros.length == 4) {
            try {
                String[] resolucaoSTR = parametros[3].split("X");
                ItfResolucao resolucao = new Resolucao(Integer.parseInt(resolucaoSTR[0]), Integer.parseInt(resolucaoSTR[1]));
                pRequest.setAttribute("resolucao", resolucao);
            } catch (Throwable e) {
                SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro determinando resolução", e);
            }
        }

        for (ArquivoDeSessao arq : arqSession.getArquivosDeSessao()) {
            if (arq.getNomeArquivo().equals(arquivoLocal) & arq.getNomePasta().equals(pasta)) {

                arquivoLocal = arq.getCaminhoLocal();
            }
        }

        return arquivoLocal;
    }

    @Override
    protected TIPOIMAGEM getTipoImagem() {
        return TIPOIMAGEM.IMAGEMAPENAS;
    }

}
