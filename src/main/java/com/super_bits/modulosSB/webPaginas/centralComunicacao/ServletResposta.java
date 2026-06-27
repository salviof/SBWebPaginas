package com.super_bits.modulosSB.webPaginas.centralComunicacao;

import com.super_bits.modulosSB.SBCore.modulos.erp.ErroJsonInterpredador;
import com.super_bits.modulosSB.SBCore.modulos.erpCore.ErpCarameloCore;
import com.super_bits.modulosSB.SBCore.modulos.objetos.dialogo.resposta.RespostaComunicacao;
import java.io.IOException;
import java.rmi.ServerException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author salvio
 */
@WebServlet("/notificacoes/dialogo/resposta")
public class ServletResposta extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        String codigoDialogo = req.getParameter("codigoSelo");
        String respostaJson = req.getParameter("resposta");
        String paginaInstanciaID = req.getParameter("paginaInstanciaID"); // ← getParameter, não getAttribute

        try {
            RespostaComunicacao resposta = ErpCarameloCore.CORE_PADRAO
                    .getDTO(respostaJson, RespostaComunicacao.class);
            req.setAttribute("respostaObj", resposta);
            if (resposta == null) {

                throw new ServletException("Resposta não detectada");
            }

            // se tem paginaInstanciaID → despacha para o xhtml renderizar o recibo
            if (paginaInstanciaID != null && !paginaInstanciaID.isEmpty()) {
                req.getRequestDispatcher("/resources/SBComp/modal/respostaTransient.xhtml")
                        .forward(req, resp); // ← caminho absoluto
                return;
            }

            // sem paginaInstanciaID → resposta JSON simples
            escreverJson(resp, "{\n"
                    + "    \"formulario\": \"não informado\",\n"
                    + "    \"respostaRegistrada\": \"" + resposta.getTipoResposta().getNome() + "\"\n"
                    + "}");

        } catch (ErroJsonInterpredador | ServletException | IOException ex) {
            Logger.getLogger(ServletResposta.class.getName()).log(Level.SEVERE, null, ex);
            try {
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                escreverJson(resp, "{\"erro\": \"Erro interno ao processar resposta\"}");
            } catch (IOException ignore) {
            }
        }
    }

    private void escreverJson(HttpServletResponse resp, String json) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getOutputStream().print(json);
        resp.flushBuffer();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        throw new ServerException("metodo não suportado");
    }

}
