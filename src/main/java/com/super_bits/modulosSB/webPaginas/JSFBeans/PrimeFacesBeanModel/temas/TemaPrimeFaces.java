package com.super_bits.modulosSB.webPaginas.JSFBeans.PrimeFacesBeanModel.temas;

import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeSimplesORM;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import java.io.Serializable;

public class TemaPrimeFaces extends EntidadeSimplesORM implements Serializable {

    @InfoCampo(tipo = FabTipoAtributoObjeto.ID)
    private Long id;

    @InfoCampo(tipo = FabTipoAtributoObjeto.NOME)
    private String name;

    @InfoCampo(tipo = FabTipoAtributoObjeto.IMG_PEQUENA)
    private String image;

    public TemaPrimeFaces() {
    }

    public TemaPrimeFaces(Long pid, String name, String image) {
        this.id = pid;
        this.name = name;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public String getImgPequena() {
        return image;
    }

}
