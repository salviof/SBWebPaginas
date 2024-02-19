/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.visualizacao;

import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.siteMap.MapaDeFormularios;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.MapaAcoesSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoFormulario;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoGerenciarEntidade;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ItfFabricaAcoes;
import com.super_bits.modulosSB.SBCore.modulos.view.ServicoVisualizacaoAbstrato;
import com.super_bits.modulosSB.webPaginas.ConfigGeral.SBWebPaginas;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWPServletTools;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWP_JSFTools;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * Classe para configuração de exibição padrão de Objetos do sistema
 *
 * Cada objeto deve conter uma classe de visualização, caso não contenha é
 * utilizado uma visulização padrão baseada na interface BeanSimples
 *
 *
 * @author salvioF
 */
public class ServicoVisuaslizacaoWebResponsivo extends ServicoVisualizacaoAbstrato {

    private static final String CAMINHO_ITEM_SIMPLES = "/resources/modelo/objeto/include/itemSimples.xhtml";
    private static final String CAMINHO_ITEM_NORMAL = "/resources/modelo/objeto/include/itemNormal.xhtml";
    public static final String CAMINHO_ITEM_SIMPLES_NULO = "/resources/modelo/objeto/include/itemSimplesNulo.xhtml";
    public static final String CAMINHO_ITEM_NORMAL_NULO = "/resources/modelo/objeto/include/itemNormalNulo.xhtml";

    public ServicoVisuaslizacaoWebResponsivo() {
        super(TIPOS_INTERFACES_COMUM_VISUALIZACAO.WEB_RESPONSIVO);
    }

    @Override
    public String getCaminhoXhtmlAcaoDoSistema(ItfAcaoFormulario pAcao) {
        throw new UnsupportedOperationException("Ainda não implementado"); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visualizarFormularioGestao(ItfAcaoGerenciarEntidade acaoForm) {
        throw new UnsupportedOperationException("Ainda não implementado"); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ServicoVisualizacaoAbstrato.TIPOS_INTERFACES_COMUM_VISUALIZACAO getTipoVisualizacao() {
        return TIPOS_INTERFACES_COMUM_VISUALIZACAO.WEB_RESPONSIVO;
    }

    private String buildArqXHTML(Class pEntidade) {
        return buildArqXHTML(pEntidade, TIPO_VISUALIZACAO_PADRAO, false);
    }

    private String buildCaminhoXHTML(TIPO_VISUALIZACAO_ITEM pTipoVisualizacao, Class pEntidade) {
        return buildCaminhoXHTML(pTipoVisualizacao, pEntidade, TIPO_VISUALIZACAO_PADRAO);
    }

    @Override
    public String getCaminhoPastaContainerLaboratorio(Class pEntidade) {
        return buildPastaContainers(TIPO_VISUALIZACAO_ITEM.LABORATORIO, pEntidade);
    }

    @Override
    public String getCaminhoPastaContainerPublicado(Class pEntidade) {
        return buildPastaContainers(TIPO_VISUALIZACAO_ITEM.PUBLICADO, pEntidade);
    }

    private String buildCaminhoXHTML(TIPO_VISUALIZACAO_ITEM pTipoVisualizacao, Class pEntidade, String pTipoEspecial) {
        return buildPastaContainers(pTipoVisualizacao, pEntidade) + pTipoEspecial + "/" + buildArqXHTML(pEntidade);

    }

    @Override
    public String getCaminhoXhtmlItemCard(Class pEntidade) {
        String caminhoPersonalizado = buildCaminhoXHTML(TIPO_VISUALIZACAO_ITEM.PUBLICADO, pEntidade);
        if (UtilSBWP_JSFTools.isExisteEsteFormulario(caminhoPersonalizado)) {
            return caminhoPersonalizado;
        }
        System.out.println(caminhoPersonalizado + " - Nâo existe");
        return CAMINHO_ITEM_SIMPLES;
    }

    @Override
    public String getCaminhoLocalPastaImagem() {
        return UtilSBWPServletTools.getCaminhoLocalServletsResource();
    }

    @Override
    public String getRemotoPastaResource() {
        return SBWebPaginas.getSiteURL() + "/resources";
    }

    @Override
    public String getCaminhoXhtmlItemCardLab(Class pEntidade) {
        String caminhoPersonalizado = buildCaminhoXHTML(TIPO_VISUALIZACAO_ITEM.LABORATORIO, pEntidade);
        if (UtilSBWP_JSFTools.isExisteEsteFormulario(caminhoPersonalizado)) {
            return caminhoPersonalizado;
        }
        return CAMINHO_ITEM_SIMPLES;
    }

    @Override
    public String getCaminhoXhtmlItemAlternativo(Class pEntidade, String pNoneAlternativo) {
        String caminhoPersonalizado = buildCaminhoXHTML(TIPO_VISUALIZACAO_ITEM.LABORATORIO, pEntidade, pNoneAlternativo);
        if (UtilSBWP_JSFTools.isExisteEsteFormulario(caminhoPersonalizado)) {
            return caminhoPersonalizado;
        }
        return CAMINHO_ITEM_SIMPLES;
    }

    @Override
    public String getCaminhoXhtmlItemAlternativoLab(Class pEntidade, String pNomeAlternativo) {
        String caminhoPersonalizado = buildCaminhoXHTML(TIPO_VISUALIZACAO_ITEM.LABORATORIO, pEntidade, pNomeAlternativo);
        if (UtilSBWP_JSFTools.isExisteEsteFormulario(caminhoPersonalizado)) {
            return caminhoPersonalizado;
        }
        return CAMINHO_ITEM_SIMPLES;
    }

    @Override
    public List<String> getTodasVisualizacoes(Class pEntidade) {
        return new ArrayList<>();
    }

    @Override
    public String buildCaminhoRelativoItemSimples() {
        return CAMINHO_ITEM_SIMPLES;
    }

    @Override
    public String buildCaminhoRelativoItemSimplesNulo() {
        return CAMINHO_ITEM_SIMPLES_NULO;
    }

    @Override
    public String buildCaminhoRelativoItemNormal() {
        return CAMINHO_ITEM_NORMAL;
    }

    @Override
    public String buildCaminhoRelativoItemNulo() {
        return CAMINHO_ITEM_NORMAL_NULO;
    }

    @Override
    public String getEndrLocalArquivoReferenciaNovoComponente() {
        return SBCore.getCentralDeArquivos().getEntrLocalArquivosFormulario() + CAMINHO_ITEM_SIMPLES;
    }

    @Override
    public String getEndrRemotoFormulario(ItfFabricaAcoes pAcao, Object... paramentros) {
        return MapaDeFormularios.getUrlFormulario(pAcao.getRegistro().getComoFormulario(), paramentros);
    }

    @Override
    public String getEndrRemotoFormulario(String pAcaoNomeUnico, Object... paramentros) {
        return MapaDeFormularios.getUrlFormulario(MapaAcoesSistema.getAcaoDoSistemaByNomeUnico(pAcaoNomeUnico), paramentros);
    }

}
