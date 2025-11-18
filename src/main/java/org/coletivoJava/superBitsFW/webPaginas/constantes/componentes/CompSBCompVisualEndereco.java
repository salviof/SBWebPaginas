package org.coletivoJava.superBitsFW.webPaginas.constantes.componentes;

import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.componentes.FabCompVisualEndereco;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import org.coletivojava.fw.api.objetoNativo.view.componente.FamiliaComponente;
import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.ComoComponenteVisualSB;

@Named
@ApplicationScoped
public class CompSBCompVisualEndereco implements Serializable {

	private final FamiliaComponente familia;
	private final ComoComponenteVisualSB endereco;
	private final ComoComponenteVisualSB bairro;
	private final ComoComponenteVisualSB cidade;
	private final ComoComponenteVisualSB unidade_federativa_Estado;
	private final ComoComponenteVisualSB nacao_Pais;
	private final ComoComponenteVisualSB cep;

	CompSBCompVisualEndereco() {
		this.familia = com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.componentes.FabCompVisualEndereco.class
				.getEnumConstants()[0].getFamilia().getRegistro();
		this.endereco = FabCompVisualEndereco.INFORMACAO_ENDERECO.getRegistro();
		this.bairro = FabCompVisualEndereco.BAIRRO.getRegistro();
		this.cidade = FabCompVisualEndereco.CIDADE.getRegistro();
		this.unidade_federativa_Estado = FabCompVisualEndereco.UNIDADE_FEDERATIVA
				.getRegistro();
		this.nacao_Pais = FabCompVisualEndereco.NACAO.getRegistro();
		this.cep = FabCompVisualEndereco.CEP.getRegistro();
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

	public ComoComponenteVisualSB getEndereco() {
		return endereco;
	}

	public ComoComponenteVisualSB getBairro() {
		return bairro;
	}

	public ComoComponenteVisualSB getCidade() {
		return cidade;
	}

	public ComoComponenteVisualSB getUnidade_federativa_Estado() {
		return unidade_federativa_Estado;
	}

	public ComoComponenteVisualSB getNacao_Pais() {
		return nacao_Pais;
	}

	public ComoComponenteVisualSB getCep() {
		return cep;
	}
}