package com.example.aqualuminexapp.utils;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class TimerClass {
    private static final int MAX_TIME = 900; // 15 minutes in seconds
    public static Timeline timeline;
    private final Label timerLabel;
    private final MFXButton generateId;
    private int secondsElapsed = 0;

    public TimerClass(Label timerLabel, MFXButton generateId) {
        this.timerLabel = timerLabel;
        this.generateId = generateId;
    }

    public void createTimeline() {
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            secondsElapsed++;
            updateTimerLabel();

        }));
        timeline.setCycleCount(MAX_TIME);

        timeline.play();


        timeline.setOnFinished(event1 -> {
            // TODO: Fade in and fade out timer label on timeout
            timerLabel.setText("15:00");
            System.out.println("Time up");
            generateId.setDisable(false);
        });

    }

    private void updateTimerLabel() {
        int minutes = secondsElapsed / 60;
        int seconds = secondsElapsed % 60;

        String formattedTime = String.format("%02d:%02d", minutes, seconds);
        if (formattedTime.equals("01:00")) {

            timerLabel.setStyle("-fx-text-fill: red");
        }
        timerLabel.setText(formattedTime);
    }

}