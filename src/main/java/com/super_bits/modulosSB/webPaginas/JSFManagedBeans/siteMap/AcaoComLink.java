/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFManagedBeans.siteMap;

import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringFiltros;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringValidador;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfModuloAcaoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoController;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoControllerEntidade;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoSecundaria;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoFormulario;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoFormularioEntidade;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoGerenciarEntidade;
import com.super_bits.modulosSB.SBCore.modulos.Controller.fabricas.FabTipoAcaoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.fabricas.FabTipoAcaoSistemaGenerica;
import com.super_bits.modulosSB.SBCore.modulos.Mensagens.ItfMensagem;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ItfFabricaAcoes;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.ItemGenerico;
import com.super_bits.modulosSB.webPaginas.controller.servletes.servletWebPaginas.EstruturaDeFormulario;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.List;
import javax.persistence.Transient;

/**
 *
 * As ações ManagedBen são ações principais do sistema, que contem uma pagina de
 * gestão MB_ vinculadas
 *
 * Todas as ações principais devem conter um managedBen vinculado.
 *
 * A criação destas ações são criadas dinamicamente no sistema e ficam
 * disponíveis logo após a criação de seus respectivos Managed Benas.
 *
 *
 * @author <a href="mailto:salviof@gmail.com">Salvio Furbino</a>
 * @since 30/01/2016
 * @version 1.0
 */
public class AcaoComLink extends ItemGenerico implements ItfAcaoDoSistema {

    @Transient
    private final ItfAcaoFormulario acaoOriginal;
    @Transient
    private String urlDeAcesso;
    @Transient
    private final String urlParcialGestao;

    public AcaoComLink(EstruturaDeFormulario pPagina) {
        acaoOriginal = pPagina.getAcaoGestaoVinculada();
        urlDeAcesso = pPagina.getUrlPadrao();
        urlParcialGestao = pPagina.getUrlPadrao().replace("/.wp", "");

    }

    /**
     *
     * Constructor para criação de url com Slug de ação inicial da pagina ex:
     * www.meusiteFabuloso.com.br/gestao/ac-editar/.wp
     *
     * @param pAcao
     * @param pAcaoGestaoLink
     */
    public AcaoComLink(ItfAcaoDoSistema pAcao, AcaoComLink pAcaoGestaoLink) {

        if (pAcaoGestaoLink == null) {
            throw new UnsupportedOperationException("A ação com o link de gestao não foi enviado no constructor");
        }

        if (pAcao.isUmaAcaoFormulario()) {
            acaoOriginal = pAcao.getComoFormulario();
        } else {
            acaoOriginal = pAcaoGestaoLink.getAcaoOriginal();
        }
        String slugurlAcao = "/ac-" + UtilSBCoreStringFiltros.gerarUrlAmigavel(pAcao.getNomeAcao());
        urlDeAcesso = pAcaoGestaoLink.getUrlParcialGestao() + slugurlAcao + "/.html";
        urlParcialGestao = pAcaoGestaoLink.getUrlParcialGestao();
    }

    protected void alterarUrl(String pNovaUrl) {
        urlDeAcesso = pNovaUrl;
    }

    @Override
    public String getNomeAcao() {
        return acaoOriginal.getNomeAcao();
    }

    @Override
    public String getIconeAcao() {
        return acaoOriginal.getIconeAcao();
    }

    @Override
    public String getCor() {
        return acaoOriginal.getCor();
    }

    @Override
    public String getDescricao() {
        return acaoOriginal.getDescricao();
    }

    @Override
    public ItfModuloAcaoSistema getModulo() {
        return acaoOriginal.getModulo();
    }

    @Override
    public boolean isPrecisaPermissao() {
        return acaoOriginal.isPrecisaPermissao();
    }

    @Override
    public void setId(int pId) {
        acaoOriginal.setId(pId);
    }

    @Override
    public void setIconeAcao(String pIcone) {
        acaoOriginal.setIconeAcao(pIcone);
    }

    @Override
    public void setModuloAcaoSistema(ItfModuloAcaoSistema pmodulo) {
        acaoOriginal.setModuloAcaoSistema(pmodulo);
    }

    @Override
    public boolean isConfigurado() {
        return acaoOriginal.isConfigurado();
    }

    @Override
    public String getImgPequena() {
        return acaoOriginal.getImgPequena();
    }

    @Override
    public String getNomeCurto() {
        return acaoOriginal.getNomeCurto();
    }

    @Override
    public int getId() {
        return acaoOriginal.getId();
    }

    @Override
    public String getNomeCampo(FabTipoAtributoObjeto pInfocampo) {
        return acaoOriginal.getNomeCampo(pInfocampo);
    }

    @Override
    public Field getCampoReflexaoByAnotacao(FabTipoAtributoObjeto pInfoCampo) {
        return acaoOriginal.getCampoReflexaoByAnotacao(pInfoCampo);
    }

    public String getUrlDeAcesso() {
        return urlDeAcesso;
    }

    public ItfAcaoFormulario getAcaoOriginal() {
        return acaoOriginal;
    }

    @Override
    public String getNomeUnico() {
        return acaoOriginal.getNomeUnico();
    }

    @Override
    public String getNomeEnumOriginal() {
        return acaoOriginal.getNomeEnumOriginal();
    }

    public FabTipoAcaoSistemaGenerica getTipoAcao() {
        return null;
    }

    @Override
    public void setDescricao(String pDescricao) {
        acaoOriginal.setDescricao(pDescricao);
    }

    @Override
    public FabTipoAcaoSistema getTipoAcaoSistema() {
        return acaoOriginal.getTipoAcaoSistema();
    }

    @Override
    public boolean isTemAcaoPrincipal() {
        return acaoOriginal.isTemAcaoPrincipal();
    }

    @Override
    public void configurarPropriedadesBasicas(ItfAcaoDoSistema pAcaoDoSistema) {
        acaoOriginal.configurarPropriedadesBasicas(pAcaoDoSistema);
    }

    @Override
    public boolean isUmaAcaoFormulario() {
        return acaoOriginal.isUmaAcaoFormulario();
    }

    @Override
    public String getNome() {
        return acaoOriginal.getNome();
    }

    @Override
    public void setNome(String pNome) {
        acaoOriginal.setNome(pNome);
    }

    @Override
    public void setNomeAcao(String pNome) {
        acaoOriginal.setNomeAcao(pNome);
    }

    @Override
    public String getIdDescritivoJira() {
        return acaoOriginal.getIdDescritivoJira();
    }

    @Override
    public void setIdDescritivoJira(String pIdJira) {
        acaoOriginal.setIdDescritivoJira(pIdJira);
    }

    @Override
    public void setPrecisaPermissao(boolean pPermissao) {
        acaoOriginal.setPrecisaPermissao(pPermissao);
    }

    @Override
    public FabTipoAcaoSistemaGenerica getTipoAcaoGenerica() {
        return FabTipoAcaoSistemaGenerica.GERENCIAR_DOMINIO;
    }

    @Override
    public boolean isUmaAcaoGenerica() {
        return true;
    }

    @Override
    public boolean isUmaAcaoGestaoDominio() {
        return true;
    }

    @Override
    public ItfFabricaAcoes getEnumAcaoDoSistema() {
        return acaoOriginal.getEnumAcaoDoSistema();
    }

    @Override
    public boolean isUmaAcaoSessaoMenu() {
        return false;
    }

    @Override
    public String getNomeUnicoSlug() {
        return getNomeCurto() + "-" + getId();
    }

    @Override
    public boolean isUmaAcaoDeEntidade() {
        return acaoOriginal.isUmaAcaoDeEntidade();
    }

    @Override
    public boolean isUmaAcaoController() {
        return acaoOriginal.isUmaAcaoController();
    }

    @Override
    public String getNomeDominio() {
        return acaoOriginal.getNomeDominio();
    }

    @Override
    public ItfAcaoFormulario getComoFormulario() {
        return acaoOriginal.getComoFormulario();
    }

    @Override
    public ItfAcaoGerenciarEntidade getComoGestaoEntidade() {
        return acaoOriginal.getComoGestaoEntidade();
    }

    @Override
    public ItfAcaoController getComoController() {
        return acaoOriginal.getComoController();
    }

    @Override
    public ItfAcaoSecundaria getComoSecundaria() {
        throw new UnsupportedOperationException("Uma ação de Managed bean não pode ser tratada como ação secundaria"); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ItfAcaoFormularioEntidade getComoFormularioEntidade() {
        return acaoOriginal.getComoFormularioEntidade();
    }

    @Override
    public ItfAcaoControllerEntidade getComoControllerEntidade() {
        return acaoOriginal.getComoControllerEntidade();
    }

    @Override
    public ItfAcaoGerenciarEntidade getAcaoDeGestaoEntidade() {
        return acaoOriginal.getAcaoDeGestaoEntidade();
    }

    @Override
    public String getIconeDaClasse() {
        return "fa fa-link";
    }

    @Override
    public String getXhtmlVisao() {
        throw new UnsupportedOperationException("O Objeto ação com link não possui uma interface de visualizacao"); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean validar() {
        return acaoOriginal.validar();
    }

    @Override
    public List<ItfMensagem> validarComMensagens() {
        return acaoOriginal.validarComMensagens();
    }

    public String getUrlParcialGestao() {

        return urlParcialGestao;
    }

    @Override
    public boolean uploadFotoTodosFormatos(InputStream pStream) {
        return acaoOriginal.uploadFotoTodosFormatos(pStream);
    }

    @Override
    public boolean uploadFotoTamanhoPequeno(InputStream pStream) {
        return acaoOriginal.uploadFotoTamanhoPequeno(pStream);
    }

    @Override
    public boolean uploadFotoTamanhoMedio(InputStream pStream) {
        return acaoOriginal.uploadFotoTamanhoMedio(pStream);
    }

    @Override
    public boolean uploadFotoTamanhoGrande(InputStream pStream) {
        return acaoOriginal.uploadFotoTamanhoGrande(pStream);
    }

    @Override
    public boolean uploadArquivoDeEntidade(ItfCampoInstanciado prcampo, byte[] pStream, String pNomeArquivo) {
        return acaoOriginal.uploadArquivoDeEntidade(prcampo, pStream, pNomeArquivo);
    }

    @Override
    public String getSlugIdentificador() {
        return acaoOriginal.getSlugIdentificador();
    }

    @Override
    public boolean isAcaoTemModal() {
        return acaoOriginal.isAcaoTemModal();
    }

    @Override
    public String getIcone() {
        return getIconeAcao();
    }

    @Override
    public boolean isTemImagemPequenaAdicionada() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getXhtmlVisaoMobile() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getXhtmlVisao(int numeroColunas) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
