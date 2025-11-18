package com.super_bits.modulosSB.webPaginas.JSFBeans.SBBeanModel.DEPRECIADO;

import com.super_bits.modulosSB.Persistencia.ConfigGeral.CSBNQ.Qr;
import com.super_bits.modulosSB.Persistencia.dao.DaoGenerico;

import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeSimplesORM;
import com.super_bits.modulosSB.SBCore.UtilGeral.ClasseTipada;
import java.util.List;

public abstract class B_autoComplete<T> extends ClasseTipada {

    DaoGenerico<EntidadeSimplesORM> daoLike;
    private List<EntidadeSimplesORM> sugestoes;
    private Qr SqlLocalizacao = Qr.SqlGenericoLike;

    protected abstract void setVariaveis();

    private void iniClass(Class<?> pClasse) {
        daoLike = new DaoGenerico<EntidadeSimplesORM>(pClasse) {
        };
        setVariaveis();
    }

    public B_autoComplete(Class<?> pClasse) {
        super(pClasse);

    }

    private void atulalizaSugestoes(String pParametro) {

        setSugestoes(daoLike.achaItensPorSBNQ(Qr.SqlGenericoLike, "teste"));

    }

    public void setSugestoes(List<EntidadeSimplesORM> sugestoes) {
        this.sugestoes = sugestoes;
    }

    public List<EntidadeSimplesORM> getSugestoes() {
        return sugestoes;
    }

}
