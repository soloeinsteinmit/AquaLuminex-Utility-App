package com.example.aqualuminexapp.utils;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ShowToastMessage {
    public static StackPane parentToastStack;
    public static void showToast(Stage ownerStage, String message, double toastDelay,
                                 double fadeInDelay,
                                 double fadeOutDelay) {
        // Create the toast container
        StackPane toastContainer = new StackPane();
        toastContainer.setAlignment(Pos.BOTTOM_CENTER);
        toastContainer.setMinHeight(40);
        // Set the background and border radius to 8
        toastContainer.setStyle("-fx-background-color: rgba(0, 0, 0, 0.7);" +
                " -fx-padding: 10px 35px;" +
                " -fx-background-radius: 4;");

        Label toastLabel = new Label();
        toastLabel.setTextFill(Color.WHITE);

        toastLabel.setFont(Font.font("Segoe UI Semibold", 20));
        //toastLabel.setStyle("-fx-font-family: 'Poppins SemiBold'");

        toastContainer.setMaxWidth(Region.USE_PREF_SIZE);
        toastContainer.setMaxHeight(53);


        toastContainer.getChildren().add(toastLabel);

        Scene scene = ownerStage.getScene();
        parentToastStack = (StackPane) scene.getRoot();
        parentToastStack.getChildren().add(toastContainer);

        toastLabel.setText(message);

        // Fade in
        FadeTransition fadeInTransition = new FadeTransition(Duration.seconds(fadeInDelay), toastContainer);
        fadeInTransition.setToValue(1.0);
        fadeInTransition.play();

        // Delay
        Timeline delayTimeline = new Timeline(new KeyFrame(Duration.seconds(toastDelay)));
        StackPane finalToastStack = parentToastStack;
        delayTimeline.setOnFinished(event -> {
            // Fade out
            FadeTransition fadeOutTransition = new FadeTransition(Duration.seconds(fadeOutDelay), toastContainer);
            fadeOutTransition.setToValue(0.0);
            fadeOutTransition.setOnFinished(e -> finalToastStack.getChildren().remove(toastContainer));
            fadeOutTransition.play();
        });
        delayTimeline.play();
    }
}