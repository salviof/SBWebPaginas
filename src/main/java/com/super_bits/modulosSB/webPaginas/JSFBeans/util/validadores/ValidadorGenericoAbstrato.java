/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.util.validadores;

import com.sun.faces.facelets.el.TagValueExpression;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringValidador;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreValidacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampoValorLogico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ErroValidacao;
import com.super_bits.modulosSB.SBCore.modulos.view.telas.LayoutTelaAreaConhecida;
import com.super_bits.modulosSB.SBCore.modulos.view.widgetsFormulario.WidgetsFormulario;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.declarados.util.PgUtil;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWP_JSFTools;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import org.primefaces.PrimeFaces;

/**
 *
 * @author desenvolvedor
 */
public abstract class ValidadorGenericoAbstrato<T> implements Validator<T> {

    protected ItfCampoInstanciado getCampoInstanciadoByComponent(FacesContext context, UIComponent component) {
        ItfCampoInstanciado campoInstanciado = null;
        try {
            TagValueExpression tag = (TagValueExpression) component.getPassThroughAttributes().get("campoInstanciado");
            campoInstanciado = (ItfCampoInstanciado) tag.getValue(context.getELContext());

        } catch (Throwable t) {

        }

        if (campoInstanciado == null) {
            campoInstanciado = UtilSBWP_JSFTools.getInputGenericoDoComponente(component).getRegistro();
        }
        return campoInstanciado;
    }

    protected void validar(ItfCampoInstanciado campoInstanciado, boolean umNovoRegistro, T value) {

        List<String> validacao = UtilSBCoreValidacao.gerarMensagensValidacao(campoInstanciado, value, umNovoRegistro, !campoInstanciado.validarCampo(value));
        if (!validacao.isEmpty()) {
            lancarMensagemValidacao(validacao.get(0));
        }
        if (campoInstanciado.isTemValidadacaoLogica()) {
            try {
                List<WidgetsFormulario> areasAtualizacao;
                if (umNovoRegistro) {
                    areasAtualizacao = campoInstanciado.getValidacaoLogicaEstrategia().validarModoNovo(value);
                } else {
                    areasAtualizacao = campoInstanciado.getValidacaoLogicaEstrategia().validarModoEdicao(value);
                }
                if (areasAtualizacao != null) {
                    for (WidgetsFormulario areaAtualizar : areasAtualizacao) {
                        UtilSBWP_JSFTools.executarJavaScript("atualizarAreaCampoSeVisivel(\"" + areaAtualizar.getValor() + "\")");
                    }
                }

            } catch (ErroValidacao tt) {
                validacao.add(tt.getMensagemAoUsuario());
                lancarMensagemValidacao(tt.getMensagemAoUsuario());

            } catch (Throwable t) {
                SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro validando " + campoInstanciado.getNomeCompostoIdentificador(), t);
            }

            if (!validacao.isEmpty()) {
                lancarMensagemValidacao(validacao.get(0));
            }

        }

    }

    @Override
    public void validate(FacesContext context, UIComponent component, T value) throws ValidatorException {
        ItfCampoInstanciado campoInstanciado = getCampoInstanciadoByComponent(context, component);

        if (campoInstanciado != null) {

            boolean umNovoRegistro = campoInstanciado.getObjetoDoAtributo().getId() == 0;
            validar(campoInstanciado, umNovoRegistro, value);

        }
    }

    protected void lancarMensagemValidacao(String pMmensagem) throws ValidatorException {
        FacesMessage mensagemErro = new FacesMessage();
        mensagemErro.setSummary(pMmensagem);
        mensagemErro.setSeverity(FacesMessage.SEVERITY_ERROR);
        // PgUtil paninaUtil = new PgUtil();
        // String idCompoentePai = paninaUtil.getNomeIdPainelGroupInputSB(component);
        //

        // pRIMEFACES.UPDATE NÃO FUNCIONA NESTE EVENTO, USAR CHAMADA JAVASCRIPT COMO NO EXEMPLO:
        //  if (pApagarErro) {
        // PrimeFaces.current().executeScript("atualizarAreaByID('" + component.getClientId() + "');");
        //  }
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
