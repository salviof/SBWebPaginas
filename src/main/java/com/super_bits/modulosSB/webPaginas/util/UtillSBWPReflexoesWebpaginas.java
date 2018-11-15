package com.super_bits.modulosSB.webPaginas.util;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreReflexao;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoGerenciarEntidade;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.interfaces.ItfB_Pagina;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.reflexao.anotacoes.beans.InfoParametroURL;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.siteMap.MapaDeFormularios;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.siteMap.parametrosURL.ParametroURL;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.siteMap.parametrosURL.ParametroUrlInstanciado;
import com.super_bits.modulosSB.webPaginas.controller.servletes.servletWebPaginas.EstruturaDeFormulario;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
/**
 *
 * @author sfurbino
 */
public abstract class UtillSBWPReflexoesWebpaginas {

    public static ItfAcaoGerenciarEntidade getAcaoDeGestaoPorClasseDoMetodoChamado() {
        try {
            final Thread t = Thread.currentThread();
            final StackTraceElement[] stackTraceTodosMetodos = t.getStackTrace();

            int i = 2;
            String classeVinculada = null;
            boolean encontrou = false;
            for (int j = i; j < 4; j++) {
                StackTraceElement metodoChamado = stackTraceTodosMetodos[j];

                classeVinculada = metodoChamado.getClassName();
                if (classeVinculada.contains("Pg")) {
                    return MapaDeFormularios.getEstruturaByClasseMB(classeVinculada).getAcaoGestaoVinculada();

                }
                System.out.println(classeVinculada + "<------");
            }
            i = 1;

            for (int j = i; j < 5; j++) {
                StackTraceElement metodoChamado = stackTraceTodosMetodos[j];

                classeVinculada = metodoChamado.getClassName();
                EstruturaDeFormulario estruturaEncontrada = MapaDeFormularios.getEstruturaByClasseMB(classeVinculada, false);
                if (estruturaEncontrada != null) {
                    return estruturaEncontrada.getAcaoGestaoVinculada();
                }

            }
            throw new UnsupportedOperationException("Impossível obter a ação da pagina por reflexão de StackTrace");
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro Obtendo ação vinculada via StackTrace, procurando pelo constructor ", t);
            return null;
        }

    }

    public static ItfAcaoGerenciarEntidade getAcaoGestaoProClasse(Class pClasse) {
        return MapaDeFormularios.getEstruturaByClasseMB(pClasse).getAcaoGestaoVinculada();

    }

    public static List<Field> instanciarInjecoes(Object instancia) {

        Class classe = instancia.getClass();
        Field[] fields = classe.getDeclaredFields();

        List<Field> resposta = new ArrayList<>();
        for (Field campo : fields) {

            if (campo.isAnnotationPresent(Inject.class)) {
                campo.setAccessible(true);
                try {
                    if (campo.getType().getName().equals(classe.getName())) {
                        throw new UnsupportedOperationException("Voce não pode injetar a classe nela mesma :" + classe.getName());
                    }
                    campo.set(instancia, campo.getType().newInstance());

                    //System.out.println("Lista Auto Instanciada" + campo.getName());
                } catch (Throwable ex) {
                    SBCore.RelatarErro(FabErro.SOLICITAR_REPARO,"Não foi possível simular a Injeção do campo" + campo.getName(), ex);
                }
            }
        }
        return resposta;
    }

    public static EstruturaDeFormulario getEstruturaFormularioByPagina(Class<? extends ItfB_Pagina> pagina) {
        return null;
    }

    public static List<ParametroURL> buildParametrosDaPagina(Class pClasseFormulario) {

        List<Field> lista = UtilSBCoreReflexao.procuraCamposPorTipo(pClasseFormulario, ParametroURL.class);
        List<ParametroURL> parametrosDaPagina = new ArrayList<>();
        for (Field cp : lista) {
            ParametroURL novoParametro = new ParametroUrlInstanciado(UtillSBWPReflexoesWebpaginas.getInfoParametroDeUrl(cp));
            parametrosDaPagina.add(novoParametro);
        }
        return parametrosDaPagina;

    }

    public static EstruturaDeFormulario getNovaEstruturaByClassePagina(Class pPagina) {

        return new EstruturaDeFormulario(pPagina);

    }

    public static InfoParametroURL getInfoParametroDeUrl(Field cp) {
        InfoParametroURL infoPr = cp.getDeclaredAnnotation(InfoParametroURL.class);
        if (infoPr == null) {
            throw new UnsupportedOperationException("Erro o parametro " + cp.getName() + " não foi anotado com @InfoParametro em" + cp.getDeclaringClass().getSimpleName());
        }
        return infoPr;
    }
}
