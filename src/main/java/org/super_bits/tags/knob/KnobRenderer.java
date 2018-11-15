package org.super_bits.tags.knob;

import java.awt.Color;
import java.io.IOException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.convert.ConverterException;
import javax.faces.render.FacesRenderer;
import org.primefaces.renderkit.CoreRenderer;
import org.primefaces.util.WidgetBuilder;

@FacesRenderer(componentFamily = Knob.COMPONENT_FAMILY, rendererType = KnobRenderer.RENDERER_TYPE)
public class KnobRenderer extends CoreRenderer {

    public static final String RENDERER_TYPE = "it.strazz.faces.KnobRenderer";

    @Override
    public void decode(FacesContext context, UIComponent component) {

        decodeBehaviors(context, component);

        String submittedValue = (String) context.getExternalContext().getRequestParameterMap().get(component.getClientId(context) + "_hidden");
        ((Knob) component).setSubmittedValue(submittedValue);
    }

    @Override
    public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
        this.encodeMarkup(context, (Knob) component);
        this.encodeScript(context, (Knob) component);
    }

    private void encodeMarkup(FacesContext context, Knob knob) throws IOException {

        ResponseWriter writer = context.getResponseWriter();

        Object value = knob.getValue() != null ? knob.getValue() : 0;

        writer.startElement("input", knob);
        writer.writeAttribute("id", knob.getClientId(), null);
        writer.writeAttribute("name", knob.getClientId(), null);
        writer.writeAttribute("disabled", true, null);
        writer.writeAttribute("value", value.toString(), null);
        writer.writeAttribute("data-min", knob.getMin(), null);
        writer.writeAttribute("data-step", knob.getStep(), null);
        writer.writeAttribute("data-max", knob.getMax(), null);
        writer.writeAttribute("data-displayInput", Boolean.toString(knob.isShowLabel()), null);
        writer.writeAttribute("data-readOnly", Boolean.toString(knob.isDisabled()), null);
        writer.writeAttribute("data-cursor", Boolean.toString(knob.isCursor()), null);

        if (knob.getThickness() != null) {
            writer.writeAttribute("data-thickness", knob.getThickness(), null);
        }

        if (knob.getForegroundColor() != null) {
            String fg;
            if (knob.getForegroundColor() instanceof Color) {
                fg = "FFFFFF";
            } else {
                fg = knob.getForegroundColor().toString();
            }
            writer.writeAttribute("data-fgColor", fg, null);
        }

        if (knob.getBackgroundColor() != null) {
            String bg;
            if (knob.getBackgroundColor() instanceof Color) {
                bg = "ffffff";
            } else {
                bg = knob.getBackgroundColor().toString();
            }
            writer.writeAttribute("data-bgColor", bg, null);
        }

        if (knob.getWidth() != null) {
            writer.writeAttribute("data-width", knob.getWidth().toString(), null);
        }

        writer.writeAttribute("class", "knob", null);

        writer.endElement("input");

        writer.startElement("input", null);
        writer.writeAttribute("id", knob.getClientId() + "_hidden", null);
        writer.writeAttribute("name", knob.getClientId() + "_hidden", null);
        writer.writeAttribute("type", "hidden", null);
        writer.writeAttribute("value", value.toString(), null);
        writer.endElement("input");
    }

    private void encodeScript(FacesContext context, Knob component) throws IOException {
        String clientId = component.getClientId();
        String widgetVar = component.resolveWidgetVar();

        WidgetBuilder wb = getWidgetBuilder(context);

        wb.initWithDomReady("Knob", widgetVar, clientId);
        wb.attr("labelTemplate", component.getLabelTemplate());
        wb.callback("onchange", "function(value)", component.getOnchange());

        encodeClientBehaviors(context, component);

        wb.finish();
    }

    @Override
    public Object getConvertedValue(FacesContext context, UIComponent component, Object submittedValue) throws ConverterException {
        if (submittedValue == null) {
            submittedValue = 0;
        }
        try {
            return ((submittedValue instanceof Integer) ? submittedValue : Integer.parseInt(submittedValue.toString()));
        } catch (NumberFormatException e) {
            throw new ConverterException(e);
        }
    }
}
