package org.coletivoJava.superBitsFW.webPaginas.constantes.componentes;

import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.componentes.FabCompVisualInputs;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import org.coletivojava.fw.api.objetoNativo.view.componente.FamiliaComponente;
import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.ComoComponenteVisualSB;

@Named
@ApplicationScoped
public class CompSBCompVisualInputs implements Serializable {

    private final FamiliaComponente familia;
    private final ComoComponenteVisualSB texto_Com_FOrmatacao;
    private final ComoComponenteVisualSB texto_Sem_Formatacao;
    private final ComoComponenteVisualSB texto_Grande_com_Formatacao;
    private final ComoComponenteVisualSB texto_multiplas_Linhas;
    private final ComoComponenteVisualSB valor_com_minimo_e_Maximo;
    private final ComoComponenteVisualSB senha;
    private final ComoComponenteVisualSB cor;
    private final ComoComponenteVisualSB hTML;
    private final ComoComponenteVisualSB hTML_Template;
    private final ComoComponenteVisualSB quantidade;
    private final ComoComponenteVisualSB moeda;
    private final ComoComponenteVisualSB email;
    private final ComoComponenteVisualSB data;
    private final ComoComponenteVisualSB data_Hora;
    private final ComoComponenteVisualSB hora;
    private final ComoComponenteVisualSB ligado_ou_Desligado;
    private final ComoComponenteVisualSB entidade_Simples;
    private final ComoComponenteVisualSB arquivo_de_Entidade;
    private final ComoComponenteVisualSB imagem_pequena_Entidade;
    private final ComoComponenteVisualSB imagem_medio_de_Entidade;
    private final ComoComponenteVisualSB imagem_grande_de_Entidade;

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

    public ComoComponenteVisualSB getComponentePadrao(
            com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.ComoComponenteVisualSB pComponente) {
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

    public ComoComponenteVisualSB getTexto_Com_FOrmatacao() {
        return texto_Com_FOrmatacao;
    }

    public ComoComponenteVisualSB getTexto_Sem_Formatacao() {
        return texto_Sem_Formatacao;
    }

    public ComoComponenteVisualSB getTexto_Grande_com_Formatacao() {
        return texto_Grande_com_Formatacao;
    }

    public ComoComponenteVisualSB getTexto_multiplas_Linhas() {
        return texto_multiplas_Linhas;
    }

    public ComoComponenteVisualSB getValor_com_minimo_e_Maximo() {
        return valor_com_minimo_e_Maximo;
    }

    public ComoComponenteVisualSB getSenha() {
        return senha;
    }

    public ComoComponenteVisualSB getCor() {
        return cor;
    }

    public ComoComponenteVisualSB getHTML() {
        return hTML;
    }

    public ComoComponenteVisualSB getHTML_Template() {
        return hTML_Template;
    }

    public ComoComponenteVisualSB getQuantidade() {
        return quantidade;
    }

    public ComoComponenteVisualSB getMoeda() {
        return moeda;
    }

    public ComoComponenteVisualSB getEmail() {
        return email;
    }

    public ComoComponenteVisualSB getData() {
        return data;
    }

    public ComoComponenteVisualSB getData_Hora() {
        return data_Hora;
    }

    public ComoComponenteVisualSB getHora() {
        return hora;
    }

    public ComoComponenteVisualSB getLigado_ou_Desligado() {
        return ligado_ou_Desligado;
    }

    public ComoComponenteVisualSB getEntidade_Simples() {
        return entidade_Simples;
    }

    public ComoComponenteVisualSB getArquivo_de_Entidade() {
        return arquivo_de_Entidade;
    }

    public ComoComponenteVisualSB getImagem_pequena_Entidade() {
        return imagem_pequena_Entidade;
    }

    public ComoComponenteVisualSB getImagem_medio_de_Entidade() {
        return imagem_medio_de_Entidade;
    }

    public ComoComponenteVisualSB getImagem_grande_de_Entidade() {
        return imagem_grande_de_Entidade;
    }
}
