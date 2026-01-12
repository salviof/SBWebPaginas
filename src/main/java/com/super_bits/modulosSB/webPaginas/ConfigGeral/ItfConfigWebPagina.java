/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulosSB.webPaginas.ConfigGeral;

import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoFormulario;
import com.super_bits.modulosSB.webPaginas.controller.servletes.urls.parametrosURL.ParametroURL;
import java.util.ArrayList;

import java.util.List;

/**
 *
 * Interface de configurador de Webpagina (implemente, e chame em
 * SBWebPaginas.configurar
 *
 *
 * @author Salvio
 */
public interface ItfConfigWebPagina {

    /**
     * Endereço do host + porta exemplo: https://www.meuEnderecoOuIP.com.br:8080
     *
     * @return
     */
    public abstract String SITE_HOST();

    public default List<String> getSitesHostsAutorizados() {
        List<String> hostsAutorizados = new ArrayList<>();
        hostsAutorizados.add(SITE_HOST());
        return hostsAutorizados;
    }

    /**
     * Diretorio base principal onde ficarão os jpgs
     *
     * @return
     */
    public abstract String pastaImagens();

    /**
     * nome do pacote da aplição que corresponde ao url/aplicacao quando em
     * desenvolvimento
     *
     * @return
     */
    public abstract String nomePacoteProjeto();

    /**
     * Titulo da aplicação (aparecerá no title da pagina caso nao seja setado
     * um)
     *
     * @return
     */
    public abstract String TituloAppWeb();

    /**
     * subdominios ou subdiretorios que estarão sempre presentes no endereço do
     * site
     *
     * @return
     */
    public abstract String URLBASE();

    public abstract Class mapaSite();

    public abstract List<ParametroURL> parametrosDeAplicacao();

    public abstract boolean parametroDeAplicacaoEmSubDominio();

    public abstract ItfAcaoFormulario getAcaoPaginaInicial();

    public default String getPacotePaginas() {
        return "com.super_bits";
    }

}
