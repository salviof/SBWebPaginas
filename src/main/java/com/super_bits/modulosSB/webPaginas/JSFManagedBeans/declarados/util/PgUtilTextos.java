/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFManagedBeans.declarados.util;

import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreReflexaoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoEntidade;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author desenvolvedor
 */
@RequestScoped
@Named
public class PgUtilTextos {

    public String gerarTextoNenhumEncontrado(ItfAcaoEntidade pAcaoEntidade) {
        InfoObjetoSB infoObjeto = UtilSBCoreReflexaoObjeto.getInfoClasseObjeto(pAcaoEntidade.getClasseRelacionada());
        if (infoObjeto.generoFeminino()) {
            return "Nenhuma " + infoObjeto.tags()[0] + "foi encontrada";
        } else {
            return "Nenhum " + infoObjeto.tags()[0] + " foi encontrado";
        }
    }

}
