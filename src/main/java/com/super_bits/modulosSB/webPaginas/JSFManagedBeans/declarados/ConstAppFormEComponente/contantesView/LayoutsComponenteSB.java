/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFManagedBeans.declarados.ConstAppFormEComponente.contantesView;

import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.FabFamiliaCompVisual;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import org.coletivojava.fw.api.objetoNativo.view.componente.FamiliaComponente;

/**
 *
 * @author salvioF
 */
@Named
@ApplicationScoped
public class LayoutsComponenteSB implements Serializable {

    private final LayoutInputCampo layoutCampo = new LayoutInputCampo();
    private final LayoutSeletorItem layoutSeletorItem = new LayoutSeletorItem();
    private final LayoutSeletorItens layoutItens = new LayoutSeletorItens();
    private final LayoutBotoes layoutBotoes = new LayoutBotoes();

    private static final List<FamiliaComponente> FAMILIAS_COMPONENTE = new ArrayList();

    public LayoutsComponenteSB() {

        for (FabFamiliaCompVisual familia : FabFamiliaCompVisual.values()) {
            FAMILIAS_COMPONENTE.add(familia.getRegistro());
        }

    }

    public LayoutInputCampo getLayoutCampo() {
        return layoutCampo;
    }

    public LayoutSeletorItem getLayoutSeletorItem() {
        return layoutSeletorItem;
    }

    public LayoutSeletorItens getLayoutItens() {
        return layoutItens;
    }

    public static List<FamiliaComponente> getFamiliasComponentes() {
        return FAMILIAS_COMPONENTE;
    }

    public LayoutBotoes getLayoutBotoes() {
        return layoutBotoes;
    }

}
