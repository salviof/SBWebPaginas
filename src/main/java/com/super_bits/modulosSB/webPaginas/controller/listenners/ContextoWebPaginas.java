package com.super_bits.modulosSB.webPaginas.controller.listenners;

import com.google.common.collect.UnmodifiableIterator;
import com.super_bits.modulos.SBAcessosModel.controller.UtilSBControllerAcessosModel;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreReflexao;
import com.super_bits.modulosSB.webPaginas.ConfigGeral.ConfiguradorCoreDeProjetoWebWarAbstrato;
import com.super_bits.modulosSB.webPaginas.ConfigGeral.ItfInicioFimAppWP;
import com.super_bits.modulosSB.webPaginas.ConfigGeral.SBWebPaginas;
import java.util.ServiceLoader;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 *
 * @author Salvio
 */
public class ContextoWebPaginas implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Iniciando contexto");
        try {

            ServiceLoader<ItfInicioFimAppWP> services = ServiceLoader.load(ItfInicioFimAppWP.class);

            ItfInicioFimAppWP inicio = services.iterator().next();
            //   inicio.inicio();

            if (inicio == null) {
                Class classeInicioFim = UtilSBCoreReflexao.getClasseQueEstendeIsto(ItfInicioFimAppWP.class, "com.super_bits.config.webPaginas");
                com.google.common.collect.Sets teste = null;
                System.out.println("A classe de inicio e fim de contexto encontrada foi" + classeInicioFim.getName());

                inicio = (ItfInicioFimAppWP) classeInicioFim.newInstance();

                System.out.println("Sistema Iniciado em " + sce.getServletContext().getVirtualServerName());
            }
            ConfiguradorCoreDeProjetoWebWarAbstrato.contextoDoServlet = sce.getServletContext();
            SBWebPaginas.configurarContexto(sce.getServletContext());
            //   String webDir = this.getClass().getClassLoader().getResource("com/company/project/mywebdir").toExternalForm();

            inicio.inicio();

            if (!SBCore.isEmModoProducao()) {
                //System.out.println("Os seguintes resources foram encontrados:");
                // ServletContext contexto = sce.getServletContext();
                //  System.out.println(contexto.getClass().getCanonicalName());
                //    URL recursoTEste = sce.getServletContext().getResource("/site/modulos/gestaoRequisitos/gerenciarRequisitosSolicitados.xhtml");

                // for (String caminho : ConfiguradorCoreDeProjetoWebWarAbstrato.contextoDoServlet.getResourcePaths("/")) {
                //     System.out.println(caminho);
                // }
            }
            //Atualizando permissões
            if (!SBCore.isIgnorarPermissoes()) {
                UtilSBControllerAcessosModel.criarPermissoesDeAcao(false);
            }

        } catch (Throwable ex) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro carregando Classe de inicio e fim de contexto", ex);

        }

        // PgUtil paginautil = new PgUtil();
        //paginautil.getCores().carregarCores();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Finalizando contexto");
    }

}
