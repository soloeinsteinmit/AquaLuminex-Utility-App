package com.example.aqualuminexapp.dashboard.meter;

import com.example.aqualuminexapp.utils.ChangingScenes;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class WaterMeterInfoController implements Initializable {
    @FXML
    private AnchorPane waterMeterAnchorPane;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void backToMeters(){
        ChangingScenes.translateScene(MetersController.class, MetersController.METERS_STACKPANE, waterMeterAnchorPane,
                "meters", 'b');
    }
}