package com.super_bits.modulosSB.webPaginas.JSFManagedBeans.declarados.Paginas.ErroCritico;

import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringFiltros;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringValidador;
import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.UtilCRCErros;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class InfoErroCritico implements Serializable {

    private String tituloErro;
    private String mensagemErro;
    private String printStack;
    private String printStackResumido;
    private InfoErroCritico beanErroCritico;

    public InfoErroCritico() {
    }

    public InfoErroCritico(String pTituloErro, Throwable pExcept) {
        tituloErro = UtilCRCStringFiltros.gerarUrlAmigavel(pTituloErro);
        mensagemErro = pExcept.getMessage();
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        pExcept.printStackTrace(pw);
        pw.close();

        printStack = "RESUMO: \n " + UtilCRCErros.getResumoErro(pExcept) + "/n caminho completo: /n" + sw.getBuffer().toString().replace("", "");

    }

    public String getTituloErro() {
        return tituloErro;
    }

    public String getMensagemErro() {
        return mensagemErro;
    }

    public String getPrintStack() {
        return printStack;
    }

    public InfoErroCritico getBeanErroCritico() {
        return beanErroCritico;
    }

    public void setBeanErroCritico(InfoErroCritico beanErroCritico) {
        this.beanErroCritico = beanErroCritico;
    }

    public String getPrintStackResumido() {
        return printStackResumido;
    }

    public void setPrintStackResumido(String printStackResumido) {
        this.printStackResumido = printStackResumido;
    }

}
