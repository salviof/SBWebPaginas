/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios;

import com.super_bits.modulos.SBAcessosModel.model.GrupoUsuarioSB;
import com.super_bits.modulos.SBAcessosModel.model.PermissaoSB;
import com.super_bits.modulos.SBAcessosModel.model.quadroPermissao.QuadroPermissaoGrupo;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.MapaAcoesSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoGerenciarEntidade;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfGrupoUsuaioEditavel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

/**
 *
 * @author desenvolvedor
 * @param <T>
 */
public class MB_PagniaGrupoPermissao<T extends ItfGrupoUsuaioEditavel> extends MB_paginaCadastroEntidades<T> {

    protected Map<String, ItfAcaoGerenciarEntidade> gestoesPermissionaveis;
    protected final List<QuadroPermissaoGrupo> permissoesGrupo = new ArrayList<>();

    public MB_PagniaGrupoPermissao() {
    }

    @PostConstruct
    public void inicio() {
        try {
            List<PermissaoSB> permissoes = UtilSBPersistencia.getListaTodos(PermissaoSB.class, getEMPagina());
            gestoesPermissionaveis = new HashMap<>();

            setEntidadesListadas(UtilSBPersistencia.getListaTodos(GrupoUsuarioSB.class));

            for (PermissaoSB p : permissoes) {
                ItfAcaoDoSistema acao = MapaAcoesSistema.getAcaoDoSistema(p.getAcao().getEnumAcaoDoSistema());
                ItfAcaoGerenciarEntidade acaoGEstao = acao.getAcaoDeGestaoEntidade();
                if (acaoGEstao != null) {
                    String nomeUnico = acao.getAcaoDeGestaoEntidade().getNomeUnico();
                    gestoesPermissionaveis.put(nomeUnico, acaoGEstao);

                }
            }

        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro executando ações Iniciais", t);
        }

    }

    public MB_PagniaGrupoPermissao(ItfAcaoGerenciarEntidade pGestao) {
        super(pGestao);
    }

    @Override
    public void setEntidadeSelecionada(T pEntidadeSelecionada) {

        if (pEntidadeSelecionada != null && pEntidadeSelecionada.getId() > 0) {
            if (getEntidadeSelecionada() == null || !getEntidadeSelecionada().equals(pEntidadeSelecionada)) {

                permissoesGrupo.clear();
                for (ItfAcaoGerenciarEntidade gestao : gestoesPermissionaveis.values()) {
                    if (gestao.getModulo().equals(pEntidadeSelecionada.getModuloPrincipal())) {
                        permissoesGrupo.add(new QuadroPermissaoGrupo((GrupoUsuarioSB) pEntidadeSelecionada, gestao));
                    }
                }
            }
        }
        super.setEntidadeSelecionada(pEntidadeSelecionada);
    }

    protected void setEntidadeDireto(T pEntidadeSelecionada) {
        super.setEntidadeSelecionada(pEntidadeSelecionada);
    }

    public Map<String, ItfAcaoGerenciarEntidade> getGestoesPermissionaveis() {
        return gestoesPermissionaveis;
    }

    public List<QuadroPermissaoGrupo> getPermissoesGrupo() {
        return permissoesGrupo;
    }

}
