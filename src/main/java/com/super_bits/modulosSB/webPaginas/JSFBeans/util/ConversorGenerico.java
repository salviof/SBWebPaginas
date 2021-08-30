package com.super_bits.modulosSB.webPaginas.JSFBeans.util;

import com.sun.faces.facelets.el.TagValueExpression;
import com.sun.faces.renderkit.html_basic.MenuRenderer;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimplesSomenteLeitura;
import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.componentes.FabCompVisualSeletorItem;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWP_JSFTools;
import java.util.Optional;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import org.primefaces.component.selectmanymenu.SelectManyMenuRenderer;

import org.super_bits.tags.inputGenerico.InputGenerico;

@FacesConverter(value = "conversorGenerico")
public class ConversorGenerico extends ConversorSB {

    public ConversorGenerico() {
        super();
    }

    @Override
    public Object getAsObject(FacesContext ctx, UIComponent component,
            String value) {

        try {
            if (value != null) {
                // ItfBeanSimples entity =(ItfBeanSimples)
                // getAttributesFrom(component).get(value);
                // System.out.println(entity.getNomeCurto());
                // System.out.println("Convertido OBJ:" +value
                EntityManager em = UtilSBPersistencia.getEMDoContexto();

                Object resposta = this.getAttributosEmComponente(component).get(value);

                if (resposta == null) {
                    //ItfCampoInstanciado objeto = ctx.getApplication().evaluateExpressionGet(ctx, "#{registro}", ItfCampoInstanciado.class);
                    System.out.println(component + "objeto não Encontrado tentando converter string para objeto, executando alternativa 2.0 >" + value);
                    //Em alguns casos os atributos adicionados no componentes são perdidos, a tecnica abaixo é um alternativa em versão beta.
                    ItfCampoInstanciado campoInstanciado = null;

                    try {
                        TagValueExpression tag = (TagValueExpression) component.getPassThroughAttributes().get("campoInstanciado");
                        campoInstanciado = (ItfCampoInstanciado) tag.getValue(ctx.getELContext());

                    } catch (Throwable t) {
                        InputGenerico componente = UtilSBWP_JSFTools.getInputGenericoDoComponente(component);
                        if (componente == null) {
                            throw new UnsupportedOperationException("Erro obtendo campo instanciado do input para o conversorgenerico");
                        }
                        campoInstanciado = componente.getRegistro();
                    }
                    if (campoInstanciado == null) {
                        throw new UnsupportedOperationException("Erro obtendo campo instanciado do input para o conversorgenerico");
                    }

                    if (campoInstanciado.getValor() != null) {
                        if (campoInstanciado.getValor().toString().equals(value)) {
                            return campoInstanciado.getValor();
                        }
                    }
                    if (campoInstanciado.isUmValorComLista()) {
                        Optional valor = null;
                        if (campoInstanciado.isUmValorMultiploComLista()) {
                            valor = campoInstanciado.getComoSeltorItens().getCampoSeletorItens().getOrigem().stream().filter(item -> item.toString().equals(value)).findFirst();
                        } else {
                            if (campoInstanciado.isUmValorComLista()) {
                                valor = campoInstanciado.getComoCampoSeltorItem().getSeletor().getOrigem().stream().filter(item -> item.toString().equals(value)).findFirst();
                                if (valor == null || !valor.isPresent()) {
                                    campoInstanciado.getComoCampoSeltorItem().getSeletor().limparSelecao();
                                    campoInstanciado.getComoCampoSeltorItem().getSeletor().reloadOrigem();
                                    valor = campoInstanciado.getComoCampoSeltorItem().getSeletor().getOrigem().stream().filter(item -> item.toString().equals(value)).findFirst();
                                }
                            }
                        }

                        if (valor != null && valor.isPresent()) {
                            return valor.get();
                        } else {
                            if (campoInstanciado.getComponenteVisualPadrao().equals(FabCompVisualSeletorItem.AUTO_COMPLETE.getRegistro())) {
                                /// TODO buscar autocomplete no  objeto UIComponent
                                return null;
                            } else {

                                throw new UnsupportedOperationException("O Conversor de string para BeanSimples falhou mizeravelmente" + "na lista origem existiam " + campoInstanciado.getComoCampoSeltorItem().getSeletor().getOrigem().size());
                            }
                        }
                    }

                }
                if (em != null) {
                    if (resposta != null) {
                        //   resposta = UtilSBPersistencia.loadEntidade((ItfBeanSimplesSomenteLeitura) resposta, em);
                    }
                }
                return resposta;

            }

        } catch (Exception e) {

            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro no conversor Generico, obtendo:" + value, e);
        }
        System.out.println("retornouNulo para " + value);
        return null;

    }

    @Override
    public String getAsString(FacesContext ctx, UIComponent component,
            Object value) {

        try {

            if (value != null && !"".equals(value)) {
                ItfBeanSimplesSomenteLeitura item = (ItfBeanSimplesSomenteLeitura) value;
                String valorEmString = item.toString();
                value.toString();
                if (item.getId() != 0) {
                    this.addAtributoEmComponente(component, valorEmString, item);

                    if (item.getId() != 0) {
                        System.out.println(valorEmString);
                        return valorEmString;
                    }
                    System.out.println("ExisteValor, mas o nome cuto é nulo");
                    return valorEmString;
                }
            }
            //System.out.println("OBJETO IMPROPRIO PARA CONVERSÃO" + value);
            return "";

        } catch (Exception e) {
            System.out.println("Erro de conversao STR");
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro de conversão generica As String FacesContext:" + ctx.toString() + "-- Component" + component.toString() + " objeto" + value, e);

        }
        System.out.println("Nao Convertido STRSTR:" + value);
        return "";

    }

}
