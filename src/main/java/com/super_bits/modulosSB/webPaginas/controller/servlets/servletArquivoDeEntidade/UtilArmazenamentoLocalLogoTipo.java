/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.controller.servlets.servletArquivoDeEntidade;

import com.super_bits.modulosSB.SBCore.ConfigGeral.CarameloCode;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoEntidadeSimples;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoSessao;
import java.io.File;

/**
 *
 * @author salvio
 */
public class UtilArmazenamentoLocalLogoTipo {

    public static String getEnderecoLogoTipoDeEntidadeRepositorioWebAppLocal(FabTipoAtributoObjeto pTipo, ComoEntidadeSimples pEntidade, ComoSessao pSessao) {

        String caminhoLegado = CarameloCode.getServicoArquivosDeEntidade().getEndrLocalImagem(pEntidade, pTipo, pSessao);
        //CarameloCode.getServicoArquivosDeEntidade().getEndrLocalImagem(prDadosREquisicaoArquivoEntidade.getEntidade(), FabTipoAtributoObjeto.IMG_PEQUENA, sessaoAtual)
        File arquivoLegado = new File(caminhoLegado);
        if (arquivoLegado.exists()) {
            return caminhoLegado;
        }
        String diretorioBase;
        if (pEntidade.getId() == null || pEntidade.getId() == null) {
            diretorioBase = pSessao.getPastaTempDeSessao() + "/" + pEntidade.getClass().getSimpleName() + "/0/";
        } else {
            diretorioBase = CarameloCode.getServicoArquivosDeEntidade().getEndrLocalImagens() + "/" + pEntidade.getClass().getSimpleName() + "/" + pEntidade.getId() + "/";
        }
        ///home/superBits/desenvolvedor/configModuloTestes/HOMOLOGACAO/carameloCodeCRM/img/TipoDadoCrmLinkIntegracao/23/
        ////home/superBits/desenvolvedor/configModuloTestes/HOMOLOGACAO/carameloCodeCRM/arquivos/TipoDadoCrmLinkIntegracao/23/imagem
        ///
        diretorioBase = diretorioBase.replace("/img/", "/arquivos/");

        String arquivoModoNovo = diretorioBase + "imagem/" + pTipo.getNomeImagemPadrao();

        return arquivoModoNovo;
    }

}
