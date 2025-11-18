package com.super_bits.modulosSB.webPaginas.controller.listenners;

import br.org.coletivojava.fw.utils.agendador.UtilSBAgendadorTarefas;
import com.super_bits.modulos.SBAcessosModel.controller.UtilSBControllerAcessosModel;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.MapaAcoesSistema;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreReflexao;
import com.super_bits.modulosSB.SBCore.modulos.Controller.ErroChamadaController;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfRespostaAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ComoAcaoControllerAutoExecucao;
import com.super_bits.modulosSB.SBCore.modulos.Controller.acoesAutomatizadas.FabTipoAutoExecucaoAcao;
import com.super_bits.modulosSB.webPaginas.ConfigGeral.ConfiguradorCoreDeProjetoWebWarAbstrato;
import com.super_bits.modulosSB.webPaginas.ConfigGeral.ItfInicioFimAppWP;
import com.super_bits.modulosSB.webPaginas.ConfigGeral.SBWebPaginas;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.declarados.Paginas.PgProjetoSBVisaoGeral;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.reflexao.anotacoes.InfoPagina;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.siteMap.MapaDeFormularios;
import java.util.List;
import java.util.ServiceLoader;
import javax.faces.application.ProjectStage;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.SessionCookieConfig;
import javax.servlet.http.HttpSessionListener;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ComoAcaoDoSistema;

/*
 *
 *
 * @author Salvio
 */
public class ContextoWebPaginas implements ServletContextListener {

    public static void buildSisteMap() {

        try {
            List<Class> paginasEncontradas = UtilSBCoreReflexao.getClassesComEstaAnotacao(InfoPagina.class, "com.super_bits");
            List<Class> paginasPlugins = UtilSBCoreReflexao.getClassesComEstaAnotacao(InfoPagina.class, "org.coletivoJava.superBitsFW.webPaginas.plugin");
            paginasPlugins.forEach(paginasEncontradas::add);
            MapaDeFormularios.buildEstrutura(paginasEncontradas);

        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.PARA_TUDO, "Erro construindo o mapa de paginas do Sitemap", t);
        }

    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Iniciando contexto");
        try {
            if (this instanceof HttpSessionListener) {
                System.out.println("");
            }

            ItfInicioFimAppWP inicio = null;
            ServiceLoader<ItfInicioFimAppWP> services = ServiceLoader.load(ItfInicioFimAppWP.class);
            try {
                inicio = services.iterator().next();
            } catch (Throwable t) {
                SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Falha encontrando inicioa do contexto verifique"
                        + "no resources do ModelRegras  a pasta  META-INF/services ", t);
            }
            //   inicio.inicio();

            if (inicio == null) {
                Class classeInicioFim = UtilSBCoreReflexao.getClasseQueEstendeIsto(ItfInicioFimAppWP.class, "com.super_bits.config.webPaginas");
                System.out.println("A classe de inicio e fim de contexto encontrada foi" + classeInicioFim.getName());
                inicio = (ItfInicioFimAppWP) classeInicioFim.newInstance();
                System.out.println("Sistema Iniciado em " + sce.getServletContext().getVirtualServerName());
            }
            ConfiguradorCoreDeProjetoWebWarAbstrato.contextoDoServlet = sce.getServletContext();
            SBWebPaginas.configurarContexto(sce.getServletContext());
            //   String webDir = this.getClass().getClassLoader().getResource("com/company/project/mywebdir").toExternalForm();

            inicio.inicio();

            ServletContext context = sce.getServletContext();
            if (SBCore.isEmModoProducao()) {
                context.setInitParameter("javax.faces.PROJECT_STAGE", ProjectStage.Production.toString());
            } else {
                context.setInitParameter("javax.faces.PROJECT_STAGE", ProjectStage.Development.toString());
            }
            buildSisteMap();
            System.out.println("Construindo SiteMap");

            System.out.println("Fim Construção");
            System.out.println("Listando autoexecuções");
            for (ComoAcaoControllerAutoExecucao acao : MapaAcoesSistema.getListaAcoesAutomatizadas()) {
                System.out.println("Programando " + acao.getNomeUnico());
                switch (acao.getTipoAutoExecucao().getEstrategia()) {
                    case GATILHO:
                        if (acao.getTipoAutoExecucao().equals(FabTipoAutoExecucaoAcao.SISTEMA_BOOT)) {
                            ItfRespostaAcaoDoSistema resposta = SBCore.getServicoController().getResposta(acao.getEnumAcaoDoSistema());
                            if (!resposta.isSucesso()) {
                                try {

                                } catch (Throwable t) {
                                    SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro executando ação de boot:  " + acao.getNomeUnico() + " -> " + resposta.getMensagens().get(0).getMenssagem(), t);
                                }
                            }
                        }
                        break;
                    case DIARIO:
                    case MENSAL:
                    case LOOP:
                    case MINUTOS:
                    case HORAS:
                        UtilSBAgendadorTarefas.agendarAutoExecucaoAcaoProxima(acao);
                        break;
                    default:
                        throw new AssertionError(acao.getTipoAutoExecucao().getEstrategia().name());

                }
            }
            if (!SBCore.isEmModoProducao()) {

            }
            //Atualizando permissões
            if (!SBCore.isIgnorarPermissoes()) {
                UtilSBControllerAcessosModel.criarPermissoesDeAcao(false);
            }

            SessionCookieConfig sessionConfig = sce.getServletContext().getSessionCookieConfig();
            inicio.definirConfiguracoesDeCookie(sessionConfig);

        } catch (Throwable ex) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro carregando Classe de inicio e fim de contexto", ex);

        }

        // PgUtil paginautil = new PgUtil();
        //paginautil.getCores().carregarCores();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Finalizando contexto");

        for (ComoAcaoControllerAutoExecucao acao : MapaAcoesSistema.getListaAcoesAutomatizadas()) {
            switch (acao.getTipoAutoExecucao().getEstrategia()) {
                case GATILHO:
                    if (acao.getTipoAutoExecucao().equals(FabTipoAutoExecucaoAcao.SISTEMA_FINAL)) {
                        ItfRespostaAcaoDoSistema resposta;
                        try {
                            resposta = SBCore.getServicoController().getResposta(acao.getEnumAcaoDoSistema());
                            if (!resposta.isSucesso()) {
                                try {

                                } catch (Throwable t) {
                                    SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro executando ação de boot:  " + acao.getNomeUnico() + " -> " + resposta.getMensagens().get(0).getMenssagem(), t);
                                }
                            }
                        } catch (ErroChamadaController ex) {
                            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Falha executando ação final do bookt", ex);
                        }

                    }
                    break;

            }
        }
    }

}
