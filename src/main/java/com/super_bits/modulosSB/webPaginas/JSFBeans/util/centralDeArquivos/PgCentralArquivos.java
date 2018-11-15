/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.util.centralDeArquivos;

import com.super_bits.modulosSB.Persistencia.util.UtilSBPersistenciaArquivosDeEntidade;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreOutputs;
import com.super_bits.modulosSB.SBCore.UtilGeral.stringSubstituicao.MapaSubstituicaoArquivo;
import com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo.UtilSBCoreArquivos;
import com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo.interfaces.ItfCentralDeArquivos;
import com.super_bits.modulosSB.SBCore.modulos.Mensagens.FabMensagens;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author desenvolvedor
 */
@Named
@ViewScoped
public class PgCentralArquivos implements Serializable {

    private ItfBeanSimples entidadeRelacionada;
    private String categoria;

    private FabTipoEnvioArquivoEntidade tipoEnvio = FabTipoEnvioArquivoEntidade.ARQUIVO_DA_ENTIDADE;
    private MapaSubstituicaoArquivo substituicaoEmArquivoExemplo;
    private String nomeArquivo;

    private UploadedFile arquivoDeEntidadePersistida;
    private UploadedFile arquivoDeEntidadeNaoPersistida;

    public UploadedFile getArquivoDeEntidadePersistida() {
        return arquivoDeEntidadePersistida;
    }

    public void setArquivoDeEntidadePersistida(UploadedFile arquivoDeEntidadePersistida) {
        this.arquivoDeEntidadePersistida = arquivoDeEntidadePersistida;
        if (arquivoDeEntidadeNaoPersistida != null) {
            salvarArquivo(arquivoDeEntidadePersistida);
        }
    }

    public UploadedFile getArquivoDeEntidadeNaoPersistida() {
        return arquivoDeEntidadeNaoPersistida;
    }

    public void setArquivoDeEntidadeNaoPersistida(UploadedFile arquivoDeEntidadeNaoPersistida) {
        this.arquivoDeEntidadeNaoPersistida = arquivoDeEntidadeNaoPersistida;
        if (arquivoDeEntidadeNaoPersistida != null) {
            throw new UnsupportedOperationException("Arquivo de entidade não persistida não foi implementado");
        }
    }

    @PostConstruct
    public void init() {
        substituicaoEmArquivoExemplo = new MapaSubstituicaoArquivo(new File("/home/teste.txt"));
        categoria = ItfCentralDeArquivos.CATEGORIA_PADRAO_ARQUIVO_DE_REGISTRO;

    }

    public List<String> getPalavrasChaveSubstituicaoArquivo() {
        return substituicaoEmArquivoExemplo.getpalavrasChave();
    }

    public void alterarTipoEnvio(FabTipoEnvioArquivoEntidade pTipoEnvioArquivoEntidade) {
        tipoEnvio = pTipoEnvioArquivoEntidade;
    }

    public String getCaminhoLocalArquivo() {
        switch (tipoEnvio) {
            case ARQUIVO_DA_ENTIDADE:
                return SBCore.getCentralDeArquivos().getEndrLocalArquivoItem(
                        entidadeRelacionada, nomeArquivo, categoria);

            case IMAGEM_REPRESENTANTE_ENTIDADE:

                break;
            default:

        }
        return "falta informação para determinar o caminho";

    }

    public void salvarArquivo(UploadedFile pArquivo) {
        String caminhoSalvarArquivo = "caminho nao definido";
        try {

            if (tipoEnvio == null) {
                throw new UnsupportedOperationException("O tipo de envio não foi selecionado, defina o tipo de envio para proceguir");
            }

            switch (tipoEnvio) {
                case ARQUIVO_DA_ENTIDADE:

                    if (categoria == null) {
                        throw new UnsupportedOperationException("o nome da categoria precisa ser configurada");
                    }
                    if (entidadeRelacionada == null) {
                        throw new UnsupportedOperationException("A entidade Relacionada precisa ser configurada");
                    }

                    //if (entidadeRelacionada.getId() == 0) {
                    //  throw new UnsupportedOperationException("O Id da entidade é igual a 0, salve a entidade primeiro, antes de enviar o arquivo relacionado");
                    //}
                    if (nomeArquivo == null) {
                        nomeArquivo = pArquivo.getFileName();
                    }
                    caminhoSalvarArquivo = SBCore.getCentralDeArquivos().getEndrLocalArquivoItem(
                            entidadeRelacionada, nomeArquivo, categoria);

                    if (UtilSBCoreArquivos.isArquivoExiste(caminhoSalvarArquivo)) {
                        SBCore.enviarMensagemUsuario("O arquivo já foi enviado para o sistema \n" + caminhoSalvarArquivo, FabMensagens.AVISO);
                        throw new UnsupportedOperationException("Este arquivo já existe");
                    }
                    if (UtilSBCoreOutputs.salvarArquivoInput(pArquivo.getInputstream(), caminhoSalvarArquivo)) {
                        SBCore.enviarAvisoAoUsuario("Arquivo armezenado com sucesso");
                    }
                    break;
                case IMAGEM_REPRESENTANTE_ENTIDADE:
                    break;
                default:
                    throw new AssertionError(tipoEnvio.name());
            }

        } catch (Throwable t) {
            SBCore.enviarMensagemUsuario("Ouve um erro inesperado ao enviar o arquivo para o servidor", FabMensagens.ERRO);
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro inesperado tentando enviar arquivo", t);
            throw new UnsupportedOperationException("Ocorreu um erro tentando salvar o arquivo");
        }
    }

    public synchronized void enviarArquivoDeEntidadeSelecionada(FileUploadEvent event) {
        if (!SBCore.isEmModoProducao()) {
            if (!SBCore.getControleDeSessao().getSessaoAtual().isIdentificado()) {
                throw new UnsupportedOperationException("Ouve uma tentativa não autorizada de enviar arquivos de entidade");
            }
        }

        salvarArquivo(event.getFile());

    }

    public void salvarImagens(FileUploadEvent event) {
        try {
            UtilSBPersistenciaArquivosDeEntidade.SalvaIMAGEM(entidadeRelacionada, event.getFile().getInputstream());
        } catch (IOException ex) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro salvando imagem para" + entidadeRelacionada, ex);
            Logger.getLogger(PgCentralArquivos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ItfBeanSimples getEntidadeRelacionada() {
        return entidadeRelacionada;
    }

    public void setEntidadeRelacionada(ItfBeanSimples entidadeRelacionada) {
        this.entidadeRelacionada = entidadeRelacionada;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

}
