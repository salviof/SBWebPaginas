package org.coletivoJava.superBitsFW.webPaginas.constantes.icones;

import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.icones.FabIconeFontAwesome;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.icones.ComoIcone;

@Named
@ApplicationScoped
public class IconeIconeFontAwesome implements Serializable {

	private final ComoIcone iCONE_PERSONALIZADO;
	private final ComoIcone rEG_NOVO;
	private final ComoIcone rEG_EDITAR;
	private final ComoIcone rEG_VALIDAR;
	private final ComoIcone rEG_REMOVER;
	private final ComoIcone rEG_ATUALIZAR;
	private final ComoIcone rEG_ENVIAR;
	private final ComoIcone rEG_BAIXAR;
	private final ComoIcone rEG_GERENCIAR;
	private final ComoIcone rEG_VISUALIZAR;
	private final ComoIcone rEG_SALVAR;
	private final ComoIcone rEG_PESQUISA_AVANCADA;
	private final ComoIcone rEG_AGRUPAR_REGISTROS;
	private final ComoIcone rEG_LISTAR;
	private final ComoIcone rEG_PROXIMO;
	private final ComoIcone rEG_ANTERIOR;
	private final ComoIcone rEG_PESQUISAR;
	private final ComoIcone rEG_SELECIONAR_ITEM;
	private final ComoIcone eSCRITORIO_DOCUMENTO_TEXTO;
	private final ComoIcone pESSOA_CORACAO;
	private final ComoIcone cOMERCIO_PRESENTE;
	private final ComoIcone cOMERCIO_BITCOINS;
	private final ComoIcone cOMERCIO_APERTO_DE_MAO;
	private final ComoIcone tECNOLOGIA_BATERIA;
	private final ComoIcone sISTEMA_HOME;
	private final ComoIcone sISTEMA_ACESSO_NEGADO;
	private final ComoIcone sISTEMA_USUARIO;
	private final ComoIcone sISTEMA_CARTAO_DE_ACESSO;
	private final ComoIcone sISTEMA_ENGRENAGEM;
	private final ComoIcone cOMUNICACAO_OK;
	private final ComoIcone cOMNUNICACAO_NAO_ESTA_OK;
	private final ComoIcone cOMUNICACAO_AVIAO_DE_PAPEL;

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

	public ComoIcone getICONE_PERSONALIZADO() {
		return iCONE_PERSONALIZADO;
	}

	public ComoIcone getREG_NOVO() {
		return rEG_NOVO;
	}

	public ComoIcone getREG_EDITAR() {
		return rEG_EDITAR;
	}

	public ComoIcone getREG_VALIDAR() {
		return rEG_VALIDAR;
	}

	public ComoIcone getREG_REMOVER() {
		return rEG_REMOVER;
	}

	public ComoIcone getREG_ATUALIZAR() {
		return rEG_ATUALIZAR;
	}

	public ComoIcone getREG_ENVIAR() {
		return rEG_ENVIAR;
	}

	public ComoIcone getREG_BAIXAR() {
		return rEG_BAIXAR;
	}

	public ComoIcone getREG_GERENCIAR() {
		return rEG_GERENCIAR;
	}

	public ComoIcone getREG_VISUALIZAR() {
		return rEG_VISUALIZAR;
	}

	public ComoIcone getREG_SALVAR() {
		return rEG_SALVAR;
	}

	public ComoIcone getREG_PESQUISA_AVANCADA() {
		return rEG_PESQUISA_AVANCADA;
	}

	public ComoIcone getREG_AGRUPAR_REGISTROS() {
		return rEG_AGRUPAR_REGISTROS;
	}

	public ComoIcone getREG_LISTAR() {
		return rEG_LISTAR;
	}

	public ComoIcone getREG_PROXIMO() {
		return rEG_PROXIMO;
	}

	public ComoIcone getREG_ANTERIOR() {
		return rEG_ANTERIOR;
	}

	public ComoIcone getREG_PESQUISAR() {
		return rEG_PESQUISAR;
	}

	public ComoIcone getREG_SELECIONAR_ITEM() {
		return rEG_SELECIONAR_ITEM;
	}

	public ComoIcone getESCRITORIO_DOCUMENTO_TEXTO() {
		return eSCRITORIO_DOCUMENTO_TEXTO;
	}

	public ComoIcone getPESSOA_CORACAO() {
		return pESSOA_CORACAO;
	}

	public ComoIcone getCOMERCIO_PRESENTE() {
		return cOMERCIO_PRESENTE;
	}

	public ComoIcone getCOMERCIO_BITCOINS() {
		return cOMERCIO_BITCOINS;
	}

	public ComoIcone getCOMERCIO_APERTO_DE_MAO() {
		return cOMERCIO_APERTO_DE_MAO;
	}

	public ComoIcone getTECNOLOGIA_BATERIA() {
		return tECNOLOGIA_BATERIA;
	}

	public ComoIcone getSISTEMA_HOME() {
		return sISTEMA_HOME;
	}

	public ComoIcone getSISTEMA_ACESSO_NEGADO() {
		return sISTEMA_ACESSO_NEGADO;
	}

	public ComoIcone getSISTEMA_USUARIO() {
		return sISTEMA_USUARIO;
	}

	public ComoIcone getSISTEMA_CARTAO_DE_ACESSO() {
		return sISTEMA_CARTAO_DE_ACESSO;
	}

	public ComoIcone getSISTEMA_ENGRENAGEM() {
		return sISTEMA_ENGRENAGEM;
	}

	public ComoIcone getCOMUNICACAO_OK() {
		return cOMUNICACAO_OK;
	}

	public ComoIcone getCOMNUNICACAO_NAO_ESTA_OK() {
		return cOMNUNICACAO_NAO_ESTA_OK;
	}

	public ComoIcone getCOMUNICACAO_AVIAO_DE_PAPEL() {
		return cOMUNICACAO_AVIAO_DE_PAPEL;
	}
}