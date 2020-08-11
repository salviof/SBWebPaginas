/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.arquivosDoProjeto;

import com.super_bits.modulosSB.Persistencia.util.UtilSBPersistenciaArquivosDeEntidade;
import com.super_bits.modulosSB.SBCore.ConfigGeral.FabTipoEmpacotamento;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreOutputs;
import com.super_bits.modulosSB.SBCore.UtilGeral.stringSubstituicao.MapaSubstituicao;
import com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo.CentralDeArquivosAbstrata;
import com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo.FabTipoArquivoConhecido;
import com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo.TIPO_ESTRUTURA_LOCAL_XHTML_PADRAO;
import com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo.acessoArquivo.FabTipoAcessoArquivo;
import com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo.interfaces.ItfCentralPermissaoArquivo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimplesSomenteLeitura;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfSessao;
import com.super_bits.modulosSB.webPaginas.ConfigGeral.SBWebPaginas;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWPServletTools;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

/**
 *
 * @author salvioF
 */
@Deprecated
public class CentralDeArquivosWebAppPastaREsources extends CentralDeArquivosAbstrata {

    private final String PASTA_IMAGEM = "img";
    private final String PASTA_ARQUIVOS_ENTIDADE = "arquivos";

    private String endrLocaResource;

    private String endrRemotoResource;

    public CentralDeArquivosWebAppPastaREsources() {
        super(FabTipoEmpacotamento.SITE_WAR, TIPO_ESTRUTURA_LOCAL_XHTML_PADRAO.MODULO_PREFIXO_SLUG_DO_MB_DE_GESTAO);

    }

    public CentralDeArquivosWebAppPastaREsources(TIPO_ESTRUTURA_LOCAL_XHTML_PADRAO pTipo) {
        super(FabTipoEmpacotamento.SITE_WAR, pTipo);

    }

    @Override
    public String getEndrLocalResources() {
        if (endrLocaResource == null) {
            endrLocaResource = UtilSBWPServletTools.getCaminhoLocalServletsResource();
        }
        return endrLocaResource;

    }

    @Override
    public String getEndrRemotoResources() {

        try {
            if (endrRemotoResource == null) {
                if (SBCore.isEmModoDesenvolvimento()) {
                    endrRemotoResource = SBWebPaginas.getCaminhoWebAppDeveloper();
                } else {
                    endrRemotoResource = UtilSBWPServletTools.getCaminhoLocalServlet();
                }
            }
            return endrRemotoResource;

        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro definindo endereço remoto para REsource", t);
            return null;

        }
    }

    @Override
    public String getEndrRemotoResourcesObjeto() {
        return getEndrLocalResources() + "/" + PASTA_ARQUIVOS_ENTIDADE;
    }

    @Override
    public String getEndrLocalResourcesObjeto() {
        return getEndrLocalResources() + "/" + PASTA_ARQUIVOS_ENTIDADE;
    }

    @Override
    public String getEndrRemotoRecursosDoObjeto(Class entidade, FabTipoAcessoArquivo tipoAcesso, FabTipoArquivoConhecido pTipoArquivo) {
        return getEndrRemotoResourcesObjeto() + "/" + entidade.getSimpleName() + "/";
    }

    @Override
    public List<String> getEndrsLocaisDeCategoriasItem(ItfBeanSimplesSomenteLeitura item) {
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
    public List<String> getNomesPastasCategoriasItem(ItfBeanSimplesSomenteLeitura item) {
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
    public String getEndrLocalRecursosDoObjeto(Class entidade) {
        return getEndrLocalResourcesObjeto() + "/" + entidade.getSimpleName();
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
    public String getEndrRemotoImagem(ItfBeanSimplesSomenteLeitura item, FabTipoAtributoObjeto tipo) {
        switch (tipo) {

            case IMG_PEQUENA:
                break;
            case IMG_MEDIA:
                break;
            case IMG_GRANDE:
                return UtilSBPersistenciaArquivosDeEntidade.getURLImagem(item, tipo);
            default:
            //   return getEndrRemotoRecursosDoObjeto(item.getClass()) + "/" + tipo.toString() + ".jpg";

        }
//        return getEndrRemotoRecursosDoObjeto(item.getClass()) + "/" + tipo.toString() + ".jpg";
        return UtilSBPersistenciaArquivosDeEntidade.getURLImagem(item, tipo);
    }

    @Override
    public String getEndrRemotoRecursosItem(ItfBeanSimples item, String galeria, FabTipoAcessoArquivo pTipoAcesso, FabTipoArquivoConhecido pTipoArquivo) {
        if (galeria == null) {
            return getEndrRemotoRecursosDoObjeto(item.getClass(), pTipoAcesso, pTipoArquivo);
        } else {
            return getEndrRemotoRecursosDoObjeto(item.getClass(), pTipoAcesso, pTipoArquivo) + "/" + galeria;
        }
    }

    @Override
    public List<String> getEnterecosLocaisRecursosItem(ItfBeanSimples item, String galeria) {
        List<String> recursosDoItem = new ArrayList<>();
        getEndrLocalRecursosDoObjeto(item.getClass(), galeria);

        return recursosDoItem;
    }

    @Override
    public List<String> getEnterecosRemotosRecursosItem(ItfBeanSimplesSomenteLeitura item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getEndrLocalArquivoItem(ItfBeanSimplesSomenteLeitura pItem, String nomeArquivo) {
        return getEndrLocalArquivoItem(pItem, nomeArquivo, null);
    }

    @Override
    public String getEndrLocalArquivoItem(ItfBeanSimplesSomenteLeitura pItem, String nomeArquivo, String categoria) {
        if (categoria == null) {
            return getEndrLocalRecursosDoObjeto(pItem.getClass()) + "/" + pItem.getId() + "/" + nomeArquivo;
        } else {
            return getEndrLocalRecursosDoObjeto(pItem.getClass()) + "/" + pItem.getId() + "/" + categoria + "/" + nomeArquivo;
        }

    }

    @Override
    public String getEndrRemotoArquivoItem(ItfBeanSimplesSomenteLeitura pItem, String nomeArquivo, FabTipoAcessoArquivo pTipoAcesso, FabTipoArquivoConhecido pTipoArquivo) {
        return getEndrRemotoRecursosDoObjeto(pItem.getClass(), pTipoAcesso, pTipoArquivo) + "/" + nomeArquivo;

    }

    @Override
    public String getEndrRemotoArquivoItem(ItfBeanSimplesSomenteLeitura pItem, String nomeArquivo, String categoria, FabTipoAcessoArquivo pTipoAcesso, FabTipoArquivoConhecido pTipoArquivo) {
        return getEndrRemotoRecursosDoObjeto(pItem.getClass(), pTipoAcesso, pTipoArquivo) + "/ " + categoria + "/" + nomeArquivo;
    }

    @Override
    public boolean salvarArquivo(ItfBeanSimplesSomenteLeitura entidade, byte[] arqivo, String nome, String categoria) {
        String caminhoArquivo = getEndrLocalArquivoItem(entidade, nome, categoria);
        return UtilSBCoreOutputs.salvarArquivoByte(arqivo, caminhoArquivo);

    }

    @Override
    public boolean salvarImagemTodosOsFormatos(ItfBeanSimplesSomenteLeitura entidade, InputStream foto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean salvarImagemTamanhoMedio(ItfBeanSimplesSomenteLeitura entidade, InputStream foto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean salvarImagemTamanhoPequeno(ItfBeanSimplesSomenteLeitura entidade, InputStream foto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean salvarImagemTamanhoGrande(ItfBeanSimplesSomenteLeitura entidade, InputStream foto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean salvarArquivo(ItfBeanSimplesSomenteLeitura entidade, byte[] arquivo, String nomeCampo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean salvarArquivo(ItfCampoInstanciado pCampo, byte[] arquivo, String pNomeArquivo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setCentralDePermissao(ItfCentralPermissaoArquivo pPermissao) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ItfCentralPermissaoArquivo getCentralPermissao() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getEndrLocalArquivoCampoInstanciado(ItfCampoInstanciado pCampo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getEndrLocalTemporarioArquivoCampoInstanciado(ItfCampoInstanciado pCampo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getEndrRemotoArquivoCampoInstanciadoBaixar(ItfCampoInstanciado pCampo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean baixarArquivo(ItfBeanSimplesSomenteLeitura entidade, InputStream arqivo, String pNomeCampoOuCategoria, String pNomeArquivo, MapaSubstituicao mapaSubistituicao) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getEndrRemotoArquivoCampoInstanciadoAbrir(ItfCampoInstanciado pCampo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getEndrLocalArquivoTemporario(String pCategoria, String pEntidade, String nomeArquivo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getEndrRemotoBaixarArquivoPastaTemporaria(String pNomeArquivo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getEndrRemotoAbrirArquivoPastaTemporaria(String pNomeArquivo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getEndrLocalImagem(ItfBeanSimplesSomenteLeitura item, FabTipoAtributoObjeto tipo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getEntrLocalArquivosFormulario() {

        throw new UnsupportedOperationException("Ainda não implementado."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getEndrLocalImagem(ItfBeanSimplesSomenteLeitura item, FabTipoAtributoObjeto tipo, ItfSessao pSessao) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

}
