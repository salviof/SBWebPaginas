/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.modal;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.interfaces.ItfModalDados;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author desenvolvedor
 */
public class PgModalDados extends PgModalRespostaAbstrato implements ItfModalDados {

    private String observacaoDeLog;
    private ItfCampoInstanciado campoSelecionado;
    private ItfBeanSimples entidadeSelecionada;

    public void setCampoSelecionado(ItfCampoInstanciado campoSelecionado) {
        this.campoSelecionado = campoSelecionado;
    }

    @Override
    public void enviarArquivoDoCampoUpload(FileUploadEvent event) {
        String nomeArquivo = null;
        try {
            nomeArquivo = event.getFile().getFileName();

            if (getCampoSelecionado().getComoArquivoDeEntidade().uploadArquivo(nomeArquivo, event.getFile().getContents()));
            RequestContext.getCurrentInstance().closeDialog(this);
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro enviando arquivo" + nomeArquivo, t);

        }
    }

    @Override
    public String getObServacaoDeLog() {
        return observacaoDeLog;
    }

    @Override
    public ItfBeanSimples getEntidadeSelecionada() {
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
