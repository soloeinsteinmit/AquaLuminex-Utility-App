package com.example.aqualuminexapp.dashboard.home;

import com.example.aqualuminexapp.utils.AppSettings;
import io.github.palexdev.materialfx.controls.MFXProgressSpinner;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class HomeController implements Initializable {

    Charts charts;
    AppSettings appSettings;
    public static ScheduledExecutorService schedulerWaterMeter;
    public static ScheduledExecutorService schedulerElectricityMeter;
    public static boolean isSchedulerStarted = false;
//    private ProgressIndicator progressIndicator;
    public static double waterProgressValue = 0.89;// Initial progress value
    public static double electricityProgressValue = 0.56;

    @FXML
    private MFXProgressSpinner electricityProgressSpinner;

    @FXML
    private LineChart<String, Integer> lineChart;

    @FXML
    private MFXProgressSpinner waterProgressSpinner;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DecimalFormat df = new DecimalFormat("#.#");
        appSettings = AppSettings.getAppSettings();
        waterProgressValue = appSettings.getWaterMeterPercent();
        electricityProgressValue = appSettings.getElectricityMeterPercent();

        drawLineChart();

//        progressIndicator = new ProgressIndicator();
        // initialize progress value to 1
        electricityProgressSpinner.setProgress(electricityProgressValue);
        waterProgressSpinner.setProgress(waterProgressValue);

        // Schedule the background task to reduce the progress every 1 minute
        isSchedulerStarted = true;
        schedulerWaterMeter = Executors.newScheduledThreadPool(1);

        schedulerWaterMeter.scheduleAtFixedRate(this::reduceProgressMeter, 0, 1, TimeUnit.MINUTES);
        schedulerElectricityMeter = Executors.newScheduledThreadPool(1);
        schedulerElectricityMeter.scheduleAtFixedRate(this::reduceProgressElectricity, 0, 1, TimeUnit.MINUTES);
    }

    public void drawLineChart(){
        charts = new Charts();
        charts.lineChart(lineChart);
        Thread lineChartThread = new Thread(()-> charts.lineChart(lineChart));
//        lineChartThread.start();
    }

    private void reduceProgressMeter(){
        // Update the progress value and set it to the ProgressIndicator
        waterProgressValue -= 0.01; // Reduce the progress value by 0.1 (10%)

        if (waterProgressValue >= 0.0) {

            waterProgressSpinner.setProgress(waterProgressValue);
        }

        else {
            // If progressValue becomes negative, reset it to 0 and cancel the scheduler
            waterProgressValue = 0.0;
            waterProgressSpinner.setProgress(waterProgressValue);
            schedulerWaterMeter.shutdown();
        }



        //TODO: change color of background in real time
       /* if (waterProgressValue <= 4.0){
            electricityProgressSpinner.setStyle("-fx-fill: rgba(175,43,43,0.26);");
        }
        else if (waterProgressValue <= 8.0){
            electricityProgressSpinner.setStyle("-fx-fill: rgba(43,175,43,0.26);");
        }*/
    }
    private void reduceProgressElectricity(){
        // Update the progress value and set it to the ProgressIndicator

        electricityProgressValue -= 0.01;
        if (electricityProgressValue >= 0.0) {
            electricityProgressSpinner.setProgress(electricityProgressValue);
        }

        else {
            // If progressValue becomes negative, reset it to 0 and cancel the scheduler
            electricityProgressValue = 0.0;
            electricityProgressSpinner.setProgress(electricityProgressValue);
            schedulerElectricityMeter.shutdown();
        }

        //TODO: change color of background in real time
       /* if (waterProgressValue <= 4.0){
            electricityProgressSpinner.setStyle("-fx-fill: rgba(175,43,43,0.26);");
        }
        else if (waterProgressValue <= 8.0){
            electricityProgressSpinner.setStyle("-fx-fill: rgba(43,175,43,0.26);");
        }*/
    }
}