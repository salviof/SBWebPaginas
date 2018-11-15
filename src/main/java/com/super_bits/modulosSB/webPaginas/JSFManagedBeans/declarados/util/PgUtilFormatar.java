/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFManagedBeans.declarados.util;

import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreNumeros;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author desenvolvedor
 */
@RequestScoped
@Named
public class PgUtilFormatar {

    public String moeda(Double pValor) {

        return UtilSBCoreNumeros.converterMoeda(pValor);
    }

    public String inteiro(long pValor) {

        return UtilSBCoreNumeros.formatarNumeroInteiro(pValor);
    }

    public String moeda(long pValor) {
        return UtilSBCoreNumeros.converterMoeda(pValor);
    }

}
