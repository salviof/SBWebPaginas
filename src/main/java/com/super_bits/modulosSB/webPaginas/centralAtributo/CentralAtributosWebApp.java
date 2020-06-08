/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulosSB.webPaginas.centralAtributo;

import com.super_bits.modulosSB.Persistencia.centralOrigemDados.CentralAtributosSBPersistencia;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.seletores.listagemItem.ItfListagemItensSomenteLeitura;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.seletores.listagemItem.ItflistagemItemEditavel;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.seletores.seletorMultiplo.ItfselecaoListaComOrigem;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.seletores.seletorUnicoObjeto.ItfSelecaoObjetoDeUmaLista;
import com.super_bits.modulosSB.webPaginas.JSFBeans.PrimeFacesBeanModel.BP_PickList;
import com.super_bits.modulosSB.webPaginas.JSFBeans.PrimeFacesBeanModel.BP_autoCompleteEPesquisa;
import com.super_bits.modulosSB.webPaginas.JSFBeans.SBBeanModel.lista.B_ListaItemEditavelJsf;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWP_JSFTools;
import javax.persistence.EntityManager;

/**
 *
 * @author desenvolvedor
 */
public class CentralAtributosWebApp extends CentralAtributosSBPersistencia {

    public CentralAtributosWebApp() {

    }

    @Override
    public Class<? extends ItfSelecaoObjetoDeUmaLista> getClasseSelecaoItem() {
        return BP_autoCompleteEPesquisa.class;
    }

    @Override
    public Class<? extends ItfselecaoListaComOrigem> getClasseSelecaoItens() {
        return BP_PickList.class;
    }

    @Override
    public Class<? extends ItflistagemItemEditavel> getClasseListaRegistrosEditavel() {
        return B_ListaItemEditavelJsf.class;
    }

    @Override
    public Class<? extends ItfListagemItensSomenteLeitura> getClasseListaRegistrosSomenteLeitura() {
        return B_ListaItemEditavelJsf.class;
    }

    @Override
    public EntityManager obterEntityManagerLasyMode() {
        return UtilSBWP_JSFTools.getEntityManagerDaPaginaAtual();
    }

}
