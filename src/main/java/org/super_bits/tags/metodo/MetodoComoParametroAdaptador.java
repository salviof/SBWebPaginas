/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.super_bits.tags.metodo;

import javax.el.ELContext;
import javax.el.MethodExpression;
import javax.el.MethodInfo;
import javax.el.ValueExpression;

/**
 *
 * @author salvioF
 */
public class MetodoComoParametroAdaptador extends MethodExpression {

    private static final long serialVersionUID = 1L;

    private final ValueExpression valueExpression;

    public MetodoComoParametroAdaptador(ValueExpression valueExpression) {
        this.valueExpression = valueExpression;
    }

    @Override
    public Object invoke(ELContext elc, Object[] os) {
        return valueExpression.getValue(elc);
    }

    @Override
    public MethodInfo getMethodInfo(ELContext context) {
        return new MethodInfo(null, valueExpression.getExpectedType(), null);
    }

    @Override
    public boolean isLiteralText() {
        return false;
    }

    @Override
    public int hashCode() {
        return valueExpression.hashCode();
    }

    @Override
    public String getExpressionString() {
        return valueExpression.getExpressionString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj instanceof MetodoComoParametroAdaptador) {
            return ((MetodoComoParametroAdaptador) obj).getValueExpression().equals(valueExpression);
        }

        return false;
    }

    public ValueExpression getValueExpression() {
        return valueExpression;
    }

}
