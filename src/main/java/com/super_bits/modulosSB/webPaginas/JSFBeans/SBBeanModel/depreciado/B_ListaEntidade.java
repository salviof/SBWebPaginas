package com.super_bits.modulosSB.webPaginas.JSFBeans.SBBeanModel.depreciado;

import com.super_bits.modulosSB.Persistencia.dao.DaoGenerico;
import com.super_bits.modulosSB.Persistencia.dao.SBNQ;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;
import com.super_bits.modulosSB.webPaginas.JSFBeans.SBBeanModel.DEPRECIADO.B_Lista;
import java.io.Serializable;
import java.util.List;

public abstract class B_ListaEntidade<T extends ItfBeanSimples> extends B_Lista implements Serializable {

    private DaoGenerico<T> dao;

    public B_ListaEntidade(String pNomeLista, SBNQ pSbqry, Class<?> pClasse) {
        super(pNomeLista, UtilSBPersistencia.getListaBySBNQ(pSbqry), pClasse);
    }

    private List<T> lista;

    // Seta parametros como Chave de seguranca e lista padrao
    protected abstract void setParametrosIniciais();

    public void loadTodos() {
        lista = dao.todos();
    }

}
