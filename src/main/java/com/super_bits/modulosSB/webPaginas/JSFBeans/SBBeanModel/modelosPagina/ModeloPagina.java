/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.SBBeanModel.modelosPagina;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.EntidadeSimples;
import com.super_bits.modulosSB.SBCore.modulos.view.modeloFormulario.ItfModeloFormularioSBFW;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author salvioF
 */
@InfoObjetoSB(tags = {"Modelo de Pagina"}, plural = "Modelos de paginas")
public class ModeloPagina extends EntidadeSimples implements ItfModeloFormularioSBFW {

    @InfoCampo(tipo = FabTipoAtributoObjeto.ID)
    private Long id;
    @InfoCampo(tipo = FabTipoAtributoObjeto.NOME)
    private String nomeModelo;
    @InfoCampo(tipo = FabTipoAtributoObjeto.DESCRITIVO)
    private String descricao;
    private String xhtmlVinculado;
    private List<String> areas;

    public ModeloPagina() {
        areas = new ArrayList<>();
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getNomeModelo() {
        return nomeModelo;
    }

    @Override
    public void setNomeModelo(String nomeModelo) {
        this.nomeModelo = nomeModelo;
    }

    @Override
    public String getDescricao() {
        return descricao;
    }

    @Override
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String getXhtmlVinculado() {
        return xhtmlVinculado;
    }

    @Override
    public void setXhtmlVinculado(String xhtmlVinculado) {
        this.xhtmlVinculado = xhtmlVinculado;
    }

    @Override
    public List<String> getAreas() {
        return areas;
    }

    @Override
    public void setAreas(List<String> areas) {
        this.areas = areas;
    }

    @Override
    public String getAreaPrincipal() {
        if (areas.isEmpty()) {
            throw new UnsupportedOperationException("Area principal não definida em" + getNomeModelo());
        }
        return areas.get(0);
    }

    @Override
    public String getAreaSecundaria() {
        if (areas.isEmpty() || areas.size() < 2) {
            throw new UnsupportedOperationException("Area secundaria não encontrada em" + getNomeModelo());
        }
        return areas.get(1);

    }

    @Override
    public String getAreaExtra1() {
        if (areas.isEmpty() || areas.size() < 3) {
            throw new UnsupportedOperationException("Area extra1 não encontrada em" + getNomeModelo());
        }
        return areas.get(2);
    }

    @Override
    public String getAreaExtra2() {
        if (areas.isEmpty() || areas.size() < 4) {
            throw new UnsupportedOperationException("Area extra2 não encontrada em" + getNomeModelo());
        }
        return areas.get(3);
    }

    @Override
    public String getAreaExtra3() {
        if (areas.isEmpty() || areas.size() < 5) {
            throw new UnsupportedOperationException("Area extra3 não encontrada em" + getNomeModelo());
        }
        return areas.get(4);
    }

}
