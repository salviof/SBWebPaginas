/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.PrimeFacesBeanModel;

import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import java.util.List;
import javax.persistence.EntityManager;

/**
 * ATENÇÃO A DOCUMENTAÇÃO DA CLASSE É OBRIGATÓRIA O JAVADOC DOS METODOS PUBLICOS
 * SÃO OBRIGATÓRIOS E QUANDO EXISTIR UMA INTERFACE DOCUMENTADA UMA REFERENCIA
 * DEVE SER CRIADA, A SINTAXE DE REFERENCIA É: @see_ NomeDAClasse#Metodo
 * DOCUMENTE DE FORMA OBJETIVA E EFICIENTE, NÃO ESQUEÇA QUE VOCÊ FAZ PARTE DE
 * UMA EQUIPE.
 *
 * @author <a href="mailto:salviof@gmail.com">Salvio Furbino</a>
 * @param <T> Entidade do autocomplete
 * @since 29/12/2015
 * @version 1.0
 *
 */
public class BP_AutoComplete<T> {

    private final EntityManager em;
    private Class classe;

    public BP_AutoComplete(EntityManager pEM, Class pClasseEntidade) {
        em = pEM;
        classe = pClasseEntidade;
    }

    public List<T> completaTexto(String parametro) {
        // todo melhorar para não refazer pesquisa toda hora
        return UtilSBPersistencia.getListaRegistrosLikeNomeCurto(parametro, classe, em);
    }

}
