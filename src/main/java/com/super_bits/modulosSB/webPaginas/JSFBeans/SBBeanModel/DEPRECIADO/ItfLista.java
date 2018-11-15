/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.SBBeanModel.DEPRECIADO;

import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanNormal;
import java.util.List;

/**
 *
 * @author Salvio
 */
public interface ItfLista {

    String getNomeLista();

    List<? extends ItfBeanNormal> getLista();

}
