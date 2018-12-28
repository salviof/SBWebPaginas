/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.tipos;

import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.ItfComponenteVisualSB;
import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.componentes.FabCompVisualBotaoAcao;
import javax.enterprise.context.ApplicationScoped;

/**
 * ATENÇÃO A DOCUMENTAÇÃO DA CLASSE É OBRIGATÓRIA O JAVADOC DOS METODOS PUBLICOS
 * SÃO OBRIGATÓRIOS E QUANDO EXISTIR UMA INTERFACE DOCUMENTADA UMA REFERENCIA
 * DEVE SER CRIADA, A SINTAXE DE REFERENCIA É: @see_ NomeDAClasse#Metodo
 * DOCUMENTE DE FORMA OBJETIVA E EFICIENTE, NÃO ESQUEÇA QUE VOCÊ FAZ PARTE DE
 * UMA EQUIPE.
 *
 * @author <a href="mailto:salviof@gmail.com">Salvio Furbino</a>
 * @since 22/12/2015
 * @version 1.0
 *
 */
@ApplicationScoped
public class TipoVisualBotao extends ConstantesWeb {

    public TipoVisualBotao() {
        super(FabCompVisualBotaoAcao.class);
    }

    @Override
    public String getPadrao() {
        FabCompVisualBotaoAcao botaoAcao = FabCompVisualBotaoAcao.ICONE;

        return botaoAcao.toString();
    }

    public ItfComponenteVisualSB getApenasIcone() {
        return FabCompVisualBotaoAcao.ICONE.getRegistro();
    }

    public ItfComponenteVisualSB getIconeENome() {
        return FabCompVisualBotaoAcao.ICONE_E_NOME.getRegistro();
    }

    public ItfComponenteVisualSB getBotaoGigante() {
        return FabCompVisualBotaoAcao.ICONE_GIGANTE.getRegistro();
    }

    public ItfComponenteVisualSB getApenasNome() {
        return FabCompVisualBotaoAcao.NOME.getRegistro();
    }

    public ItfComponenteVisualSB getApenasDescricao() {
        return FabCompVisualBotaoAcao.ICONE_GIGANTE.getRegistro();
    }

}
