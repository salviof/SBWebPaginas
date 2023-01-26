/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package org.super_bits.tags.botao.generico;

import javax.faces.context.FacesContext;
import org.primefaces.component.button.Button;
import org.primefaces.component.button.ButtonRenderer;
import org.primefaces.util.EscapeUtils;
import org.primefaces.util.SharedStringBuilder;

/**
 *
 * @author salvio
 */
public class TagBotaoGenericoRender extends ButtonRenderer {

    private static final String SB_BUILD_ONCLICK = ButtonRenderer.class.getName() + "#buildOnclick";

    public TagBotaoGenericoRender() {
        super();
    }

    @Override
    protected String buildOnclick(FacesContext context, Button button) {
        String targetURL = getTargetURL(context, button);

        if (targetURL != null) {
            StringBuilder onclick = SharedStringBuilder.get(context, SB_BUILD_ONCLICK);
            if (onclick.length() > 0) {
                return onclick.toString();
            }
            onclick.append("location.href='").append(targetURL).append("';");
            return onclick.toString();
        } else {
            return super.buildOnclick(context, button); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        }

    }

}
