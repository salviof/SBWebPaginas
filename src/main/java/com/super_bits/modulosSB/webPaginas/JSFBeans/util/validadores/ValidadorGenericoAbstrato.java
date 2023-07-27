/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.util.validadores;

import com.google.common.collect.Lists;
import com.sun.faces.facelets.el.TagValueExpression;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringValidador;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreValidacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ErroValidacao;
import com.super_bits.modulosSB.SBCore.modulos.view.telas.LayoutTelaAreaConhecida;
import com.super_bits.modulosSB.SBCore.modulos.view.widgetsFormulario.WidgetsFormulario;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWP_JSFTools;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import org.primefaces.expression.SearchExpressionUtils;

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

    private static final List<String> componentesInicioPesquisaCampoRelacionado = Lists.newArrayList("Repeat", "FildSet");

    public UIComponent getPaiCampoPesquisa(UIComponent pComponent) {

        if (componentesInicioPesquisaCampoRelacionado.stream().filter(cp -> pComponent.toString().contains(cp)).findFirst().isPresent()) {
            return pComponent;
        }
        if (pComponent.getParent() == null) {
            return null;
        }
        return getPaiCampoPesquisa(pComponent.getParent());

    }

    protected void validar(ItfCampoInstanciado campoInstanciado, UIComponent pComponent, boolean umNovoRegistro, T value) {

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

                        switch (areaAtualizar.getTipoWidget()) {
                            case CAMPO:
                                String nomeCampoCanonico = areaAtualizar.getComoWidgetCampoInstanciado().getCampoCanonico();
                                // TODO: a atualização de campos em subformulários onde o campo se repete diversas vezes em uma tabela (ex: Cliente 0, boleto 1, boleto 2 boleto3)
                                // não é compativel com esta chamada.
                                // Os desafios são:
                                //1 -> encontrar o ID no server side via nome da classe não é possível, nõs não temos um id fácil de identificar devido a forma gambiarrada comom o inpubSB foi feito,
                                // é nessessário imbutir os tipos de reiderização no InputSB.java e retirar aquele tanto de render condicional em inputSB.xhtml, isso vai fazer a reiderização do campo ficar mais rápida
                                // e vai permitir colocar um ID único no template decorator dos cambos
                                //2-> mesmo com o id sendo definido e podendo ser localizado via  SearchExpressionUtils é nescessário encontrar o id mais próximo para não atualizar apenas o campo da mesma linha
                                //na tabela.
                                //3-> um contro caminho é utilizar o PrimeFaces Selectors (PFS) que só funciona no client side, pois com ele é possível utilizar o parametro first combinado com outros seletores
                                //Salvio Furbino

                                UIComponent campoAtualizar = null;//getProximoCampoInstanciado(pComponent, nomeCampoCanonico);
                                if (campoAtualizar == null) {
                                    //UtilSBWP_JSFTools.executarJavaScript("atualizarAreaCampoSeVisivel(\"" + nomeCampoCanonico + "\")");
                                    //PrimeFaces.current().ajax().update("@(." + areaAtualizar.getComoWidgetCampoInstanciado().getEntidade() + areaAtualizar.getComoWidgetCampoInstanciado().getNomeAtributo() + ")");
                                    String classecssInput = areaAtualizar.getComoWidgetCampoInstanciado().getEntidade() + areaAtualizar.getComoWidgetCampoInstanciado().getNomeAtributo();

                                    FacesContext.getCurrentInstance().getPartialViewContext().getEvalScripts().add("atualizarAreaCampoByCssEstilo('" + classecssInput + "');");

                                    UtilSBWP_JSFTools.executarJavaScript(classecssInput);
                                    //PrimeFaces.current().executeScript("PrimeFaces.ab({s:\"\",f:\"formAjaxUpdateClientSide\",u:\"@(." + classecssInput + ")\",fp:\"\"});");
                                    //            PrimeFaces.current().executeInitScript("PrimeFaces.ab({s:\"\",f:\"\",u:\"@(." + classeJavascript + ")\",fp:\"\"});");
                                } else {
                                    UtilSBWP_JSFTools.executarJavaScript("atualizarAreaByID('" + campoAtualizar.getClientId() + "');");
                                }
                                break;
                            case FORMULARIO:
                                break;
                            case COMPONENTE_PAI_RELATIVO:
                                if (areaAtualizar.isTemExpressaoPrimefaces()) {
                                    String id = SearchExpressionUtils.resolveClientId(areaAtualizar.getPFExpression(), pComponent);
                                    if (id != null) {
                                        UtilSBWP_JSFTools.executarJavaScript("atualizarAreaByID('" + id + "');");
                                    }
                                }
                                break;
                            case COMPONENTE_FILHO_RELATIVO:
                                break;
                            default:
                                throw new AssertionError();
                        }

                        // UtilSBWP_JSFTools.executarJavaScript("atualizarAreaCampoSeVisivel(\"" + areaAtualizar.getCampoCanonico() + "\")");
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
            validar(campoInstanciado, component, umNovoRegistro, value);

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
