package com.super_bits.modulosSB.webPaginas.TratamentoDeErros;

import com.super_bits.modulosSB.webPaginas.controller.servletes.tratamentoErro.ErroSBGenericoWeb;

public class ErroSBCritico extends ErroSBGenericoWeb {

    public ErroSBCritico(String pMsg) {
        super(pMsg);
        for (Integer i = 0; i < 100; i++) {
            System.out.println("ERRO GRAVE" + pMsg);
        }

    }

}
