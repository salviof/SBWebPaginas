/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFManagedBeans.declarados.util;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWP_JSFTools;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import org.primefaces.PrimeFaces;

/**
 *
 * @author sfurbino
 */
@RequestScoped
@Named
public class PgUtilAjax implements Serializable {

    private String idarea;

    public String getIdarea() {
        return idarea;
    }

    public void setIdarea(String idarea) {
        this.idarea = idarea;
    }

    public void atualizarArea() {
        try {
            UtilSBWP_JSFTools.atualizaPorId(idarea);
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, idarea, t);
        }
    }

    public void enviarMensagemArea() {

    }

}
