/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.util;

import com.super_bits.modulos.SBAcessosModel.fabricas.FabAcaoProjetoSB;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.icones.FabIconeFontAwesome;
import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.componentes.FabCompVisualEndereco;
import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.componentes.FabCompVisualFormularioDeAcao;
import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.componentes.FabCompVisualGrupoCampo;
import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.componentes.FabCompVisualGruposCampo;
import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.componentes.FabCompVisualInputs;
import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.componentes.FabCompVisualItem;
import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.componentes.FabCompVisualItens;
import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.componentes.FabCompVisualSeletorItem;
import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.componentes.FabCompVisualSeletorItens;
import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.componentes.FabCompVisualSubItens;
import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.componentes.FabcompVisualEnums;
import com.super_bits.modulosSB.webPaginas.ConfiguradorSBCoreWebPaginaTestes;
import org.junit.Test;
import testesFW.TesteJunit;

/**
 *
 * @author desenvolvedor
 */
public class UtilSBDevelGeradorCodigoWebTest extends TesteJunit {

    public UtilSBDevelGeradorCodigoWebTest() {
    }

    /**
     * Test of gerarCodigoGetAcoesGestao method, of class
     * UtilSBDevelGeradorCodigoWeb.
     */
    public void testGerarCodigoGetAcoesGestao() {
        try {
            System.out.println("TESTE:");
            ItfAcaoDoSistema acao = FabAcaoProjetoSB.PROJETO_MB_GERENCIAR.getRegistro();
            System.out.println(acao.getComoGestaoEntidade().getAcoesVinculadas().toString());
            UtilSBDevelGeradorCodigoWeb.gerarGetAppScopeAcoesDeGestaoDoProjeto(FabAcaoProjetoSB.PROJETO_MB_GERENCIAR.getRegistro().getComoGestaoEntidade());
            System.out.println(SBCore.getCaminhoDesenvolvimento());
        } catch (Throwable t) {
            lancarErroJUnit(t);
        }
    }

    @Override
    protected void configAmbienteDesevolvimento() {
        SBCore.configurar(new ConfiguradorSBCoreWebPaginaTestes(), SBCore.ESTADO_APP.DESENVOLVIMENTO);
    }

    @Test
    public void testarGerarCOmponente() {
        try {
            System.out.println("TESTE:");

            UtilSBDevelGeradorCodigoWeb.criarClassesDeComponentes(FabCompVisualSeletorItens.class, true);
            UtilSBDevelGeradorCodigoWeb.criarClassesDeComponentes(FabCompVisualSeletorItem.class, true);
            UtilSBDevelGeradorCodigoWeb.criarClassesDeComponentes(FabCompVisualInputs.class, true);
            UtilSBDevelGeradorCodigoWeb.criarClassesDeComponentes(FabCompVisualGrupoCampo.class, true);
            UtilSBDevelGeradorCodigoWeb.criarClassesDeComponentes(FabCompVisualGruposCampo.class, true);
            UtilSBDevelGeradorCodigoWeb.criarClassesDeComponentes(FabCompVisualFormularioDeAcao.class, true);
            UtilSBDevelGeradorCodigoWeb.criarClassesDeComponentes(FabcompVisualEnums.class, true);
            UtilSBDevelGeradorCodigoWeb.criarClassesDeComponentes(FabCompVisualEndereco.class, true);
            UtilSBDevelGeradorCodigoWeb.criarClassesDeComponentes(FabCompVisualItens.class, true);
            UtilSBDevelGeradorCodigoWeb.criarClassesDeComponentes(FabCompVisualItem.class, true);
            UtilSBDevelGeradorCodigoWeb.criarClassesDeComponentes(FabCompVisualSubItens.class, true);
            UtilSBDevelGeradorCodigoWeb.criarClassesDeIcones(FabIconeFontAwesome.class, true);

        } catch (Throwable t) {
            lancarErroJUnit(t);
        }
    }

}
