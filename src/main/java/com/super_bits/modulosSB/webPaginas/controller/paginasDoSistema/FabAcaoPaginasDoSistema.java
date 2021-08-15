/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.controller.paginasDoSistema;

import com.super_bits.modulos.SBAcessosModel.controller.FabModulosSistemaSB;
import com.super_bits.modulos.SBAcessosModel.controller.InfoModulosSistemaSB;
import com.super_bits.modulos.SBAcessosModel.model.acoes.AcaoDoSistema;
import com.super_bits.modulos.SBAcessosModel.model.acoes.UtilFabricaDeAcoesAcessosModel;
import com.super_bits.modulos.SBAcessosModel.model.tokens.TokenAcesso;
import com.super_bits.modulos.SBAcessosModel.model.tokens.tokenLoginDinamico.TokenAcessoDinamico;
import com.super_bits.modulosSB.SBCore.modulos.Controller.anotacoes.InfoTipoAcaoFormulario;
import com.super_bits.modulosSB.SBCore.modulos.Controller.anotacoes.InfoTipoAcaoGestaoEntidade;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ItfFabricaAcoes;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.icones.FabIconeFontAwesome;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWP_JSFTools;

/**
 *
 *
 *
 *
 *
 *
 *
 * @author Sálvio Furbino
 */
@InfoModulosSistemaSB(modulo = FabModulosSistemaSB.PAGINAS_DO_SISTEMA)
public enum FabAcaoPaginasDoSistema implements ItfFabricaAcoes {

    @InfoTipoAcaoGestaoEntidade(xhtmlDaAcao = UtilSBWP_JSFTools.FORMULARIO_HOME, icone = "fa fa-heart-o", precisaPermissao = false)
    PAGINA_NATIVA_HOME_MB_PADRAO,
    @InfoTipoAcaoGestaoEntidade(icone = "fa fa-lock", precisaPermissao = false, xhtmlDaAcao = UtilSBWP_JSFTools.FORMULARIO_ACESSO_NEGADO)
    PAGINA_NATIVA_ACESSO_NEGADO_MB_PADRAO,
    @InfoTipoAcaoGestaoEntidade(icone = "fa fa-lock",
            precisaPermissao = false, xhtmlDaAcao = UtilSBWP_JSFTools.FORMULARIO_PARAMETRO_URL_INVALIDO)
    PAGINA_NATIVA_PARAMETRO_NAO_ENCONTRADO_MB,
    @InfoTipoAcaoFormulario(icone = "fa fa-lock", precisaPermissao = false, xhtmlDaAcao = UtilSBWP_JSFTools.FORMULARIO_ACESSO_NEGADO_SUB_FORM)
    PAGINA_NATIVA_ACESSO_NEGADO_FRM_SUB_FORM,
    @InfoTipoAcaoGestaoEntidade(iconeFonteAnsowame = FabIconeFontAwesome.REG_ATUALIZAR, precisaPermissao = false, xhtmlDaAcao = UtilSBWP_JSFTools.FORMULARIO_SESSAO_EXPIROU)
    PAGINA_NATIVA_VIEW_EXPIROU_MB_PADRAO,
    @InfoTipoAcaoGestaoEntidade(icone = "fa fa-key", precisaPermissao = false, xhtmlDaAcao = UtilSBWP_JSFTools.FORMULARIO_LOGIN)
    PAGINA_NATIVA_LOGIN_MB_PADRAO,
    @InfoTipoAcaoGestaoEntidade(icone = "fa fa-key", precisaPermissao = false, xhtmlDaAcao = UtilSBWP_JSFTools.FORMULARIO_RECUPERACAO_DE_SENHA, entidade = TokenAcesso.class)
    PAGINA_NATIVA_RECUPERACAO_SENHA_MB,
    @InfoTipoAcaoGestaoEntidade(icone = "fa fa-key", precisaPermissao = false, xhtmlDaAcao = UtilSBWP_JSFTools.FORMULARIO_ACESSO_DINAMICO_VIA_TOKEN, entidade = TokenAcessoDinamico.class)
    PAGINA_NATIVA_TOKEN_DINAMICO_MB,
    @InfoTipoAcaoFormulario(icone = "fa fa-key", precisaPermissao = false, xhtmlDaAcao = UtilSBWP_JSFTools.FORMULARIO_RECUPERACAO_DE_SENHA_GERAR_SENHA)
    PAGINA_NATIVA_RECUPERACAO_SENHA_FRM_GERAR_NOVA_SENHA,
    @InfoTipoAcaoGestaoEntidade(icone = "fa fa-key", precisaPermissao = false, xhtmlDaAcao = UtilSBWP_JSFTools.FORMULARIO_ERRO_CRITICO)
    PAGINA_NATIVA_ERRO_CRITICO_MB_PADRAO,
    @InfoTipoAcaoGestaoEntidade(iconeFonteAnsowame = FabIconeFontAwesome.REG_PESQUISA_AVANCADA, precisaPermissao = false, xhtmlDaAcao = UtilSBWP_JSFTools.FORMULARIO_MODAL_PESQUISA_ITEM_AVANCADO)
    PAGINA_PESQUISA_AVANCADA_MB_PADRAO,
    @InfoTipoAcaoGestaoEntidade(iconeFonteAnsowame = FabIconeFontAwesome.REG_PESQUISA_AVANCADA, precisaPermissao = false, xhtmlDaAcao = UtilSBWP_JSFTools.FORMULARIO_MODAL_JUSTIFICATIVA)
    PAGINA_NATIVA_MODAL_JUSTIFICATIVA_MB,
    @InfoTipoAcaoGestaoEntidade(iconeFonteAnsowame = FabIconeFontAwesome.REG_PESQUISA_AVANCADA, precisaPermissao = false, xhtmlDaAcao = UtilSBWP_JSFTools.FORMULARIO_MODAL_COMUNICACAO)
    PAGINA_NATIVA_MODAL_COMUNICACAO_GENERICA_MB,
    @InfoTipoAcaoGestaoEntidade(iconeFonteAnsowame = FabIconeFontAwesome.REG_PESQUISA_AVANCADA, precisaPermissao = false, xhtmlDaAcao = UtilSBWP_JSFTools.FORMULARIO_MODAL_COMUNICACAO_ACAO_TRANSIENTE)
    PAGINA_NATIVA_MODAL_COMUNICACAO_ACAO_TRANSIENT_MB;

    @Override
    public Class getEntidadeDominio() {
        return AcaoDoSistema.class;

    }

    @Override
    public String getNomeModulo() {
        return UtilFabricaDeAcoesAcessosModel.getModuloByFabrica(this).getNome();
    }

    @Override
    public AcaoDoSistema getRegistro() {
        AcaoDoSistema acao = (AcaoDoSistema) UtilFabricaDeAcoesAcessosModel.getNovaAcao(this);
        return acao;
    }

}
