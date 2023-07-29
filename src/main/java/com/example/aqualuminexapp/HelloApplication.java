package com.example.aqualuminexapp;

import com.example.aqualuminexapp.dashboard.home.HomeController;
import com.example.aqualuminexapp.register.RegisterMainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(DashboardController.class.getResource("dashboard.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader(RegisterMainController.class.getResource("register-main.fxml"));
//        FXMLLoader fxmlLoader = new FXMLLoader(PasswordSecurityController.class.getResource("password-security.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
        stage.setScene(scene);

        //TODO: make is continue until computer is shutdown
        stage.setOnCloseRequest(windowEvent -> {
            System.out.println("Scheduler Thread shutdown from main app");
            if (HomeController.isSchedulerStarted){
                HomeController.scheduler.shutdown();
            }
        });
        stage.show();
    }
}