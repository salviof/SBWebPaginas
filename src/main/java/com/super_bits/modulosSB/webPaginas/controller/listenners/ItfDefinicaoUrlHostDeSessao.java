/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.controller.listenners;

import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfSessao;
import java.io.Serializable;

/**
 *
 * @author salvio
 */
public interface ItfDefinicaoUrlHostDeSessao
        extends Serializable {

    public String gerarUrlDeAcesso(ItfSessao pAcesso);

}
