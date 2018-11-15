/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.recursosJSF;

import com.sun.faces.application.ApplicationAssociate;
import com.sun.faces.application.resource.ResourceInfo;
import com.sun.faces.application.resource.ResourceManager;
import com.super_bits.modulosSB.webPaginas.ConfigGeral.SBWebPaginas;
import java.net.URL;
import javax.faces.FacesException;
import javax.faces.application.ResourceHandler;
import javax.faces.application.ResourceHandlerWrapper;
import javax.faces.application.ViewResource;
import javax.faces.context.FacesContext;

/**
 *
 * @author desenvolvedor
 */
public class RecursosJSF extends ResourceHandlerWrapper {

    private final ResourceHandler wrapped;

    public RecursosJSF(ResourceHandler wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public ViewResource createViewResource(FacesContext context, final String name) {
        ViewResource resource = super.createViewResource(context, name);

        if (resource == null) {
            resource = new ViewResource() {
                @Override
                public URL getURL() {
                    try {
                        String caminho = "/META-INF" + name;

                        URL urlEncontrada = getClass().getResource(caminho);

                        if (urlEncontrada == null) {
                            urlEncontrada = SBWebPaginas.class.getResource(caminho);
                        }

                        if (urlEncontrada == null) {
                            urlEncontrada = SBWebPaginas.class.getResource(name);
                        }
                        String caminho2 = "META-INF" + name;
                        if (urlEncontrada == null) {

                            urlEncontrada = getClass().getResource(caminho2);
                        }
                        if (urlEncontrada == null) {
                            urlEncontrada = SBWebPaginas.class.getResource(caminho2);
                        }

                        if (urlEncontrada == null) {
                            ResourceManager manager = ApplicationAssociate.getInstance(context.getExternalContext()).getResourceManager();
                            String contentType = context.getExternalContext().getMimeType(caminho);
                            ResourceInfo resourceInfo = manager.findViewResource(caminho, contentType, context);
                            if (resourceInfo == null) {
                                resourceInfo = manager.findViewResource(caminho2, contentType, context);
                            }
                            if (resourceInfo != null) {
                                return resourceInfo.getHelper().getURL(resourceInfo, context);
                            }
                        }

                        return urlEncontrada;

                    } catch (Throwable e) {

                        throw new FacesException("Erro Localizando recurso" + name, e);
                    }
                }
            };
        }

        return resource;
    }

    @Override
    public ResourceHandler getWrapped() {
        return wrapped;
    }

}
