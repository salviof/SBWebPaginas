/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFManagedBeans.declarados.util;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

/**
 *
 * @author sfurbino
 */
@RequestScoped
@Named
public class PgUtilDataHoraFormato implements Serializable {

    public String getDiaDaSemana(Date pDiaDaSemana) {
        try {
            if (pDiaDaSemana == null) {
                return null;
            }
            Locale local = new Locale("pt", "BR");

            SimpleDateFormat formatador = new SimpleDateFormat("EEEE", local);
            return formatador.format(pDiaDaSemana);
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro formantando data", t);
            return null;
        }
    }

    public String getDiaDoMes(Date pDiaDaSemana) {
        try {
            if (pDiaDaSemana == null) {
                return null;
            }
            Locale local = new Locale("pt", "BR");

            SimpleDateFormat formatador = new SimpleDateFormat("dd", local);
            return formatador.format(pDiaDaSemana);
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro formantando data", t);
            return null;
        }
    }

    public String getDataHoraSimplificado(Date pDiaDaSemana) {
        try {
            if (pDiaDaSemana == null) {
                return null;
            }
            Locale local = new Locale("pt", "BR");

            SimpleDateFormat formatador = new SimpleDateFormat("EE, dd, 'de' MMMM 'às' HH:mm", local);
            return formatador.format(pDiaDaSemana);
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro formantando data", t);
            return null;
        }
    }

    public String getMesExtenso(Date pDiaDaSemana) {
        try {
            if (pDiaDaSemana == null) {
                return null;
            }
            Locale local = new Locale("pt", "BR");

            SimpleDateFormat formatador = new SimpleDateFormat("MMMM", local);
            return formatador.format(pDiaDaSemana);
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro formantando data", t);
            return null;
        }
    }

    public String getMesHorario(Date pDiaDaSemana) {
        try {
            if (pDiaDaSemana == null) {
                return null;
            }
            Locale local = new Locale("pt", "BR");

            SimpleDateFormat formatador = new SimpleDateFormat("HH:mm", local);
            return formatador.format(pDiaDaSemana);
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro formantando data", t);
            return null;
        }
    }

    public String getAno(Date pDiaDaSemana) {
        try {
            if (pDiaDaSemana == null) {
                return null;
            }
            Locale local = new Locale("pt", "BR");

            SimpleDateFormat formatador = new SimpleDateFormat("yyyy", local);
            return formatador.format(pDiaDaSemana);
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro formantando data", t);
            return null;
        }
    }

}
