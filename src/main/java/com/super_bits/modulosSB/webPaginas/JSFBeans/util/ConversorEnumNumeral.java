/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.util;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;
import javax.persistence.EnumType;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ComoFabrica;

/**
 *
 *
 * @author SalvioF
 */
@FacesConverter(value = "conversorEnumNumeral", forClass = EnumType.class)
public class ConversorEnumNumeral extends ConversorSB {

    private final String ATRIBUTO_CLASSE_FABRICA = "CLASSE_FABRICA";

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        try {
            if (value != null) {
                return Enum.valueOf((Class) getAttributoDoComponente(component, ATRIBUTO_CLASSE_FABRICA), value);
            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro convertendo String em enum", t);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        try {
            Class enumFabrica = (Class) getAttributoDoComponente(component, ATRIBUTO_CLASSE_FABRICA);
            if (enumFabrica == null) {
                addAtributoEmComponente(component, ATRIBUTO_CLASSE_FABRICA, value.getClass());
            } else {

            }

            if (value != null && value instanceof ComoFabrica) {
                return (value).toString();
            } else {
                throw new UnsupportedOperationException("O valor que está utilizando ConversorEnum não é do tipo enum");
            }

        } catch (Throwable t) {

            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "", t);
            return null;

        }
    }
}
