/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.util.validadores;

import com.sun.faces.facelets.el.TagValueExpression;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import java.lang.reflect.Field;
import java.util.Date;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.ValidatorException;
import javax.validation.constraints.Future;

/**
 *
 * @author desenvolvedor
 */
@FacesValidator("org.super_bits.view.validadores.genericoDatas")
public class ValidadorGenericoData extends ValidadorGenericoAbstrato<Date> {

    @Override
    public void validate(FacesContext context, UIComponent component, Date value) throws ValidatorException {
        super.validate(context, component, value); //To change body of generated methods, choose Tools | Templates.
        try {
            TagValueExpression tag = (TagValueExpression) component.getPassThroughAttributes().get("campoInstanciado");
            ItfCampoInstanciado campoInstanciado = (ItfCampoInstanciado) tag.getValue(context.getELContext());
            String nomecampo = campoInstanciado.getNomeCamponaClasse();
            Object obj = campoInstanciado.getObjetoDoAtributo();
            Field campo = obj.getClass().getDeclaredField(nomecampo);
            if (value != null) {

                if (campo.isAnnotationPresent(Future.class)) {
                    if (value.getTime() < new Date().getTime()) {
                        exibirMensagemValidacao(component, "A data precisa ser superior a data atual");
                    }
                }
            }
        } catch (NoSuchFieldException | SecurityException t) {

        }

    }

}
