/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.util.validadores;

import java.util.Date;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author desenvolvedor
 */
@FacesValidator("org.super_bits.view.validadores.genericoDatas")
public class ValidadorGenericoData extends ValidadorGenericoAbstrato<Date> {

    @Override
    public void validate(FacesContext context, UIComponent component, Date value) throws ValidatorException {
        super.validate(context, component, value); //To change body of generated methods, choose Tools | Templates.
    }

}
