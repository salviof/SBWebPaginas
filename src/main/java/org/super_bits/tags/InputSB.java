/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.super_bits.tags;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.component.UINamingContainer;
import javax.faces.context.FacesContext;
import org.primefaces.component.api.Widget;

/**
 *
 * * -- DEPRECATED utilizar org.super_bits.tags.inputGenericoInputGenerico
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
 * documentação sobre isso é escaça
 *
 * -- DEPRECATED utilizar InputGenerico
 *
 * @author salvioF
 */
@FacesComponent(InputSB.CLASSE_DO_COMPONENTE)
@ResourceDependencies({
    @ResourceDependency(library = "primefaces", name = "jquery/jquery.js"),
    @ResourceDependency(library = "primefaces", name = "primefaces.js"),
    @ResourceDependency(library = "SBtagLib", name = "inputTeste.js")})
@Deprecated
public class InputSB extends UIComponentBase implements Widget {

    public static final String CLASSE_DO_COMPONENTE = "org.super_bits.tags.InputSB";
    public static final String FAMILIA_DO_COMPONENTE = "org.super_bits.tags.componentes";

    private ItfCampoInstanciado campoInstanciado;

    @Override
    public String getId() {
        String id = super.getId(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        return id;
    }

    @Override
    public String getClientId() {

        String clientID = super.getClientId();
        return clientID;
    }

    //TODO
    //private UIComponentBase componentPrimeVinculado;
    public ItfCampoInstanciado getCampoInstanciado() {
        return campoInstanciado;
    }

    @Override
    public String getFamily() {
        return FAMILIA_DO_COMPONENTE;
    }

    /**
     * Através dessa variavel é possivel acessar o objeto via javascript
     *
     * @return
     */
    @Override
    public String resolveWidgetVar() {
        FacesContext context = getFacesContext();
        String userWidgetVar = (String) getAttributes().get("widgetVar");

        if (userWidgetVar != null) {
            return userWidgetVar;
        } else {
            return "widget_"
                    + getClientId(context).replaceAll(
                            "-|" + UINamingContainer.getSeparatorChar(context),
                            "_");
        }
    }

    protected static enum PropertyKeys {
        metodoOnchange, widgetVar, propriedadeTextoTeste;
    }

    public String getWidgetVar() {
        return (String) getStateHelper().eval(PropertyKeys.widgetVar, null);
    }

    public void setWidgetVar(String _widgetVar) {
        getStateHelper().put(PropertyKeys.widgetVar, _widgetVar);
    }

}
