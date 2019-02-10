/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.controller.sessao;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.ItensGenericos.basico.SessaoOffline;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.ItensGenericos.basico.UsuarioAplicacaoEmExecucao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfUsuario;

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

    private void inicializaSessaoAplicativo() {
        if (usuarioAplicacao == null) {

            usuarioAplicacao = new UsuarioAplicacaoEmExecucao();
            SessaoAtualSBWP sessaoAtual = new SessaoAtualSBWP();
            sessaoAtual.setUsuario(usuarioAplicacao);

        }
    }

    @Override
    public ItfUsuario getUsuario() {
        return usuarioAplicacao;
    }

    @Override
    public void encerrarSessao() {
        System.out.println("Log Fim Aplicativo");
    }

}
