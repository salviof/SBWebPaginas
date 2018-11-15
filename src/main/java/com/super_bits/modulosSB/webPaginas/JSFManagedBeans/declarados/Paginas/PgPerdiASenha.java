/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFManagedBeans.declarados.Paginas;

import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.declarados.util.PgUtil;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.MB_PaginaConversation;

/**
 * ATENÇÃO A DOCUMENTAÇÃO DA CLASSE É OBRIGATÓRIA O JAVADOC DOS METODOS PUBLICOS
 * SÃO OBRIGATÓRIOS E QUANDO EXISTIR UMA INTERFACE DOCUMENTADA UMA REFERENCIA
 * DEVE SER CRIADA, A SINTAXE DE REFERENCIA É: @see_ NomeDAClasse#Metodo
 * DOCUMENTE DE FORMA OBJETIVA E EFICIENTE, NÃO ESQUEÇA QUE VOCÊ FAZ PARTE DE
 * UMA EQUIPE.
 *
 * @author <a href="mailto:salviof@gmail.com">Salvio Furbino</a>
 * @since 05/01/2016
 * @version 1.0
 *
 */
public class PgPerdiASenha extends MB_PaginaConversation {

    public PgUtil paginaUtil;
    public String nomeOuEmail;

    public void enviarSenha() {

    }

    @Override
    protected String defineTitulo() {
        return "Recuperação de Senha";
    }

    @Override
    protected String defineNomeLink() {
        return "Perdeu a senha?";
    }

    @Override
    protected String defineDescricao() {
        return "Pagina para recuperação de senha";
    }

    @Override
    public int getId() {
        return 100;
    }

    @Override
    public ItfBeanSimples getBeanSelecionado() {
        return null;
    }

    @Override
    public void setBeanSelecionado(ItfBeanSimples pBeanSimples) {

    }

}
