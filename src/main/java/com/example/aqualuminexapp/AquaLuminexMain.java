package com.example.aqualuminexapp;

import com.example.aqualuminexapp.splashscreen.ProgressBarCounterTask;
import io.github.palexdev.materialfx.controls.MFXProgressBar;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;

public class AquaLuminexMain extends Application implements Initializable {

    private static final URL[] sound = new URL[1];
    public static AtomicInteger getQuaterLoaderInt = new AtomicInteger();
    public static Stage fileChooserStage;
    private static Stage pStage;
    int firstQuarter = (int) (ProgressBarCounterTask.COUNT_LIMIT / 3);
    //    public static final int COUNT_LIMIT = 500000;
    private ProgressBarCounterTask controlCounterTask;
    //    int firstQuarter = 166666666;
    private Clip clip;
    @FXML
    private Circle circle;
    @FXML
    private ImageView logoDotImg;
    @FXML
    private MFXProgressBar progressBarCounter;
    @FXML
    private Label loadingMessage;
    @FXML
    private AnchorPane splashScreenParentContainer;
    @FXML
    private Label progressCounterLabel;

    public static void main(String[] args) {
//        LauncherImpl.launchApplication(AquaLuminexMain.class, MyPreloader.class, args);
        launch();
    }

    public static void setRoot(Stage stage) throws IOException {
        Parent root = loadFXML("login");

        fileChooserStage = stage;

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initStyle(StageStyle.DECORATED);

        // Set the default app icon
        String iconPath = "com/example/aqualuminexapp/images/logo_only.png"; // Replace 'icon.png' with your icon
        // file path
        stage.getIcons().add(new javafx.scene.image.Image(iconPath));
        stage.setTitle("AquaLuminex Utility App");


        pStage.close();
        stage.setResizable(false);

        for (int i = 1; i <= 1; i++) {
            stage.show();
        }


    }

    /**
     * Gets fxml file to be loaded.
     *
     * @param fxml the path to the fxml file
     * @return the fxml file to be loaded.
     * @throws IOException throws when getting fxml
     */
    private static Parent loadFXML(final String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(
                AquaLuminexMain.class.getResource(fxml + ".fxml")
        );
        return fxmlLoader.load();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("splash-screen.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setTitle("AquaLuminex Utility App");
        stage.setScene(scene);
        pStage = stage;
        pStage.setResizable(false);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        sound[0] = getClass().getResource("intro.wav");
        invokeControlCounterTask();
        try {
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(sound[0]);
            clip = AudioSystem.getClip();
            clip.open(inputStream);
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
        Thread circlePath = new Thread(() ->{


            Line line = new Line();
            line.setStartX(-500);
            line.setEndX(265);
            PathTransition pt = new PathTransition();
            pt.setNode(circle);
            pt.setDuration(Duration.seconds(10));
            pt.setPath(line);
            pt.play();
            clip.start();
            pt.setOnFinished(event -> {
                FadeTransition fadeOut = new FadeTransition(Duration.seconds(2), splashScreenParentContainer);
                fadeOut.setCycleCount(1);
                fadeOut.play();

            });
        });
        circlePath.start();

    }

    private void invokeControlCounterTask() {
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setNode(loadingMessage);
        fadeTransition.setCycleCount(Animation.INDEFINITE);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.setInterpolator(Interpolator.LINEAR);
        fadeTransition.setDelay(Duration.seconds(5));
        fadeTransition.setDuration(Duration.seconds(2));

        controlCounterTask = new ProgressBarCounterTask();
        Thread counterTaskThread = new Thread(controlCounterTask);

        progressBarCounter.progressProperty().bind(controlCounterTask.progressProperty());
        controlCounterTask.valueProperty().addListener((observableValue, aLong, t1) -> {
            fadeTransition.play();

            if (t1 >= firstQuarter * 0.5) {
                loadingMessage.setText("Getting things ready...");
            }
            if (t1 >= firstQuarter * 1.9) {
                loadingMessage.setText("Almost there...");
            }
            if (t1 >= firstQuarter * 2.4) {
                loadingMessage.setText("Setting things up...");
            }
            if (t1 >= firstQuarter * 2.9) {

                loadingMessage.setText("Done");
            }

            if (controlCounterTask.isDone()) {
                controlCounterTask.cancel();
                try {

                    setRoot(new Stage());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

        });


        counterTaskThread.setDaemon(true);
        counterTaskThread.start();


    }
}