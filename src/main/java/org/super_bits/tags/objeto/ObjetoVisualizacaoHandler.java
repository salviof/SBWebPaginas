/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.super_bits.tags.objeto;

import java.io.IOException;
import javax.faces.component.UIComponent;
import javax.faces.event.ListenerFor;
import javax.faces.event.PostAddToViewEvent;
import javax.faces.event.PostConstructViewMapEvent;
import javax.faces.event.PreRenderComponentEvent;
import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.TagAttribute;
import javax.faces.view.facelets.TagConfig;
import javax.faces.view.facelets.TagHandler;

/**
 *
 * @author salvioF
 */
@ListenerFor(systemEventClass = PostAddToViewEvent.class)
public class ObjetoVisualizacaoHandler extends TagHandler {

    private final TagAttribute visualizacao;

    public ObjetoVisualizacaoHandler(TagConfig config) {
        super(config);
        this.visualizacao = this.getRequiredAttribute("visualizacao");
    }

    @Override
    public void apply(FaceletContext ctx, UIComponent parent) throws IOException {
        if (parent == null || visualizacao == null) {
            return;
        }

        // Os objetos #{valor} ou {registro} não estão disponíveis em nenhuma etapa de reinderização
        DynamicIncludeComponent component = new DynamicIncludeComponent();
        component.setTemplatePath(visualizacao.getValue());
        component.setContextoFacelet(ctx);
        component.setVisualizacao(visualizacao);
        component.subscribeToEvent(PostConstructViewMapEvent.class, new ListenerComponenteObjeto(ctx));
        parent.getChildren().add(component); // Adiciona o

    }

}
