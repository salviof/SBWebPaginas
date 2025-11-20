/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.util;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author salvio
 */
@FacesConverter(value = "conversorTelefoneGenerico")
public class ConversorTelefoneGenerico extends ConversorSB {

    @Override
    public Object getAsObject(FacesContext ctx, UIComponent comp, String value) {
        if (value == null) {
            return null;
        }

        // extrai apenas os dígitos
        String digits = value.replaceAll("\\D", "");

        // caso campo vazio ou só +55
        if (digits.length() <= 2) {
            return null;
        }

        return digits; // salva só os dígitos
    }

    @Override
    public String getAsString(FacesContext ctx, UIComponent comp, Object value) {
        if (value == null) {
            return "";
        }

        String digits = value.toString().replaceAll("\\D", "");

        // sem país → assume +55
        if (digits.length() == 10) {
            // fixo
            return "+55 (" + digits.substring(0, 2) + ") "
                    + digits.substring(2, 6) + "-"
                    + digits.substring(6);
        }

        if (digits.length() == 11) {
            // celular
            return "+55 (" + digits.substring(0, 2) + ") "
                    + digits.substring(2, 7) + "-"
                    + digits.substring(7);
        }

        return value.toString();
    }

}
