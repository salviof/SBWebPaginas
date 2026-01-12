/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFManagedBeans.declarados.util;

import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo.UtilCRCArquivos;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstArquivoEntidade;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoEntidadeSimples;
import java.io.Serializable;
import java.util.List;
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
    private ComoEntidadeSimples entidade;

    public synchronized void enviarArquivo(FileUploadEvent event) {
        try {
            Thread.sleep(300);
            if (caminhoCampo == null || entidade == null) {
                throw new UnsupportedOperationException("Os detalhes do upload não foram enviados");
            }
            ItfCampoInstanciado cp = entidade.getCPinst(caminhoCampo);
            if (cp.getValor() instanceof List) {
                if (cp.isUmaListagemParticular()) {

                    byte[] arquivoByteArray = IOUtils.toByteArray(event.getFile().getInputStream());
                    String hashArquivoNovo = UtilCRCArquivos.getHashDoByteArray(arquivoByteArray);
                    //todo impedir envio arquivo duplicado
                    List<ComoEntidadeSimples> arquivos = (List) cp.getValor();
                    for (ComoEntidadeSimples arquivo : arquivos) {
                        ItfCampoInstanciado campoArquivo = (ItfCampoInstanciado) arquivo.getCampoInstanciadoByAnotacao(FabTipoAtributoObjeto.ARQUIVO_DE_ENTIDADE);
                        String hashDoArquivo = SBCore.getServicoArquivosDeEntidade().getHashArquivoDeEntidadeRegistrado(campoArquivo);
                        if (hashDoArquivo != null) {
                            if (hashDoArquivo.startsWith(hashArquivoNovo)) {
                                throw new ErroEnviandoArquivoDeEntidade("Este arquivo já existe em " + entidade.getCPinst(caminhoCampo).getLabel());
                            }
                        }
                    }

                    cp.getComoSubItens().getSubItens().adicionarItem();
                    ComoEntidadeSimples item = cp.getComoSubItens().getSubItens().getListaObjetosSelecionados().get(cp.getComoSubItens().getSubItens().getListaObjetosSelecionados().size() - 1);
                    ItfCampoInstanciado campoArquivo = item.getCampoInstanciadoByAnotacao(FabTipoAtributoObjeto.ARQUIVO_DE_ENTIDADE);
                    campoArquivo.getComoArquivoDeEntidade();

                    ComoEntidadeSimples arquivoAtualizado = UtilSBPersistencia.mergeRegistro(cp.getComoSubItens().getSubItens().getListaObjetosSelecionados().get(cp.getComoSubItens().getSubItens().getListaObjetosSelecionados().size() - 1));
                    campoArquivo = arquivoAtualizado.getCampoInstanciadoByAnotacao(FabTipoAtributoObjeto.ARQUIVO_DE_ENTIDADE);
                    campoArquivo.setValor(event.getFile().getFileName());
                    arquivoAtualizado.setNome(event.getFile().getFileName());
                    UtilSBPersistencia.mergeRegistro(arquivoAtualizado);
                    campoArquivo.getComoArquivoDeEntidade().uploadArquivo(event.getFile().getFileName(), arquivoByteArray);

                } else {
                    throw new UnsupportedOperationException("O tipo de atributo em " + caminhoCampo + "Ainda não 'ecompatível com este componente");
                }
            } else {
                cp.getComoArquivoDeEntidade().uploadArquivo(caminhoCampo, IOUtils.toByteArray(event.getFile().getInputStream()));
            }
        } catch (ErroEnviandoArquivoDeEntidade erro) {
            throw new UnsupportedOperationException(erro.getMessage());
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro enviando imagem " + t.getMessage(), t);

        }
    }

    public String getCaminhoCampo() {
        return caminhoCampo;
    }

    public void setCaminhoCampo(String caminhoCampo) {
        this.caminhoCampo = caminhoCampo;
    }

    public ComoEntidadeSimples getEntidade() {
        return entidade;
    }

    public void setEntidade(ComoEntidadeSimples entidade) {
        this.entidade = entidade;
    }

}
