/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.PrimeFacesBeanModel;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.seletores.seletorUnicoObjeto.B_ObjetoDeUmaListaAbs;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ComoEntidadeSimplesSomenteLeitura;

/**
 *
 * @author desenvolvedor
 * @param <T>
 */
public class BP_autoCompleteEPesquisaSemPersistencia<T extends ComoEntidadeSimplesSomenteLeitura> extends B_ObjetoDeUmaListaAbs<T> {

    public BP_autoCompleteEPesquisaSemPersistencia(ItfCampoInstanciado pCampoInstanciado) {
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
