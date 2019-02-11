package org.coletivoJava.superBitsFW.webPaginas.constantes.componentes;

import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.componentes.FabCompVisualSeletorItem;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.ItfComponenteVisualSB;
import org.coletivojava.fw.api.objetoNativo.view.componente.FamiliaComponente;

@Named
@ApplicationScoped
public class CompSBCompVisualSeletorItem implements Serializable {

	private final FamiliaComponente familia;
	private final ItfComponenteVisualSB carrousel;
	private final ItfComponenteVisualSB menu_em_Botoes;
	private final ItfComponenteVisualSB auto_Complete;
	private final ItfComponenteVisualSB grade;
	private final ItfComponenteVisualSB combo;
	private final ItfComponenteVisualSB radio;

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

	public ItfComponenteVisualSB getCarrousel() {
		return carrousel;
	}

	public ItfComponenteVisualSB getMenu_em_Botoes() {
		return menu_em_Botoes;
	}

	public ItfComponenteVisualSB getAuto_Complete() {
		return auto_Complete;
	}

	public ItfComponenteVisualSB getGrade() {
		return grade;
	}

	public ItfComponenteVisualSB getCombo() {
		return combo;
	}

	public ItfComponenteVisualSB getRadio() {
		return radio;
	}
}