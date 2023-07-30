package com.example.aqualuminexapp.dashboard.meter;

import com.example.aqualuminexapp.utils.ChangingScenes;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

public class TopUpPrepaidController implements Initializable {

    public static StackPane topUpStack;
    @FXML
    private AnchorPane topUpPrepaidAnchorPane;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void backToMeterPage(MouseEvent event) {
        ChangingScenes.translateScene(MetersController.class, topUpStack, topUpPrepaidAnchorPane,
                "meters", 'b');
    }
}