/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.modal;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.interfaces.ItfB_Pagina;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import org.primefaces.PrimeFaces;

/**
 *
 * @author SalvioF
 */
public class PgModalCampoSelecionadoAbstrato extends PgModalAbstrato implements ItfModalCampoInstanciado {

    private ItfCampoInstanciado campoinstanciado;

    public ItfCampoInstanciado getCampoInstanciado() {
        try {
            if (campoinstanciado == null) {
                campoinstanciado = getPaginaVinculada().getCampoInstSelecionado();
                if (campoinstanciado == null) {
                    throw new UnsupportedOperationException("O Campo Instanciado da página não foi selecionado");
                }
                if (campoinstanciado.isUmValorComLista()) {
                    campoinstanciado.getComoCampoSeltorItem().limparFiltro();

                }
            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro obtendo campo instanciado do modal", t);
            return null;
        }
        return campoinstanciado;
    }

    @Override
    public ItfB_Pagina getPaginaVinculada() {
        return super.getPaginaVinculada();
    }

    @Override
    public void fecharModal() {
        Object valorSelecionado = campoinstanciado.getValor();
        getPaginaVinculada().getCampoInstSelecionado().setValor(valorSelecionado);
        PrimeFaces.current().dialog().closeDynamic(valorSelecionado);

    }

}
