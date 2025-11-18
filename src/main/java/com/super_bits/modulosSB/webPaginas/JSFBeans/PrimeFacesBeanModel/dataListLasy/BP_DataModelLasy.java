/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.PrimeFacesBeanModel.dataListLasy;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreListas;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreListasObjeto;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringValidador;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ComoEntidadeSimples;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWPServletTools;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;
import jersey.repackaged.com.google.common.collect.Lists;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;

/**
 *
 * @author SalvioF
 * @param <T>
 */
public class BP_DataModelLasy<T extends ComoEntidadeSimples> extends LazyDataModel<T> {

    private final List<T> listaCompleta;
    private final List<String> camposPesquisa;
    private final boolean iniciarMostrandoTodos;

    public BP_DataModelLasy(List<T> listaCompleta) {
        this(listaCompleta, null, false);
    }

    public BP_DataModelLasy(List<T> listaCompleta, boolean pIniciarMostrandoTodos) {
        this(listaCompleta, null, pIniciarMostrandoTodos);
    }

    public BP_DataModelLasy(List<T> listaCompleta, List<String> camposPesquisa, boolean iniciarMostrandoTodos) {
        this.listaCompleta = listaCompleta;
        this.camposPesquisa = camposPesquisa;
        this.iniciarMostrandoTodos = iniciarMostrandoTodos;
    }

    public BP_DataModelLasy(List<T> listaCompleta, List<String> pCampoPesquisa) {
        this(listaCompleta, null, false);
    }

    @Override
    public T getRowData(String rowKey) {
        try {
            return listaCompleta.stream().filter(item -> item.getId() == Long.getLong(rowKey)).findFirst().get();
        } catch (Throwable t) {
            return null;
        }
    }

    @Override
    public List<T> load(int first, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {
        return load(first, pageSize, null, SortOrder.ASCENDING, filterBy);
    }

    // KD O LOAD?
    public List<T> load(int first, int pageSize, String sortField, SortOrder multiSortMeta, Map<String, FilterMeta> filters) {
        ConcurrentLinkedQueue lista = new ConcurrentLinkedQueue();

        if (filters.isEmpty()) {
            if (iniciarMostrandoTodos) {
                List listac = new ArrayList();

                listaCompleta.forEach(listac::add);
                setRowCount(listaCompleta.size());
                return listac;
            } else {
                return new ArrayList();
            }
        }
        String parametro = filters.values().iterator().next().toString().toUpperCase();
        boolean pesquisaNumerica = UtilSBCoreStringValidador.isContemApenasNumero(parametro);
        Map<String, String[]> parametrosreq = UtilSBWPServletTools.getRequestAtual().getParameterMap();
        long streamStartTime = System.currentTimeMillis();
        if (camposPesquisa != null) {

            //    lista.addAll(UtilSBCoreListasObjeto.filtrarOrdenandoMaisParecidos(listaCompleta, parametro, 15));
            camposPesquisa.stream().forEach(campo
                    -> {
                lista.addAll(listaCompleta.stream().filter(item
                        -> item.getCampoInstanciadoByNomeOuAnotacao(campo).contem(parametro)
                ).collect(Collectors.toList()));
            });

        } else {
            UtilSBWPServletTools.getRequestAtual().getParameter("globalFilter");
            if (true
                    || filters.size() == 1 && filters.keySet().iterator().next().equals("globalFilter")) {

                if (!pesquisaNumerica) {
                    lista.addAll(UtilSBCoreListasObjeto.filtrarOrdenandoMaisParecidos(listaCompleta, parametro, 15));
                    //lista.addAll(listaCompleta.stream().filter(item
                    //      -> item.getNome().toUpperCase().contains(parametro)
                    //).collect(Collectors.toList()));
                } else {
                    final long parmetroNumerico = Long.valueOf(parametro);
                    if (!listaCompleta.isEmpty()) {
                        if (listaCompleta.get(0).getClass().getSimpleName().contains("Produto")
                                && !listaCompleta.get(0).getCPinst("codigoDeBarras").isCampoNaoInstanciado()) {
                            lista.addAll(listaCompleta.stream().filter(item
                                    -> (item.getCPinst("codigoDeBarras").getValor().toString().contains(parametro))
                            ).collect(Collectors.toList()));
                        } else {
                            lista.addAll(listaCompleta.stream().filter(item
                                    -> (item.getId() == parmetroNumerico)
                            ).collect(Collectors.toList()));
                        }
                    }

                }

            } else {
                filters.keySet().parallelStream().forEach(campo -> {
                    lista.addAll(listaCompleta.stream().filter(item
                            -> item.getCampoInstanciadoByNomeOuAnotacao(campo).contem(filters.get(campo))
                    ).collect(Collectors.toList()));
                });
            }
        }
        List<T> listaOrdenada = Lists.newArrayList(lista);
        this.setRowCount(listaOrdenada.size());
        long streamEndTime = System.currentTimeMillis();
        SBCore.enviarAvisoAoUsuario("Tempo Total: " + (streamEndTime - streamStartTime));

        if (listaOrdenada.size() > pageSize) {
            try {
                return listaOrdenada.subList(first, first + pageSize);
            } catch (IndexOutOfBoundsException e) {
                return listaOrdenada.subList(first, first + (listaOrdenada.size() % pageSize));
            }
        } else {
            return listaOrdenada;
        }
    }

    @Override
    public int count(Map<String, FilterMeta> map) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
