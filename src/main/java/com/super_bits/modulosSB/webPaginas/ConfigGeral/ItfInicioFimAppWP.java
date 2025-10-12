/*
 *  Super-Bits.com CODE CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.ConfigGeral;

import javax.servlet.SessionCookieConfig;

/**
 *
 * @author Salvio
 */
public interface ItfInicioFimAppWP {

    public void inicio();

    public void fim();

    public void definirConfiguracoesDeCookie(SessionCookieConfig pSEssao);
}
