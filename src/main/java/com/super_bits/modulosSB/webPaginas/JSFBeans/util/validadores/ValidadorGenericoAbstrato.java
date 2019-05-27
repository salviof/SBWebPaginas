/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.util.validadores;

import com.sun.faces.facelets.el.TagValueExpression;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringValidador;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreValidacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ErroValidacao;
import com.super_bits.modulosSB.SBCore.modulos.view.telas.LayoutTelaAreaConhecida;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWP_JSFTools;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

/**
 *
 * @author desenvolvedor
 */
public abstract class ValidadorGenericoAbstrato<T> implements Validator<T> {

    @Override
    public void validate(FacesContext context, UIComponent component, T value) throws ValidatorException {
        ItfCampoInstanciado campoInstanciado = null;

        try {
            TagValueExpression tag = (TagValueExpression) component.getPassThroughAttributes().get("campoInstanciado");
            campoInstanciado = (ItfCampoInstanciado) tag.getValue(context.getELContext());

        } catch (Throwable t) {

        }
        if (campoInstanciado == null) {
            campoInstanciado = UtilSBWP_JSFTools.getInputGenericoDoComponente(component).getRegistro();
        }
        if (campoInstanciado != null) {
            boolean umNovoRegistro = campoInstanciado.getObjetoDoAtributo().getId() == 0;
            List<String> validacao = UtilSBCoreValidacao.gerarMensagensValidacao(campoInstanciado, value, umNovoRegistro, !campoInstanciado.validarCampo(value));

            if (campoInstanciado.isTemValidadacaoLogica()) {
                try {
                    if (umNovoRegistro) {
                        campoInstanciado.getValidacaoLogica().validarModoNovo(value);
                    } else {
                        campoInstanciado.getValidacaoLogica().validarModoEdicao(value);
                    }
                } catch (ErroValidacao tt) {
                    validacao.add(tt.getMensagemAoUsuario());
                } catch (Throwable t) {
                    SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro validando " + campoInstanciado.getNomeCompostoIdentificador(), t);
                }

            }

            if (!validacao.isEmpty()) {

                exibirMensagemValidacao(component, validacao.get(0));
            }
        }
    }

    public void exibirMensagemValidacao(UIComponent component, String pMmensagem) {
        FacesMessage mensagemErro = new FacesMessage();
        mensagemErro.setSummary(pMmensagem);
        mensagemErro.setSeverity(FacesMessage.SEVERITY_ERROR);
        // PgUtil paninaUtil = new PgUtil();
        // String idCompoentePai = paninaUtil.getNomeIdPainelGroupInputSB(component);
        if (!UtilSBCoreStringValidador.isNuloOuEmbranco(true)) {
            //         PrimeFaces.current().ajax().update(idCompoentePai);
            //PrimeFaces.current().scrollTo(idCompoentePai);

            throw new ValidatorException(mensagemErro);
        } else {
            SBCore.getCentralDeMensagens().enviarMsgErroAoUsuario(mensagemErro.getSummary());
            UtilSBWP_JSFTools.atualizaPorId(LayoutTelaAreaConhecida.AREA_MENSAGEM_INTERFACE);
            throw new ValidatorException(mensagemErro);
        }
    }

}
