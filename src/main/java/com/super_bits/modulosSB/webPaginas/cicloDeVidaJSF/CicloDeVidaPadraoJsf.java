/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.cicloDeVidaJSF;

import com.sun.faces.facelets.compiler.UIInstructions;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import java.util.Collection;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;
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

    private final static GambiarraNovoCiclodetectado GAMBIARRA = new GambiarraNovoCiclodetectado();

    @Override
    public void beforePhase(PhaseEvent event) {

        if (!SBCore.isEmModoProducao()) {
            System.out.println(event.getPhaseId());
            System.out.println(event.getFacesContext());
            System.out.println(event.getPhaseId().getName());

        }
    }

    public static class GambiarraNovoCiclodetectado {

        private Timer timer;
        private boolean liberado = true;

        public GambiarraNovoCiclodetectado() {

        }

        class RemindTask extends TimerTask {

            public void run() {
                liberado = true;
                timer.cancel(); //Terminate the timer thread
            }
        }

        public boolean isliberado() {
            if (liberado) {
                timer = new Timer();
                timer.purge();
                timer.schedule(new RemindTask(), 2 * 1000);
                liberado = false;
                return true;
            } else {
                return false;
            }
        }

        public void liberar() {
            timer.cancel(); //Terminate the timer thread
            liberado = true;

        }

    }

    @Override
    public void afterPhase(PhaseEvent event) {
        if (GAMBIARRA.isliberado()) {
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
                        PrimeFaces.current().executeScript("setTimeout(function(){$(PrimeFaces.escapeClientId('" + idFoco + "')).focus();},2000)");
                        break;
                    }
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
