/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package org.super_bits.tags.inputGenerico;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIInput;
import javax.faces.component.UINamingContainer;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

/**
 *
 * @author desenvolvedor
 */
@FacesComponent(value = "inputGenerico")
public class InputGenerico extends UINamingContainer {

    enum PropertyKeys {
        registro
    }

    private String xhtmlInclude;

    private UIInput registro;

    public InputGenerico() {
        super();
    }

    public ItfCampoInstanciado getRegistroOld() {
        try {
            return (ItfCampoInstanciado) registro.getValue();
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro obtendo campo instanciado", t);
            return null;
        }

    }

    public ItfCampoInstanciado getRegistro() {
        return (ItfCampoInstanciado) getStateHelper().eval(PropertyKeys.registro, null);
    }

    public void setRegistro(java.lang.String _mask) {
        getStateHelper().put(PropertyKeys.registro, _mask);
    }

    public String getXhtmlInclude() {
        if (getRegistro() != null) {
            xhtmlInclude = "/" + getRegistro().getComponenteVisualPadrao().getXhtmlJSF();
        }
        return xhtmlInclude;
    }

}
