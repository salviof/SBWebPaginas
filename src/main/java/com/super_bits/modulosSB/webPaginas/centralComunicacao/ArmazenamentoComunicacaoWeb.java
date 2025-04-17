/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.centralComunicacao;

import com.super_bits.modulosSB.SBCore.modulos.comunicacao.ArmazenamentoComunicacaoTransient;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWP_JSFTools;

/**
 *
 * @author salvio
 */
public class ArmazenamentoComunicacaoWeb extends ArmazenamentoComunicacaoTransient {

    @Override
    public boolean removerDialogoByCodigoSelo(String pCodigoSelo) {
        if (!super.removerDialogoByCodigoSelo(pCodigoSelo)) {
            return false;
        }
        UtilSBWP_JSFTools.atualizaPorId("painelMenuUsuario");
        return true;
    }

}
