package com.super_bits.modulosSB.webPaginas.JSFBeans.SBBeanModel.depreciado.filtro.old;

import com.super_bits.modulosSB.Persistencia.dao.SBNQ;
import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeSimples;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import java.io.Serializable;

@Deprecated
public class OpcaoFiltro extends EntidadeSimples implements Serializable {

    @InfoCampo(tipo = FabTipoAtributoObjeto.ID)
    private Long id;
    @InfoCampo(tipo = FabTipoAtributoObjeto.NOME)
    private String nome;

    private SBNQ qrySelecao;
    private SBNQ qryItensMultiplos;
    private boolean itensMultiplos = false;

    public OpcaoFiltro(String pNome, SBNQ pQrySelecao) {
        super();
        setNome(pNome);
        setQrySelecao(pQrySelecao);

    }

    public OpcaoFiltro(String pNome, SBNQ pSelecaoItems, SBNQ PSelecaoMultipla) {
        super();
        setNome(pNome);

        setQrySelecao(pSelecaoItems);
        setQryItensMultiplos(PSelecaoMultipla);
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isItensMultiplos() {
        return itensMultiplos;
    }

    public SBNQ getQrySelecao() {
        return qrySelecao;
    }

    public void setQrySelecao(SBNQ qrySelecao) {
        this.qrySelecao = qrySelecao;
    }

    public SBNQ getQryItensMultiplos() {
        return qryItensMultiplos;
    }

    public void setQryItensMultiplos(SBNQ qryItensMultiplos) {
        if (qryItensMultiplos != null) {
            itensMultiplos = true;
        } else {
            itensMultiplos = false;
        }
        this.qryItensMultiplos = qryItensMultiplos;
    }

}
