/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.super_bits.modulosSB.webPaginas.push;

import com.super_bits.modulosSB.SBCore.modulos.comunicacao.ComoDialogo;
import com.super_bits.modulosSB.SBCore.modulos.servicosCore.ComoServicoComunicacaoUI;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.declarados.webSite.InfoWebApp;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWPServletTools;

/**
 *
 * @author salvio
 */
public class NotificadorIntranetJSF implements ComoServicoComunicacaoUI {

    @Override
    public boolean notificarViaMenu(ComoDialogo pDialogo) {

        InfoWebApp aplicacao = (InfoWebApp) UtilSBWPServletTools.getBeanByNamed("infoWebApp", InfoWebApp.class);
        return aplicacao.publicar(pDialogo);

    }

    @Override
    public boolean notificarViaBloqueioTEla(ComoDialogo pDialogo) {
        InfoWebApp aplicacao = (InfoWebApp) UtilSBWPServletTools.getBeanByNamed("infoWebApp", InfoWebApp.class);
        return aplicacao.publicar(pDialogo);
    }

}
