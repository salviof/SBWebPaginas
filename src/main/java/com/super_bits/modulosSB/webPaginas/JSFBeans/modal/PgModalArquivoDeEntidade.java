/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.modal;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import org.primefaces.model.file.UploadedFile;

/**
 *
 * @author desenvolvedor
 */
@Named
@ViewScoped
public class PgModalArquivoDeEntidade extends PgModalDados {

    private UploadedFile arquivoSelecionado;
    private List<UploadedFile> arquivosSelecionados;

    @PostConstruct
    public void inicioArqEntidade() {
        try {

            setCampoSelecionado(getPaginaVinculada().getCampoInstSelecionado());

            if (getCampoSelecionado() == null) {
                throw new UnsupportedOperationException("O campo instanciado selecionado não foi configurado antes de chamar o modal");
            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro obetendo campo instanciado da pagina", t);
        }
    }

    public UploadedFile getArquivoSelecionado() {
        return arquivoSelecionado;
    }

    public void setArquivoSelecionado(UploadedFile arquivoSelecionado) {
        this.arquivoSelecionado = arquivoSelecionado;
    }

    public List<UploadedFile> getArquivosSelecionados() {
        return arquivosSelecionados;
    }

    public void setArquivosSelecionados(List<UploadedFile> arquivosSelecionados) {
        this.arquivosSelecionados = arquivosSelecionados;
    }

}
