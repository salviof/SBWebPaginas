package com.super_bits.modulosSB.webPaginas.JSFBeans.SBBeanModel.crud;

import com.super_bits.modulosSB.Persistencia.dao.DaoGenerico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoEntidadeSimples;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ViewScoped
@ManagedBean
public abstract class B_CRUD<T extends ComoEntidadeSimples> implements Serializable {

    public B_CRUD(DaoGenerico<T> pDao, Class<?> pClasse) {

    }

    /// METODOS CRUD
    public void salvaRegistroAtual() {
        try {

            //		dao.savarRegistroSafe(getRegistroAtual());
            //		fecharCaixaDeDialogo();
            //		mostrarAviso("Criado Com Sucesso!");
            //	loadTodos();
//			reset();
        } catch (Exception e) {

            //		manterCaixaDeDialogo();
            //		mostrarErro("Nao foi possivel gravar o Registro");
            e.printStackTrace();
        }
    }

    public void atualizaRegistroAtual() {
        try {
            //		dao.atualizarRegistro(getRegistroAtual());
            //		fecharCaixaDeDialogo();
            //		mostrarAviso("Atualizado com Sucesso");
            //		loadTodos();
            //		reset();
        } catch (Exception e) {
            //	manterCaixaDeDialogo();
            //	mostrarErro("Nao foi possivel Atualizar o Registro");
            e.printStackTrace();
        }
    }

    public void deletaRegistroAtual() {
        try {
            //		dao.deletarRegistro(getRegistroAtual());
            //		fecharCaixaDeDialogo();
            //		mostrarAviso("Exluido Com Sucesso");
            //	loadTodos();
            //		reset();
        } catch (Exception e) {
            //		manterCaixaDeDialogo();
            //		mostrarAviso("Nao foi possivel Excluir");
            e.printStackTrace();
        }
    }

}
