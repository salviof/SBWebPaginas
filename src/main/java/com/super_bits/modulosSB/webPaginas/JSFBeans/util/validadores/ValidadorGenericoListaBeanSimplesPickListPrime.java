/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.util.validadores;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.ValidatorException;
import org.primefaces.model.DualListModel;

/**
 *
 * @author salviofurbino
 * @since 17/04/2019
 * @version 1.0
 */
@FacesValidator("org.super_bits.view.validadores.listaBeanSimplesPickPrimer")
public class ValidadorGenericoListaBeanSimplesPickListPrime extends ValidadorGenericoAbstrato<DualListModel> {

    @Override
    public void validate(FacesContext context, UIComponent component, DualListModel value) throws ValidatorException {
        if (value instanceof DualListModel) {
            super.validate(context, component, value); //To change body of generated methods, choose Tools | Templates.
        } else {
            if (!SBCore.isEmModoProducao()) {
                System.out.println("Validando como String");
            }
        }
    }

}
