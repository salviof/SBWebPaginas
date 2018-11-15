/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package org.super_bits.tags.uiSBinput;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;

/**
 *
 * @author desenvolvedor
 */
public class UiInputSB extends UIInput {

    @SuppressWarnings("FieldNameHidesFieldInSuperclass")
    public static final String COMPONENT_TYPE = "org.super_bits.tags.uiSB.input.UiInputSB";
    @SuppressWarnings("FieldNameHidesFieldInSuperclass")
    public static final String COMPONENT_FAMILY = "org.primefaces.component.UiInputSB";
    //   private static final String DEFAULT_RENDERER = "org.super_bits.tags.uiSBinput.UiInputSBRender";
    private String xhtmlInclude;

    public UiInputSB() {
        super();
    }

    protected enum PropertyKeys {

        registro;
        String toString;

        PropertyKeys(String toString) {
            this.toString = toString;
        }

        PropertyKeys() {
        }

        @Override
        public String toString() {
            return ((this.toString != null) ? this.toString : super.toString());
        }

    }

    @Override
    public void decode(FacesContext context) {
        super.decode(context); //To change body of generated methods, choose Tools | Templates.
    }

    public static String getCOMPONENT_FAMILY() {
        return COMPONENT_FAMILY;
    }

    public static String getCOMPOSITE_COMPONENT_TYPE_KEY() {
        return COMPOSITE_COMPONENT_TYPE_KEY;
    }

    public ItfCampoInstanciado getRegistro() {
        return (ItfCampoInstanciado) getStateHelper().eval(PropertyKeys.registro, null);
    }

    public void setRegistro(ItfCampoInstanciado pCampoInstanciado) {
        getStateHelper().put(PropertyKeys.registro, pCampoInstanciado);
    }

    public String getXhtmlInclude() {
        if (getRegistro() != null) {
            xhtmlInclude = "/" + getRegistro().getComponenteVisualPadrao().getXhtmlJSF();
        }
        return xhtmlInclude;
    }
}
