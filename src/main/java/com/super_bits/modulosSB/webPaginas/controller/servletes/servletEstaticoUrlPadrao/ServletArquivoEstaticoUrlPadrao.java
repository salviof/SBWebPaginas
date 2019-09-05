/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.controller.servletes.servletEstaticoUrlPadrao;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreBytes;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringNomeArquivosEDiretorios;
import com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo.FabTipoArquivoConhecido;
import com.super_bits.modulosSB.webPaginas.ConfigGeral.SBWebPaginas;
import com.super_bits.modulosSB.webPaginas.controller.servletes.ServletArquivosSBWPGenerico;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWPServletTools;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

/**
 *
 * @author SalvioF
 */
public class ServletArquivoEstaticoUrlPadrao extends ServletArquivosSBWPGenerico implements Serializable {

    @Override
    protected void doGet(HttpServletRequest requisicao, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String caminhoRecurso = UtilSBWPServletTools.geCaminhoRecursoDoUrl(requisicao);
            caminhoRecurso = caminhoRecurso.replace("libUrlPadrao/", "");
            resp.addHeader("Cache-Control", "max-age=2592000");
            String caminhoLocal = requisicao.getServletContext().getRealPath("/") + "/resources/SBComp/recursos/urlPadrao/" + caminhoRecurso;
            if (!new File(caminhoLocal).exists()) {
                String caminhoRelativoStreamResource = "/META-INF/resources/SBComp/recursos/urlPadrao" + caminhoRecurso;
                caminhoRelativoStreamResource = caminhoRelativoStreamResource.replace("//", "/");
                ClassLoader classLoader = SBWebPaginas.class.getClassLoader();
                InputStream is = classLoader.getResourceAsStream(caminhoRelativoStreamResource);
                if (is == null) {
                    throw new UnsupportedOperationException("Erro obtendo stream por classloader" + "/META-INF/resources/SBComp/recursos/urlPadrao/" + caminhoRecurso);
                }
                definirHeaderPorNomeDeArquivo(caminhoRecurso, resp);
                realizarStreamDoArquivo(resp, UtilSBCoreBytes.gerarBytePorInputstream(is));
            } else {
                String nomeArquivo = UtilSBCoreStringNomeArquivosEDiretorios.getNomeArquivo(caminhoRecurso);
                abrirArquivo(caminhoLocal, nomeArquivo, requisicao, resp, FabTipoArquivoConhecido.getTipoArquivoByNomeArquivo(nomeArquivo));
            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro obtendo recurso estático", t);
            try {
                resp.sendError(404, "Arquivo estático não contrado");
            } catch (Throwable tt) {

            }

        }

    }

}
