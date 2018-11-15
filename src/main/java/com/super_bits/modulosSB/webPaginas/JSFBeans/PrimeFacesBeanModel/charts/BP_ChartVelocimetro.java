package com.super_bits.modulosSB.webPaginas.JSFBeans.PrimeFacesBeanModel.charts;

import java.util.ArrayList;
import java.util.List;
import org.primefaces.model.chart.MeterGaugeChartModel;

public class BP_ChartVelocimetro {
	private MeterGaugeChartModel grafico;  
	private String nomeGrafico;
	private String metrica;
	
	public BP_ChartVelocimetro(double[] pIntervalos,double pValor,String nomeGrafico,String pMetrica) {
		List<Number> intervalos= new ArrayList<Number>();
		for (double interval : pIntervalos){
			intervalos.add(interval);
		}
		metrica=pMetrica;
		setGrafico(new MeterGaugeChartModel(pValor,intervalos));
	}

	public MeterGaugeChartModel getGrafico() {
		return grafico;
	}

	public void setGrafico(MeterGaugeChartModel grafico) {
		this.grafico = grafico;
	}

	public String getNomeGrafico() {
		return nomeGrafico;
	}

	public void setNomeGrafico(String nomeGrafico) {
		this.nomeGrafico = nomeGrafico;
	}

	public String getMetrica() {
		return metrica;
	}

	public void setMetrica(String metrica) {
		this.metrica = metrica;
	}

}
