package com.example.aqualuminexapp;

import com.example.aqualuminexapp.dashboard.home.HomeController;
import com.example.aqualuminexapp.splashscreen.ProgressBarCounterTask;
import com.example.aqualuminexapp.utils.AppSettings;
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
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;

public class AquaLuminexMain extends Application implements Initializable {

    private static final URL[] sound = new URL[1];
    public static AtomicInteger getQuaterLoaderInt = new AtomicInteger();
    public static Stage fileChooserStage;
    private static Stage pStage;
    int firstQuarter = (int) (ProgressBarCounterTask.COUNT_LIMIT);
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
    static  AppSettings appSettings;
    public static Stage messageToastStage;
    public static void setRoot(Stage stage) throws IOException {
        Parent root = loadFXML("login");

        fileChooserStage = stage;

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initStyle(StageStyle.DECORATED);
        messageToastStage = stage;

        // Set the default app icon
        String iconPath = "com/example/aqualuminexapp/images/logo_only.png"; // Replace 'icon.png' with your icon
        // file path
        stage.getIcons().add(new javafx.scene.image.Image(iconPath));


        // closes the meter thread once the window is closed
        //TODO: make is continue until computer is shutdown
        stage.setOnCloseRequest(windowEvent -> {


            /*
            * Runs into null pointer, so we check if scheduler is started before
            * we close the thread task
            * */
            if (HomeController.isSchedulerStarted){
                DecimalFormat df = new DecimalFormat("#.##");
                System.out.println("Scheduler Thread shutdown from main app");

                appSettings.setWaterMeterPercent(Double.parseDouble(df.format(HomeController.waterProgressValue)));
                System.out.println(df.format(HomeController.waterProgressValue) + " water progress");
                appSettings.setElectricityMeterPercent(Double.parseDouble(df.format(HomeController.electricityProgressValue)));
                AppSettings.writeAppSettingsToConfig(appSettings);

                HomeController.schedulerWaterMeter.shutdown();
                HomeController.schedulerElectricityMeter.shutdown();


            }

        });

        stage.setTitle("AquaLuminex Utility App");


        pStage.close();
        stage.setResizable(false);

        stage.show();



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
        appSettings = AppSettings.getAppSettings();

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
    public boolean setRootCalled = false; // Add a boolean flag to keep track if setRoot has already been called

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

            if (t1 >= firstQuarter * 0.25) {
                loadingMessage.setText("Getting things ready...");
            }
            if (t1 >= firstQuarter * 0.5) {
                loadingMessage.setText("Almost there...");
            }
            if (t1 >= firstQuarter * 0.75) {
                loadingMessage.setText("Setting things up...");
            }
            if (t1 >= firstQuarter) {
                loadingMessage.setText("Done");
                System.out.println(t1+" here");
                System.out.println(firstQuarter * 2.9 + " qqq");
            }

            if (controlCounterTask.isDone() && !setRootCalled) {
                System.out.println(t1 +" tooo");
                controlCounterTask.cancel();
                setRootCalled = true; // Set the flag to true to avoid calling setRoot multiple times
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