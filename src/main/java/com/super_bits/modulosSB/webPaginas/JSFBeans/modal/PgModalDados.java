/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.modal;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringSlugs;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ComoEntidadeSimples;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.interfaces.ItfModalDados;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import org.primefaces.PrimeFaces;

import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author desenvolvedor
 */
public class PgModalDados extends PgModalRespostaAbstrato implements ItfModalDados {

    private String observacaoDeLog;
    private ItfCampoInstanciado campoSelecionado;
    private ComoEntidadeSimples entidadeSelecionada;

    public void setCampoSelecionado(ItfCampoInstanciado campoSelecionado) {
        this.campoSelecionado = campoSelecionado;
    }

    @Override
    public void enviarArquivoDoCampoUpload(FileUploadEvent event) {
        String nomeArquivo = null;
        try {
            String nomeCompleto = event.getFile().getFileName();
            String extencao = nomeCompleto.substring(event.getFile().getFileName().lastIndexOf("."), nomeCompleto.length());
            String nome = nomeCompleto.substring(0, event.getFile().getFileName().lastIndexOf("."));
            nome = UtilCRCStringSlugs.gerarSlugSimples(nome);
            nomeArquivo = nome + extencao;

            if (getCampoSelecionado().getComoArquivoDeEntidade().uploadArquivo(nomeArquivo, event.getFile().getContent()));

        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro enviando arquivo" + nomeArquivo, t);

        }
    }

    @Override
    public String getObServacaoDeLog() {
        return observacaoDeLog;
    }

    @Override
    public ComoEntidadeSimples getEntidadeSelecionada() {
        if (entidadeSelecionada != null) {
            return entidadeSelecionada;
        }
        if (getCampoSelecionado() != null) {
            return getCampoSelecionado().getObjetoDoAtributo();
        }
        return null;

    }

    @Override
    public ItfCampoInstanciado getCampoSelecionado() {
        return campoSelecionado;
    }

    @Override
    public void setObServacaoDeLog(String pObservacao) {
        observacaoDeLog = pObservacao;
    }

}
