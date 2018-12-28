/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.cicloDeVidaJSF;

import com.google.common.collect.Lists;
import com.sun.faces.facelets.compiler.UIInstructions;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlInputText;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import org.primefaces.PrimeFaces;

/**
 *
 * @author desenvolvedor
 */
public class CicloDeVidaPadraoJsf implements PhaseListener {

    @Override
    public void beforePhase(PhaseEvent event) {
        if (!SBCore.isEmModoProducao()) {
            System.out.println(event.getPhaseId());
            System.out.println(event.getFacesContext());
        }
    }

    @Override
    public void afterPhase(PhaseEvent event) {

        Collection<String> idsEnviado = event.getFacesContext().getPartialViewContext().getExecuteIds();
        Iterator<String> areasComMensagem = event.getFacesContext().getClientIdsWithMessages();
        StringBuilder idsAreaComMsg = new StringBuilder();
        String idFoco = null;

        if (!idsEnviado.isEmpty() && idsEnviado.size() < 4) {
            StringBuilder idsJavascript = new StringBuilder();
            idsEnviado.forEach(id
                    -> {
                String prefixo = "";
                if (idsJavascript.length() != 0) {
                    prefixo = ",";
                }
                if (!id.contains("@")) {
                    idsJavascript.append(prefixo + "'" + id + "'");
                }

            });
            if (idsJavascript.length() > 0) {
                if (PrimeFaces.current().isAjaxRequest()) {
                    PrimeFaces.current().executeScript("setTimeout(function(){focarComSelacaoAposAjax(" + idsJavascript + ");},300)");
                }
            }
        }
        while (areasComMensagem.hasNext()) {
            idFoco = areasComMensagem.next();
            if (idFoco != null) {
                UIComponent componente = event.getFacesContext().getViewRoot().findComponent(idFoco);
                if (componente instanceof HtmlInputText) {
                    PrimeFaces.current().executeScript("setTimeout(function(){$(PrimeFaces.escapeClientId('" + idFoco + "')).focus();},1000)");
                    break;
                }
            }

        }

    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.ANY_PHASE;
    }

    private void depurarArvoreComponentes(UIComponent component) {

        if (component != null) {
            for (UIComponent child : component.getChildren()) {
                // Display component ID and its type
                System.out.println("+ " + child.getId() + " [" + child.getClass() + "]");
                System.out.println(child.getClass().getSimpleName());
                System.out.println(child.getFamily());
                System.out.println(child.getRendererType());
                System.out.println(child.getValueExpression("mask"));
                if (child instanceof UIInstructions) {
                    UIInstructions uiInst = (UIInstructions) child;
                    for (UIComponent componente : uiInst.getChildren()) {
                        System.out.println(componente.getRendererType());

                    }
                }
                if (child instanceof HtmlInputText) {

                    HtmlInputText inputText = (HtmlInputText) child;
                    System.out.println(inputText.getValue());
                    Object valor = inputText.getValue();
                    if (valor instanceof Double) {
                        Double val = (Double) valor;
                        if (val == 0) {
                            SBCore.enviarAvisoAoUsuario("zero:" + child.getClientId());
                        }
                    }
                }
                // If component has a given ID then check if you can hide it

                // Process next node
                depurarArvoreComponentes(child);
            }
        }
    }
}
