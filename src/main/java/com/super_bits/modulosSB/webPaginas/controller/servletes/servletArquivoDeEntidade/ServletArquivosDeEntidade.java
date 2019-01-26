/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.controller.servletes.servletArquivoDeEntidade;

import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo.TipoRecurso;
import com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo.acessoArquivo.FabTipoAcessoArquivo;
import com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo.acessoArquivo.TipoAcessoArquivo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.ItensGenericos.basico.UsuarioAnonimo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.ItensGenericos.basico.UsuarioSistemaRoot;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.ItfDadoDinamico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.MapaObjetosProjetoAtual;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.ItemSimplesOffilineApartirDeSlugDeObjeto;
import com.super_bits.modulosSB.webPaginas.ConfigGeral.SBWebPaginas;
import com.super_bits.modulosSB.webPaginas.controller.servletes.ServletArquivosSBWPGenerico;
import com.super_bits.modulosSB.webPaginas.controller.servletes.urls.UrlInterpretada;
import com.super_bits.modulosSB.webPaginas.controller.servletes.urls.UtilFabUrlServlet;
import com.super_bits.modulosSB.webPaginas.controller.sessao.QlSessaoFacesContext;
import com.super_bits.modulosSB.webPaginas.controller.sessao.SessaoAtualSBWP;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWP_JSFTools;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import javax.inject.Inject;
import javax.persistence.Entity;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

/**
 *
 * @author desenvolvedor
 */
public class ServletArquivosDeEntidade extends ServletArquivosSBWPGenerico implements Serializable {

    public static final String NOME_URL_SERVLET = "arquivos";
    public static final String URL_SERVLET = SBWebPaginas.getSiteURL() + "/" + NOME_URL_SERVLET;

    @Inject
    @QlSessaoFacesContext
    public SessaoAtualSBWP sessaoAtual;

    @Override
    protected void doGet(HttpServletRequest requisicao, HttpServletResponse resp) throws ServletException, IOException {

        UrlInterpretada parametrosDeUrl;
        try {

            parametrosDeUrl = UtilFabUrlServlet.getUrlInterpretada(FabUrlArquivoDeEntidade.class, requisicao);

        } catch (Throwable t) {
            RequestDispatcher wp = requisicao.getRequestDispatcher(UtilSBWP_JSFTools.FORMULARIO_PARAMETRO_URL_INVALIDO);
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro obtendo parametros de URL", t);
            wp.forward(requisicao, resp);
            return;
        }

        String pNomeEntidade = parametrosDeUrl.getValorComoString(FabUrlArquivoDeEntidade.ENTIDADE);
        String pCategoria = parametrosDeUrl.getValorComoString(FabUrlArquivoDeEntidade.CATEGORIA);
        String pSlugObjeto = parametrosDeUrl.getValorComoString(FabUrlArquivoDeEntidade.NOME_E_CODIGO_ENTIDADE);
        TipoRecurso pTipoRecurso = (TipoRecurso) parametrosDeUrl.getValorComoBeanSimples(FabUrlArquivoDeEntidade.TIPO_ARQUIVO);
        TipoAcessoArquivo pTipoAcesso = (TipoAcessoArquivo) parametrosDeUrl.getValorComoBeanSimples(FabUrlArquivoDeEntidade.TIPO_ACESSO);
        String pNomeArquivo = parametrosDeUrl.getValorComoString(FabUrlArquivoDeEntidade.NOME_DO_ARQUIVO);

        ItemSimplesOffilineApartirDeSlugDeObjeto itemEnviado = new ItemSimplesOffilineApartirDeSlugDeObjeto(pSlugObjeto);

        String caminhoLocal = null;
        String nomeArquivoDownload = null;

        if (itemEnviado.getId() == 0) {
            ItfBeanSimples beanNovoItemTemporario = null;
            switch (pTipoRecurso.getFabipoArquivo()) {
                case IMAGE_REPRESENTATIVA_ENTIDADE_PEQUENO:
                case IMAGE_REPRESENTATIVA_ENTIDADE_MEDIO:
                case IMAGE_REPRESENTATIVA_ENTIDADE_GRANDE:
                default:
                    try {
                        beanNovoItemTemporario = (ItfBeanSimples) MapaObjetosProjetoAtual.getClasseDoObjetoByNome(pNomeEntidade).newInstance();
                    } catch (Throwable t) {
                        SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro criando nova instancia de entidade para obtenção de arquivo temporario", t);
                    }
            }
            switch (pTipoRecurso.getFabipoArquivo()) {

                case IMAGE_REPRESENTATIVA_ENTIDADE_PEQUENO:
                    caminhoLocal = SBCore.getCentralDeArquivos().getEndrLocalImagem(beanNovoItemTemporario, FabTipoAtributoObjeto.IMG_PEQUENA, sessaoAtual);
                    nomeArquivoDownload = FabTipoAtributoObjeto.IMG_PEQUENA.toString() + ".jpg";
                    break;
                case IMAGE_REPRESENTATIVA_ENTIDADE_MEDIO:
                    caminhoLocal = SBCore.getCentralDeArquivos().getEndrLocalImagem(beanNovoItemTemporario, FabTipoAtributoObjeto.IMG_MEDIA, sessaoAtual);
                    nomeArquivoDownload = FabTipoAtributoObjeto.IMG_MEDIA.toString() + ".jpg";
                    break;
                case IMAGE_REPRESENTATIVA_ENTIDADE_GRANDE:
                    caminhoLocal = SBCore.getCentralDeArquivos().getEndrLocalImagem(beanNovoItemTemporario, FabTipoAtributoObjeto.IMG_GRANDE, sessaoAtual);
                    nomeArquivoDownload = FabTipoAtributoObjeto.IMG_GRANDE.toString() + ".jpg";
                    break;
                default:
                    caminhoLocal = SBCore.getCentralDeArquivos().getEndrLocalArquivoTemporario(pCategoria, pNomeEntidade, pNomeArquivo);
                    nomeArquivoDownload = pNomeArquivo;
            }

        } else {
            Class classeEntidade = MapaObjetosProjetoAtual.getClasseDoObjetoByNome(pNomeEntidade);

            try {
                if (classeEntidade == null) {

                    throw new UnsupportedOperationException("A classe de Entidade enviada não foi encontrada no projeto, nome da entidade" + pNomeEntidade);
                }
            } catch (Throwable t) {
                despachaParametrosNaoDefinidos(requisicao, resp, UtilFabUrlServlet.getListaStringsParametroURL(requisicao));
                return;
            }
            ItfBeanSimples entidade = null;
            if (classeEntidade.getAnnotation(Entity.class) == null) {
                if (classeEntidade.getSimpleName().equals(UsuarioAnonimo.class.getSimpleName())) {
                    entidade = new UsuarioAnonimo();
                }
                if (classeEntidade.getSimpleName().equals(UsuarioSistemaRoot.class.getSimpleName())) {
                    entidade = new UsuarioSistemaRoot();
                }
            } else {
                entidade = (ItfBeanSimples) UtilSBPersistencia.getRegistroByNomeSlug(classeEntidade, pSlugObjeto, UtilSBPersistencia.getNovoEM());
            }

            if (entidade == null) {
                throw new UnsupportedOperationException("A entidade é reconhecida, mas o registro não pôde ser localizado para" + pNomeEntidade + "-" + pSlugObjeto);
            }

            switch (pTipoRecurso.getFabipoArquivo()) {

                case IMAGE_REPRESENTATIVA_ENTIDADE_PEQUENO:
                    caminhoLocal = SBCore.getCentralDeArquivos().getEndrLocalImagem(entidade, FabTipoAtributoObjeto.IMG_PEQUENA, sessaoAtual);
                    nomeArquivoDownload = FabTipoAtributoObjeto.IMG_PEQUENA.toString() + ".jpg";
                    break;
                case IMAGE_REPRESENTATIVA_ENTIDADE_MEDIO:
                    caminhoLocal = SBCore.getCentralDeArquivos().getEndrLocalImagem(entidade, FabTipoAtributoObjeto.IMG_MEDIA, sessaoAtual);
                    nomeArquivoDownload = FabTipoAtributoObjeto.IMG_MEDIA.toString() + ".jpg";
                    break;
                case IMAGE_REPRESENTATIVA_ENTIDADE_GRANDE:
                    caminhoLocal = SBCore.getCentralDeArquivos().getEndrLocalImagem(entidade, FabTipoAtributoObjeto.IMG_GRANDE, sessaoAtual);
                    nomeArquivoDownload = FabTipoAtributoObjeto.IMG_GRANDE.toString() + ".jpg";
                    break;
                default:
                    ItfCampoInstanciado campoArquivo = null;
                    if (entidade instanceof ItfDadoDinamico) {
                        campoArquivo = ((ItfDadoDinamico) entidade).getCampoInstanciado();
                    } else {
                        campoArquivo = entidade.getCampoByNomeOuAnotacao(pCategoria);
                    }
                    if (!campoArquivo.getComoArquivoDeEntidade().isUsuarioLogadoTemPermissaoBaixarArquivo()) {
                        RequestDispatcher wp = requisicao.getRequestDispatcher(UtilSBWP_JSFTools.FORMULARIO_ACESSO_NEGADO);
                        wp.forward(requisicao, resp);
                        return;
                    }

                    caminhoLocal = SBCore.getCentralDeArquivos().getEndrLocalArquivoCampoInstanciado(campoArquivo);
                    nomeArquivoDownload = campoArquivo.getValor().toString();
            }

        }

        File arquivo = new File(caminhoLocal);
        if (!arquivo.exists()) {
            caminhoLocal = SBWebPaginas.getPastaBaseImagens() + "/imagempadrao.jpg";
            arquivo = new File(caminhoLocal);
        }
        FabTipoAcessoArquivo tipoAcesso = (FabTipoAcessoArquivo) pTipoAcesso.getFabricaObjeto();
        switch (tipoAcesso) {
            case VISUALIZAR:
                abrirArquivo(caminhoLocal, nomeArquivoDownload, requisicao, resp, pTipoRecurso.getFabipoArquivo());
                break;
            case BAIXAR:
                baixarArquivo(caminhoLocal, nomeArquivoDownload, requisicao, resp);
                break;
            default:
                throw new AssertionError(tipoAcesso.name());

        }

    }

}
