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
    private ScheduledExecutorService scheduler;
//    private ProgressIndicator progressIndicator;
    private double progressValue = 1.0; // Initial progress value

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
        electricityProgressSpinner.setProgress(progressValue);
        waterProgressSpinner.setProgress(0.8);

        // Schedule the background task to reduce the progress every 1 minute
        scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(this::reduceProgress, 0, 1, TimeUnit.MINUTES);
    }

    public void drawLineChart(){
        charts = new Charts();
        Thread lineChartThread = new Thread(()-> charts.lineChart(lineChart));
        lineChartThread.start();
    }

    private void reduceProgress(){
        // Update the progress value and set it to the ProgressIndicator
        progressValue -= 0.01; // Reduce the progress value by 0.1 (10%)
        if (progressValue >= 0.0) {
            electricityProgressSpinner.setProgress(progressValue);
            waterProgressSpinner.setProgress(0.82);
        }


        else {
            // If progressValue becomes negative, reset it to 0 and cancel the scheduler
            progressValue = 0.0;
            electricityProgressSpinner.setProgress(progressValue);
            waterProgressSpinner.setProgress(progressValue);
            scheduler.shutdown();
        }
        //TODO: change color of background in real time
        if (progressValue <= 4.0){
            electricityProgressSpinner.setStyle("-fx-fill: rgba(175,43,43,0.26);");
        }
        else if (progressValue <= 8.0){
            electricityProgressSpinner.setStyle("-fx-fill: rgba(43,175,43,0.26);");
        }
    }
}