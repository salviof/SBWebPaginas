package org.coletivoJava.superBitsFW.webPaginas.constantes.componentes;

import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.componentes.FabCompVisualFormularioDeAcao;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.ItfComponenteVisualSB;
import org.coletivojava.fw.api.objetoNativo.view.componente.FamiliaComponente;

@Named
@ApplicationScoped
public class CompSBCompVisualFormularioDeAcao implements Serializable {

	private final FamiliaComponente familia;
	private final ItfComponenteVisualSB formulario_de_acao_registro_Responsiva;
	private final ItfComponenteVisualSB formulario_de_acao_registro_Mobile;
	private final ItfComponenteVisualSB formulario_de_acao_registro_Desktop;
	private final ItfComponenteVisualSB formulario_de_acao_Listagem_Responsiva;
	private final ItfComponenteVisualSB formulario_de_acao_listagem_Mobile;
	private final ItfComponenteVisualSB formulario_de_acao_listagem_Desktop;
	private final ItfComponenteVisualSB formulario_de_acao_listagem_Card_Full;

	CompSBCompVisualFormularioDeAcao() {
		this.familia = com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.componentes.FabCompVisualFormularioDeAcao.class
				.getEnumConstants()[0].getFamilia().getRegistro();
		this.formulario_de_acao_registro_Responsiva = FabCompVisualFormularioDeAcao.ACAO_REGISTRO_RESPONSIVO
				.getRegistro();
		this.formulario_de_acao_registro_Mobile = FabCompVisualFormularioDeAcao.ACAO_REGISTRO_DESKTOP
				.getRegistro();
		this.formulario_de_acao_registro_Desktop = FabCompVisualFormularioDeAcao.ACAO_REGISTRO_MOBILE
				.getRegistro();
		this.formulario_de_acao_Listagem_Responsiva = FabCompVisualFormularioDeAcao.ACAO_LISTAGEM_RESPONSIVA
				.getRegistro();
		this.formulario_de_acao_listagem_Mobile = FabCompVisualFormularioDeAcao.ACAO_LISTAGEM_MOBILE
				.getRegistro();
		this.formulario_de_acao_listagem_Desktop = FabCompVisualFormularioDeAcao.ACAO_LISTAGEM_DESKTOP
				.getRegistro();
		this.formulario_de_acao_listagem_Card_Full = FabCompVisualFormularioDeAcao.ACAO_LISTAGEM_CARD_FULL
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

	public ItfComponenteVisualSB getFormulario_de_acao_registro_Responsiva() {
		return formulario_de_acao_registro_Responsiva;
	}

	public ItfComponenteVisualSB getFormulario_de_acao_registro_Mobile() {
		return formulario_de_acao_registro_Mobile;
	}

	public ItfComponenteVisualSB getFormulario_de_acao_registro_Desktop() {
		return formulario_de_acao_registro_Desktop;
	}

	public ItfComponenteVisualSB getFormulario_de_acao_Listagem_Responsiva() {
		return formulario_de_acao_Listagem_Responsiva;
	}

	public ItfComponenteVisualSB getFormulario_de_acao_listagem_Mobile() {
		return formulario_de_acao_listagem_Mobile;
	}

	public ItfComponenteVisualSB getFormulario_de_acao_listagem_Desktop() {
		return formulario_de_acao_listagem_Desktop;
	}

	public ItfComponenteVisualSB getFormulario_de_acao_listagem_Card_Full() {
		return formulario_de_acao_listagem_Card_Full;
	}
}