package com.example.aqualuminexapp;

import javafx.application.Application;
import javafx.stage.Stage;

public class OtpInputApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
       /* GridPane root = new GridPane();
        root.setPadding(new Insets(10));
        root.setHgap(10);

        int numOfOtpFields = 4;
        TextField[] otpFields = new TextField[numOfOtpFields];

        // Create and configure OTP text fields
        for (int i = 0; i < numOfOtpFields; i++) {

            otpFields[i] = new TextField();

            otpFields[i].setStyle("""
                -fx-pref-height: 60;
                -fx-pref-width: 60;
                -fx-text-alignment: center;
                -fx-alignment: center;
                -fx-background-color: #1b1d60;
                -fx-text-fill: #fff;
                -fx-font-family: 'Poppins Medium';
                -fx-font-weight: bold;
                -fx-font-size: 16px;
                """);

            otpFields[i].setPrefColumnCount(1); // Set the width of the text field to display one character at a time
            otpFields[i].setTextFormatter(new TextFormatter<>(new CharacterStringConverter(), null, c -> {
                if (c.getControlNewText().length() > 1) {
                    return null; // Reject input if more than one character is entered
                } else {
                    return c; // Accept input if it's a single character
                }
            })); // Restrict each field to accept only one character
            int finalI = i;
            otpFields[i].textProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue.length() == 1) {
                    // When the current text field is filled with one character, move focus to the next field
                    int currentIndex = GridPane.getColumnIndex(otpFields[finalI]);
                    if (currentIndex < numOfOtpFields - 1) {
                        otpFields[finalI + 1].requestFocus();
                    }
                } else if (newValue.isEmpty() && !oldValue.isEmpty()) {
                    // When the current text field is cleared (by pressing backspace), move focus to the previous field
                    int currentIndex = GridPane.getColumnIndex(otpFields[finalI]);
                    if (currentIndex > 0) {
                        otpFields[finalI - 1].requestFocus();
                    }
                }
            });

            root.add(otpFields[i], i, 0);
        }

        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("OTP Input");
        primaryStage.show();

        // Focus the first text field when the application starts
        otpFields[0].requestFocus();*/
    }
}