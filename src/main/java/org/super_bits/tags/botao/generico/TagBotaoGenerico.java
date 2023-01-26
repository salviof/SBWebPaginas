/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package org.super_bits.tags.botao.generico;

import com.sun.faces.application.applicationimpl.InstanceFactory;
import javax.faces.application.ResourceDependency;
import org.primefaces.component.button.Button;

/**
 *
 * @author salvio
 */
@ResourceDependency(library = "primefaces", name = "components.css")
@ResourceDependency(library = "primefaces", name = "jquery/jquery.js")
@ResourceDependency(library = "primefaces", name = "core.js")
@ResourceDependency(library = "primefaces", name = "components.js")
public class TagBotaoGenerico extends Button {

    public static final String RENDERERGENERICO = TagBotaoGenericoRender.class.getName();
    public static final String COMPONENTE_TIPO = "br.org.superbits.tags.BotaoGenerico";
    public static final String COMPONENr_FAMILIA = "br.org.superbits.tags";

    public TagBotaoGenerico() {
        super();
        setRendererType(RENDERERGENERICO);
    }

    @Override
    public String getFamily() {
        return COMPONENr_FAMILIA;
    }

}
