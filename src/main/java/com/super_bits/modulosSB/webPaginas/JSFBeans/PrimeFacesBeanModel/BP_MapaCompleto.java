package com.super_bits.modulosSB.webPaginas.JSFBeans.PrimeFacesBeanModel;

import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeEnderecavel;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanLocalizavel;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;
import com.super_bits.modulosSB.webPaginas.JSFBeans.SBBeanModel.depreciado.B_ItemGenerico;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

public abstract class BP_MapaCompleto<T> extends B_ItemGenerico implements Serializable {

    public abstract List<T> getRegistros();

    private static final long serialVersionUID = 1L;
    private MapModel mapa;
    private Marker marcador;
    private ItfBeanLocalizavel registroSelecionado;
    private boolean temRegistros;
    private String cepLocalizacao;

    private void setRegistroSelecionado(String pNome) {

        List<T> registros = getRegistros();
        for (T registro : registros) {
            if (((ItfBeanLocalizavel) registro).getNomeCurto().equals(pNome)) {
                registroSelecionado = (ItfBeanLocalizavel) registro;
                break;
            }
        }

    }

    // apenas para compatibilidade com WELD
    protected BP_MapaCompleto() {
        System.out.println("ATENCAO mapa iniciado ignorando o construtor");
    }

    @PostConstruct
    public void startBean() {
        //DaoGenerico<T> dao = new DaoGenerico<T>(Prestador.class,dados.getEm());
        configRegistros();

    }

    protected BP_MapaCompleto(Class<T> tipo) {

        super(tipo);
        mapa = new DefaultMapModel();

        //TODO Ver uma form
    }

    public MapModel getMapa() {
        return mapa;
    }

    public void onMarkerSelect(OverlaySelectEvent event) {
        marcador = (Marker) event.getOverlay();
        setRegistroSelecionado(marcador.getTitle());
    }

    public void configRegistros() {
        //	 List<T> registros = setRegistros(getRegistros());

        mapa = new DefaultMapModel();

        List<T> registros = getRegistros();
        if (registros == null || registros.size() == 0) {
            temRegistros = false;
            return;
        }
        List<EntidadeEnderecavel> locais = new ArrayList();
        for (T registro : registros) {
            locais.add((EntidadeEnderecavel) registro);
        }

        setRegistroSelecionado(((ItfBeanSimples) registros.get(0)).getNomeCurto());

        for (EntidadeEnderecavel local : locais) {
            System.out.println("Falta implementar MApa");
        }
        //	mapa.addOverlay(new Marker(  , local.getNomeCurto(),null,local.getImgPequena()));
        //TODO adciona novo LAtlong;

    }

    public Marker getMarcador() {
        return marcador;
    }

    public ItfBeanLocalizavel getRegistroSelecionado() {
        return registroSelecionado;
    }

    public String getCepLocalizacao() {
        return cepLocalizacao;
    }

    public void setCepLocalizacao(String cepLocalizacao) {
        this.cepLocalizacao = cepLocalizacao;
    }

    public boolean isTemRegistros() {
        return temRegistros;
    }

}
