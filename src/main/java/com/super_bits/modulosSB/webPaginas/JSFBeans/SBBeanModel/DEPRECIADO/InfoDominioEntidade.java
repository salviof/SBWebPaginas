/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.SBBeanModel.DEPRECIADO;

import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreReflexao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.UtilSBCoreReflexaoAtributoDeObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.MapaObjetosProjetoAtual;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.reflexao.anotacoes.beans.InfoMB_Bean;
import java.io.Serializable;
import java.lang.reflect.Field;

/**
 *
 * @author Salvio
 */
public class InfoDominioEntidade implements Serializable {

    private String classe;
    private String descricao;
    private String exemplo;
    private final String formularioVisualizacao;
    private String formularioNulo;
    private String formularioLista;
    private final String chamada;
    private String formularioLab;

    public String getChamada() {
        return chamada;
    }

    public String getFormularioLista() {
        return formularioLab;
    }

    public InfoDominioEntidade(Field campo) {
        InfoMB_Bean infoBean = campo.getAnnotation(InfoMB_Bean.class);
        Class classeObjeto = UtilSBCoreReflexaoAtributoDeObjeto.getClassePrincipalDoCampo(campo);
        classe = classeObjeto.getSimpleName();
        //classe = campo.getType().getSimpleName();
        if (infoBean != null) {
            descricao = infoBean.descricao();
            exemplo = infoBean.exemplo();
        }
        chamada = "#{" + campo.getDeclaringClass().getSimpleName() + "." + campo.getName() + "}";

        formularioVisualizacao = MapaObjetosProjetoAtual.getVisualizacaoDoObjeto(classeObjeto);

    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getExemplo() {
        return exemplo;
    }

    public void setExemplo(String exemplo) {
        this.exemplo = exemplo;
    }

    public String getVisualizacaoItem() {
        return formularioVisualizacao;
    }

    public String getVisualizacaoItemNulo() {
        return formularioNulo;
    }

    public String getFormularioVisualizacaoItemListas() {
        return formularioLista;
    }

    public String getFormularioLab() {
        if (formularioLab == null) {
            formularioLab = SBCore.getCentralVisualizacao().getCaminhoXhtmlItemCardLab(UtilSBPersistencia.getEntidadeByNomeClasse(classe));

        }

        return formularioLab;
    }

}
