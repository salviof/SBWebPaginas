/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.controller.servlets.servletArquivoDeEntidade;

import com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo.TipoRecurso;
import com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo.acessoArquivo.TipoAcessoArquivo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ComoEntidadeSimples;
import com.super_bits.modulosSB.webPaginas.controller.servletes.urls.UrlInterpretada;

/**
 *
 * @author sfurbino
 */
public class DadosRequisicaoArquivoDeEntidade {

    private UrlInterpretada parametrosDeUrl;
    private ComoEntidadeSimples entidade = null;
    private String caminhoLocal = null;
    private String nomeArquivoDownload = null;
    private TipoAcessoArquivo tipoAcesso;
    private TipoRecurso tipoRecurso;
    private String categoria;

    public UrlInterpretada getParametrosDeUrl() {
        return parametrosDeUrl;
    }

    public void setParametrosDeUrl(UrlInterpretada parametrosDeUrl) {
        this.parametrosDeUrl = parametrosDeUrl;
    }

    public ComoEntidadeSimples getEntidade() {
        return entidade;
    }

    public void setEntidade(ComoEntidadeSimples entidade) {
        this.entidade = entidade;
    }

    public String getCaminhoLocal() {
        return caminhoLocal;
    }

    public void setCaminhoLocal(String caminhoLocal) {
        this.caminhoLocal = caminhoLocal;
    }

    public String getNomeArquivoDownload() {
        return nomeArquivoDownload;
    }

    public void setNomeArquivoDownload(String nomeArquivoDownload) {
        this.nomeArquivoDownload = nomeArquivoDownload;
    }

    public TipoAcessoArquivo getTipoAcesso() {
        return tipoAcesso;
    }

    public void setTipoAcesso(TipoAcessoArquivo tipoAcesso) {
        this.tipoAcesso = tipoAcesso;
    }

    public TipoRecurso getTipoRecurso() {
        return tipoRecurso;
    }

    public void setTipoRecurso(TipoRecurso tipoRecurso) {
        this.tipoRecurso = tipoRecurso;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

}
