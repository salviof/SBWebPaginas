/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.controller.sessao;

import com.super_bits.modulosSB.webPaginas.ConfigGeral.SBWebPaginas;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWPServletTools;
import java.io.Serializable;

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
public class ArquivoDeSessao implements Serializable {

    public static enum TIPOARQUIVO {

        JPG, ZIP
    }

    public static String PASTA_PADRAO = "UPLOADFILES";

    public ArquivoDeSessao(String pNomeArquivo, String pPasa, TIPOARQUIVO ptipoArquivo, String pJsessionID) {
        nomeArquivo = pNomeArquivo;
        nomePasta = pPasa;
        tipoArquivo = ptipoArquivo;
        jSessionID = pJsessionID;
        configPaths();
    }

    private void configPaths() {
        pastaLocal = UtilSBWPServletTools.getCaminhoLocalServlet() + "/" + jSessionID + "/" + tipoArquivo + nomePasta;
        caminhoLocal = pastaLocal + "/" + nomeArquivo;

        caminhoRemoto = SBWebPaginas.getSiteURL() + "/arquivosDeSessao/" + nomePasta + "/" + nomeArquivo;
    }

    public ArquivoDeSessao(String pNomeArquivo, TIPOARQUIVO ptipoArquivo, String pJsessionID) {
        nomeArquivo = pNomeArquivo;
        tipoArquivo = ptipoArquivo;
        nomePasta = PASTA_PADRAO;
        jSessionID = pJsessionID;
        configPaths();
    }

    private String nomePasta;
    private String nomeArquivo;
    private TIPOARQUIVO tipoArquivo;
    private String pastaLocal;
    private String caminhoLocal;
    private String caminhoRemoto;
    private String jSessionID;

    public String getNomePasta() {
        return nomePasta;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public TIPOARQUIVO getTipoArquivo() {
        return tipoArquivo;
    }

    public String getPastaLocal() {
        return pastaLocal;
    }

    public String getCaminhoLocal() {
        return caminhoLocal;
    }

    public String getCaminhoRemoto() {
        return caminhoRemoto;
    }

    @Override
    public String toString() {
        return caminhoLocal;
    }

}
