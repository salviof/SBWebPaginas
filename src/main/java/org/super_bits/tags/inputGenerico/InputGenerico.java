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
 *
 * é nessessário imbutir os tipos de reiderização no InputSB.java e retirar
 * aquele tanto de render condicional em inputSB.xhtml, isso vai fazer a
 * reiderização do campo ficar mais rápida
 *
 * O um decorator que instancie de maneira condicional o componente correto logo
 * no constructor seria uma boa estrategia
 *
 * Desafios:
 *
 * 1->o @ResourceDependency será um problema, pois como a anotação não estará
 * presente terá que ser injetado programaticamente.
 *
 * 2 -> Possíveis problemas podem acontecer devido a instancia não ser do
 * componente correto, é possível que o primefaces trate o componente do tipo
 * esperado em uma função externa
 *
 *
 *
 * 3 -> Os atributos que são definidas de maneira automática terão que ser
 * definidas programaticamente uma a uma de acordo com o tipo do campo, a
 * documentação sobre isso deixa a desejar
 *
 * @author desenvolvedor
 */
@FacesComponent(value = "inputGenerico")
public class InputGenerico extends UINamingContainer {

    // private String idGerado;
    enum PropertyKeys {
        registro
    }

    @Override
    public String getId() {
        return super.getId();
        /**
         * if (getRegistro() == null) { return super.getId(); } if (idGerado ==
         * null) { String clienteID = super.getId(); StringBuilder str = new
         * StringBuilder(clienteID); str.append(":");
         * str.append(getRegistro().getNomeClasseAtributoDeclarado());
         * str.append(getRegistro().getNomeCamponaClasse()); idGerado =
         * str.toString(); }
         *
         * return idGerado;
         */
    }

    @Override
    public String getClientId() {

        return super.getClientId();
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
