/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFManagedBeans.declarados.ConstAppFormEComponente.contantesView;

import com.super_bits.modulosSB.SBCore.modulos.view.telas.LayoutTelaAreaConhecida;
import java.io.Serializable;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 *
 * @author SalvioF
 */
@Named
@ApplicationScoped
public class LayoutIdsAreasConhecidas extends LayoutTelaAreaConhecida implements Serializable {

    public String getAREA_MENSAGEM_INTERFACE() {
        return AREA_MENSAGEM_INTERFACE;
    }

    public String getAREA_MENSAGEM_FORMULARIO() {
        return AREA_MENSAGEM_FORMULARIO;
    }

    public String getAREA_TOPO_ITERNFACES() {
        return AREA_TOPO_ITERNFACES;
    }

    public String getAREA_MENU_ITERFACES() {
        return AREA_MENU_ITERFACES;
    }

    public String getAREA_FORMULARIO() {
        return AREA_FORMULARIO;
    }

    public String getAREA_RODAPE() {
        return AREA_RODAPE;
    }

    public String getAREA_CONTEUDO() {
        return AREA_CONTEUDO;
    }

    public String getAREA_CHAT() {
        return AREA_CHAT;
    }

    public String getAREA_CARRINHO_COMPRA() {
        return AREA_CARRINHO_COMPRA;
    }

    public String getAREA_INPUT_GENERICO() {
        return AREA_INPUT_GENERICO;
    }
}
