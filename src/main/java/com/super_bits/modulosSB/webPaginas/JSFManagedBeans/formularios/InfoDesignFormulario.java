/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios;

import com.google.common.collect.Lists;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.view.UtilSBCoreLayoutComponenteEmTelas;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ItfFabricaAcoes;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.ItfCampoExibicaoFormulario;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.ItfGrupoCampos;
import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.componentes.FabCompVisualBotaoAcao;
import com.super_bits.modulosSB.SBCore.modulos.view.telas.ColunaTela;
import com.super_bits.modulosSB.SBCore.modulos.view.telas.ItfTelaUsuario;
import com.super_bits.modulosSB.SBCore.modulos.view.telas.LayoutComponentesEmTela;
import com.super_bits.modulosSB.SBCore.modulos.view.telas.LayoutComponentesEmTelaComGrupoDeAcoes;
import com.super_bits.modulosSB.webPaginas.JSFBeans.SBBeanModel.modelosPagina.ModeloPagina;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.interfaces.ItfB_Pagina;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

/**
 *
 * @author desenvolvedor
 */
public class InfoDesignFormulario {

    private ModeloPagina modeloPrincipal;
    private Map<ItfFabricaAcoes, ModeloPagina> modelosSecundarios;
    private Map<ItfFabricaAcoes, List<String>> areasDeAtualizacao;
    private Map<String, LayoutComponentesEmTela> componentesEmExibixao;
    private final ItfB_Pagina pagina;
    private Map<String, LayoutComponentesEmTela> mapaLayoutPorIdentificacao;
    private final ItfTelaUsuario telaUsuario;

    public InfoDesignFormulario(ItfB_Pagina pPagina, ItfTelaUsuario pTipoTela) {
        pagina = pPagina;
        telaUsuario = pTipoTela;
    }

    public List<LayoutComponentesEmTela> getLayoutsGerados() {
        if (mapaLayoutPorIdentificacao == null) {
            return new ArrayList<>();
        }
        return Lists.newArrayList(mapaLayoutPorIdentificacao.values());
    }

    public ModeloPagina getModeloSecundario(ItfFabricaAcoes pAcao) {
        return modelosSecundarios.get(pAcao);
    }

    public void alterarModelo() {

    }

    public synchronized LayoutComponentesEmTelaComGrupoDeAcoes gerarLayout(ItfGrupoCampos pGrupoCampo, List<ItfAcaoDoSistema> pAcoes) {
        String identificacao = UtilSBCoreLayoutComponenteEmTelas.gerarIDLayout(pGrupoCampo);
        if (!isExisteLayout(identificacao)) {
            LayoutComponentesEmTela layout = gerarNovoLayout((List) pGrupoCampo.getCampos(), pAcoes, identificacao);
            return (LayoutComponentesEmTelaComGrupoDeAcoes) layout;
        } else {
            return (LayoutComponentesEmTelaComGrupoDeAcoes) mapaLayoutPorIdentificacao.get(identificacao);
        }
    }

    public LayoutComponentesEmTela gerarLayoutGrid(ItfGrupoCampos pGrupoCampo) {
        return gerarLayout(pGrupoCampo);
    }

    public synchronized LayoutComponentesEmTela gerarLayout(ItfGrupoCampos pGrupoCampo) {
        try {
            if (pGrupoCampo == null) {
                throw new UnsupportedOperationException("Tentativa de gerar um layout com grupo campo nulo");
            }
            String identificacao = UtilSBCoreLayoutComponenteEmTelas.gerarIDLayout(pGrupoCampo);
            if (!isExisteLayout(identificacao)) {
                LayoutComponentesEmTela layout = gerarNovoLayout((List) pGrupoCampo.getCampos(), new ArrayList(), identificacao);
                return layout;
            } else {
                return mapaLayoutPorIdentificacao.get(identificacao);
            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro gerando layout de componente em tela" + pGrupoCampo, t);
            return null;
        }
    }

    private LayoutComponentesEmTela gerarNovoLayout(
            List<ItfCampoExibicaoFormulario> campos, List<ItfAcaoDoSistema> pAcoes,
            String pIdentificacao) {
        try {

            if (mapaLayoutPorIdentificacao == null) {
                mapaLayoutPorIdentificacao = new HashMap<>();
            }

            if (isExisteLayout(pIdentificacao)) {
                return mapaLayoutPorIdentificacao.get(pIdentificacao);
            } else {

                if (telaUsuario == null) {
                    throw new UnsupportedOperationException("O Tipo da tela não pôde ser recuperado");
                }
                LayoutComponentesEmTelaComGrupoDeAcoes novoLayout = UtilSBCoreLayoutComponenteEmTelas.gerarLayoutColunasComAcao(campos, pAcoes, FabCompVisualBotaoAcao.ICONE, telaUsuario.getTipoTela(), pIdentificacao, true);
                adicionarLayout(pIdentificacao, novoLayout);

            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro criando layout de colunas para componentes id:" + pIdentificacao, t);
        }
        return mapaLayoutPorIdentificacao.get(pIdentificacao);

    }

    private boolean isExisteLayout(String pIdentificacao) {

        if (mapaLayoutPorIdentificacao == null) {
            return false;
        }
        return mapaLayoutPorIdentificacao.get(pIdentificacao) != null;
    }

    public ColunaTela getColunaLayoutByNomeCampoCompletoComGrupo(String pNomeCampo) {
        try {
            if (pNomeCampo == null || pNomeCampo.isEmpty()) {
                if (SBCore.isEmModoDesenvolvimento()) {
                    System.out.println("Tentativa de obter as propriedades da coluna envioando um nome de coluna nulo");
                }
                return null;
            }
            String[] partes = pNomeCampo.split("\\.");
            String identificador = partes[0] + "." + partes[1];
            LayoutComponentesEmTela layoyt = mapaLayoutPorIdentificacao.get(identificador);
            if (layoyt == null) {
                throw new UnsupportedOperationException("O layoyt não foi encontrado" + identificador);
            }
            String nome = pNomeCampo.replace(identificador + ".", "");
            return layoyt.getColunaByNome(nome);

        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro obtendo coluna por nome do campo" + pNomeCampo, t);
            return null;
        }

    }

    public ColunaTela getUltimaColunaDoLayoyt(String pNomeLayout) {

        String[] partes = pNomeLayout.split("\\.");
        String identificador = partes[0];
        LayoutComponentesEmTela layoyt = mapaLayoutPorIdentificacao.get(identificador);
        return layoyt.getUltimaColuna();

    }

    private LayoutComponentesEmTela adicionarLayout(String pIdentificacaoLayout, LayoutComponentesEmTela pLayout) {
        LayoutComponentesEmTela layout = mapaLayoutPorIdentificacao.get(pIdentificacaoLayout);
        if (layout != null) {
            return mapaLayoutPorIdentificacao.get(pIdentificacaoLayout);
        } else {

            mapaLayoutPorIdentificacao.put(pIdentificacaoLayout, pLayout);

            return mapaLayoutPorIdentificacao.get(pIdentificacaoLayout);
        }

    }

}
