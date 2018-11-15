/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFManagedBeans.siteMap;

import com.google.common.collect.Lists;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringFiltros;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringValidador;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.modulosSB.webPaginas.ConfigGeral.SBWebPaginas;
import com.super_bits.modulosSB.webPaginas.controller.servletes.servletWebPaginas.EstruturaDeFormulario;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

/**
 *
 *
 *
 *
 * @author desenvolvedor
 */
public class MapaDeFormularios {

    private static Map<String, EstruturaDeFormulario> mapaFormulariosByXhtmlPrincipal;
    private static Map<String, EstruturaDeFormulario> mapaFormulariosByAcaoGestao;
    private static Map<String, Class> mapaClasseByAcaoGestao;
    private static Map<String, EstruturaDeFormulario> mapaFormulariosByNomeCompletoClasse;
    private static Map<String, EstruturaDeFormulario> mapaFormulariosBySlug;

    private static boolean criouEestrutura = false;

    public static void buildEstrutura(List<Class> paginas) {

        if (criouEestrutura) {
            return;
        }
        mapaFormulariosByXhtmlPrincipal = new HashMap<>();
        mapaFormulariosByNomeCompletoClasse = new HashMap<>();
        mapaFormulariosByAcaoGestao = new HashMap<>();
        mapaFormulariosBySlug = new HashMap<>();
        mapaFormulariosBySlug = new HashMap<>();
        mapaClasseByAcaoGestao = new HashMap<>();
        criouEestrutura = true;

        paginas.forEach((classePagina) -> {
            try {
                buildEstrutura(classePagina);
            } catch (Throwable t) {
                SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro criando estrutura de formulário para " + classePagina.getSimpleName(), t);
            }
        });

    }

    public static EstruturaDeFormulario getEstruturaByXHTMLDeGestao(String xhtml) {
        return mapaFormulariosByXhtmlPrincipal.get(xhtml);
    }

    private static void buildEstrutura(Class pClasseFormulario) {

        if (mapaFormulariosByNomeCompletoClasse.get(pClasseFormulario.getName()) != null) {
            return;
        }
        EstruturaDeFormulario estrutura = new EstruturaDeFormulario(pClasseFormulario);
        mapaClasseByAcaoGestao.put(estrutura.getAcaoGestaoVinculada().getNomeUnico(), pClasseFormulario);
        mapaFormulariosByAcaoGestao.put(estrutura.getAcaoGestaoVinculada().getNomeUnico(), estrutura);

        mapaFormulariosByXhtmlPrincipal.put(
                estrutura.getAcaoGestaoVinculada().getEnumAcaoDoSistema().getRegistro().getComoFormulario().getXhtml(),
                mapaFormulariosByAcaoGestao.get(estrutura.getAcaoGestaoVinculada().getNomeUnico()));

        mapaFormulariosByNomeCompletoClasse.put(
                pClasseFormulario.getName(),
                mapaFormulariosByAcaoGestao.get(estrutura.getAcaoGestaoVinculada().getNomeUnico()));

        mapaFormulariosBySlug.put(
                estrutura.getNomeCurto(),
                mapaFormulariosByAcaoGestao.get(estrutura.getAcaoGestaoVinculada().getNomeUnico()));

        mapaFormulariosBySlug.put(UtilSBCoreStringFiltros.gerarUrlAmigavel(estrutura.getAcaoGestaoVinculada().getModulo().getNome() + "_" + estrutura.getAcaoGestaoVinculada().getNomeAcao()),
                mapaFormulariosByAcaoGestao.get(estrutura.getAcaoGestaoVinculada().getNomeUnico()));

        estrutura.getTagsPalavraChave().stream().forEach((tag) -> {
            mapaFormulariosBySlug.put(UtilSBCoreStringFiltros.gerarUrlAmigavel(tag),
                    mapaFormulariosByAcaoGestao.get(estrutura.getAcaoGestaoVinculada().getNomeUnico()));
        });

    }

    public static EstruturaDeFormulario getPaginaBySlug(String pSlug) {
        return mapaFormulariosBySlug.get(pSlug);
    }

    public static List<EstruturaDeFormulario> getTodasEstruturas() {
        return Lists.newArrayList(mapaFormulariosByAcaoGestao.values());
    }

    public static EstruturaDeFormulario getEstruturaByClasseMB(Class pClasse) {

        EstruturaDeFormulario est = mapaFormulariosByNomeCompletoClasse.get(pClasse.getName());
        if (est == null) {
            buildEstrutura(pClasse);
        }
        return getEstruturaByClasseMB(pClasse.getName());

    }

    public static Class getClasseMBByAcaoGestaoNomeUnico(String pNomeUnico) {
        return mapaClasseByAcaoGestao.get(pNomeUnico);
    }

    public static EstruturaDeFormulario getEstruturaByClasseMB(String pClasse) {
        return getEstruturaByClasseMB(pClasse, true);
    }

    public static EstruturaDeFormulario getEstruturaByClasseMB(String pClasse, boolean pGerarErroSeNaoEncontrar) {
        if (pClasse.contains("$")) {
            pClasse = pClasse.split("\\$")[0];
        }
        EstruturaDeFormulario est = mapaFormulariosByNomeCompletoClasse.get(pClasse);
        if (est == null) {
            if (pGerarErroSeNaoEncontrar) {
                throw new UnsupportedOperationException("A estrutura de pagnia vinculada a classe[" + pClasse + "] não foi encontrada, talvez ela não tenha sido injetada no SiteMapa, ou a pagina não esteja anotada com infoAcao (o nome enviado deve ser o nome completo e não SimpleName)");
            }
        }
        return est;

    }

    public static EstruturaDeFormulario getEstruturaByNomeAcao(String pnomeAcao) {

        return getEstruturaByNomeAcao(pnomeAcao, true);

    }

    public static EstruturaDeFormulario getEstruturaByNomeAcao(String pnomeAcao, boolean pGerarErro) {

        EstruturaDeFormulario est = mapaFormulariosByAcaoGestao.get(pnomeAcao);
        if (est == null) {
            if (pGerarErro) {
                throw new UnsupportedOperationException("A estrutura de pagnia vinculada a ação" + pnomeAcao + " não foi encontrada,  utilize Acao.getNomeUnico, e certifique que está ação tenha sido declarada no core.");
            }
        }
        return est;

    }

    public static String getUrlFormulario(ItfAcaoDoSistema pAcao, Object... parametros) {
        try {
            EstruturaDeFormulario strtura = mapaFormulariosByXhtmlPrincipal.get(pAcao.getAcaoDeGestaoEntidade().getXhtml());
            String url = strtura.gerarUrlPorValorParametro(pAcao, null, parametros);
            return url;
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro determinando url de formulario para ação" + pAcao + " com parametros" + parametros, t);
        }
        return SBWebPaginas.getURLBase();
    }

}
