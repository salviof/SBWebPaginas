/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.controller.servletes;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreBytes;
import com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo.FabTipoArquivoConhecido;
import com.super_bits.modulosSB.webPaginas.ConfigGeral.SBWebPaginas;
import com.super_bits.modulosSB.webPaginas.controller.servletes.servletArquivoDeEntidade.ServletArquivosDeEntidade;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWP_JSFTools;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

/**
 *
 * @author desenvolvedor
 */
public class ServletArquivosSBWPGenerico extends HttpServlet {

    protected void baixarArquivo(String pcaminhoArquivo, String pNomeArquivoDownload,
            HttpServletRequest requisicao, HttpServletResponse resp) {
        resp.setHeader("Content-Disposition", "attachment; filename=\"" + pNomeArquivoDownload + "\"");;
        resp.setContentType("application/force-download");
        resp.setHeader("Content-Transfer-Encoding", "binary");
        realizarStreamDoArquivo(resp, pcaminhoArquivo);
    }

    protected void abrirArquivo(String pCaminhoArquivo, String pNomeArquivoDownload,
            HttpServletRequest requisicao, HttpServletResponse resp, FabTipoArquivoConhecido pArquivoConhecido) {
        resp.setContentType(pArquivoConhecido.getTipoConteudoRespostaHTML());
        switch (pArquivoConhecido) {
            case IMAGEM_WEB:
                break;
            case IMAGE_REPRESENTATIVA_ENTIDADE_PEQUENO:
                if (!new File(pCaminhoArquivo).exists()) {
                    pCaminhoArquivo = SBWebPaginas.getCaminhoRealJavaWebAppContexto() + "/resources/img/SBPequeno.jpg";
                }
                break;
            case IMAGE_REPRESENTATIVA_ENTIDADE_MEDIO:
                if (!new File(pCaminhoArquivo).exists()) {
                    pCaminhoArquivo = SBWebPaginas.getCaminhoRealJavaWebAppContexto() + "/resources/img/SBMedio.jpg";
                }
                break;
            case IMAGE_REPRESENTATIVA_ENTIDADE_GRANDE:
                if (!new File(pCaminhoArquivo).exists()) {
                    pCaminhoArquivo = SBWebPaginas.getCaminhoRealJavaWebAppContexto() + "/resources/img/SBGrande.jpg";
                }
                break;
            case VIDEO:
                break;
            case DOCUMENTO_WORD_XDOC2007:
                break;
            case FOLHA_DE_ESTILO_CSS:
                break;
            case DOCUMENTO_PDF:

                break;
            case ARQUIVO_TEXTO_SIMPLES:
                break;
            case JAVASCRIPT:
                break;
            default:
                throw new AssertionError(pArquivoConhecido.name());

        }
        realizarStreamDoArquivo(resp, pCaminhoArquivo);
    }

    protected void realizarStreamDoArquivo(HttpServletResponse resp, String pCaminhoLocalArquivo) {
        try {
            byte[] dadosArquivo = UtilSBCoreBytes.gerarBytesPorArquivo(new File(pCaminhoLocalArquivo));
            resp.setContentLength(dadosArquivo.length);
            try (ServletOutputStream ouputStream = resp.getOutputStream()) {
                ouputStream.write(dadosArquivo, 0, dadosArquivo.length);
                ouputStream.flush();
            }
        } catch (Exception t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro Preparando arquivo para download", t);
        }
        //   try (FileInputStream in = new FileInputStream(pCaminhoLocalArquivo)) {
        //       OutputStream out = resp.getOutputStream();
        //       byte[] buffer = new byte[4096];
        //       int length;
        //       while ((length = in.read(buffer)) > 0) {
        ///           out.write(buffer, 0, length);
        ///      }
        //     out.flush();
        ///  } catch (Throwable t) {
        ///      SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro Preparando arquivo para download", t);
        /// }
    }

    protected void despachaParametrosNaoDefinidos(HttpServletRequest requisicao, HttpServletResponse resp, List<String> slugsURL) {

        try {

            RequestDispatcher wp = requisicao.getRequestDispatcher(UtilSBWP_JSFTools.FORMULARIO_PARAMETRO_URL_INVALIDO);
            wp.forward(requisicao, resp);
        } catch (ServletException ex) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "", ex);
        } catch (IOException ex) {
            Logger.getLogger(ServletArquivosDeEntidade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
