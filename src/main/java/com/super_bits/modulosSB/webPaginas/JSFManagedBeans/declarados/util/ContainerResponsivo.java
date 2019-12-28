/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFManagedBeans.declarados.util;

import com.super_bits.modulosSB.SBCore.modulos.view.telas.ItfTelaUsuario;

/**
 *
 * @author desenvolvedor
 */
public class ContainerResponsivo {

    private final ItfTelaUsuario tela;

    public ContainerResponsivo(ItfTelaUsuario pTela) {
        tela = pTela;
    }

    public int numeroColunasParaComponente(double pPesoComponente, int pContainerPai) {

        return calcularNumeroMaximoColunasPorPesoComponente(pPesoComponente, pContainerPai);
    }
    // 9

    public int numeroColunasParaComponente2p2(int pContainerPai) {
        return calcularNumeroMaximoColunasPorPesoComponente(2.2, pContainerPai);
    }

    private int calcularNumeroMaximoColunasPorPesoComponente(double peso, int pContainerPai) {
        int colunasNaTela = tela.getNumeroMaximoColunas();

        int containerPaiTransformado = colunasNaTela;
        if (colunasNaTela < 12) {
            double porcentagem = 100 / colunasNaTela * pContainerPai; // 12/ 9
            Double novoValor = (containerPaiTransformado / 100D) * porcentagem;
            containerPaiTransformado = novoValor.intValue();

        }
        Double valor = containerPaiTransformado / peso;
        return valor.intValue();
    }

    public int numeroColunasParaComponente3(int pContainerPai) {
        return calcularNumeroMaximoColunasPorPesoComponente(3, pContainerPai);
    }

}
