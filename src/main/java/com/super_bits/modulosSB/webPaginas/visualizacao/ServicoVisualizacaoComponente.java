/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.super_bits.modulosSB.webPaginas.visualizacao;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoEntidadeSimples;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoStatus;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoTemIcone;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author salvio
 */
@Named
@RequestScoped
public class ServicoVisualizacaoComponente {

    private static Map<ComoEntidadeSimples, TIPO_VISUALIZACAO> MAPA_ENTIDADES = new HashMap<>();

    enum TIPO_VISUALIZACAO {
        APENAS_NOME,
        IMAGEM,
        ICONE,
        ICONE_COLORIDO,
        ICONE_COLORIDO_NOME,

    }

    public synchronized TIPO_VISUALIZACAO getTipoVisualizacao(ComoEntidadeSimples pItem) {
        if (MAPA_ENTIDADES.containsKey(pItem)) {
            return MAPA_ENTIDADES.get(pItem);
        }

        if (!(isItemPossuiIcone(pItem) && isItemPossuiCor(pItem)) && pItem.isTemImagemPequenaAdicionada()) {
            if (pItem.getImgPequena() != null) {

                MAPA_ENTIDADES.put(pItem, TIPO_VISUALIZACAO.IMAGEM);
                return MAPA_ENTIDADES.get(pItem);
            }
        }

        if (pItem instanceof ComoTemIcone && pItem.isTemCampoAnotado(FabTipoAtributoObjeto.COR) && pItem instanceof ComoStatus) {
            if (!(((ComoTemIcone) pItem).getIcone() != null)) {
                if (!((ComoTemIcone) pItem).getIcone().isEmpty()) {

                    MAPA_ENTIDADES.put(pItem, TIPO_VISUALIZACAO.ICONE_COLORIDO_NOME);
                    return MAPA_ENTIDADES.get(pItem);

                }
            }
        }

        if (pItem instanceof ComoTemIcone && pItem.isTemCampoAnotado(FabTipoAtributoObjeto.COR)) {
            if (!(((ComoTemIcone) pItem).getIcone() != null)) {
                if (!((ComoTemIcone) pItem).getIcone().isEmpty()) {

                    MAPA_ENTIDADES.put(pItem, TIPO_VISUALIZACAO.ICONE_COLORIDO);
                    return MAPA_ENTIDADES.get(pItem);

                }
            }
        }

        if (pItem instanceof ComoTemIcone) {
            if (!(((ComoTemIcone) pItem).getIcone() != null)) {
                if (!((ComoTemIcone) pItem).getIcone().isEmpty()) {

                    MAPA_ENTIDADES.put(pItem, TIPO_VISUALIZACAO.ICONE);
                    return MAPA_ENTIDADES.get(pItem);
                }
            }
        }

        MAPA_ENTIDADES.put(pItem, TIPO_VISUALIZACAO.APENAS_NOME);
        return MAPA_ENTIDADES.get(pItem);

    }

    public String getVisualizacaoCampo(ComoEntidadeSimples pVisualizacao) {
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

    public String getIcone(ComoEntidadeSimples pItem) {

        if (isItemPossuiIcone(pItem)) {

            if (pItem.getCampoInstanciadoByAnotacao(FabTipoAtributoObjeto.ICONE).getValor() == null) {
                return null;
            }
            return pItem.getCampoInstanciadoByAnotacao(FabTipoAtributoObjeto.ICONE).getValor().toString();
        }
        return null;
    }

    public String getCor(ComoEntidadeSimples pItem) {
        if (isItemPossuiCor(pItem)) {
            if (pItem.getCampoInstanciadoByAnotacao(FabTipoAtributoObjeto.COR).getValor() == null) {
                return null;
            }
            return pItem.getCampoInstanciadoByAnotacao(FabTipoAtributoObjeto.COR).getValor().toString();
        }
        return null;
    }

    public String getImagemURl(ComoEntidadeSimples pItem) {
        if (isItemPossuiImagemPequena(pItem)) {
            return pItem.getImgPequena();
        }
        return null;
    }

    public boolean isItemPossuiIcone(ComoEntidadeSimples pItem) {

        return pItem.isTemCampoAnotado(FabTipoAtributoObjeto.ICONE);
    }

    public String getEntidadeSimplesNome(ComoEntidadeSimples pValor) {
        String conteudo = (String) pValor.getCampoInstanciadoByAnotacao(FabTipoAtributoObjeto.NOME).getValorTextoFormatado();
        if (conteudo == null) {
            return pValor.getNome();
        }
        return conteudo;
    }

    public boolean isItemPossuiImagemPequena(ComoEntidadeSimples pItem) {

        if (pItem.getImgPequena() == null) {
            return false;
        }
        return pItem.isTemImagemPequenaAdicionada();
    }

    public boolean isItemPossuiCor(ComoEntidadeSimples pItem) {
        return pItem.isTemCampoAnotado(FabTipoAtributoObjeto.COR);
    }

    public boolean visualizarEstiloStatus(ComoEntidadeSimples pValor) {
        return getTipoVisualizacao(pValor).equals(TIPO_VISUALIZACAO.ICONE_COLORIDO_NOME);
    }

    public boolean visualizarEstiloNomeImagem(ComoEntidadeSimples pValor) {
        return getTipoVisualizacao(pValor).equals(TIPO_VISUALIZACAO.IMAGEM);
    }

    public boolean visualizarEstiloNomeSimples(ComoEntidadeSimples pValor) {
        return getTipoVisualizacao(pValor).equals(TIPO_VISUALIZACAO.APENAS_NOME);
    }

    public boolean visualizarEstiloIcone(ComoEntidadeSimples pValor) {
        return getTipoVisualizacao(pValor).equals(TIPO_VISUALIZACAO.ICONE);

    }
}
