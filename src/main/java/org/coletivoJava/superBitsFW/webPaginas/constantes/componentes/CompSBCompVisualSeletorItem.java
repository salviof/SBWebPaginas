package org.coletivoJava.superBitsFW.webPaginas.constantes.componentes;

import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.componentes.FabCompVisualSeletorItem;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import org.coletivojava.fw.api.objetoNativo.view.componente.FamiliaComponente;
import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.ComoComponenteVisualSB;

@Named
@ApplicationScoped
public class CompSBCompVisualSeletorItem implements Serializable {

	private final FamiliaComponente familia;
	private final ComoComponenteVisualSB carrousel;
	private final ComoComponenteVisualSB menu_em_Botoes;
	private final ComoComponenteVisualSB auto_Complete;
	private final ComoComponenteVisualSB grade;
	private final ComoComponenteVisualSB combo;
	private final ComoComponenteVisualSB radio;

	CompSBCompVisualSeletorItem() {
		this.familia = com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.componentes.FabCompVisualSeletorItem.class
				.getEnumConstants()[0].getFamilia().getRegistro();
		this.carrousel = FabCompVisualSeletorItem.CARROULSEL.getRegistro();
		this.menu_em_Botoes = FabCompVisualSeletorItem.BOTOES_MENU
				.getRegistro();
		this.auto_Complete = FabCompVisualSeletorItem.AUTO_COMPLETE
				.getRegistro();
		this.grade = FabCompVisualSeletorItem.GRADE.getRegistro();
		this.combo = FabCompVisualSeletorItem.COMBO.getRegistro();
		this.radio = FabCompVisualSeletorItem.RADIO.getRegistro();
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

	public ComoComponenteVisualSB getCarrousel() {
		return carrousel;
	}

	public ComoComponenteVisualSB getMenu_em_Botoes() {
		return menu_em_Botoes;
	}

	public ComoComponenteVisualSB getAuto_Complete() {
		return auto_Complete;
	}

	public ComoComponenteVisualSB getGrade() {
		return grade;
	}

	public ComoComponenteVisualSB getCombo() {
		return combo;
	}

	public ComoComponenteVisualSB getRadio() {
		return radio;
	}
}