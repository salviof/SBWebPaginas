package com.super_bits.modulosSB.webPaginas.JSFBeans.PrimeFacesBeanModel;

import java.io.Serializable;
import org.apache.poi.ss.usermodel.charts.ChartSeries;
import org.apache.poi.ss.usermodel.charts.LineChartSeries;
import org.primefaces.model.chart.CartesianChartModel;

public abstract class BP_GraficoLinear implements Serializable {

    private CartesianChartModel categoryModel;

    private CartesianChartModel linearModel;

    public BP_GraficoLinear() {
        createCategoryModel();
        createLinearModel();
    }

    public CartesianChartModel getCategoryModel() {
        return categoryModel;
    }

    public CartesianChartModel getLinearModel() {
        return linearModel;
    }

    private void createCategoryModel() {
        categoryModel = new CartesianChartModel();

    }

    private void createLinearModel() {
        linearModel = new CartesianChartModel();

    }
}
