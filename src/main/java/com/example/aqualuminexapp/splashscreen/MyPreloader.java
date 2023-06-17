package com.example.aqualuminexapp.splashscreen;

import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Objects;

public class MyPreloader extends Preloader {

    private Stage preloaderStage;
    private Scene preloaderScene;

    public MyPreloader() {

    }

    @Override
    public void init() throws Exception {
        Parent preloaderRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("splash-screen.fxml")));
        preloaderScene = new Scene(preloaderRoot);
    }

    @Override
    public void start(Stage primaryStage) {
        this.preloaderStage = primaryStage;

        // set preloader scene and show the stage
        preloaderStage.setScene(preloaderScene);
        preloaderStage.initStyle(StageStyle.UNDECORATED);
        preloaderStage.show();
    }

    @Override
    public void handleApplicationNotification(Preloader.PreloaderNotification info) {

        if (info instanceof ProgressNotification) {
            SplashScreenController.progressCounter.setText(((ProgressNotification) info).getProgress() + " %");
        }
    }

    @Override
    public void handleStateChangeNotification(Preloader.StateChangeNotification info) {
        StateChangeNotification.Type type = info.getType();
        // called after AquaLuminexMain #init and before AquaLuminexMain #start

        switch (type) {
            case BEFORE_LOAD:
                // Called after MyPreloader#start is called.
                System.out.println("BEFORE_LOAD");
                break;
            case BEFORE_INIT:
                // Called before MyApplication#init is called.
                System.out.println("BEFORE_INIT");
                break;
            case BEFORE_START:
                // Called after MyApplication#init and before MyApplication#start is called.
                System.out.println("BEFORE_START");
                preloaderStage.hide();
                break;
        }

    }
}