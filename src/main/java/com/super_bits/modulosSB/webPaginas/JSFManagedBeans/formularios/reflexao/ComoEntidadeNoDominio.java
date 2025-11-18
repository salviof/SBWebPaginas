/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.reflexao;

import com.super_bits.modulosSB.webPaginas.JSFBeans.SBBeanModel.DEPRECIADO.InfoDominioEntidade;

/**
 *
 * @author salvioF
 */
public interface ComoEntidadeNoDominio {

    public InfoDominioEntidade getInfoBean();

    public Object getValor();

    public void setValor(Object pValor);

    public String getVisualizacaoItem();

}
