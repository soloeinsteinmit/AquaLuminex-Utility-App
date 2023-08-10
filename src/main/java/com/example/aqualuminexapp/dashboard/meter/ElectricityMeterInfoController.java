package com.example.aqualuminexapp.dashboard.meter;

import com.example.aqualuminexapp.utils.ChangingScenes;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ElectricityMeterInfoController implements Initializable {
    @FXML
    private AnchorPane electricityMeterAnchorPane;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    @FXML
    void backToMeters(){
        ChangingScenes.translateScene(MetersController.class, MetersController.METERS_STACKPANE, electricityMeterAnchorPane,
                "meters", 'b');
    }
    @FXML
    void buyCredit() {
        ChangingScenes.translateScene(TopUpPrepaidController.class, MetersController.METERS_STACKPANE, electricityMeterAnchorPane,
                "top_up_prepaid", 'f');
    }
}