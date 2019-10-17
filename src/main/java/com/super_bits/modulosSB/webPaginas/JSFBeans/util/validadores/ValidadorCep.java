/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.util.validadores;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;

import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringValidador;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.webPaginas.JSFBeans.util.UtilSBWPMensagensJSF;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWP_JSFTools;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author desenvolvedor
 */
@FacesValidator("validadorCEP")
public class ValidadorCep implements Validator<String> {

    @Override
    public void validate(FacesContext fc, UIComponent uic, String pCep) throws ValidatorException {
        ItfCampoInstanciado campoInstanciado = UtilSBWP_JSFTools.getInputGenericoDoComponente(uic).getRegistro();
//        SBCore.getServicoLocalizacao().getImplementacaoPadraoApiCep().
//UtilSBCoreCEP.cepExiste(pCep);

        if (!SBCore.getServicoLocalizacao().getImplementacaoPadraoApiCep().getImplementacaoDoContexto().cepExiste(pCep)) {
            if (campoInstanciado != null) {
                if (campoInstanciado.isObrigatorio() || UtilSBCoreStringValidador.isNAO_NuloNemBranco(pCep)) {
                    if (campoInstanciado.getComoCampoLocalizacao().isCepEncontradoObrigatorio()) {
                        throw new ValidatorException(UtilSBWPMensagensJSF.criarMensagemJSF(FacesMessage.SEVERITY_ERROR, "O cep não foi encontrado"));
                    }
                }
            } else {
                SBCore.getCentralDeMensagens().enviarMsgAlertaAoUsuario("O Cep " + pCep + " não foi Encontrado!");
            }
        }
    }

}
