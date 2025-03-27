/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package org.super_bits.tags.objeto;

import java.io.IOException;
import java.util.Map;
import javax.el.ELContext;
import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.event.ComponentSystemEventListener;
import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.TagAttribute;
import org.super_bits.tags.inputGenerico.InputGenerico;

/**
 *
 * @author salvio
 */
public class ListenerComponenteObjeto implements ComponentSystemEventListener {

    private final FaceletContext ctx;

    public ListenerComponenteObjeto(FaceletContext ctx) {
        this.ctx = ctx;
    }

    @Override
    public void processEvent(ComponentSystemEvent event) throws AbortProcessingException {

        DynamicIncludeComponent componenteDinamico = (DynamicIncludeComponent) event.getComponent();
        UIComponent componente = UIComponent.getCompositeComponentParent(componenteDinamico);
        if (componente instanceof InputGenerico) {
            componenteDinamico.getAttributes().put("componentePrincipal", componente);
        }
        //InputGenerico componente2 = (InputGenerico) UIComponent.getCompositeComponentParent(component.getParent());

        Map<String, Object> atributos = componente.getPassThroughAttributes(true);
        System.out.println(atributos);

    }

}
