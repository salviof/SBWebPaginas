/*
 *  Super-Bits.com CODE CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFManagedBeans.declarados.Paginas;

import com.super_bits.modulos.SBAcessosModel.model.PermissaoSB;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfPermissao;
import java.util.List;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ComoGrupoUsuario;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ComoUsuario;

/**
 *
 * @author Salvio
 */
public interface ItfPgAcessos {

    /**
     *
     * @return Todos os Acessos do Sistema
     */
    public List<PermissaoSB> getAcessos();

    /**
     * Acesso selecionado para edição
     *
     * @return Acesso selecionado para edição
     */
    public ItfPermissao getAcessoSelecionado();

    /**
     * Usuário Selecionado para inclusão em acessoSBSelecionado.negados ou
     * permitido
     *
     * @return Usuário Selecionado para inclusão em acessoSBSelecionado.negados
     * ou permitido
     */
    public ComoUsuario getUsuarioSBInclusao();

    /**
     * Usuário selecionado para Exlusão em acessoSBSelecionado.negados ou
     * permitido
     *
     * @return Usuário selecionado para Exlusão em acessoSBSelecionado.negados
     * ou permitido
     */
    public ComoUsuario getUsuarioSBExclusao();

    /**
     * Grupo selecoinado para inclusão em acesso acessoSBSelecionado.negados ou
     * permitido
     *
     * @return Grupo selecoinado para inclusão em acesso
     * acessoSBSelecionado.negados ou permitido
     */
    public ComoGrupoUsuario getGrupoUsuarioSBInclusao();

    /**
     * @return Grupo selecoinado para exclusão em acessoSBSelecionado.negados ou
     * permitido
     */
    public ComoGrupoUsuario getGrupoUsuarioSBExclusao();

    /**
     * Adiciona o usuário SelecionadoInclusão, ao
     * getAcessoSelecionado.usuariosPermitidos
     */
    public void adicionarUsuarioPermitido();

    /**
     * Adciona o usuário SelecionadoInclusão, ao
     * getAcessoSelecionado.usuariosNegados
     */
    public void adicionarUsuarioNegado();

    /**
     * remove o usuárioSelecionadoExclusão do
     * getAcessoSelecionado.usuariosPermitids
     */
    public void removerUsuarioPermitido();

    /**
     * remove o usuarioSelecionadoExclusão do getAcessoSelecionado.usuarioNegado
     */
    public void removerUsuarioNegado();

    /**
     * remove o grupoSelecionadoExclusao da lista de acessos negado em
     * getAcessoSelecionado.grupoNegado
     */
    public void removerGrupoNegado();

    /**
     * remove o grupoSelecionadoExclusao da lista de acessos negado em
     * getAcessoSelecionado.permitido
     */
    public void removerGrupoPermitido();
}
