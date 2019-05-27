/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFManagedBeans.declarados.util;

import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreOutputs;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.annotation.ViewMap;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.apache.commons.io.IOUtils;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author salviofurbino
 * @since 01/05/2019
 * @version 1.0
 */
@Named
@ViewScoped
public class PgUtilUploadDeArquivo implements Serializable {

    private String caminhoCampo;
    private ItfBeanSimples entidade;

    public synchronized void enviarArquivo(FileUploadEvent event) {
        try {
            Thread.sleep(300);
            if (caminhoCampo == null || entidade == null) {
                throw new UnsupportedOperationException("Os detalhes do upload não foram enviados");
            }
            ItfCampoInstanciado cp = entidade.getCPinst(caminhoCampo);
            if (cp.getValor() instanceof List) {
                if (cp.isUmaListagemParticular()) {
                    cp.getComoSubItens().getSubItens().adicionarItem();
                    ItfBeanSimples item = cp.getComoSubItens().getSubItens().getListaObjetosSelecionados().get(cp.getComoSubItens().getSubItens().getListaObjetosSelecionados().size() - 1);
                    ItfCampoInstanciado campoArquivo = item.getCampoInstanciadoByAnotacao(FabTipoAtributoObjeto.ARQUIVO_DE_ENTIDADE);
                    campoArquivo.getComoArquivoDeEntidade();
                    //todo impedir envio arquivo duplicado
                    ItfBeanSimples arquivoAtualizado = UtilSBPersistencia.mergeRegistro(cp.getComoSubItens().getSubItens().getListaObjetosSelecionados().get(cp.getComoSubItens().getSubItens().getListaObjetosSelecionados().size() - 1));
                    campoArquivo = arquivoAtualizado.getCampoInstanciadoByAnotacao(FabTipoAtributoObjeto.ARQUIVO_DE_ENTIDADE);
                    campoArquivo.setValor(event.getFile().getFileName());
                    arquivoAtualizado.setNome(event.getFile().getFileName());
                    UtilSBPersistencia.mergeRegistro(arquivoAtualizado);
                    campoArquivo.getComoArquivoDeEntidade().uploadArquivo(event.getFile().getFileName(), IOUtils.toByteArray(event.getFile().getInputstream()));

                } else {
                    throw new UnsupportedOperationException("O tipo de atributo em " + caminhoCampo + "Ainda não 'ecompatível com este componente");
                }
            } else {
                cp.getComoArquivoDeEntidade().uploadArquivo(caminhoCampo, IOUtils.toByteArray(event.getFile().getInputstream()));
            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro enviando imagem tamanho curto", t);
        }
    }

    public String getCaminhoCampo() {
        return caminhoCampo;
    }

    public void setCaminhoCampo(String caminhoCampo) {
        this.caminhoCampo = caminhoCampo;
    }

    public ItfBeanSimples getEntidade() {
        return entidade;
    }

    public void setEntidade(ItfBeanSimples entidade) {
        this.entidade = entidade;
    }

}
