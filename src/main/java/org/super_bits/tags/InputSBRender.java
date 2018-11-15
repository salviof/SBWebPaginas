/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.super_bits.tags;

import java.io.IOException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;
import org.primefaces.renderkit.CoreRenderer;

/**
 *
 * @author salvioF
 */
@FacesRenderer(componentFamily = InputSB.FAMILIA_DO_COMPONENTE, rendererType = InputSBRender.RENDERER_TYPE)
public class InputSBRender extends CoreRenderer {

    public static final String RENDERER_TYPE = "org.super_bits.tags.InputSBRender";

    @Override
    public void decode(FacesContext context, UIComponent component) {
        super.decode(context, component); //To change body of generated methods, choose Tools | Templates.
    }

    private void encodeMarkup(FacesContext context, InputSB inputSB) throws IOException {

        ResponseWriter writer = context.getResponseWriter();

        //Object value = inputSB.getValue() != null ? inputSB.getValue() : 0;
        // writer.startElement(RENDERER_TYPE,);
        writer.startElement("input", inputSB);
        writer.writeAttribute("id", inputSB.getClientId(), null);
        writer.writeAttribute("name", inputSB.getClientId(), null);
        writer.writeAttribute("disabled", true, null);
        //  writer.writeAttribute("value", value.toString(), null);
        //   writer.writeAttribute("data-min", inputSB.getMin(), null);
        //   writer.writeAttribute("data-step", inputSB.getStep(), null);
        //   writer.writeAttribute("data-max", inputSB.getMax(), null);
        //writer.writeAttribute("data-displayInput", Boolean.toString(inputSB.isShowLabel()), null);
        // writer.writeAttribute("data-readOnly", Boolean.toString(inputSB.isDisabled()), null);
        //  writer.writeAttribute("data-cursor", Boolean.toString(inputSB.isCursor()), null);

        writer.writeAttribute("class", "knob", null);

        writer.endElement("input");

        writer.startElement("input", null);
        writer.writeAttribute("id", inputSB.getClientId() + "_hidden", null);
        writer.writeAttribute("name", inputSB.getClientId() + "_hidden", null);
        writer.writeAttribute("type", "hidden", null);
        // writer.writeAttribute("value", value.toString(), null);
        writer.endElement("input");
    }

    @Override
    public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
        this.encodeMarkup(context, (InputSB) component);
        //   this.encodeScript(context, (InputSB) component);
    }

}
