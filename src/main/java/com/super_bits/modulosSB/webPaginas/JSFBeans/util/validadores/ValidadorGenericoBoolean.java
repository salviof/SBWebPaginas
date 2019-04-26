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
 * @author salviofurbino
 * @since 17/04/2019
 * @version 1.0
 */
@FacesValidator("org.super_bits.view.validadores.genericoBoolean")
public class ValidadorGenericoBoolean extends ValidadorGenericoAbstrato<Boolean> {

    @Override
    public void validate(FacesContext context, UIComponent component, Boolean value) throws ValidatorException {
        super.validate(context, component, value); //To change body of generated methods, choose Tools | Templates.

    }
}
