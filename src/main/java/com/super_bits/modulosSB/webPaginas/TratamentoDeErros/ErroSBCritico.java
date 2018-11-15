package com.super_bits.modulosSB.webPaginas.TratamentoDeErros;

public class ErroSBCritico extends ErroSBGenericoWeb {

    public ErroSBCritico(String pMsg) {
        super(pMsg);
        for (Integer i = 0; i < 100; i++) {
            System.out.println("ERRO GRAVE" + pMsg);
        }

    }

}
