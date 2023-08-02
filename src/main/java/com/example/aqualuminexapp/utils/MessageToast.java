package com.example.aqualuminexapp.utils;

import com.example.aqualuminexapp.AquaLuminexMain;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 * <p>
 *     This class is for making toast messages
 * </p>
 * */
public final class MessageToast {
    /**
     * @param ownerStage the main stage where the toast message is going to show
     * @param toastMsg message to be displayed the toast message
     * @param toastDelay the duration of the toast message delay
     * @param fadeInDelay duration of fade in transition
     * @param fadeOutDelay duration of fade out transition
     * */
    public static void makeText(Stage ownerStage, String toastMsg, int toastDelay, int fadeInDelay, int fadeOutDelay)
    {
        Stage toastStage = new Stage();
        toastStage.initOwner(ownerStage);
        toastStage.setResizable(false);
        toastStage.initStyle(StageStyle.TRANSPARENT);

        Text text = new Text(toastMsg);
        text.setFont(Font.font("Segoe UI Semibold", 17));
        text.setFill(Color.BLACK);

        StackPane root = new StackPane(text);
        root.setStyle("-fx-background-radius: 5; -fx-background-color: rgba(0, 0, 0, 0.2); -fx-padding: 10px;");
        root.setOpacity(0);

        root.setMinWidth(257);
        root.setMinHeight(53);



        // Calculate the center position of the screen
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        double centerX = screenBounds.getMinX() + (screenBounds.getWidth() - AquaLuminexMain.messageToastStage.getWidth()) / 2;
        double centerY = screenBounds.getMinY() + (screenBounds.getHeight() - AquaLuminexMain.messageToastStage.getHeight()) / 2;

        // Set the layoutX and layoutY of the stage to center it on the screen
        toastStage.setX(centerX);
        toastStage.setY(centerY);

        root.setLayoutX(centerX);
        root.setLayoutY(centerY);
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        toastStage.setScene(scene);
        toastStage.show();

        Timeline fadeInTimeline = new Timeline();
        KeyFrame fadeInKey1 = new KeyFrame(Duration.millis(fadeInDelay), new KeyValue (toastStage.getScene().getRoot().opacityProperty(), 1));
        fadeInTimeline.getKeyFrames().add(fadeInKey1);
        fadeInTimeline.setOnFinished((ae) ->
                new Thread(() -> {
                    try
                    {
                        Thread.sleep(toastDelay);
                    }
                    catch (InterruptedException e)
                    {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    Timeline fadeOutTimeline = new Timeline();
                    KeyFrame fadeOutKey1 = new KeyFrame(Duration.millis(fadeOutDelay), new KeyValue (toastStage.getScene().getRoot().opacityProperty(), 0));
                    fadeOutTimeline.getKeyFrames().add(fadeOutKey1);
                    fadeOutTimeline.setOnFinished((aeb) -> toastStage.close());
                    fadeOutTimeline.play();
                }).start());
        fadeInTimeline.play();
    }
}