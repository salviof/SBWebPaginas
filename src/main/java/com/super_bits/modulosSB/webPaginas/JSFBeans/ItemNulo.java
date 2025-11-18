package com.super_bits.modulosSB.webPaginas.JSFBeans;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.EntidadeSimples;
import java.io.Serializable;

public class ItemNulo extends EntidadeSimples implements Serializable {

    @InfoCampo(tipo = FabTipoAtributoObjeto.NOME)
    String nomeCurto = "RegistroNulo";
    @InfoCampo(tipo = FabTipoAtributoObjeto.ID)
    int id = 0;

    public ItemNulo() {
        // TODO Auto-generated constructor stub
    }

}
