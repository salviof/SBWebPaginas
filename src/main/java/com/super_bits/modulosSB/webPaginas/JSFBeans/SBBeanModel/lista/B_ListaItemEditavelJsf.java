/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.SBBeanModel.lista;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.listas.B_ListaItemEditavel;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import javax.faces.context.FacesContext;

/**
 *
 * @author SalvioF
 */
public class B_ListaItemEditavelJsf extends B_ListaItemEditavel {

    public B_ListaItemEditavelJsf(ItfCampoInstanciado pCampoInstanciado) {
        super(pCampoInstanciado);
    }

    @Override
    public void removerItemSelecionadoPeloIndice() {
        int indice;
        boolean parametroEnviado = false;
        for (String parametro : FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().keySet()) {
            if (parametro.contains("indiceSubformulario")) {
                try {
                    indice = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(parametro));
                    setIndiceItemSelecionado(indice);
                    parametroEnviado = true;
                } catch (Throwable t) {
                    parametroEnviado = false;
                }
                break;
            }
        }
        if (!getListaObjetosSelecionados().isEmpty()) {
            if (parametroEnviado) {
                if (getIndiceItemSelecionado() <= getListaObjetosSelecionados().size() - 1) {
                    getListaObjetosSelecionados().remove(getIndiceItemSelecionado());
                } else {
                    //resolução holística para resolver tentativas de remover itens rapidamente, resultando em envio de indice errado a solução ideal é bloquear e rmoção dupla de itens bloqueando a tela para o usuário a cada ajax
                    //este else resolve quando clicado no ultimo elemento, clicando em 2 elementos no meio de uma lista, vai exluir o item errado, podermiamos também enviar o total de itens como paramentro, desta forma, teriamos mais
                    // uma dado para trabalhar e mitigar a exclusão de item errado subtraindo sempre do indice a diferença entre o total agora para o total anterior, o que funcionaria bem para um click rápido em sequencia de cima para baixo
                    // que é o mais facil de acontecer..
                    getListaObjetosSelecionados().remove(getListaObjetosSelecionados().size() - 1);
                }
            }
        }
    }

}
