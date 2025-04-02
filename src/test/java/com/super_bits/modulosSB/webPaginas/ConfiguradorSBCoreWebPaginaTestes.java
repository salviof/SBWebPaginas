/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas;

import com.super_bits.modulos.SBAcessosModel.fabricas.FabAcaoProjetoSB;
import com.super_bits.modulosSB.SBCore.ConfigGeral.ItfConfiguracaoCoreCustomizavel;
import com.super_bits.modulosSB.webPaginas.ConfigGeral.ConfiguradorCoreDeProjetoWebWarAbstrato;
import com.super_bits.modulos.SBAcessosModel.view.FabAcaoPaginasDoSistema;

/**
 *
 * @author desenvolvedor
 */
public class ConfiguradorSBCoreWebPaginaTestes extends ConfiguradorCoreDeProjetoWebWarAbstrato {

    public ConfiguradorSBCoreWebPaginaTestes() {
        super(true);
        setIgnorarConfiguracaoAcoesDoSistema(true);
        setIgnorarConfiguracaoPermissoes(true);

    }

    @Override
    public void defineFabricasDeACao(ItfConfiguracaoCoreCustomizavel pConfig) {
        pConfig.setFabricaDeAcoes(new Class[]{FabAcaoProjetoSB.class, FabAcaoPaginasDoSistema.class});
    }

}
