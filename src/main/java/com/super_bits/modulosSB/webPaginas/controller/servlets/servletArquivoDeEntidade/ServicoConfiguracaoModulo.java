/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.controller.servlets.servletArquivoDeEntidade;

import com.super_bits.modulosSB.SBCore.ConfigGeral.CarameloCode;
import com.super_bits.modulosSB.SBCore.ConfigGeral.arquivosConfiguracao.ConfigModuloDetalhes;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author salvio
 */
@RequestScoped
@Named
public class ServicoConfiguracaoModulo {

    private List<ConfigModuloDetalhes> configurcaoes;

    @PostConstruct
    public void inicio() {
        configuracoes = CarameloCode.getServicoConfigModulo().getDetalhesModulosAtivos();
    }

    private List<ConfigModuloDetalhes> configuracoes;

    public List<ConfigModuloDetalhes> getConfiguracoes() {
        return configuracoes;
    }

}
