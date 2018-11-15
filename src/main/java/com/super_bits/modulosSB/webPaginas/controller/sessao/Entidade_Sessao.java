package com.super_bits.modulosSB.webPaginas.controller.sessao;

import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfPermissao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfSessao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfUsuario;
import com.super_bits.modulosSB.SBCore.modulos.view.menu.MenusDaSessao;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWPServletTools;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.super_bits.modulosSB.SBCore.modulos.view.telas.ItfTelaUsuario;

/**
 * The persistent class for the MB_Sessao database table.
 *
 */
public class Entidade_Sessao implements ItfSessao, Serializable {

    protected static final int IDANONIMO = 0;

    private int idSessao;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date inicio;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fim;

    @Temporal(TemporalType.TIMESTAMP)
    private Date datahoraUltimaacao;

    private int id_filtroFinal;

    private List<ItfPermissao> acessos;

    private ItfUsuario usuario;

    private String ip;

    private String pastaTempDeSessao;

    public Entidade_Sessao() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDatahoraUltimaacao() {
        return this.datahoraUltimaacao;
    }

    public void setDatahoraUltimaacao(Date datahoraUltimaacao) {
        this.datahoraUltimaacao = datahoraUltimaacao;
    }

    public int getId_filtroFinal() {
        return this.id_filtroFinal;
    }

    public void setId_filtroFinal(int id_filtroFinal) {
        this.id_filtroFinal = id_filtroFinal;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public ItfUsuario getUsuario() {
        return usuario;
    }

    @Override
    public Date getInicio() {
        return inicio;
    }

    @Override
    public Date getFim() {
        return fim;
    }

    @Override
    public List<ItfPermissao> getAcoesRealizadas() {
        return acessos;
    }

    @Override
    public void setUsuario(ItfUsuario pUsuario) {
        usuario = pUsuario;
    }

    @Override
    public boolean isIdentificado() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ItfTelaUsuario getTelaUsuario() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isTipoViewDefinido() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getPastaTempDeSessao() {
        if (pastaTempDeSessao == null) {
            pastaTempDeSessao = UtilSBWPServletTools.getCaminhoLocalServletsResource() + "/arqSessao/" + usuario.getId();
        }
        return pastaTempDeSessao;
    }

    @Override
    public void encerrarSessao() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isAcessoPermitido(ItfAcaoDoSistema pAcao) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MenusDaSessao getMenusDaSessao() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getIdSessao() {
        if (idSessao == 0) {
            idSessao = Thread.currentThread().toString().hashCode();
        }
        return idSessao;
    }

}
