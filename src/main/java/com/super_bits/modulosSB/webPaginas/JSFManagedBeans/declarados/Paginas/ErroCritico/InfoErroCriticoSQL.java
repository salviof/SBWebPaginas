package com.super_bits.modulosSB.webPaginas.JSFManagedBeans.declarados.Paginas.ErroCritico;

import com.super_bits.modulosSB.Persistencia.dao.SBNQ;

public class InfoErroCriticoSQL extends InfoErroCritico {
	
	String sqlText;
	String parametros;
	private SBNQ qinfo;
	
	
	public InfoErroCriticoSQL(String pMsg,SBNQ pQinfo,Exception pExcept) {
		super(pMsg,pExcept);
		qinfo=pQinfo;
		
	}


	public SBNQ getQinfo() {
		return qinfo;
	}


	

}
