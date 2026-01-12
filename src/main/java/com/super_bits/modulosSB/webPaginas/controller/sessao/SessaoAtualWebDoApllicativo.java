/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.controller.sessao;

import com.super_bits.modulosSB.SBCore.ConfigGeral.CarameloCode;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.ItensGenericos.basico.SessaoOffline;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.ItensGenericos.basico.UsuarioAplicacaoEmExecucao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoSessao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoUsuario;
import java.io.File;

/**
 *
 * @author desenvolvedor
 */
public class SessaoAtualWebDoApllicativo extends SessaoOffline {

    private static SessaoAtualSBWP sessaoAtualAplicativo;
    private static UsuarioAplicacaoEmExecucao usuarioAplicacao;

    public SessaoAtualWebDoApllicativo() {
        super();
        inicializaSessaoAplicativo();
    }

    @Override
    public String getPastaTempDeSessao() {
        // super.getPastaTempDeSessao();
        String caminho = SBCore.getServicoArquivosDeEntidade().getEndrLocalRecursosDoObjeto(ComoSessao.class, SBCore.getUsuarioLogado().getEmail());

        File pastaTemo = new File(caminho + "/");
        pastaTemo.mkdirs();
        return caminho;
    }

    private void inicializaSessaoAplicativo() {
        if (usuarioAplicacao == null) {

            usuarioAplicacao = new UsuarioAplicacaoEmExecucao();
            SessaoAtualSBWP sessaoAtual = new SessaoAtualSBWP();
            sessaoAtual.setUsuario(usuarioAplicacao);

        }
    }

    @Override
    public ComoUsuario getUsuario() {
        return usuarioAplicacao;
    }

    @Override
    public void encerrarSessao() {
        System.out.println("Log Fim Aplicativo");
    }

}
