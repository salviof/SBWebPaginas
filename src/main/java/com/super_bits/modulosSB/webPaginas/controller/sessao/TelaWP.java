
/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.controller.sessao;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.view.telas.FabTipoTamanhoTelas;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import com.super_bits.modulosSB.SBCore.modulos.view.telas.ItfTelaUsuario;
import com.super_bits.modulosSB.SBCore.modulos.view.telas.ItfTipoTela;
import com.super_bits.modulosSB.SBCore.modulos.view.telas.TipoTela;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

/**
 *
 * @author sfurbino
 */
public class TelaWP implements ItfTelaUsuario, Serializable {

    private String dispositivo;

    private String aplicativo;

    private String versaoAplicativo;

    private int x;

    private int y;

    private int numeroMaximoColunas;

    private final Map<String, String> parametrosEncontrados;

    private TipoTela tipoTela;

    public TelaWP(String parametroTela) {
        parametrosEncontrados = new HashMap<>();
        try {

            for (String pr : parametroTela.split("\\|\\|")) {
                String[] parametro = pr.split(":");
                parametrosEncontrados.put(parametro[0], parametro[1]);
            }

            setAplicativo(parametrosEncontrados.get("aplicativo"));
            setVersaoAplicativo(parametrosEncontrados.get("versaoAplicativo"));
            setX(Integer.parseInt(parametrosEncontrados.get("tamanhoX")));
            setY(Integer.parseInt(parametrosEncontrados.get("tamanhoY")));

            if (x < 540) {
                numeroMaximoColunas = 1;
            } else if (x >= 540 && x < 720) {
                numeroMaximoColunas = 3;
            } else if (x >= 720 && x < 960) {
                numeroMaximoColunas = 6;
            } else if (x >= 960 && x < 1140) {
                numeroMaximoColunas = 9;
            } else if (x >= 1140) {
                numeroMaximoColunas = 12;
            }

        } catch (Throwable e) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro configurando parametros da tela para" + parametroTela, e);
        }

    }

    @Override
    public String getDispositivo() {
        return dispositivo;
    }

    public void setDispositivo(String dispositivo) {
        this.dispositivo = dispositivo;
    }

    @Override
    public String getAplicativo() {
        return aplicativo;
    }

    public final void setAplicativo(String aplicativo) {
        this.aplicativo = aplicativo;
    }

    @Override
    public String getVersaoAplicativo() {
        return versaoAplicativo;
    }

    public final void setVersaoAplicativo(String versaoAplicativo) {
        this.versaoAplicativo = versaoAplicativo;
    }

    @Override
    public int getX() {
        return x;
    }

    public final void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    public final void setY(int y) {
        this.y = y;
    }

    @Override
    public int getNumeroMaximoColunas() {
        return numeroMaximoColunas;
    }

    @Override
    public boolean isUmMobile() {
        return x < 600;
    }

    @Override
    public ItfTipoTela getTipoTela() {
        if (tipoTela == null) {
            tipoTela = (TipoTela) FabTipoTamanhoTelas.getTipoTelaByX(x);
        }
        return tipoTela;

    }

    @Override
    public int getNumeroMaximoColunasAteSeis() {
        if (getNumeroMaximoColunas() > 6) {
            return 6;
        } else {
            return getNumeroMaximoColunas();
        }
    }

    @Override
    public int getNumeroMaximoColunasAteTres() {
        if (getNumeroMaximoColunas() > 3) {
            return 3;
        } else {
            return getNumeroMaximoColunas();
        }
    }

    @Override
    public int getNumeroMaximoColunasAteNove() {
        if (getNumeroMaximoColunas() > 9) {
            return 9;
        } else {
            return getNumeroMaximoColunas();
        }
    }

    @Override
    public int getNumeroMaximoColunasAtedoze() {
        if (getNumeroMaximoColunas() > 12) {
            return 12;
        } else {
            return getNumeroMaximoColunas();
        }
    }

    @Override
    public int getNumeroMaximoColunasAteCinco() {
        if (getNumeroMaximoColunas() > 5) {
            return 5;
        } else {
            return getNumeroMaximoColunas();
        }
    }

    @Override
    public int getNumeroMaximoColunasTamanhoDobro() {
        if (numeroMaximoColunas == 1) {
            return 1;
        }
        return numeroMaximoColunas / 2;
    }

    @Override
    public int getNumeroMaximoColunasTamanhoTriplo() {
        if (numeroMaximoColunas == 1) {
            return 1;
        }
        return numeroMaximoColunas / 3;
    }

}
