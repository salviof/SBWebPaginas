/*
 *  Super-Bits.com CODE CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.util;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringFiltros;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfParametroRequisicaoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ComoEntidadeSimples;
import com.super_bits.modulosSB.webPaginas.ConfigGeral.SBWebPaginas;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.MB_PaginaAtual;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.interfaces.ItfPaginaAtual;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.siteMap.MB_SiteMapa;
import com.super_bits.modulosSB.webPaginas.TratamentoDeErros.ErroGenericoProcessandoRespostaJson;
import com.super_bits.modulosSB.webPaginas.controller.servletes.tratamentoErro.ErroSBGenericoWeb;
import com.super_bits.modulosSB.webPaginas.controller.sessao.ControleDeSessaoWeb;
import com.super_bits.modulosSB.webPaginas.controller.sessao.SessaoAtualSBWP;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.inject.spi.BeanManager;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

/**
 *
 * @author Salvio
 */
public class UtilSBWPServletTools {

    private enum pathBean {
        APLICACAO, SESSAO, REQUEST
    }

    /**
     *
     * Obtem o Bean por sua nomeação.
     *
     * Obs: Utiliza FaceContext para obter a instancia atual, ou seja, use
     * apenas dentro de MAnaged Bens, nunca direto no servelet
     *
     * @param pNomeBean
     * @param pClasse
     * @return
     */
    public static Object getBeanByNamed(String pNomeBean, Class pClasse, boolean pIgnorarErro) {

        try {
            FacesContext context = FacesContext.getCurrentInstance();
            if (context == null) {
                throw new UnsupportedOperationException("Impossivel determinar o FacesContext neste momento");
            }
            Object objeto = context.getApplication().evaluateExpressionGet(context, "#{" + pNomeBean + "}", pClasse);

            if (objeto == null) {
                throw new UnsupportedOperationException("Objeto via getEvaluteExpression retornou null");
            } else {
                return objeto;
            }
        } catch (Throwable t) {
            if (!pIgnorarErro) {
                SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "erro Tentando obter objeto [" + pNomeBean + " ]de contexto injetado manualmento por evalutionExpressionGet", t);
            }

            return null;
        }
    }

    public static Cookie cookieGet(HttpServletRequest pRequisicao, String nome) {
        Cookie[] cookies = pRequisicao.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals(nome)) {
                    return c;
                }
            }
        }
        return null;
    }

    public static Cookie cookieGet(String nome) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        if (facesContext == null) {
            return null;
        }
        HttpServletRequest request
                = (HttpServletRequest) facesContext.getExternalContext().getRequest();

        return cookieGet(request, nome);
    }

    public static String cookieLerValor(String nome) {
        Cookie c = cookieGet(nome);
        if (c != null) {
            String valor = c.getValue();
            if (valor != null && !valor.isEmpty()) {
                valor = new String(Base64.getUrlDecoder().decode(valor), StandardCharsets.UTF_8
                );
                return valor;
            } else {
                return null;
            }

        }
        return null;
    }

    public static void cookieAdicionar(String nome, String valor, int tempoDeVidaSegundos) {
        try {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            HttpServletResponse response
                    = (HttpServletResponse) facesContext.getExternalContext().getResponse();
            if (tempoDeVidaSegundos <= 0) {
                tempoDeVidaSegundos = 34560000;//40 dias (MAximo do crhome)
            }
            String valorSuporteAcentuacao = Base64.getUrlEncoder().encodeToString(valor.getBytes(StandardCharsets.UTF_8));

            Cookie cookie = new Cookie(nome, valorSuporteAcentuacao);
            cookie.setMaxAge(tempoDeVidaSegundos);  // em segundos (ex: 3600 = 1h)
            cookie.setPath("/");                     // define o escopo (raiz do app)
            cookie.setHttpOnly(true);                // impede acesso via JS (segurança)
            cookie.setSecure(false);                 // true se for HTTPS

            response.addCookie(cookie);
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "", t);
        }
    }

    public static Object getObjetoByInstanciadoViewScopedByExpressao(String pExpressao, Class pClasse) throws ErroGenericoProcessandoRespostaJson {
        try {
            if (!pExpressao.startsWith("#{") && !pExpressao.endsWith("}")) {
                throw new ErroGenericoProcessandoRespostaJson("Expressão " + pExpressao + " não foi encontrada");
            }
            FacesContext context = FacesContext.getCurrentInstance();
            if (context == null) {
                throw new UnsupportedOperationException("Impossivel determinar o FacesContext neste momento");
            }
            Object objeto = context.getApplication().evaluateExpressionGet(context, pExpressao, pClasse);

            if (objeto == null) {
                throw new UnsupportedOperationException("Objeto via getEvaluteExpression retornou null");
            } else {
                return objeto;
            }
        } catch (Throwable t) {

            throw new ErroGenericoProcessandoRespostaJson(t);

        }

    }

    public static String getCorpoRequisicaoI(HttpServletRequest pRequisicao) {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;
        try {
            InputStream inputStream = pRequisicao.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            } else {
                stringBuilder.append("");
            }
        } catch (IOException ex) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Falha processando corpor da resicao" + ex.getMessage(), ex);
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Falha processando corpor da resicao" + ex.getMessage(), ex);
                }
            }
        }
        //Store request pody content in 'body' variable
        return stringBuilder.toString();
    }

    public static Object getBeanByNamed(String pNomeBean, Class pClasse) {
        return getBeanByNamed(pNomeBean, pClasse, false);
    }

    public static String geCaminhoRecursoDoUrl(HttpServletRequest requisicao) {
        String caminhoCOmpleto = "nãoDefinido";
        try {

            return requisicao.getRequestURI();
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro obtendo caminho para recuros da requisição " + requisicao + " url=" + caminhoCOmpleto, t);
            return null;
        }
    }

    public static HttpServletRequest getRequestAtual() {
        try {
            return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Falha obtendo requisição do contecto atual", t);
            return null;
        }
    }

    /**
     *
     * Retorna a sessão atual via Faces Context
     *
     * @return
     */
    public static SessaoAtualSBWP getSessaoAtual() {
        return getSessaoAtual(false);
    }

    public static SessaoAtualSBWP getSessaoAtual(boolean pIgnorarErro) {
        try {
            if (SBCore.getEstadoAPP() == SBCore.ESTADO_APP.DESENVOLVIMENTO) {
                return new SessaoAtualSBWP();
            }

            SessaoAtualSBWP sessao = (SessaoAtualSBWP) getBeanByNamed("sessaoAtualSBWP", SessaoAtualSBWP.class, pIgnorarErro);
            return sessao;
        } catch (Exception e) {
            if (!pIgnorarErro) {
                SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro Tentando obter sesao atual por el", e);
            }
            return null;
        }
    }

    /**
     *
     * retorna o controle de sessao via FacesContext
     *
     * @return
     */
    public static ControleDeSessaoWeb getControleDeSessaoWeb() {
        try {
            if (FacesContext.getCurrentInstance() == null) {
                return new ControleDeSessaoWeb();
            }
            ControleDeSessaoWeb controle = (ControleDeSessaoWeb) getBeanByNamed("controleDeSessaoWeb", ControleDeSessaoWeb.class);
            return controle;
        } catch (Exception e) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro Tentando obter controle de sessao por EL", e);
            return null;
        }
    }

    public MB_SiteMapa getSiteMap() {
        return null;
    }

    public MB_PaginaAtual getPaginaAtual() {
        try {
            ItfPaginaAtual controle = (ItfPaginaAtual) getBeanByNamed("paginaAtual", ControleDeSessaoWeb.class);
            return (MB_PaginaAtual) controle;
        } catch (Exception e) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro Tentando obter pagina Atual por EL", e);
            return null;
        }
    }

    public static Map<String, String> getParametrosDaAplicacao(String pURL) {
        return new HashMap<>();
    }

    public static String getIpCliente() {

        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }
        System.out.println("ip:" + ipAddress);
        if (ipAddress.equals("0:0:0:0:0:0:0:1")) {
            ipAddress = "127.0.0.1";
        }
        return ipAddress;
    }

    /**
     *
     * Retorna o caminho da pasta WebAPP onde a aplicação SERVLET está
     * instalada. Em caso de não encontrar, retorna o caminho da pasta de
     * desenvolvimento
     *
     * @return Retorna o caminho onde a aplicação SERVLET está instalada. Em
     * caso de não encontrar, retorna o caminho da pasta de desenvolvimento
     */
    public static String getCaminhoLocalServlet() {

        try {

            if (SBWebPaginas.getCaminhoRealJavaWebAppContexto() != null) {
                return SBWebPaginas.getCaminhoRealJavaWebAppContexto();
            }
            //Caso o caminho não esteja configurado tentar buscar por contexto de execução

            FacesContext contextoVisualizacaoAtual = FacesContext.getCurrentInstance();
            // String teste = context.getRealPath("/");

            if (contextoVisualizacaoAtual == null) {
                //TODO VA PARA VIEWEXPIRED
                //UtilSBWP_JSFTools.vaParaPaginaInicial();
                throw new UnsupportedOperationException("Contexto não pôde ser obtido utilizando FaceContex para determinar caminho local do servlet");
            }
            ExternalContext contextoExterno = contextoVisualizacaoAtual.getExternalContext();

            ServletContext scontext = (ServletContext) contextoExterno.getContext();

            return scontext.getRealPath("/");
        } catch (Exception e) {

            if (SBCore.getEstadoAPP() == SBCore.ESTADO_APP.DESENVOLVIMENTO) {
                System.out.println("UTILIZANDO PASTA RESOURCE DE DESENVOLVIMENTO." + e.getMessage() + e.getClass());
                return SBWebPaginas.getCaminhoWebAppDeveloper();
            } else {
                SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Ouve uma tentativa de obter o caminho local do Servlet fi FacesContext, e esta tentativa falhou!", e);

                return null;

            }
        }

    }

    /**
     * Este metodo deve ser movido para o facesUtil pois utiliza o contecto
     * atual do faces para obter o caminho
     *
     * @return Retorna o caminho da pasta resources do WEBApp, em caso de não
     * ser possível encontrar utiliza a pasta de desenvolvimento
     */
    @Deprecated
    public static String getCaminhoLocalServletsResource() {
        return getCaminhoLocalServlet() + "/resources";
    }

    public static String getCaminhoLocalWebAppServlet() {
        return getCaminhoLocalServlet() + "/resources";
    }

    /**
     *
     * Coloca um objeto no request Scoped, utiliza FacesContext para obter o
     * request
     *
     * @param pnome nome do Atributo
     * @param pObjeto Objeto que será amazenado
     */
    public static void putObjetoRequestScope(String pnome, Object pObjeto) {
        HttpServletRequest request = (HttpServletRequest) FacesContext
                .getCurrentInstance().getExternalContext().getRequest();
        request.setAttribute(pnome, pObjeto);
    }

    /**
     *
     * Coloca um objeto no Session Scoped, utiliza FaceContext para obter o
     * request
     *
     * @param pnome nome do atributo
     * @param pObjeto Objeto que será armazenado
     */
    public static void putObjetoSessionScope(String pnome, Object pObjeto) {
        HttpServletRequest request = (HttpServletRequest) FacesContext
                .getCurrentInstance().getExternalContext().getRequest();
        request.getSession().setAttribute(pnome, pObjeto);
    }

    /**
     *
     * @return
     */
    public static String listaMBView() {

        Map<String, Object> viewMap = FacesContext.getCurrentInstance()
                .getViewRoot().getViewMap();
        String resposta = "";
        for (Map.Entry<String, Object> viewBean : viewMap.entrySet()) {
            resposta = resposta.concat(viewBean.getKey());
        }
        return resposta;
    }

    /**
     *
     * Busca um artibuto armazenado em uma requisição
     *
     * @param pNomeBean nome do Atributo
     * @return Objeto armazenado
     */
    public static Object getRequestAtribute(String pNomeBean) {

        try {
            HttpServletRequest request = (HttpServletRequest) FacesContext
                    .getCurrentInstance().getExternalContext().getRequest();
            if (request == null) {
                return null;
            }
            Object obj = request.getAttribute(pNomeBean);

            return obj;
        } catch (Exception e) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro tentando obter o bean RequestScoped:" + pNomeBean, e);
            return null;
        }
    }

    /**
     *
     * Procura um parametro que foi enviado em uma requisição e retorna seu
     * valor (Utiliza facesContext para obter a requisição)
     *
     * @param pNomeParametro Nome do parametro
     * @return valor do parametro ou nulo se parametro não encontrado
     */
    public static Object getRequestParametro(String pNomeParametro) {

        try {
            return getRequestParametro(pNomeParametro, true);
        } catch (ErroSBGenericoWeb ex) {
            Logger.getLogger(UtilSBWPServletTools.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    public static Object getRequestParametro(String pNomeBean, boolean ignorarAusencia) throws ErroSBGenericoWeb {
        Map<String, String> parametros = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Object resposta = parametros.get(pNomeBean);
        if (resposta == null) {
            if (!ignorarAusencia) {
                throw new ErroSBGenericoWeb("Parametro de requisição " + pNomeBean + " não enviado");
            }
        }
        return resposta;
    }

    /**
     *
     * Retorna o valor de um atributo de evento
     *
     * Os atributos de eventos são configurados através do <f:attribute dentro
     * de command Buttons
     *
     *
     * @param event
     * @param pNome
     * @return
     */
    public static ComoEntidadeSimples getActionBeanSimples(ActionEvent event, String pNome) {
        try {
            ComoEntidadeSimples resposta = (ComoEntidadeSimples) event.getComponent().getAttributes().get(pNome);
            if (resposta == null) {
                SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Atributo de Evento JSF não encontrado:" + pNome + " para o evento" + event.toString(), null);
            }
            return resposta;
        } catch (Exception e) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro ao converter o atributo de Evento JSF " + pNome + " para o evento" + event.toString(), null);
        }

        return null;

    }

    private static Object getBean(pathBean pathb, String pNomeBean) {

        ExternalContext externalContext = FacesContext.getCurrentInstance()
                .getExternalContext();
        Map<String, Object> mapaBeans = null;
        switch (pathb) {
            case APLICACAO:

                mapaBeans = externalContext.getApplicationMap();
                Set<Map.Entry<String, Object>> appObjts = externalContext
                        .getApplicationMap().entrySet();

                for (Map.Entry<String, Object> registro : appObjts) {
                    String nome = registro.getKey();
                    Object beandeSessao = registro.getValue();
                    if (nome.endsWith(".spi.BeanManager")) {
                        BeanManager testeBM = (BeanManager) beandeSessao;

                        //     Bean bean = testeBM.gegetBeans("dados").iterator().next();
                        //      CreationalContext ctx = testeBM
                        //                .createCreationalContext(bean); // could be inlined
                        // below
                        return testeBM;
                        //          Object o = testeBM.get(, bean.getBeanClass(),
                        //                ctx);

                    }

                }

                break;
            case SESSAO:
                mapaBeans = externalContext.getSessionMap();
                break;
            case REQUEST:
                mapaBeans = externalContext.getRequestMap();
                break;

            default:
                break;
        }

        Set<Map.Entry<String, Object>> teste = mapaBeans.entrySet();

        for (Map.Entry<String, Object> registro : teste) {
            String nome = registro.getKey();

            if (nome.endsWith(pNomeBean)) {
                return UtilSBWP_JSFTools.retirarProxyDeVisualizacaoDoBean(registro.getValue());
            }

        }
        return UtilSBWP_JSFTools.retirarProxyDeVisualizacaoDoBean(pNomeBean);

    }

    /**
     *
     * Retorna o bean enviado no escopo de sessão do contexto atual
     *
     * @param pNomeBean
     * @return
     *
     */
    public static Object getSessionBean(String pNomeBean) {
        return getBean(pathBean.SESSAO, pNomeBean);
    }

    /**
     *
     * Retorna o bean enviado no escopo de Aplicação do contexto da face atual
     *
     *
     * @param pNomeBean
     * @return
     * @deprecated Tenta obter um bean instanciado via CDI
     */
    public static Object getAppBean(String pNomeBean) {
        return getBean(pathBean.APLICACAO, pNomeBean);

    }

    /**
     *
     * @return a Url da requisição, utiliza FacesContext para obter a url
     */
    public static String getUrlDigitada() {
        HttpServletRequest origRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String urlVerdadeira = origRequest.getRequestURL().toString();

        return (String) SBWebPaginas.getSiteHost() + origRequest.getAttribute("javax.servlet.forward.request_uri");
    }

    public static List<Field> getCamposReflexcaoInjetados(Class pClasse) {
        List<Field> camposInjetados = new ArrayList<>();

        for (Field campo : pClasse.getDeclaredFields()) {
            if (campo.getAnnotation(Inject.class) != null) {
                camposInjetados.add(campo);
            }
        }

        return camposInjetados;

    }

    public static void setValoresParametrosByUrl(Map<String, ItfParametroRequisicaoInstanciado> pParametros) {
        try {
            pParametros.keySet().forEach((prStr) -> {
                try {
                    pParametros.get(UtilSBCoreStringFiltros.gerarUrlAmigavel(prStr)).setValor(getRequestParametro(prStr));
                } catch (Throwable t) {
                    throw new UnsupportedOperationException("Erro configurando parametro " + prStr);
                }

            });

        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro configurando parametros da pagina pela URL" + pParametros, t);

        }
    }

    public static void logRequest(HttpServletRequest request) {
        System.out.println("========== NOVA REQUISIÇÃO ==========");

        // Informações básicas
        System.out.println("Método HTTP: " + request.getMethod());
        System.out.println("URL: " + request.getRequestURL().toString());
        System.out.println("URI: " + request.getRequestURI());
        System.out.println("Query String: " + request.getQueryString());
        System.out.println("Remote Addr: " + request.getRemoteAddr());
        System.out.println("Remote Host: " + request.getRemoteHost());
        System.out.println("User-Agent: " + request.getHeader("User-Agent"));

        // Cabeçalhos
        System.out.println("\n--- Cabeçalhos ---");
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String header = headerNames.nextElement();
            System.out.println(header + ": " + request.getHeader(header));
        }

        // Parâmetros
        System.out.println("\n--- Parâmetros ---");
        for (Map.Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
            String paramName = entry.getKey();
            String[] values = entry.getValue();
            System.out.print(paramName + ": ");
            for (int i = 0; i < values.length; i++) {
                System.out.print(values[i]);
                if (i < values.length - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println();
        }

        System.out.println("=====================================");
    }

}
