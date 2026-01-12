/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.controller.sessao;

import com.super_bits.modulos.SBAcessosModel.model.UsuarioSB;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.Controller.qualificadoresCDI.sessao.QlSessaoRequestScoped;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.ItensGenericos.basico.SessaoOffline;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWPServletTools;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoSessao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoUsuario;

/**
 *
 * @author salvio
 */
@RequestScoped
@QlSessaoRequestScoped
public class SessaoAtualRequestScoped extends SessaoOffline implements ComoSessao, Serializable {

    private HttpServletRequest req;

    public SessaoAtualRequestScoped() {

    }

    public HttpServletRequest getReq() {
        return req;
    }

    public void setReq(HttpServletRequest req) {
        this.req = req;
    }

    @PostConstruct
    public void inicio() {
        System.out.println("sessaoIniciada");
    }

    @Override
    public ComoUsuario getUsuario() {
        if (!isIdentificado()) {

            String emailUsuario = req.getHeader("usuario");
            System.out.println("Email enviado:" + emailUsuario);
            UsuarioSB usr = (UsuarioSB) SBCore.getServicoPermissao().getUsuarioByEmail(emailUsuario);
            setUsuario(usr);
        }
        return super.getUsuario(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

}
