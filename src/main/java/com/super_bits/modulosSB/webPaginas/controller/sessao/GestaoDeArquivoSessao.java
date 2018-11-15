/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.controller.sessao;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UTilSBCoreInputs;
import com.super_bits.modulosSB.webPaginas.ConfigGeral.SBWebPaginas;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import javax.enterprise.context.SessionScoped;
import javax.imageio.ImageIO;
import javax.inject.Inject;
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
@SessionScoped
public class GestaoDeArquivoSessao implements Serializable {

    private final ConcurrentLinkedQueue<ArquivoDeSessao> arquivosDeSessao;
    @Inject
    private ControleDeSessaoWeb controleDeSessao;

    public GestaoDeArquivoSessao() {
        arquivosDeSessao = new ConcurrentLinkedQueue<>();
        if (SBCore.getEstadoAPP() == SBCore.ESTADO_APP.DESENVOLVIMENTO) {
            controleDeSessao = new ControleDeSessaoWeb();
        }
    }

    public List<String> getLinkArquivosDeSessao(String pasta) {
        throw new UnsupportedOperationException("ainda não implementado");
    }

    public List<String> getLinkArquivosDeSessao() {
        List<String> novaLista = new ArrayList<>();
        for (ArquivoDeSessao arq : arquivosDeSessao) {
            novaLista.add(SBWebPaginas.getSiteURL() + "arquivoDeSessao/" + arq.getNomeArquivo());
        }

        return novaLista;
    }

    public void adcionaarquivo(String pArquivo, InputStream pStream) {

        adcionaarquivo(pArquivo, pStream, "padrao");
    }

    public void adcionaarquivo(String pNomeArquivo, InputStream pStream, String pDiretorio) {

        try {
            ArquivoDeSessao arquivoDeSessao = new ArquivoDeSessao(pNomeArquivo, pDiretorio, ArquivoDeSessao.TIPOARQUIVO.JPG, controleDeSessao.getSessionID());

            BufferedImage imgBuff = ImageIO.read(pStream);
            if (imgBuff == null) {
                System.out.println("imageBuff nulo");
                throw new UnsupportedOperationException("Arquivo inválido impossível criar um Buffered Image a partir do InputStream");
            }
            adcionaarquivo(arquivoDeSessao, imgBuff);

        } catch (IOException | UnsupportedOperationException e) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO,"erro Salvando arquivo com parametros " + pDiretorio + pNomeArquivo, e);
        }
    }

    private synchronized void adcionaarquivo(ArquivoDeSessao pArquivoDeSessao, BufferedImage pImgBf) {

        try {
            File pastas = new File(pArquivoDeSessao.getPastaLocal());
            pastas.mkdirs();
            File arquivo = new File(pArquivoDeSessao.getCaminhoLocal());
            arquivosDeSessao.add(pArquivoDeSessao);
            ImageIO.write(pImgBf, "jpg", arquivo);

        } catch (Exception e) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO,"erro Salvando arquivo com parametros " + pArquivoDeSessao, e);
        }

    }

    public ArquivoDeSessao adcionaarquivo(String pNomeArquivo, BufferedImage pImgBf, String pDiretorio) {

        try {
            ArquivoDeSessao arquivoDeSessao = new ArquivoDeSessao(pNomeArquivo, pDiretorio, ArquivoDeSessao.TIPOARQUIVO.JPG, controleDeSessao.getSessionID());
            adcionaarquivo(arquivoDeSessao, pImgBf);
            return arquivoDeSessao;
        } catch (Exception e) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO,"erro Salvando arquivo com parametros " + pDiretorio + pNomeArquivo, e);
        }
        return null;
    }

    public void limparPasta(String pNomePasta) {
        synchronized (arquivosDeSessao) {

            int i = 0;
            for (ArquivoDeSessao arq : arquivosDeSessao) {

                if (arq.getNomePasta().equals(pNomePasta)) {
                    File pasta = new File(arq.getPastaLocal());
                    arquivosDeSessao.remove(arq);

                    try {
                        pasta.delete();
                    } catch (Exception e) {
                        SBCore.RelatarErro(FabErro.SOLICITAR_REPARO,"Erro excluindo diretório" + pNomePasta, e);
                    }

                }
                i++;
            }

        }
    }

    public ConcurrentLinkedQueue<ArquivoDeSessao> getArquivosDeSessao() {
        return arquivosDeSessao;
    }

    public BufferedImage getImagemArquivoDeSessao(ArquivoDeSessao pArquivo) {
        return UTilSBCoreInputs.getImagemBufferedByLocalFile(pArquivo.getCaminhoLocal());
    }

    public InputStream getStreamByArquivoDeSessao(ArquivoDeSessao pArquivo) {
        return UTilSBCoreInputs.getStreamByLocalFile(pArquivo.getCaminhoLocal());
    }

    public List<ArquivoDeSessao> getArquivosDeSessaoDaPasta(String pPasta) {
        List<ArquivoDeSessao> arqPasta = new ArrayList<>();
        for (ArquivoDeSessao arq : arquivosDeSessao) {
            if (arq.getNomePasta().equals(pPasta)) {
                arqPasta.add(arq);
            }

        }
        return arqPasta;
    }

}
