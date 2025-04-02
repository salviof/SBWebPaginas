/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.tipos;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 *
 *
 *
 *
 *
 * @author sfurbino
 */
@ApplicationScoped
public class TipoCampos extends ConstantesWeb {

    public TipoCampos() {
        super(FabTipoAtributoObjeto.class);
    }

    @Override
    public String getPadrao() {

        FabTipoAtributoObjeto campopadrao = FabTipoAtributoObjeto.TEXTO_SIMPLES;
        return campopadrao.toString();

    }

    public static String getStrTipoCampoInput(FabTipoAtributoObjeto pCampo) {

        return pCampo.getTipo_input_prime().getRegistro().toString();

    }

    public String getNomeCurto() {
        return getStrTipoCampoInput(FabTipoAtributoObjeto.NOME);
    }

    public String getNomeLongo() {
        return getStrTipoCampoInput(FabTipoAtributoObjeto.NOME);
    }

    public String getLC_CEP() {
        return getStrTipoCampoInput(FabTipoAtributoObjeto.DESCRITIVO);
    }

    public String getID() {
        return getStrTipoCampoInput(FabTipoAtributoObjeto.ID);
    }

    public String getLOOKUPMULTIPLO() {
        return getStrTipoCampoInput(FabTipoAtributoObjeto.LISTA_OBJETOS_PUBLICOS);
    }

    public String getTEXTO_SIMPLES() {
        return getStrTipoCampoInput(FabTipoAtributoObjeto.TEXTO_SIMPLES);
    }

    public String getNOME_CURTO() {
        return getStrTipoCampoInput(FabTipoAtributoObjeto.NOME);
    }

    public String getNOME_CURTO_LONGO() {
        return getStrTipoCampoInput(FabTipoAtributoObjeto.NOME_LONGO);
    }

    public String getDescritivo() {
        return getStrTipoCampoInput(FabTipoAtributoObjeto.DESCRITIVO);
    }

    public String getDataHora() {

        return getStrTipoCampoInput(FabTipoAtributoObjeto.DATAHORA);

    }

    public String getIMG_PEQUENA() {
        return getStrTipoCampoInput(FabTipoAtributoObjeto.IMG_PEQUENA);
    }

    public String getIMG_MEDIA() {
        return getStrTipoCampoInput(FabTipoAtributoObjeto.IMG_MEDIA);
    }

    public String getIMG_GRANDE() {
        return getStrTipoCampoInput(FabTipoAtributoObjeto.IMG_GRANDE);
    }

    public String getIDENTIFICADOR() {
        return getStrTipoCampoInput(FabTipoAtributoObjeto.ID);
    }

    public String getLAT() {
        return getStrTipoCampoInput(FabTipoAtributoObjeto.LATITUDE);
    }

    public String LONG() {
        return getStrTipoCampoInput(FabTipoAtributoObjeto.LONGITUDE);
    }

    public String getLCLOGRADOURO() {
        return getStrTipoCampoInput(FabTipoAtributoObjeto.LC_LOGRADOURO);
    }

    public String getLCCEP() {
        return getStrTipoCampoInput(FabTipoAtributoObjeto.LCCEP);
    }

    public String getLCBairro() {
        return getStrTipoCampoInput(FabTipoAtributoObjeto.LC_BAIRRO);
    }

    public String getLCCidade() {
        return getStrTipoCampoInput(FabTipoAtributoObjeto.LC_CIDADE);
    }

    public String getTelefoneFixoNacional() {
        return getStrTipoCampoInput(FabTipoAtributoObjeto.TELEFONE_FIXO_NACIONAL);
    }

    public String getTelefoneComplementar() {
        return getStrTipoCampoInput(FabTipoAtributoObjeto.TELEFONE_CELULAR);
    }

    public String getLCComplemeto() {
        return getStrTipoCampoInput(FabTipoAtributoObjeto.LC_COMPLEMENTO_E_NUMERO);
    }

    public String getLCCampoAberto() {
        return getStrTipoCampoInput(FabTipoAtributoObjeto.LC_CAMPO_ABERTO);
    }

    public String getHTML() {
        return getStrTipoCampoInput(FabTipoAtributoObjeto.HTML);
    }

    public String getCHART_VALOR() {
        return getStrTipoCampoInput(FabTipoAtributoObjeto.CHART_VALOR);
    }

    public String getCHART_LABEL() {
        return FabTipoAtributoObjeto.CHART_LABEL.toString();
    }

    public String getCHART_CATEGORIA() {
        return getStrTipoCampoInput(FabTipoAtributoObjeto.CHART_CATEGORIA);
    }

    public String getCALENDARIO() {
        return getStrTipoCampoInput(FabTipoAtributoObjeto.CALENDARIO);
    }

    public String getTELEFONE_FIXO_NACIONAL() {
        return getStrTipoCampoInput(FabTipoAtributoObjeto.TELEFONE_FIXO_NACIONAL);
    }

    public String getMOEDAReal() {
        return getStrTipoCampoInput(FabTipoAtributoObjeto.MOEDA_REAL);
    }

    public String getLOOKUP() {
        return getStrTipoCampoInput(FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA);
    }

    public String getCOR() {
        return getStrTipoCampoInput(FabTipoAtributoObjeto.COR);
    }

    public String getEMAIL() {
        return getStrTipoCampoInput(FabTipoAtributoObjeto.EMAIL);
    }

    public String getSITE() {
        return getStrTipoCampoInput(FabTipoAtributoObjeto.SITE);
    }

    public String getURL() {
        return getStrTipoCampoInput(FabTipoAtributoObjeto.URL);
    }

    public String getRESPONSAVEL() {
        return getStrTipoCampoInput(FabTipoAtributoObjeto.RESPONSAVEL);
    }

    public String getCNPJ() {
        return getStrTipoCampoInput(FabTipoAtributoObjeto.CNPJ);
    }

    public String getCPF() {
        return getStrTipoCampoInput(FabTipoAtributoObjeto.CPF);
    }

    public String getINSCRICAO_ESTADUAL() {
        return getStrTipoCampoInput(FabTipoAtributoObjeto.INSCRICAO_ESTADUAL);
    }

    public String getINSCRIACAO_MUNICIPAL() {
        return getStrTipoCampoInput(FabTipoAtributoObjeto.INSCRIACAO_MUNICIPAL);
    }

    public String getAAANOME_CURTO() {
        return getStrTipoCampoInput(FabTipoAtributoObjeto.NOME);
    }

    public String getNOME_LONGO() {
        return getStrTipoCampoInput(FabTipoAtributoObjeto.NOME_LONGO);
    }

    public String getDESCRITIVO() {
        return getStrTipoCampoInput(FabTipoAtributoObjeto.DESCRITIVO);
    }

    public String getQUANTIDADE() {
        return getStrTipoCampoInput(FabTipoAtributoObjeto.QUANTIDADE);
    }

    public String getPERCENTUA() {
        return getStrTipoCampoInput(FabTipoAtributoObjeto.PERCENTUAL);
    }

    public String getSenha() {
        return getStrTipoCampoInput(FabTipoAtributoObjeto.SENHA);
    }

    public String getVerdadeiroFalso() {
        return getStrTipoCampoInput(FabTipoAtributoObjeto.VERDADEIRO_FALSO);
    }

    public String getDATA() {
        return getStrTipoCampoInput(FabTipoAtributoObjeto.DATA);
    }

}
