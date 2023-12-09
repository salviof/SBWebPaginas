/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFManagedBeans.declarados.util;

import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreAnotacoesDinamicas;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringFiltros;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author sfurbino
 */
@RequestScoped
@Named
public class PgUtilTelefone implements Serializable {

    public String gerarNumeroInternacional(String pNumero) {
        if (pNumero == null) {
            return null;
        }
        String numeroSemCaracteresEspeciais = UtilSBCoreStringFiltros.removeCaracteresEspeciaisEspacosETracos(pNumero);
        if (numeroSemCaracteresEspeciais.startsWith("55")) {
            return "+" + numeroSemCaracteresEspeciais;
        }
        if (numeroSemCaracteresEspeciais.startsWith("+55")) {
            return numeroSemCaracteresEspeciais;
        }
        if (numeroSemCaracteresEspeciais.length() < 5) {
            return null;
        }
        if (numeroSemCaracteresEspeciais.length() < 10) {
            return "+5531" + numeroSemCaracteresEspeciais;
        }
        return "+55" + numeroSemCaracteresEspeciais;

    }

    public String gerarJavascriptWhatsqpp(String pNumero, String texto) {
        try {
            String textoCodificado = URLEncoder.encode(texto, StandardCharsets.UTF_8.toString());
            StringBuilder comandoJS = new StringBuilder();
            comandoJS.append("window.open('https://web.whatsapp.com/send?phone=");
            comandoJS.append(gerarNumeroInternacional(pNumero).replace("+", ""));
            comandoJS.append("&text=");
            comandoJS.append(textoCodificado);
            comandoJS.append("')");
            return comandoJS.toString();
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(PgUtilTelefone.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        }

    }

    public String gerarUrlWhasapp(String pNumero, String texto) {
        try {
            String textoCodificado = URLEncoder.encode(texto, StandardCharsets.UTF_8.toString());
            StringBuilder comandoJS = new StringBuilder();
            comandoJS.append("https://api.whatsapp.com/send?phone=");
            comandoJS.append(gerarNumeroInternacional(pNumero).replace("+", ""));
            comandoJS.append("&text=");
            comandoJS.append(textoCodificado);

            return comandoJS.toString();
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(PgUtilTelefone.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        }
    }

}
