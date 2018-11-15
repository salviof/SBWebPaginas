/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.util;

import java.util.Map;
import javax.faces.component.UIComponent;
import javax.faces.convert.Converter;

/**
 *
 * @author SalvioF
 */
public abstract class ConversorSB implements Converter {

    protected void addAtributoEmComponente(UIComponent component, String nomeAtributo, Object pValor) {
        // codigo da empresa
        // como chave neste
        // caso
        //  System.out.println("Adcionando Key" + key);

        getAttributosEmComponente(component).put(nomeAtributo, pValor);

    }

    protected Map<String, Object> getAttributosEmComponente(UIComponent component) {
        Object resposta = component.getAttributes();
        if (resposta == null) {

            System.out.println("Nenum Atributo encontrado no componente");
        }
        return component.getAttributes();
    }

    protected Object getAttributoDoComponente(UIComponent component, String pNomeAtributo) {
        return getAttributosEmComponente(component).get(pNomeAtributo);
    }

}
