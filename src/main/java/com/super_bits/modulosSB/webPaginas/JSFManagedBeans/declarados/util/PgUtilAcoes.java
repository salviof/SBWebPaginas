/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFManagedBeans.declarados.util;

import com.super_bits.modulosSB.SBCore.modulos.objetos.acao.AcaoTransient;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ComoAcaoController;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoFormulario;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.siteMap.MapaDeFormularios;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ComoEntidadeSimplesSomenteLeitura;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ComoAcaoDoSistema;

/**
 *
 * @author desenvolvedor
 */
@RequestScoped
@Named
public class PgUtilAcoes implements Serializable {

    public List<ComoAcaoDoSistema> getListaAcaoSelecaoRegistro() {
        List<ComoAcaoDoSistema> lista = new ArrayList<>();
        AcaoTransient acao = new AcaoTransient();
        acao.setNome("Selecionar este");
        acao.setIconeAcao("fa fa-hand-pointer-o");
        lista.add(acao);
        return lista;
    }

    /**
     *
     * @param pAcao
     * @param pParametros Parametros de Url para construção da URL
     * @return A url para acesso a ação de formulário
     */
    public String getUrlAcao(ComoAcaoController pAcao, ComoEntidadeSimplesSomenteLeitura... pParametros) {
        if (pParametros != null) {
            if (pParametros.length == 1) {
                if (pParametros[0] == null) {

                    return MapaDeFormularios.getUrlFormulario(pAcao);
                }
            }
        }
        return MapaDeFormularios.getUrlFormulario(pAcao, (Object[]) pParametros);
    }

    public String getUrlAcao(ItfAcaoFormulario pAcao, ComoEntidadeSimplesSomenteLeitura... pParametros) {
        if (pParametros != null) {
            if (pParametros.length == 1) {
                if (pParametros[0] == null) {

                    return MapaDeFormularios.getUrlFormulario(pAcao);
                }
            }
        }
        return MapaDeFormularios.getUrlFormulario(pAcao, (Object[]) pParametros);
    }

    public String getUrlAcao(ItfAcaoFormulario pAcao) {
        return MapaDeFormularios.getUrlFormulario(pAcao);
    }

    public String getUrlAcao(ComoAcaoController pAcao) {
        return MapaDeFormularios.getUrlFormulario(pAcao);
    }

}
