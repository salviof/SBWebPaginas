/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFManagedBeans.declarados.util;

import java.util.Map;

/**
 *
 * @author salvio
 */
public interface ItfServicoPush {

    /**
     * Envia mensagem push para o usuário.
     *
     * @param emailDestinatario Email do usuário destinatário
     * @param mensagem Dados da mensagem (chave-valor)
     * @return true se entregou para pelo menos uma aba
     */
    boolean enviar(String emailDestinatario, Map<String, Object> mensagem);

    /**
     * Verifica se o usuário está online.
     *
     * @param emailUsuario Email do usuário
     * @return true se tem pelo menos uma aba aberta
     */
    boolean isOnline(String emailUsuario);

    /**
     * Retorna o número de abas abertas do usuário.
     *
     * @param emailUsuario Email do usuário
     * @return quantidade de abas, 0 se offline
     */
    int contarAbas(String emailUsuario);

}
