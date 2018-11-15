/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.util;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.GrupoCampos;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.ItfCampoExibicaoFormulario;
import com.super_bits.modulosSB.webPaginas.ConfiguradorSBCoreWebPaginaTestes;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.declarados.util.PgUtil;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import testesFW.TesteJunit;

/**
 *
 * @author <a href="mailto:salviof@gmail.com">Salvio Furbino</a>
 */
public class PgUtilTest extends TesteJunit {

    public PgUtilTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @Test
    public void testeGerarGrupo() {
        PgUtil paginaUtil = new PgUtil();
        GrupoCampos pGrupoUsuario = paginaUtil.gerarGrupoCamposEntidade("ContatoProspecto",
                "Teste", "contatosProspecto[].nome", "contatosProspecto[].email");

        for (ItfCampoExibicaoFormulario campo : pGrupoUsuario.getCampos()) {
            System.out.println(campo.getCaminhoCampo());
        }

    }

    /**
     * Test of getCorHeader method, of class PgUtil.
     */
    public void testAlterarCor() {
        PgUtil requestPgUtil = new PgUtil();
        String novoValor = requestPgUtil.makeCaminhoComponenteByClientID("111111111111:22222222222222:3333333333333:444444444444444444:5555555555555555");
        SBCore.enviarAvisoAoUsuario("Novo Valor" + novoValor);

        Assert.assertEquals("Nao retornou o esperadoo", "111111111111:22222222222222:3333333333333:444444444444444444", novoValor);
        System.out.println(novoValor);

    }

    @Override
    protected void configAmbienteDesevolvimento() {
        SBCore.configurar(new ConfiguradorSBCoreWebPaginaTestes(), SBCore.ESTADO_APP.DESENVOLVIMENTO);
    }

}
