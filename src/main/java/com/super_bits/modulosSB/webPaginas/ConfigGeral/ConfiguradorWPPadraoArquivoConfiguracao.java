/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.ConfigGeral;

import com.super_bits.modulosSB.SBCore.ConfigGeral.arquivosConfiguracao.ConfigModulo;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringValidador;

import java.io.IOException;

/**
 *
 * @author salviofurbino
 * @since 06/05/2019
 * @version 1.0
 */
public abstract class ConfiguradorWPPadraoArquivoConfiguracao implements ItfConfigWebPagina {

    private final ConfigModulo configModulo;
    private final String siteHost;
    private final String urlBase;

    public ConfiguradorWPPadraoArquivoConfiguracao() throws IOException {
        this.configModulo = new ConfigModulo(FabConfigModuloWebAppGenerico.class);
        String pParametroDominioAplicacao = configModulo.getPropriedade(FabConfigModuloWebAppGenerico.URL_DOMINIO_APLICACAO);

        if (!pParametroDominioAplicacao.startsWith("http")) {
            siteHost = "https://" + pParametroDominioAplicacao;
        } else {
            siteHost = pParametroDominioAplicacao;
        }
        urlBase = siteHost;
    }

    @Override
    public String SITE_HOST() {

        return siteHost;
    }

    @Override
    public String pastaImagens() {
        return "/img";
    }

    @Override
    public String nomePacoteProjeto() {
        return configModulo.getPropriedade(FabConfigModuloWebAppGenerico.PREFIXO_PATHURL);
    }

    @Override
    public String TituloAppWeb() {
        return configModulo.getPropriedade(FabConfigModuloWebAppGenerico.NOME_APLICACAO);
    }

    @Override
    public String URLBASE() {
        return urlBase;
    }

    @Override
    public boolean parametroDeAplicacaoEmSubDominio() {
        return false;
    }

}
