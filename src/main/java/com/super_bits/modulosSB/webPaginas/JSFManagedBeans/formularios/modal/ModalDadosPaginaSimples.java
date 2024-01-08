/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.modal;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringsMaiuculoMinusculo;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.ItfComunicacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;
import com.super_bits.modulosSB.webPaginas.JSFBeans.SBBeanModel.InfoMBAcao;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.B_Pagina;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.interfaces.ItfB_Pagina;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.interfaces.ItfModalDados;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.interfaces.ItfPaginaAtual;
import java.util.List;
import java.util.Map;
import javax.faces.component.UIViewRoot;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import org.primefaces.PrimeFaces;

import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author desenvolvedor
 */
@Deprecated
public class ModalDadosPaginaSimples implements ItfModalDados {

    private ItfCampoInstanciado campoInstanciadoSelecionado;
    private ItfBeanSimples entidadeSelecionada;
    private ItfComunicacao comunicacaoDoMomento;
    private String observacaoDoLog;
    private final UIViewRoot viewRootOrigem;
    private final Class classeOrigem;

    /**
     *
     *
     * Campo utlizado para leitura de informações em formulário de envio de
     * arquivo de entidade
     *
     *
     * @return Campo instanciado vinculado ao Arquivo de Entidade
     */
    @Override
    public ItfCampoInstanciado getCampoSelecionado() {
        return campoInstanciadoSelecionado;
    }

    public ModalDadosPaginaSimples(UIViewRoot pViewRoot, Class pClassePagina) {
        viewRootOrigem = pViewRoot;
        classeOrigem = pClassePagina;
    }

    @Override
    public void enviarArquivoDoCampoUpload(FileUploadEvent event) {
        String nomeArquivo = null;
        try {
            nomeArquivo = event.getFile().getFileName();

            if (getCampoSelecionado().getComoArquivoDeEntidade().uploadArquivo(nomeArquivo, event.getFile().getContent()));
            PrimeFaces.current().dialog().closeDynamic(this);
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro enviando arquivo" + nomeArquivo, t);

        }
    }

    @Override
    public void enviarImagemPequenaUpload(FileUploadEvent event) {
        ItfModalDados.super.enviarImagemPequenaUpload(event); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ItfBeanSimples getEntidadeSelecionada() {

        if (entidadeSelecionada != null) {
            return entidadeSelecionada;
        }
        Map<String, Object> parametros = viewRootOrigem.getViewMap(false);
        ItfB_Pagina mbDoModal = null;
        if (parametros.containsKey(UtilSBCoreStringsMaiuculoMinusculo.getPrimeiraLetraMinuscula(classeOrigem.getSimpleName()))) {
            mbDoModal = (ItfB_Pagina) parametros.get(UtilSBCoreStringsMaiuculoMinusculo.getPrimeiraLetraMinuscula(classeOrigem.getSimpleName()));
        }
        if (mbDoModal == null) {
            if (parametros.containsKey("paginaAtual")) {
                ItfPaginaAtual paginaAtual = (ItfPaginaAtual) parametros.get("paginaAtual");
                mbDoModal = paginaAtual.getInfoPagina().getComoPaginaDeGestao();
            }

        }
        if (mbDoModal == null) {
            throw new UnsupportedOperationException("Impossível encontrar a entidade Selecionda");
        }
        return mbDoModal.getBeanSelecionado();
    }

    public void setEntidadeSelecionada(ItfBeanSimples entidadeSelecionada) {
        this.entidadeSelecionada = entidadeSelecionada;
    }

    public void setCampoSelecionado(ItfCampoInstanciado campoUpload) {
        this.campoInstanciadoSelecionado = campoUpload;

    }

    public void setComunicacaoDoMomento(ItfComunicacao comunicacaoDoMomento) {
        this.comunicacaoDoMomento = comunicacaoDoMomento;
    }

    @Override
    public String getObServacaoDeLog() {
        return observacaoDoLog;
    }

    @Override
    public void setObServacaoDeLog(String pObservacao) {
        observacaoDoLog = pObservacao;
    }

    @Override
    public ItfB_Pagina getPaginaVinculada() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void fecharModal() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ItfModalDados getComoModalDados() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getTitulo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getDescricao() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getRecursoXHTML() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getIdAreaExbicaoAcaoSelecionada() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getXhtmlAcaoAtual() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void executarAcaoSelecionada() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<InfoMBAcao> getInfoAcoes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<B_Pagina.BeanDeclarado> getInfoBeans() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Map<String, String> getInfoIds() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ItfB_Pagina getComoPaginaDeGestao() {
        return getPaginaVinculada();
    }

}
