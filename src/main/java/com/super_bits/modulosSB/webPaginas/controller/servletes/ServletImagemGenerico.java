//Sistema desenvolvido sob encomenda para processamento de placas veiculares em tempo real pela Super Bits Sistemas sob encomnda da Sphera Secucurity
package com.super_bits.modulosSB.webPaginas.controller.servletes;

import com.super_bits.modulosSB.SBCore.UtilGeral.UTilSBCoreInputs;
import com.super_bits.modulosSB.webPaginas.ConfigGeral.SBWebPaginas;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWPServletTools;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;

/**
 *
 *
 *
 * @author Sálvio Furbino <salviof@gmail.com>
 * @since 29/05/2014
 *
 */
public abstract class ServletImagemGenerico extends HttpServlet implements Serializable {

    protected enum TIPOIMAGEM {

        IMAGEMCOMCOMENTATRIOS, IMAGEMAPENAS, TESTES
    }

    protected String getParametrosURL(HttpServletRequest requisicao) {

        String caminho = requisicao.getRequestURL().toString();
        String parametros = caminho.substring(SBWebPaginas.getSiteURL().length() + this.getClass().getSimpleName().length() + 1);
        return parametros;

    }

    protected abstract BufferedImage trataimagem(BufferedImage pImagemOriginal, HttpServletRequest pRequest);

    protected abstract String getCaminho(HttpServletRequest pRequest);

    protected abstract TIPOIMAGEM getTipoImagem();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        boolean caminhURL;
        String cm;
        URL urlImagem = null;
        int tentativas;

        cm = getCaminho(req);

        System.out.println(cm);

        try {
            urlImagem = new URL(cm);
            caminhURL = true;
            System.out.println("url");
        } catch (MalformedURLException ex) {
            caminhURL = false;
            System.out.println("local");
        }

        if (getTipoImagem()
                == TIPOIMAGEM.IMAGEMAPENAS) {
            enviaImagemBufferedIMG(caminhURL, urlImagem, resp, req);
        }

        if (getTipoImagem()
                == TIPOIMAGEM.IMAGEMCOMCOMENTATRIOS) {
            enviaImagemComentarios(caminhURL, urlImagem, resp, req);
        }

        if (getTipoImagem()
                == TIPOIMAGEM.TESTES) {
            enviaTeste(resp, req);
        }

    }

    private void enviaImagemBufferedIMG(boolean pCaminhoURL, URL pUrlImagem, HttpServletResponse resposta, HttpServletRequest requisicao) {
        OutputStream out = null;

        try {

            BufferedImage imagem = null;

            if (pCaminhoURL && pUrlImagem != null) {
                //          System.out.println("LendoURL:" + pUrlImagem);
                imagem = UTilSBCoreInputs.getImagemBufferedbyURL(pUrlImagem.toString(), 40, 2000);
            } else {
                //        System.out.println("Lendo" + getCaminho(requisicao));
                imagem = UTilSBCoreInputs.getImagemBufferedByLocalFile(getCaminho(requisicao));
            }

            if (imagem == null) {
                //      System.err.println("NAO FOI POSSIVEL ENCONTRAR" + getCaminho(requisicao));
                //    System.out.println("Obtendo imagem padrão");

                imagem = UTilSBCoreInputs.getImagemBufferedByLocalFile(requisicao.getSession().getServletContext().getRealPath("/") + "/resources" + "/img/icones/frameNaoAlcancado.jpg");
                if (imagem != null) {
                    System.out.println("Imagem padrão obtida");
                }
            }

            out = resposta.getOutputStream();

            if (out == null) {
                System.out.println("out==nulll!!!!");
            }
            if (imagem == null) {
                System.out.println("imagem==null");
            }

            imagem = trataimagem(imagem, requisicao);

            resposta.setContentType("image/jpeg");

            ImageIO.write(imagem, "jpg", out);

            ByteArrayOutputStream os = new ByteArrayOutputStream();
            //ImageIO.write(imagem, "jpg", os);
            InputStream is = new ByteArrayInputStream(os.toByteArray());
            IOUtils.copy(is, out);
            is.close();
            //  final byte[] bytes = new byte[1024];
            // while ((read = dadosImput.read(bytes)) != -1) {
            //    out.write(bytes, 0, read);
            // }
            //out.flush();
        } catch (IOException ex) {

            System.err.println("@@@ errro ENVIANDO IMAGEM PARA O RESP" + ex.getMessage());

            Logger.getLogger(ServletImagemGenerico.class
                    .getName()).log(Level.SEVERE, null, ex);

        } finally {
            try {
                if (out != null) {
                    out.close();
                }

            } catch (IOException ex) {
                System.err.println("@@@ errro executando Close" + ex.getMessage());
                Logger
                        .getLogger(ServletImagemGenerico.class
                                .getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    private void enviaImagemComentarios(boolean pCaminhoURL, URL pUrlImagem, HttpServletResponse resposta, HttpServletRequest requisicao) {
        OutputStream out = null;
        try {

            InputStream dadosImput;
            try {

                if (pCaminhoURL && pUrlImagem != null) {
                    dadosImput = pUrlImagem.openStream();
                } else {
                    dadosImput = new FileInputStream(getCaminho(requisicao));
                }
            } catch (IOException e) {
                System.err.println("NAO FOI POSSIVEL ENCONTRAR" + getCaminho(requisicao));
                dadosImput = null;

            }
            if (dadosImput == null) {
                try {
                    File f = new File(UtilSBWPServletTools.getCaminhoLocalServlet() + "/resources/img/SBGrande.png");
                    System.out.println("Imagem de camera encontrada enviando imagem padrão" + getCaminho(requisicao));
                    dadosImput = new FileInputStream(f);
                } catch (IOException ex) {
                    System.err.println("@@@@ ERRO LENDO IMAGEM PADRÃO");
                    Logger
                            .getLogger(ServletImagemGenerico.class
                                    .getName()).log(Level.SEVERE, null, ex);
                }
            }
            out = resposta.getOutputStream();
            //dadosImput = trataimagem(dadosImput);
            resposta.setContentType("image/jpeg");
            int read = 0;
            final byte[] bytes = new byte[1024];
            while ((read = dadosImput.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }

            out.flush();
            out.close();
        } catch (IOException ex) {
            System.err.println("@@@ errro ENVIANDO IMAGEM PARA O RESP");
            Logger
                    .getLogger(ServletImagemGenerico.class
                            .getName()).log(Level.SEVERE, null, ex);

        } finally {
            try {
                System.err.println("@@@ errro ENVIANDO IMAGEM PARA O RESP");
                out.close();

            } catch (IOException ex) {
                Logger.getLogger(ServletImagemGenerico.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    protected void enviaTeste(HttpServletResponse resposta, HttpServletRequest requisicao) {
        // if Content-Disposition header value is set to inline, the response is displayed in browser

        resposta.setHeader("Content-Disposition", "inline; filename=\"" + "teste.jpg");

        // Copy input file's InputStream to response's OutputStream
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = new FileInputStream(getCaminho(requisicao));
            try {
                outputStream = resposta.getOutputStream();

            } catch (IOException ex) {
                Logger.getLogger(ServletImagemGenerico.class
                        .getName()).log(Level.SEVERE, null, ex);
            }

            int read = 0;
            final byte[] bytes = new byte[1024];
            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }

        } catch (FileNotFoundException fne) {
            fne.printStackTrace();

        } catch (IOException ex) {
            Logger.getLogger(ServletImagemGenerico.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();

                } catch (IOException ex) {
                    Logger.getLogger(ServletImagemGenerico.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();

                } catch (IOException ex) {
                    Logger.getLogger(ServletImagemGenerico.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

}
