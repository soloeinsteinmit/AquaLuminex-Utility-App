package com.example.aqualuminexapp.utils;

import com.example.aqualuminexapp.register.RegisterMainController;
import io.github.palexdev.materialfx.controls.MFXProgressBar;
import javafx.application.Platform;
import javafx.scene.image.ImageView;

public class ProgressBarAnimationForward implements Runnable {

    private static double progress;
    MFXProgressBar progressBar;
    ImageView doneImg;

    public ProgressBarAnimationForward(MFXProgressBar progressBar, ImageView doneImg) {
        this.progressBar = progressBar;
        this.doneImg = doneImg;
    }

    @Override
    public void run() {

        while (progressBar.getProgress() <= 1) {
//            System.out.println(progressBar.getProgress() + " forward");

            Platform.runLater(() -> {
                progress = Math.round((progressBar.getProgress() + 0.1) * 10.0) / 10.0;
                progressBar.setProgress(progress);
                progressBar.setPrefHeight(5);
                if ((int) progressBar.getProgress() == 1 && RegisterMainController.isDone) {
//                    System.out.println("progress num = " + progressBar.getProgress());
                    doneImg.setVisible(true);
                }
//                progressBar.progressProperty().bind(progressBar.getProgress());
            });


            synchronized (this) {
                try {
                    Thread.sleep(50);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println("am done here");
                }
            }
        }
        System.out.println("moving to next");
    }

//    @Override
//    protected Double call() throws Exception {
//        double trackProgressCounter = 0;
//
//        while (progressBar.getProgress() <= 1) {
//
//            trackProgressCounter = progressBar.getProgress() + 0.1;
//            System.out.println(trackProgressCounter);
//            System.out.println(progressBar.getProgress());
//            progressBar.setProgress(trackProgressCounter);
//            System.out.println(trackProgressCounter);
//            System.out.println(progressBar.getProgress());
//            if (trackProgressCounter == 1) {
//                System.out.println("progress num = " + trackProgressCounter);
//            }
//
//            updateProgress(trackProgressCounter, 1);
//        }
//        return trackProgressCounter;
//    }
}