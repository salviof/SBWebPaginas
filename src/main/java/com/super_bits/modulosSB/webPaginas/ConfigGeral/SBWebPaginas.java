package com.super_bits.modulosSB.webPaginas.ConfigGeral;

import com.super_bits.modulosSB.webPaginas.controller.listenners.ContextoWebPaginas;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.ConfigGeral.arquivosConfiguracao.ArquivoConfiguracaoDistribuicao;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringValidador;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoFormulario;
import com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo.UtilCRCArquivos;
import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.UtilCRCErros;

import com.super_bits.modulosSB.webPaginas.controller.servlets.WebPaginasServlet;
import com.super_bits.modulosSB.webPaginas.controller.servletes.urls.parametrosURL.ParametroURL;
import java.util.List;
import javax.servlet.ServletContext;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

public abstract class SBWebPaginas {

    public static final String DIRETORIO_PADRAO_MODULO_WEBAPP_FW_DESENVOLVIMENTO = "/home/superBits/projetos/coletivoJava/source/fw/SBWebPaginas";
    // variaveis temporarias apenas para compatibilidade
    // VARIAVEIS DE ARQUIVAR_LOG
    private static String SITE_HOST;
    private static Integer porta = 8080;
    private static String pastaImagens;
    private static String nomePacoteProjeto;
    private static String SITE_URL;
    private static String TituloAppWeb;
    private static String URLBASE = "";

    private static List<String> URLS_HOSTS_PERMITIDOS;

    private static boolean configurado = false;
    private static Class siteMap;

    private static List<ParametroURL> parametros;
    private static boolean parametroEmSubdominio;
    private static ItfAcaoFormulario acaoPaginaInicial;
    private static String caminhoRealJavaWebAppContexto;
    private static String pacotePaginas;

    public static void configurarContexto(ServletContext pContexto) {
        caminhoRealJavaWebAppContexto = pContexto.getRealPath("/");
        System.out.println("Sistema configurado em " + pContexto.getVirtualServerName());

    }

    public static String getCaminhoRealJavaWebAppContexto() {
        return caminhoRealJavaWebAppContexto;
    }

    public static boolean isAmbienteConfigurado() {
        return configurado;
    }

    public static String getPacotePaginas() {
        return pacotePaginas;
    }

    public static void configurar(ItfConfigWebPagina config) {
        System.out.println("INICIANDO CONFIGURADOR WEBPAGINAS");
        String urlDesenvolvimento = "http://localhost:8080";
        SITE_HOST = config.SITE_HOST();
        pastaImagens = config.pastaImagens();
        nomePacoteProjeto = config.nomePacoteProjeto();
        SITE_URL = config.SITE_HOST();
        System.out.println("siteURL=" + SITE_URL);
        TituloAppWeb = config.TituloAppWeb();
        URLBASE = config.URLBASE();
        parametros = config.parametrosDeAplicacao();
        System.out.println("Módulo Webpaginas Configurado");
        siteMap = config.mapaSite();
        configurado = true;
        pacotePaginas = config.getPacotePaginas();
        acaoPaginaInicial = config.getAcaoPaginaInicial();
        URLS_HOSTS_PERMITIDOS = config.getSitesHostsAutorizados();
        ArquivoConfiguracaoDistribuicao distribuicao = SBCore.getArquivoDistribuicao();

        if (distribuicao == null) {
            System.out.println("O arquivo de implantação ainda não foi configurado, esta aplicação rodara no modo localhost:8080/" + nomePacoteProjeto);
        }
        //  URLBASE = urlDesenvolvimento + "/" + config.nomePacoteProjeto();
        if (SBCore.isEmModoProducao()) {
            SITE_HOST = config.SITE_HOST();
            SITE_URL = config.URLBASE();
            URLBASE = config.URLBASE();
        } else {
            if (UtilCRCStringValidador.isNuloOuEmbranco(config.SITE_HOST())) {
                URLBASE = urlDesenvolvimento;
                SITE_HOST = urlDesenvolvimento;
            } else {
                URLBASE = config.URLBASE();
                SITE_HOST = config.SITE_HOST();
            }
            //URLBASE = urlDesenvolvimento + "/" + config.nomePacoteProjeto();

            SITE_URL = URLBASE;
        }
        if (SBCore.isEmModoDesenvolvimento()) {
            try {
                System.out.println("Nome Projeto" + SBCore.getNomeProjeto());
                if (SBCore.getNomeProjeto().equals("webApp")) {
                    UtilCRCArquivos.copiarArquivoResourceJar(config.getClass(), "SBProjeto.prop", SBCore.getCaminhoGrupoProjetoSource() + "/SBProjeto.prop");
                }
                ContextoWebPaginas.buildSisteMap();
                WebPaginasServlet construindoSiteMap = new WebPaginasServlet();

            } catch (Throwable t) {

            }
        }
    }

    private static void validaConfigurado() {
        if (configurado) {
            return;
        }

        SBCore.RelatarErro(FabErro.PARA_TUDO,
                "CONFIG DO SBWEBPAGINAS NAO DEFINIDO", null);

        try {
            throw new UnsupportedOperationException("CONFIG webapp NAO DEFINIDO");
        } catch (Exception e) {
            UtilCRCErros.getResumoErro(e);
        }
        System.exit(0);

    }

    /**
     *
     * @return Endereço de acesso externo ao site exemplo
     * http://www.meusiteOuIp.com.br /*
     */
    public static String getSiteURL() {
        validaConfigurado();
        return SITE_URL;
    }

    /**
     * Caso exista um prefixo após a "/" em toda URL da aplicação
     */
    public static String getURLBase() {
        validaConfigurado();
        return URLBASE;
    }

    public static List<String> getURLSHostsPermitidos() {
        validaConfigurado();
        return URLS_HOSTS_PERMITIDOS;
    }

    /**
     * Titulo da Aplicação (aparece no titulo da pagina se não especificado)
     */
    public static String getTituloApp() {
        validaConfigurado();
        return TituloAppWeb;
    }

    /**
     * caminho para pasta base onde ficaram as imagens do site (sem a url
     * completa)
     */
    public static String getPastaBaseImagens() {
        validaConfigurado();
        return pastaImagens;
    }

    /**
     * endereço com a porta caso diferente da porta 80 exemplo:
     * https://MeuEnderecoOuIp:666
     *
     * @return endereço do host+porta
     */
    public static String getSiteHost() {
        validaConfigurado();
        return SITE_HOST;
    }

    /**
     *
     * @return Classe SiteMap
     */
    public static Class getSiteMap() {
        validaConfigurado();
        return siteMap;
    }

    /**
     *
     * @return especifica parametros da aplicação nescessários em todas as
     * paginas
     */
    public static List<ParametroURL> getParametrosAplicacao() {
        validaConfigurado();
        return parametros;
    }

    /**
     *
     * @return especifica se os parametros da aplicação vão estar em subdominios
     * ou em url
     */
    public static boolean isParametrosEmSubdominios() {
        validaConfigurado();
        return parametroEmSubdominio;
    }

    public static String getCaminhoWebAppDeveloper() {
        return SBCore.getCaminhoDesenvolvimento() + "/src/main/webapp";
    }

    public static String getCaminhoWebResourcesDeveloper() {
        return SBCore.getCaminhoDesenvolvimento() + "/src/main/webapp/resources";
    }

    public static String getCaminhoWebSBCompDeveloper() {
        return SBCore.getCaminhoDesenvolvimento() + "/src/main/webapp/resources/SBComp";
    }

    public static String getCaminhoWebExemplorsDeveloper() {
        return SBCore.getCaminhoDesenvolvimento() + "/src/main/webapp/resources/exemplos";
    }

    public static ItfAcaoFormulario getAcaoPAginaInicial() {
        return acaoPaginaInicial;
    }

    public static String getNomePacote() {
        return nomePacoteProjeto;
    }

}
