/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.PrimeFacesBeanModel.dataListLasy;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringValidador;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;
import jersey.repackaged.com.google.common.collect.Lists;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;

/**
 *
 * @author SalvioF
 * @param <T>
 */
public class BP_DataModelLasy<T extends ItfBeanSimples> extends LazyDataModel<T> {

    private final List<T> listaCompleta;
    private final List<String> camposPesquisa;

    public BP_DataModelLasy(List<T> listaCompleta) {
        this.listaCompleta = listaCompleta;
        camposPesquisa = null;
    }

    public BP_DataModelLasy(List<T> listaCompleta, List<String> pCampoPesquisa) {
        this.listaCompleta = listaCompleta;
        camposPesquisa = pCampoPesquisa;
    }

    @Override
    public T getRowData(String rowKey) {
        try {
            return listaCompleta.stream().filter(item -> item.getId() == Integer.getInteger(rowKey)).findFirst().get();
        } catch (Throwable t) {
            return null;
        }
    }

    @Override
    public Object getRowKey(T object) {
        return object.getId();
    }

    @Override
    public List<T> load(int first, int pageSize, List<SortMeta> multiSortMeta, Map<String, Object> filters) {
        ConcurrentLinkedQueue lista = new ConcurrentLinkedQueue();

        if (filters.isEmpty()) {
            return new ArrayList();
        }
        String parametro = filters.values().iterator().next().toString().toUpperCase();
        boolean pesquisaNumerica = UtilSBCoreStringValidador.isContemApenasNumero(parametro);

        long streamStartTime = System.currentTimeMillis();
        if (camposPesquisa != null) {

            camposPesquisa.stream().forEach(campo
                    -> {
                lista.addAll(listaCompleta.stream().filter(item
                        -> item.getCampoInstanciadoByNomeOuAnotacao(campo).contem(parametro)
                ).collect(Collectors.toList()));
            });

        } else {
            if (filters.size() == 1 && filters.keySet().iterator().next().equals("globalFilter")) {

                if (!pesquisaNumerica) {
                    lista.addAll(listaCompleta.stream().filter(item
                            -> item.getNome().toUpperCase().contains(parametro)
                    ).collect(Collectors.toList()));
                } else {
                    final int parmetroNumerico = Integer.valueOf(parametro);
                    lista.addAll(listaCompleta.stream().filter(item
                            -> (item.getId() == parmetroNumerico)
                    ).collect(Collectors.toList()));
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

}
