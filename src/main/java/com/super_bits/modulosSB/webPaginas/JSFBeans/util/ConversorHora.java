/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author salvio
 */
@FacesConverter(value = "conversorHora")
public class ConversorHora extends ConversorSB {

    private static final SimpleDateFormat FORMATADOR_DATA = new SimpleDateFormat("HH:mm");

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {

        try {
            if (value == null) {
                return null;
            }
            if (value.contains("_")) {
                // Bug primefaces
                return new Date();
            }

            return FORMATADOR_DATA.parse(value);

        } catch (Throwable t) {
            return null;
        }

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Map<String, Object> atributos = component.getAttributes();
        String strbuild = FORMATADOR_DATA.format(value);
        return strbuild;

    }

}
