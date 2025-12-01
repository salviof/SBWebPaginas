package com.super_bits.modulosSB.webPaginas.JSFBeans.SBBeanModel.DEPRECIADO;

import com.super_bits.modulosSB.SBCore.UtilGeral.ClasseTipada;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringFiltros;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringValidador;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ComoEntidadeSimples;
import java.io.Serializable;
import java.util.List;

/**
 *
 *
 * Classe para lista tipada com título
 *
 *
 * @author Salvio
 *
 */
public class B_Lista<T extends ComoEntidadeSimples> extends ClasseTipada implements Serializable {

    private List<? extends ComoEntidadeSimples> itens;
    private String nomeLista;

    public B_Lista(String nomeLista, List<? extends ComoEntidadeSimples> itens, Class<?> pClasse) {
        super(pClasse);
        this.itens = itens;
        this.nomeLista = nomeLista;
    }

    public String getNomeJsfAmigavel() {
        return UtilCRCStringFiltros.gerarUrlAmigavel(nomeLista);
    }

    public List<? extends ComoEntidadeSimples> getItens() {
        return itens;
    }

    public void setItens(List<? extends ComoEntidadeSimples> itens) {
        this.itens = itens;
    }

    public String getNomeLista() {
        return nomeLista;
    }

    public void setNomeLista(String nomeLista) {
        this.nomeLista = nomeLista;
    }

    private Boolean validaParametrosIniciais() {
        // TODO
        return true;
    }

    public int getQuantCampos() {
        return 4;
    }

    public String getLabel(String campo) {
        return "teste";
    }

}
