/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.util.validadores;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoEntidadeSimples;
import javax.faces.application.FacesMessage;
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
@FacesValidator("org.super_bits.view.validadores.beanSimples")
public class ValidadorGenericoBeanSimples extends ValidadorGenericoAbstrato<Object> {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (value instanceof ComoEntidadeSimples) {
            super.validate(context, component, value); //To change body of generated methods, choose Tools | Templates.
        } else {
            if (value == null) {
                ItfCampoInstanciado campoInstanciado = getCampoInstanciadoByComponent(context, component);
                if (campoInstanciado != null) {
                    switch (campoInstanciado.getTipoPrimitivoDoValor()) {

                        case ENTIDADE:
                        case OUTROS_OBJETOS:

                            boolean umNovoRegistro = campoInstanciado.getObjetoDoAtributo().getId() == null;
                            validar(campoInstanciado, component, umNovoRegistro, value);
                            break;
                        default:
                            lancarMensagemValidacao("O Tipo de atributo não é compatível com este tipo de validação");
                    }
                } else {
                    if (!SBCore.isEmModoProducao()) {
                        FacesMessage mensagemErro = new FacesMessage();
                        mensagemErro.setSummary("O atributo campo instanciado não foi encontrado");
                        mensagemErro.setSeverity(FacesMessage.SEVERITY_ERROR);
                    }
                }

            } else {
                FacesMessage mensagemErro = new FacesMessage();
                mensagemErro.setSummary("O tipo de objeto não é compatvel com este tipo de validação");
                mensagemErro.setSeverity(FacesMessage.SEVERITY_ERROR);
            }

        }
    }

}
