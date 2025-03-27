/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.super_bits.modulosSB.webPaginas.visualizacao;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWPServletTools;
import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author salvio
 */
@Named
@RequestScoped
public class ServicoVisualizacaoComponente {

    public String getVisualizacaoCampo(ItfBeanSimples pVisualizacao) {
        FacesContext contexto = FacesContext.getCurrentInstance();
        //   UIComponent componente = UIComponent.getCurrentComponent(contexto);

        if (pVisualizacao != null) {
            String xhtml = pVisualizacao.getXhtmlVisao();
            return xhtml;
        }
        return "/resources/modelo/objeto/include/itemSimples.xhtml";
    }

    public String getCompositeTemplate() {

        FacesContext context = FacesContext.getCurrentInstance();
        ELContext elContext = context.getELContext();
        ExpressionFactory factory = context.getApplication().getExpressionFactory();
        ValueExpression valueExpression = factory.createValueExpression(elContext, "#{visualizacao}", Object.class);
        Object result = valueExpression.getValue(elContext);
        return result != null ? result.toString() : "/resources/modelo/objeto/include/itemSimples.xhtml";

        //   UIComponent currentComponent = UIComponent.getCurrentComponent(context);
        // Se for um composite component, obter o atributo "template"
        //if (currentComponent != null) {
        //TagValueExpression tagTemplate = (TagValueExpression) currentComponent.getPassThroughAttributes(true).get("visu");
        //    UtilSBWPServletTools.getBeanByNamed("visualizacao", String.class, true);
        //   UtilSBWPServletTools.getBeanByNamed("valor", String.class, true);
        //}
        //return "/resources/modelo/objeto/include/itemSimples.xhtml"; // Um fallback para evitar erro
    }

    public String getIcone(ItfBeanSimples pItem) {
        if (isItemPossuiIcone(pItem)) {
            return pItem.getCampoInstanciadoByAnotacao(FabTipoAtributoObjeto.ICONE).getValor().toString();
        }
        return null;
    }

    public String getCor(ItfBeanSimples pItem) {
        if (isItemPossuiCor(pItem)) {
            return pItem.getCampoInstanciadoByAnotacao(FabTipoAtributoObjeto.COR).getValor().toString();
        }
        return null;
    }

    public String getImagemURl(ItfBeanSimples pItem) {
        if (isItemPossuiImagemPequena(pItem)) {
            return pItem.getImgPequena();
        }
        return null;
    }

    public boolean isItemPossuiIcone(ItfBeanSimples pItem) {

        return pItem.isTemCampoAnotado(FabTipoAtributoObjeto.ICONE);
    }

    public boolean isItemPossuiImagemPequena(ItfBeanSimples pItem) {
        return pItem.isTemCampoAnotado(FabTipoAtributoObjeto.IMG_PEQUENA);
    }

    public boolean isItemPossuiCor(ItfBeanSimples pItem) {
        return pItem.isTemCampoAnotado(FabTipoAtributoObjeto.COR);
    }

    public boolean visualizarEstiloStatus(ItfBeanSimples pValor) {
        return (isItemPossuiIcone(pValor) && isItemPossuiCor(pValor)) && !pValor.isTemImagemPequenaAdicionada();
    }

    public boolean visualizarEstiloNomeImagem(ItfBeanSimples pValor) {
        return !(isItemPossuiIcone(pValor) && isItemPossuiCor(pValor)) && pValor.isTemImagemPequenaAdicionada();
    }

    public boolean visualizarEstiloNomeSimples(ItfBeanSimples pValor) {
        return !(isItemPossuiIcone(pValor) && isItemPossuiCor(pValor)) && !pValor.isTemImagemPequenaAdicionada();
    }
}
