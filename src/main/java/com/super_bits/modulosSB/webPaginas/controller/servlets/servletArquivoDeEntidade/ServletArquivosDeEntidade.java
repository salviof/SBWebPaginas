/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.controller.servlets.servletArquivoDeEntidade;

import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringValidador;
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
import com.super_bits.modulosSB.webPaginas.controller.servlets.ServletArquivosSBWPGenerico;
import com.super_bits.modulosSB.webPaginas.controller.servletes.util.UtilFabUrlServlet;
import com.super_bits.modulosSB.webPaginas.controller.servlets.ErroRequisicaoServlet;
import com.super_bits.modulosSB.webPaginas.controller.servlets.FabTipoErroRequisicao;

import com.super_bits.modulosSB.SBCore.modulos.Controller.qualificadoresCDI.sessao.QlSessaoFacesContext;
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

    public DadosRequisicaoArquivoDeEntidade buildParametrosDeUrl(HttpServletRequest requisicao) throws ErroRequisicaoServlet {
        DadosRequisicaoArquivoDeEntidade prDadosREquisicaoArquivoEntidade = new DadosRequisicaoArquivoDeEntidade();
        try {

            prDadosREquisicaoArquivoEntidade.setParametrosDeUrl(UtilFabUrlServlet.getUrlInterpretada(FabUrlArquivoDeEntidade.class, requisicao));

        } catch (Throwable t) {

            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro obtendo parametros de URL", t);
            throw new ErroRequisicaoServlet(FabTipoErroRequisicao.PARAMETROS_INVALIDOS, "Erro obtendo parametros de URL");

        }
        String pNomeEntidade = prDadosREquisicaoArquivoEntidade.getParametrosDeUrl().getValorComoString(FabUrlArquivoDeEntidade.ENTIDADE);
        String pCategoria = prDadosREquisicaoArquivoEntidade.getParametrosDeUrl().getValorComoString(FabUrlArquivoDeEntidade.CATEGORIA);
        String pSlugObjeto = prDadosREquisicaoArquivoEntidade.getParametrosDeUrl().getValorComoString(FabUrlArquivoDeEntidade.NOME_E_CODIGO_ENTIDADE);
        prDadosREquisicaoArquivoEntidade.setTipoRecurso((TipoRecurso) prDadosREquisicaoArquivoEntidade.getParametrosDeUrl().getValorComoBeanSimples(FabUrlArquivoDeEntidade.TIPO_ARQUIVO));
        prDadosREquisicaoArquivoEntidade.setTipoAcesso((TipoAcessoArquivo) prDadosREquisicaoArquivoEntidade.getParametrosDeUrl().getValorComoBeanSimples(FabUrlArquivoDeEntidade.TIPO_ACESSO));
        prDadosREquisicaoArquivoEntidade.setNomeArquivoDownload(prDadosREquisicaoArquivoEntidade.getParametrosDeUrl().getValorComoString(FabUrlArquivoDeEntidade.NOME_DO_ARQUIVO));

        ItemSimplesOffilineApartirDeSlugDeObjeto itemEnviado = new ItemSimplesOffilineApartirDeSlugDeObjeto(pSlugObjeto);

        if (itemEnviado.getId() == 0) {
            ItfBeanSimples beanNovoItemTemporario = null;
            switch (prDadosREquisicaoArquivoEntidade.getTipoRecurso().getFabipoArquivo()) {
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
            switch (prDadosREquisicaoArquivoEntidade.getTipoRecurso().getFabipoArquivo()) {

                case IMAGE_REPRESENTATIVA_ENTIDADE_PEQUENO:
                    prDadosREquisicaoArquivoEntidade.setCaminhoLocal(SBCore.getCentralDeArquivos().getEndrLocalImagem(beanNovoItemTemporario, FabTipoAtributoObjeto.IMG_PEQUENA, sessaoAtual));
                    prDadosREquisicaoArquivoEntidade.setNomeArquivoDownload(FabTipoAtributoObjeto.IMG_PEQUENA.toString() + ".jpg");

                    break;
                case IMAGE_REPRESENTATIVA_ENTIDADE_MEDIO:

                    prDadosREquisicaoArquivoEntidade.setCaminhoLocal(SBCore.getCentralDeArquivos().getEndrLocalImagem(beanNovoItemTemporario, FabTipoAtributoObjeto.IMG_MEDIA, sessaoAtual));
                    prDadosREquisicaoArquivoEntidade.setNomeArquivoDownload(FabTipoAtributoObjeto.IMG_MEDIA.toString() + ".jpg");
                    break;
                case IMAGE_REPRESENTATIVA_ENTIDADE_GRANDE:
                    prDadosREquisicaoArquivoEntidade.setCaminhoLocal(SBCore.getCentralDeArquivos().getEndrLocalImagem(beanNovoItemTemporario, FabTipoAtributoObjeto.IMG_GRANDE, sessaoAtual));
                    prDadosREquisicaoArquivoEntidade.setNomeArquivoDownload(FabTipoAtributoObjeto.IMG_GRANDE.toString() + ".jpg");
                    break;
                default:
                    prDadosREquisicaoArquivoEntidade.setCaminhoLocal(SBCore.getCentralDeArquivos().getEndrLocalArquivoTemporario(pCategoria, pNomeEntidade, prDadosREquisicaoArquivoEntidade.getNomeArquivoDownload()));
                    prDadosREquisicaoArquivoEntidade.setNomeArquivoDownload(prDadosREquisicaoArquivoEntidade.getNomeArquivoDownload());
            }

        } else {
            Class classeEntidade = MapaObjetosProjetoAtual.getClasseDoObjetoByNome(pNomeEntidade);

            try {
                if (classeEntidade == null) {

                    throw new UnsupportedOperationException("A classe de Entidade enviada não foi encontrada no projeto, nome da entidade" + pNomeEntidade);
                }
            } catch (Throwable t) {
                throw new ErroRequisicaoServlet(FabTipoErroRequisicao.PARAMETROS_INVALIDOS, "Parametro de entidade não reconhecido");

            }

            if (classeEntidade.getAnnotation(Entity.class) == null) {
                if (classeEntidade.getSimpleName().equals(UsuarioAnonimo.class.getSimpleName())) {

                    prDadosREquisicaoArquivoEntidade.setEntidade(new UsuarioAnonimo());
                }
                if (classeEntidade.getSimpleName().equals(UsuarioSistemaRoot.class.getSimpleName())) {
                    prDadosREquisicaoArquivoEntidade.setEntidade(new UsuarioSistemaRoot());
                }

            } else {
                prDadosREquisicaoArquivoEntidade.setEntidade((ItfBeanSimples) UtilSBPersistencia.getRegistroByNomeSlug(classeEntidade, pSlugObjeto, UtilSBPersistencia.getNovoEM()));
            }

            switch (prDadosREquisicaoArquivoEntidade.getTipoRecurso().getFabipoArquivo()) {
                case IMAGE_REPRESENTATIVA_ENTIDADE_GRANDE:
                case IMAGE_REPRESENTATIVA_ENTIDADE_MEDIO:
                case IMAGE_REPRESENTATIVA_ENTIDADE_PEQUENO:
                    try {
                    prDadosREquisicaoArquivoEntidade.setEntidade((ItfBeanSimples) classeEntidade.newInstance());
                    String[] partes = pSlugObjeto.split("-");
                    for (String parte : partes) {
                        if (UtilSBCoreStringValidador.isContemApenasNumero(parte)) {
                            prDadosREquisicaoArquivoEntidade.getEntidade().setId(Integer.valueOf(parte));
                        } else {
                            prDadosREquisicaoArquivoEntidade.getEntidade().setNome(parte);
                        }
                    }
                } catch (Throwable t) {
                    prDadosREquisicaoArquivoEntidade.setEntidade(null);
                }
                break;
            }

            if (prDadosREquisicaoArquivoEntidade.getEntidade() == null) {

                throw new UnsupportedOperationException("A entidade é reconhecida, mas o registro não pôde ser localizado para" + pNomeEntidade + "-" + pSlugObjeto);
            }

            switch (prDadosREquisicaoArquivoEntidade.getTipoRecurso().getFabipoArquivo()) {

                case IMAGE_REPRESENTATIVA_ENTIDADE_PEQUENO:
                    prDadosREquisicaoArquivoEntidade.setCaminhoLocal(SBCore.getCentralDeArquivos().getEndrLocalImagem(prDadosREquisicaoArquivoEntidade.getEntidade(), FabTipoAtributoObjeto.IMG_PEQUENA, sessaoAtual));
                    prDadosREquisicaoArquivoEntidade.setNomeArquivoDownload(FabTipoAtributoObjeto.IMG_PEQUENA.toString() + ".jpg");
                    break;
                case IMAGE_REPRESENTATIVA_ENTIDADE_MEDIO:
                    prDadosREquisicaoArquivoEntidade.setCaminhoLocal(SBCore.getCentralDeArquivos().getEndrLocalImagem(prDadosREquisicaoArquivoEntidade.getEntidade(), FabTipoAtributoObjeto.IMG_MEDIA, sessaoAtual));
                    prDadosREquisicaoArquivoEntidade.setNomeArquivoDownload(FabTipoAtributoObjeto.IMG_MEDIA.toString() + ".jpg");
                    break;
                case IMAGE_REPRESENTATIVA_ENTIDADE_GRANDE:
                    prDadosREquisicaoArquivoEntidade.setCaminhoLocal(SBCore.getCentralDeArquivos().getEndrLocalImagem(prDadosREquisicaoArquivoEntidade.getEntidade(), FabTipoAtributoObjeto.IMG_GRANDE, sessaoAtual));
                    prDadosREquisicaoArquivoEntidade.setNomeArquivoDownload(FabTipoAtributoObjeto.IMG_GRANDE.toString() + ".jpg");
                    break;
                default:
                    ItfCampoInstanciado campoArquivo = null;
                    if (prDadosREquisicaoArquivoEntidade.getEntidade() instanceof ItfDadoDinamico) {
                        campoArquivo = ((ItfDadoDinamico) prDadosREquisicaoArquivoEntidade.getEntidade()).getCampoInstanciado();
                    } else {
                        campoArquivo = prDadosREquisicaoArquivoEntidade.getEntidade().getCampoByNomeOuAnotacao(pCategoria);
                    }
                    if (!campoArquivo.getComoArquivoDeEntidade().isUsuarioLogadoTemPermissaoBaixarArquivo()) {

                        throw new ErroRequisicaoServlet(FabTipoErroRequisicao.ACESSO_NEGADO, "O usuário não tem acesso ao arquivo");

                    }

                    prDadosREquisicaoArquivoEntidade.setCaminhoLocal(SBCore.getCentralDeArquivos().getEndrLocalArquivoCampoInstanciado(campoArquivo));
                    prDadosREquisicaoArquivoEntidade.setNomeArquivoDownload(campoArquivo.getValor().toString());
            }

        }
        return prDadosREquisicaoArquivoEntidade;
    }

    @Override
    protected void doGet(HttpServletRequest requisicao, HttpServletResponse resp) throws ServletException, IOException {
        DadosRequisicaoArquivoDeEntidade dados;
        try {
            dados = buildParametrosDeUrl(requisicao);
        } catch (ErroRequisicaoServlet ex) {
            RequestDispatcher wp = null;

            switch (ex.getTipoErro()) {
                case ACESSO_NEGADO:
                    wp = requisicao.getRequestDispatcher(UtilSBWP_JSFTools.FORMULARIO_ACESSO_NEGADO);
                    wp.forward(requisicao, resp);
                    break;
                case PARAMETROS_INVALIDOS:
                    wp = requisicao.getRequestDispatcher(UtilSBWP_JSFTools.FORMULARIO_PARAMETRO_URL_INVALIDO);
                    wp.forward(requisicao, resp);
                    break;
                default:
                    throw new ServletException("Erro na requisição " + ex.getMessage());

            }
            throw new ServletException("Erro na requisição " + ex.getMessage());
        }

        if (!dados.getCaminhoLocal().startsWith("https")) {
            File arquivo = new File(dados.getCaminhoLocal());
            if (!arquivo.exists()) {
                dados.setCaminhoLocal(SBWebPaginas.getCaminhoRealJavaWebAppContexto() + "/resources/img/imagempadrao.jpg");
            }
        }

        FabTipoAcessoArquivo tipoAcesso = (FabTipoAcessoArquivo) dados.getTipoAcesso().getFabricaObjeto();
        switch (tipoAcesso) {
            case VISUALIZAR:
                abrirArquivo(dados.getCaminhoLocal(), dados.getNomeArquivoDownload(), requisicao, resp, dados.getTipoRecurso().getFabipoArquivo());
                break;
            case BAIXAR:
                baixarArquivo(dados.getCaminhoLocal(), dados.getNomeArquivoDownload(), requisicao, resp);
                break;
            default:
                throw new AssertionError(tipoAcesso.name());

        }

    }

}
