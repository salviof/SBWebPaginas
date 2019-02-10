/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.util;

import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreDataHora;
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

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {

        try {
            return UtilSBCoreDataHora.converteStringDD_MM_YYYYEmData(value);

        } catch (Throwable t) {
            return new Date();
        }

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return value.toString();
    }

}
