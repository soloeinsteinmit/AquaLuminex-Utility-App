package com.example.aqualuminexapp.dashboard.home;

import io.github.palexdev.materialfx.controls.MFXProgressSpinner;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class HomeController implements Initializable {

    Charts charts;
    public static ScheduledExecutorService scheduler;
    public static boolean isSchedulerStarted = false;
//    private ProgressIndicator progressIndicator;
    private double waterProgressValue = 1.0; // Initial progress value
    private double electricityProgressValue = 0.83;

    @FXML
    private MFXProgressSpinner electricityProgressSpinner;

    @FXML
    private LineChart<String, Integer> lineChart;

    @FXML
    private MFXProgressSpinner waterProgressSpinner;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        drawLineChart();

//        progressIndicator = new ProgressIndicator();
        // initialize progress value to 1
        electricityProgressSpinner.setProgress(electricityProgressValue);
        waterProgressSpinner.setProgress(waterProgressValue);

        // Schedule the background task to reduce the progress every 1 minute
        isSchedulerStarted = true;
        scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(this::reduceProgress, 0, 1, TimeUnit.MINUTES);
    }

    public void drawLineChart(){
        charts = new Charts();
        charts.lineChart(lineChart);
        Thread lineChartThread = new Thread(()-> charts.lineChart(lineChart));
//        lineChartThread.start();
    }

    private void reduceProgress(){
        // Update the progress value and set it to the ProgressIndicator
        waterProgressValue -= 0.01; // Reduce the progress value by 0.1 (10%)

        if (waterProgressValue >= 0.0) {
            electricityProgressSpinner.setProgress(electricityProgressValue);
            waterProgressSpinner.setProgress(waterProgressValue);
        }

        else {
            // If progressValue becomes negative, reset it to 0 and cancel the scheduler
            waterProgressValue = 0.0;
            waterProgressSpinner.setProgress(waterProgressValue);
            scheduler.shutdown();
        }

        electricityProgressValue -= 0.01;
        if (electricityProgressValue >= 0.0) {
            electricityProgressSpinner.setProgress(electricityProgressValue);
        }

        else {
            // If progressValue becomes negative, reset it to 0 and cancel the scheduler
            electricityProgressValue = 0.0;
            electricityProgressSpinner.setProgress(electricityProgressValue);
            scheduler.shutdown();
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