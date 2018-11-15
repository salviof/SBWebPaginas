/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.temas;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.GrupoCampos;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.ItfCampoExibicaoFormulario;
import java.util.ArrayList;
import java.util.List;
import org.primefaces.adamantium.view.data.datatable.ColumnsView;

/**
 *
 * @author desenvolvedor
 */
public class PgUtilPrimeModel {

    public List<ColumnsView.ColumnModel> gerarColunas(GrupoCampos pGrupoCampo) {
        List<ColumnsView.ColumnModel> colunas = new ArrayList<>();
        for (ItfCampoExibicaoFormulario campo : pGrupoCampo.getCampos()) {
            ColumnsView.ColumnModel novaColuna = new ColumnsView.ColumnModel("", "");
        }
        return colunas;

    }

}
