/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.controller.servlets.servletWebPaginas;

import com.super_bits.modulosSB.SBCore.modulos.view.telas.ItfEstruturaDeFormuario;
import com.google.common.collect.Lists;
import com.super_bits.modulos.SBAcessosModel.model.acoes.acaoDeEntidade.AcaoGestaoEntidade;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;

import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCReflexao;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCReflexaoObjeto;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringBuscaTrecho;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringFiltros;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringValidador;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfParametroRequisicao;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfParametroRequisicaoInstanciado;
import static com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.TIPO_PARTE_URL.ENTIDADE;
import static com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.TIPO_PARTE_URL.OBJETO_COM_CONSTRUCTOR;
import static com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.TIPO_PARTE_URL.TEXTO;
import com.super_bits.modulosSB.SBCore.modulos.Controller.UtilSBController;
import com.super_bits.modulosSB.SBCore.modulos.objetos.MapaObjetosProjetoAtual;
import com.super_bits.modulosSB.webPaginas.ConfigGeral.SBWebPaginas;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.interfaces.ItfPaginaGerenciarEntidade;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.reflexao.anotacoes.InfoPagina;
import com.super_bits.modulosSB.webPaginas.controller.servletes.urls.parametrosURL.ParametroURL;
import com.super_bits.modulosSB.webPaginas.controller.servletes.urls.parametrosURL.ParametroUrlInstanciado;
import com.super_bits.modulosSB.webPaginas.controller.sessao.SessaoAtualSBWP;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWPServletTools;

import com.super_bits.modulosSB.webPaginas.util.UtillSBWPReflexoesWebpaginas;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ComoAcaoDoSistema;

/**
 *
 * @author salvioF
 */
public class EstruturaDeFormulario implements ItfEstruturaDeFormuario {

    private final AcaoGestaoEntidade acaoGestaoVinculada;
    private final Map<String, ParametroURL> parametrosURL;
    private final List<ParametroURL> parametroOrdenado = new ArrayList();

    private final List<String> tagsPalavraChave;

    private final int quantidadeParametrosObrigatorios;
    private final boolean umaPaginaCadastroEntidade;
    private final String nomeCurto;
    private final boolean acessoLivre;
    private final String urlPadrao;
    private final String recursoXHTML;
    private final boolean umModal;
    private boolean permitirSelecaoRegistroPorURL;

    public EstruturaDeFormulario(Class pClassePAgina) {
        parametrosURL = new HashMap<>();
        tagsPalavraChave = new ArrayList();
        int qtdObrigatorio = 0;
        for (ParametroURL pr : UtillSBWPReflexoesWebpaginas.buildParametrosDaPagina(pClassePAgina)) {
            if (pr.isParametroObrigatorio()) {
                qtdObrigatorio++;
            }
            parametrosURL.put(pr.getNome(), pr);
            parametroOrdenado.add(pr);
        }
        quantidadeParametrosObrigatorios = qtdObrigatorio;

        umaPaginaCadastroEntidade = UtilCRCReflexao.isInterfaceImplementadaNaClasse(pClassePAgina, ItfPaginaGerenciarEntidade.class);

        try {
            InfoPagina anotacaoInfopagina = (InfoPagina) pClassePAgina.getAnnotation(InfoPagina.class);
            if (anotacaoInfopagina == null) {
                throw new UnsupportedOperationException("A pagina" + pClassePAgina.getSimpleName() + " não foi anotada com Info-Pagina");
            }

            if (anotacaoInfopagina.nomeCurto() == null) {
                throw new UnsupportedOperationException(" O nome curto na anotação InfoPagina da Pagina:" + pClassePAgina.getName() + "  não foi configurado");
            }
            //TODO tratar duplicidade de TAGS e Existencia do recurso

            nomeCurto = (anotacaoInfopagina.nomeCurto());
            permitirSelecaoRegistroPorURL = anotacaoInfopagina.permitirSelecaoPorURL();
            acessoLivre = anotacaoInfopagina.acessoLivre();
            umModal = anotacaoInfopagina.umModal();
            for (String tg : anotacaoInfopagina.tags()) {
                tagsPalavraChave.add(tg);
            }
        } catch (Exception e) {

            SBCore.RelatarErro(FabErro.PARA_TUDO, "Erro configurando anotações de infoPagina da pagina" + pClassePAgina, e);
            throw new UnsupportedOperationException("Não  foi possível determinar as palavras chave para a classe" + pClassePAgina.getSimpleName());
        }

        acaoGestaoVinculada = (AcaoGestaoEntidade) UtilSBController.getAcaoByClasse(pClassePAgina);

        try {
            if (acaoGestaoVinculada == null) {
                throw new UnsupportedOperationException("NÃO EXITE AÇÃO VINCULADA AO MANAGED_BEAN" + pClassePAgina.toString());
            }
            urlPadrao = gerarURL(acaoGestaoVinculada);
            recursoXHTML = acaoGestaoVinculada.getXhtml();

            if (urlPadrao == null) {
                throw new UnsupportedOperationException("Impossível determinar a Url da pagina" + pClassePAgina.getSimpleName());
            }

        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "NÃO EXITE AÇÃO VINCULADA AO MANAGED_BEAN " + this.getClass().getSimpleName(), t);
            SBCore.RelatarErro(FabErro.PARA_TUDO, "NÃO EXITE AÇÃO VINCULADA AO MANAGED_BEAN" + pClassePAgina.getSimpleName(), t);
            throw new UnsupportedOperationException("Não  foi possível determinar as palavras chave para a classe" + pClassePAgina.getSimpleName());
        }

        if (tagsPalavraChave == null) {
            throw new UnsupportedOperationException("Não  foi possível determinar as palavras chave para a classe" + pClassePAgina.getSimpleName());
        }

    }

    @Override
    public AcaoGestaoEntidade getAcaoGestaoVinculada() {
        return acaoGestaoVinculada;
    }

    @Override
    public List<ItfParametroRequisicao> getParametrosURL() {
        return Lists.newArrayList(parametrosURL.values());
    }

    public int getQuantidadeParametrosObrigatorios() {
        return quantidadeParametrosObrigatorios;
    }

    @Override
    public String getUrlPadrao() {

        List<ItfParametroRequisicaoInstanciado> listaParametros = new ArrayList();

        parametroOrdenado.forEach(pr -> listaParametros.add(new ParametroUrlInstanciado(pr.getEstrutura())));
        return gerarUrlPorParametro(listaParametros, acaoGestaoVinculada, null);
    }

    private String gerarBaseURL(String tagUsada) {
        SessaoAtualSBWP sessaoAtual = UtilSBWPServletTools.getSessaoAtual(true);
        String urlBase;
        if (sessaoAtual != null) {
            urlBase = sessaoAtual.getUrlHostDaSessao();
        } else {
            urlBase = SBWebPaginas.getURLBase();
        }
        String tagURL = tagUsada;
        if (tagURL == null) {
            tagURL = tagsPalavraChave.get(0);
        }

        return urlBase + "/" + UtilCRCStringFiltros.gerarUrlAmigavel(tagURL);
    }

    public String gerarUrlPorParametro(List<ItfParametroRequisicaoInstanciado> parametros, ComoAcaoDoSistema pAcao, String tagUsada) {
        String url = gerarBaseURL(tagUsada);

        for (ParametroURL pr : parametroOrdenado) {
            boolean enviado = false;
            for (ItfParametroRequisicaoInstanciado prEnviado : parametros) {
                if (prEnviado.getNome().equals(pr.getNome())) {
                    url += "/" + prEnviado.getSlugValorParametro();

                    enviado = true;
                }
            }
            if (!enviado) {
                url += "/";
            }

        }

        String urlAcao = "";
        if (pAcao != null) {
            if (!pAcao.isUmaAcaoGestaoDominio()) {
                urlAcao = "/ac-" + UtilCRCStringFiltros.gerarUrlAmigavel(pAcao.getNomeAcao());
            }
        }

        return url + urlAcao + ".html";

    }

    public String gerarUrlPorValorParametro(ComoAcaoDoSistema pAcao, String tagUsada, Object... parametros) {

        Map<String, List<Object>> mapaParametrosEnviados = new HashMap<>();
        Map<String, Integer> mapaIndicePrAplicados = new HashMap<>();

        if (parametros != null) {

            for (Object obj : parametros) {
                if (obj != null) {
                    String nomeClasse = null;
                    if (mapaParametrosEnviados.get(obj.getClass().getSimpleName()) == null) {
                        nomeClasse = UtilCRCStringBuscaTrecho.getStringAteEncontrarIsto(obj.getClass().getSimpleName(), "$");
                        if (nomeClasse == null) {
                            nomeClasse = obj.getClass().getSimpleName();
                        }
                        mapaParametrosEnviados.put(nomeClasse, new ArrayList());

                    }
                    if (nomeClasse == null) {
                        nomeClasse = obj.getClass().getSimpleName();
                    }
                    List<Class> classesHerenca = UtilCRCReflexaoObjeto.getClassesEntidadeComHeranca(MapaObjetosProjetoAtual.getClasseDoObjetoByNome(nomeClasse));
                    for (Class classe : classesHerenca) {
                        mapaParametrosEnviados.put(classe.getSimpleName(), new ArrayList<>());
                        mapaParametrosEnviados.get(classe.getSimpleName()).add(obj);
                    }

                }
            }

        }

        List<ItfParametroRequisicaoInstanciado> novosParametros = new ArrayList<>();
        if (parametros.length > parametroOrdenado.size()) {
            throw new UnsupportedOperationException("O número de parametro enviado é maior que o número de parametros existentes no formulario");
        }
        for (ParametroURL pr : parametroOrdenado) {
            ParametroUrlInstanciado novoParametro = new ParametroUrlInstanciado(pr.getEstrutura());
            List<Object> parametrosDoTipoEnviado = null;
            String strTipoObjeto = pr.getClasseObjetoValor().getSimpleName();
            switch (pr.getTipoParametro()) {
                case TEXTO:

                case ENTIDADE:
                case OBJETO_COM_CONSTRUCTOR:

                    int indiceParametro = 0;
                    if (mapaIndicePrAplicados.get(strTipoObjeto) == null) {
                        mapaIndicePrAplicados.put(strTipoObjeto, 0);
                    } else {
                        indiceParametro = mapaIndicePrAplicados.get(strTipoObjeto);
                    }
                    parametrosDoTipoEnviado = mapaParametrosEnviados.get(strTipoObjeto);
                    if (parametrosDoTipoEnviado == null && pr.isParametroObrigatorio()) {
                        if (UtilCRCStringValidador.isNuloOuEmbranco(pr.getValorPadrao())) {
                            throw new UnsupportedOperationException("O Atributo obrigatório:" + pr.getNome() + " é um parametro obrigatório, e não foi definido");
                        }

                    }
                    if (parametrosDoTipoEnviado == null) {
                        continue;
                    }
                    if (indiceParametro > parametrosDoTipoEnviado.size() - 1) {
                        if (!pr.isParametroObrigatorio()) {
                            continue;
                        } else {
                            throw new UnsupportedOperationException("Parametro obrigatório não enviado para criação de url" + pr.getNome());
                        }
                    }
                    Object prEnviadoRelacionado = parametrosDoTipoEnviado.get(indiceParametro);
                    mapaIndicePrAplicados.put(strTipoObjeto, indiceParametro + 1);
                    if (prEnviadoRelacionado == null) {
                        novosParametros.add(novoParametro);
                    } else {
                        novoParametro.setValor(prEnviadoRelacionado);
                        novosParametros.add(novoParametro);
                    }

                    break;
                default:
                    throw new AssertionError(pr.getTipoParametro().name());

            }

        }

        return gerarUrlPorParametro(novosParametros, pAcao, tagUsada);

    }

    private String gerarURL(ComoAcaoDoSistema pAcao) {

        List<ItfParametroRequisicaoInstanciado> listaParametros = new ArrayList();

        parametroOrdenado.forEach(pr -> listaParametros.add(new ParametroUrlInstanciado(pr.getEstrutura())));
        return gerarUrlPorParametro(listaParametros, pAcao, null);
    }

    public boolean isAcessoLivre() {
        return acessoLivre;
    }

    public String getNomeCurto() {
        return nomeCurto;
    }

    public boolean isPermitirSelecaoRegistroPorURL() {
        return permitirSelecaoRegistroPorURL;
    }

    public String getRecursoXHTML() {
        return recursoXHTML;
    }

    public List<String> getTagsPalavraChave() {
        return tagsPalavraChave;
    }

    public boolean isUmaPaginaCadastroEntidade() {
        return umaPaginaCadastroEntidade;
    }

    public boolean isUmModal() {
        return umModal;
    }

}
