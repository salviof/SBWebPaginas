/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.modal;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import org.primefaces.PrimeFaces;

/**
 *
 * @author SalvioF
 */
@Named
@ViewScoped
public class PgModalREspostaComunicacao extends PgModalRespostaAbstrato {

    @PostConstruct
    public void inicioModalRespostaComunicacao() {
        comunicacao = getPaginaVinculada().getComunincacaoAguardandoResposta();
    }

    @Override
    public void enviarRespostaEExecutarAcao() {

        try {

            if (getRespostaSelecionada() == null) {
                throw new UnsupportedOperationException("A resposta não foi configurada");
            }
            SBCore.getServicoComunicacao().responderComunicacao(getComunicacao().getCodigoSelo(), getRespostaSelecionada());

            PrimeFaces.current().dialog().closeDynamic(getRespostaSelecionada());
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro enviando resposta para pagina", t);
        }

    }

}
