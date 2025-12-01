/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.arquivosDoProjeto;

import com.super_bits.modulosSB.SBCore.ConfigGeral.FabTipoEmpacotamento;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCOutputs;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringFiltros;
import com.super_bits.modulosSB.SBCore.UtilGeral.stringSubstituicao.MapaSubstituicao;
import com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo.CentralDeArquivosAbstrata;
import com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo.CentralPermissaoArquivoTudoLiberado;
import com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo.FabTipoArquivoConhecido;
import com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo.TIPO_ESTRUTURA_LOCAL_XHTML_PADRAO;
import com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo.acessoArquivo.FabTipoAcessoArquivo;
import com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo.interfaces.ItfCentralDeArquivos;
import com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo.interfaces.ItfCentralPermissaoArquivo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ComoEntidadeSimples;
import com.super_bits.modulosSB.webPaginas.ConfigGeral.SBWebPaginas;
import com.super_bits.modulosSB.webPaginas.controller.servlets.servletArquivoDeEntidade.ServletArquivosDeEntidade;
import static com.super_bits.modulosSB.webPaginas.controller.servlets.servletArquivoDeEntidade.ServletArquivosDeEntidade.NOME_URL_SERVLET;
import com.super_bits.modulosSB.webPaginas.controller.servlets.servletArquivoDeSessao.ServletArquivoDeSessao;
import com.super_bits.modulosSB.webPaginas.controller.sessao.SessaoAtualSBWP;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWPServletTools;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.IOUtils;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ComoEntidadeSimplesSomenteLeitura;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ComoSessao;

/**
 *
 * @author salvioF
 */
public class CentralDeArquivosWebAppServidorSB extends CentralDeArquivosAbstrata {

    private final String PASTA_IMAGEM = "img";
    private final String PASTA_ARQUIVOS_ENTIDADE = "arquivos";
    private final String PASTA_ARQUIVOS_DE_ENTIDADE_DO_SERVIDOR = "/home/servidorSBFW/arquivosDeEntidade/" + SBCore.getGrupoProjeto();
    private final String PASTA_ARQUIVOS_DE_ENTIDADE_SERVIDOR_HOMOLOGACAO = "/home/superBits/desenvolvedor/configModuloTestes/HOMOLOGACAO/"
            + SBCore.getGrupoProjeto();
    private ItfCentralPermissaoArquivo centralPermissao;

    private String endrLocaResource;

    public CentralDeArquivosWebAppServidorSB() {
        super(FabTipoEmpacotamento.SITE_WAR,
                TIPO_ESTRUTURA_LOCAL_XHTML_PADRAO.MODULO_PREFIXO_SLUG_DO_MB_DE_GESTAO);
        //UtilSBPersistenciaArquivosDeEntidade.getEndrRemotoImagem(item, FabTipoAtributoObjeto.ID)
    }

    private String getUrlSErviletSEssao() {
        try {
            SessaoAtualSBWP sessaoAtual = UtilSBWPServletTools.getSessaoAtual(true);
            String urlBase;
            if (sessaoAtual != null) {
                urlBase = sessaoAtual.getUrlHostDaSessao();
            } else {
                urlBase = SBWebPaginas.getURLBase();
            }
            urlBase = urlBase + "/" + NOME_URL_SERVLET;
            return urlBase;
        } catch (Throwable t) {
            return ServletArquivosDeEntidade.URL_SERVLET;
        }
    }

    @Override
    public String getEntrLocalArquivosFormulario() {
        // if (endrLocaResource != null) {
        //      return endrLocaResource;
        //  }
        if (!SBCore.isEmModoDesenvolvimento()) {
            endrLocaResource = SBWebPaginas.getCaminhoRealJavaWebAppContexto();
        } else {
            endrLocaResource = SBCore.getCaminhoGrupoProjetoSource() + "/" + SBCore.getNomeProjeto() + "/src/main/webapp";
        }
        return endrLocaResource;

    }

    @Override
    public String getEndrLocalArquivoCampoInstanciado(ItfCampoInstanciado pCampo) {

        String caminhoArquivo = getEndrLocalArquivoItem((ComoEntidadeSimplesSomenteLeitura) pCampo.getObjetoDoAtributo(), (String) pCampo.getValor(), ItfCentralDeArquivos.CATEGORIA_PADRAO_ARQUIVO_DE_REGISTRO);
        // todo resolver caminho legado de outra forma, sem precisar verficar a existencia do arquivo no caminho legado
        if (!new File(caminhoArquivo).exists()) {
            caminhoArquivo = getEndrLocalArquivoItem((ComoEntidadeSimplesSomenteLeitura) pCampo.getObjetoDoAtributo(), (String) pCampo.getValor(), pCampo.getNomeCamponaClasse());
        }
        return caminhoArquivo;
    }

    @Override
    public String getEndrLocalResources() {
        if (endrLocaResource == null) {
            if (SBCore.isEmModoProducao()) {
                endrLocaResource = PASTA_ARQUIVOS_DE_ENTIDADE_DO_SERVIDOR;


                ///home/servidorSBFW/arquivosDeEntidade
            } else {
                endrLocaResource = PASTA_ARQUIVOS_DE_ENTIDADE_SERVIDOR_HOMOLOGACAO;
            }

        }
        return endrLocaResource;

    }

    @Override
    public String getEndrRemotoResources() {

        try {

            return getUrlSErviletSEssao();

        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro definindo endereço remoto para REsource", t);
            return null;

        }
    }

    @Override
    public String getEndrRemotoResourcesObjeto() {

        return getUrlSErviletSEssao();
    }

    @Override
    public String getEndrRemotoRecursosDoObjeto(Class entidade, FabTipoAcessoArquivo pTipoAcesso, FabTipoArquivoConhecido pTipoConhecido) {
        return getEndrRemotoResourcesObjeto() + "/" + pTipoConhecido.getSlugUrl() + "/" + pTipoAcesso.getSlugUrl() + "/" + entidade.getSimpleName() + "/";
    }

    @Override
    public List<String> getEndrsLocaisDeCategoriasItem(ComoEntidadeSimplesSomenteLeitura item) {
        List<String> pastasCategoria = new ArrayList<>();
        File pastaItem = new File(getEndrLocalRecursosDoObjeto(item.getClass()));

        for (File arquivoDaPasta : pastaItem.listFiles()) {
            if (arquivoDaPasta.isDirectory()) {
                pastasCategoria.add(arquivoDaPasta.getAbsolutePath());
            }
        }

        return pastasCategoria;

    }

    @Override
    public List<String> getNomesPastasCategoriasItem(ComoEntidadeSimplesSomenteLeitura item) {
        List<String> pastasCategoria = new ArrayList<>();
        try {

            File pastaItem = new File(getEndrLocalRecursosDoObjeto(item.getClass()));
            if (pastaItem.exists()) {
                for (File arquivoDaPasta : pastaItem.listFiles()) {
                    if (arquivoDaPasta.isDirectory()) {
                        pastasCategoria.add(arquivoDaPasta.getName());
                    }
                }
            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro obtendo nomes de categorias do item" + item, t);
        }

        return pastasCategoria;
    }

    @Override
    public String getEndrLocalImagens() {
        return getEndrLocalResources() + "/" + getNomeLocalPastaImagem();
    }

    @Override
    public String getEndrRemotoImagens() {

        return getEndrRemotoResources() + "/" + getNomeRemotoPastaImagem();

    }

    @Override
    public String getNomeRemotoPastaImagem() {
        return PASTA_IMAGEM;
    }

    @Override
    public String getNomeLocalPastaImagem() {
        return PASTA_IMAGEM;
    }

    @Override
    public String getEndrLocalRecursosDoObjeto(Class entidade, String pGaleria) {

        if (pGaleria == null) {
            return getEndrLocalRecursosDoObjeto(entidade);
        } else {
            return getEndrLocalRecursosDoObjeto(entidade) + "/" + pGaleria;
        }

    }

    @Override
    public String getEndrRemotoIMGPadraoPorTipoCampo(FabTipoAtributoObjeto tipo) {
        return getEndrRemotoResourcesObjeto() + "/" + tipo.toString() + ".jpg";
    }

    @Override
    public String getEntdrRemotoIMGPadraoPorTipoClasse(Class entidade) {
        return getEndrRemotoRecursosDoObjeto(entidade, FabTipoAcessoArquivo.VISUALIZAR, FabTipoArquivoConhecido.IMAGE_REPRESENTATIVA_ENTIDADE_PEQUENO) + "/";
    }

    @Override
    public String getEndrRemotoImagem(ComoEntidadeSimplesSomenteLeitura item, FabTipoAtributoObjeto tipo) {

        String arquivoLocal = getEndrLocalImagem(item, tipo);
        String caminhoFinal = null;

        caminhoFinal = tipo.toString() + "/" + UtilCRCStringFiltros.gerarUrlAmigavel(item.getNome()) + "-" + item.getId() + "/" + tipo.toString() + ".jpg";

        switch (tipo) {

            case IMG_PEQUENA:
                return getEndrRemotoRecursosDoObjeto(item.getClass(), FabTipoAcessoArquivo.VISUALIZAR, FabTipoArquivoConhecido.IMAGE_REPRESENTATIVA_ENTIDADE_PEQUENO) + caminhoFinal;
            case IMG_MEDIA:
                return getEndrRemotoRecursosDoObjeto(item.getClass(), FabTipoAcessoArquivo.VISUALIZAR, FabTipoArquivoConhecido.IMAGE_REPRESENTATIVA_ENTIDADE_MEDIO) + caminhoFinal;
            case IMG_GRANDE:
                return getEndrRemotoRecursosDoObjeto(item.getClass(), FabTipoAcessoArquivo.VISUALIZAR, FabTipoArquivoConhecido.IMAGE_REPRESENTATIVA_ENTIDADE_GRANDE) + caminhoFinal;
            default:
                return getEndrRemotoRecursosDoObjeto(item.getClass(), FabTipoAcessoArquivo.VISUALIZAR, FabTipoArquivoConhecido.IMAGE_REPRESENTATIVA_ENTIDADE_PEQUENO) + caminhoFinal;
        }

    }

    @Override
    public String getEndrRemotoRecursosItem(ComoEntidadeSimples item, String categoria, FabTipoAcessoArquivo pTipoAcesso, FabTipoArquivoConhecido pTipoArquivo) {
        if (categoria == null) {
            return getEndrRemotoRecursosDoObjeto(item.getClass(), pTipoAcesso, pTipoArquivo) + item.getSlugIdentificador() + "/";
        } else {
            return getEndrRemotoRecursosDoObjeto(item.getClass(), pTipoAcesso, pTipoArquivo) + categoria + "/" + item.getSlugIdentificador() + "/";
        }
    }

    @Override
    @Deprecated
    public List<String> getEnterecosLocaisRecursosItem(ComoEntidadeSimples item, String galeria) {
        List<String> recursosDoItem = new ArrayList<>();
        getEndrLocalRecursosDoObjeto(item.getClass(), galeria);

        return recursosDoItem;
    }

    @Override
    @Deprecated
    public List<String> getEnterecosRemotosRecursosItem(ComoEntidadeSimplesSomenteLeitura item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean salvarImagemTodosOsFormatos(ComoEntidadeSimplesSomenteLeitura entidade, InputStream foto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private boolean salvarImagemPadraoDeEntidade(ComoEntidadeSimplesSomenteLeitura pEntidade, FabTipoAtributoObjeto pTipo, InputStream pFoto) {

        String caminhoArquivo = "caminhoNaoDefinido";
        try {
            caminhoArquivo = getEndrLocalImagem(pEntidade, pTipo);

            return UtilCRCOutputs.salvarArquivoByte(IOUtils.toByteArray(pFoto), caminhoArquivo);

        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro salvando arquivo de imagem de " + pEntidade + " em" + caminhoArquivo, t);
            return false;
        }
    }

    @Override
    public boolean salvarImagemTamanhoMedio(ComoEntidadeSimplesSomenteLeitura entidade, InputStream foto) {
        return salvarImagemPadraoDeEntidade(entidade, FabTipoAtributoObjeto.IMG_MEDIA, foto);
    }

    @Override
    public boolean salvarImagemTamanhoPequeno(ComoEntidadeSimplesSomenteLeitura entidade, InputStream foto) {
        return salvarImagemPadraoDeEntidade(entidade, FabTipoAtributoObjeto.IMG_PEQUENA, foto);
    }

    @Override
    public boolean salvarImagemTamanhoGrande(ComoEntidadeSimplesSomenteLeitura entidade, InputStream foto) {
        return salvarImagemPadraoDeEntidade(entidade, FabTipoAtributoObjeto.IMG_GRANDE, foto);
    }

    @Override
    public boolean salvarArquivo(ComoEntidadeSimplesSomenteLeitura entidade, byte[] arquivo, String nomeCampo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }

    @Override
    @Deprecated
    public boolean baixarArquivo(ComoEntidadeSimplesSomenteLeitura entidade, InputStream arqivo, String pNomeCampoOuCategoria, String pNomeArquivo, MapaSubstituicao mapaSubistituicao) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getEndrLocalImagem(ComoEntidadeSimplesSomenteLeitura item, FabTipoAtributoObjeto tipo, ComoSessao pSessao) {
        String diretorioBase = "ERRO";
        if (item.getId() == null || item.getId() == null) {
            diretorioBase = pSessao.getPastaTempDeSessao() + "/" + item.getClass().getSimpleName() + "/0/";
        } else {
            diretorioBase = getEndrLocalImagens() + "/" + item.getClass().getSimpleName() + "/" + item.getId() + "/";
        }
        switch (tipo) {

            case IMG_PEQUENA:
                return diretorioBase + "imagemLogoPequeno.jpg";

            case IMG_MEDIA:
                return diretorioBase + "imagemLogoMedio.jpg";
            case IMG_GRANDE:
                return diretorioBase + "imagemLogoGrande.jpg";
            default:
                return diretorioBase + "imagemLogoPequeno.jpg";

        }
    }

    @Override
    public String getEndrLocalImagem(ComoEntidadeSimplesSomenteLeitura item, FabTipoAtributoObjeto tipo) {
        if (item.getId() == null && item.getId() == null) {
            return getEndrLocalImagem(item, tipo, SBCore.getControleDeSessao().getSessaoAtual());
        } else {
            return getEndrLocalImagem(item, tipo, null);
        }

    }

    @Override
    public void setCentralDePermissao(ItfCentralPermissaoArquivo pPermissao) {

        centralPermissao = pPermissao;

    }

    @Override
    public ItfCentralPermissaoArquivo getCentralPermissao() {
        if (centralPermissao == null) {
            return new CentralPermissaoArquivoTudoLiberado();
        }
        return centralPermissao;
    }

    @Override
    public String getEndrLocalTemporarioArquivoCampoInstanciado(ItfCampoInstanciado pCampo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getEndrLocalResourcesObjeto() {
        return getEndrLocalResources() + "/" + PASTA_ARQUIVOS_ENTIDADE;
    }

    private String getEndrRemotoServletPastaTemporaria() {
        return ServletArquivoDeSessao.URL_SERVLET;
    }

    @Override
    public String getEndrRemotoBaixarArquivoPastaTemporaria(String pNomeArquivo) {
        FabTipoArquivoConhecido pTipoArquivo = FabTipoArquivoConhecido.getTipoArquivoByNomeArquivo(pNomeArquivo);

        return getEndrRemotoServletPastaTemporaria() + "/" + pTipoArquivo.getSlugUrl() + "/" + FabTipoAcessoArquivo.BAIXAR.getSlugUrl() + "/" + pNomeArquivo;
    }

    @Override
    public String getEndrRemotoAbrirArquivoPastaTemporaria(String pNomeArquivo) {
        FabTipoArquivoConhecido pTipoArquivo = FabTipoArquivoConhecido.getTipoArquivoByNomeArquivo(pNomeArquivo);
        return getEndrRemotoServletPastaTemporaria() + "/" + pTipoArquivo.getSlugUrl() + "/" + FabTipoAcessoArquivo.VISUALIZAR.getSlugUrl() + "/" + pNomeArquivo;
    }

    @Override
    public boolean isExisteArquivo(ItfCampoInstanciado pCampo) {
        return super.isExisteArquivo(pCampo); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean excluirArquivo(ItfCampoInstanciado pCampo) {
        try {
            String arquivo = SBCore.getCentralDeArquivos().getEndrLocalArquivoCampoInstanciado(pCampo);

            File novoFile = new File(arquivo);
            novoFile.delete();
            pCampo.setValor(null);
            return true;
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Falha excluindo" + pCampo, t);
            return false;
        }
    }

    @Override
    public String getHashArquivoDeEntidadeRegistrado(ItfCampoInstanciado ici) {
        return null;
    }

}
