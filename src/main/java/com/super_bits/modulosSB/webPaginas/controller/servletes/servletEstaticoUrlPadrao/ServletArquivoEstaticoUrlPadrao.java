/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.controller.servletes.servletEstaticoUrlPadrao;

import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringNomeArquivosEDiretorios;
import com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo.FabTipoArquivoConhecido;
import com.super_bits.modulosSB.webPaginas.controller.servletes.ServletArquivosSBWPGenerico;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWPServletTools;
import java.io.IOException;
import java.io.Serializable;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author SalvioF
 */
public class ServletArquivoEstaticoUrlPadrao extends ServletArquivosSBWPGenerico implements Serializable {

    @Override
    protected void doGet(HttpServletRequest requisicao, HttpServletResponse resp) throws ServletException, IOException {

        String caminhoRecurso = UtilSBWPServletTools.geCaminhoRecursoDoUrl(requisicao);

        //scontext.getRealPath("/");
        caminhoRecurso = caminhoRecurso.substring(caminhoRecurso.indexOf("libUrlPadrao"));
        //req scontext.getRealPath("/")

        String caminhoLocal = requisicao.getServletContext().getRealPath("/") + "/resources/SBComp/recursos/urlPadrao/" + caminhoRecurso;
        System.out.println("Abrindo Recuroso Local:" + caminhoLocal);
        String nomeArquivo = UtilSBCoreStringNomeArquivosEDiretorios.getNomeArquivo(caminhoRecurso);
        System.out.println(caminhoLocal);

        abrirArquivo(caminhoLocal, nomeArquivo, requisicao, resp, FabTipoArquivoConhecido.getTipoArquivoByNomeArquivo(nomeArquivo));

    }

}
