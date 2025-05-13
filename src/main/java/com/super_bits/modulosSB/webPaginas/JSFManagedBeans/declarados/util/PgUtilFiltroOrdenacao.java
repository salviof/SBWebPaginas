/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFManagedBeans.declarados.util;

import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringComparador;
import com.super_bits.modulosSB.SBCore.modulos.objetos.comparacao.FabTipoPesquisaGenerica;
import com.super_bits.modulosSB.SBCore.modulos.objetos.comparacao.ItemSimilar;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.interfaces.ItfPaginaAtual;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.hibernate.Hibernate;
import org.hibernate.collection.internal.PersistentBag;

/**
 *
 * @author salvio
 */
@RequestScoped
@Named
public class PgUtilFiltroOrdenacao {

    private Map<String, List<ItfBeanSimples>> mapaPesquisas;

    @Inject
    private ItfPaginaAtual paginaAtual;

    public boolean filtrarColuna(Object item, String pFiltro, Locale coluna) {
        FabTipoPesquisaGenerica tipoPesquisa = FabTipoPesquisaGenerica.getTipoPesquisaByTermo(pFiltro);
        ItemSimilar itemSimilar = new ItemSimilar((ItfBeanSimples) item, UtilSBCoreStringComparador.normalizarTexto(pFiltro), tipoPesquisa);
        itemSimilar.getNota();

        return itemSimilar.getNota() > 0.8;
    }

    public int ordernarPorColuna(Object comparacao1, Object comparacao2) {
        if (comparacao1 instanceof PersistentBag) {

            Hibernate.initialize(comparacao1);
            Hibernate.initialize(comparacao2);
            PersistentBag valor1 = (PersistentBag) comparacao1;
            PersistentBag valor2 = (PersistentBag) comparacao2;
            if (valor1.isEmpty() && valor2.isEmpty()) {
                return 0;
            }
            if (valor2.isEmpty()) {
                return 1;
            }
            if (valor1.isEmpty()) {
                return -1;
            }
            if (valor1.get(0) instanceof ItfBeanSimples) {
                ItfBeanSimples item = (ItfBeanSimples) valor1.get(0);
                ItfBeanSimples item2 = (ItfBeanSimples) valor2.get(0);
                return item.getNome().compareTo(item2.getNome());
            }
        }
        if (comparacao1 instanceof ItfBeanSimples) {
            ItfBeanSimples item = (ItfBeanSimples) comparacao1;
            ItfBeanSimples item2 = (ItfBeanSimples) comparacao2;
            return item.getNome().compareTo(item2.getNome());
        }
        if (comparacao1 instanceof Double) {
            Double num1 = new Double(comparacao1.toString());
            Double num2 = new Double(comparacao2.toString());
            return num1.compareTo(num2);
            //System.out.println("double");
        }
        if (comparacao1 instanceof Comparable) {
            return ((Comparable) comparacao1).compareTo((Comparable) comparacao2);
        }
        return 0;
    }
}
