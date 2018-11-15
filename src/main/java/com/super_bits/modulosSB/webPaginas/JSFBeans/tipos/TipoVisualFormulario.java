/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.tipos;

import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author desenvolvedor
 */
@ApplicationScoped
public class TipoVisualFormulario extends ConstantesWeb {

    public TipoVisualFormulario() {
        super(FabTipoVisualFormulario.class);
    }

    @Override
    public String getPadrao() {
        return FabTipoVisualFormulario.GRUPOS.name();
    }

    public String getGruposCampos() {
        return FabTipoVisualFormulario.GRUPOS.name();
    }

    public String getAssistente() {
        return FabTipoVisualFormulario.ASSISTENTE.name();
    }

}
