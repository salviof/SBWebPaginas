/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.ConfigGeral;

import com.super_bits.modulosSB.SBCore.ConfigGeral.ConfigCoreCustomizavel;
import com.super_bits.modulosSB.SBCore.ConfigGeral.ControleDeSessaoPadrao;
import com.super_bits.modulosSB.SBCore.ConfigGeral.ItfConfiguradorCore;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.Mensagens.CentramMensagemProgramadorMsgStop;
import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.InfoErroSBCoreDeveloperSopMessagem;
import com.super_bits.modulosSB.SBCore.modulos.logeventos.CentralLogEventosArqTextoGenerica;
import com.super_bits.modulosSB.webPaginas.arquivosDoProjeto.CentralDeArquivosWebAppPastaREsources;
import com.super_bits.modulosSB.webPaginas.util.CentralDeMensagensJSFAPP;

/**
 *
 * @author salvioF
 */
public interface ItfFabConfiguradorCoreWebPaginas extends ItfConfiguradorCore {

    public class UtilFabConfigWebPaginas {

        public static void setClassesPadrao(SBCore.ESTADO_APP pEstado, ConfigCoreCustomizavel pConfig) {
            pConfig.setCentralDeEventos(CentralLogEventosArqTextoGenerica.class);
            pConfig.setCentralMEnsagens(CentralDeMensagensJSFAPP.class);
            pConfig.setClasseErro(InfoErroSBCoreDeveloperSopMessagem.class);
            pConfig.setControleDeSessao(ControleDeSessaoPadrao.class);
            pConfig.setCentralDeArquivos(CentralDeArquivosWebAppPastaREsources.class);

            switch (pEstado) {
                case DESENVOLVIMENTO:
                    pConfig.setEstadoAPP(SBCore.ESTADO_APP.DESENVOLVIMENTO);
                    pConfig.setCentralMEnsagens(CentramMensagemProgramadorMsgStop.class);
                    pConfig.setClasseErro(InfoErroSBCoreDeveloperSopMessagem.class);
                    break;
                case PRODUCAO:
                    pConfig.setEstadoAPP(SBCore.ESTADO_APP.PRODUCAO);
                    break;
                case HOMOLOGACAO:
                    pConfig.setEstadoAPP(SBCore.ESTADO_APP.HOMOLOGACAO);
                    break;
                default:
                    throw new AssertionError(pEstado.name());

            }
        }

    }

}
