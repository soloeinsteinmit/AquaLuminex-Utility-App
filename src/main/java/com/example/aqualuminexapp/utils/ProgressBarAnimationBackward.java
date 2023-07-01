package com.example.aqualuminexapp.utils;

import io.github.palexdev.materialfx.controls.MFXProgressBar;
import javafx.application.Platform;

public class ProgressBarAnimationBackward implements Runnable {

    private static double progress;
    MFXProgressBar progressBar;

    public ProgressBarAnimationBackward(MFXProgressBar progressBar) {
        this.progressBar = progressBar;
    }

    @Override
    public void run() {

        progressBar.setProgress(1.0);

        while (progressBar.getProgress() != 0.0) {
//            System.out.println(progressBar.getProgress() + "backward");

            Platform.runLater(() -> {
                progress = Math.round((progressBar.getProgress() - 0.1) * 10.0) / 10.0;
                progressBar.setProgress(progress);

//                System.out.println("progress num back counter= " + progressBar.getProgress());
                if (progressBar.getProgress() == 0.0) {

//                    System.out.println("progress num back      = " + progressBar.getProgress());
                    progressBar.setPrefHeight(4);

                }
            });
            if (progressBar.getProgress() < 0.0) {
                System.out.println("break");
                break;
            }
            synchronized (this) {
                try {
                    Thread.sleep(50);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println("am done here");
                }
            }
        }
        System.out.println("moving to back");
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