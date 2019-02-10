/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.SBBeanModel.lista;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.listas.B_ListaItemEditavel;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import javax.faces.context.FacesContext;

/**
 *
 * @author SalvioF
 */
public class B_ListaItemEditavelJsf extends B_ListaItemEditavel {

    public B_ListaItemEditavelJsf(ItfCampoInstanciado pCampoInstanciado) {
        super(pCampoInstanciado);
    }

    @Override
    public void removerItemSelecionadoPeloIndice() {
        int indice;
        boolean parametroEnviado = false;
        for (String parametro : FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().keySet()) {
            if (parametro.contains("indiceSubformulario")) {
                indice = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(parametro));
                setIndiceItemSelecionado(indice);
                parametroEnviado = true;
                break;
            }
        }
        if (parametroEnviado) {
            getListaObjetosSelecionados().remove(getIndiceItemSelecionado());
        }
    }

}
