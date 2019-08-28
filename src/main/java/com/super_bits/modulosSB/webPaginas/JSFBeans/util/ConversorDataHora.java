/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author SalvioF
 */
@FacesConverter(value = "conversorDataHora")
public class ConversorDataHora extends ConversorSB {

    private static final SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

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
            return formatador.parse(value);

        } catch (Throwable t) {
            return null;
        }

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return formatador.format(value);

    }

}
