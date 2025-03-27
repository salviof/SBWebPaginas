/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package org.super_bits.tags.objeto;

import java.io.IOException;
import javax.el.ELContext;
import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.TagAttribute;
import org.super_bits.tags.inputGenerico.InputGenerico;

/**
 *
 * @author salvio
 */
public class DynamicIncludeComponent
        extends UIComponentBase {

    private String templatePath;
    private FaceletContext contextoFacelet;
    private TagAttribute visualizacao;

    @Override
    public String getFamily() {
        return "custom";
    }

    public void setContextoFacelet(FaceletContext contextoFaceler) {
        this.contextoFacelet = contextoFaceler;
    }

    public void setTemplatePath(String templatePath) {
        this.templatePath = templatePath;
    }

    public void setVisualizacao(TagAttribute visualizacao) {
        this.visualizacao = visualizacao;
    }

    @Override
    public void encodeBegin(FacesContext context) throws IOException {
        if (templatePath != null) {
            // Inclui o Facelet no momento certo do ciclo de vida
            InputGenerico generico = (InputGenerico) getAttributes().get("componentePrincipal");
            if (generico != null) {
                FacesContext facesContext = context;
                ELContext elContext = facesContext.getELContext();

                ValueExpression ve = facesContext.getApplication()
                        .getExpressionFactory()
                        .createValueExpression(elContext, templatePath, Object.class);
                UIComponent componente = UIComponent.getCompositeComponentParent(this);

                String caminho = ve.getValue(elContext).toString();

                contextoFacelet.includeFacelet(this, caminho);
            }
            String caminho = "/resources/modelo/objeto/include/itemSimples.xhtml";

            contextoFacelet.includeFacelet(this, caminho);
            //String templatePath = visualizacao.getValue(context.);
            // Incluir o Facelet usando ctx.includeFacelet(), que é a abordagem correta

        }
    }

}
