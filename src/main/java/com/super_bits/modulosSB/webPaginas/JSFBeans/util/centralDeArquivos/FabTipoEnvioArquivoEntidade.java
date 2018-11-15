/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.util.centralDeArquivos;

import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.FabFamiliaCompVisual;
import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.InfoComponenteVisual;
import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.ItfFabTipoComponenteVisual;

/**
 *
 * @author desenvolvedor
 */
public enum FabTipoEnvioArquivoEntidade implements ItfFabTipoComponenteVisual {
    @InfoComponenteVisual(classesCSS = "")
    ARQUIVO_DA_ENTIDADE,
    @InfoComponenteVisual(classesCSS = "")
    IMAGEM_REPRESENTANTE_ENTIDADE;

    @Override
    public FabFamiliaCompVisual getFamilia() {
        return FabFamiliaCompVisual.INPUT;
    }

}
