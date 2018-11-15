/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.util;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.Mensagens.FabMensagens;
import com.super_bits.modulosSB.webPaginas.JSFBeans.temas.Tema;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWP_JSFTools;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Properties;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

/**
 *
 * Conjunto de cores padrao para criação de Templates do FrameWork
 *
 * @author <a href="mailto:salviof@gmail.com">Salvio Furbino</a>
 * @since 14/01/2015
 * @version 1.0
 */
@RequestScoped
@Named
public class Cores implements Serializable {

    private static String corHeader = "#4DA86E";
    private static String corBorda = "#4DA86E";
    private static String corBackground = "#4DA86E";
    private static String corFundo1 = "#4DA86E";
    private static String corFundo2 = "#4DA86E";
    private static String corHover = "#4DA86E";
    private static String corTitulo = "#4DA86E";
    private static String corAction = "#4DA86E";
    private static String corComplementa = "#4DA86E";
    private static String corFonte = "#4DA86E";
    private static String corOpacidade = "#4DA86E";
    private static String corMenuHeader = "#4DA86E";
    private static String corMenuBorda = "#4DA86E";
    private static String corMenuBackground = "#4DA86E";
    private static String corMenuHover = "#4DA86E";
    private static String corMenuTitulo = "#4DA86E";
    private static String corMenuAction = "#4DA86E";
    private static String corPrincipal = "#4DA86E";

    public String getCorPrincipal() {
        return corPrincipal;
    }

    public void setCorPrincipal(String corPrincial) {
        Cores.corPrincipal = corPrincial;
    }

    public String getCorHeader() {
        return corHeader;
    }

    public String getCorBorda() {
        return corBorda;
    }

    public String getCorBackground() {
        return corBackground;
    }

    public String getCorFundo1() {
        return corFundo1;
    }

    public String getCorFundo2() {
        return corFundo2;
    }

    public String getCorHover() {
        return corHover;
    }

    public String getCorTitulo() {
        return corTitulo;
    }

    public String getCorAction() {
        return corAction;
    }

    public String getCorComplementa() {
        return corComplementa;
    }

    public String getCorFonte() {
        return corFonte;
    }

    public String getCorOpacidade() {
        return corOpacidade;
    }

    public String getCorMenuHeader() {
        return corMenuHeader;
    }

    public String getCorMenuBorda() {
        return corMenuBorda;
    }

    public String getCorMenuBackground() {
        return corMenuBackground;
    }

    public String getCorMenuHover() {
        return corMenuHover;
    }

    public String getCorMenuTitulo() {
        return corMenuTitulo;
    }

    public String getCorMenuAction() {
        return corMenuAction;
    }

    public String getEditorCorHeader() {
        return corHeader.replace("#", "");
    }

    public void setEditorCorHeader(String editorCorHeader) {
        this.corHeader = "#" + editorCorHeader.toUpperCase();
    }

    public String getEditorCorBorda() {
        return corBorda.replace("#", "");
    }

    public void setEditorCorBorda(String editorCorBorda) {
        this.corBorda = "#" + editorCorBorda.toUpperCase();
    }

    public String getEditorCorBackground() {
        return corBackground.replace("#", "");
    }

    public void setEditorCorBackground(String editorCorBackground) {
        this.corBackground = "#" + editorCorBackground.toUpperCase();
    }

    public String getEditorCorPrincipal() {
        return corPrincipal.replace("#", "");
    }

    public void setEditorCorPrincipal(String corPrincial) {
        Cores.corPrincipal = "#" + corPrincial.toUpperCase();
    }

    public String getEditorCorFundo1() {
        return corFundo1.replace("#", "");
    }

    public void setEditorCorFundo1(String editorCorFundo1) {
        this.corFundo1 = "#" + editorCorFundo1.toUpperCase();
    }

    public String getEditorCorFundo2() {
        return corFundo2.replace("#", "");
    }

    public void setEditorCorFundo2(String editorCorFundo2) {
        this.corFundo2 = "#" + editorCorFundo2.toUpperCase();
    }

    public String getEditorCorHover() {
        return corHover.replace("#", "");
    }

    public void setEditorCorHover(String editorCorHover) {
        this.corHover = "#" + editorCorHover.toUpperCase();
    }

    public String getEditorCorTitulo() {
        return corTitulo.replace("#", "");
    }

    public void setEditorCorTitulo(String editorCorTitulo) {
        this.corTitulo = "#" + editorCorTitulo.toUpperCase();
    }

    public String getEditorCorAction() {
        return corAction.replace("#", "");
    }

    public void setEditorCorAction(String editorCorAction) {
        this.corAction = "#" + editorCorAction.toUpperCase();
    }

    public String getEditorCorComplementa() {
        return corComplementa.replace("#", "");
    }

    public void setEditorCorComplementa(String editorCorComplementa) {
        this.corComplementa = "#" + editorCorComplementa.toUpperCase();
    }

    public String getEditorCorFonte() {
        return corFonte.replace("#", "");
    }

    public void setEditorCorFonte(String editorCorFonte) {
        this.corFonte = "#" + editorCorFonte.toUpperCase();
    }

    public String getEditorCorOpacidade() {
        return corOpacidade.replace("#", "");
    }

    public void setEditorCorOpacidade(String editorCorOpacidade) {
        this.corOpacidade = "#" + editorCorOpacidade.toUpperCase();
    }

    public String getEditorCorMenuHeader() {
        return corMenuHeader.replace("#", "");
    }

    public void setEditorCorMenuHeader(String editorCorMenuHeader) {
        this.corMenuHeader = "#" + editorCorMenuHeader.toUpperCase();
    }

    public String getEditorCorMenuBorda() {
        return corMenuBorda.replace("#", "");
    }

    public void setEditorCorMenuBorda(String editorCorMenuBorda) {
        this.corMenuBorda = "#" + editorCorMenuBorda.toUpperCase();
    }

    public String getEditorCorMenuBackground() {
        return corMenuBackground.replace("#", "");
    }

    public void setEditorCorMenuBackground(String editorCorMenuBackground) {
        this.corMenuBackground = "#" + editorCorMenuBackground.toUpperCase();
    }

    public String getEditorCorMenuHover() {
        return corMenuHover.replace("#", "");
    }

    public void setEditorCorMenuHover(String editorCorMenuHover) {
        this.corMenuHover = "#" + editorCorMenuHover.toUpperCase();
    }

    public String getEditorCorMenuTitulo() {
        return corMenuTitulo.replace("#", "");
    }

    public void setEditorCorMenuTitulo(String editorCorMenuTitulo) {
        this.corMenuTitulo = "#" + editorCorMenuTitulo.toUpperCase();
    }

    public String getEditorCorMenuAction() {
        return corMenuAction.replace("#", "");
    }

    public void setEditorCorMenuAction(String editorCorMenuAction) {
        this.corMenuAction = "#" + editorCorMenuAction.toUpperCase();
    }

    public synchronized void salvarCores() {
        System.out.println("Executando tarefa salvar cores em arquivos");
        Properties prop = new Properties();
        OutputStream output = null;
        Tema tema = new Tema();

        prop.setProperty("tema", tema.getTemaOficial().getName());
        try {

            output = new FileOutputStream(UtilSBWP_JSFTools.getCaminhoLocalRessource() + "/configCores.properties");

            Field[] campos = this.getClass().getDeclaredFields();
            for (Field campo : campos) {
                if (campo.getName().contains("cor")) {
                    prop.setProperty(campo.getName(), campo.get(this).toString());
                }
            }

            // save properties to project root folder
            prop.store(output, "Configuração de cores basicas do FrameWork Super-Bits");
            System.out.println("Arquivo de configuracao salvo com suceso");
            SBCore.enviarMensagemUsuario("Arquivo de configuração salvo com sucesso", FabMensagens.AVISO);
        } catch (Throwable e) {
            SBCore.enviarMensagemUsuario("Erro tentando gravar arquivo de configuração", FabMensagens.AVISO);
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro salvando arquivo de configuração de cores", e);
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro salvando arquivo de configuração de cores", e);
                }
            }

        }

    }

    public void carregarCores() {

        Properties prop = new Properties();
        InputStream input = null;

        try {

            input = new FileInputStream(UtilSBWP_JSFTools.getCaminhoLocalRessource() + "/configCores.properties");

            // load a properties file
            prop.load(input);
            prop.propertyNames();
            String temaString = prop.getProperty("tema");
            Tema tema = new Tema();
            tema.setTemaPeloNome(temaString);
            Field[] campos = this.getClass().getDeclaredFields();
            for (Field campo : campos) {
                campo.setAccessible(true);
                if (campo.getName().contains("cor")) {
                    campo.set(this, prop.getProperty(campo.getName()));
                }
            }

        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "erro carregando cores apartir de arquivo, Utilizando Templates oficiais no projeto este arquivo é obrigatório", t);
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "erro carregando cores apartir de arquivo", e);
                }
            }
        }

    }

}
