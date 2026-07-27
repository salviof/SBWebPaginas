/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.controller.servlets.pwa;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "manifestServlet", urlPatterns = {"/manifest.json"})
public class ManifestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ConstrutorManifest manifest = ConstrutorManifest.getInstancia(getServletContext());
        String etag = manifest.getEtag();

        // Revalidação barata: se o navegador já tem essa versão, 304 e nem escreve corpo.
        if (etag.equals(req.getHeader("If-None-Match"))) {
            resp.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
            return;
        }

        resp.setContentType("application/manifest+json");
        resp.setCharacterEncoding("UTF-8");
        resp.setHeader("ETag", etag);
        resp.setHeader("Cache-Control", "public, max-age=86400");

        byte[] corpo = manifest.getJson().getBytes(StandardCharsets.UTF_8);
        resp.setContentLength(corpo.length);
        resp.getOutputStream().write(corpo);
    }
}
