package com.super_bits.modulosSB.webPaginas.JSFBeans.SBBeanModel.depreciado.filtro.old;

import com.super_bits.modulosSB.Persistencia.dao.DaoGenerico;
import com.super_bits.modulosSB.Persistencia.dao.SBNQ;

import com.super_bits.modulosSB.SBCore.UtilGeral.ClasseTipada;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringFiltros;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ComoEntidadeSimples;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWP_JSFTools;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;

@Deprecated
public abstract class MB_Filtro<T extends ComoEntidadeSimples> extends ClasseTipada implements Serializable {

    // o primeiro sempre será o Default
    protected abstract List<OpcaoFiltro> definePrefiltros();

    protected abstract String defineNomeFiltro();

    protected abstract String defineStrTodos();

    protected abstract Class<?> defineTipo();

    private String nomeFiltro = defineNomeFiltro();
    private List<OpcaoFiltro> opcoes;
    private ComoEntidadeSimples opcaoAtual;
    private List<T> selecao;
    private String strTodos;

    enum Tipofiltro {

        INDIVID
    }

    public List<T> getSelecao() {

        return selecao;
    }

    public String getNomeParametroFiltroPagina() {
        return 'p' + getTipo().getSimpleName();
    }

    public void aplicaValorPadrao() {
        opcaoAtual = opcoes.get(0);
    }

    public List<Long> getSelecaoSimples() {
        List<Long> respostatemp = new ArrayList<>();
        respostatemp.add(1l);
        respostatemp.add(2l);

        if (selecao == null || selecao.isEmpty()) {
            return respostatemp;
        }

        List<Long> resposta = new ArrayList<>();;

        for (T item : selecao) {
            resposta.add(((ComoEntidadeSimples) item).getId());
        }
        return resposta;
    }

    private void defineVariaveisIniciais() {
        classe = defineTipo();
        strTodos = defineStrTodos();
    }

    // Apenas para compatibilidade com CDI
    public MB_Filtro() {
        super();
        defineVariaveisIniciais();
    }

    public MB_Filtro(Class<?> pClasse) {
        super(pClasse);
        defineVariaveisIniciais();

    }

    public void configSelecao() {
        //definindo a selecao de dados representada pela escolha
        DaoGenerico<T> qrSelecao = new DaoGenerico<T>(classe);
        this.selecao = (List<T>) qrSelecao.achaItensPorSBNQ(((OpcaoFiltro) opcaoAtual)
                .getQrySelecao());

    }

    @PostConstruct
    private void startBean() {
        opcoes = new ArrayList<OpcaoFiltro>();
        validaConfig();

    }

    public void validaConfig() {
        // TODO VERIFICA SE AS CONFIGS OBRIGATORIAS FORAM SETADAS

        nomeFiltro = defineNomeFiltro();
        opcoes.clear();
        List<OpcaoFiltro> opAdd = definePrefiltros();
        for (OpcaoFiltro op : opAdd) {
            adcionaOpcao(op);
        }
        opcaoAtual = opcoes.get(0);

        if (getNomeFiltro() == null) {
            UtilSBWP_JSFTools.mensagens().erroSistema("Voce nao especificou o nome do filtro em " + this.getClass().getName());
        }
        if (getOpcoes().isEmpty()) {
            UtilSBWP_JSFTools.mensagens().erroSistema("Nao existe nenhum item predefinido para o filtro" + nomeFiltro);
        }

    }

    public List<OpcaoFiltro> getOpcoes() {
        return opcoes;
    }

    public String getNomeFiltro() {
        return nomeFiltro;
    }

    public String getNomeFiltroUrlAmigavel() {
        return UtilCRCStringFiltros.gerarUrlAmigavel(nomeFiltro);
    }

    protected void adcionaOpcao(OpcaoFiltro pFiltro) {
        // / se For Individual

        if (pFiltro.isItensMultiplos()) {
            DaoGenerico<T> daoOpcoes = new DaoGenerico<T>(classe);
            List<T> listaDeOpcoesdoFiltro = (List<T>) daoOpcoes.achaItensPorSBNQ(pFiltro
                    .getQryItensMultiplos());

            int i = 0;
            for (T itemopcao : listaDeOpcoesdoFiltro) {

                System.out.println(((ComoEntidadeSimples) itemopcao).getNomeCurto());
                Long idNovoItem = ((ComoEntidadeSimples) itemopcao).getId();
                Object[] paramtroID = {idNovoItem};
                SBNQ qryItem = new SBNQ(pFiltro.getQrySelecao()
                        .getSql(), paramtroID);
                OpcaoFiltro novaopcao = new OpcaoFiltro(
                        ((ComoEntidadeSimples) itemopcao).getNomeCurto(), qryItem);
                novaopcao.setId(idNovoItem);
                i = i + 1;
                opcoes.add(novaopcao);
            }

        } else {
            //		System.out.println("Adcionando opcao Multipla");
            opcoes.add(pFiltro);
        }

    }

    protected void setNomeFiltro(String nomeFiltro) {
        this.nomeFiltro = nomeFiltro;
    }

    public void setOpcaoAtualByNome(String pNomeOpcao) {

        for (OpcaoFiltro op : opcoes) {
            if (op.getNomeCurto().equalsIgnoreCase(pNomeOpcao) || op.getNomeCurtoURLAmigavel().equalsIgnoreCase(UtilCRCStringFiltros.gerarUrlAmigavel(pNomeOpcao))) {
                setOpcaoAtual(op);
                return;
            }
        }

        UtilSBWP_JSFTools.mensagens().erroSistema("Item" + pNomeOpcao + " ou " + UtilCRCStringFiltros.gerarUrlAmigavel(pNomeOpcao) + "não encontrado no filtro" + this.getClass().getSimpleName());
    }

    public void setOpcaoAtual(ComoEntidadeSimples pOpcaoAtual) {
        // configurando o Parametro da Pagina

        // definindo a opcao
        this.opcaoAtual = (ComoEntidadeSimples) pOpcaoAtual;
        configSelecao();
    }

    public ComoEntidadeSimples getOpcaoAtual() {
        return opcaoAtual;
    }

    public void testaresultado() {
        validaConfig();
        DaoGenerico<T> qrSelecao = new DaoGenerico<T>(classe);
        this.selecao = (List<T>) qrSelecao.achaItensPorSBNQ(((OpcaoFiltro) opcaoAtual)
                .getQrySelecao());
        for (T teste : selecao) {
            System.out.println(((ComoEntidadeSimples) teste).getNomeCurto());
        }

    }

    public String getStrTodos() {
        return strTodos;
    }

    public void setStrTodos(String strTodos) {
        this.strTodos = strTodos;
    }

}
