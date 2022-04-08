package com.super_bits.modulosSB.webPaginas.TratamentoDeErros;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.webPaginas.ConfigGeral.SBWebPaginas;
import com.super_bits.modulosSB.webPaginas.controller.servletes.tratamentoErro.ErroSBCriticoWeb;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;

@Deprecated
public class ErroSBCriticoWebContextoJsf extends ErroSBCriticoWeb {

    public ErroSBCriticoWebContextoJsf(String pMsg) {

        super(pMsg);

        if (!SBCore.isEmModoDesenvolvimento()) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect(SBWebPaginas.getSiteURL() + "/resources/SBComp/SBSystemPages/erroCriticoDeSistema.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(ErroSBCriticoWebContextoJsf.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            throw new UnsupportedOperationException("Ouve Lançamento de erro crítico para usuário:" + pMsg);
        }
    }

}
