/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.controller.servlets.pwa;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import javax.servlet.ServletContext;

public final class ConstrutorManifest {

    private static volatile ConstrutorManifest instancia;

    private final String json;
    private final String etag;

    private ConstrutorManifest(ServletContext ctx) {
        String template = lerTemplate(ctx, "/resources/pwa/manifest.template");
        String contextPath = ctx.getContextPath();               // "" na raiz, "/crm" com context path
        String version = String.valueOf(System.currentTimeMillis()); // = momento do deploy (ver nota)

        this.json = template
                .replace("${contextPath}", contextPath)
                .replace("${version}", version);

        this.etag = "\"" + Integer.toHexString(this.json.hashCode()) + "\"";
    }

    public static ConstrutorManifest getInstancia(ServletContext ctx) {
        ConstrutorManifest ref = instancia;
        if (ref == null) {
            synchronized (ConstrutorManifest.class) {
                ref = instancia;
                if (ref == null) {
                    instancia = ref = new ConstrutorManifest(ctx);
                }
            }
        }
        return ref;
    }

    /**
     * Descarta o cache em memória — útil se você trocar tema por tela admin em
     * runtime.
     */
    public static void invalidar() {
        instancia = null;
    }

    private static String lerTemplate(ServletContext ctx, String caminho) {
        try (InputStream in = ctx.getResourceAsStream(caminho)) {
            if (in == null) {
                throw new IllegalStateException("Template não encontrado: " + caminho);
            }
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] buffer = new byte[4096];
            int n;
            while ((n = in.read(buffer)) != -1) {
                bos.write(buffer, 0, n);
            }
            return new String(bos.toByteArray(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new IllegalStateException("Falha ao ler " + caminho, e);
        }
    }

    public String getJson() {
        return json;
    }

    public String getEtag() {
        return etag;
    }
}
