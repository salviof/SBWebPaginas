/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.controller.sessao;

import java.lang.annotation.ElementType;
import static java.lang.annotation.ElementType.FIELD;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;
import javax.inject.Qualifier;

/**
 *
 * @author desenvolvedor
 */
@Qualifier
@Retention(RUNTIME)
@Target({ElementType.TYPE, ElementType.PARAMETER, FIELD, ElementType.METHOD})
public @interface QlSessaoFacesContext {

}
