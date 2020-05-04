/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFManagedBeans.declarados.util;

import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreNumeros;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringFiltros;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    public String gerarTextoSemCaracterEspecial(String pValor) {
        return UtilSBCoreStringFiltros.gerarUrlAmigavel(pValor);

    }

    public String gerarTextoApenasNumero(String pValor) {
        return UtilSBCoreStringFiltros.getNumericosDaString(pValor);

    }

    public String inteiro(long pValor) {

        return UtilSBCoreNumeros.formatarNumeroInteiro(pValor);
    }

    public String getNumeroComCasasDecimais(int pInteiro, int pQtd) {
        try {
            if (pQtd == 0) {
                return String.valueOf(pInteiro);
            } else {
                return String.valueOf(pInteiro) + ",00";
            }
        } catch (Throwable t) {
            return String.valueOf(pInteiro);
        }
    }

    public String getNimeroComCasasDecimais(double pNumero, int pQtdCasasDecimais) {
        try {
            BigDecimal bd = new BigDecimal(pNumero);

            bd = bd.setScale(pQtdCasasDecimais, RoundingMode.HALF_DOWN);
            String valor = String.valueOf(bd.doubleValue());
            return valor.replace(".", ",");
        } catch (Throwable t) {
            return String.valueOf(pNumero);
        }
    }

    public String gerarDataHoraSegundoJavascript(Date pDataHora) {
        if (pDataHora == null) {
            return null;
        }
        SimpleDateFormat formatador = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return formatador.format(pDataHora);
    }

}
