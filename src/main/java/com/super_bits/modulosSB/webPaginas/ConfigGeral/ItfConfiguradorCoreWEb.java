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

/**
 *
 * @author salvioF
 */
public interface ItfConfiguradorCoreWEb extends ItfConfiguradorCore {

    public class UtilConfiguracaoCoreWeb {

        public static void setclassesPadraoJar(ConfigCoreCustomizavel pConfig, SBCore.ESTADO_APP pEstadoApp) {
            pConfig.setCentralDeEventos(CentralLogEventosArqTextoGenerica.class);
            pConfig.setCentralMEnsagens(CentramMensagemProgramadorMsgStop.class);
            pConfig.setClasseErro(InfoErroSBCoreDeveloperSopMessagem.class);
            pConfig.setControleDeSessao(ControleDeSessaoPadrao.class);
        }

    }

}
