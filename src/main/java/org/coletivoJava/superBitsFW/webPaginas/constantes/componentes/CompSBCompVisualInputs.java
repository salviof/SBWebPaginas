package org.coletivoJava.superBitsFW.webPaginas.constantes.componentes;

import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.componentes.FabCompVisualInputs;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.ItfComponenteVisualSB;
import org.coletivojava.fw.api.objetoNativo.view.componente.FamiliaComponente;

@Named
@ApplicationScoped
public class CompSBCompVisualInputs implements Serializable {

    private final FamiliaComponente familia;
    private final ItfComponenteVisualSB texto_Com_FOrmatacao;
    private final ItfComponenteVisualSB texto_Sem_Formatacao;
    private final ItfComponenteVisualSB texto_Grande_com_Formatacao;
    private final ItfComponenteVisualSB texto_multiplas_Linhas;
    private final ItfComponenteVisualSB valor_com_minimo_e_Maximo;
    private final ItfComponenteVisualSB senha;
    private final ItfComponenteVisualSB cor;
    private final ItfComponenteVisualSB hTML;
    private final ItfComponenteVisualSB hTML_Template;
    private final ItfComponenteVisualSB quantidade;
    private final ItfComponenteVisualSB moeda;
    private final ItfComponenteVisualSB email;
    private final ItfComponenteVisualSB data;
    private final ItfComponenteVisualSB data_Hora;
    private final ItfComponenteVisualSB hora;
    private final ItfComponenteVisualSB ligado_ou_Desligado;
    private final ItfComponenteVisualSB entidade_Simples;
    private final ItfComponenteVisualSB arquivo_de_Entidade;
    private final ItfComponenteVisualSB imagem_pequena_Entidade;
    private final ItfComponenteVisualSB imagem_medio_de_Entidade;
    private final ItfComponenteVisualSB imagem_grande_de_Entidade;

    CompSBCompVisualInputs() {
        this.familia = com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.componentes.FabCompVisualInputs.class
                .getEnumConstants()[0].getFamilia().getRegistro();
        this.texto_Com_FOrmatacao = FabCompVisualInputs.TEXTO_COM_FORMATACAO
                .getRegistro();
        this.texto_Sem_Formatacao = FabCompVisualInputs.TEXTO_SEM_FORMATACAO
                .getRegistro();
        this.texto_Grande_com_Formatacao = FabCompVisualInputs.TEXTO_GRANDE_COM_FORMATACAO
                .getRegistro();
        this.texto_multiplas_Linhas = FabCompVisualInputs.TEXTMO_MULTIPLAS_LINHAS
                .getRegistro();
        this.valor_com_minimo_e_Maximo = FabCompVisualInputs.NUMERO_MINIMO_MAXIMO
                .getRegistro();
        this.senha = FabCompVisualInputs.SENHA.getRegistro();
        this.cor = FabCompVisualInputs.COR.getRegistro();
        this.hTML = FabCompVisualInputs.HTML.getRegistro();
        this.hTML_Template = FabCompVisualInputs.HTML_TEMPLATE.getRegistro();
        this.quantidade = FabCompVisualInputs.QUANTIDADE.getRegistro();
        this.moeda = FabCompVisualInputs.MOEDA.getRegistro();
        this.email = FabCompVisualInputs.EMAIL.getRegistro();
        this.data = FabCompVisualInputs.DATA.getRegistro();
        this.data_Hora = FabCompVisualInputs.DATA_HORA.getRegistro();
        this.hora = FabCompVisualInputs.HORA.getRegistro();
        this.ligado_ou_Desligado = FabCompVisualInputs.LIGADO_DESLIGADO
                .getRegistro();
        this.entidade_Simples = FabCompVisualInputs.ENTIDADE_SIMPLES
                .getRegistro();
        this.arquivo_de_Entidade = FabCompVisualInputs.ARQUIVO_DE_ENTIDADE
                .getRegistro();
        this.imagem_pequena_Entidade = FabCompVisualInputs.IMAGEM_PEQUENA_DE_ENTIDADE
                .getRegistro();
        this.imagem_medio_de_Entidade = FabCompVisualInputs.IMAGEM_MEDIO_DE_ENTIDADE
                .getRegistro();
        this.imagem_grande_de_Entidade = FabCompVisualInputs.IMAGEM_GRANDE_DE_ENTIDADE
                .getRegistro();
    }

    public ItfComponenteVisualSB getComponentePadrao(
            com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.ItfComponenteVisualSB pComponente) {
        try {
            if (pComponente == null) {
                return familia.getFabrica().getComponentePadrao();
            } else {
                if (!pComponente.getFamilia().equals(familia.getFabrica())) {
                    throw new UnsupportedOperationException(
                            "O layout enviado não pertence a família de componentes compatíveis,"
                            + pComponente.getNomeComponente()
                            + "é da família: "
                            + pComponente.getFamilia().getNomeFAmilia()
                            + "incompativel com " + familia.getNome());
                }
                return pComponente;
            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO,
                    "Erro obtendo componente padrão para " + familia.getNome(),
                    t);
            return null;
        }
    }

    public FamiliaComponente getFamilia() {
        return familia;
    }

    public ItfComponenteVisualSB getTexto_Com_FOrmatacao() {
        return texto_Com_FOrmatacao;
    }

    public ItfComponenteVisualSB getTexto_Sem_Formatacao() {
        return texto_Sem_Formatacao;
    }

    public ItfComponenteVisualSB getTexto_Grande_com_Formatacao() {
        return texto_Grande_com_Formatacao;
    }

    public ItfComponenteVisualSB getTexto_multiplas_Linhas() {
        return texto_multiplas_Linhas;
    }

    public ItfComponenteVisualSB getValor_com_minimo_e_Maximo() {
        return valor_com_minimo_e_Maximo;
    }

    public ItfComponenteVisualSB getSenha() {
        return senha;
    }

    public ItfComponenteVisualSB getCor() {
        return cor;
    }

    public ItfComponenteVisualSB getHTML() {
        return hTML;
    }

    public ItfComponenteVisualSB getHTML_Template() {
        return hTML_Template;
    }

    public ItfComponenteVisualSB getQuantidade() {
        return quantidade;
    }

    public ItfComponenteVisualSB getMoeda() {
        return moeda;
    }

    public ItfComponenteVisualSB getEmail() {
        return email;
    }

    public ItfComponenteVisualSB getData() {
        return data;
    }

    public ItfComponenteVisualSB getData_Hora() {
        return data_Hora;
    }

    public ItfComponenteVisualSB getHora() {
        return hora;
    }

    public ItfComponenteVisualSB getLigado_ou_Desligado() {
        return ligado_ou_Desligado;
    }

    public ItfComponenteVisualSB getEntidade_Simples() {
        return entidade_Simples;
    }

    public ItfComponenteVisualSB getArquivo_de_Entidade() {
        return arquivo_de_Entidade;
    }

    public ItfComponenteVisualSB getImagem_pequena_Entidade() {
        return imagem_pequena_Entidade;
    }

    public ItfComponenteVisualSB getImagem_medio_de_Entidade() {
        return imagem_medio_de_Entidade;
    }

    public ItfComponenteVisualSB getImagem_grande_de_Entidade() {
        return imagem_grande_de_Entidade;
    }
}
