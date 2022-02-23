/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFManagedBeans.declarados.Paginas;

import com.super_bits.modulos.SBAcessosModel.model.UsuarioSB;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.MapaAcoesSistema;
import com.super_bits.modulosSB.SBCore.UtilGeral.MapaDeAcoes;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringValidador;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.erp.ItfSistemaERP;
import com.super_bits.modulosSB.SBCore.modulos.objetos.MapaObjetosProjetoAtual;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfUsuario;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.MB_paginaCadastroEntidades;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.reflexao.anotacoes.InfoPagina;
import com.super_bits.modulosSB.webPaginas.controller.paginasDoSistema.FabAcaoPaginasDoSistema;
import com.super_bits.modulosSB.webPaginas.controller.paginasDoSistema.InfoAcaoPaginaDoSistema;
import com.super_bits.modulosSB.webPaginas.controller.sessao.ControleDeSessaoWeb;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

@InfoAcaoPaginaDoSistema(acao = FabAcaoPaginasDoSistema.PAGINA_NATIVA_LOGIN_OAUTH_MB)
@Named
@ViewScoped
@InfoPagina(nomeCurto = "loginoauth", tags = "Concessao de permissao via Oauth")
public class PgAutenticacaoOauth extends MB_paginaCadastroEntidades<UsuarioSB> {

    private ItfSistemaERP sistemaCliente;

    @Inject
    private ControleDeSessaoWeb controleDeSessao;

    private List<ItfAcaoDoSistema> acoesEscopoUsuario;

    private boolean permitidoAutenticar;

    @PostConstruct()
    public void inicio() {

        HttpServletRequest requisicao = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String hashCliente = requisicao.getParameter("hashChavePublicaAplicacaoSolicitante");
        String scopoUsuario = requisicao.getParameter("scopo");
        ItfUsuario usuarioSolicitante = SBCore.getServicoPermissao().getUsuarioByEmail(scopoUsuario);
        if (!UtilSBCoreStringValidador.isNuloOuEmbranco(hashCliente) && !UtilSBCoreStringValidador.isNuloOuEmbranco(scopoUsuario)) {
            List<ItfSistemaERP> sistemaEncontrado = (List) UtilSBPersistencia.gerarConsultaDeEntidade(MapaObjetosProjetoAtual.getClasseDoObjetoByNome("SistemaERPConfiavel"), getEMPagina())
                    .addcondicaoCampoIgualA("hashChavePublica", hashCliente).resultadoRegistros();
            if (!sistemaEncontrado.isEmpty()) {
                sistemaCliente = sistemaEncontrado.get(0);
            }
            acoesEscopoUsuario = new ArrayList<>();
            MapaAcoesSistema.getListaTodasGestao().stream()
                    .filter(ac -> (ac.getModulo().equals(usuarioSolicitante.getGrupo().getModuloPrincipal())
                    && SBCore.getServicoPermissao().isAcaoPermitidaUsuarioLogado(ac)))
                    .forEach(acoesEscopoUsuario::add);
            controleDeSessao.setUsuarioLogar(scopoUsuario);
            permitidoAutenticar = true;
        }

    }

    public ItfSistemaERP getSistemaRequisitante() {
        return sistemaCliente;
    }

    public boolean isPermitidoAutenticar() {
        return permitidoAutenticar;
    }

    public List<ItfAcaoDoSistema> getAcoesEscopoUsuario() {
        return acoesEscopoUsuario;
    }

}
