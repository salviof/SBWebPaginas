/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.centralComunicacao;

import java.io.IOException;
import java.util.Arrays;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author salvio
 */
@WebServlet("/CRCForms/infoPagina.json")
public class ServletFormulario
        extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String paginaInstanciaID = req.getParameter("paginaInstanciaID");
        String tipodado = req.getParameter("tipodado");
        if (tipodado == null) {

        }
        FabTipoDadoInfoPagina tipoDado = Arrays.stream(FabTipoDadoInfoPagina.values())
                .filter(e -> e.name().equals(tipodado))
                .findFirst()
                .orElse(null);

        if (tipoDado == null) {
            throw new ServletException("Tipo dado " + tipodado + " não reconhecido");
        }

        if (paginaInstanciaID != null && !paginaInstanciaID.isEmpty()) {
            req.getRequestDispatcher(tipoDado.getXhtmlJson())
                    .forward(req, resp); // ← caminho absoluto
            return;
        }
    }

}
