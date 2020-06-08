/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.interfaces;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;
import com.super_bits.modulosSB.webPaginas.JSFBeans.modal.ItfModalWebApp;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author desenvolvedor
 */
public interface ItfModalDados extends ItfModalWebApp {

    public ItfCampoInstanciado getCampoSelecionado();

    /**
     *
     * @return
     */
    public ItfBeanSimples getEntidadeSelecionada();

    /**
     *
     * Primefaces 7.0 ou anterior
     *
     * @param event
     */
    @Deprecated
    public void enviarArquivoDoCampoUpload(FileUploadEvent event);

    public default void enviarImagemPequenaUpload(FileUploadEvent event) {
        try {
            getEntidadeSelecionada().uploadFotoTamanhoPequeno(event.getFile().getInputStream());
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro enviando imagem tamanho curto", t);
        }
    }

    public String getObServacaoDeLog();

    public void setObServacaoDeLog(String pObservacao);

}
