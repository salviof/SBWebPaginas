/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.SBBeanModel.developer;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.EntidadeSimples;
import com.super_bits.modulosSB.webPaginas.ConfigGeral.SBWebPaginas;

/**
 *
 * @author salvioF
 */
@InfoObjetoSB(tags = "Detalhes do sistema", plural = "Sistemas")
public class SistemaWebAtual extends EntidadeSimples {

    @InfoCampo(tipo = FabTipoAtributoObjeto.ID)
    private Long id = 0l;
    @InfoCampo(tipo = FabTipoAtributoObjeto.NOME)
    private String nome;
    @InfoCampo(tipo = FabTipoAtributoObjeto.DESCRITIVO)
    private String descricao;
    @InfoCampo(tipo = FabTipoAtributoObjeto.URL)
    private String url;
    private String empresa;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getNome() {
        if (nome == null) {
            nome = SBCore.getNomeProjeto();
        }
        return nome;
    }

    public String getDescricao() {
        if (descricao == null) {
            SBWebPaginas.getTituloApp();
        }
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUrl() {
        if (url == null) {
            url = SBWebPaginas.getSiteHost();
        }
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getEmpresa() {
        if (empresa == null) {
            empresa = SBCore.getInfoAplicacao().getCliente();
        }
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

}
