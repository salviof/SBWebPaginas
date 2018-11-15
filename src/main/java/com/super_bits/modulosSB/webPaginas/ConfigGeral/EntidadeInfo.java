package com.super_bits.modulosSB.webPaginas.ConfigGeral;

import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;

public class EntidadeInfo {
	private Map<String,Class<?>> entidades=new HashMap<String, Class<?>>() ;
	
	@PostConstruct
	public void initEntidadeInfo(){
		EntityManager em =UtilSBPersistencia.getNovoEM();
		Set<EntityType<?>> lista= em.getMetamodel().getEntities();
		for (EntityType<?> teste:lista ) {
			System.out.println(teste.getJavaType().toString());
			Class<?> classe=teste.getJavaType();
			Annotation[] annotacoes = classe.getAnnotations();
			//annotations.
			System.out.println(teste.getClass().getName());
		
		
	}
	}

}
