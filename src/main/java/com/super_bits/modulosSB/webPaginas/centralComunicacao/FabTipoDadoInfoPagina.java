/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.centralComunicacao;

/**
 *
 * @author salvio
 */
public enum FabTipoDadoInfoPagina {

    STATUS_FORMULARIO,
    ENTIDADE_SELECIONADA,
    ENTIDADES_LISTADAS;

    public String getXhtmlJson() {
        switch (this) {
            case STATUS_FORMULARIO:
                return "/resources/SBComp/include/formulario/instanciaAtualJson/status_formulario.xhtml";

            case ENTIDADE_SELECIONADA:
                return "/resources/SBComp/modal/entidade_selecionada.xhtml";

            case ENTIDADES_LISTADAS:
                return "/resources/SBComp/modal/entidades_listadas.xhtml";

            default:
                throw new AssertionError(this.name());

        }
    }

}
