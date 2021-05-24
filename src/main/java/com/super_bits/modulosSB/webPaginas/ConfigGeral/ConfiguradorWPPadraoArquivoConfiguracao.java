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

    public ConfiguradorWPPadraoArquivoConfiguracao() throws IOException {
        this.configModulo = new ConfigModulo(FabConfigModuloWebAppGenerico.class);
    }

    @Override
    public String SITE_HOST() {
        return configModulo.getPropriedade(FabConfigModuloWebAppGenerico.URL_DOMINIO_APLICACAO);
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

    private static String urlbase;

    @Override
    public String URLBASE() {
        if (urlbase == null) {
            if (UtilSBCoreStringValidador.isNuloOuEmbranco(nomePacoteProjeto())) {
                urlbase = SITE_HOST();
            } else {
                urlbase = SITE_HOST() + "/" + nomePacoteProjeto();
            }
        }
        return urlbase;
    }

    @Override
    public boolean parametroDeAplicacaoEmSubDominio() {
        return false;
    }

}
