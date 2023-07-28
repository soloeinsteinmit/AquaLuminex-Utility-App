package com.example.aqualuminexapp.dashboard.home;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

public class Charts {

    /**
     * @param lineChart gets the line graph
     * <p>Creates the line at the home page</p>
     * */
    public void lineChart(LineChart<?,?> lineChart){

        XYChart.Series electricitySeries = new XYChart.Series();
        XYChart.Series waterSeries = new XYChart.Series();

        electricitySeries.setName("Data Set 2");
        electricitySeries.getData().add(new XYChart.Data<>("Jan", 5));
        electricitySeries.getData().add(new XYChart.Data<>("Feb", 10));
        electricitySeries.getData().add(new XYChart.Data<>("Mar", 23));

        electricitySeries.getData().add(new XYChart.Data<>("Apr", 29));
        electricitySeries.getData().add(new XYChart.Data<>("May", 34));

        electricitySeries.getData().add(new XYChart.Data<>("June", 2));
        electricitySeries.getData().add(new XYChart.Data<>("July", 5));
        electricitySeries.getData().add(new XYChart.Data<>("Aug", 12));
        electricitySeries.getData().add(new XYChart.Data<>("Sep", 54));
        electricitySeries.getData().add(new XYChart.Data<>("Oct", 61));
        electricitySeries.getData().add(new XYChart.Data<>("Nov", 29));
        electricitySeries.getData().add(new XYChart.Data<>("Dec", 44));
        electricitySeries.setName("Electricity");



        waterSeries.setName("Data Set 1");
        waterSeries.getData().add(new XYChart.Data<>("Jan", 4));
        waterSeries.getData().add(new XYChart.Data<>("Feb", 0.9));
        waterSeries.getData().add(new XYChart.Data<>("Mar", 0.5));
        waterSeries.getData().add(new XYChart.Data<>("Apr", 21));
        waterSeries.getData().add(new XYChart.Data<>("May", 39));
        waterSeries.getData().add(new XYChart.Data<>("June", 40));
        waterSeries.getData().add(new XYChart.Data<>("July", 11));
        waterSeries.getData().add(new XYChart.Data<>("Aug", 34));
        waterSeries.getData().add(new XYChart.Data<>("Sep", 55));
        waterSeries.getData().add(new XYChart.Data<>("Oct", 9));
        waterSeries.getData().add(new XYChart.Data<>("Nov", 43));
        waterSeries.getData().add(new XYChart.Data<>("Dec", 12));
        waterSeries.setName("Water");


        lineChart.getData().addAll(electricitySeries, waterSeries);
    }
}