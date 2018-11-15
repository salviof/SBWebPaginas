/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.super_bits.tags.componenteAdaptador;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import java.io.IOException;
import java.util.Map;
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
public class ExibicaoComponentePropriedades extends TagHandler {

    private final TagAttribute componente;
    private final TagAttribute variavelXhtml;
    private final TagAttribute teste1;
    private final TagAttribute teste2;

    public ExibicaoComponentePropriedades(TagConfig config) {
        super(config);
        this.componente = this.getRequiredAttribute("componente");
        this.variavelXhtml = this.getRequiredAttribute("variavelXhtml");
        this.teste1 = this.getRequiredAttribute("teste1");
        this.teste2 = this.getRequiredAttribute("teste2");
        //this.name = this.getRequiredAttribute("nome");
        //this.value = this.getRequiredAttribute("valor");
    }

    @Override
    public void apply(FaceletContext ctx, UIComponent uic) throws IOException {

        String nomeDaVariavel = variavelXhtml.getValue(ctx);
        // The original value expression we get inside the Facelets tag, that's actually the method expression passed-in by the user.
        ValueExpression valueExpression = componente.getValueExpression(ctx, ItfCampoInstanciado.class);
        ItfCampoInstanciado campo = (ItfCampoInstanciado) valueExpression.getValue(ctx); // A method expression that wraps the value expression and uses its own invoke method to get the value from the wrapped expression.

        ValueExpression expressaoTeste1 = teste1.getValueExpression(ctx, String.class);
        ValueExpression expressaoTeste2 = teste1.getValueExpression(ctx, String.class);

        String valorteste1 = (String) expressaoTeste1.getValue(ctx);
        String valorteste2 = (String) expressaoTeste2.getValue(ctx);
        System.out.println(valorteste1);
        System.out.println(valorteste2);

        Map<String, Object> mapaAtributosObjeto = uic.getAttributes();
        UIComponent inputpai = uic.getParent();
        if (inputpai != null) {
            Map<String, Object> mapaAtributos = inputpai.getAttributes();
        }
        if (campo == null) {

            ValueExpression vlteste2 = componente.getValueExpression(ctx, ItfCampoInstanciado.class);
            ValueExpression vlteste3 = componente.getValueExpression(ctx, ItfCampoInstanciado.class);
            ValueExpression vlteste4 = componente.getValueExpression(ctx, ItfCampoInstanciado.class);
            ValueExpression vlteste5 = componente.getValueExpression(ctx, ItfCampoInstanciado.class);
            ValueExpression vlteste6 = componente.getValueExpression(ctx, ItfCampoInstanciado.class);

            ValueExpression VEItem = ctx.getVariableMapper().resolveVariable("item");
            if (VEItem != null) {
                campo = (ItfCampoInstanciado) VEItem.getValue(ctx);
            }
        }

        String xhtml = "input/campo/campoNaoDeterminado.xhtml";
        if (campo != null) {
            System.out.println("CAMPO" + campo.getLabel());
            xhtml = campo.getComponenteVisualPadrao().getXhtmlJSF();
        }
        // Using the variable mapper so the expression is scoped to the body of the Facelets tag. Since the variable mapper only accepts
        // value expressions, we once again wrap it by a value expression that directly returns the method expression.

        if (campo != null) {
            System.out.println(xhtml);
            ctx.getVariableMapper().setVariable(nomeDaVariavel, ctx.getExpressionFactory().createValueExpression(xhtml, String.class));
        }
    }

}
