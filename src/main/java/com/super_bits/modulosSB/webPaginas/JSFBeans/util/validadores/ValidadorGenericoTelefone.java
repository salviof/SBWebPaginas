/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.util.validadores;

import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringTelefone;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.webPaginas.JSFBeans.util.UtilSBWPMensagensJSF;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author salvio
 */
@FacesValidator("org.super_bits.view.validadores.telefone")
public class ValidadorGenericoTelefone extends ValidadorGenericoString {

    @Override
    protected void validar(ItfCampoInstanciado campoInstanciado, UIComponent pComponent, boolean umNovoRegistro, String value) throws ValidatorException {
        super.validar(campoInstanciado, pComponent, umNovoRegistro, value); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        if (value.startsWith("+55")) {
            String tel = UtilCRCStringTelefone.gerarNumeroTelefoneInternacional(value);
            if (tel == null) {
                throw new ValidatorException(UtilSBWPMensagensJSF.criarMensagemJSF(FacesMessage.SEVERITY_ERROR, "Valor (" + value + ") inválido"));
            }
            if (value.contains("_")) {
                throw new ValidatorException(UtilSBWPMensagensJSF.criarMensagemJSF(FacesMessage.SEVERITY_ERROR, "Valor (" + value + ") inválido"));
            }
            if (value.contains("666")) {
                throw new ValidatorException(UtilSBWPMensagensJSF.criarMensagemJSF(FacesMessage.SEVERITY_ERROR, "Valor (" + value + ") inválido https://google.com"));
            }
        }
        System.out.println(value);

    }

    @Override
    public void validate(FacesContext context, UIComponent component, String value) throws ValidatorException {
        super.validate(context, component, value); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

}
