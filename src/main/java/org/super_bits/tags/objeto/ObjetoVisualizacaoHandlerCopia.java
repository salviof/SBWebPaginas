/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.super_bits.tags.objeto;

import com.super_bits.modulosSB.webPaginas.util.UtilSBWPServletTools;
import java.io.IOException;
import javax.el.ELContext;
import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.event.ComponentSystemEventListener;
import javax.faces.view.facelets.ComponentHandler;
import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.TagAttribute;
import javax.faces.view.facelets.TagConfig;
import javax.faces.view.facelets.TagHandler;

/**
 *
 * @author salvioF
 */
public class ObjetoVisualizacaoHandlerCopia extends TagHandler {

    private final TagAttribute visualizacao;

    public ObjetoVisualizacaoHandlerCopia(TagConfig config) {
        super(config);
        this.visualizacao = this.getRequiredAttribute("visualizacao");
    }

    @Override
    public void apply(FaceletContext ctx, UIComponent parent) throws IOException {
        FacesContext facesContext = FacesContext.getCurrentInstance();

        if (ComponentHandler.isNew(parent)) { // Só aplica se o componente for novo
            UIComponent compositeParent = UIComponent.getCompositeComponentParent(parent);
            Object teste = resolveELExpression(visualizacao.getValue());
            if (compositeParent != null) {
                Object templateValue = compositeParent.getAttributes().get("template");

                if (templateValue != null) {
                    System.out.println("Template encontrado: " + templateValue);
                    try {
                        // Inclui o facelet diretamente no parent, similar ao ui:include
                        ctx.includeFacelet(parent, templateValue.toString());
                    } catch (Exception e) {
                        throw new IOException("Erro ao incluir o facelet: " + "/resources/modelo/objeto/include/itemSimples.xhtml", e);
                    }
                } else {
                    System.out.println("Template está NULL!");
                }
            } else {
                System.out.println("Não é um Composite Component!");
            }
        }

    }

    private Object resolveELExpression(String expression) {
        if (expression == null || !expression.contains("#{")) {
            return expression; // Retorna direto se não for EL
        }

        FacesContext facesContext = FacesContext.getCurrentInstance();
        ELContext elContext = facesContext.getELContext();
        ValueExpression ve = facesContext.getApplication()
                .getExpressionFactory()
                .createValueExpression(elContext, expression, Object.class);

        return ve.getValue(elContext); // Retorna o valor resolvido
    }
// Listener interno para processar a inclusão na fase correta

    private static class VisualizacaoListener implements ComponentSystemEventListener {

        private final FaceletContext ctx;

        public VisualizacaoListener(FaceletContext ctx) {
            this.ctx = ctx;
        }

        @Override
        public void processEvent(ComponentSystemEvent event) throws AbortProcessingException {
            UIComponent component = (UIComponent) event.getComponent();
            UIComponent componente = UIComponent.getCompositeComponentParent(component);
            componente.getPassThroughAttributes(true);

            TagAttribute visualizacaoAttr = (TagAttribute) component.getAttributes().get("visualizacao");

            FacesContext facesContext = event.getFacesContext();
            ELContext elContext = facesContext.getELContext();
            ValueExpression ve1 = facesContext.getApplication()
                    .getExpressionFactory()
                    .createValueExpression(elContext, "#{valor}", Object.class);
            Object valor = ve1.getValue(elContext);

            Object caminhoVisualizacao;
            if (visualizacaoAttr.isLiteral()) {
                caminhoVisualizacao = visualizacaoAttr.getValue();
            } else {
                ValueExpression ve = visualizacaoAttr.getValueExpression(ctx, Object.class);
                caminhoVisualizacao = (Object) ve.getValue(ctx.getFacesContext().getELContext());
            }

            if (caminhoVisualizacao == null || caminhoVisualizacao.toString().trim().isEmpty()) {
                throw new IllegalStateException("Caminho de visualização é nulo ou vazio");
            }

            try {
                // Inclui o facelet no componente pai
                ctx.includeFacelet(component.getParent(), caminhoVisualizacao.toString());
            } catch (IOException e) {
                throw new javax.faces.event.AbortProcessingException("Erro ao incluir facelet: " + caminhoVisualizacao, e);
            }
        }
    }
}
