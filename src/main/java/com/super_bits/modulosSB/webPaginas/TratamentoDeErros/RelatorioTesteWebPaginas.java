/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.TratamentoDeErros;

import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.MapaAcoesSistema;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreReflexaoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfModuloAcaoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoGerenciarEntidade;
import com.super_bits.modulosSB.SBCore.modulos.Mensagens.FabMensagens;
import com.super_bits.modulosSB.SBCore.modulos.Mensagens.FabTipoAgenteDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.InfoErroSBCoreFW;

import com.super_bits.modulosSB.SBCore.modulos.fabrica.ItfFabricaAcoes;
import com.super_bits.modulosSB.SBCore.modulos.tratamentoErros.ItfInfoErroSB;
import com.super_bits.modulosSB.webPaginas.JSFBeans.util.testes.UtilTestePagina;
import com.super_bits.modulosSB.webPaginas.util.UtilSBDevelGeradorCodigoWeb;
import java.util.ArrayList;
import java.util.List;
import org.coletivojava.fw.api.objetoNativo.mensagem.Mensagem;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import testesFW.RelatorioTesteAbstrato;

/**
 *
 * @author desenvolvedor
 */
public abstract class RelatorioTesteWebPaginas extends RelatorioTesteAbstrato {

    @Override
    public List<ItfInfoErroSB> executarTestesBanco() {
        List<ItfInfoErroSB> erros = new ArrayList<>();
        for (Class entidade : UtilSBPersistencia.getTodasEntidades()) {
            try {
                System.out.println("entidade::::" + entidade.getSimpleName());
                UtilSBCoreReflexaoObjeto.getNomeDoObjetoPorAnotacaoInfoClasse(entidade);
            } catch (Throwable t) {
                InfoErroSBCoreFW errro = new InfoErroSBCoreFW();
                errro.configurar(new Mensagem(FabTipoAgenteDoSistema.DESENVOLVEDOR, FabMensagens.ERRO, t.getMessage()), FabErro.ARQUIVAR_LOG, t);
                erros.add(errro);
            }
        }
        return erros;
    }

    @Override
    public List<ItfInfoErroSB> executarTestesBancoAcoes() {
        List<ItfInfoErroSB> erros = new ArrayList<>();
        erros.addAll(executarTestesAcoes());
        erros.addAll(executarTestesBanco());
        return erros;
    }

    public List<ItfInfoErroSB> gerarMangedBeansPgs(ItfFabricaAcoes pAcao) {
        //    ItfAcaoDoSistema acao = UtilSBDevelGeradorCodigoWeb.gerarCodigoGetAcoesGestao(pAcao);
        //AcaoGestaoEntidade acaogestao =
        throw new UnsupportedOperationException("Não foi implementado");
    }

    public List<ItfInfoErroSB> gerarMangedBeansPgs() {

        throw new UnsupportedOperationException("Não foi implementado");

    }

    public List<ItfInfoErroSB> gerarMangedBeansAcessoAcoes() {

        List<ItfInfoErroSB> erros = new ArrayList<>();

        for (ItfAcaoDoSistema acao : MapaAcoesSistema.getListaTodasAcoes()) {

            try {
                if (acao.isUmaAcaoGestaoDominio()) {

                    UtilSBDevelGeradorCodigoWeb.gerarGetAppScopeAcoesDeGestaoDoProjeto(acao.getComoGestaoEntidade());

                }
            } catch (Throwable t) {
                InfoErroSBCoreFW errro = new InfoErroSBCoreFW();
                errro.configurar(new Mensagem(FabTipoAgenteDoSistema.DESENVOLVEDOR, FabMensagens.ERRO, t.getMessage()), FabErro.ARQUIVAR_LOG, t);
                erros.add(errro);
            }

        }
        return erros;

    }

    @Override
    public List<ItfInfoErroSB> executarTestesAcoes() {
        List<ItfInfoErroSB> erros = new ArrayList<>();

        for (ItfAcaoDoSistema acao : MapaAcoesSistema.getListaTodasAcoes()) {

            try {
                UtilTestePagina.testaconfigIcone(acao.getEnumAcaoDoSistema());
                if (acao.isUmaAcaoFormulario()) {

                    UtilTestePagina.testaAcaoFormulario(acao.getComoFormulario());
                }
            } catch (Throwable t) {
                InfoErroSBCoreFW errro = new InfoErroSBCoreFW();
                errro.configurar(new Mensagem(FabTipoAgenteDoSistema.DESENVOLVEDOR, FabMensagens.ERRO, t.getMessage()), FabErro.ARQUIVAR_LOG, t);
                erros.add(errro);
            }

        }
        return erros;

    }

    public void criarLayoutsDeObjetos() {
        UtilSBDevelGeradorCodigoWeb.gerarGetAppScopeContainersDeObjetos();
    }

    public void criarMapaDeAcoesEscopoAplicacao() {
        for (ItfModuloAcaoSistema modulo : MapaAcoesSistema.getModulos()) {
            for (ItfAcaoGerenciarEntidade gestao : MapaAcoesSistema.getAcoesGestaoByModulo(modulo)) {
                if (SBCore.getNomeProjeto().equals("webApp")) {
                    UtilSBDevelGeradorCodigoWeb.gerarGetAppScopeAcoesDeGestaoDoProjeto(gestao);
                }
            }
        }
    }

}
