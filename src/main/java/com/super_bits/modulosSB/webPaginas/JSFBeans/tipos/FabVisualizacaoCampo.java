/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.tipos;

import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author sfurbino
 */
@ApplicationScoped
public enum FabVisualizacaoCampo {

    resumido, labelEsquerda, labelSuperior;
    public static final String DIRETORIO_XHTML = "/resources/SBComp/input/";

    public FabVisualizacaoCampo getLabelEsquerda() {
        return labelEsquerda;
    }

    public FabVisualizacaoCampo getResumido() {
        return resumido;
    }

    public FabVisualizacaoCampo getLabelSuperior() {
        return labelSuperior;
    }

    public String getXhtmlVisualizacao() {
        switch (this) {
            case resumido:
                return DIRETORIO_XHTML + "resumido.xhtml";
            case labelEsquerda:
                return DIRETORIO_XHTML + "labelEsquerda.xhtml";
            case labelSuperior:
                return DIRETORIO_XHTML + "labelEsquerda.xhtml";
            default:
                return resumido.getXhtmlVisualizacao();

        }
    }

}
