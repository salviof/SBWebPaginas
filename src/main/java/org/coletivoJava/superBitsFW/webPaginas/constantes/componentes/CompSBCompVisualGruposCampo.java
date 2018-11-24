package org.coletivoJava.superBitsFW.webPaginas.constantes.componentes;

import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.componentes.FabCompVisualGruposCampo;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.ItfComponenteVisualSB;
import org.coletivojava.fw.api.objetoNativo.view.componente.FamiliaComponente;

@Named
@ApplicationScoped
public class CompSBCompVisualGruposCampo implements Serializable {

	private final FamiliaComponente familia;
	private final ItfComponenteVisualSB grpo_de_Campos_Responsivos;
	private final ItfComponenteVisualSB grpo_de_Campos_Mobile;
	private final ItfComponenteVisualSB grpo_de_Campos_Desktop;

	CompSBCompVisualGruposCampo() {
		this.familia = com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.componentes.FabCompVisualGruposCampo.class
				.getEnumConstants()[0].getFamilia().getRegistro();
		this.grpo_de_Campos_Responsivos = FabCompVisualGruposCampo.GRUPOS_DE_CAMPO_ACAO_FORMULARIO_RESPONSIVO
				.getRegistro();
		this.grpo_de_Campos_Mobile = FabCompVisualGruposCampo.GRUPOS_DE_CAMPO_ACAO_FORMULARIO_MOBILE
				.getRegistro();
		this.grpo_de_Campos_Desktop = FabCompVisualGruposCampo.GRUPOS_DE_CAMPO_ACAO_FORMULARIO_DESKTOP
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

	public ItfComponenteVisualSB getGrpo_de_Campos_Responsivos() {
		return grpo_de_Campos_Responsivos;
	}

	public ItfComponenteVisualSB getGrpo_de_Campos_Mobile() {
		return grpo_de_Campos_Mobile;
	}

	public ItfComponenteVisualSB getGrpo_de_Campos_Desktop() {
		return grpo_de_Campos_Desktop;
	}
}