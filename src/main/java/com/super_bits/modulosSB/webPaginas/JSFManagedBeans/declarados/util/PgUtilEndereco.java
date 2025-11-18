/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFManagedBeans.declarados.util;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ComoTemEndereco;

/**
 *
 * @author salvio
 */
@Named
@RequestScoped
public class PgUtilEndereco {

    public void gerarEnderecoCasoNaoExista(ItfCampoInstanciado pCampo) {
        if (pCampo.getFabricaTipoAtributo().isCampoLocalizacao()) {
            if (pCampo.getComoCampoLocalizacao().getLocal() == null) {
                try {
                    if (pCampo.getObjetoDoAtributo() instanceof ComoTemEndereco) {
                        ((ComoTemEndereco) pCampo.getObjetoDoAtributo()).instanciarNovoEndereco();
                    }
                } catch (Throwable t) {
                    SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Falha instanciando ", t);
                }
            }
        }
    }

}
