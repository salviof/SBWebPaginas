/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulosSB.webPaginas.JSFManagedBeans.declarados.Paginas;

import com.super_bits.modulos.SBAcessosModel.fabricas.FabAcaoProjetoSB;
import com.super_bits.modulos.SBAcessosModel.fabricas.InfoAcaoProjetoSB;
import com.super_bits.modulos.SBAcessosModel.model.ModuloAcaoSistema;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.MapaAcoesSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoFormulario;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoGerenciarEntidade;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ComoFabricaAcoes;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ComoEntidadeSimples;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.MB_PaginaConversation;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.reflexao.anotacoes.InfoPagina;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ComoAcaoDoSistema;

/**
 *
 *
 *
 * @author desenvolvedor
 */
@Named
@SessionScoped
@InfoAcaoProjetoSB(acao = FabAcaoProjetoSB.PROJETO_MB_GERENCIAR)
@InfoPagina(nomeCurto = "GPROJ", tags = "Andamento do Projeto")
public class PgProjetoSBVisaoGeral extends MB_PaginaConversation {

    private List<ItfAcaoGerenciarEntidade> acoesGestaoDoModulo;
    private ItfAcaoGerenciarEntidade acaoGEstaoSelecionada;
    private ModuloAcaoSistema moduloSelecionado;
    private List<ModuloAcaoSistema> modulosDoSistema;

    private List<ComoEntidadeSimples> objetosDoSistema;
    private final ItfAcaoFormulario acaoVisualizarPontosDeFuncao = FabAcaoProjetoSB.PROJETO_FRM_VISUALIZAR_ACOES.getRegistro().getComoFormularioEntidade();
    private final ItfAcaoFormulario acaoVisualizarBancoDeDados = FabAcaoProjetoSB.PROJETO_FRM_VISUALIZAR_BANCO_DE_DADOS.getRegistro().getComoFormularioEntidade();
    private final ItfAcaoFormulario acaoVisisaoGeral = FabAcaoProjetoSB.PROJETO_FRM_VISAO_GERAL.getRegistro().getComoFormularioEntidade();

    @PostConstruct
    public void init() {
        modulosDoSistema = (List) MapaAcoesSistema.getModulos();
        acaoSelecionada = acaoVisisaoGeral;
    }

    public List<ComoAcaoDoSistema> buildListaDeAcoes(ComoFabricaAcoes... acoes) {
        List<ComoAcaoDoSistema> acoesCriadas = new ArrayList<>();

        try {
            for (ComoFabricaAcoes fabrica : acoes) {
                acoesCriadas.add(fabrica.getRegistro());
            }
        } catch (Throwable t) {
            SBCore.getCentralDeMensagens().enviarMsgErroAoUsuario("Erro criando Grupo de ações, é possível que esta tela exiba informações incompletas, entre em contato com a Central");
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, PAGINAINICIALID, t);
        }

        return acoesCriadas;

    }

    public List<ItfAcaoFormulario> getTiposVisualizacao() {
        return (List) buildListaDeAcoes(FabAcaoProjetoSB.PROJETO_FRM_VISUALIZAR_ACOES,
                FabAcaoProjetoSB.PROJETO_FRM_VISUALIZAR_BANCO_DE_DADOS, FabAcaoProjetoSB.PROJETO_FRM_VISAO_GERAL, FabAcaoProjetoSB.PROJETO_FRM_VISUALIZAR_TABELA);

    }

    @Override
    public void executarAcaoSelecionada() {
        super.executarAcaoSelecionada(); //To change body of generated methods, choose Tools | Templates.
    }

    public ModuloAcaoSistema getModuloSelecionado() {
        return moduloSelecionado;
    }

    public void setModuloSelecionado(ModuloAcaoSistema moduloSelecionado) {
        this.moduloSelecionado = moduloSelecionado;
    }

    public List<ModuloAcaoSistema> getModulosDoSistema() {
        return modulosDoSistema;
    }

    public void setModulosDoSistema(List<ModuloAcaoSistema> modulosDoSistema) {
        this.modulosDoSistema = modulosDoSistema;
    }

    public List<ComoEntidadeSimples> getObjetosDoSistema() {
        return objetosDoSistema;
    }

    public void setObjetosDoSistema(List<ComoEntidadeSimples> objetosDoSistema) {
        this.objetosDoSistema = objetosDoSistema;
    }

    public ItfAcaoFormulario getAcaoVisualizarPontosDeFuncao() {
        return acaoVisualizarPontosDeFuncao;
    }

    public ItfAcaoFormulario getAcaoVisualizarBancoDeDados() {
        return acaoVisualizarBancoDeDados;
    }

    public ItfAcaoFormulario getAcaoVisisaoGeral() {
        return acaoVisisaoGeral;
    }

    public List<ItfAcaoGerenciarEntidade> getAcoesGestaoDoModulo() {
        return acoesGestaoDoModulo;
    }

    public void setAcoesGestaoDoModulo(List<ItfAcaoGerenciarEntidade> acoesGestaoDoModulo) {
        this.acoesGestaoDoModulo = acoesGestaoDoModulo;
    }

    public ItfAcaoGerenciarEntidade getAcaoGEstaoSelecionada() {
        return acaoGEstaoSelecionada;
    }

    public void setAcaoGEstaoSelecionada(ItfAcaoGerenciarEntidade acaoGEstaoSelecionada) {
        this.acaoGEstaoSelecionada = acaoGEstaoSelecionada;
    }

    @Override
    public ComoEntidadeSimples getBeanSelecionado() {
        return moduloSelecionado;
    }

    @Override
    public void setBeanSelecionado(ComoEntidadeSimples pBeanSimples) {
        moduloSelecionado = (ModuloAcaoSistema) pBeanSimples;
    }

}
