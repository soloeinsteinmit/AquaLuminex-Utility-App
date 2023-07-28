package com.example.aqualuminexapp.utils;

import io.github.palexdev.materialfx.controls.MFXProgressSpinner;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import tray.notification.NotificationType;

import java.io.IOException;
import java.util.Objects;

public class ChangingScenes {
    /**
     * @param className       class to get resources from.
     * @param parentContainer container where transitions happens.
     * @param container       content to be changed.
     * @param filename        the name of the fxml file you want to change
     * @param direction       the transition direction weather front(f) or back(b)
     * @code for making transitions in the application
     */
    public static void translateScene(Class<?> className, StackPane parentContainer, AnchorPane container,
                                      String filename,
                                      char direction) {

        Parent fxmlName;

        try {
            fxmlName = FXMLLoader.load(
                    Objects.requireNonNull(className.getResource(filename + ".fxml")));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        parentContainer.getChildren().removeAll();
        parentContainer.getChildren().setAll(fxmlName);

        if (direction == 'b') {
            fxmlName.translateXProperty().set(-container.getWidth());
        } else {
            fxmlName.translateXProperty().set(container.getWidth());
        }


        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(fxmlName.translateXProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.seconds(0.3), kv);
        timeline.getKeyFrames().add(kf);
        timeline.setOnFinished(event1 -> parentContainer.getChildren().remove(container));
        timeline.play();


    }

    /**
     * @param event the mouse event that results in the changing of the scnene
     * @param className the name of the class you are changing the window to
     * @param fxmlFileName the name of the fxml file you are changing to
     *
     * <p><br>This method changes the window </p>
    * */
    public static void changeWindow(MouseEvent event,Class<?> className, String fxmlFileName) {
//        Thread changeWindowThread = new Thread(()->{
            Parent root;
            try {
                root = FXMLLoader.load(Objects.requireNonNull(className.getResource(fxmlFileName + ".fxml")));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
//        });
//        changeWindowThread.start();


    }

    /**
     * @param fxmlFileName name of fxml to be changed to.
     */
    public static void progressSpinner(MouseEvent event, String fxmlFileName, MFXProgressSpinner spinner) throws IOException {
        Task<Parent> fxmlTask = new Task<Parent>() {
            @Override
            protected Parent call() throws Exception {
                spinner.setVisible(true);
                return FXMLLoader.load(
                        Objects.requireNonNull(getClass().getResource(fxmlFileName + ".fxml")));
            }
        };
        fxmlTask.setOnSucceeded(e -> {
            spinner.setVisible(false);
            Parent root = fxmlTask.getValue();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        });
        fxmlTask.setOnFailed(e -> {
            spinner.setVisible(false);
            AlertNotification.trayNotification("ERROR", "INVALID CREDENTIALS... PLEASE TRY AGAIN",
                    4, NotificationType.ERROR);
        });
        Thread thread = new Thread(fxmlTask);
        thread.start();

    }

    public void initializeScene(Class<?> className, StackPane container, String fxmlFileName) {
        Parent fxml;
        try {
            fxml = FXMLLoader.load(Objects.requireNonNull(className.getResource(
                    fxmlFileName + ".fxml")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        container.getChildren().removeAll();
        container.getChildren().setAll(fxml);
    }


    /*public static void translateFoward(StackPane parentContainer, AnchorPane container, String filename) {
        Parent fxmlName;
        try {
            fxmlName = FXMLLoader.load(
                    Objects.requireNonNull(ChangingScenes.class.getResource(filename + ".fxml")));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        parentContainer.getChildren().removeAll();
        parentContainer.getChildren().setAll(fxmlName);

        fxmlName.translateXProperty().set(container.getWidth());
        timeline = new Timeline();
        KeyValue kv = new KeyValue(fxmlName.translateXProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.seconds(0.3), kv);
        timeline.getKeyFrames().add(kf);
        timeline.setOnFinished(event1 -> parentContainer.getChildren().remove(container));
        timeline.play();
    }*/

}