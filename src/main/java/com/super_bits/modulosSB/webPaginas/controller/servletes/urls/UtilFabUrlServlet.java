/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.controller.servletes.urls;

import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringValidador;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.TIPO_PARTE_URL;
import com.super_bits.modulosSB.webPaginas.ConfigGeral.SBWebPaginas;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.reflexao.anotacoes.beans.InfoParametroURL;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWPServletTools;
import com.super_bits.modulosSB.webPaginas.util.UtillSBWPReflexoesWebpaginas;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

/**
 *
 * @author desenvolvedor
 */
public class UtilFabUrlServlet {

    private static final Map<ItfFabUrlServletSBFW, ParteURLServlet> partesCarregadas = new HashMap<>();
    private static final Map< Class< ? extends ItfFabUrlServletSBFW>, Boolean> mapaPossuiEntidade = new HashMap<>();

    public static ParteURLServlet getParteURL(final ItfFabUrlServletSBFW pParteURL) {
        try {
            Field cp = pParteURL.getClass().getField(pParteURL.toString());
            ParteURLServlet parte = new ParteURLServlet(UtillSBWPReflexoesWebpaginas.getInfoParametroDeUrl(cp));
            return parte;
        } catch (Throwable t) {
            if (pParteURL != null) {
                SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro criando parte de url da fabrica" + pParteURL.getClass().getSimpleName() + "-" + pParteURL, t);
            } else {
                SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro criando parte de url, a Fabrica não foi enviada!! impossível determinar a parte", t);
            }
            return null;
        }

    }

    public static boolean possuiEntidade(Class< ? extends ItfFabUrlServletSBFW> pfabricaURL) {
        try {
            Boolean possuiEntidade = mapaPossuiEntidade.get(pfabricaURL);
            if (possuiEntidade != null) {
                return possuiEntidade;
            }
            for (ItfFabUrlServletSBFW pFabrica : pfabricaURL.getEnumConstants()) {

                Field campo = pFabrica.getClass().getField(pFabrica.toString());
                InfoParametroURL infoPr = UtillSBWPReflexoesWebpaginas.getInfoParametroDeUrl(campo);
                if (infoPr.tipoParametro().equals(TIPO_PARTE_URL.ENTIDADE)) {
                    mapaPossuiEntidade.put(pfabricaURL, true);
                    return true;
                }

            }
            mapaPossuiEntidade.put(pfabricaURL, false);
            return false;
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro verificando se a Fabrica de URL possui registro de entidade", t);
            return false;
        }
    }

    public static UrlInterpretada getUrlInterpretada(Class<? extends ItfFabUrlServletSBFW> fabrica,
            HttpServletRequest requisicao
    ) throws Throwable {

        try {
            List<String> slugsURL = getListaStringsParametroURL(requisicao);
            if (possuiEntidade(fabrica)) {
                EntityManager em = UtilSBPersistencia.getNovoEM();
                return new UrlInterpretada(fabrica, slugsURL, em);
            } else {
                return new UrlInterpretada(fabrica, slugsURL);
            }
        } catch (Throwable t) {
            String texto = "Erro interpretando urls a partir da classe" + fabrica.getSimpleName() + " com o caminho de url=" + UtilSBWPServletTools.getSlugsDeUrl(requisicao);
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro Interpretando Urls", t);
            throw new UnsupportedOperationException("Erro interpretando url");
        }

    }

    public static List<String> getListaStringsParametroURL(HttpServletRequest requisicao) {
        List<String> slugsURL = UtilSBWPServletTools.getSlugsDeUrl(requisicao);
        if (!UtilSBCoreStringValidador.isNuloOuEmbranco(SBWebPaginas.getNomePacote())) {
            slugsURL.remove(0);
        }

        return slugsURL;
    }

}
