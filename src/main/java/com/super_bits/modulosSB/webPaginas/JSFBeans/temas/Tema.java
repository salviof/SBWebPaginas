/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.temas;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UTilSBCoreInputs;
import com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo.UtilSBCoreArquivoTexto;
import com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo.UtilSBCoreArquivos;
import com.super_bits.modulosSB.SBCore.modulos.Mensagens.FabMensagens;
import com.super_bits.modulosSB.webPaginas.JSFBeans.PrimeFacesBeanModel.temas.TemaPrimeFaces;
import com.super_bits.modulosSB.webPaginas.JSFBeans.PrimeFacesBeanModel.temas.TemasDisponiveis;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWP_JSFTools;
import java.io.File;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.RequestScoped;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * Configuração de Temas
 *
 *
 *
 * @author <a href="mailto:salviof@gmail.com">Salvio Furbino</a>
 * @since 14/01/2015
 * @version 1.0
 */
@RequestScoped
public class Tema {

    private static TemaPrimeFaces temaOficial = new TemaPrimeFaces(1, "blitzer", "blitzer");

    /**
     *
     * Importa o Arquivo zip do ThemeRoller, versão 1.11.2, e aplica no sistema
     *
     *
     * @param event
     */
    public void importarRollerTheme(FileUploadEvent event) {

        System.out.println("Chamou importar");

        if (event.getFile() != null) {

            File pastaArquivos = new File(UtilSBWP_JSFTools.getCaminhoLocalRessource() + "/primefaces-temaPersonalizado/temp");
            pastaArquivos.mkdirs();
            System.out.println("Salvando arquivo em " + pastaArquivos.getAbsolutePath());

            try {
                event.getFile().write(pastaArquivos.getAbsolutePath() + "/tema.zip");

            } catch (Exception ex) {
                Logger.getLogger(Tema.class.getName()).log(Level.SEVERE, null, ex);
            }

            UtilSBCoreArquivos.descompactar(pastaArquivos + "/tema.zip", pastaArquivos.getAbsolutePath());
            String pastaTema = pastaArquivos.getAbsolutePath() + "/jquery-ui-1.11.4.custom";
            List<String> css = UTilSBCoreInputs.getStringsByArquivoLocal(pastaTema
                    + "/jquery-ui.css");
            if (css == null) {
                SBCore.enviarMensagemUsuario("Erro lendo arquivo CSS, certifique ter enviado um arquivo versão 1.11.2 ", FabMensagens.ERRO);
                return;
            }
            UtilSBCoreArquivoTexto.limparArquivoTexto(UtilSBWP_JSFTools.getCaminhoLocalRessource() + "/primefaces-temaPersonalizado/theme.css");
            for (String linha : css) {
                linha = linha.replace("url(\"images/", "url(\"#{resource['primefaces-temaPersonalizado:images/");
                linha = linha.replace(".png\")", ".png']}\")");
                linha = linha.replace(".gif\")", ".gif']}\")");
                if (UtilSBCoreArquivoTexto.escreverEmArquivo(UtilSBWP_JSFTools.getCaminhoLocalRessource() + "/primefaces-temaPersonalizado/theme.css", linha)) {
                    System.out.println("linha css salva em arquivo:" + linha);
                }

            }
            UtilSBCoreArquivos.copiarArquivos(pastaTema + "/images", UtilSBWP_JSFTools.getCaminhoLocalRessource() + "/primefaces-temaPersonalizado/images");

            SBCore.enviarMensagemUsuario("Arquivo Importado com sucesso de" + event.getFile().getFileName(), FabMensagens.AVISO);

        } else {
            SBCore.enviarMensagemUsuario("Tentando aplicando template, certifique-se que baixou a versão jquery-ui-1.10.4", FabMensagens.AVISO);
        }
    }

    public List<TemaPrimeFaces> getTemasDisponiveis() {
        TemasDisponiveis temas = new TemasDisponiveis();
        return temas.getThemes();
    }

    public TemaPrimeFaces getTemaOficial() {
        return temaOficial;
    }

    public void setTemaOficial(TemaPrimeFaces temaOficial) {
        Tema.temaOficial = temaOficial;
    }

    public void setTemaPeloNome(String pNome) {
        for (TemaPrimeFaces tm : getTemasDisponiveis()) {
            if (tm.getName().equals(pNome)) {
                temaOficial = tm;
            }
        }
    }

}
