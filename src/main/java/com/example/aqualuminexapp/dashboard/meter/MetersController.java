package com.example.aqualuminexapp.dashboard.meter;

import com.example.aqualuminexapp.utils.ChangingScenes;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

public class MetersController implements Initializable {

    @FXML
    private MFXButton addElectricityMeter;

    @FXML
    private MFXButton addWaterMeter;

    @FXML
    private MFXButton deleteElectricityMeter;

    @FXML
    private MFXButton deleteWaterMeter;

    @FXML
    private Label meterIdLabel;

    @FXML
    private Label meterNameLabel;

    @FXML
    private AnchorPane metersAnchorPane;

    @FXML
    private StackPane metersParentStackPane;

    @FXML
    private ImageView postpaidMeter1_img;

    @FXML
    private ImageView prepaidMeter1_img;

    @FXML
    private Label waterMeterId;

    @FXML
    private Label waterMeterName;
    public static StackPane METERS_STACKPANE;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //TopUpPrepaidController.topUpStack = metersParentStackPane;
        TopUpPostPaidController.topUpStack = metersParentStackPane;
        METERS_STACKPANE = metersParentStackPane;

    }

    @FXML
    void payBill() {
        ChangingScenes.translateScene(TopUpPostPaidController.class, metersParentStackPane, metersAnchorPane,
                "top_up_postpaid_water", 'f');
    }

    @FXML
    void topUpPrepaid() {
        ChangingScenes.translateScene(TopUpPrepaidController.class, metersParentStackPane, metersAnchorPane,
                "top_up_prepaid", 'f');
    }

    @FXML
    void addElectricityMeter() {
        ChangingScenes.translateScene(AddElectricityMeterController.class, metersParentStackPane, metersAnchorPane,
                "addElectricityMeter", 'f');
    }

    @FXML
    void addWaterMeter() {
        ChangingScenes.translateScene(AddElectricityMeterController.class, metersParentStackPane, metersAnchorPane,
                "addWaterMeter", 'f');
    }

    @FXML
    void deleteElectricityMeter(MouseEvent event) {

    }

    @FXML
    void deleteWaterMeter(MouseEvent event) {

    }


    @FXML
    void openElectricityMeterInfo(){
        ChangingScenes.translateScene(ElectricityMeterInfoController.class, metersParentStackPane, metersAnchorPane,
                "electricity_meter_info", 'f');
    }
    @FXML
    void openWaterMeterInfo(){
        ChangingScenes.translateScene(WaterMeterInfoController.class, metersParentStackPane, metersAnchorPane,
                "water_meter_info", 'f');
    }
}