/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFManagedBeans.declarados.util;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCDataHora;
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

    public String getMesAtualExtenso() {
        try {
            Date pDiaDaSemana = new Date();
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

    public String getMesAnoExtenso(Date pDiaDaSemana) {
        try {
            if (pDiaDaSemana == null) {
                return null;
            }
            Locale local = new Locale("pt", "BR");

            SimpleDateFormat formatador = new SimpleDateFormat("MMMM yy", local);
            return formatador.format(pDiaDaSemana);
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro formantando data", t);
            return null;
        }
    }

    public String getHorarioSimples(Date pDiaDaSemana) {
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

    public String getAnoResumido(Date pDiaDaSemana) {
        try {
            if (pDiaDaSemana == null) {
                return null;
            }
            Locale local = new Locale("pt", "BR");

            SimpleDateFormat formatador = new SimpleDateFormat("yy", local);
            return formatador.format(pDiaDaSemana);
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro formantando data", t);
            return null;
        }
    }

    public String getDataHoraRelativa(Date pData) {
        Date hoje = new Date();
        String texto = "Sem data definida";

        SimpleDateFormat formatacaoHorario = new SimpleDateFormat("HH:mm");
        String horario = formatacaoHorario.format(pData);

        if (UtilCRCDataHora.isDiaIgual(hoje, pData)) {
            texto = "Hoje às " + horario;
            return texto;
        } else if (UtilCRCDataHora.isDiaIgual(pData, UtilCRCDataHora.incrementaDias(hoje, 1))) {
            texto = "Amanhã às " + horario;
            return texto;
        } else {
            long dias = UtilCRCDataHora.intervaloTempoDias(hoje, pData);
            String diaDaSemana = UtilCRCDataHora.getDiaDaSemana(pData);
            if (dias < 6) {
                texto = diaDaSemana + " às " + horario;
                return texto;
            } else {
                Locale local = new Locale("pt", "BR");
                SimpleDateFormat formatacaoFaraway = new SimpleDateFormat("dd'/'MM, EE", local);
                texto = formatacaoFaraway.format(pData) + ", às " + horario;
                return texto;
            }
        }
    }

}
