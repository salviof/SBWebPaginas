/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.centralAtributo;

import com.super_bits.modulosSB.SBCore.modulos.fonteDados.CentralAtributosDeObjetosSemPersistencia;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.seletores.listagemItem.ItfListagemItensSomenteLeitura;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.seletores.listagemItem.ItflistagemItemEditavel;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.seletores.seletorMultiplo.ItfselecaoListaComOrigem;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.seletores.seletorUnicoObjeto.ItfSelecaoObjetoDeUmaLista;
import com.super_bits.modulosSB.webPaginas.JSFBeans.PrimeFacesBeanModel.BP_PickList;
import com.super_bits.modulosSB.webPaginas.JSFBeans.PrimeFacesBeanModel.BP_autoCompleteEPesquisa;
import com.super_bits.modulosSB.webPaginas.JSFBeans.SBBeanModel.lista.B_ListaItemEditavelJsf;

/**
 *
 * @author desenvolvedor
 */
public class CentralAtributosWebappSemPersistencia extends CentralAtributosDeObjetosSemPersistencia {

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
}
