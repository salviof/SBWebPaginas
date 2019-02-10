/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFManagedBeans.declarados.util;

import com.super_bits.modulosSB.SBCore.modulos.objetos.MapaObjetosProjetoAtual;
import com.super_bits.modulosSB.SBCore.modulos.view.ServicoVisualizacaoAbstrato;
import com.super_bits.modulosSB.SBCore.modulos.view.componenteObjeto.NovoContainerVisualizacaoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.view.telas.FabTipoTamanhoTelas;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author desenvolvedor
 */
@ViewScoped
@Named
public class PgUtilObjetosContainersVisuais implements Serializable {

    private NovoContainerVisualizacaoObjeto novoContainer;
    private int numeroColunas = 3;
    private boolean criarVersaoMobile = false;
    private String objetoSelecionado;
    private List<String> lista;

    @PostConstruct
    public void inicio() {
        lista = new ArrayList<>();
        MapaObjetosProjetoAtual.getListaTodosEstruturaObjeto().forEach(est -> lista.add(est.getNomeEntidade()));
    }

    public void criarContainer() {
        novoContainer = new NovoContainerVisualizacaoObjeto(
                MapaObjetosProjetoAtual.getClasseDoObjetoByNome(objetoSelecionado), ServicoVisualizacaoAbstrato.TIPO_VISUALIZACAO_ITEM.PUBLICADO);

        novoContainer.setColunas(numeroColunas);

        System.out.println(novoContainer.getEntrLocalArquivoDesktop());
    }

    public NovoContainerVisualizacaoObjeto getNovoContainer() {
        return novoContainer;
    }

    public void setNovoContainer(NovoContainerVisualizacaoObjeto novoContainer) {
        this.novoContainer = novoContainer;
    }

    public int getNumeroColunas() {
        return numeroColunas;
    }

    public void setNumeroColunas(int numeroColunas) {
        this.numeroColunas = numeroColunas;
    }

    public String getObjetoSelecionado() {
        return objetoSelecionado;
    }

    public void setObjetoSelecionado(String objetoSelecionado) {
        this.objetoSelecionado = objetoSelecionado;
    }

    public List<String> getLista() {
        return lista;
    }

    public void setLista(List<String> lista) {
        this.lista = lista;
    }

    public boolean isCriarVersaoMobile() {
        return criarVersaoMobile;
    }

    public void setCriarVersaoMobile(boolean criarVersaoMobile) {
        this.criarVersaoMobile = criarVersaoMobile;
    }

}
