package com.super_bits.modulosSB.webPaginas.push;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * CarameloPush — Endpoint WebSocket Server Side
 *
 * Suporta múltiplas abas por usuário via Set<Session>. Identificador do
 * usuário: email (mesmo usado no cliente JS).
 *
 * Compatível com: - Jetty 9.4.7 (JSR-356 nativo) - Java 8 - Weld 2.4.8 (sem
 * dependência CDI aqui — classe pura JSR-356)
 */
@ServerEndpoint("/carameloPush/{usuario}")
public class PushEndpointNativo {

    private static final Logger LOG = Logger.getLogger(PushEndpointNativo.class.getName());

    /**
     * Map de sessões ativas por usuário (email → Set de sessões/abas abertas).
     * ConcurrentHashMap garante thread-safety no acesso ao Map.
     * Collections.synchronizedSet garante thread-safety no acesso ao Set
     * interno.
     */
    private static final Map<String, Set<Session>> sessoes = new ConcurrentHashMap<>();

    // ─── Ciclo de vida da conexão ─────────────────────────────────────────────
    @OnOpen
    public void onOpen(Session session, @PathParam("usuario") String usuario) {
        String emailDecodificado = _decodificarEmail(usuario);

        sessoes.computeIfAbsent(emailDecodificado, k
                -> Collections.synchronizedSet(new HashSet<>())
        ).add(session);

        _log(emailDecodificado, "Aba conectada. Total abas: "
                + sessoes.get(emailDecodificado).size());
    }

    @OnClose
    public void onClose(Session session, @PathParam("usuario") String usuario) {
        _removerSessao(_decodificarEmail(usuario), session);
    }

    @OnError
    public void onError(Session session, @PathParam("usuario") String usuario,
            Throwable erro) {
        _removerSessao(_decodificarEmail(usuario), session);
    }

    // ─── Envio de mensagem ────────────────────────────────────────────────────
    /**
     * Envia uma mensagem JSON para todas as abas abertas do usuário.
     *
     * @param usuario Email do usuário destinatário
     * @param jsonMensagem Mensagem serializada em JSON
     * @return true se entregou para pelo menos uma aba, false se usuário
     * offline
     */
    public static boolean enviar(String usuario, String jsonMensagem) {
        Set<Session> abas = sessoes.get(usuario);

        if (abas == null || abas.isEmpty()) {
            LOG.info("[CarameloPush] Usuário offline ou sem abas abertas: " + usuario);
            return false;
        }

        boolean entregou = false;

        // Itera em cópia para evitar ConcurrentModificationException
        Set<Session> copia = new HashSet<>(abas);

        for (Session session : copia) {
            if (session.isOpen()) {
                try {
                    session.getBasicRemote().sendText(jsonMensagem);
                    entregou = true;
                    LOG.fine("[CarameloPush] Mensagem entregue para aba de: " + usuario);
                } catch (IOException e) {
                    LOG.log(Level.WARNING, "[CarameloPush] Falha ao enviar para aba de: " + usuario, e);
                    _removerSessao(usuario, session);
                }
            } else {
                // Sessão fechada que não foi limpa — remove agora
                _removerSessao(usuario, session);
            }
        }

        return entregou;
    }

    /**
     * Retorna o número de abas abertas de um usuário. Útil para diagnóstico e
     * logs.
     *
     * @param usuario Email do usuário
     * @return quantidade de abas abertas, 0 se offline
     */
    public static int contarAbas(String usuario) {
        Set<Session> abas = sessoes.get(usuario);
        return abas != null ? abas.size() : 0;
    }

    /**
     * Retorna true se o usuário tem pelo menos uma aba conectada.
     *
     * @param usuario Email do usuário
     * @return true se online
     */
    public static boolean isOnline(String usuario) {
        return contarAbas(usuario) > 0;
    }

    // ─── Utilitários internos ─────────────────────────────────────────────────
    private static void _removerSessao(String usuario, Session session) {
        Set<Session> abas = sessoes.get(usuario);
        if (abas != null) {
            abas.remove(session);
            if (abas.isEmpty()) {
                sessoes.remove(usuario);
                LOG.info("[CarameloPush] Usuário saiu completamente: " + usuario);
            }
        }
    }

    private static void _log(String usuario, String msg) {
        LOG.info("[CarameloPush][" + usuario + "] " + msg);
    }

    private static String _decodificarEmail(String usuario) {
        try {
            return URLDecoder.decode(usuario, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            LOG.warning("[CarameloPush] Falha ao decodificar email: " + usuario);
            return usuario; // retorna original se falhar
        }
    }
}
