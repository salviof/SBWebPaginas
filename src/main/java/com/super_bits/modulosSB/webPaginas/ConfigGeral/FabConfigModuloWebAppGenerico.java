/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.ConfigGeral;

import com.super_bits.modulosSB.SBCore.ConfigGeral.arquivosConfiguracao.ItfConfigModulo;
import com.super_bits.modulosSB.SBCore.ConfigGeral.arquivosConfiguracao.ItfFabConfigModulo;

/**
 *
 * @author salviofurbino
 * @since 06/05/2019
 * @version 1.0
 */
public enum FabConfigModuloWebAppGenerico implements ItfFabConfigModulo {

    URL_DOMINIO_APLICACAO,
    NOME_APLICACAO,
    PREFIXO_PATHURL,
    DOMINIO_MASTER,
    @Deprecated
    ROCKET_CHAT_SERVER,
    @Deprecated
    MAUTIC_SERVER,
    @Deprecated
    OAUTH_SERVER,
    @Deprecated
    CONTROLLER_SERVER;

    @Override
    public String getValorPadrao() {
        switch (this) {
            case URL_DOMINIO_APLICACAO:
                return "http://localhost:8080";

            case NOME_APLICACAO:
                return "Aplicação não nomeada, criada pelos incríveis cidadãos do coletivo Java";

            case DOMINIO_MASTER:
                return "localhost";

            case ROCKET_CHAT_SERVER:
                return "https://chat.coletivojava.com.br";

            case OAUTH_SERVER:
                return "";
            case CONTROLLER_SERVER:
                return "";
            case PREFIXO_PATHURL:
                return "";
            case MAUTIC_SERVER:
                return "";

            default:
                throw new AssertionError(this.name());

        }
    }

}
