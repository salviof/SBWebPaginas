/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.controller.servlets.pwa;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author salvio
 */
@WebServlet(name = "swServlet", urlPatterns = {"/sw.js"})
public class ServiceWorkerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try (InputStream in = getServletContext().getResourceAsStream("/resources/pwa/sw.js")) {
            if (in == null) {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
                return;
            }
            resp.setContentType("application/javascript");
            resp.setCharacterEncoding("UTF-8");
            resp.setHeader("Service-Worker-Allowed", "/"); // permite escopo raiz mesmo sob context path
            resp.setHeader("Cache-Control", "no-cache");   // SW sempre revalidado -> updates propagam
            OutputStream out = resp.getOutputStream();
            byte[] buffer = new byte[4096];
            int n;
            while ((n = in.read(buffer)) != -1) {
                out.write(buffer, 0, n);
            }
        }
    }
}
