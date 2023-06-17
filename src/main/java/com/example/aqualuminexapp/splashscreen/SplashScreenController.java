package com.example.aqualuminexapp.splashscreen;

import io.github.palexdev.materialfx.controls.MFXProgressBar;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class SplashScreenController implements Initializable {

    public static Label progressCounter;
    @FXML
    private Label loadingMessage;
    @FXML
    private MFXProgressBar progressBarBottom;
    @FXML
    private MFXProgressBar progressBarLeft;
    @FXML
    private MFXProgressBar progressBarTop;
    @FXML
    private Label progressCounterLabel;
    @FXML
    private AnchorPane splashScreenParentContainer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // sets progress counter to that of teh fxml
        progressCounter = progressCounterLabel;
    }
}