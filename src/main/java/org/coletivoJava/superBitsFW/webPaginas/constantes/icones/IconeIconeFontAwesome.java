package org.coletivoJava.superBitsFW.webPaginas.constantes.icones;

import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.icones.FabIconeFontAwesome;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.icones.itfIcone;

@Named
@ApplicationScoped
public class IconeIconeFontAwesome implements Serializable {

    private final itfIcone iCONE_PERSONALIZADO;
    private final itfIcone rEG_NOVO;
    private final itfIcone rEG_EDITAR;
    private final itfIcone rEG_VALIDAR;
    private final itfIcone rEG_REMOVER;
    private final itfIcone rEG_ATUALIZAR;
    private final itfIcone rEG_ENVIAR;
    private final itfIcone rEG_BAIXAR;
    private final itfIcone rEG_GERENCIAR;
    private final itfIcone rEG_VISUALIZAR;
    private final itfIcone rEG_SALVAR;
    private final itfIcone rEG_PESQUISA_AVANCADA;
    private final itfIcone rEG_AGRUPAR_REGISTROS;
    private final itfIcone rEG_LISTAR;
    private final itfIcone rEG_PROXIMO;
    private final itfIcone rEG_ANTERIOR;
    private final itfIcone rEG_PESQUISAR;
    private final itfIcone rEG_SELECIONAR_ITEM;
    private final itfIcone eSCRITORIO_DOCUMENTO_TEXTO;
    private final itfIcone pESSOA_CORACAO;
    private final itfIcone cOMERCIO_PRESENTE;
    private final itfIcone cOMERCIO_BITCOINS;
    private final itfIcone cOMERCIO_APERTO_DE_MAO;
    private final itfIcone tECNOLOGIA_BATERIA;
    private final itfIcone sISTEMA_HOME;
    private final itfIcone sISTEMA_ACESSO_NEGADO;
    private final itfIcone sISTEMA_USUARIO;
    private final itfIcone sISTEMA_CARTAO_DE_ACESSO;
    private final itfIcone sISTEMA_ENGRENAGEM;
    private final itfIcone cOMUNICACAO_OK;
    private final itfIcone cOMNUNICACAO_NAO_ESTA_OK;
    private final itfIcone cOMUNICACAO_AVIAO_DE_PAPEL;

    IconeIconeFontAwesome() {
        this.iCONE_PERSONALIZADO = FabIconeFontAwesome.ICONE_PERSONALIZADO
                .getIcone();
        this.rEG_NOVO = FabIconeFontAwesome.REG_NOVO.getIcone();
        this.rEG_EDITAR = FabIconeFontAwesome.REG_EDITAR.getIcone();
        this.rEG_VALIDAR = FabIconeFontAwesome.REG_VALIDAR.getIcone();
        this.rEG_REMOVER = FabIconeFontAwesome.REG_REMOVER.getIcone();
        this.rEG_ATUALIZAR = FabIconeFontAwesome.REG_ATUALIZAR.getIcone();
        this.rEG_ENVIAR = FabIconeFontAwesome.REG_ENVIAR.getIcone();
        this.rEG_BAIXAR = FabIconeFontAwesome.REG_BAIXAR.getIcone();
        this.rEG_GERENCIAR = FabIconeFontAwesome.REG_GERENCIAR.getIcone();
        this.rEG_VISUALIZAR = FabIconeFontAwesome.REG_VISUALIZAR.getIcone();
        this.rEG_SALVAR = FabIconeFontAwesome.REG_SALVAR.getIcone();
        this.rEG_PESQUISA_AVANCADA = FabIconeFontAwesome.REG_PESQUISA_AVANCADA
                .getIcone();
        this.rEG_AGRUPAR_REGISTROS = FabIconeFontAwesome.REG_AGRUPAR_REGISTROS
                .getIcone();
        this.rEG_LISTAR = FabIconeFontAwesome.REG_LISTAR.getIcone();
        this.rEG_PROXIMO = FabIconeFontAwesome.REG_PROXIMO.getIcone();
        this.rEG_ANTERIOR = FabIconeFontAwesome.REG_ANTERIOR.getIcone();
        this.rEG_PESQUISAR = FabIconeFontAwesome.REG_PESQUISAR.getIcone();
        this.rEG_SELECIONAR_ITEM = FabIconeFontAwesome.REG_SELECIONAR_ITEM
                .getIcone();
        this.eSCRITORIO_DOCUMENTO_TEXTO = FabIconeFontAwesome.ESCRITORIO_DOCUMENTO_TEXTO
                .getIcone();
        this.pESSOA_CORACAO = FabIconeFontAwesome.PESSOA_CORACAO.getIcone();
        this.cOMERCIO_PRESENTE = FabIconeFontAwesome.COMERCIO_PRESENTE
                .getIcone();
        this.cOMERCIO_BITCOINS = FabIconeFontAwesome.COMERCIO_BITCOINS
                .getIcone();
        this.cOMERCIO_APERTO_DE_MAO = FabIconeFontAwesome.COMERCIO_APERTO_DE_MAO
                .getIcone();
        this.tECNOLOGIA_BATERIA = FabIconeFontAwesome.TECNOLOGIA_BATERIA
                .getIcone();
        this.sISTEMA_HOME = FabIconeFontAwesome.SISTEMA_HOME.getIcone();
        this.sISTEMA_ACESSO_NEGADO = FabIconeFontAwesome.SISTEMA_ACESSO_NEGADO
                .getIcone();
        this.sISTEMA_USUARIO = FabIconeFontAwesome.SISTEMA_USUARIO.getIcone();
        this.sISTEMA_CARTAO_DE_ACESSO = FabIconeFontAwesome.SISTEMA_CARTAO_DE_ACESSO
                .getIcone();
        this.sISTEMA_ENGRENAGEM = FabIconeFontAwesome.SISTEMA_ENGRENAGEM
                .getIcone();
        this.cOMUNICACAO_OK = FabIconeFontAwesome.COMUNICACAO_OK.getIcone();
        this.cOMNUNICACAO_NAO_ESTA_OK = FabIconeFontAwesome.COMNUNICACAO_NAO_ESTA_OK
                .getIcone();
        this.cOMUNICACAO_AVIAO_DE_PAPEL = FabIconeFontAwesome.COMUNICACAO_AVIAO_DE_PAPEL
                .getIcone();
    }

    public itfIcone getICONE_PERSONALIZADO() {
        return iCONE_PERSONALIZADO;
    }

    public itfIcone getREG_NOVO() {
        return rEG_NOVO;
    }

    public itfIcone getREG_EDITAR() {
        return rEG_EDITAR;
    }

    public itfIcone getREG_VALIDAR() {
        return rEG_VALIDAR;
    }

    public itfIcone getREG_REMOVER() {
        return rEG_REMOVER;
    }

    public itfIcone getREG_ATUALIZAR() {
        return rEG_ATUALIZAR;
    }

    public itfIcone getREG_ENVIAR() {
        return rEG_ENVIAR;
    }

    public itfIcone getREG_BAIXAR() {
        return rEG_BAIXAR;
    }

    public itfIcone getREG_GERENCIAR() {
        return rEG_GERENCIAR;
    }

    public itfIcone getREG_VISUALIZAR() {
        return rEG_VISUALIZAR;
    }

    public itfIcone getREG_SALVAR() {
        return rEG_SALVAR;
    }

    public itfIcone getREG_PESQUISA_AVANCADA() {
        return rEG_PESQUISA_AVANCADA;
    }

    public itfIcone getREG_AGRUPAR_REGISTROS() {
        return rEG_AGRUPAR_REGISTROS;
    }

    public itfIcone getREG_LISTAR() {
        return rEG_LISTAR;
    }

    public itfIcone getREG_PROXIMO() {
        return rEG_PROXIMO;
    }

    public itfIcone getREG_ANTERIOR() {
        return rEG_ANTERIOR;
    }

    public itfIcone getREG_PESQUISAR() {
        return rEG_PESQUISAR;
    }

    public itfIcone getREG_SELECIONAR_ITEM() {
        return rEG_SELECIONAR_ITEM;
    }

    public itfIcone getESCRITORIO_DOCUMENTO_TEXTO() {
        return eSCRITORIO_DOCUMENTO_TEXTO;
    }

    public itfIcone getPESSOA_CORACAO() {
        return pESSOA_CORACAO;
    }

    public itfIcone getCOMERCIO_PRESENTE() {
        return cOMERCIO_PRESENTE;
    }

    public itfIcone getCOMERCIO_BITCOINS() {
        return cOMERCIO_BITCOINS;
    }

    public itfIcone getCOMERCIO_APERTO_DE_MAO() {
        return cOMERCIO_APERTO_DE_MAO;
    }

    public itfIcone getTECNOLOGIA_BATERIA() {
        return tECNOLOGIA_BATERIA;
    }

    public itfIcone getSISTEMA_HOME() {
        return sISTEMA_HOME;
    }

    public itfIcone getSISTEMA_ACESSO_NEGADO() {
        return sISTEMA_ACESSO_NEGADO;
    }

    public itfIcone getSISTEMA_USUARIO() {
        return sISTEMA_USUARIO;
    }

    public itfIcone getSISTEMA_CARTAO_DE_ACESSO() {
        return sISTEMA_CARTAO_DE_ACESSO;
    }

    public itfIcone getSISTEMA_ENGRENAGEM() {
        return sISTEMA_ENGRENAGEM;
    }

    public itfIcone getCOMUNICACAO_OK() {
        return cOMUNICACAO_OK;
    }

    public itfIcone getCOMNUNICACAO_NAO_ESTA_OK() {
        return cOMNUNICACAO_NAO_ESTA_OK;
    }

    public itfIcone getCOMUNICACAO_AVIAO_DE_PAPEL() {
        return cOMUNICACAO_AVIAO_DE_PAPEL;
    }
}
