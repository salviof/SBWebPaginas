/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.super_bits.tags.metodo;

import java.io.IOException;
import javax.el.MethodExpression;
import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.TagAttribute;
import javax.faces.view.facelets.TagConfig;
import javax.faces.view.facelets.TagHandler;

/**
 *
 * @author salvioF
 */
public class MetodoComoParametro extends TagHandler {

    private final TagAttribute name;
    private final TagAttribute value;

    public MetodoComoParametro(TagConfig config) {
        super(config);
        this.name = this.getRequiredAttribute("nome");
        this.value = this.getRequiredAttribute("valor");
    }

    @Override
    public void apply(FaceletContext ctx, UIComponent uic) throws IOException {
        String nameStr = name.getValue(ctx);

        // The original value expression we get inside the Facelets tag, that's actually the method expression passed-in by the user.
        ValueExpression valueExpression = value.getValueExpression(ctx, Object.class);

        // A method expression that wraps the value expression and uses its own invoke method to get the value from the wrapped expression.
        MethodExpression methodExpression = new MetodoComoParametroAdaptador(valueExpression);

        // Using the variable mapper so the expression is scoped to the body of the Facelets tag. Since the variable mapper only accepts
        // value expressions, we once again wrap it by a value expression that directly returns the method expression.
        ctx.getVariableMapper().setVariable(nameStr, ctx.getExpressionFactory().createValueExpression(methodExpression, MethodExpression.class));
    }

}
