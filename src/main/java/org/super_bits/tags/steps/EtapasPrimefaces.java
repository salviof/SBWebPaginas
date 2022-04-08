/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package org.super_bits.tags.steps;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.faces.FacesException;
import javax.faces.component.UIComponent;
import javax.faces.component.UIForm;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import org.primefaces.component.api.AjaxSource;

import org.primefaces.component.api.UIOutcomeTarget;
import org.primefaces.component.steps.Steps;
import org.primefaces.component.steps.StepsRenderer;
import org.primefaces.model.menu.MenuItem;
import org.primefaces.util.ComponentTraversalUtils;

/**
 *
 * facesconfig.xml:
 * <render-kit>
 * <renderer>
 * <component-family>org.primefaces.component</component-family>
 * <renderer-type>org.primefaces.component.StepsRenderer</renderer-type>
 * <renderer-class>org.super_bits.tags.steps.EtapasPrimefaces</renderer-class>
 * </renderer>
 * </render-kit>
 *
 * @author sfurbino
 */
public class EtapasPrimefaces extends StepsRenderer {

    @Override
    protected void encodeItem(FacesContext context, Steps steps, MenuItem item, int activeIndex, int index) throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        String itemClass;

        if (steps.isReadonly()) {
            itemClass = (index == activeIndex) ? Steps.ACTIVE_ITEM_CLASS : Steps.INACTIVE_ITEM_CLASS;
        } else {
            if (index == activeIndex) {
                itemClass = Steps.ACTIVE_ITEM_CLASS;
            } else {
                itemClass = Steps.VISITED_ITEM_CLASS;
            }
        }

        String containerStyle = item.getContainerStyle();
        String containerStyleClass = item.getContainerStyleClass();

        if (containerStyleClass != null) {
            itemClass = itemClass + " " + containerStyleClass;
        }

        //header container
        writer.startElement("li", null);
        writer.writeAttribute("class", itemClass, null);
        writer.writeAttribute("role", "tab", null);
        if (containerStyle != null) {
            writer.writeAttribute("style", containerStyle, null);
        }

        encodeMenuItem(context, steps, item, activeIndex, index);

        writer.endElement("li");
    }

    @Override
    protected void encodeMenuItem(FacesContext context, Steps steps, MenuItem menuitem, int activeIndex, int index) throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        String title = menuitem.getTitle();
        String style = menuitem.getStyle();
        String styleClass = this.getLinkStyleClass(menuitem);

        writer.startElement("a", null);
        writer.writeAttribute("tabindex", "-1", null);
        if (shouldRenderId(menuitem)) {
            writer.writeAttribute("id", menuitem.getClientId(), null);
        }
        if (title != null) {
            writer.writeAttribute("title", title, null);
        }

        writer.writeAttribute("class", styleClass, null);

        if (style != null) {
            writer.writeAttribute("style", style, null);
        }

        if (steps.isReadonly() || menuitem.isDisabled()) {
            writer.writeAttribute("href", "#", null);
            writer.writeAttribute("onclick", "return false;", null);
        } else {
            String onclick = menuitem.getOnclick();

            //GET
            if (menuitem.getUrl() != null || menuitem.getOutcome() != null) {
                String targetURL = getTargetURL(context, (UIOutcomeTarget) menuitem);
                writer.writeAttribute("href", targetURL, null);

                if (menuitem.getTarget() != null) {
                    writer.writeAttribute("target", menuitem.getTarget(), null);
                }
            } //POST
            else {
                writer.writeAttribute("href", "#", null);

                UIComponent form = ComponentTraversalUtils.closestForm(context, steps);
                if (form == null) {
                    throw new FacesException("MenuItem must be inside a form element");
                }

                String command;
                if (menuitem.isDynamic()) {
                    String menuClientId = steps.getClientId(context);
                    Map<String, List<String>> params = menuitem.getParams();
                    if (params == null) {
                        params = new LinkedHashMap<String, List<String>>();
                    }
                    List<String> idParams = new ArrayList<String>();
                    idParams.add(menuitem.getId());
                    params.put(menuClientId + "_menuid", idParams);
                    AjaxSource source = (AjaxSource) menuitem;
                    command = menuitem.isAjax()
                            ? buildAjaxRequest(context, form, source, (UIForm) form, params)
                            : buildNonAjaxRequest(context, steps, form, menuClientId, params, true);
                } else {
                    UIComponent source = (UIComponent) menuitem;

                    if (menuitem.isAjax()) {
                        //        command = buildAjaxRequest(context, source, (UIForm) form);
                        command = "";
                    } else {
                        command = buildNonAjaxRequest(context, ((UIComponent) menuitem), form, ((UIComponent) menuitem).getClientId(context), true);
                    }

                }

                onclick = (onclick == null) ? command : onclick + ";" + command;
            }

            if (onclick != null) {
                writer.writeAttribute("onclick", onclick, null);
            }
        }

        writer.startElement("span", steps);
        writer.writeAttribute("class", Steps.STEP_NUMBER_CLASS, null);
        writer.writeText((index + 1), null);
        writer.endElement("span");

        Object value = menuitem.getValue();
        if (value != null) {
            writer.startElement("span", steps);
            writer.writeAttribute("class", Steps.STEP_TITLE_CLASS, null);
            writer.writeText(value, null);
            writer.endElement("span");
        }

        writer.endElement("a");
    }

}
