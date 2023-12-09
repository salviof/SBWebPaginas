/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.controller.servlets;

import java.io.Serializable;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import java.io.FileNotFoundException;
import java.io.IOException;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWP_JSFTools;

/**
 *
 * @author salvio
 */
public class ServletNotion extends HttpServlet implements Serializable {

    public final static String PASTA_NOTION_WIDGETS_PADRAO = "/resources/notion/";

    public String getArquivoXHTMLByUrl(String pURI) {
        int inicio = pURI.lastIndexOf("/");
        int fim = pURI.lastIndexOf(".notion");
        String build = PASTA_NOTION_WIDGETS_PADRAO + pURI.substring(inicio + 1, fim) + ".xhtml";
        return build;

    }

    @Override
    protected void doGet(HttpServletRequest requisicao, HttpServletResponse resp) throws ServletException, IOException {
        requisicao.getContextPath();
        resp.addHeader("Cache-Control", "max-age=1000");
        boolean autenticado = false;
        if (SBCore.isEmModoProducao()) {
            if (requisicao.getHeader("referer") != null) {
                if (requisicao.getHeader("referer").equals("https://www.notion.so/")) {
                    //if (requisicao.getHeader("sec-fetch-dest") != null) {
                    //     if (requisicao.getHeader("sec-fetch-dest").equals("iframe")) {
                    autenticado = true;
                    //      }
                    //   }

                }

            }
            if (!autenticado) {
                RequestDispatcher despachadorDeRespostaParaRequisicao = requisicao.getRequestDispatcher(UtilSBWP_JSFTools.FORMULARIO_ACESSO_NEGADO);
                despachadorDeRespostaParaRequisicao.forward(requisicao, resp);
                return;
            }
        } else {
            autenticado = true;
        }
        String patch = requisicao.getRequestURI();
        String nomeRecurso = getArquivoXHTMLByUrl(patch);
        RequestDispatcher despachadorDeRespostaParaRequisicao = requisicao.getRequestDispatcher(nomeRecurso);
        despachadorDeRespostaParaRequisicao.forward(requisicao, resp);

    }

    public void loadPDF(HttpServletRequest requisicao, HttpServletResponse resp) {
        resp.setHeader("Content-type", "application/pdf");
        Document document = new Document();

        try {

            PdfWriter.getInstance(document, resp.getOutputStream());

            //open
            document.open();

            Paragraph p = new Paragraph();
            p.add("This is my paragraph 1");
            p.setAlignment(Element.ALIGN_CENTER);

            document.add(p);

            Paragraph p2 = new Paragraph();
            p2.add("This is my paragraph 2"); //no alignment

            document.add(p2);

            Font f = new Font();
            f.setStyle(Font.BOLD);
            f.setSize(8);

            document.add(new Paragraph("This is my paragraph 3", f));

            //close
            document.close();

            System.out.println("Done");

        } catch (FileNotFoundException | DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
