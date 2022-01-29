/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.ConfigGeral;

import com.super_bits.modulosSB.Persistencia.centralOrigemDados.CentralAtributosSBPersistencia;
import com.super_bits.modulosSB.Persistencia.registro.persistidos.modulos.CEP.CentraLocalizacaoSBPersistenciaPadrao;
import com.super_bits.modulosSB.SBCore.ConfigGeral.ConfiguradorCoreAbstrato;
import com.super_bits.modulosSB.SBCore.ConfigGeral.ControleDeSessaoPadrao;
import com.super_bits.modulosSB.SBCore.ConfigGeral.FabTipoProjeto;
import com.super_bits.modulosSB.SBCore.ConfigGeral.ItfConfiguracaoCoreCustomizavel;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.ConfigGeral.arquivosConfiguracao.ArquivoConfiguracaoBase;
import com.super_bits.modulosSB.SBCore.ConfigGeral.arquivosConfiguracao.ArquivoConfiguracaoDistribuicao;
import com.super_bits.modulosSB.SBCore.modulos.Controller.ServicoControllerExecucaoLocal;
import com.super_bits.modulosSB.SBCore.modulos.Mensagens.CentramMensagemProgramadorMsgStop;
import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.InfoErroSBCoreDeveloperSopMessagem;
import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.InfoErroSBCoreFW;
import com.super_bits.modulosSB.SBCore.modulos.logeventos.CentralLogEventosArqTextoGenerica;
import com.super_bits.modulosSB.webPaginas.arquivosDoProjeto.CentralDeArquivosWebAppServidorSB;
import com.super_bits.modulosSB.webPaginas.centralAtributo.CentralAtributosWebApp;
import com.super_bits.modulosSB.webPaginas.centralComunicacao.CentralComunicaoWebPadrao;
import com.super_bits.modulosSB.webPaginas.centralDados.CentralDadosWebApp;
import com.super_bits.modulosSB.webPaginas.controller.sessao.ControleDeSessaoWeb;
import com.super_bits.modulosSB.webPaginas.util.CentralDeMensagensJSFAPP;
import com.super_bits.modulosSB.webPaginas.visualizacao.ServicoVisuaslizacaoWebResponsivo;
import java.io.InputStream;
import java.util.Properties;
import javax.servlet.ServletContext;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

/**
 *
 * @author salvioF
 */
public abstract class ConfiguradorCoreDeProjetoWebWarAbstrato extends ConfiguradorCoreAbstrato {

    public static ServletContext contextoDoServlet;

    public ConfiguradorCoreDeProjetoWebWarAbstrato(ServletContext contexto) {
        super(false);

        try {

            arquivoConfiguradorBase = new ArquivoConfiguracaoBase(getPropriedadesArquivoConfiguracaoWar(contexto));
            //O arquivo cliente não é nessessário em caso de distribuicao
            arquivoConfiguradorDistribuicao = new ArquivoConfiguracaoDistribuicao(arquivoConfiguradorBase);
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "O Core não pôde ser configurado", t);
        }

    }

    public ConfiguradorCoreDeProjetoWebWarAbstrato(boolean modoDesenvolvimento) {

        super(modoDesenvolvimento);
    }

    protected final Properties getPropriedadesArquivoConfiguracaoWar(ServletContext contexto) {

        try {
            Properties pPropriedadesArquivoSBProjeto = new Properties();
            InputStream input = contexto.getResourceAsStream("/WEB-INF/classes/SBProjeto.prop");
            pPropriedadesArquivoSBProjeto.load(input);
            return pPropriedadesArquivoSBProjeto;
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro tentando encontrar arquivo SBProjeto no war, certifique a conformidade dos parametros de buid\resources do seu pom, e a existencia do mesmo na pasta main/src/resourceresource do projeto", t);
            return null;
        }
    }

    @Override
    public void defineClassesBasicas(ItfConfiguracaoCoreCustomizavel pConfiguracao) {
        aplicarDadosArquivoConfiguracao(pConfiguracao);
        pConfiguracao.setCentralDeEventos(CentralLogEventosArqTextoGenerica.class);
        pConfiguracao.setServicoVisualizacao(ServicoVisuaslizacaoWebResponsivo.class);
        pConfiguracao.setCentralDados(CentralDadosWebApp.class);
        //pConfiguracao.setCentralComunicacao(CentralComunicacaoDesktop.class);
        pConfiguracao.setCentralComunicacao(CentralComunicaoWebPadrao.class);
        pConfiguracao.setCentralDeArquivos(CentralDeArquivosWebAppServidorSB.class);
        pConfiguracao.setCentralAtributoDados(CentralAtributosSBPersistencia.class);
        pConfiguracao.setCentralDeLocalizacao(CentraLocalizacaoSBPersistenciaPadrao.class);
        pConfiguracao.setTipoProjeto(FabTipoProjeto.WEB_APP);
        pConfiguracao.setServicoController(ServicoControllerExecucaoLocal.class);
        switch (pConfiguracao.getEstadoApp()) {
            case DESENVOLVIMENTO:
                pConfiguracao.setCentralMEnsagens(CentramMensagemProgramadorMsgStop.class);
                pConfiguracao.setClasseErro(InfoErroSBCoreDeveloperSopMessagem.class);
                pConfiguracao.setControleDeSessao(ControleDeSessaoPadrao.class);
                pConfiguracao.setCentralAtributoDados(CentralAtributosSBPersistencia.class);
                break;
            case PRODUCAO:
                pConfiguracao.setClasseErro(InfoErroSBCoreFW.class);
                pConfiguracao.setControleDeSessao(ControleDeSessaoWeb.class);
                pConfiguracao.setCentralMEnsagens(CentralDeMensagensJSFAPP.class);
                pConfiguracao.setCentralAtributoDados(CentralAtributosWebApp.class);
                break;
            case HOMOLOGACAO:

                pConfiguracao.setControleDeSessao(ControleDeSessaoWeb.class);
                pConfiguracao.setCentralMEnsagens(CentralDeMensagensJSFAPP.class);
                pConfiguracao.setClasseErro(InfoErroSBCoreDeveloperSopMessagem.class);
                pConfiguracao.setCentralAtributoDados(CentralAtributosWebApp.class);
                break;
            default:
                throw new AssertionError(pConfiguracao.getEstadoApp().name());

        }
    }

}
