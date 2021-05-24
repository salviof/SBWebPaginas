/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.util.geradorCodigo;

import com.super_bits.modulosSB.webPaginas.ConfigGeral.SBWebPaginas;
import java.io.Serializable;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import org.jboss.forge.roaster.model.source.JavaClassSource;
import testesFW.geradorDeCodigo.GeradorClasseGenerico;

/**
 *
 * @author SalvioF
 */
public abstract class GeradorClasseEscopoApp extends GeradorClasseGenerico {

    protected static final String DIRETORIO_CODIGO_WEB_PAGINAS = SBWebPaginas.DIRETORIO_PADRAO_MODULO_WEBAPP_FW_DESENVOLVIMENTO + "/src/main/java/";

    public GeradorClasseEscopoApp(String pNomePacote, String pNomeClasse) {
        super(pNomePacote, pNomeClasse);
        adicionarAnotacoesEInterfaces();
    }

    public GeradorClasseEscopoApp(String pNomePacote, String pNomeClasse, String pDiretorioAlternativo) {
        super(pNomePacote, pNomeClasse, pDiretorioAlternativo, TIPO_PACOTE.IMPLEMENTACAO);
        adicionarAnotacoesEInterfaces();
    }

    private void adicionarAnotacoesEInterfaces() {
        getCodigoJava().addAnnotation(Named.class);
        getCodigoJava().addAnnotation(ApplicationScoped.class);
        getCodigoJava().addInterface(Serializable.class);
    }

    public abstract JavaClassSource gerarCodigo();

}
