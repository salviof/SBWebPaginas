package org.coletivoJava.superBitsFW.webPaginas.constantes.componentes;

import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.componentes.FabCompVisualSeletorItens;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import org.coletivojava.fw.api.objetoNativo.view.componente.FamiliaComponente;
import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.ComoComponenteVisualSB;

@Named
@ApplicationScoped
public class CompSBCompVisualSeletorItens implements Serializable {

	private final FamiliaComponente familia;
	private final ComoComponenteVisualSB pickList;

	CompSBCompVisualSeletorItens() {
		this.familia = com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.componentes.FabCompVisualSeletorItens.class
				.getEnumConstants()[0].getFamilia().getRegistro();
		this.pickList = FabCompVisualSeletorItens.PICKLIST.getRegistro();
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

	public ComoComponenteVisualSB getPickList() {
		return pickList;
	}
}