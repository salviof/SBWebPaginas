package org.coletivoJava.superBitsFW.webPaginas.constantes.componentes;

import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.componentes.FabCompVIsualInputsLayout;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.ComoComponenteVisualSB;
import org.coletivojava.fw.api.objetoNativo.view.componente.FamiliaComponente;

@Named
@ApplicationScoped
public class CompSBCompVIsualInputsLayout implements Serializable {

	private final FamiliaComponente familia;
	private final ComoComponenteVisualSB resumido;
	private final ComoComponenteVisualSB superior;
	private final ComoComponenteVisualSB esquerda;
	private final ComoComponenteVisualSB automatico;
	private final ComoComponenteVisualSB sem_Label;
	private final ComoComponenteVisualSB debug_Campo;

	CompSBCompVIsualInputsLayout() {
		this.familia = com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.componentes.FabCompVIsualInputsLayout.class
				.getEnumConstants()[0].getFamilia().getRegistro();
		this.resumido = FabCompVIsualInputsLayout.LABEL_RESUMIDO.getRegistro();
		this.superior = FabCompVIsualInputsLayout.LABEL_SUPERIOR.getRegistro();
		this.esquerda = FabCompVIsualInputsLayout.LABEL_ESQUEDA.getRegistro();
		this.automatico = FabCompVIsualInputsLayout.AUTOMATICO.getRegistro();
		this.sem_Label = FabCompVIsualInputsLayout.INPUTSEM_LABEL.getRegistro();
		this.debug_Campo = FabCompVIsualInputsLayout.DEBUG_CAMPO.getRegistro();
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

	public ComoComponenteVisualSB getResumido() {
		return resumido;
	}

	public ComoComponenteVisualSB getSuperior() {
		return superior;
	}

	public ComoComponenteVisualSB getEsquerda() {
		return esquerda;
	}

	public ComoComponenteVisualSB getAutomatico() {
		return automatico;
	}

	public ComoComponenteVisualSB getSem_Label() {
		return sem_Label;
	}

	public ComoComponenteVisualSB getDebug_Campo() {
		return debug_Campo;
	}
}