/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.modal;

import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.interfaces.ItfB_PaginaComEntityManager;
import java.io.Serializable;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Salvio Furbino
 */
@ViewScoped
@Named
public class PgModalSBJSF extends PgModalCampoSelecionadoAbstrato implements Serializable, ItfB_PaginaComEntityManager {

    @Override
    public EntityManager getEMPagina() {
        return getPaginaVinculada().getEMPagina();
    }

    @Override
    public void recarregarEntidadeSelecionada() {
        getPaginaVinculada().recarregarEntidadeSelecionada();
    }

    public String getSessionId() {
        return ((HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false)).getId();
    }

}
