/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.util.validadores;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author SalvioF
 */
@FacesValidator("org.super_bits.view.validadores.senha")
public class ValidadorGenericoSenha extends ValidadorGenericoAbstrato<String> {

    @Override
    public void validate(FacesContext context, UIComponent component, String value) throws ValidatorException {

        super.validate(context, component, value);

    }

}
