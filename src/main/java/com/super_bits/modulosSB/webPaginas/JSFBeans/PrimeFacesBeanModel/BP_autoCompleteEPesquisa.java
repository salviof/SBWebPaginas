/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.PrimeFacesBeanModel;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.seletores.seletorUnicoObjeto.B_ObjetoDeUmaListaAbs;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimplesSomenteLeitura;
import java.util.List;

/**
 *
 * @author desenvolvedor
 * @param <T>
 */
public class BP_autoCompleteEPesquisa<T extends ItfBeanSimplesSomenteLeitura> extends B_ObjetoDeUmaListaAbs<T> {

    public BP_autoCompleteEPesquisa(ItfCampoInstanciado pCampoInstanciado) {

        super(pCampoInstanciado);
    }

    @Override
    public void limparSelecao() {
        setObjetoSelecionado(null);
    }

    @Override
    public void selecionarTudo() {
        if (!getOrigem().isEmpty()) {
            setObjetoSelecionado((T) getOrigem().get(0));
        }
    }

}
