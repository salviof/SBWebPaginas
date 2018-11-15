package com.super_bits.modulosSB.webPaginas.TratamentoDeErros;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWP_JSFTools;

public class ErroSBCriticoWeb extends ErroSBGenericoWeb {

    public ErroSBCriticoWeb(String pMsg) {

        super(pMsg);
        System.out.println(pMsg);
        if (!SBCore.isEmModoDesenvolvimento()) {
            UtilSBWP_JSFTools.vaParaPaginadeErro(pMsg);
        } else {
            throw new UnsupportedOperationException("Ouve Lançamento de erro crítico para usuário:" + pMsg);
        }
    }

}
